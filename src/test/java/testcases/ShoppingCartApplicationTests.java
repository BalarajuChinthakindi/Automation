package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import helpers.Wait;
import pages.HomePageObjects;

public class ShoppingCartApplicationTests extends BaseTest {
	
	HomePageObjects HomePage = new HomePageObjects(driver);
	
	@Test
	public void LoginTest()
	{
		HomePage.clickOnSignIn();
		Assert.assertTrue(HomePage.isAlreadyRegisteredMessageDisplayed(),"Already Registered? label is not displayed");		
	}

}
