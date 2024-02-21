package onliner.pageObject.pages;

import framework.elements.Label;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CataloguePage extends BaseOnlinerPage {

    private static final String PAGE_LOCATOR = "//div[@class='catalog-navigation__title' and text()='Каталог']";
    private static final String NAV_CATALOGUE_SUBITEM = "//div[@class='catalog-navigation-list__aside-item' and contains(., '%s')]";

    private static final String NAV_SUBITEM_GROUP = "//div[@data-id='1']//span[@class='catalog-navigation-list__dropdown-title' and contains(., '%s')]";

    public CataloguePage() {
        super(By.xpath(PAGE_LOCATOR), "Catalogue Page");
    }

    @Step("Click on item group")
    public void clickOnItemGroup(String itemGroup) {
        Label lblItemGroup = new Label(By.xpath(String.format(NAV_CATALOGUE_SUBITEM, itemGroup)));
        lblItemGroup.clickAndWait();
    }

    @Step("Click on subItem")
    public void clickOnSubItemGroup(String subItemGroup) {
        Label lblSubItemGroup = new Label(By.xpath(String.format(NAV_SUBITEM_GROUP, subItemGroup)));
        lblSubItemGroup.isDisplayed();
        lblSubItemGroup.clickAndWait();
    }
}