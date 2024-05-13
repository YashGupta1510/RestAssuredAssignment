package api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	
	public String createBoard(String name) {
		log.info("Testing create Board name = " + name);
		extentTest.info("Testing create Board name = "+ name);
		
		Response response = helper.postRequest(Resources.BoardsEndpoint, PostPayload.addNewBoard(name), log);
		
		JsonPath js = response.jsonPath();
		id = js.get("id");
		String testName = js.get("name");
		
		PostPayload.addNewList(id);
		
		return testName;
	}
	
	
	public Response deleteBoard() {
		
		log.info("Testing delete Card ID = " + id);
		extentTest.info("Testing delete Card ID = "+ id);
		
		return helper.deleteRequest(Resources.BoardsEndpoint, id, log);
	}
	
	public Response wrongResourceIssue() {
		
		log.info("Testing with wrong Resource url");
		extentTest.info("Testing with wrong Resource url");
		return helper.wrongResourceIssue();
	}
	
	public Response wrongCredentialsIssue() {
	
		log.info("Testing with wrong Token");
		extentTest.info("Testing with wrong Token");
		
		return helper.wrongCredentialsIssue();
	}
	
	public Response badRequestIssue() {
		
		log.info("Testing with no params");
		extentTest.info("Testing with no params");
		
		return helper.badRequestIssue();
	}
	
	
	
}
