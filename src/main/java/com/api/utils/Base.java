package com.api.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

public class Base {
	
	protected SoftAssert assertion;
	
	static String filePath;
	
	@BeforeSuite
    public void setPath(ITestContext context) {
        String endpointHostName = context.getCurrentXmlTest().getParameter("filePath");
        filePath=endpointHostName;
    }
	
	@DataProvider(name = "book_data", parallel = false)
	//public Iterator<Object[]> getData(Method m, ITestContext testContex) {  //to access information about the test method itself and interface provides access to information about the current test run
		public Iterator<Object[]> getData() {

		// Define a mapping between entity types and their corresponding classes
		LinkedHashMap<String, Class<?>> entityClazzMap = new LinkedHashMap<String, Class<?>>();
		entityClazzMap.put("Entity", Entity.class);

		// specify the file path
		//String DATA_PROVIDE_CSV = "C:\\Users\\BalarajuGone\\eclipse-workspace\\API_Framework\\src\\test\\java\\com\\api\\request\\book\\bookdata.xlsx";
		String DATA_PROVIDE_CSV = filePath;

		Iterator<Object[]> testData = SpreadSheetUtils.getTestData(this.getClass(), entityClazzMap, DATA_PROVIDE_CSV, 0);
		return testData;
		
	}

}
