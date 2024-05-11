package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import api.ListAPI;
import io.restassured.response.Response;
import pages.BasePage;
import utils.TestNGListener;

@Listeners(TestNGListener.class)
public class ListAPITest extends BasePage{
	
	ListAPI api = new ListAPI();
	
	@Test(description = "Creates list with a name on the board", groups = {"CardFlowCreate"} )
	public void createListWithNameOnBoardWithID() {
		api.createList();
	}
	
	@Test(description = "deletes list", groups = {"CardFlowDelete"} )
	public void deleteListID() {
		Response response = api.deleteList();
		Assert.assertEquals(response.statusCode(), 200);
		
	}
}
