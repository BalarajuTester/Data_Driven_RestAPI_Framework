package com.api.reporting;

import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;


public class Report {
	
		public static void printRequestLogInReport(FilterableRequestSpecification request) {
		ExtentReportManager.logInfo("Endpoint : " + request.getURI());
		ExtentReportManager.logInfo("Method : " + request.getMethod());
		ExtentReportManager.logInfo("Request Headers : ");
		ExtentReportManager.logHeaders(request.getHeaders());
		String requestBody = request.getBody();
		if (requestBody != null && !requestBody.isEmpty()) {
		ExtentReportManager.logInfo("Request body : ");
		ExtentReportManager.logjson(requestBody);
		}
	}

		public static void printResponseLogInReport(Response response) {

		ExtentReportManager.logInfo("Response Status : " + (response.getStatusCode()));
		ExtentReportManager.logInfo("Response Headers : ");
		ExtentReportManager.logHeaders(response.getHeaders());
		ExtentReportManager.logInfo("Response body : ");
		ExtentReportManager.logjson(response.asPrettyString());
	}

}
