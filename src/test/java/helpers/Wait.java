package helpers;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testcases.BaseTest;

public class Wait {

	public WebElement waitForElementTobeClickable(By element)
	{
		return (new WebDriverWait(BaseTest.driver, Duration.ofSeconds(Constants.maxWaitTime)))
				   .until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public WebElement waitForElementTobeClickable(By element, int timeInSeconds)
	{
		return (new WebDriverWait(BaseTest.driver, Duration.ofSeconds(timeInSeconds)))
				   .until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public WebElement waitForElementTobeDisplayed(By element)
	{
		return (new WebDriverWait(BaseTest.driver, Duration.ofSeconds(Constants.maxWaitTime)))
				   .until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	public WebElement waitForElementTobeDisplayed(By element, int timeInSeconds)
	{
		return (new WebDriverWait(BaseTest.driver, Duration.ofSeconds(timeInSeconds)))
				   .until(ExpectedConditions.visibilityOfElementLocated(element));
	}
}
