package com.krieger.pages;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.krieger.utils.utils;

public class kriegerpages {

	utils ob = new utils();
	fileReader flRdr = new fileReader();
	protected static ChromeDriver driver;
	org.apache.logging.log4j.Logger log = LogManager.getLogger(kriegerpages.class);

	static {
		System.setProperty("webdriver.chrome.driver", "./software/chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "./software/geckodriver.exe");
	}
	
	/* Currently Chrome support has been provided as few elements were throwing errors while executing through WebDriver.
	Inconsistency was observed with Webdriver(Chrome and Firefox drivers) while clicking few Webelements (Opening email and Registration Link).*/
	public ChromeDriver launchBrowser(String browser) throws InterruptedException {
		try {
			if(browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			}else if(browser.equalsIgnoreCase("Firefox")) {
				// driver = new FirefoxDriver();                           // Disabling firefox support for now 
			}
			ob.waitForElm(2);
			driver.get(flRdr.getURL());		
			driver.manage().window().maximize();
		} catch (Exception e) {
			log.error("Error occured while launching the browser");
		}
		return driver;
	}

	
	public void navigateTokrieger(){
		try {
			launchBrowser(flRdr.getBrowser());
		} catch (InterruptedException e) {
			log.error("Error occured while navigating to cluno url on browser");
		}
	}

	public void cookiesBtn() {
		ob.waitAndClick(driver, kriegerLocators.cookiesAcptBtn);
	}

	public void regMail(String mailid) {
		ob.sendInput(driver, kriegerLocators.registerMail, mailid);
		ob.waitAndClick(driver, kriegerLocators.submitBtn);
		ob.waitForElm(20);
		validateSentEmail();
	}
	
	public void validateSentEmail() {
		try {
			if(ob.selectElement(driver, kriegerLocators.successRgsMsg).isDisplayed()) {
				String elem = ob.getElemText(driver, kriegerLocators.successRgsMsg);
				if(elem.contains("Sie haben es geschafft!")) {
					log.info("Registration Mail has been sent to new user successfully");
				}else {
					log.warn("Registration Mail is not sent to user");
				}
			}else if(ob.selectElement(driver, kriegerLocators.errorOnSendingMail).isDisplayed()) {
				String erorTx = ob.getElemText(driver, kriegerLocators.successRgsMsg);
				if(erorTx.contains("Sie haben es geschafft!")) {
					log.warn("Registration Mail has been sent to new user successfully");
				}else {
					log.warn("User is already registered but error message is not displayed");
				}
			}			
		} catch (Exception e) {
			log.error("Sent email message is not visible to driver");
		}
	}

	/*... This method is used  as if multiple mails are present in inbox so it will always click on 1st Mail ...*/
	public WebElement getClickablMail() {
		ob.waitForGivenElem(driver, kriegerLocators.mailOpen);
		List <WebElement> elms = driver.findElements(By.xpath(kriegerLocators.mailOpen));
		WebElement elm = null;
		if(elms.size()>=1) {
			elm = elms.get(0);
		}else {
			System.out.println("No email are found in inbox");
		}
		return elm;
	}
	
	/*...Using testing mail domain "getnada.com" to test the registration functionality mutiple times ....*/
	public void opnMailInbox(String email) {
		String eml = ob.trimString(email);
		String testMailUrl = "https://www.getnada.com";
		ob.openNewWindow(driver , testMailUrl);
		ob.waitAndClick(driver, kriegerLocators.addInbox);
		ob.clearElm(driver, kriegerLocators.inboxName);
		ob.sendInput(driver, kriegerLocators.inboxName, eml);
		ob.selectDrpDwnValue(driver, kriegerLocators.dropDown, "getnada.com");
		ob.waitAndClick(driver, kriegerLocators.addNow);
		ob.clickElemWithJS(driver, getClickablMail());
	}

	/*.. Getnada inbox consist of iFrames and multiple adds on the page. Here we are handing Iframe and then clicking on the register link...*/
	public void clickOnRegister() {
		ob.waitForElm(10);
		ob.switchToiframe(driver);
		ob.scrollPageDown(driver);
		ob.waitForElm(20);
		//ob.waitAndClick(driver, mailRegister); // Register link is not clickable all th times 
		ob.clickElemWithJS(driver, ob.selectElement(driver, kriegerLocators.mailRegister)); // Clicking on register link using JavaScript as an alternative
		ob.waitForElm(10);
	}

	/*..Script is opening upto 3 tabs in browser. It is handled here and finally validating the successful registration ..*/
	public void validateRegistration() {
		try {
			ob.switchToWindow(driver, 2);
			ob.waitForElm(5);
			String success = ob.getPageTitle(driver);
			if(success.contains("hoeffner")) {
				log.info("User has registered successfully");
			}else {
				log.info("Page title is "+success);
				ob.takeScrShot(driver);
			}
		} catch (IOException e) {
			log.error("Error occured while validating the successful registion");
		}
	}

	public void quitBrwsr() {
		driver.quit();
	}

}
