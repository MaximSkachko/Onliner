package onliner.pageObject.pages;

import org.openqa.selenium.By;

public class ItemsPage extends BaseOnlinerPage {

    private static final String PAGE_LOCATOR = "//h1[@class='catalog-form__title catalog-form__title_big-alter' and contains(., '%s')]";

    public ItemsPage() {
        super(By.xpath(String.format(PAGE_LOCATOR, "Телевизоры")), "Item Page");
    }
}