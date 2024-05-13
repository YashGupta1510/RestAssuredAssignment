package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import api.BoardsAPI;
import io.restassured.response.Response;
import pages.BasePage;
import utils.FileReader;
import utils.TestNGListener;

@Listeners(TestNGListener.class)
public class BoardsAPITest extends BasePage {

	BoardsAPI api = new BoardsAPI();

	@Test(description = "Creates Board with a name", groups = { "CardFlowCreate" })
	public void createBoardByNameTest() {
		String name = FileReader.props.getProperty("board-name");
		String testName = api.createBoard(name);

		// Asserting whether the name was correct or not
		Assert.assertEquals(testName, name);
	}

	@Test(description = "deletes Board", groups = { "CardFlowDelete" })
	public void deleteBoardTest() {
		Response response = api.deleteBoard();
		Assert.assertEquals(response.statusCode(), 200);
	}

	// Validating Negative Scenarios
	@Test(description = "Assert for wrong resource issue", groups = { "FailTest" })
	public void testWrongResourceIssue() {
		Response response = api.wrongResourceIssue();
		Assert.assertEquals(response.statusCode(), 404);
	}

	@Test(description = "Assert for wrong token issue", groups = { "FailTest" })
	public void testWrongCredentialsIssue() {
		Response response = api.wrongCredentialsIssue();
		Assert.assertEquals(response.statusCode(), 401);
	}

	@Test(description = "Assert for Bad Request issue", groups = { "FailTest" })
	public void testBadRequestIssue() {
		Response response = api.badRequestIssue();
		Assert.assertEquals(response.statusCode(), 400);
	}

}
