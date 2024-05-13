package api;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	public String createCard() {

		Response response = helper.postRequest(Resources.CardsEndpoint, PostPayload.AddNewCard, log);

		JsonPath js = response.jsonPath();
		id = js.get("id");
		String testName = js.get("name");

		log.info("Card Created with id = " + id);
		extentTest.info("Card Created with id = " + id);

		return testName;

	}
	
	public String updateCard(String name) {

		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", name);
		Response response = helper.putRequest(Resources.CardsEndpoint, id, map, log);
		
		JsonPath js = response.jsonPath();
		String testName = js.get("name");

		return testName;
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
