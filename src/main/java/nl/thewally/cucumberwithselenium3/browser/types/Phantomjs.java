package nl.thewally.cucumberwithselenium3.browser.types;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class Phantomjs extends BaseDriver {

    @Override
    public DesiredCapabilities setHeader(Map<String, String> headers) {
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        for (String key : headers.keySet()) {
            capabilities.setCapability(
                    PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + key,
                    headers.get(key));
        }

        return capabilities;
    }

    @Override
    public WebDriver init(DesiredCapabilities capabilities) {
        try {
            if (capabilities == null) {
                capabilities = DesiredCapabilities.phantomjs();
            }

            capabilities.setJavascriptEnabled(true);
            capabilities.setCapability(
                    PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    System.getProperty("phantomjs.binary"));
            capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                    new String[] { "--web-security=no", "--ignore-ssl-errors=yes","--ignore-ssl-errors=true","--ssl-protocol=tlsv1" });
            driver = new PhantomJSDriver(capabilities);
            driver.manage().window().setSize(new Dimension(1920, 1080));
            return driver;
        } catch (Exception e) {
            throw new RuntimeException("Error on creating new Phantomjs driver", e);
        }
    }
}
