package helpers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import testcases.BaseTest;

public class Screenshot {

	public static String captureScreenshot(String screenshotName) throws Exception
	{

		String fileName=System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+BaseTest.currentTimestamp+File.separator+screenshotName;
		File f = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
		String destinationFileName=fileName+".png";
		try {
			FileUtils.copyFile(f, new File(destinationFileName));
		}catch(IOException e)
		{
			e.printStackTrace();	
		}
		return destinationFileName;
	}
}
