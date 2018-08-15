package com.lithium.mineraloi.university;

import com.lithium.mineraloi.university.browser.DriverManager;
import com.lithium.mineraloil.selenium.elements.BaseElement;
import com.lithium.mineraloil.selenium.elements.Driver;
import com.lithium.mineraloil.selenium.elements.TextElement;
import org.openqa.selenium.By;

public class TwitterUIView {
    private Driver driver;

    public TwitterUIView() {
        driver = DriverManager.INSTANCE.getDriver();
    }

    /* Tabs */
    public BaseElement getHomeTLTab() {
        return driver.createBaseElement(By.xpath("//div[@class='tabContainer']/button[1]"));
    }

    public BaseElement getUserTLTab() {
        return driver.createBaseElement(By.xpath("//div[@class='tabContainer']/button[2]"));
    }

    public BaseElement getPostTweetTab() {
        return driver.createBaseElement(By.xpath("//div[@class='tabContainer']/button[3]"));
    }


    /* Filter Items */
    public BaseElement getFilterButton() {
        return driver.createBaseElement(By.xpath("//button[@class='filterButton active']"));
    }

    public TextElement getFilterBox() {
        return driver.createTextElement(By.xpath("//input[@class='textInput']"));
    }


    /* Home Timeline Items */
    public BaseElement getHomeTimelineDiv() {
        return driver.createBaseElement(By.xpath("//div[@class='dataHome']"));
    }

    public BaseElement getHomeTimelineButton() {
        return driver.createBaseElement(By.xpath("//button[@class='homeTimelineButton']"));
    }


    /* User Timeline Items*/
    public BaseElement getUserTimelineDiv() {
        return driver.createBaseElement(By.xpath("//div[@class='dataUser']"));
    }

    public BaseElement getUserTimelineButton() {
        return driver.createBaseElement(By.xpath("//button[@class='userTimelineButton']"));
    }

    /* Post Tweet Items */
    public BaseElement getPostTweetWrapper() {
        return driver.createBaseElement(By.xpath("//span[@class='postButtonWrapper']"));
    }

    public TextElement getPostTweetTextArea() {
        return driver.createTextElement(By.xpath("//textarea[@class='postTextArea']"));
    }

    public BaseElement getPostTweetButton() {
        return driver.createBaseElement(By.xpath("//button[@class='postButton']"));
    }
}
