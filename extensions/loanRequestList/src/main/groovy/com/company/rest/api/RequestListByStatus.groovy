package com.company.rest.api;

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.time.format.DateTimeFormatter;
import org.apache.http.HttpHeaders
import org.bonitasoft.engine.bpm.flownode.HumanTaskInstance
import org.bonitasoft.engine.identity.UserMembershipCriterion
import org.bonitasoft.web.extension.ResourceProvider
import org.bonitasoft.web.extension.rest.RestApiResponse
import org.bonitasoft.web.extension.rest.RestApiResponseBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.company.model.BLRequest
import com.company.model.BLRequestDAO

import org.bonitasoft.web.extension.rest.RestAPIContext
import org.bonitasoft.web.extension.rest.RestApiController

import java.time.LocalDate

class RequestListByStatus implements RestApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestListByStatus.class)

    @Override
    RestApiResponse doHandle(HttpServletRequest request, RestApiResponseBuilder responseBuilder, RestAPIContext context) {
        def p = request.getParameter "p"
		def RISK_TEAM_GROUP_NAME = "risk";
		p = p
        if (p == null) {
            return buildResponse(responseBuilder, HttpServletResponse.SC_BAD_REQUEST,"""{"error" : "the parameter p is missing"}""")
        }

        def c = request.getParameter "c"
        if (c == null) {
            return buildResponse(responseBuilder, HttpServletResponse.SC_BAD_REQUEST,"""{"error" : "the parameter c is missing"}""")
        }

        def statusList = request.getParameter "statusList"
        if (statusList == null) {
            return buildResponse(responseBuilder, HttpServletResponse.SC_BAD_REQUEST,"""{"error" : "the parameter statusList is missing"}""")
        }
		def session = context.apiSession;
		def userId = session.userId;
		def identityApi = context.apiClient.identityAPI;
		def memberships = identityApi.getUserMemberships(userId, 0, 10, UserMembershipCriterion.ASSIGNED_DATE_ASC);
		def isRiskTeam = memberships.any { m ->
			identityApi.getGroup(m.groupId).name == RISK_TEAM_GROUP_NAME;
		}
		
		
		
		def processApi = context.apiClient.processAPI;
		statusList = statusList.split(",").findAll{ it } as String[];
		def requestDAO = context.apiClient.getDAO(BLRequestDAO.class);
		def isStatusListEmpty = statusList.length == 0;
		def requests, requestCount;
		if (isStatusListEmpty) {
			requests = requestDAO.find(p as int, c as int)
			requestCount = requestDAO.countForFind();
		} else {
			requests = requestDAO.findByStatus(statusList, p as int, c as int)
			requestCount = requestDAO.countForFindByStatus(statusList);
		}
		

		def CHECK_REQUEST_TASK_NAME= "Check loan application";
		def CHECK_REQUEST_BY_RISK_TEAM = "Risk manager check";
		def DOCUMENT_LIST_NAME = "attachments";
		def checkTaskId, riskTaskId;
		def requestResponseList = [];
		for(BLRequest loanRequest : requests) {
			def requestResponse = loanRequest.properties.findAll { k, v -> !(k in ['class','metaClass']) }
			def List<HumanTaskInstance> taskList = processApi.getHumanTaskInstances(loanRequest.processInstanceId, CHECK_REQUEST_TASK_NAME, 0, 1);
			checkTaskId = taskList?.getAt(0)?.id;
			if(isRiskTeam) {
				def List<HumanTaskInstance> riskTaskList = processApi.getHumanTaskInstances(loanRequest.processInstanceId, CHECK_REQUEST_BY_RISK_TEAM, 0, 1);
				riskTaskId = riskTaskList?.getAt(0)?.id;
			}
			
	
			def documentList = processApi.getDocumentList(loanRequest.processInstanceId, DOCUMENT_LIST_NAME, 0, 10);
			
			//documents.forEach { t -> processApi.getDocumentContent(t.contentStorageId)}
			def attachments = documentList.collect{ doc -> 
				/*def bytes = processApi.getDocumentContent(doc.contentStorageId)
				def base64 = Base64.encoder.encodeToString(bytes)*/
				def mimeType = doc.contentMimeType;
				[
					name: doc.name,
					fileName: doc.contentFileName,
					mimeType: doc.contentMimeType,
					contentStorageId: doc.contentStorageId
					//content: "data:${mimeType};base64,${base64}"
				]
			}
			
			requestResponse.put("attachments",attachments);
			requestResponse.put("taskId",checkTaskId);
			requestResponse.put("riskTaskId",riskTaskId);
			requestResponse.put("createdAt", loanRequest.createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE))
			requestResponse.put("updatedAt", loanRequest.updatedAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
			requestResponseList.add(requestResponse);
		}
		


        def result = ["requests":requestResponseList, "count": requestCount ];

        return buildResponse(responseBuilder, HttpServletResponse.SC_OK, new JsonBuilder(result).toString())
    }

    /**
     * Build an HTTP response.
     *
     * @param  responseBuilder the Rest API response builder
     * @param  httpStatus the status of the response
     * @param  body the response body
     * @return a RestAPIResponse
     */
    RestApiResponse buildResponse(RestApiResponseBuilder responseBuilder, int httpStatus, Serializable body) {
        return responseBuilder.with {
            withResponseStatus(httpStatus)
            withResponse(body)
            build()
        }
    }
	

    /**
     * Returns a paged result like Bonita BPM REST APIs.
     * Build a response with a content-range.
     *
     * @param  responseBuilder the Rest API response builder
     * @param  body the response body
     * @param  p the page index
     * @param  c the number of result per page
     * @param  total the total number of results
     * @return a RestAPIResponse
     */
    RestApiResponse buildPagedResponse(RestApiResponseBuilder responseBuilder, Serializable body, int p, int c, long total) {
        return responseBuilder.with {
            withContentRange(p,c,total)
            withResponse(body)
            build()
        }
    }

    /**
     * Load a property file into a java.util.Properties
     */
    Properties loadProperties(String fileName, ResourceProvider resourceProvider) {
        Properties props = new Properties()
        resourceProvider.getResourceAsStream(fileName).withStream { InputStream s ->
            props.load s
        }
        props
    }

}
