package com.weather.prediction.controller;

import org.springframework.http.HttpStatus;

import com.weather.prediction.utility.General;

import springfox.documentation.service.ResponseMessage;

public class ReturnConvertor {
	/** The status. */
	private String status;
	
	/** The message. */
	private String message;
	
	/** The return code. */
	private Integer statusCode;
	
	private Data data;

	public void success() {
		statusCode = HttpStatus.OK.value();
		status = General.SUCCESS;
	}
	
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Integer getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}


	public Data getData() {
		return data;
	}


	public void setData(Data data) {
		this.data = data;
	}

	public static class ErrorDoc {
		/** The status. */
		private String status;
		
		/** The message. */
		private String message;
		
		/** The return code. */
		private Integer statusCode;
		
		private BlankData data = new BlankData();
		
		class BlankData {
			
		}
	}

}
