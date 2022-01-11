package helpers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	int count=0;
	@Override	
	public boolean retry(ITestResult result) {		
		
		
		if(count < Constants.retryCount)
		{
			count++;
			return true;
		}
		return false;
	}

}

