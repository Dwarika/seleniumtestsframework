package com.seleniumtests.driver.web.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.seleniumtests.controller.Logging;
import com.seleniumtests.driver.web.ScreenShot;
import com.seleniumtests.driver.web.ScreenshotUtil;
import com.seleniumtests.exception.MauiException;

public abstract class WebPageSection extends BaseHtmlPage {

	private String name = null;
	private String locator = null;
	protected WebElement element = null;
	private By by = null;
	
	public WebPageSection(String name) {
		super();
		this.name = name;
	}

	public WebPageSection(String name, By by) {
		super();
		this.name = name;
		this.by = by;
	}

	public WebPageSection(String name, String locator) throws MauiException {
		super();
		if (locator.startsWith("xpath=")) {
			locator = locator.substring(6);
		} else if (!locator.startsWith("/") && !locator.startsWith("("))
			locator = "//*[@id='" + locator + "' or @name='" + locator + "']";

		this.name = name;
		this.locator = locator;
		this.by = By.xpath(locator);
	}

	/**
	 * Captures page snapshot.
	 */
	public void capturePageSnapshot() {
		
		ScreenShot screenShot = new ScreenshotUtil(driver).captureWebPageSnapshot();
		String title = screenShot.getTitle();
		String url = screenShot.getLocation();
		
		Logging.logWebOutput(url, title + " (" + Logging.buildScreenshotLog(screenShot) + ")", false);
	}

	public String getLocator() {
		return locator;
	}

	public String getName() {
		return name;
	}

	public By getBy() {
		return by;
	}

	public boolean isPageSectionPresent() {
		return isElementPresent(by);
	}
}