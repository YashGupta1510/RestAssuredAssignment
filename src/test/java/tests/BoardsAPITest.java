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
public class BoardsAPITest extends BasePage{

	BoardsAPI api = new BoardsAPI();
	
	@Test(description = "Creates Board with a name", groups = {"CardFlowCreate"} )
	public void createBoardByNameTest() {
		String name = FileReader.props.getProperty("Board-name");
		api.createBoard(name);
	}
	

	@Test(description = "deletes Board", groups = {"CardFlowDelete"} )
	public void deleteBoardTest() {
		Response response = api.deleteBoard();
		Assert.assertEquals(response.statusCode(), 200);
	}
	
}
