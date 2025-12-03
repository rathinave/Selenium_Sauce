package Test_Class;

import org.testng.annotations.Test;

import POM.Cart_Page;
import POM.Checkout_Page;
import POM.Login_Page;
import POM.Product_Page;
import Utilites.Common_Methods;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class test001 {
	
	   private WebDriver dr;
	   Login_Page lp;
	   Product_Page pp;
	   Cart_Page cp;
	   Checkout_Page chp;
	
	  @BeforeClass
	  public void beforeClass() throws IOException {
		  
		   Common_Methods cm = new Common_Methods("https://www.saucedemo.com/");
		   dr = cm.driver();
		   lp = new Login_Page(dr);
		   pp = new Product_Page(dr);
		   cp = new Cart_Page(dr);
		   chp = new Checkout_Page(dr);
		   
		   String name = cm.read_data(1, 0);
		   String pass = cm.read_data(1, 1);
		   lp.Username(name);
		   lp.Password(pass);
		   lp.Click_Login();
	  }
      
	  
	  @Test(priority = 1)
	  public void Test_Title() {
		  
		  String actual = dr.getTitle();
		  assertEquals(actual, "Swag Labs");
	  }
	  
	  @Test(priority = 2)
	  public void Test_Login() throws IOException {
		  
		  boolean actual = lp.Validate_Login();
		  assertEquals(actual, true);
	  }

     @Test(priority = 3)
     public void Test_ProductPage() throws InterruptedException {
    	 
    	 Thread.sleep(2000);
    	 pp.Select_Product_Order();
    	 pp.AddProduct_to_Cart();
    	 int actual = pp.Validate_product();
    	 assertEquals(actual, 1);
    	 
     }
     
     @Test(priority = 4)
     public void Test_CartPage() {
    	 
    	 cp.Cart_btn_click();
    	 boolean actual = cp.Validate_cart();
    	 assertEquals(actual,true);
    	 
     }
     
     @Test(priority = 5)
     public void Test_Checkout() {
    	 
    	 chp.Click_Checkout();
    	 chp.First_Name("Gun");
    	 chp.Last_Name("Park");
    	 chp.Zip_Code("621717");
    	 chp.Click_Continue();
    	 chp.Click_Finish();

    	 boolean actual = chp.Validate_checkout();
    	 assertEquals(actual, true);
    	 
     }

	  @AfterClass
	  public void afterClass() throws InterruptedException {
		  Thread.sleep(2000);
		  dr.quit();
		  
	  }

}
