package com.lithium.mineraloi.university;

import com.lithium.mineraloil.selenium.elements.BaseElement;
import org.openqa.selenium.By;

public class ExampleController {
    private ExampleView view;

    public ExampleController() {
        view = new ExampleView();
    }

    public String getInputText() {
        return view.getFilterBox().getText();
    }

    public void filterSearch(String text) {
        view.getFilterBox().type(text);
        view.getFilterButton().click();
    }

    public boolean checkErrorMessage() {
        BaseElement homeTLError = view.getHomeTimelineDiv()
                                        .createBaseElement(By.xpath("//div[@class='errorMessage']"));

        BaseElement userTLError = view.getUserTimelineDiv()
                                        .createBaseElement(By.xpath("//div[@class='errorMessage']"));

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
