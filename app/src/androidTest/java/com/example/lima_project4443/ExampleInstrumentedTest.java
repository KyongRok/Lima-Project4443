package com.example.lima_project4443;

import android.content.Context;
import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

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
    public void performTestB() {
        Espresso.onView(ViewMatchers.withId(R.id.typeToggle)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.editTextAge)).perform(ViewActions.typeText("25"));
        Espresso.onView(ViewMatchers.withId(R.id.radioButtonMale)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.editTextHours)).perform(ViewActions.typeText("4"));
        Espresso.onView(ViewMatchers.withId(R.id.editPartID)).perform(ViewActions.typeText("100"));
        Espresso.onView(ViewMatchers.withId(R.id.buttonSubmit)).perform(click());
        assertEquals("B", MainActivity.selectedVersion);

        //loginactivity
        Espresso.onView(ViewMatchers.withId(R.id.editTextUsername)).perform(ViewActions.typeText("admin"));
        Espresso.onView(ViewMatchers.withId(R.id.editTextPassword)).perform(ViewActions.typeText("password"));
        Espresso.onView(ViewMatchers.withId(R.id.buttonLogin)).perform(click());

        //ProductDisplayActivity
        Espresso.onView(ViewMatchers.withId(R.id.search_view)).perform(ViewActions.typeText("Under"));
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //ProductDetail
        Espresso.onView(ViewMatchers.withId(R.id.wishlistButton)).perform(click());

    }

    private static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return hasDescendant(withId(id));
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified ID.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View childView = view.findViewById(id);
                if (childView != null) {
                    childView.performClick();
                }
            }
        };
    }
}

