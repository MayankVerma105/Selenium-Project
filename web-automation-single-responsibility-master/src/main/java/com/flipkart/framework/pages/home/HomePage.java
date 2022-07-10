package com.flipkart.framework.pages.home;

import com.flipkart.framework.pages.common.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {

    private LoginLink loginLink;
    private MyAccountLink myAccountLink;
    private LoginWidget loginWidget;
    private SearchWidget searchWidget;
    private SearchSuggestion searchSuggestion;

    public HomePage(WebDriver driver) {
        super(driver);
        this.myAccountLink = PageFactory.initElements(driver, MyAccountLink.class);
        this.loginLink = PageFactory.initElements(driver, LoginLink.class);
        this.loginWidget = PageFactory.initElements(driver, LoginWidget.class);
        this.searchWidget = PageFactory.initElements(driver, SearchWidget.class);
        this.searchSuggestion = PageFactory.initElements(driver, SearchSuggestion.class);
    }

    public LoginLink getLoginLink() { return loginLink; }

    public MyAccountLink getMyAccountLink() { return myAccountLink; }

    public LoginWidget getLoginWidget() {
        return loginWidget;
    }

    public SearchWidget getSearchWidget() {
        return searchWidget;
    }

    public SearchSuggestion getSearchSuggestion() { return searchSuggestion; }
}
