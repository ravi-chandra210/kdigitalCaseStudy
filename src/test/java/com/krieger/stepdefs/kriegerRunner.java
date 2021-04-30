package com.krieger.stepdefs;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber-reports"}, features = {"src/test/resources/kreigerTest"}, monochrome=true)

public class kriegerRunner {

}
