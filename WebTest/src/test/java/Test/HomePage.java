package Test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
	private static final Logger log = LogManager.getLogger(HomePage.class);
	@BeforeTest(groups="Sanity")
	public void initialize(){
		driver=base.InitBrowser();
		driver.get(base.prop.getProperty("url"));
		hp=new HomePageObjects(driver);
		log.info("Browser initialization is completed");
	}
	@BeforeSuite(enabled=true, groups="Sanity" )
	public void bfrsut(){
		//System.out.println("before suite in homepage");
		log.info("before suite in homepage");
	}
	
	@BeforeClass(enabled=true, groups="Sanity" )
	public void bfrcls(){
		//System.out.println("before class in home page");
		log.info("before class in home page");
	}
	
	@BeforeTest(enabled=true, groups="Sanity" )
	public void bfrtst(){
		//System.out.println("before test in home page");
		log.info("before test in home page");
	}
	
	@Test(enabled=true, groups="Sanity" )
	public void radioBtnTest(){
		try{
			hp.getoutsideModal().click();
		}
		catch (NoSuchElementException nse){
			//System.out.println("Login popup is not displayed. Continuing execution!");
			log.info("Login popup is not displayed. Continuing execution!");
		}
		List<WebElement> li=hp.getTicketOptions();
		for (int i=0;i<li.size();i++){
			if (li.get(i).getAttribute("Class").matches("selected"))
			Assert.assertEquals(li.get(i).getText(), "ONEWAY");
		}
		hp.getReturnLabel().click();
		for (int i=0;i<li.size();i++){
			if (li.get(i).getAttribute("Class").matches("selected"))
			Assert.assertEquals(li.get(i).getText(), "ROUND TRIP");
		}		
	}
	
	@Test(enabled=true, dependsOnMethods="radioBtnTest")
	public void dependsOnMethodsTest(){
		//System.out.println("This method will always run after radioBtnTest");
		log.info("This method will always run after radioBtnTest");
	}
	
	@Test(enabled=true, groups={"Smoke", "Random"})
	public void excludeGroupTest(){
		//System.out.println("This method will go in exclude tag in testng.xml");
		log.info("This method will go in exclude tag in testng.xml");
	}
	
	@AfterTest(enabled=true, groups="Sanity" )
	public void tearDown(){
		base.tearDown(driver);
		log.info("Browser closed");
	}	
}
