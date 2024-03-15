package com.api.requests;

import java.io.IOException;

import org.apache.http.ParseException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.api.run.Assertion;
import com.api.run.RestAPI;
import com.api.utils.Base;
import com.api.utils.Entity;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;

public class Book2 extends Base{

	@Test(dataProvider = "book_data", groups = { "p1" }, description = "book details")
	public void test(Entity entity) throws StreamReadException, DatabindException, IOException {

		JsonNode response = null;
		response = RestAPI.executeAPI(entity);
        verifyBook(response, entity);
	}

	public void verifyBook(JsonNode response, Entity testdata) throws ParseException, IOException {

		assertion = new SoftAssert();

		if (testdata.getid() != 0) {
			assertion.assertEquals(new Assertion(response).element("id"), testdata.getid(), " Id is not correct ");
		}

		if (testdata.getbookName() != null) {
			assertion.assertEquals(new Assertion(response).element("bookName"), testdata.getbookName(),
					" Book Name is not correct ");
		}

		if (testdata.getbookAuthor() != null) {
			assertion.assertEquals(new Assertion(response).element("bookAuthor"), testdata.getbookAuthor(),
					" Book Author name is not correct ");
		}

		if (testdata.getbookPrice() != 0) {
			assertion.assertEquals(new Assertion(response).element("bookPrice"), testdata.getbookPrice(),
					" Book Price is not correct ");
		}
		if (testdata.geterrorMessage() != null) {
			assertion.assertNotNull(new Assertion(response).element("data.violations[0].message"), " Message is null ");
		}

		assertion.assertAll();
	}

}
