package com.api.reporting;



import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

public class Report {
	
	//public static void printPostRequestLogInReport(HttpPost httpPost, String jsonPayload)			throws ParseException, IOException {
		public static void printRequestLogInReport(FilterableRequestSpecification request) {
		ExtentReportManager.logInfo("Endpoint : " + request.getURI());
		ExtentReportManager.logInfo("Method : " + request.getMethod());
		ExtentReportManager.logInfo("Request Headers : ");
		request.getHeaders().forEach(header ->ExtentReportManager.logInfo(header.getName() + " : " + header.getValue()));
		String requestBody = request.getBody();
		if (requestBody != null && !requestBody.isEmpty()) {
		ExtentReportManager.logInfo("Request body : ");
		ExtentReportManager.logjson(requestBody);
		}
	}

	//public static void printPostResponseLogInReport(HttpResponse response, String responseBody)			throws ParseException, IOException {
		public static void printResponseLogInReport(Response response) {

		ExtentReportManager.logInfo("Response Status : " + (response.getStatusCode()));
		ExtentReportManager.logInfo("Response Headers : ");
		response.getHeaders().forEach(header ->ExtentReportManager.logInfo(header.getName() + " : " + header.getValue()));
		ExtentReportManager.logInfo("Response body : ");
		ExtentReportManager.logjson(response.asPrettyString());
	}

//	public static void printGetRequestLogInReport(HttpGet httpGet) {
//		ExtentReportManager.logInfo("Endpoint : " + httpGet.getURI());
//		ExtentReportManager.logInfo("Method : " + httpGet.getMethod());
//		ExtentReportManager.logInfo("Request Headers : ");
//		ExtentReportManager.logHeaders(httpGet.getAllHeaders());
//	}
//
//	public static void printGetResponseLogInReport(HttpResponse response, String responseBody)
//			throws ParseException, IOException {
//
//		ExtentReportManager.logInfo("Response Status : " + response.getStatusLine().getStatusCode());
//		ExtentReportManager.logInfo("Response Headers : ");
//		ExtentReportManager.logHeaders(response.getAllHeaders());
//		ExtentReportManager.logInfo("Response body : ");
//		ExtentReportManager.logjson(responseBody);
//	}

}
