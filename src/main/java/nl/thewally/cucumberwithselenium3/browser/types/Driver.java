package nl.thewally.cucumberwithselenium3.browser.types;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

/**
 *
 * @author schoemanas
 */
public interface Driver {

    public boolean isClosed();

    public void close();

    public DesiredCapabilities setHeader(Map<String, String> headers);

    public WebDriver init(DesiredCapabilities capabilities);

    public WebDriver getDriver();
}
