package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.commons.validator.routines.EmailValidator;

public class LoginPage {	

	WebDriver driver;
	
	public LoginPage(WebDriver driver){
	
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}
  
	@FindBy(xpath = "//input[@type='email' and @placeholder='e.g. email@domain.com']")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@type='password' and @name='password']")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(xpath = "//button[normalize-space()='Log in']")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath = "//div[@class='Message-danger alert alert-block alert-danger Message--withMargin']")
	@CacheLookup
	WebElement validationMsg;
	
	public void setEmail(String email) {
		txtEmail.clear();
		
		if(email.trim().isEmpty()) { txtEmail.sendKeys(""); }
	
		if(!email.trim().isEmpty()) { txtEmail.sendKeys(email); } 
	}

	public void setPassword(String password) {
		txtPassword.clear(); 
		txtPassword.sendKeys(password);   
	}
	
	public void clickBtnLogin() {

		btnLogin.click();
	} 
	
	public String getValidationMsg(String email, String pass, String inputType) {
		
		String validationMessage = "";
		
		if(inputType.equals("invalid")) {
			
		JavascriptExecutor js = (JavascriptExecutor)driver; 
		validationMessage = validationMsg.getText(); 
		
		if(email.trim().isEmpty() || !isEmailValid(email)) {
			validationMessage = (String)js.executeScript("return arguments[0].validationMessage;", txtEmail);	
//			System.out.println("email");
		}
		if(pass.trim().isEmpty()) {
			validationMessage = (String)js.executeScript("return arguments[0].validationMessage;", txtPassword);
//			System.out.println("pass");
		}
	    
		}
		return validationMessage;  
	}
	
	public boolean isEmailValid(String email) {
		
		return EmailValidator.getInstance().isValid(email);
		
	}
	
}
