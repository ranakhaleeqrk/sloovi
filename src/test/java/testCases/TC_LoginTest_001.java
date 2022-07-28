package testCases;


import java.io.IOException;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import utilities.XLUtility;


public class TC_LoginTest_001 extends BaseClass{
	
	
	@Test(dataProvider = "LoginData")
	public void loginTest(String email, String pass, String msg, String inputType) throws InterruptedException {
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.setPassword(pass);
		loginPage.setEmail(email);
		loginPage.clickBtnLogin();
//		Thread.sleep(1000);

		String validationMsg = loginPage.getValidationMsg(email,pass,inputType);
		String url = driver.getCurrentUrl();
		
		System.out.println("validationMsg - " + validationMsg );

		if(validationMsg.contains(msg) && inputType.equals("invalid")) 
		{
			Assert.assertTrue(true);
			
		}else if(validationMsg.equals("valid"))
		{
			Assert.assertEquals(url, "https://stage.outreach.sloovi.com/inbox");
			
		}
	}
	
	@DataProvider(name = "LoginData")
	public String[][] getLoginData() throws IOException {
		
		String path = ".\\dataFiles\\loginData.xlsx";
		
		XLUtility xlUtility = new XLUtility(path);
		
		int totalRows = xlUtility.getRowCount("login");
		int totalCols = xlUtility.getCellCount("login", 1);
		
		String loginData[][] = new String[totalRows][totalCols]; 
		
		for(int i=1; i<=totalRows; i++) //1
		{
			for(int j=0; j<totalCols; j++) //0
			{
				loginData[i-1][j] = xlUtility.getCellData("login", i, j);
			}
				
		}
		
		return loginData;
	}

}
