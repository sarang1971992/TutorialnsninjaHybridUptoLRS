package com.tutorialsninja.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorials.ninja.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.utils.Utils;

public class LoginTest extends Base {

	LoginPage loginpage;
	public WebDriver driver;
	AccountPage accountpage;

	@BeforeMethod
	public void setup() {

		loadPropertiesFile();
		driver = initializeBrowser(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		loginpage = homepage.selectLoginOption();

	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyWithValidCredentials(String email, String password) {

		loginpage.emailAddressField(email);
		loginpage.passwordField(password);
		accountpage = loginpage.clickLoginButton();

		Assert.assertTrue(accountpage.getDisplayStatusAboutEditYourInformation(),
				"Edit your Account information not Displayed");

	}

	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {

		// Object [][] data = Utils.getTestDataFromExcel("Login");

		Object[][] data = Utils.getTestDataFromExcel("Login");

		return data;
	}

	@Test(priority = 2)
	public void verifyWithInvalidCredentials() {

		loginpage.emailAddressField(Utils.generateTimeStamp());
		loginpage.passwordField(dataProp.getProperty("invalidpassword"));
		loginpage.clickLoginButton();

		String actualStringWarning = loginpage.retireveEmailPasswordNotMatching();

		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";

		Assert.assertTrue(actualStringWarning.contains(expectedWarningMessage),
				"Expected Warning message is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailandValidPassword() {

		loginpage.emailAddressField(Utils.generateTimeStamp());
		loginpage.passwordField("Sarang@123");
		loginpage.clickLoginButton();

		String actualStringWarning = loginpage.retireveEmailPasswordNotMatching();

		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";

		Assert.assertTrue(actualStringWarning.contains(expectedWarningMessage),
				"Expected Warning message is not displayed");

	}

	@Test(priority = 4)
	public void verifyWithValidEmailAndInvalidPassword() {

		loginpage.emailAddressField("Sarangbadgujar4@gmail.com");
		loginpage.passwordField(dataProp.getProperty("invalidpassword"));
		loginpage.clickLoginButton();

		String actualStringWarning = loginpage.retireveEmailPasswordNotMatching();

		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";

		Assert.assertTrue(actualStringWarning.contains(expectedWarningMessage),
				"Expected Warning message is not displayed");

	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {

		loginpage.clickLoginButton();

		String actualStringWarning = loginpage.retireveEmailPasswordNotMatching();

		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";

		Assert.assertTrue(actualStringWarning.contains(expectedWarningMessage),
				"Expected Warning message is not displayed");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
