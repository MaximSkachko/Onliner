package onliner.pageObject.baseComponents;

import framework.elements.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Filters {
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
    public void checkResolution(String resolution) {
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
    public void selectToDiagonal(String diagonalTo) {
        SELECT_TO_DIAGONAL.selectByValue(diagonalTo);
    }
}