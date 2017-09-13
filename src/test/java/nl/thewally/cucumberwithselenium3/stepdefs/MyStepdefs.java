package nl.thewally.cucumberwithselenium3.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.thewally.cucumberwithselenium3.context.AppContext;
import nl.thewally.cucumberwithselenium3.browser.pageobjects.GenericObjects;
import nl.thewally.cucumberwithselenium3.properties.TestProperties;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

    @When("^open url (.*)$")
    public void openUrl(String url) throws Throwable {
        context.getBrowserDriver().init();
        context.getBrowserDriver().openUrl(url);
    }

//    TODO: Fix cookie creation with PHANTOMJS: https://sqa.stackexchange.com/questions/18244/phantomsjs-rejects-cookies-set-programmatically
    @When("^Set cookies$")
    public void setCookies(Map<String, String> cookies) throws Throwable {
        context.getBrowserDriver().setCookies(cookies, "google.nl");
    }

    @When("^open location (.*)$")
    public void openLocation(String url) throws Throwable {
        context.getBrowserDriver().openUrl(url);
    }

    @Then("^stop browser$")
    public void stopBrowser() throws Throwable {
        Thread.sleep(300000);
        LOG.info("Stop Browser for Test purposes.");
    }

    @Then("^cookie is created$")
    public void cookieIsCreated() throws Throwable {
        Assert.assertEquals("cookievalue", context.getBrowserDriver().getCookieByName("cookie"));
    }

    @After
    public void close() {
        try {
            context.getBrowserDriver().quit();
        } catch (Exception e) {

        }
    }


}
