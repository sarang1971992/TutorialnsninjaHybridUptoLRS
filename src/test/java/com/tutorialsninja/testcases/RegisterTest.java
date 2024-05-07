package com.tutorialsninja.testcases;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorials.ninja.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.utils.Utils;

public class RegisterTest extends Base {

	RegisterPage registerpage;
	public WebDriver driver;
	AccountSuccessPage accountsuccesspage;

	@BeforeMethod
	public void setup() {

		loadPropertiesFile();
		driver = initializeBrowser(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		registerpage = homepage.selectRegisterOption();

	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {

		registerpage.registerWithmandatoryFileds("saru", "badgujar", Utils.generateTimeStamp(), "1234567890", "12345",
				"12345");

//		registerpage.firstNameFieldFunction("saru");
//		registerpage.lastNameFieldFunction("badgujar");
//		registerpage.emailFieldFunction(Utils.generateTimeStamp());
//		registerpage.telephoneFieldFunction("1234567890");
//		registerpage.passwordFieldFunction("12345");
//		registerpage.confirmFieldFunction("12345");
//		registerpage.privacyPolicyFieldFunction();

		accountsuccesspage = registerpage.continueButtonFunction();
		String actualSuccessHeading = accountsuccesspage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!", "Account Success page is created");

	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFields() {

		registerpage.registerWithAllFields("saru", "badgujar", Utils.generateTimeStamp(), "1234567890", "12345",
				"12345");

//		registerpage.firstNameFieldFunction("saru");
//		registerpage.lastNameFieldFunction("badgujar");
//		registerpage.emailFieldFunction(Utils.generateTimeStamp());
//		registerpage.telephoneFieldFunction("1234567890");
//		registerpage.passwordFieldFunction("12345");
//		registerpage.confirmFieldFunction("12345");
//		registerpage.yesNewsLetterFieldFunction();
//		registerpage.privacyPolicyFieldFunction();
		accountsuccesspage = registerpage.continueButtonFunction();

		String actualSuccessHeading = accountsuccesspage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!", "Account Success page is created");

	}

	@Test(priority = 3)
	public void verifyRegisteredAccountWithExistingEmailAddressField() {

		registerpage.registerWithAllFields("saru", "badgujar", "sarangbadgujar4@gmail.com", "1234567890", "Sarang@123",
				"Sarang@123");

//		registerpage.firstNameFieldFunction("saru");
//		registerpage.lastNameFieldFunction("badgujar");
//		registerpage.emailFieldFunction("sarangbadgujar4@gmail.com");
//
//		registerpage.telephoneFieldFunction("1234567890");
//		registerpage.passwordFieldFunction("Sarang@123");
//		registerpage.confirmFieldFunction("Sarang@123");
//		registerpage.yesNewsLetterFieldFunction();
//		registerpage.privacyPolicyFieldFunction();

		registerpage.continueButtonFunction();

		String actualWarningHeading = registerpage.retireveDuplicateEmailAddressWarning();

		Assert.assertTrue(actualWarningHeading.contains("Warning: E-Mail Address is already registered!"),
				"Warning message regarding duplicate email!");

	}

	@Test(priority = 4)
	public void verifyRegisteredAccountWithoutFillingAnyDetails() {

		registerpage.continueButtonFunction();

		String actualPrivacyPolicyWarning = registerpage.privacyPolicyWarningFunction();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy!"),
				"privacy policy message for displaying!");

		String actualFirstNameWarning = registerpage.actualFirstNameWarningFunction();
		Assert.assertEquals(actualFirstNameWarning, "First Name must be between 1 and 32 characters!",
				"First Name Warning is not displayed");

		String actualLastNameWarning = registerpage.actualLastNameWarningFunction();
		Assert.assertEquals(actualLastNameWarning, "Last Name must be between 1 and 32 characters!",
				"Last Name Warning is not displayed");

		String actualEmailNameWarning = registerpage.emailWarningFunction();
		Assert.assertEquals(actualEmailNameWarning, "E-Mail Address does not appear to be valid!",
				"Email Warning Name Warning is not displayed");

		String actualTelephoneWarning = registerpage.telePhoneWarningFunction();
		Assert.assertEquals(actualTelephoneWarning, "Telephone must be between 3 and 32 characters!",
				"Telephone Warning is not displayed");

		String actualpasswordWarning = registerpage.passwordWarningFunction();
		Assert.assertEquals(actualpasswordWarning, "Password must be between 4 and 20 characters!",
				"Password Warning is not displayed");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
