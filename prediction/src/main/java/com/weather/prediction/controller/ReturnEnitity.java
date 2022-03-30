package com.weather.prediction.controller;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ReturnEnitity  extends RepresentationModel<ReturnEnitity>{
	/** The status. */
	private String status;

	/** The message. */
	private String message;

	/** The return code. */
	private Integer statusCode;

	private Data data;


	public String getStatus() {
		return status;
	}

	public ReturnEnitity setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ReturnEnitity setMessage(String message) {
		this.message = message;
		return this;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public ReturnEnitity setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	public Data getData() {
		return data;
	}

	public ReturnEnitity setData(Data data) {
		this.data = data;
		return this;
	}

	public static class ErrorDoc {
		/** The status. */
		private String status;

		/** The message. */
		private String message;

		/** The return code. */
		private Integer statusCode;


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

	}

}
