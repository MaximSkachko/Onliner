package onliner.pageObject.pages;

import framework.elements.*;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.testng.asserts.SoftAssert;

public class TVPage {
    private static final String APPLIED_FILTERS = "//div[@class='catalog-form__tag-list']//div[text()='%s']";
    private static final String MANUFACTURE = "//div[@class='catalog-form__checkbox-sign' and text()='%s']";
    private static final String CHECK_RESOLUTION = "//div[@class='catalog-form__checkbox-sign' and text()='%s']";
    public static final TextBox TXT_SET_TO_PRICE = new TextBox(By.xpath("//input[@type='text' and @placeholder='до']"));
    private static final TextBox TXT_SPECIAL_PRICE_OFFER = new TextBox(By.xpath("//div[@class='catalog-form__description catalog-form__description_huge-additional " +
            "catalog-form__description_font-weight_bold catalog-form__description_condensed-other catalog-form__description_error-alter']//span[2]"));
    private static final Label SCROLL_TO_RESOLUTION_FILTER = new Label(By.xpath("//div[@class='catalog-form__label-title' and contains(., 'Разрешение')]"));
    private static final Label SCROLL_TO_DIAGONAL_FILTER = new Label(By.xpath("//div[@class='catalog-form__label-title' and contains(., 'Диагональ')]"));
    private static final ComboBox CMBBX_SELECT_DIAGONAL_FROM = new ComboBox(By.xpath("//div[contains(@class,'catalog-form__input-combo_width_full')]/div[1]//select"));
    private static final ComboBox CMBBX_SELECT_DIAGONAL_TO = new ComboBox(By.xpath("//div[contains(@class,'catalog-form__input-combo_width_full')]/div[2]//select"));
    CheckBox checkFilter;
    SoftAssert softAssert = new SoftAssert();

    @Step("Check manufacturer checkbox")
    public void checkManufacture(String manufacture) {
        checkFilter = new CheckBox(By.xpath(String.format(MANUFACTURE, manufacture)));
        checkFilter.click();
    }

    @Step("Set price")
    public void setToPrice(String priceTo) {
        TXT_SET_TO_PRICE.sendKeys(priceTo);
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
        CMBBX_SELECT_DIAGONAL_FROM.selectByValue(diagonalFrom);
    }

    @Step("Set diagonal to")
    public void selectToDiagonal(String diagonalTo) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        CMBBX_SELECT_DIAGONAL_TO.selectByValue(diagonalTo);
    }

    @Step("Check if manufacturer filter applied")
    public void checkManufacturerFilterApplied(String manufacture) {
        TextBox manufacturerFilter = new TextBox(By.xpath(String.format(APPLIED_FILTERS, manufacture)));
        softAssert.assertTrue(manufacturerFilter.isDisplayed());
    }

    @Step("Check if resolution filter applied")
    private void checkResolutionFilterApplied(String resolution) {
        TextBox resolutionFilter = new TextBox(By.xpath(String.format(APPLIED_FILTERS, resolution)));
        softAssert.assertTrue(resolutionFilter.isDisplayed());
    }

    @Step("Assertion: manufacturer is equal to parameter")
    private void checkIfManufacturerMatch(String manufacturer) {
        TextBox FILTERED_ITEM_NAME = new TextBox((By.xpath("//a[@class='catalog-form__link catalog-form__link_primary-additional " +
                "catalog-form__link_base-additional catalog-form__link_font-weight_semibold catalog-form__link_nodecor']/..")));
        for (WebElement w : FILTERED_ITEM_NAME.getElements()) {
            System.out.println(w.getText());
            softAssert.assertTrue(w.getText().contains(manufacturer));
        }
    }

    @Step("Assertion: Resolution is equal to parameter")
    private void checkResolution(String resolution) {
        TextBox FILTERED_DIAGONAL_AND_RESOLUTION = new TextBox(By.xpath("//div[@class='catalog-form__parameter-part " +
                "catalog-form__parameter-part_1']/div[1]"));
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
        double specialOfferPrice = Double.parseDouble(TXT_SPECIAL_PRICE_OFFER.getText().replaceAll("[\\s.а-я]", "").replaceAll(",", "."));
        System.out.println(specialOfferPrice);
        softAssert.assertTrue(specialOfferPrice <= priceTo);
    }

    @Step("Assertion: check prices")
    private void checkPrices(double priceTo) {
        TextBox PRICES = new TextBox(By.xpath("//a[@class='catalog-form__link catalog-form__link_nodecor " +
                "catalog-form__link_primary-additional catalog-form__link_huge-additional catalog-form__link_font-weight_bold']"));
        for (WebElement w : PRICES.getElements()) {
            double prices = Double.parseDouble(w.getText().replaceAll("[\\s.а-я]", "").replaceAll(",", "."));
            System.out.println(prices);
            softAssert.assertTrue(prices <= priceTo);
        }
    }

    public void ifFiltersApplied(String manufacturer, String resolution) {
        checkManufacturerFilterApplied(manufacturer);
        checkResolutionFilterApplied(resolution);
        softAssert.assertAll();
    }

    public void validationOfAllFilters(String manufacturer, String resolution, double diagonalFrom, double diagonalTo, double priceTo) {
        checkIfManufacturerMatch(manufacturer);
        checkResolution(resolution);
        checkInches(diagonalFrom, diagonalTo);
        checkSpecialOfferPrice(priceTo);
        checkPrices(priceTo);
        softAssert.assertAll();
    }
}