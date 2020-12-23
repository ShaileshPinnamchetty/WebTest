package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPageObjects {
	WebDriver driver;
	
	public LogInPageObjects(WebDriver d){
		this.driver=d;
		PageFactory.initElements(d, this);
	}
	
	//@FindBy(xpath="//p[contains(text(),'Login or Create Account')]")
	@FindBy(xpath="//li[@data-cy='account']")	
	WebElement loginl;
	
	@FindBy(xpath="//input[@placeholder='Enter email or mobile number']")
	WebElement emailTxtBox;
	
	@FindBy(xpath="//button/span[contains(text(),'Continue')]")
	WebElement submitBtn;
	
	@FindBy(xpath="//section[@class='modalMain ']/div/p/span[contains(text(),'Invalid phone number')]")
	WebElement errorLabel;
	
	@FindBy(xpath="//*[@id='SW']/div[1]/div[1]/ul/li[6]/div[3]")
	WebElement login_popup;
	
	@FindBy(xpath="//*[@id='SW']/div[1]/div[1]/ul/li[6]/div[3]/div/div[2]/div/p/label[contains(text(),'Login with Phone/Email')]")
	WebElement LoginWithPhoneEmail;
	
	public WebElement getLoginBtn(){
		return loginl;
	}
	
	public WebElement getEmailTxtBox(){
		return emailTxtBox;
	}
	
	public WebElement getSubmitBtn(){
		return submitBtn;
	}
	
	public WebElement getErrorLabel(){
		return errorLabel;
	}
	
	public WebElement getLoginPopup(){
		return login_popup;
	}
	
	public WebElement getLoginWithPhoneEmail(){
		return LoginWithPhoneEmail;
	}
}
