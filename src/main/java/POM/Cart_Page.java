package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart_Page {
	
	static WebDriver dr;
	
	public Cart_Page(WebDriver dr) {
		this.dr = dr;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	static 
	WebElement cart_btn;
	
    public  void Cart_btn_click() {
   	 cart_btn.click();
   }
    
    public  boolean Validate_cart() {
    	return dr.findElement(By.xpath("//div[@class='cart_item_label']")).isDisplayed();
    }
   

}
