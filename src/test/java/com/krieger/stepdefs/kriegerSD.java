package com.krieger.stepdefs;

import com.krieger.pages.kriegerpages;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class kriegerSD {
	kriegerpages kriegerOb = new kriegerpages(); 
	@Given("User navigates to hoeffner url")
	public void user_navigates_to_hoeffner_url() {
	    kriegerOb.navigateTokrieger();
	}

	@When("Accepts all cookies and sees newsletter subscription")
	public void accepts_all_cookies_and_sees_newsletter_subscription() {
	    kriegerOb.cookiesBtn();
	}

	@Then("User enters valid {string} and clicks submit to observe mail sent successfully")
	public void user_enters_valid_and_clicks_submit_to_observe_mail_sent_successfully(String mailid) {
		kriegerOb.regMail(mailid);
	}

	@When("User should navigate to mailbox {string} in new tab and open received email")
	public void user_should_navigate_to_mailbox_in_new_tab_and_open_received_email(String mailid) {
	    kriegerOb.opnMailInbox(mailid);
	}

	@Then("User should click on Complete Registration Now")
	public void user_should_click_on_Complete_Registration_Now() {
		kriegerOb.clickOnRegister();
	}

	@Then("User should navigate to successfully registered window")
	public void user_should_navigate_to_successfully_registered_window() throws InterruptedException {
	    kriegerOb.validateRegistration();
	}

	@Then("User should quit the browser")
	public void user_should_quit_the_browser() {
	    kriegerOb.quitBrwsr();
	}
}