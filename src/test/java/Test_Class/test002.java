package Test_Class;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import POM.ExtractLinks;
import POM.Login_Page;
import Utilites.Common_Methods;

public class test002 {
	 private WebDriver dr;
	 Login_Page lp;
	 ExtractLinks el;
	 @BeforeClass
	  public void beforeClass() throws IOException {
		 Common_Methods cm = new Common_Methods("https://www.saucedemo.com/");
		 dr = cm.driver();
		 lp = new Login_Page(dr);
		 el = new ExtractLinks(dr);
		 String name = cm.read_data(1, 0);
		 String pass = cm.read_data(1, 1);
		 lp.Username(name);
		 lp.Password(pass);
		 lp.Click_Login();
	  }
	 
	  @Test
	  public void test() {

		List<WebElement> links = el.Return_Links();
		
		for (WebElement link : links) {
		    if (!link.isDisplayed()) continue;
		
		    String text = link.getText();                  
		    String href = link.getAttribute("href");       
		    if (text != null && !text.isEmpty() && href != null && !href.isEmpty()) {
		        System.out.println(text + " <<===>> " + href);
		    }  
			  }
	  }
 

	  @AfterClass
	  public void afterClass() {
		  dr.quit();
	  }

}
