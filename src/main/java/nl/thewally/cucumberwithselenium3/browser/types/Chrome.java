package nl.thewally.cucumberwithselenium3.browser.types;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Chrome extends BaseDriver {

    private Map<String, String> headers;

    public Chrome() {
        ChromeDriverManager.getInstance().setup();
    }

    public WebDriver getDriver(Map<String, String> headers) {
        Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator();
        // TODO: try to use import/export functionality from modify headers. 
        File modifyHeaders = new File(System.getProperty("user.dir")
                + "/src/main/resources/Modify-Headers-for-Chrome.crx");

        ChromeOptions options = new ChromeOptions();
        options.addExtensions(modifyHeaders);

        DesiredCapabilities chromeCap = new DesiredCapabilities();
        chromeCap.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(chromeCap);
        driver.get("chrome-extension://innpjfdalfhpcoinfnehdnbkglpmogdi/options.html");
        driver.navigate().refresh();

        while (it.hasNext()) {
            driver.findElement(By.xpath("//button[@tooltip='Add New']")).click();
            WebElement actionElem = driver.findElement(By.name("action"));
            Select select = new Select(actionElem);
            Map.Entry<String, String> pair = it.next();
            select.selectByVisibleText("Add");
            WebElement nameElem = driver.findElement(By.name("name"));
            nameElem.sendKeys(pair.getKey());
            WebElement valueElem = driver.findElement(By.name("value"));
            valueElem.sendKeys(pair.getValue());
            List<WebElement> saveButtons = driver.findElements(By.xpath("//button[@tooltip='Save']"));
            WebElement saveButton = saveButtons.get(saveButtons.size() - 1);
            saveButton.click();
        }

        driver.findElement(By.xpath("//button[@tooltip='Start Modifying Headers']")).click();
        driver.findElement(By.xpath("//button[@tooltip='Enable All']")).click();
        WebElement yesButton = driver.findElement(By.className("btn-success"));
        yesButton.click();
        return driver;
    }

    @Override
    public DesiredCapabilities setHeader(Map<String, String> headers) {
        this.headers = headers;
        return null;
    }

    @Override
    public WebDriver init(DesiredCapabilities capabilities) {
        try {
            driver = (capabilities != null)
                    ? new ChromeDriver(capabilities) : headers != null
                            ? getDriver(headers) : new ChromeDriver();
            return driver;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error on creating new Chrome driver", e);
        }
    }

    @Override
    public void close() {
        super.close();
        this.headers = null;
    }

}
