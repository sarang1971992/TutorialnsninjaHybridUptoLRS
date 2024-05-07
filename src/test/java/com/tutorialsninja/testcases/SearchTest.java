package com.tutorialsninja.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorials.ninja.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {

	HomePage homepage;
	SearchPage searchpage;
	public WebDriver driver;

	@BeforeMethod
	public void setup() {

		loadPropertiesFile();
		driver = initializeBrowser(prop.getProperty("browser"));

		homepage = new HomePage(driver);
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {

		homepage.searchBoxFieldFunction("HP");
		searchpage = homepage.searchButtonFieldFunction();

		Assert.assertTrue(searchpage.displatStatusOfHPValidProduct(), "Valid product HP is not displayed in search");

	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {

		homepage.searchBoxFieldFunction("HONDA");
		searchpage = homepage.searchButtonFieldFunction();

		String actualSearchMessage = searchpage.noProductMessageFunction();
		Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.",
				"No product in search");

	}

	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {

		homepage.searchBoxFieldFunction("");
		searchpage = homepage.searchButtonFieldFunction();

		String actualSearchMessage = searchpage.noProductMessageFunction();
		Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.",
				"No product in search");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
