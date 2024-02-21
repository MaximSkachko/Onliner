package onliner.pageObject.pages;

import framework.BasePage;
import onliner.pageObject.baseComponents.*;
import org.openqa.selenium.By;

public class BaseOnlinerPage extends BasePage {
    public Header header = new Header();
    public Filters filters = new Filters();

    public BaseOnlinerPage(By locator, String pageTitle) {
        super(locator, pageTitle);
    }
}