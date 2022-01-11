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
		Assert.assertNotNull(Wait.waitForElementTobeDisplayed(HomePage.label_AlreadyRegistered),"Already Registered? label is not displayed");		
	}

}
