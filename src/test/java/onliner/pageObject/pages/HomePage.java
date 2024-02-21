package onliner.pageObject.pages;

import org.openqa.selenium.By;

public class HomePage extends BaseOnlinerPage {
    private static final String PAGE_LOCATOR = "//img[@class='onliner_logo']";

    public HomePage() {
        super(By.xpath(PAGE_LOCATOR), "Onliner Page");
    }
}