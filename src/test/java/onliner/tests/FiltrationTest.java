package onliner.tests;

import framework.BaseTest;
import io.qameta.allure.Description;
import onliner.pageObject.pages.*;
import org.testng.annotations.*;

public class FiltrationTest extends BaseTest {
    @Test
    @Description("Test description")
    @Parameters({"manufacturer", "resolution", "priceTo", "diagonalFrom", "diagonalTo"})
    public void checkFiltration(String manufacturer, String resolution, double priceTo, double diagonalFrom, double diagonalTo) throws InterruptedException {
        HomePage homePage = new HomePage();
        homePage.header.mainMenuNavigation("Каталог");

        CataloguePage cataloguePage = new CataloguePage();
        cataloguePage.header.catalogueNavigation("Электроника");
        cataloguePage.clickOnItemGroup("Телевидение");
        cataloguePage.clickOnSubItemGroup("Телевизоры");

        TVPage tvPage = new TVPage();
        tvPage.checkManufacture(manufacturer);
        tvPage.setToPrice(String.valueOf(priceTo));
        tvPage.checkResolutionCB(resolution);
        tvPage.selectFromDiagonal("400");
        tvPage.selectToDiagonal("500");
        tvPage.validationOfAllFilters(manufacturer, resolution, diagonalFrom, diagonalTo, priceTo);
    }
}