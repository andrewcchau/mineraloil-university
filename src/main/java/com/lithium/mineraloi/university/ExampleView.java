package com.lithium.mineraloi.university;

import com.lithium.mineraloi.university.browser.DriverManager;
import com.lithium.mineraloil.selenium.elements.BaseElement;
import com.lithium.mineraloil.selenium.elements.Driver;
import com.lithium.mineraloil.selenium.elements.TextElement;
import org.openqa.selenium.By;

public class ExampleView {
    private Driver driver;

    public ExampleView() {
        driver = DriverManager.INSTANCE.getDriver();
    }

    public BaseElement getFilterButton() {
        return driver.createBaseElement(By.xpath("//button[@class='filterButton']"));
    }

    public TextElement getFilterBox() {
        return driver.createTextElement(By.xpath("//input[@class='textInput']"));
    }

    public BaseElement getHomeTimelineDiv() {
        return driver.createBaseElement(By.xpath("//div[@class='dataHome']"));
    }

    public BaseElement getHomeTimelineButton() {
        return driver.createBaseElement(By.xpath("//button[@class='homeTimelineButton']"));
    }

    public BaseElement getUserTimelineDiv() {
        return driver.createBaseElement(By.xpath("//div[@class='userHome']"));
    }

    public BaseElement getUserTimelineButton() {
        return driver.createBaseElement(By.xpath("//button[@class='userTimelineButton']"));
    }
}
