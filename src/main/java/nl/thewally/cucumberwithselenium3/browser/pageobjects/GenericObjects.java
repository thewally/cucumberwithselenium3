package nl.thewally.cucumberwithselenium3.browser.pageobjects;



import nl.thewally.cucumberwithselenium3.context.AppContext;

public class GenericObjects {

    private final AppContext context;

    public GenericObjects(AppContext context) {
        this.context = context;
    }

//    public String getPageTitle() throws Exception {
//        DriverWaitUtil.waitForElement(context.getDriver(), By.className("rass-page-title"));
//        return context.getDriver().findElement(By.className("rass-page-title")).getText();
//    }
//
//    public String getUrl() {
//        return context.getDriver().getCurrentUrl();
//    }
//
//    public WebElement getWebElementByLinkText(String linkText) {
//        return context.getDriver().findElement(By.linkText(linkText));
//    }
//
//    public List<WebElement> getListOfRowsForTableById(String id) throws Throwable {
//        WebElement table = context.getDriver().findElement(By.id(id));
//        DriverWaitUtil.waitForElement(context.getDriver(), 15).
//                until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.id(id),
//                                By.tagName("tr")));
//        return table.findElements(By.tagName("tr"));
//    }
//
//    public List<WebElement> getListOfTableHeadersForTableByClass(String className) {
//        WebElement table = context.getDriver().findElement(By.className(className));
//        return table.findElements(By.tagName("th"));
//    }
//
//    public WebElement getLinkById(String id) {
//        DriverWaitUtil.waitForElement(context.getDriver(), By.id(id));
//        return context.getDriver().findElement(By.id(id));
//    }
//
//    public WebElement getLinkByLinkText(String linkText) {
//        DriverWaitUtil.waitForElement(context.getDriver(), By.linkText(linkText), 3);
//        return context.getDriver().findElement(By.linkText(linkText));
//    }
//
//    public void switchToIframe(String iframe) {
//        DriverWaitUtil.waitForElement(context.getDriver(), By.id(iframe), 15);
//        context.getDriver().switchTo().frame(iframe);
//    }
//
//    public WebElement getSectionIdinError() {
//        DriverWaitUtil.waitForElement(context.getDriver(), By.id("rass-error-section"));
//        return context.getDriver().findElement(By.id("rass-error-section"));
//    }

    public void switchToDefault() {
        context.getDriver().switchTo().defaultContent();
    }

    public class KeyValuePair {

        private String key, value;

        public void setKey(String key) {
            this.key = key;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getkey() {
            return key;
        }

        public String getValue() {
            return value;
        }

    }
}
