package com.lithium.mineraloil.university;

import com.lithium.mineraloi.university.ExampleController;
import com.lithium.mineraloi.university.browser.BaseUITest;
import com.lithium.mineraloil.selenium.elements.BaseElement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class UITest extends BaseUITest {

    @DisplayName("Button Clicks: No Error Messages")
    @Test
    void testTwitterConnectionPresent() {
        ExampleController timeline = new ExampleController();
        timeline.clickHomeTimelineButton();
        timeline.clickUserTimelineButton();
        Assertions.assertThat(timeline.checkErrorMessage()).isEqualTo(false);
    }

    @DisplayName("Filter Test: Jibberish should return error message")
    @Test
    void filterJibberishTest() {
        String text = "abcdefghikslasdkalsjdal;ksjawasdaw awdasdwq";
        ExampleController homeTL = new ExampleController();
        homeTL.filterSearch(text);
        BaseElement homeTLError = homeTL.getHomeTLError();
        Assertions.assertThat(homeTLError.isDisplayed()).isEqualTo(true);
        Assertions.assertThat(homeTL.getHomeTLString()).isEqualTo("No tweets match your search query.");

    }

    @DisplayName("Filter Test: Use 1st result of home timeline")
    @Test
    void filterProperText() {
        try{
            ExampleController homeTL = new ExampleController();

            /* Built in sleep to ensure page loads */
            TimeUnit.SECONDS.sleep(1);

            String data = homeTL.getHomeTLString();
            String[] dataArray = data.split("\n");
            homeTL.filterSearch(dataArray[3]);
            String resultingText = homeTL.getHomeTLElement().createBaseElement(By.xpath("//a[@class='messageText']")).getInnerText();

            Assertions.assertThat(homeTL.checkErrorMessage()).isEqualTo(false);
            Assertions.assertThat(resultingText).isEqualTo(dataArray[3]);
        } catch(Exception e) {
            e.printStackTrace();
            Assertions.fail("Exception caught");
        }
    }
}
