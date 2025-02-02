package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropDownMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(name = "search")
	private WebElement searchBoxField;

	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButtonField;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void searchBoxFieldFunction(String productText) {

		searchBoxField.sendKeys(productText);
	}

	public SearchPage searchButtonFieldFunction() {

		searchButtonField.click();
		return new SearchPage(driver);

	}

	public void clickOnMyAccount() {

		myAccountDropDownMenu.click();
	}

	public LoginPage selectLoginOption() {

		loginOption.click();
		return new LoginPage(driver);

	}

	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}
}
