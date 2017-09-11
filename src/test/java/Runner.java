import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber"},
        dryRun = false,
        glue = {"nl.thewally.cucumberwithselenium3.stepdefs"},
        features = {"src/test/resources/features"}
//        ,tags = {"@x"}
)
public class Runner {
}
