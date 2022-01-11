package helpers;

import java.io.File;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testcases.BaseTest;

public class Excel {

	public XSSFWorkbook Workbook;
	public XSSFSheet Sheet;
	public int iCurrentTestCaseRow;

	public Excel()
	{
		String ProjectPath=System.getProperty("user.dir");
		try{
			Workbook = new XSSFWorkbook(ProjectPath+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"testdata"+File.separator+"SampleTestDataSheet.xlsx");
			Sheet = Workbook.getSheetAt(0);

		}catch(IOException exception)
		{
			exception.printStackTrace();
			BaseTest.logger.warning("Unable to locate testdata sheet .... !");
		}
	}


	public String getTestData(String sColumnName)
	{
		try {
			return Sheet.getRow(iCurrentTestCaseRow).getCell(getColumnNumber(sColumnName)).getStringCellValue();
		}catch (NullPointerException exception)
		{
			exception.printStackTrace();
			BaseTest.logger.warning("Unable to locate testdata with column: " + sColumnName +" .... !");
			return null;
		}
	}

	public int getColumnNumber(String sColumnName)
	{
		int colNum = Sheet.getRow(0).getLastCellNum();		
		if (Sheet.getRow(0).cellIterator().hasNext()) {
			for (int j = 0; j < colNum; j++) {
				if(Sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase(sColumnName) )
					return j;
			}
		}

		BaseTest.logger.warning("Unable to locate testdata for ColumnName: " + sColumnName +" .... !");
		return -1;
	}

	public int setCurrentTestCaseRow(String sTestCaseName)
	{
		int rowNum = Sheet.getLastRowNum() + 1;     	   
		for (int j = 1; j < rowNum; j++) {
			if(Sheet.getRow(j).getCell(0).getStringCellValue().equalsIgnoreCase(sTestCaseName ))
				return j;
		}

		BaseTest.logger.warning("Unable to locate testdata for TestCase: " + sTestCaseName +" .... !");
		return -1;
	}

}

