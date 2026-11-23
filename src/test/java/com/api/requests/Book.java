package com.api.requests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import com.api.reporting.Setup;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.api.run.RestAPI;
import com.api.utils.Base;
import com.api.utils.Entity;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import io.restassured.response.Response;
@Listeners(Setup.class)
public class Book extends Base{
	
	 String filePath ="D:\\eclips-workspace-new\\Data_Driven_RestAPI_Framework\\src\\test\\resources\\API Resources\\data\\Bookdata.xlsx";

	 @BeforeClass
	    public void setFilePath() {
	        setFilepath(filePath);
	    }
	 
	 
	@Test(dataProvider = "data", groups = { "p1" }, description = "book details")
	public void test(Entity entity) throws StreamReadException, DatabindException, IOException {
		
		Response response = RestAPI.executeAPI(entity);
		
	    SoftAssert assertion = new SoftAssert();
	    
		if (entity.getHttpStatus() != 0) {
			assertion.assertEquals(response.getStatusCode(), entity.getHttpStatus(), " Status is not correct ");
		}
		
		if (entity.getid() != 0) {
			//assertion.assertEquals(response.jsonPath().getString("id[20]"), String.valueOf(entity.getid()), " Id is not correct ");
		}

		if (entity.getbookName() != null) {
			assertion.assertEquals(response.jsonPath().getString("bookName"), entity.getbookName(),
					" Book Name is not correct ");
		}

		if (entity.getbookAuthor() != null) {
			assertion.assertEquals(response.jsonPath().getString("bookAuthor"), entity.getbookAuthor(),
					" Book Author name is not correct ");
		}
		System.out.println("Price is "+entity.getbookPrice());
		if (entity.getbookPrice() != null) {
			assertion.assertEquals(response.jsonPath().getString("bookPrice[4]"), entity.getbookPrice(),
					" Book Price is not correct ");
		}
		if (entity.geterrorMessage() != null) {
			assertion.assertNotNull(response.jsonPath().getString("data.violations[0].message"), " Message is null ");
		}

		assertion.assertAll();
		
	}

}
