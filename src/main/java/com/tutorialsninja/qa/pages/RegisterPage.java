package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement confirmField;

	@FindBy(name = "agree")
	private WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement pricayPolicyWarning;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement actualFirstNameWarning;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement actualLastNameWarning;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telePhoneWarning;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void yesNewsLetterFieldFunction() {

		yesNewsLetterOption.click();

	}

	public String privacyPolicyWarningFunction() {

		String pricayPolicyWarningText = pricayPolicyWarning.getText();
		return pricayPolicyWarningText;

	}

	public String actualFirstNameWarningFunction() {

		String actualFirstNameWarningText = actualFirstNameWarning.getText();
		return actualFirstNameWarningText;

	}

	public String actualLastNameWarningFunction() {

		String actualLastNameWarningText = actualLastNameWarning.getText();
		return actualLastNameWarningText;
	}

	public String emailWarningFunction() {

		String emailWarningText = emailWarning.getText();
		return emailWarningText;
	}

	public String telePhoneWarningFunction() {

		String telePhoneWarningText = telePhoneWarning.getText();
		return telePhoneWarningText;
	}

	public String retireveDuplicateEmailAddressWarning() {

		String duplicateEmailWarningText = duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;

	}

	public String passwordWarningFunction() {

		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;

	}

	public void firstNameFieldFunction(String firstNameText) {

		firstNameField.sendKeys(firstNameText);

	}

	public void lastNameFieldFunction(String lastNameText) {

		lastNameField.sendKeys(lastNameText);

	}

	public void emailFieldFunction(String emailText) {

		emailField.sendKeys(emailText);

	}

	public void telephoneFieldFunction(String telephoneText) {

		telephoneField.sendKeys(telephoneText);

	}

	public void passwordFieldFunction(String passwordFieldText) {

		passwordField.sendKeys(passwordFieldText);

	}

	public void confirmFieldFunction(String confirmFieldText) {

		confirmField.sendKeys(confirmFieldText);

	}

	public void privacyPolicyFieldFunction() {

		privacyPolicyField.click();

	}

	public AccountSuccessPage continueButtonFunction() {

		continueButton.click();
		return new AccountSuccessPage(driver);

	}
	
	public void registerWithmandatoryFileds(String firstNameText,String lastNameText, String emailText,String telephoneText,String passwordFieldText,String confirmFieldText) {
		
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordFieldText);
		confirmField.sendKeys(confirmFieldText);
		privacyPolicyField.click();
		
	}
	
	public void registerWithAllFields(String firstNameText,String lastNameText, String emailText,String telephoneText,String passwordFieldText,String confirmFieldText) {
		
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordFieldText);
		confirmField.sendKeys(confirmFieldText);
		yesNewsLetterOption.click();
		privacyPolicyField.click();
		
	}

}
