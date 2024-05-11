package api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pages.BasePage;
import utils.Helper;
import utils.PostPayload;
import utils.Resources;

public class ListAPI extends BasePage {

	Helper helper = new Helper();
	protected static Logger log = LogManager.getLogger();
	static String id;
	
	public void createList() {
		
		log.info("Creating list with name = " + PostPayload.AddNewList);
		extentTest.info("Creating list with name = " + PostPayload.AddNewList);
		
		
		Response response = helper.postRequest(Resources.ListsEndpoint, PostPayload.AddNewList, log);
		
		JsonPath js = response.jsonPath();
		id = js.get("id");
		
		log.info("List Created with id = " + id);
		extentTest.info("List Created with id = " + id);
		
		PostPayload.addNewCard(id);
		
		Assert.assertEquals(response.statusCode(), 200);
		
	}

	public Response deleteList() {
		log.info("archiving list with id = " + id);
		extentTest.info("archiving list with id = " + id);
		
		Response response = helper.putRequest(Resources.ListsEndpoint, id, log);
		
		Assert.assertEquals(response.statusCode(), 200);
		
		return response;
		
	}
	
	
	
}
