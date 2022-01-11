package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helpers.WebElementExtensions;

public class HomePageObjects {
	
	WebDriver _driver;
	
	public HomePageObjects(WebDriver driver)
	{
		_driver = driver;
	}
	
	public By link_signin = By.xpath("//a[@title='Log in to your customer account']");
	public By input_CreateAccount_Email = By.id("email_create");
	public By button_CreateAccount = By.name("SubmitCreate");
    public By label_AlreadyRegistered = By.xpath("//h3[text()='Already registered']");
    
    public void clickOnSignIn()
    {
    	WebElementExtensions.clickButton(link_signin);
    }
}
