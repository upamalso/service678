package com.dialog.service678.dto.response;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;

public class ApiResponse {

	private String responseCode;
	private String responseDescription;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public static ApiResponse getApiResponse(String resultCode, String resultDescription, MessageSource messageSource, Logger log) {

		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setResponseCode(resultCode);

		String description = messageSource.getMessage(resultDescription, null, null);
		apiResponse.setResponseDescription(description);

		log.info(description);

		return apiResponse;
	}
}
