package com.company.rest.api;

import groovy.json.JsonSlurper

import javax.servlet.http.HttpServletRequest

import org.bonitasoft.web.extension.ResourceProvider
import org.bonitasoft.web.extension.rest.RestApiResponseBuilder

import spock.lang.Specification
import org.bonitasoft.web.extension.rest.RestAPIContext

import java.time.LocalDate;

/**
 * @see http://spockframework.github.io/spock/docs/
 */
class RequestListTest extends Specification {

/*
    def httpRequest = Mock(HttpServletRequest)
    def resourceProvider = Mock(ResourceProvider)
    def context = Mock(RestAPIContext)


    def setup(){

        context.resourceProvider >> resourceProvider
        resourceProvider.getResourceAsStream("configuration.properties") >> RequestListTest.class.classLoader.getResourceAsStream("testConfiguration.properties")
    }

    def should_return_a_json_representation_as_result() {
        given: "a RestAPIController"
        def index = new Index()

        httpRequest.getParameter("p") >> "aValue1"
        httpRequest.getParameter("c") >> "aValue2"
        httpRequest.getParameter("caseId") >> "aValue3"
        httpRequest.getParameter("requestStatus") >> "aValue4"
        httpRequest.getParameter("dateOrder") >> "aValue5"

        when: "Invoking the REST API"
        def apiResponse = index.doHandle(httpRequest, new RestApiResponseBuilder(), context)

        then: "A JSON representation is returned in response body"
        def jsonResponse = new JsonSlurper().parseText(apiResponse.response)

        apiResponse.httpStatus == 200
        jsonResponse.p == "aValue1"
        jsonResponse.c == "aValue2"
        jsonResponse.caseId == "aValue3"
        jsonResponse.requestStatus == "aValue4"
        jsonResponse.dateOrder == "aValue5"
        jsonResponse.myParameterKey == "testValue"
        jsonResponse.currentDate == LocalDate.now().toString()
    }

    def should_return_an_error_response_if_p_is_not_set() {
        given: "a request without p"
        def index = new Index()
        httpRequest.getParameter("p") >> null

        httpRequest.getParameter("c") >> "aValue2"
        httpRequest.getParameter("caseId") >> "aValue3"
        httpRequest.getParameter("requestStatus") >> "aValue4"
        httpRequest.getParameter("dateOrder") >> "aValue5"

        when: "Invoking the REST API"
        def apiResponse = index.doHandle(httpRequest, new RestApiResponseBuilder(), context)

        then: "A JSON response is returned with a HTTP Bad Request Status (400) and an error message in body"
        def jsonResponse = new JsonSlurper().parseText(apiResponse.response)

        apiResponse.httpStatus == 400
        jsonResponse.error == "the parameter p is missing"
    }

    def should_return_an_error_response_if_c_is_not_set() {
        given: "a request without c"
        def index = new Index()
        httpRequest.getParameter("c") >> null

        httpRequest.getParameter("p") >> "aValue1"
        httpRequest.getParameter("caseId") >> "aValue3"
        httpRequest.getParameter("requestStatus") >> "aValue4"
        httpRequest.getParameter("dateOrder") >> "aValue5"

        when: "Invoking the REST API"
        def apiResponse = index.doHandle(httpRequest, new RestApiResponseBuilder(), context)

        then: "A JSON response is returned with a HTTP Bad Request Status (400) and an error message in body"
        def jsonResponse = new JsonSlurper().parseText(apiResponse.response)

        apiResponse.httpStatus == 400
        jsonResponse.error == "the parameter c is missing"
    }

    def should_return_an_error_response_if_caseId_is_not_set() {
        given: "a request without caseId"
        def index = new Index()
        httpRequest.getParameter("caseId") >> null

        httpRequest.getParameter("p") >> "aValue1"
        httpRequest.getParameter("c") >> "aValue2"
        httpRequest.getParameter("requestStatus") >> "aValue4"
        httpRequest.getParameter("dateOrder") >> "aValue5"

        when: "Invoking the REST API"
        def apiResponse = index.doHandle(httpRequest, new RestApiResponseBuilder(), context)

        then: "A JSON response is returned with a HTTP Bad Request Status (400) and an error message in body"
        def jsonResponse = new JsonSlurper().parseText(apiResponse.response)

        apiResponse.httpStatus == 400
        jsonResponse.error == "the parameter caseId is missing"
    }

    def should_return_an_error_response_if_requestStatus_is_not_set() {
        given: "a request without requestStatus"
        def index = new Index()
        httpRequest.getParameter("requestStatus") >> null

        httpRequest.getParameter("p") >> "aValue1"
        httpRequest.getParameter("c") >> "aValue2"
        httpRequest.getParameter("caseId") >> "aValue3"
        httpRequest.getParameter("dateOrder") >> "aValue5"

        when: "Invoking the REST API"
        def apiResponse = index.doHandle(httpRequest, new RestApiResponseBuilder(), context)

        then: "A JSON response is returned with a HTTP Bad Request Status (400) and an error message in body"
        def jsonResponse = new JsonSlurper().parseText(apiResponse.response)
 
        apiResponse.httpStatus == 400
        jsonResponse.error == "the parameter requestStatus is missing"
    }

    def should_return_an_error_response_if_dateOrder_is_not_set() {
        given: "a request without dateOrder"
        def index = new Index()
        httpRequest.getParameter("dateOrder") >> null

        httpRequest.getParameter("p") >> "aValue1"
        httpRequest.getParameter("c") >> "aValue2"
        httpRequest.getParameter("caseId") >> "aValue3"
        httpRequest.getParameter("requestStatus") >> "aValue4"

        when: "Invoking the REST API"
        def apiResponse = index.doHandle(httpRequest, new RestApiResponseBuilder(), context)

        then: "A JSON response is returned with a HTTP Bad Request Status (400) and an error message in body"
        def jsonResponse = new JsonSlurper().parseText(apiResponse.response)

        apiResponse.httpStatus == 400
        jsonResponse.error == "the parameter dateOrder is missing"
    }
	*/

}