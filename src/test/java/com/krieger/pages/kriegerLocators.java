package com.krieger.pages;

public class kriegerLocators {

/*.. All the webelement locators are stored in this class for easier maintenance ..*/
	
	public static String cookiesAcptBtn = "//div[@class='button__label button__label--']";
	public static String registerMail = "//input[@class='input input----text']";
	public static String submitBtn = "//button[@class='button button--widthAuto']";
	public static String addInbox = "//li[@class='items-center']/button";
	public static String inboxName = "//input[@id='grid-first-name']";
	public static String dropDown = "//select[contains(@class, 'block')]";
	public static String addNow = "//button[contains(text(), 'Add now!')]";
	public static String mailOpen = "//*[contains(@class, 'w-full flex flex-row')]/tbody/tr/td/a";
	public static String iFrame = "the_message_iframe";
	public static String mailRegister = "//a[@class='cta1a']";
	public static String successRgsMsg = "//div[@id='mainContent']//p[contains(text(),'Gutschein erhalten')]";
	public static String successMail = "//div[@class='footerNewsletter__confirmation']";
	public static String errorOnSendingMail = "//div[@class='footerNewsletter__confirmation footerNewsletter__error']";
}
