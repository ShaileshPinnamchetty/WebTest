package Test;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects {
	WebDriver driver;
	
	public HomePageObjects(WebDriver d){
		this.driver=d;
		PageFactory.initElements(d, this);
	}
	
	@FindBy(css="ul[class='fswTabs latoBlack greyText']>li")
	List<WebElement> ticketOptions;
	
	@FindBy(css="div[class='fsw_inputBox dates reDates inactiveWidget ']>div span")
	WebElement returnLabel;
	
	@FindBy(xpath="//li[@data-cy='mulitiCityTrip']")
	WebElement multiCityRadioBtn;
	
	@FindBy(css="input[data-cy='fromAnotherCity0']")
	WebElement fromList;
	
	@FindBy(xpath="//ul[@class='react-autosuggest__suggestions-list']/li[1]/div/div/p[1]")
	WebElement firstListItem;
	
	@FindBy(xpath="(//div[@class='DayPicker-Body']//following::p[text()='21'])[1]")
	WebElement departure1;
	
	@FindBy(css="input#toAnotherCity1")
	WebElement anotherCity;
	
	@FindBy(xpath="(//div[@class='DayPicker-Body']//following::p[text()='28'])[1]")
	WebElement departure2;
	
	@FindBy(xpath="//div[@class='DayPicker-Month'][1]/div[3]//descendant::p")
	List<WebElement> calndar;
	
	@FindBy(xpath="//div[@data-cy='outsideModal']")
	WebElement outsideModal;	
		
	public List<WebElement> getTicketOptions(){
		return ticketOptions;
	}
	
	public WebElement getReturnLabel(){
		return returnLabel;
	}
	
	public WebElement getmultiCityRadioBtn(){
		return multiCityRadioBtn;
	}
	
	public WebElement getfromList(){
		return fromList;
	}
	
	public WebElement getfirstListItem(){
		return firstListItem;
	}
	
	public WebElement getdeparture1(){
		return departure1;
	}
	
	public WebElement getanotherCity(){
		return anotherCity;
	}
	
	public WebElement getdeparture2(){
		return departure2;
	}

	public List<WebElement> getCalndar(){
		return calndar;
	}
	
	public WebElement getoutsideModal(){
		return outsideModal;
	}
}
