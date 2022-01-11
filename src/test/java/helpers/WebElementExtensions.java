package helpers;

import org.openqa.selenium.By;

import testcases.BaseTest;

public class WebElementExtensions extends Wait {
	
	public static void clickButton(By element)
	{
		BaseTest.driver.findElement(element).click();
	}
	
	public static void clickButtonWithWait(By element)
	{
		Wait.waitForElementTobeClickable(element).click();
	}

}
