package com.api.requests;

import java.io.IOException;

import org.apache.http.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.run.Assertion;
import com.api.run.RestAPI;
import com.api.utils.Base;
import com.api.utils.Entity;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;

public class Book extends Base{
	
	 String filePath ="C:\\Users\\BalarajuGone\\eclipse-workspace\\API_Framework\\src\\test\\resources\\API Resources\\data\\Bookdata.xlsx";

	 @BeforeClass
	    public void setFilePath() {
	        setFilepath(filePath);
	    }
	 
	 
	@Test(dataProvider = "book_data", groups = { "p1" }, description = "book details")
	public void test(Entity entity) throws StreamReadException, DatabindException, IOException {
		JsonNode response = null;
		response = RestAPI.executeAPI(entity);

		if (entity.getid() != 0) {
			assertion.assertEquals(new Assertion(response).element("id"), entity.getid(), " Id is not correct ");
		}

		if (entity.getbookName() != null) {
			assertion.assertEquals(new Assertion(response).element("bookName"), entity.getbookName(),
					" Book Name is not correct ");
		}

		if (entity.getbookAuthor() != null) {
			assertion.assertEquals(new Assertion(response).element("bookAuthor"), entity.getbookAuthor(),
					" Book Author name is not correct ");
		}

		if (entity.getbookPrice() != 0) {
			assertion.assertEquals(new Assertion(response).element("bookPrice"), entity.getbookPrice(),
					" Book Price is not correct ");
		}
		if (entity.geterrorMessage() != null) {
			assertion.assertNotNull(new Assertion(response).element("data.violations[0].message"), " Message is null ");
		}

		assertion.assertAll();
	}

}
