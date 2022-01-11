package testcases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import testcases.BaseTest;

public class SampleTests extends BaseTest {
	
	@Test
	public void TestCase1(Method method)
	{
		logger.info("This is :" + method.getName());
		logger.info("Username"+excel.getTestData("Username"));
		logger.info("Password"+excel.getTestData("Password"));
		Assert.assertTrue(excel.getTestData("Password")!=null);
	}
	
	@Test
	public void TestCase2(Method method)
	{
		logger.info("This is :" + method.getName());
		logger.info("Username"+excel.getTestData("Username"));
		logger.info("Password"+excel.getTestData("Password"));
	}
	
	@Test
	public void TestCase3(Method method)
	{
		logger.info("This is :" + method.getName());
		logger.info("Username"+excel.getTestData("Username"));
		logger.info("Password"+excel.getTestData("Password"));
	}

}
