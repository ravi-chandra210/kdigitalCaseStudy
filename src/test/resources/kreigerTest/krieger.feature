Feature: Subscription

@test
Scenario Outline: Subscribe with different mails
Given User navigates to hoeffner url
When Accepts all cookies and sees newsletter subscription
Then User enters valid "<mailid>" and clicks submit to observe mail sent successfully
When User should navigate to mailbox "<mailid>" in new tab and open received email
Then User should click on Complete Registration Now
And User should navigate to successfully registered window
And User should quit the browser

Examples:
			|mailid|
			|kdizitaal007@getnada.com|
			|kdizitaal022@getnada.com|

# As there is only 1 test case so I am not reading it from excel sheet. But when there are more test cases we read all the test cases and test data from excel itself which is a best practice for any automation.