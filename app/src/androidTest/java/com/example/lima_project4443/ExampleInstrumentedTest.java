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

import com.example.lima_project4443.Model.Cart_Model;

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
        Espresso.onView(ViewMatchers.withId(R.id.wishButtonB)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.btn_fav)).perform(click());
        //wishlist
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.addcartButton)));
        Espresso.onView(ViewMatchers.withId(R.id.btn_home_bottom)).perform(click());
        //productDisplayActivity
        Espresso.onView(ViewMatchers.withId(R.id.search_view)).perform(ViewActions.typeText("Reebok"));
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.addToCartSleek)));
        Espresso.onView(ViewMatchers.withId(R.id.btn_cart)).perform(click());
        //cart
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView2))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.removeButton)));
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView2))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.qtyPlus)));
        assertEquals(2,ShoppingCart.getInstance().cartList.get(0).getQuantity());
        Espresso.onView(ViewMatchers.withId(R.id.checkoutbtn)).perform(click());
        //checkout
        Espresso.onView(ViewMatchers.withId(R.id.editfirstName)).perform(ViewActions.typeText("Foo"));
        Espresso.onView(ViewMatchers.withId(R.id.editLastName)).perform(ViewActions.typeText("Voo"));
        Espresso.onView(ViewMatchers.withId(R.id.editContactPhone)).perform(ViewActions.typeText("123123123"));
        Espresso.onView(ViewMatchers.withId(R.id.editStreetAdd)).perform(ViewActions.typeText("123 York University"));
        Espresso.onView(ViewMatchers.withId(R.id.editCity)).perform(ViewActions.typeText("Toronto"));
        Espresso.onView(ViewMatchers.withId(R.id.editPostalCode)).perform(ViewActions.typeText("A1B2C3"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.buttonPay)).perform(click());
        //Espresso.onView(ViewMatchers.withId(R.id.buttonPay)).perform(click());
        //paymentinfo
        Espresso.onView(ViewMatchers.withId(R.id.editfirstName)).perform(ViewActions.typeText("Foo"));
        Espresso.onView(ViewMatchers.withId(R.id.editLastName)).perform(ViewActions.typeText("Voo"));
        Espresso.onView(ViewMatchers.withId(R.id.editCardNum)).perform(ViewActions.typeText("123456789"));
        //Espresso.onView(ViewMatchers.withId(R.id.expDate)).perform(ViewActions.typeText("1025"));
       // Espresso.onView(ViewMatchers.withId(R.id.editCVV)).perform(ViewActions.typeText("123"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.buttonPay)).perform(click());
        //ProfileActivity
        Espresso.onView(ViewMatchers.withId(R.id.fnameedit)).perform(ViewActions.typeText("Jane"));
        Espresso.onView(ViewMatchers.withId(R.id.lnameedit)).perform(ViewActions.typeText("Smith"));
        Espresso.onView(ViewMatchers.withId(R.id.dobedit)).perform(ViewActions.typeText("1995/05/05"));
        //Testing for screen navigations
        Espresso.onView(ViewMatchers.withId(R.id.btn_home_bottom)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.btn_fav)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.btn_cart)).perform(ViewActions.click());













    }


}

