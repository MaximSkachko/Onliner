package framework;

import framework.elements.Label;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Date;

public class BasePage {
    protected By titleLocator;
    protected String title;

    protected PropertyReader propertyReader = new PropertyReader("log.Properties");

    public BasePage(final By locator, final String pageTitle) {
        init(locator, pageTitle);
        assertIsOpen();
    }

    private void init(final By locator, final String pageTitle) {
        this.titleLocator = locator;
        this.title = pageTitle;
    }

    public void assertIsOpen() {
        long before = new Date().getTime();
        try {
            Browser.waitForPageLoaded();
            Label element = new Label(titleLocator);
            element.isDisplayed();
            long openTime = new Date().getTime() - before;
            System.out.println(String.format(propertyReader.getProperty("loc.form.appears"), title) + String.format(" in %s msec", openTime));
        } catch (Throwable e) {
            Assert.assertTrue(true, title + " does not open");
        }
    }
}