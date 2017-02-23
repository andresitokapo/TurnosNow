package com.iramconsulting.turnosnow.DConexionHTTP;

public class MetaData {

	private String httpStatus;
	private String info;
	private String message;

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MetaData [httpStatus=" + httpStatus + ", code=" + info + ", message=" + message + "]";
	}


}
