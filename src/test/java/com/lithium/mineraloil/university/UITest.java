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
    private static final int WAIT_TIME = 20;

    @DisplayName("Home Timeline Button Click: No Error Messages")
    @Test
    void testTwitterConnectionPresentHomeTimeline() {
        TwitterUIController timeline = new TwitterUIController();
        timeline.clickHomeTimelineTab();
        timeline.clickHomeTimelineButton();

        Awaitility.await().atMost(WAIT_TIME, TimeUnit.SECONDS).until(() -> timeline.getHomeTLTweet().isDisplayed());

        assertThat(timeline.checkErrorMessage(TwitterUIController.UI_Select.HOME)).isEqualTo(false);
    }

    @DisplayName("User Timeline Button Click: No Error Messages")
    @Test
    void testTwitterConnectionPresentUserTimeline() {
        TwitterUIController timeline = new TwitterUIController();
        timeline.clickUserTimelineTab();
        timeline.clickUserTimelineButton();

        Awaitility.await().atMost(WAIT_TIME, TimeUnit.SECONDS).until(() -> timeline.getUserTLTweet().isDisplayed());

        assertThat(timeline.checkErrorMessage(TwitterUIController.UI_Select.USER)).isEqualTo(false);
    }

    @DisplayName("Filter Test: Gibberish should return error message")
    @Test
    void filterJibberishTest() {
        String text = "abcdefghikslasdkalsjdal;ksjawasdaw awdasdwq";
        TwitterUIController timeline = new TwitterUIController();
        timeline.clickHomeTimelineTab();

        Awaitility.await().atMost(WAIT_TIME, TimeUnit.SECONDS).until(() -> timeline.getHomeTLTweet().isDisplayed());

        timeline.filterSearch(text);
        BaseElement timelineError = timeline.getHomeTLSearchError();
        assertThat(timelineError.isDisplayed()).isEqualTo(true);
        assertThat(timeline.getHomeTLString()).isEqualTo("No tweets match your search query.");

    }

    @DisplayName("Filter Test: Use 1st result of home timeline")
    @Test
    void filterProperText() {
        TwitterUIController timeline = new TwitterUIController();
        timeline.clickHomeTimelineTab();

        Awaitility.await().atMost(WAIT_TIME, TimeUnit.SECONDS).until(() -> timeline.getHomeTLTweet().isDisplayed());

        String data = timeline.getHomeTLString();
        String[] dataArray = data.split("\n");
        String message = dataArray[3];
        timeline.filterSearch(message);
        String resultingText = timeline.getHomeMessageText().getInnerText();

        assertThat(timeline.checkErrorMessage(TwitterUIController.UI_Select.HOME)).isEqualTo(false);
        assertThat(resultingText).isEqualTo(message);
    }

    @DisplayName("Post Test: Post Tweet Works")
    @Test
    void testPostTweet() {
        TwitterUIController timeline = new TwitterUIController();

        /* Store a string for post tweet usage */
        timeline.clickUserTimelineTab();
        timeline.clickUserTimelineButton();

        Awaitility.await().atMost(WAIT_TIME, TimeUnit.SECONDS).until(() -> timeline.getUserTLTweet().isDisplayed());

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
        Awaitility.await().atMost(WAIT_TIME, TimeUnit.SECONDS).until(() -> timeline.getPostTweetSuccessMessage().isDisplayed());

        /* Go Back to USer Timeline and verify */
        timeline.clickUserTimelineTab();
        timeline.clickUserTimelineButton();

        Awaitility.await().atMost(WAIT_TIME, TimeUnit.SECONDS).until(() -> timeline.getUserTLTweet().isDisplayed());

        data = timeline.getUserTLString();
        dataArray = data.split("\n");
        assertThat(dataArray[2]).isEqualTo(message);
    }
}
