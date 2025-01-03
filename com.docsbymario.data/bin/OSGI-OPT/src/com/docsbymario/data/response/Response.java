package com.docsbymario.data.response;

public class Response {
	private int statusCode;
	private String body;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append("Status Code: ")
				.append(statusCode)
				.append("\n")
				.append("Body: ")
				.append(body).toString();
	}
}
