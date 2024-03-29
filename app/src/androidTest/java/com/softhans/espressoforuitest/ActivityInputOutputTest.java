package com.softhans.espressoforuitest;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *
 *
 */



@RunWith(AndroidJUnit4.class)


public class ActivityInputOutputTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.softhans.espressoforuitest", appContext.getPackageName());
    }

    //test whether the SecondActivity View elements appear when clicking the Button
    @Test
    public void activityLaunch(){
        onView(withId(R.id.button1)).perform(click());

        //method to find the corresponding textView (which is in SecondActivity),
        // and a ViewAction expression to perform a check to see if the View is displayed:
        onView(withId(R.id.text_message2)).check(matches(isDisplayed()));

        //similar statements to test whether clicking the button_second Button in SecondActivity
        // switches to MainActivity:
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.text_message)).check(matches(isDisplayed()));
    }

// method to test text input and output:
@Test
public void textInputOutput() {
    onView(withId(R.id.edit_txt)).perform(typeText("This is a test."));
    onView(withId(R.id.button1)).perform(click());

    //test that the message was correctly sent
    onView(withId(R.id.text_message2)).check(matches(withText("This is a test.")));
}

}
