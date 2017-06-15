package com.example.kyoda.myapplication;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tools.fastlane.screengrab.Screengrab;
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class OnClickMainActivityTest {


    @Rule
    public ActivityTestRule<OnClickMainActivity> mActivityTestRule = new ActivityTestRule<>(OnClickMainActivity.class);

    @Test
    public void onClickMainActivityTest() {

        Screengrab.setDefaultScreenshotStrategy(new UiAutomatorScreenshotStrategy());

        try {

            sleep(3000);

            Screengrab.screenshot("screenshot0");


            ViewInteraction appCompatButton = onView(
                    allOf(withId(R.id.button1), withText("1"), isDisplayed()));
            appCompatButton.perform(click());

            sleep(500);
            Screengrab.screenshot("screenshot1");

            appCompatButton = onView(
                    allOf(withId(R.id.buttonAdd), withText("+"), isDisplayed()));
            appCompatButton.perform(click());

            appCompatButton = onView(
                    allOf(withId(R.id.button2), withText("2"), isDisplayed()));
            appCompatButton.perform(click());
            sleep(500);
            Screengrab.screenshot("screenshot2");


            appCompatButton = onView(
                    allOf(withId(R.id.buttonEqual), withText("="), isDisplayed()));
            appCompatButton.perform(click());
            sleep(500);
            Screengrab.screenshot("screenshot3");







        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
