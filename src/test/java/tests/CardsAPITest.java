package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import api.CardsAPI;
import io.restassured.response.Response;
import pages.BasePage;
import utils.FileReader;
import utils.TestNGListener;

@Listeners(TestNGListener.class)
public class CardsAPITest extends BasePage{
	
	CardsAPI api = new CardsAPI();
	
	@Test(description = "Creates Card with a name", groups = {"CardFlowCreate"} )
	public void createCardByNameTest() {
		api.createCard();
	}
	
	@Test(description = "delete Card with a name", groups = {"CardFlowDelete"} )
	public void deleteCard() {
		Response response = api.deleteCard();
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test
	public void getCardByIDTest() {
		String id = FileReader.props.getProperty("card-id");
		Response response = api.getCardByID(id);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test
	public void getCardByIDTestFail() {
		Response response = api.getCardByID("wrong-id");
		Assert.assertEquals(response.statusCode(), 400);
	}
}
