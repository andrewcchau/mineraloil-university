package com.lithium.mineraloi.university;

import com.lithium.mineraloil.selenium.elements.BaseElement;
import org.openqa.selenium.By;

public class TwitterUIController {
    private TwitterUIView view;

    public enum UI_Select {
        HOME,
        USER,
        POST
    }

    public TwitterUIController() {
        view = new TwitterUIView();
    }

    public boolean checkErrorMessage(UI_Select ui) {
        BaseElement error = null;
        if(ui.equals(UI_Select.HOME)){
            error = getHomeTLError();
        } else if(ui.equals(UI_Select.USER)){
            error = getUserTLError();
        } else if(ui.equals(UI_Select.POST)) {
            error = getPostTweetErrorMessage();
        }

        if(error.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }


    /* Home Timeline Methods */
    public void clickHomeTimelineButton() {
        view.getHomeTimelineButton().click();
    }

    public void clickHomeTimelineTab() {
        view.getHomeTLTab().click();
    }

    public String getHomeTLString() {
        return view.getHomeTimelineDiv().getInnerText();
    }

    public BaseElement getHomeTLElement() {
        return view.getHomeTimelineDiv();
    }

    public BaseElement getHomeTLError() {
        return view.getHomeTimelineDiv().createBaseElement(By.xpath("//div[@class='errorMessage']"));
    }

    public BaseElement getHomeMessageText() {
        return getHomeTLElement().createBaseElement(By.xpath("//a[@class='messageText']"));
    }

    public void filterSearch(String text) {
        view.getFilterBox().type(text);
        view.getFilterButton().click();
        view.getFilterButton().click();
    }


    /* User Timeline Methods */
    public void clickUserTimelineButton() {
        view.getUserTimelineButton().click();
    }
    public void clickUserTimelineTab() {
        view.getUserTLTab().click();
    }

    public BaseElement getUserTLError() {
        return view.getUserTimelineDiv().createBaseElement(By.xpath("//div[@class='errorMessage']"));
    }

    public String getUserTLString() {
        return view.getUserTimelineDiv().getInnerText();
    }


    /* Post Tweet Methods */
    public BaseElement getPostTweetErrorMessage() {
        return view.getPostTweetWrapper().createBaseElement(By.xpath("//div[@class='returnMessage error']"));
    }

    public BaseElement getPostTweetSuccessMessage() {
        return view.getPostTweetWrapper().createBaseElement(By.xpath("//div[@class='returnMessage success']"));
    }

    public void clickPostTweetTab() {
        view.getPostTweetTab().click();
    }

    public void postTweet(String message) {
        view.getPostTweetTextArea().click();
        view.getPostTweetTextArea().type(message);
        view.getPostTweetButton().click();
    }

}
