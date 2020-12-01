package com.college.collegeconnect.ui.attendance;

import android.view.KeyEvent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.college.collegeconnect.R;
import com.college.collegeconnect.activities.Navigation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
public class AttendanceFragmentTest {

    private static final String SUBJECT_NAME = "AAA_TEST_SUBJECT";

    private static final String DIALOG_POSITIVE_BUTTON = "Done";

    @Rule
    public ActivityScenarioRule<Navigation> testRule = new ActivityScenarioRule<Navigation>(Navigation.class);

    /**
     * Instrumented text which first create new subject and then add timetable unit for it.
     */
    @Test
    public void addTimeTableForNewSubject() {

        testRule.getScenario().recreate();

        onView(withId(R.id.nav_attendance)).perform(click());

        onView(withId(R.id.subjectName)).perform(typeText(SUBJECT_NAME));

        onView(withId(R.id.subjectName)).perform(pressKey(KeyEvent.KEYCODE_ENTER));

        onView(withId(R.id.nav_home)).perform(click());

        onView(withId(R.id.recyclerviewHome)).perform(click());

        onView(withId(R.id.time_table_fab)).perform(click());

        onView(withId(R.id.spinner2)).check(matches(withSpinnerText(containsString(SUBJECT_NAME))));

        onView(withText(DIALOG_POSITIVE_BUTTON)).perform(click());
    }

}
