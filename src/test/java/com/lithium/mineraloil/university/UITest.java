package com.lithium.mineraloil.university;

import com.lithium.mineraloi.university.TwitterUIController;
import com.lithium.mineraloi.university.browser.BaseUITest;
import com.lithium.mineraloil.selenium.elements.BaseElement;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class UITest extends BaseUITest {
    @DisplayName("Home Timeline Button Click: No Error Messages")
    @Test
    void testTwitterConnectionPresentHomeTimeline() {
        TwitterUIController timeline = new TwitterUIController();
        timeline.clickHomeTimelineTab();
        timeline.clickHomeTimelineButton();

        Awaitility.await().atMost(5, TimeUnit.SECONDS).until(() -> !timeline.getHomeTLError().isDisplayed());

        assertThat(timeline.checkErrorMessage(TwitterUIController.UI_Select.HOME)).isEqualTo(false);
    }

    @DisplayName("User Timeline Button Click: No Error Messages")
    @Test
    void testTwitterConnectionPresentUserTimeline() {
        TwitterUIController timeline = new TwitterUIController();
        timeline.clickUserTimelineTab();
        timeline.clickUserTimelineButton();

        Awaitility.await().atMost(5, TimeUnit.SECONDS).until(() -> !timeline.getUserTLError().isDisplayed());

        assertThat(timeline.checkErrorMessage(TwitterUIController.UI_Select.USER)).isEqualTo(false);
    }

    @DisplayName("Filter Test: Jibberish should return error message")
    @Test
    void filterJibberishTest() {
        String text = "abcdefghikslasdkalsjdal;ksjawasdaw awdasdwq";
        TwitterUIController timeline = new TwitterUIController();
        timeline.clickHomeTimelineTab();

        Awaitility.await().atMost(5, TimeUnit.SECONDS).until(() -> !timeline.getHomeTLError().isDisplayed());

        timeline.filterSearch(text);
        BaseElement timelineError = timeline.getHomeTLError();
        assertThat(timelineError.isDisplayed()).isEqualTo(true);
        assertThat(timeline.getHomeTLString()).isEqualTo("No tweets match your search query.");

    }

    @DisplayName("Filter Test: Use 1st result of home timeline")
    @Test
    void filterProperText() {
        TwitterUIController timeline = new TwitterUIController();
        timeline.clickHomeTimelineTab();

        Awaitility.await().atMost(5, TimeUnit.SECONDS).until(() -> !timeline.getHomeTLError().isDisplayed());

        String data = timeline.getHomeTLString();
        String[] dataArray = data.split("\n");
        timeline.filterSearch(dataArray[3]);
        String resultingText = timeline.getHomeMessageText().getInnerText();

        assertThat(timeline.checkErrorMessage(TwitterUIController.UI_Select.HOME)).isEqualTo(false);
        assertThat(resultingText).isEqualTo(dataArray[3]);
    }

    @DisplayName("Post Test: Post Tweet Works")
    @Test
    void testPostTweet() {
        TwitterUIController timeline = new TwitterUIController();

        /* Store a string for post tweet usage */
        timeline.clickUserTimelineTab();
        timeline.clickUserTimelineButton();

        Awaitility.await().atMost(10, TimeUnit.SECONDS).until(() -> !timeline.getHomeTLError().isDisplayed());

        String data = timeline.getUserTLString();
        String[] dataArray = data.split("\n");

        /* Generate a 'unique' string to post*/
        Random r = new Random();
        String append = String.valueOf((char) (r.nextInt(75) + '0'));
        String message = dataArray[2];
        if(message.length() < 280) {
            message += append;
        } else {
            message = append;
        }

        /* Go to Post Tweet Tab and Post */
        timeline.clickPostTweetTab();
        timeline.postTweet(message);

        /* Check that it was successful */
        Awaitility.await().atMost(10, TimeUnit.SECONDS).until(() -> timeline.getPostTweetSuccessMessage().isDisplayed());

        /* Go Back to USer Timeline and verify */
        timeline.clickUserTimelineTab();
        timeline.clickUserTimelineButton();

        Awaitility.await().atMost(10, TimeUnit.SECONDS).until(() -> !timeline.getUserTLString().equals("Pending . . ."));

        data = timeline.getUserTLString();
        dataArray = data.split("\n");
        assertThat(dataArray[2]).isEqualTo(message);
    }
}
