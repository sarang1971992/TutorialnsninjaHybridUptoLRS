package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	@FindBy(id = "input-password")
	private WebElement emailPasswordField;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void emailAddressField(String emailText) {
		emailAddressField.sendKeys(emailText);
	}

	public void passwordField(String passwordText) {

		emailPasswordField.sendKeys(passwordText);
	}

	public AccountPage clickLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}

	public String retireveEmailPasswordNotMatching() {
		String textWarning = emailPasswordNotMatchingWarning.getText();
		return textWarning;
	}

}
