package com.krieger.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class utils {
	
	/*.. All required methods are overridden here in util class in oder to achieve a effective automation script ...*/
	
	org.apache.logging.log4j.Logger log = LogManager.getLogger(utils.class);
	
	public void waitForElm(int wt) {
		try {
			int wait = wt*100;
			Thread.sleep(wait);
		} catch (InterruptedException e) {
		}
	}
	
	public void clickElemWithJS(ChromeDriver driver, WebElement elem) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", elem);
	}
	
	public void scrollPageDown(ChromeDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
	}

	public void clearElm(ChromeDriver driver, String xpath) {
		try {
			waitForGivenElem(driver, xpath);
			WebElement elem = selectElement(driver, xpath);
			if(elem.isDisplayed()==true) {
				elem.clear();
			}
		} catch (Exception e) {
			log.error("Error occured while clearing Input filed");
		}
	}

	public void selectDrpDwnValue(ChromeDriver driver, String xpath, String value) {
		try {
			waitForGivenElem(driver, xpath);
			WebElement elem = selectElement(driver, xpath);
			Select dropdomain = new Select(elem);
			dropdomain.selectByValue(value);
		} catch (Exception e) {
			log.error("Error occured while selecting dropdown value");
		}
	}

	public void sendInput(ChromeDriver driver, String xpath, String inputValue) {
		try {
			WebElement elem = selectElement(driver, xpath);
			elem.sendKeys(inputValue);
		} catch (Exception e) {
			log.error("Error occured while sending input value");
		}
	}

	public void takeScrShot(ChromeDriver driver) throws IOException {
		try {
			TakesScreenshot p1 = (TakesScreenshot)driver;
			File actual = p1.getScreenshotAs(OutputType.FILE);
			File expected = new File("./screenshots/" + getCurrentTime() +".jpeg");
			FileUtils.copyFile(actual, expected);
		} catch (Exception e) {
			log.error("Unable to take screenshot of required screen.");
		} 
	}

	public String getCurrentTime() {
		String time = null;
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmmss");  
			LocalDateTime now = LocalDateTime.now();  
			time = dtf.format(now);
		} catch (Exception e) {
			log.error("Error occured while capturing the current time");
		}
		return time;
	}

	public WebElement selectElement(ChromeDriver driver, String xPath) {
		WebElement element = null;
		try {
			element = driver.findElementByXPath(xPath);
		} catch (Exception e) {
			
			log.error("Error occured while finding the element");
		}
		return element;	
	}
	
	public void switchToiframe(ChromeDriver driver) {
		try {
			WebElement iframe1 = driver.findElementById("the_message_iframe");
			driver.switchTo().frame(iframe1);
		} catch (Exception e) {
			log.error("Iframe is not displayed");
		}	
	}

	public void openNewWindow(ChromeDriver driver , String url) {
		((JavascriptExecutor)driver).executeScript("window.open()");
		switchToWindow(driver, 1);
		waitForElm(2);
		driver.get(url);
	}

	public void waitForGivenElem(ChromeDriver driver , String xpath) {
		try {
			WebDriverWait w = new WebDriverWait(driver,10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			log.error("Error Occured while waiting for the element");
		}
	}
	
	public void waitAndClick(ChromeDriver driver , String xpath) {
		try {
			WebDriverWait w = new WebDriverWait(driver,10);
			w.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
		} catch (Exception e) {
			log.error("Error Occured while waiting for the element to be clickable");
		}
	}

	public void switchActiveWindow(ChromeDriver driver) {
		driver.switchTo().activeElement();	   
	}

	public void switchToWindow(ChromeDriver driver , int window) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		if(window<tabs.size()) {
		driver.switchTo().window(tabs.get(window));
		}else {
			log.error("Desired window is not opened");
		}
	}

	public String getElemText(ChromeDriver driver , String xpath) {
		String txt = null;
		try {
			waitForGivenElem(driver, xpath);
			WebElement elem = selectElement(driver, xpath);
			if(elem.isDisplayed()==true) {
				txt = elem.getText();
			}
		} catch (Exception e) {
			log.error("Element for which user wants to get the text is not displayed");
		}
		return txt;
	}
	
	public String getPageTitle(ChromeDriver driver) {
	waitForElm(10);
		return driver.getTitle();
	}

	public String trimString(String str) {
		String[] strAr = str.split("@");
		return strAr[0];

	}

}