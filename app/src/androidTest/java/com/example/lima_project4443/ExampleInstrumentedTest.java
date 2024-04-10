package com.example.lima_project4443;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    // Specify the activity to be launched before each test
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.lima_project4443", appContext.getPackageName());
    }
    @Test
    public void performTestB(){
        Espresso.onView(ViewMatchers.withId(R.id.typeToggle)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.editTextAge)).perform(ViewActions.typeText("25"));
        Espresso.onView(ViewMatchers.withId(R.id.radioButtonMale)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.editTextHours)).perform(ViewActions.typeText("4"));
        Espresso.onView(ViewMatchers.withId(R.id.editPartID)).perform(ViewActions.typeText("100"));
        Espresso.onView(ViewMatchers.withId(R.id.buttonSubmit)).perform(ViewActions.click());
        assertEquals("B", MainActivity.selectedVersion);

        //loginactivity
        Espresso.onView(ViewMatchers.withId(R.id.editTextUsername)).perform(ViewActions.typeText("admin"));
        Espresso.onView(ViewMatchers.withId(R.id.editTextPassword)).perform(ViewActions.typeText("password"));
        Espresso.onView(ViewMatchers.withId(R.id.buttonLogin)).perform(ViewActions.click());

        //ProductDisplayActivity
        Espresso.onView(ViewMatchers.withId(R.id.search_view)).perform(ViewActions.typeText("Under"));




    }
}

