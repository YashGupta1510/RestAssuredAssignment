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

public class CardsAPI extends BasePage {
	
	Helper helper = new Helper();
	protected static Logger log = LogManager.getLogger();
	static String id;
	
	public void createCard() {
		
		Response response = helper.postRequest(Resources.CardsEndpoint, PostPayload.AddNewCard, log);
		
		JsonPath js = response.jsonPath();
		id = js.get("id");
		
		log.info("Card Created with id = " + id);
		extentTest.info("Card Created with id = " + id);
		
		Assert.assertEquals(response.statusCode(), 200);

	}
	
	
	public Response getCardByID(String id) {
		
		log.info("Testing Card ID = " + id);
		extentTest.info("Testing Card ID = " + id);
		
		return helper.getRequest(Resources.CardsEndpoint, id, log);
	}
	
	public Response deleteCard() {
		
		log.info("Delete Card ID = " + id);
		extentTest.info("Delete Card ID = " + id);
		
		return helper.deleteRequest(Resources.CardsEndpoint, id, log);
	}
	
	
}
