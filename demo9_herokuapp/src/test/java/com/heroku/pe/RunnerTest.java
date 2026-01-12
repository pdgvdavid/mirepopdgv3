package com.heroku.pe;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
glue = "com.heroku.pe.definitions",
tags = "@Suite",
plugin = {
        "pretty",
        "html:target/cucumber-reports.html"
}
)
public class RunnerTest {

}
