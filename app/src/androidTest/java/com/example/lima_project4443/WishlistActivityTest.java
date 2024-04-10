package com.example.lima_project4443;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class WishlistActivityTest {

    // Specify the activity to be launched before each test
    @Rule
    public ActivityTestRule<WishlistActivity> mActivityRule = new ActivityTestRule<>(WishlistActivity.class);

    @Test
    public void testActivityLaunch() {
        // Check if the activity is launched successfully
        //onView(withId(R.id.wishlist_activity_layout)).check(matches(isDisplayed()));
    }

    // Add more test methods as needed...
}
