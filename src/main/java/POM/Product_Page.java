package POM;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class Product_Page {
	
	static WebDriver dr;
		
	@FindBy(xpath = "//select[@class='product_sort_container']")
	static 
	WebElement product_order;
	
	@FindBy(xpath = "//div[@class='inventory_list']/div[1]/div[2]/div[2]/button")
	static 
	WebElement product_name;
	
	
	public Product_Page(WebDriver dr)
	{
		this.dr = dr;
		PageFactory.initElements(dr, this);
	}
	
	public  void Select_Product_Order() {
		Select s = new Select(product_order);
		s.selectByValue("za");
	}
	
	public  void AddProduct_to_Cart() {
		product_name.click();
	}
	public int  Validate_product() {
		String s =dr.findElement(By.xpath( "//a[@class='shopping_cart_link']/span")).getText();
		return Integer.parseInt(s);
	}
    


}
