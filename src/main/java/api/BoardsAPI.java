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

public class BoardsAPI extends BasePage {

	Helper helper = new Helper();
	protected static Logger log = LogManager.getLogger();
	static String id;
	
	public void createBoard(String name) {
		log.info("Testing create Board name = " + name);
		extentTest.info("Testing create Board name = "+ name);
		
		Response response = helper.postRequest(Resources.BoardsEndpoint, PostPayload.addNewBoard(name), log);
		
		JsonPath js = response.jsonPath();
		id = js.get("id");
		
		PostPayload.addNewList(id);
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	
	public Response deleteBoard() {
		
		log.info("Testing delete Card ID = " + id);
		extentTest.info("Testing delete Card ID = "+ id);
		
		return helper.deleteRequest(Resources.BoardsEndpoint, id, log);
	}
	
		
}
