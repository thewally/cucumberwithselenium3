package nl.thewally.cucumberwithselenium3.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.thewally.cucumberwithselenium3.context.AppContext;
import nl.thewally.cucumberwithselenium3.browser.pageobjects.GenericObjects;
import nl.thewally.cucumberwithselenium3.properties.TestProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyStepdefs {
    private static final Logger LOG = LoggerFactory.getLogger(MyStepdefs.class);
    private GenericObjects genericObjects;

    @Autowired
    private AppContext context;

    @Autowired
    private TestProperties properties;

    @Before
    public void prepare() throws Throwable {
        genericObjects = new GenericObjects(context);
    }

    @Given("^Set request headers$")
    public void setRequestHeaders(Map<String, String> requestHeaders) throws Throwable {
        Map<String, String> headers = new HashMap<>();

        for (String key : requestHeaders.keySet()) {
                headers.put(key, requestHeaders.get(key));
        }
        context.getBrowserDriver().setHeaders(headers);
    }

    @When("^open idp authenticate screen with timestamp (\\d+) seconds in the (.*)$")
    public void openIdpAuthenticateScreen(int seconds, String pastOrFuture) throws Throwable {
        Date date = new Date();
        long minusMilliSeconds = seconds*1000;
        long milliseconds = date.getTime();
        long result;
        if(pastOrFuture.equals("future")) {
            result = milliseconds + minusMilliSeconds;
        } else {
            result = milliseconds - minusMilliSeconds;
        }
        context.getBrowserDriver().init();
        context.getBrowserDriver().openUrl("/idp/bankid/authenticate.htm?trxid=1234567890123456&idpid="+result+"-123456");
    }

    @Then("^stop browser$")
    public void stopBrowser() throws Throwable {
        Thread.sleep(300000);
        LOG.info("Stop Browser for Test purposes.");
    }

    @After
    public void close() {
        try {
            context.getBrowserDriver().quit();
        } catch (Exception e) {

        }
    }
}
