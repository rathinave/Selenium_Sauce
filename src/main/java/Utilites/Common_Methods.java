package Utilites;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Common_Methods {
	   
	
    protected static WebDriver dr;
    static String URL;
    
    public Common_Methods(String URL) {
    	this.URL = URL;
    }
    
    
    public Common_Methods() {
		super();
		
	}


	public static WebDriver Launch()
    {
    	dr = new EdgeDriver();
    	dr.get(URL);
    	return dr;
    }
    
	public static String read_data(int i,int j) throws IOException	{
		
		
			String filename = "SwagLab";
			String sheetname = "Sheet1";
			FileInputStream fis = new FileInputStream(filename);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheetname);
			
			XSSFRow r = sh.getRow(i);
			XSSFCell c= r.getCell(j);
			String s = c.getStringCellValue();
			return s;	
	}

}
