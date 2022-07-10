package com.flipkart.test.purchase;

import com.flipkart.framework.utils.JsonHelper;
import com.flipkart.test.base.BaseTest;
import com.flipkart.framework.pages.home.HomePage;
import com.flipkart.framework.pages.productDetails.ProductDetailsPage;
import com.flipkart.framework.pages.searchResults.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Map;

public class ProductDetailsComparison extends BaseTest {

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private ProductDetailsPage productDetailsPage;
    private JsonHelper jsonHelper;

    @BeforeTest
    public void setupPages() {
        homePage = new HomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        jsonHelper = new JsonHelper();
    }

    @Test
    public void compareProductDetails() throws Exception {

        String projectRoot = System.getProperty("user.dir");
        Map<?,?> testData = jsonHelper.readJson(projectRoot + "/src/main/java/com/flipkart/framework/testdata/flipkartTestData.json");
        String baseUrl = testData.get("baseUrl").toString();
        String email = testData.get("email").toString();
        String password = testData.get("password").toString();
        String keyword = testData.get("keyword").toString();

        homePage.goTo(baseUrl);
        homePage.getLoginLink().click();
        Assert.assertTrue(homePage.getLoginWidget().isDisplayed());

        homePage.getLoginWidget().login(email, password);
        Assert.assertTrue(homePage.getMyAccountLink().isDisplayed());
        homePage.waitForPageLoaded();

        
        homePage.getSearchWidget().search(keyword);
        Assert.assertTrue(searchResultsPage.getResultList().isDisplayed());

        
        Map<String, String> productInfoFromResults = searchResultsPage.getResultList().selectRandomResultAndReturnProductInfo();
        productDetailsPage.switchToProductDetailsWindow();
        Assert.assertTrue(productDetailsPage.getProductName().isDisplayed());

        String productNameFromDetails = productDetailsPage.getProductName().getName();
        String productPriceFromDetails = productDetailsPage.getProductPrice().getPrice();

        Assert.assertTrue(productNameFromDetails.contains( productInfoFromResults.get("name")),
                "productNameFromResults: " + productInfoFromResults.get("name") +
                        " | productNameFromDetails: " + productNameFromDetails);

        
        Assert.assertTrue(productPriceFromDetails.contains( productInfoFromResults.get("price")),
                "productPriceFromResults: " + productInfoFromResults.get("price") +
                        " | productPriceFromDetails: " + productPriceFromDetails);

    }

}
