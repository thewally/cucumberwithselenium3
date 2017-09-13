package nl.thewally.cucumberwithselenium3.browser;

import nl.thewally.cucumberwithselenium3.browser.types.Driver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Browser {
    private static final Logger LOG = LoggerFactory.getLogger(Browser.class);
    private final Driver driver;
    private DesiredCapabilities capabilities;

    public Browser(String browser) {
        this.driver = DriverFactory.getDriver(browser);
    }

    /**
     * This method needs to be called before init
     *
     * @param headers
     */
    public void setHeaders(Map<String, String> headers) {
        capabilities = driver.setHeader(headers);
    }

    /**
     * Call me in the feature step which opens the web browser.
     */
    public void init() {
        /*
         closes driver if the driver is open... this is cause some step defs do
         (loops) multiple headers settings which requires new driver instance
         for the request.
         */
        if (!isClosed()) {
            quit();
        }

        driver.init(capabilities);
    }

    public void openUrl(String url) {
        if (StringUtils.isNotEmpty(url)) {
            driver.getDriver().get(url);
        } else {
            throw new RuntimeException("No url provided!");
        }
    }

    public boolean isClosed() {
        return driver.isClosed();
    }

    public static boolean isWindows(final String os) {
        return os.toLowerCase().contains("win");
    }

    public void openLocation(String url) {
        driver.getDriver().navigate().to(url);
    }

    public void setCookie(String name, String value, String domain, String path, boolean isSecure) {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, 10);
        Date expiry = cal.getTime();
        driver.getDriver().manage().addCookie(
                new Cookie.Builder(name, value).domain(domain)
                        .expiresOn(expiry).path(path).isSecure(isSecure).build()
        );
    }

    public void setCookies(Map<String, String> cookies, String domain) throws Throwable {
        for (String key : cookies.keySet()) {
            setCookie(key, cookies.get(key), domain, "/", false);
        }
    }

    public String getCookieByName(String name) throws Throwable {
        return driver.getDriver().manage().getCookieNamed(name).getValue();
    }

    public String getUrl() {
        return driver.getDriver().getCurrentUrl();
    }

    public void printAllCookies() {
        Set<Cookie> cookies = driver.getDriver().manage().getCookies();
        Iterator<Cookie> itr = cookies.iterator();

        while (itr.hasNext()) {
            Cookie c = itr.next();
            LOG.info("Cookie Name: " + c.getName() + " --- "
                    + "Cookie Domain: " + c.getDomain() + " --- "
                    + "Cookie Value: " + c.getValue() + " --- "
                    + "Cookie Path: " + c.getPath() + " --- "
                    + "Cookie Expiry: " + c.getExpiry());
        }
    }

    public void refresh() {
        driver.getDriver().navigate().refresh();
    }

    public void quit() {
        driver.close();
    }

    /**
     * @return the driver
     */
    public WebDriver getDriver() {
        return driver.getDriver();
    }
}
