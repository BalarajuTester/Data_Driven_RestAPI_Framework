package com.api.run;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.api.reporting.Report;
import com.api.reporting.Setup;
import com.api.utils.Entity;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import static io.restassured.RestAssured.given;


public class RestAPI {

	public static Response executeAPI(Entity entity) throws ClientProtocolException, IOException {
				
		Response response = null;
		
		if (Setup.extentTest.get() == null) {
	        throw new IllegalStateException(
	            "Extent test is not initialized. Check whether Setup listener is registered."
	        );
	    }
		
		if (!(entity.getHttpCode() == null) && entity.getHttpCode().equalsIgnoreCase("get")) {
			response = given().filter(reportFilter)
				   .header("Accept","application/json")
				   .when()
				   .get(entity.getEndPoint())
				   .then()
				   .extract()
				   .response();
			
		} else if (!(entity.getHttpCode() == null) && entity.getHttpCode().equalsIgnoreCase("post")) {
			
			response = given().filter(reportFilter)
					   .header("Accept","application/json")
					   .header("Content-Type","application/json")
					   .body(entity.getTestRequest())
					   .when()
					   .post(entity.getEndPoint())
					   .then()
					   .extract()
					   .response();
		}else if(!(entity.getHttpCode() == null) && entity.getHttpCode().equalsIgnoreCase("delete")) {
			System.out.println(entity.getid());
			response = given().filter(reportFilter)
					   .header("Accept","application/json")
					   .when()
					   .delete(entity.getEndPoint()+"/"+entity.getid())
					   .then()
					   .extract()
					   .response();
			
		}else if(!(entity.getHttpCode() == null) && entity.getHttpCode().equalsIgnoreCase("put")) {
			response = given().filter(reportFilter)
					.header("Accept","application/json")
					.header("Content-Type", "application/json")
		            .body(entity.getTestRequest())
		            .when()
		            .put(entity.getEndPoint() + "/" + entity.getid())
					.then()
					.extract()
					.response();
			
		}else if(entity.getHttpCode() == null){
			
			throw new IllegalArgumentException("HTTP code is Null");
			
		}else {
			
			throw new IllegalArgumentException("Invalid HTTP code - " + entity.getHttpCode());
		}
		
		return response;
	}
	
	private static final Filter reportFilter =(FilterableRequestSpecification request,
			FilterableResponseSpecification response,
	         FilterContext context) -> {

	            Report.printRequestLogInReport(request);

	            Response apiResponse = context.next(request, response);

	            Report.printResponseLogInReport(apiResponse);

	            return apiResponse;
	        };
}
