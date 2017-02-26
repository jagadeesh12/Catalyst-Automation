package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty","json:target/reports/json/cucumber-html-reports/cucumber.json" },

        features={
                "src/test/java/Feature/Org-CreateDelete.feature"
               // "/home/jagadeesh/CatalystAPIAutomation/comcatalyst/src/test/java/Feature/BU-CreateDelete.feature"
        }

        //features="/home/jagadeesh/CatalystAPIAutomation/comcatalyst/src/test/java/Feature/BU-CreateDelete.feature"
        //features = "D:\\automation\\TORQ\\comcatalyst\\src\\test\\java\\Feature\\Org-CreateDelete.feature"
        //features="/home/jagadeesh/comcatalyst/src/test/java/Feature/BU-CreateDelete.feature"


        ,glue={"stepdefinition"}
)

public class TestRunner1 {

}