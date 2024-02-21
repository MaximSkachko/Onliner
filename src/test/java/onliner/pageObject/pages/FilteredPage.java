package onliner.pageObject.pages;

import framework.elements.*;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static framework.Browser.getDriver;

public class FilteredPage {
    private static final Label PAGE_LOCATOR = new Label(By.xpath("//img[@class='onliner_logo']"));
    private static final TextBox APPLIED_FILTER_MANUFACTURE = new TextBox(By.xpath("//div[@class='catalog-form__tag-list']/div[1]"));

    private static final TextBox APPLIED_FILTER_RESOLUTION = new TextBox(By.xpath("//div[@class='catalog-form__tag-list']/div[2]"));

    private static final List<WebElement> FILTERED_ITEM_NAME = getDriver().findElements(By.xpath("//div[@class='catalog-form__description " +
            "catalog-form__description_primary catalog-form__description_base-additional " +
            "catalog-form__description_font-weight_semibold catalog-form__description_condensed-other']/a"));

    private static final List<WebElement> FILTERED_DIAGONAL_AND_RESOLUTION = getDriver().findElements(By.xpath("//div[@class='catalog-form__parameter-part catalog-form__parameter-part_1']/div[1]"));

    private static final TextBox SPECIAL_PRICE_OFFER = new TextBox(By.xpath("//div[@class='catalog-form__description catalog-form__description_huge-additional " +
            "catalog-form__description_font-weight_bold catalog-form__description_condensed-other catalog-form__description_error-alter']//span[2]"));

    private static final List<WebElement> PRICES = getDriver().findElements(By.xpath("//div[@class='catalog-form__description catalog-form__description_huge-additional " +
            "catalog-form__description_font-weight_bold catalog-form__description_condensed-other catalog-form__description_primary']"));
    SoftAssert softAssert = new SoftAssert();

    @Step("Check if manufacturer filter applied")
    public void checkManufacturerFilterApplied(String manufacture) {
        PAGE_LOCATOR.scrollIntoView();
        softAssert.assertEquals(APPLIED_FILTER_MANUFACTURE.getText(), manufacture);
    }

    @Step("Check if resolution filter applied")
    public void checkResolutionFilterApplied(String resolution) {
        softAssert.assertEquals(APPLIED_FILTER_RESOLUTION.getText(), resolution);
    }

    @Step("Assertion: manufacturer is equal to parameter")
    public void checkIfManufacturerMatch(String manufacturer) {

        for (WebElement w : FILTERED_ITEM_NAME) {
            System.out.println(w.getText());
            softAssert.assertTrue(w.getText().contains(manufacturer));
        }
    }

    @Step("Assertion: Resolution is equal to parameter")
    public void checkResolution(String resolution) {
        for (WebElement w : FILTERED_DIAGONAL_AND_RESOLUTION) {
            System.out.println(w.getText());
            softAssert.assertTrue(w.getText().contains(resolution));
        }
    }

    @Step("Assertion: Diagonal is equal to parameter")
    public void checkInches(double diagonalFrom, double diagonalTo) {
        for (WebElement w : FILTERED_DIAGONAL_AND_RESOLUTION) {
            double diagonal = Double.parseDouble(w.getText().substring(0, 2));
            softAssert.assertTrue(diagonal >= diagonalFrom & diagonal <= diagonalTo);
        }
    }

    @Step("Assertion: check special offer price")
    public void checkSpecialOfferPrice(double priceTo) {
        double specialOfferPrice = Double.parseDouble(SPECIAL_PRICE_OFFER.getText().replaceAll("[\\s.а-я]", "").replaceAll(",", "."));
        softAssert.assertTrue(specialOfferPrice <= priceTo);
    }

    @Step("Assertion: check prices")
    public void checkPrices(double priceTo) {
        for (WebElement w : PRICES) {
            double prices = Double.parseDouble(w.getText().replaceAll("[\\s.а-я]", "").replaceAll(",", "."));
            System.out.println(prices);
            softAssert.assertTrue(prices <= priceTo);
        }
    }
}