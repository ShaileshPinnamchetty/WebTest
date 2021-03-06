package Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageSanity {
	WebDriver driver;
	Base base=new Base();
	HomePageObjects hp;
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sh;
	private static final Logger log = LogManager.getLogger(HomePageSanity.class);
	@BeforeTest(enabled=true, groups="Sanity")
	public void initialize() throws IOException{
		driver=base.InitBrowser();
		driver.get(base.prop.getProperty("url"));
		hp=new HomePageObjects(driver);
		fis=new FileInputStream("C:\\Users\\Dinesh\\workspace\\WebTest\\MultiCityTestData.xlsx");
		wb=new XSSFWorkbook(fis);
		log.info("Browser initialization is completed");
	}
	
	@Test(enabled=true, groups="Sanity")
	public void sanityTest() throws InterruptedException, IOException{	
		try{
			hp.getoutsideModal().click();
		}
		catch (NoSuchElementException nse){
			//System.out.println("Login popup is not displayed. Continuing execution!");
			log.info("Login popup is not displayed. Continuing execution!");
		}
		int today = LocalDate.now().getDayOfMonth();
		XSSFSheet sh=wb.getSheetAt(0);
		String fromCity=sh.getRow(1).getCell(0).getStringCellValue();
		String toCity1=sh.getRow(1).getCell(1).getStringCellValue();
		String toCity2=sh.getRow(1).getCell(2).getStringCellValue();	
		hp.getmultiCityRadioBtn().click();
		hp.getfromList().click();
		driver.switchTo().activeElement().sendKeys(fromCity);
		Thread.sleep(2000);
		hp.getfirstListItem().click();
		driver.switchTo().activeElement().sendKeys(toCity1);
		Thread.sleep(2000);
		hp.getfirstListItem().click();
		List<WebElement> cal=hp.getCalndar();
		for (int i=0;i<cal.size();i++){
			if (cal.get(i).getText().equals(Integer.toString(today))){		
				cal.get(i).click();
				break;
			}
		}
		Thread.sleep(2000);
		hp.getanotherCity().click();
		driver.switchTo().activeElement().sendKeys(toCity2);		
		Thread.sleep(2000);
		hp.getfirstListItem().click();
		for (int i=0;i<cal.size();i++){
			if (cal.get(i).getText().equals("10")){
				cal.get(i).click();
				break;
			}
		}
		Thread.sleep(2000);
	}
	
	
	@AfterTest(enabled=true, groups="Sanity")
	public void tearDown(){
		base.tearDown(driver);
		log.info("Browser closed");
	}	

}
