package com.lithium.mineraloil.university;

import com.lithium.mineraloi.university.TwitterUIController;
import com.lithium.mineraloi.university.browser.BaseUITest;
import com.lithium.mineraloil.selenium.elements.BaseElement;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class UITest extends BaseUITest {

    @DisplayName("Button Clicks: No Error Messages")
    @Test
    void testTwitterConnectionPresent() {
        TwitterUIController timeline = new TwitterUIController();
        timeline.clickHomeTimelineButton();
        timeline.clickUserTimelineButton();
        Assertions.assertThat(timeline.checkErrorMessage()).isEqualTo(false);
    }

    @DisplayName("Filter Test: Jibberish should return error message")
    @Test
    void filterJibberishTest() {
        String text = "abcdefghikslasdkalsjdal;ksjawasdaw awdasdwq";
        TwitterUIController homeTL = new TwitterUIController();
        homeTL.filterSearch(text);
        BaseElement homeTLError = homeTL.getHomeTLError();
        Assertions.assertThat(homeTLError.isDisplayed()).isEqualTo(true);
        Assertions.assertThat(homeTL.getHomeTLString()).isEqualTo("No tweets match your search query.");

    }

    @DisplayName("Filter Test: Use 1st result of home timeline")
    @Test
    void filterProperText() {
        TwitterUIController homeTL = new TwitterUIController();
        homeTL.clickHomeTimelineButton();

        Awaitility.await().atMost(2, TimeUnit.SECONDS).until(() -> !homeTL.getHomeTLError().isDisplayed());

        String data = homeTL.getHomeTLString();
        String[] dataArray = data.split("\n");
        homeTL.filterSearch(dataArray[3]);
        String resultingText = homeTL.getHomeTLElement().createBaseElement(By.xpath("//a[@class='messageText']")).getInnerText();

        Assertions.assertThat(homeTL.checkErrorMessage()).isEqualTo(false);
        Assertions.assertThat(resultingText).isEqualTo(dataArray[3]);
    }
}
