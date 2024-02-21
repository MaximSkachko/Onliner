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

        ItemsPage itemsPage = new ItemsPage();
        itemsPage.filters.checkManufacture(manufacturer);
        itemsPage.filters.setToPrice(String.valueOf(priceTo));
        itemsPage.filters.checkResolution(resolution);
        itemsPage.filters.selectFromDiagonal("400");
        Thread.sleep(5000);
        itemsPage.filters.selectToDiagonal("500");
        Thread.sleep(5000);

        FilteredPage filteredPage = new FilteredPage();
        filteredPage.checkManufacturerFilterApplied(manufacturer);
        filteredPage.checkResolutionFilterApplied(resolution);
        filteredPage.checkIfManufacturerMatch(manufacturer);
        filteredPage.checkResolution(resolution);
        filteredPage.checkInches(diagonalFrom, diagonalTo);
        filteredPage.checkSpecialOfferPrice(priceTo);
        filteredPage.checkPrices(priceTo);
    }
}