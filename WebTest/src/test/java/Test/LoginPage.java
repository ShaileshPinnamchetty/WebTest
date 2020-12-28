package Test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import Test.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPage {
	WebDriver driver;
	Base base=new Base();
	LogInPageObjects lp;
	private static final Logger log = LogManager.getLogger(LoginPage.class);
	@BeforeTest(enabled=true, groups="Sanity")
	public void initialize(){
		driver=base.InitBrowser();
		driver.get(base.prop.getProperty("url"));
		lp=new LogInPageObjects(driver);	
		log.info("Browser initialization is completed");
	}
	
	@BeforeMethod(enabled=true, groups="Sanity")
	public void bfrmtd(){
		//System.out.println("before method in loginpage");
		log.info("before method in loginpage");
	}
	
	@BeforeClass(enabled=true, groups="Sanity")
	public void bfrcls(){
		//System.out.println("before class in login pages");
		log.info("before class in login pages");
	}
	
	@BeforeTest(enabled=true, groups="Sanity")
	public void bfrtst(){
		//System.out.println("before test in login page");
		log.info("before test in login page");
	}
	
	@Test(dataProvider="data", enabled=true, groups="Sanity")
	public void loginTest(Integer mobNum) throws IOException{
	driver.navigate().refresh();
	try {
		lp.getLoginWithPhoneEmail().click();
	}
	catch (NoSuchElementException nse){
		//System.out.println("Login popup is not displayed. Clicking on login button!");
		log.info("Login popup is not displayed. Clicking on login button!");
		WebDriverWait wait = new WebDriverWait(driver,30); 
		wait.until(ExpectedConditions.visibilityOf(lp.getLoginBtn()));
		lp.getLoginBtn().click();
	}
	//lp.getEmailTxtBox().clear();
	lp.getEmailTxtBox().sendKeys(mobNum.toString());
	Actions a =new Actions(driver);
	a.doubleClick(lp.getSubmitBtn()).build().perform();
	Assert.assertTrue(lp.getErrorLabel().isDisplayed());
	}
	
	@DataProvider
	public Object[] data(){
		Object[] ob=new Object[3];
		ob[0]=1112223331;
		ob[1]=1112224332;
		ob[2]=1112221433;
		return ob;
	}
	
	@AfterTest(enabled=true, groups="Sanity")
	public void tearDown(){
		base.tearDown(driver);
		log.info("Browser closed");
	}	
}
