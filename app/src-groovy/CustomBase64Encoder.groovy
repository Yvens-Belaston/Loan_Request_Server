class CustomBase64Encoder {
    public CustomBase64Encoder() {}
	
	
	public toBase64Url(byte[] documentBytes, String mimeType) {
		def base64 = Base64.encoder.encodeToString(documentBytes);
		return "data:${mimeType};base64,${base64}"
		
	}
}