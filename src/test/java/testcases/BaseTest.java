package testcases;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import helpers.Excel;
import helpers.Screenshot;
import helpers.Constants;

public class BaseTest {
	public ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static WebDriver driver;
	public helpers.Excel excel; 
	public Logger applicationLog;
	public static String currentTimestamp;
	public SimpleDateFormat dateFormat;

	@BeforeTest
	public void beforeTestMethod()
	{
		excel = new Excel();
		applicationLog= LogManager.getLogger();	
		dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		currentTimestamp=dateFormat.format(new Timestamp(System.currentTimeMillis()));
		System.out.print(currentTimestamp);
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+File.separator+"reports"+File.separator+"TestRun_Report-"+currentTimestamp+".html");
		htmlReporter.config().setReportName("Automation Test Run Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Balaraju Chinthakindi");	
		applicationLog.info("Setting up screenshots folder:");
		File theDir = new File(System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+currentTimestamp);
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
	}

	@BeforeMethod
	@Parameters("browserName")
	public void beforeMethod(Method testMethod, String browserName)
	{
		logger= extent.createTest(testMethod.getName());
		excel.iCurrentTestCaseRow=excel.setCurrentTestCaseRow(testMethod.getName());	
		driver=setupWebDriver(browserName);
		driver.manage().window().maximize();
		driver.get(Constants.url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@AfterMethod
	public void afterMethod(ITestResult result)
	{
		if(result.getStatus()== ITestResult.SUCCESS)
		{
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: " + methodName + " Passed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			logger.log(Status.PASS,m);
		}else if(result.getStatus()== ITestResult.FAILURE)
		{
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: " + methodName + " Failed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			logger.log(Status.FAIL,m);
			try {
				logger.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.captureScreenshot(result.getMethod().getMethodName())).build());
			} catch (Exception e) {				
				e.printStackTrace();
			}		
		}else if(result.getStatus()== ITestResult.SKIP)
		{
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: " + methodName + " Skipped";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
			logger.log(Status.SKIP,m);
		}
		
		if(driver!=null) {
			applicationLog.info("quit");
			driver.quit();
		}
	}
	@AfterTest
	public void afterTestMethod()
	{		
		extent.flush();		
	}
	
	public WebDriver setupWebDriver(String browserName) {
	
		WebDriver _driver;
		
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+File.separator+"drivers"+File.separator+"chromedriver.exe");
			_driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.geeko.driver", System.getProperty("user.dir")+File.separator+"drivers"+File.separator+"geekodriver.exe");
			_driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("InternetExplorer"))
		{
			System.setProperty("webdriver.IE.driver", System.getProperty("user.dir")+File.separator+"drivers"+File.separator+"IEDriverServer.exe");
			_driver = new InternetExplorerDriver();
		}else
		{
			logger.info("Please select a correct browser");
			return null;
		}
		
		return _driver;
	}
}

