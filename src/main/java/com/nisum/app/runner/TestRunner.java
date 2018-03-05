package com.nisum.app.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty", "html:target/cucumber" },
        features = "classpath:features/promotion.feature",
        glue = "classpath:com.nisum.app.steps"
)
public class TestRunner {

}
