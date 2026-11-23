package com.api.requests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.run.RestAPI;
import com.api.utils.Base;
import com.api.utils.Entity;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import io.restassured.response.Response;

public class Book extends Base{
	
	 String filePath ="D:\\eclipse-workspace\\API_Framework\\src\\test\\resources\\API Resources\\data\\Bookdata.xlsx";

	 @BeforeClass
	    public void setFilePath() {
	        setFilepath(filePath);
	    }
	 
	 
	@Test(dataProvider = "data", groups = { "p1" }, description = "book details")
	public void test(Entity entity) throws StreamReadException, DatabindException, IOException {
		
		
		//Assertion response = new Assertion(RestAPI.executeAPI(entity));
		Response response = RestAPI.executeAPI(entity);

		System.out.println(response.body().asString());
		
		if (entity.getid() != null) {
			assertion.assertEquals(response.jsonPath().getString("id[23]"), entity.getid(), " Id is not correct ");
		}

		if (entity.getbookName() != null) {
			assertion.assertEquals(response.jsonPath().getString("bookName"), entity.getbookName(),
					" Book Name is not correct ");
		}

		if (entity.getbookAuthor() != null) {
			assertion.assertEquals(response.jsonPath().getString("bookAuthor"), entity.getbookAuthor(),
					" Book Author name is not correct ");
		}

		if (entity.getbookPrice() != 0) {
			assertion.assertEquals(response.jsonPath().getString("bookPrice"), entity.getbookPrice(),
					" Book Price is not correct ");
		}
		if (entity.geterrorMessage() != null) {
			assertion.assertNotNull(response.jsonPath().getString("data.violations[0].message"), " Message is null ");
		}

		assertion.assertAll();
		
	}

}
