package POM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExtractLinks {
	
	static WebDriver dr;
	
	public ExtractLinks(WebDriver dr) {
		this.dr = dr;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(tagName = "a")
	static 
	List<WebElement> links;
	
	public List<WebElement> Return_Links() {
		return links;
	}
	

}
