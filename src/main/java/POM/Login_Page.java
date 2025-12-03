package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilites.Common_Methods;

public class Login_Page  {
	
	static WebDriver dr;
	public Login_Page(WebDriver dr) {
		
		this.dr = dr;
		PageFactory.initElements(dr,this);
	}
	
	@FindBy(xpath = "//input[@id='user-name']")
	static
	WebElement username;
	
	@FindBy(xpath = "//input[@id='password']")
	static
	WebElement password;
	
	@FindBy(xpath = "//input[@id='login-button']")
	static 
	WebElement button;
	
	public void Username(String name) {
	     username.sendKeys(name);
	}
	 
	public  void Password(String pass) {
		password.sendKeys(pass);
	}
	
	public  void Click_Login() {
		button.click();
	}
	
	public  boolean Validate_Login() {
		return dr.findElement(By.xpath("//span[@class='title']")).isDisplayed();
	}

}
