package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPageObjects {
	WebDriver driver;
	
	public LogInPageObjects(WebDriver d){
		this.driver=d;
		PageFactory.initElements(d, this);
	}
	
	@FindBy(xpath="//p[contains(text(),'Login or Create Account')]")
	WebElement loginl;
	
	@FindBy(xpath="//input[@placeholder='Enter email or mobile number']")
	WebElement emailTxtBox;
	
	@FindBy(xpath="//button/span[contains(text(),'Continue')]")
	WebElement submitBtn;
	
	@FindBy(xpath="//section[@class='modalMain ']/div/p/span[contains(text(),'Invalid phone number')]")
	WebElement errorLabel;
	
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
	
}
