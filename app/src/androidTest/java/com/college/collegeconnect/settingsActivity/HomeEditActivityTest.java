package com.college.collegeconnect.settingsActivity;

import android.content.Context;
import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import com.college.collegeconnect.R;
import com.college.collegeconnect.datamodels.SaveSharedPreference;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.assertEquals;


/**
 * Instrumented test for checking change of structure saved to shared preferences.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeEditActivityTest {

    private static final String UNIVERSITY = "Bharati Vidyapeeth's College of Engineering, New Delhi";

    private Context context;

    @Rule
    public ActivityScenarioRule<HomeEditActivity> testRule = new ActivityScenarioRule<>(HomeEditActivity.class);

    /***
     * Setup method for test app context initialization.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        testRule.getScenario().recreate();
    }

    /***
     * Instrumented test, which update user college, and check if change was
     * propagated to shared preferences
     */
    @Test
    public void checkSharedPreferencesUpdate() {

        onView(withId(R.id.textView5copy)).perform(clearText(), click());

        onView(withId(R.id.textView5copy)).perform(click());

        onView(withId(R.id.textView5copy)).perform(typeText(UNIVERSITY));

        onView(withId(R.id.submitDetailscopy)).check(matches(allOf(isEnabled(), isClickable()))).perform(
                new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return isEnabled(); // no constraints, they are checked above
                    }

                    @Override
                    public String getDescription() {
                        return "click plus button";
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        view.performClick();
                    }
                });

        assertEquals(SaveSharedPreference.getCollege(context), UNIVERSITY);

        SaveSharedPreference.getClearall(context);
    }


    @After
    public void tearDown() throws Exception {
        SaveSharedPreference.getClearall(context);
        context = null;
    }
}