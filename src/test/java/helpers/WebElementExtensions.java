package helpers;

import org.openqa.selenium.By;

import testcases.BaseTest;

public class WebElementExtensions extends Wait {
	
	public void clickButton(By element)
	{
		BaseTest.driver.findElement(element).click();
	}
	
	public void clickButtonWithWait(By element)
	{
		waitForElementTobeClickable(element).click();
	}

}
