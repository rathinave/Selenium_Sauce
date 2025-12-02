package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout_Page {
	
	static WebDriver dr;
	 public Checkout_Page(WebDriver dr) {
		 this.dr = dr;
		 PageFactory.initElements(dr, this);
	 }
	 
	 @FindBy(xpath = "//button[@id='checkout']")
	 static 
	 WebElement chechout_btn;
	 
	 @FindBy(xpath = "//input[@id='first-name']")
	 static 
	 WebElement first_name;
	 
	 @FindBy(xpath = "//input[@id='last-name']")
	 static 
	 WebElement last_name;
	 
	 @FindBy(xpath = "//input[@id='postal-code']")
	 static 
	 WebElement zip_code;
	 
	 @FindBy(xpath = "//input[@id='continue']")
	 static 
	 WebElement continue_btn;
	 
	 @FindBy(xpath = "//button[@id='finish']")
	 static 
	 WebElement finish_btn;
	 
	 public void Click_Checkout() {
		 chechout_btn.click();
	 }
	 
	 public void First_Name(String fname) {
		 first_name.sendKeys(fname);
	 }
	 
	 public void Last_Name(String lname) {
		 last_name.sendKeys(lname);
	 }
	 
	 public void Zip_Code(String zcode) {
		 zip_code.sendKeys(zcode);
	 }
	 
	 public void Click_Continue() {
		 continue_btn.click();
	 }
	 
	 public void Click_Finish() {
		 finish_btn.click();
	 }
	 
	 public boolean Validate_checkout() {
		 return dr.findElement(By.xpath("//h2[@class='complete-header']")).isDisplayed();
	 }
	 

}
