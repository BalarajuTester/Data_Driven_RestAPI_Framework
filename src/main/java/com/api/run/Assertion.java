package com.api.run;

import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;

import io.restassured.response.Response;

public class Assertion {

//	private Response response;
//	//private Response Assresponsee;
//
//	public Assertion(Response response2) {
//		this.response = response2;
//	}
//
//	public Object element(String str) {
//		//response=Assresponsee;
//		String[] comp = null;
//
//		if (str.contains(".") || str.contains("[")) {
//			String[] dotsp = str.split("\\.|\\[|\\]");
//
//			// Remove empty strings from the array
//			dotsp = Arrays.stream(dotsp).filter(s -> !s.isEmpty()).toArray(String[]::new);
//
//			comp = new String[dotsp.length];
//			int j = 0;
//
//			for (int i = 0; i < dotsp.length; i++) {
//				String part = dotsp[i];
//				comp[j] = part;
//				if (part.matches("\\d+")) {
//					response = response.path(Integer.parseInt(part));
//				} else {
//					response = response.path(part);
//				}
//				j++;
//			}
//		} else {
//			response = response.path(str);
//		}
//
//		String responseText=response.asText();
//	    
//		if (responseText.matches("\\d+")) {
//		    return Integer.parseInt(responseText); // Convert to int
//		} else if (responseText.matches("\\d+(\\.\\d+)?")) {
//		    return Double.parseDouble(responseText); // Convert to double
//		} else {
//		    return responseText; // return it as a String
//		}
//		
//	}
//
}
