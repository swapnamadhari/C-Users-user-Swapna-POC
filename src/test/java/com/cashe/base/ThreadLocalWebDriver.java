package com.cashe.base;

import org.openqa.selenium.WebDriver;

public class ThreadLocalWebDriver {

	private ThreadLocalWebDriver() {
	}

	private static ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {

		WebDriver rwd = threadLocalWebDriver.get();
		return rwd;
	}

	public static void setWebDriver(WebDriver driver) {
		threadLocalWebDriver.set(driver);
	}

}