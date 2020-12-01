package com.college.collegeconnect.ui.tools;

import android.content.Context;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.college.collegeconnect.R;
import com.college.collegeconnect.activities.Navigation;
import com.college.collegeconnect.datamodels.SaveSharedPreference;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ToolsFragmentTest {

    Context context;

    @Rule
    public ActivityScenarioRule<Navigation> testRule = new ActivityScenarioRule<>(Navigation.class);

    /**
     * Setup method for app context initialization.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    /**
     * Instrumented test checks room locator visibility, if actual user college is BVCOE.
     */
    @Test
    public void checkRoomLocatorVisible() {
        testRule.getScenario().recreate();

        SaveSharedPreference.setCollege(context, "Bharati Vidyapeeth's College of Engineering, New Delhi");

        onView(withId(R.id.nav_tools)).perform(click());

        onView(withId(R.id.tools_header)).check(matches(isDisplayed()));

        onView(withId(R.id.roomLocator)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        SaveSharedPreference.getClearall(context);
    }

    /**
     * Test checks room locator visibility, if actual user college is not BVCOE.
     */
    @Test
    public void checkRoomLocatorNotVisible() {
        testRule.getScenario().recreate();

        SaveSharedPreference.setCollege(context, "Technical University of Kosice");

        onView(withId(R.id.nav_tools)).perform(click());

        onView(withId(R.id.tools_header)).check(matches(isDisplayed()));

        onView(withId(R.id.roomLocator)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        SaveSharedPreference.getClearall(context);
    }

    /**
     * Tear down method for setting vars to init state
     */
    @After
    public void tearDown() throws Exception {
        SaveSharedPreference.getClearall(context);
        context = null;
    }
}