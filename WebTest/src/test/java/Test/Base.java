package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

public class Base {
	public static WebDriver driver;
	public Properties prop;
	public FileInputStream fis;
	
	public WebDriver InitBrowser() {
		prop=new Properties();
		try {
		//fis=new FileInputStream("C:\\Users\\Dinesh\\workspace\\WebTest\\src\\test\\resources\\prop.properties");
		String propFileName = "prop.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		prop.load(inputStream);
		String browser=prop.getProperty("browser");
		if (browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "D:\\selenium-java-3.141.59\\chromedriver_win32 for version 84\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		if (browser.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", "D:\\selenium-java-3.141.59\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		if (browser.equals("ie")){
			System.setProperty("webdriver.ie.driver", "D:\\selenium-java-3.141.59\\IEDriverServer_x64_3.150.1\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return driver;
	}
	
	public void tearDown(WebDriver driver){
		driver.close();
		driver=null;
	}
	
	public void getScreenshot(String classname, String testname) throws IOException{
		//new File(System.getProperty("user.dir")+"\\"+classname).mkdir();
		new File("C:\\Users\\Dinesh\\workspace\\WebTest\\"+classname).mkdir();
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File("C:\\Users\\Dinesh\\workspace\\WebTest\\"+classname+"\\"+testname+".jpg"));
		System.out.println("Screenshot captured at: "+"C:\\Users\\Dinesh\\workspace\\WebTest\\"+classname+"\\"+testname+".jpg");
		
	}

}
