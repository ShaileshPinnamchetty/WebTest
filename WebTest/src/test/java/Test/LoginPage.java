package Test;

import java.io.IOException;
//import Test.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
	@BeforeTest
	public void initialize(){
		driver=base.InitBrowser();
		driver.get(base.prop.getProperty("url"));
		lp=new LogInPageObjects(driver);
	}
	
	@BeforeMethod
	public void bfrmtd(){
		System.out.println("before method in loginpage");
	}
	
	@BeforeClass
	public void bfrcls(){
		System.out.println("before class in login pages");
	}
	
	@BeforeTest
	public void bfrtst(){
		System.out.println("before test in login page");
	}
	
	@Test(dataProvider="data")
	public void loginTest(Integer mobNum) throws IOException{
	driver.navigate().refresh();
	lp.getLoginBtn().click();
	lp.getEmailTxtBox().sendKeys(mobNum.toString());
	Actions a =new Actions(driver);
	a.doubleClick(lp.getSubmitBtn()).build().perform();
	Assert.assertTrue(lp.getErrorLabel().isDisplayed());
	}
	
	@DataProvider
	public Object[] data(){
		Object[] ob=new Object[3];
		ob[0]=1112223336;
		ob[1]=1112224333;
		ob[2]=1112221433;
		return ob;
	}
	
	@AfterTest()
	public void tearDown(){
		base.tearDown(driver);
	}	
}
