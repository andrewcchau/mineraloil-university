package com.lithium.mineraloi.university;

import com.lithium.mineraloil.selenium.elements.BaseElement;
import org.openqa.selenium.By;

public class TwitterUIController {
    private TwitterUIView view;

    public TwitterUIController() {
        view = new TwitterUIView();
    }

    public void filterSearch(String text) {
        view.getFilterBox().type(text);
        view.getFilterButton().click();
    }

    public BaseElement getHomeTLError() {
        return view.getHomeTimelineDiv().createBaseElement(By.xpath("//div[@class='errorMessage']"));
    }

    public BaseElement getUserTLError() {
        return view.getUserTimelineDiv().createBaseElement(By.xpath("//div[@class='errorMessage']"));
    }

    public boolean checkErrorMessage() {
        BaseElement homeTLError = getHomeTLError();
        BaseElement userTLError = getUserTLError();

        if(userTLError.isDisplayed() || homeTLError.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public String getHomeTLString() {
        return view.getHomeTimelineDiv().getInnerText();
    }

    public BaseElement getHomeTLElement() {
        return view.getHomeTimelineDiv();
    }

    public void clickHomeTimelineButton() {
        view.getHomeTimelineButton().click();
    }

    public void clickUserTimelineButton() {
        view.getUserTimelineButton().click();
    }
}
