package Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePage {
	
	WebDriver driver;
	Base base=new Base();
	HomePageObjects hp;
	@BeforeTest
	public void initialize(){
		driver=base.InitBrowser();
		driver.get(base.prop.getProperty("url"));
		hp=new HomePageObjects(driver);
	}
	@BeforeSuite
	public void bfrsut(){
		System.out.println("before suite in homepage");
	}
	
	@BeforeClass
	public void bfrcls(){
		System.out.println("before class in home page");
	}
	
	@BeforeTest
	public void bfrtst(){
		System.out.println("before test in home page");
	}
	
	@Test
	public void radioBtnTest(){
		List<WebElement> li=hp.getTicketOptions();
		for (int i=0;i<li.size();i++){
			if (li.get(i).getAttribute("Class").matches("selected"))
			Assert.assertEquals(li.get(i).getText(), "ONEWAY");
		}
		hp.getReturnLabel().click();
		for (int i=0;i<li.size();i++){
			if (li.get(i).getAttribute("Class").matches("selected"))
			Assert.assertEquals(li.get(i).getText(), "ROUND TRIP11");
		}		
	}

	@AfterTest()
	public void tearDown(){
		base.tearDown(driver);
	}	
}
