class RequestStatus {
	static final RequestStatus PENDING    = new RequestStatus("pending")
	static final RequestStatus SUBMITTED  = new RequestStatus("submitted")
	static final RequestStatus APPROVED   = new RequestStatus("approved")
	static final RequestStatus REJECTED   = new RequestStatus("rejected")
	static final RequestStatus DOCUMENT_INVALID   = new RequestStatus("document_invalid")

	private final String value

	private RequestStatus(String value) {
		this.value = value
	}

	String getValue() {
		return value
	}

	@Override
	String toString() {
		return value
	}

	static RequestStatus fromValue(String val) {
		[PENDING, SUBMITTED, APPROVED, REJECTED].find { it.value == val }
	}
}