package framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Browser {

    private static Browser instance;
    public static WebDriver driver;

    public static Browser getInstance() {
        if (instance == null) {
            driver = DriverFactory.getDriver();
            driver.manage().timeouts().implicitlyWait(PropertyReader.getIntProperty("timeout"), TimeUnit.SECONDS);
        } else {
            System.err.println("Driver does not instance");
        }
        return instance = new Browser();
    }

    public static void windowMaximize() {
        driver.manage().window().maximize();
    }

    public static void navigateTo(String url) {
        driver.get(url);
    }

    public static void close() {
        driver.quit();
        instance = null;
        System.out.println("Driver has been closed.");
    }

    public static void waitForPageLoaded() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(PropertyReader.getIntProperty("page.load.timeout")));
        wait.until(driver1 -> executor.executeScript("return document.readyState").equals("complete"));
    }

    public static WebDriver getDriver() {
        return driver;
    }
}