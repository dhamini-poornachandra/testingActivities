package sai.myfirstapp.com.sai;

import android.content.Intent;
import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;


import org.junit.Before;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertTrue;

@Config(constants = BuildConfig.class)

@RunWith(RobolectricGradleTestRunner.class)

public class First_Activity_Test {
    private First_Activity activity;
    private Button pressMeButton;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(First_Activity.class).create().get();
        pressMeButton = (Button) activity.findViewById(R.id.button);
    }

//    @Test
//    public void pressingTheButtonShouldStartTheListActivity() throws Exception {
//        pressMeButton.performClick();
//
//        ShadowActivity shadowActivity = shadowOf(activity);
////        Intent startedIntent = shadowActivity.getNextStartedActivity();
////        ShadowIntent shadowIntent = shadowOf(startedIntent);
////        assertThat(shadowIntent.getClassName(), equalTo(Second_Activity.class));
//
//    }

    @Test
    public void secondActivityStartedOnClick() {
        activity.findViewById(R.id.button).performClick();
// The intent we expect to be launched when a user clicks on the button
        Intent expectedIntent = new Intent(activity, Second_Activity.class);
// An Android "Activity" doesn't expose a way to find out about activities it launches
        // Robolectric's "ShadowActivity" keeps track of all launched activities and exposes this information
        // through the "getNextStartedActivity" method.
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
// Determine if two intents are the same for the purposes of intent resolution (filtering).
        // That is, if their action, data, type, class, and categories are the same. This does
        // not compare any extra data included in the intents
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

}
