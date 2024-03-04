package onliner.pageObject.pages;

import framework.elements.*;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static framework.Browser.getDriver;

public class TVPage {
    private static final Label PAGE_LOCATOR = new Label(By.xpath("//img[@class='onliner_logo']"));
    private static final TextBox APPLIED_FILTERS = new TextBox(By.xpath("//div[@class='catalog-form__tag-list']/div"));

    //private static final TextBox APPLIED_FILTER_RESOLUTION = new TextBox(By.xpath("//div[@class='catalog-form__tag-list']/div[2]"));

//    private static final List<WebElement> FILTERED_ITEM_NAME = getDriver().findElements(By.xpath("//div[@class='catalog-form__description " +
//            "catalog-form__description_primary catalog-form__description_base-additional " +
//            "catalog-form__description_font-weight_semibold catalog-form__description_condensed-other']/a"));

//    private static final List<WebElement> FILTERED_DIAGONAL_AND_RESOLUTION = getDriver().findElements(By.xpath("//div[@class='catalog-form__parameter-part catalog-form__parameter-part_1']/div[1]"));

    private static final TextBox SPECIAL_PRICE_OFFER = new TextBox(By.xpath("//div[@class='catalog-form__description catalog-form__description_huge-additional " +
            "catalog-form__description_font-weight_bold catalog-form__description_condensed-other catalog-form__description_error-alter']//span[2]"));

//    private static List<WebElement> prices = getDriver().findElements(By.xpath("//div[@class='catalog-form__description catalog-form__description_huge-additional " +
//            "catalog-form__description_font-weight_bold catalog-form__description_condensed-other catalog-form__description_primary']"));

    private static final String MANUFACTURE = "//div[@class='catalog-form__checkbox-sign' and text()='%s']";

    public static final TextBox SET_TO_PRICE = new TextBox(By.xpath("//input[@type='text' and @placeholder='до']"));

    private static final String CHECK_RESOLUTION = "//div[@class='catalog-form__checkbox-sign' and text()='%s']";

    private static final Label SCROLL_TO_RESOLUTION_FILTER = new Label(By.xpath("//div[@class='catalog-form__label-title' and contains(., 'Разрешение')]"));

    private static final Label SCROLL_TO_DIAGONAL_FILTER = new Label(By.xpath("//div[@class='catalog-form__label-title' and contains(., 'Диагональ')]"));

    private static final ComboBox SELECT_FROM_DIAGONAL = new ComboBox(By.xpath("//div[2]/div/div[1]/div/select"));

    private static final ComboBox SELECT_TO_DIAGONAL = new ComboBox(By.xpath("//div[2]/div/select"));
    CheckBox checkFilter;

    @Step("Check manufacturer checkbox")
    public void checkManufacture(String manufacture) {
        checkFilter = new CheckBox(By.xpath(String.format(MANUFACTURE, manufacture)));
        checkFilter.click();
    }

    @Step("Set price")
    public void setToPrice(String priceTo) {
        SET_TO_PRICE.sendKeys(priceTo);
    }

    @Step("Check resolution checkbox")
    public void checkResolutionCB(String resolution) {
        checkFilter = new CheckBox(By.xpath(String.format(CHECK_RESOLUTION, resolution)));
        SCROLL_TO_RESOLUTION_FILTER.scrollIntoView();
        checkFilter.click();
    }
    @Step("Set diagonal From")
    public void selectFromDiagonal(String diagonalFrom) {
        SCROLL_TO_DIAGONAL_FILTER.scrollIntoView();
        SELECT_FROM_DIAGONAL.selectByValue(diagonalFrom);
    }
    @Step("Set diagonal to")
    public void selectToDiagonal(String diagonalTo) throws InterruptedException {
        Thread.sleep(3000);
        SELECT_TO_DIAGONAL.selectByValue(diagonalTo);
    }
    SoftAssert softAssert = new SoftAssert();

    @Step("Check if manufacturer filter applied")
    private void checkManufacturerFilterApplied(String manufacture) {
        PAGE_LOCATOR.scrollIntoView();
        System.out.println(APPLIED_FILTERS.getText() + " !!!!!!!!!!!!!!!!!!!!!!!!");
        softAssert.assertTrue(APPLIED_FILTERS.getText().contains(manufacture));
    }

    @Step("Check if resolution filter applied")
    private void checkResolutionFilterApplied(String resolution) {
        softAssert.assertTrue(APPLIED_FILTERS.getText().contains(resolution));
    }

    @Step("Assertion: manufacturer is equal to parameter")
    private void checkIfManufacturerMatch(String manufacturer) {
        TextBox FILTERED_ITEM_NAME = new TextBox((By.xpath("//div[@class='catalog-form__description " +
                "catalog-form__description_primary catalog-form__description_base-additional " +
                "catalog-form__description_font-weight_semibold catalog-form__description_condensed-other']/a")));
        for (WebElement w : FILTERED_ITEM_NAME.getElements()) {
            System.out.println(w.getText());
            softAssert.assertTrue(w.getText().contains(manufacturer));
        }
    }

    @Step("Assertion: Resolution is equal to parameter")
    private void checkResolution(String resolution) {
        TextBox FILTERED_DIAGONAL_AND_RESOLUTION = new TextBox(By.xpath("//div[@class='catalog-form__parameter-part catalog-form__parameter-part_1']/div[1]"));
        for (WebElement w : FILTERED_DIAGONAL_AND_RESOLUTION.getElements()) {
            System.out.println(w.getText());
            softAssert.assertTrue(w.getText().contains(resolution));
        }
    }

    @Step("Assertion: Diagonal is equal to parameter")
    private void checkInches(double diagonalFrom, double diagonalTo) {
        TextBox FILTERED_DIAGONAL_AND_RESOLUTION = new TextBox(By.xpath("//div[@class='catalog-form__parameter-part catalog-form__parameter-part_1']/div[1]"));
        for (WebElement w : FILTERED_DIAGONAL_AND_RESOLUTION.getElements()) {
            double diagonal = Double.parseDouble(w.getText().substring(0, 2));
            softAssert.assertTrue(diagonal >= diagonalFrom & diagonal <= diagonalTo);
        }
    }

    @Step("Assertion: check special offer price")
    private void checkSpecialOfferPrice(double priceTo) {
        double specialOfferPrice = Double.parseDouble(SPECIAL_PRICE_OFFER.getText().replaceAll("[\\s.а-я]", "").replaceAll(",", "."));
        System.out.println(specialOfferPrice);
        softAssert.assertTrue(specialOfferPrice <= priceTo);
    }

    @Step("Assertion: check prices")
    private void checkPrices(double priceTo) {
        TextBox PRICES = new TextBox(By.xpath("//div[@class='catalog-form__description catalog-form__description_huge-additional " +
                "catalog-form__description_font-weight_bold catalog-form__description_condensed-other catalog-form__description_primary']"));
        for (WebElement w : PRICES.getElements()) {
            double prices = Double.parseDouble(w.getText().replaceAll("[\\s.а-я]", "").replaceAll(",", "."));
            System.out.println(prices);
            softAssert.assertTrue(prices <= priceTo);
        }
    }


    public void validationOfAllFilters(String manufacturer, String resolution, double diagonalFrom, double diagonalTo, double priceTo) {
//        checkManufacturerFilterApplied(manufacturer);
//        checkResolutionFilterApplied(resolution);
        checkIfManufacturerMatch(manufacturer);
        checkResolution(resolution);
        checkInches(diagonalFrom, diagonalTo);
        checkSpecialOfferPrice(priceTo);
        checkPrices(priceTo);
        softAssert.assertAll();
    }
}