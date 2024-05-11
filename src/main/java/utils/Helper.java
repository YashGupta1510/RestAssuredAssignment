package utils;

import java.util.Map;

import org.apache.logging.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pages.BasePage;

public class Helper extends BasePage{
	
	public Response getRequest(String resource, String id, Logger log) {
		
		request = RestAssured.given().contentType("application/json; charset=utf-8");
		
		final String endpoint = BASE_URL + resource + id + AUTH_PARAM;
		
		log.info("Hitting Endpoint "+ endpoint);
		extentTest.info("Hitting Endpoint "+ endpoint);
		
		
		Response response = request.when()
			.get(endpoint)
			.then()
			.extract()
			.response();
		

		log.info("This was response Body: " + response.asPrettyString());
		extentTest.info("This was response Body: " + response.asPrettyString());
		
		return response;
	}
	
	
	
	public Response postRequest(String resource, Map<String, Object> params, Logger log) {
		
		request = RestAssured.given().contentType("application/json; charset=utf-8");
		
		final String endpoint = BASE_URL + resource + AUTH_PARAM;
		
		log.info("Hitting Endpoint "+ endpoint);
		extentTest.info("Hitting Endpoint "+ endpoint);
		
		log.info("With Payload "+ params);
		extentTest.info("With Payload "+ params);
		
		Response response = request.when()
				.queryParams(params)
			.post(endpoint)
			.then()
			.extract()
			.response();
		

		log.info("This was response Body: " + response.asPrettyString());
		extentTest.info("This was response Body: " + response.asPrettyString());
		
		return response;
	}
	
	
	
	
	public Response deleteRequest(String resource, String id, Logger log) {

		request = RestAssured.given().contentType("application/json; charset=utf-8");
		
		final String endpoint = BASE_URL + resource + id + AUTH_PARAM;
		
		log.info("Hitting Endpoint "+ endpoint);
		extentTest.info("Hitting Endpoint "+ endpoint);
		
		
		Response response = request.when()
			.delete(endpoint)
			.then()
			.extract()
			.response();
		

		log.info("This was response Body: " + response.asPrettyString());
		extentTest.info("This was response Body: " + response.asPrettyString());
		
		return response;
	}



	public Response putRequest(String resource, String id, Logger log) {

		request = RestAssured.given().contentType("application/json; charset=utf-8");
		
		final String endpoint = BASE_URL + resource + id + AUTH_PARAM;
		
		log.info("Hitting Endpoint "+ endpoint);
		extentTest.info("Hitting Endpoint "+ endpoint);
		
		
		Response response = request.when()
			.put(endpoint)
			.then()
			.extract()
			.response();
		

		log.info("This was response Body: " + response.asPrettyString());
		extentTest.info("This was response Body: " + response.asPrettyString());
		
		return response;
	}
}
