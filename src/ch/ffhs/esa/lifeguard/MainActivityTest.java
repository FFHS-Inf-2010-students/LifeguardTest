package ch.ffhs.esa.lifeguard;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class MainActivityTest
	extends ActivityUnitTestCase<MainActivity> {

    private MainActivity activity;
    
	public MainActivityTest() {
		super(MainActivity.class);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityUnitTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(
	        getInstrumentation().getTargetContext(), MainActivity.class
        );
		startActivity(intent, null, null);
		activity = getActivity();
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityUnitTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public final void testLayout() {
	    // check if delay label exists
	    int delayLabelId = ch.ffhs.esa.lifeguard.R.id.textViewDelayLabel;
	    assertNotNull(activity.findViewById(delayLabelId));
	    TextView delayLabel = (TextView)activity.findViewById(delayLabelId);
	    assertEquals(
            activity.getString(ch.ffhs.esa.lifeguard.R.string.main_label_delay),
            delayLabel.getText()
        );
	    
	    // check if delay view exists
	    int delayViewId = ch.ffhs.esa.lifeguard.R.id.textViewDelay;
	    assertNotNull(activity.findViewById(delayViewId));
	    TextView delayView = (TextView)activity.findViewById(delayViewId);
	    assertEquals(
	        activity.getString(ch.ffhs.esa.lifeguard.R.string.main_value_delay),
	        delayView.getText()
        );
	    
	    // check if progress bar exists
	    int progressBarId = ch.ffhs.esa.lifeguard.R.id.progressBarDelay;
	    assertNotNull(activity.findViewById(progressBarId));
	    
	    // check if alarm switch label exists
	    int alarmSwitchLabelId = ch.ffhs.esa.lifeguard.R.id.textViewAlarmSwitch;
	    assertNotNull(activity.findViewById(alarmSwitchLabelId));
	    TextView alarmSwitchLabel = (TextView)activity.findViewById(alarmSwitchLabelId);
        assertEquals(
            activity.getString(ch.ffhs.esa.lifeguard.R.string.main_label_alarm_switch),
            alarmSwitchLabel.getText()
        );
	    
	    // check if alarm switch exists
	    int alarmSwitchId = ch.ffhs.esa.lifeguard.R.id.toggleButtonAlarmSwitch;
	    assertNotNull(activity.findViewById(alarmSwitchId));
	    ToggleButton alarmSwitch = (ToggleButton)activity.findViewById(alarmSwitchId);
	    assertTrue(alarmSwitch.isEnabled());
	    assertTrue(!alarmSwitch.isChecked());
	    
	    // check if SOS button exists
	    int sosButtonId = ch.ffhs.esa.lifeguard.R.id.SOSButton;
	    assertNotNull(activity.findViewById(sosButtonId));
	    Button sosButton = (Button)activity.findViewById(sosButtonId);
	    assertEquals(
            activity.getString(ch.ffhs.esa.lifeguard.R.string.main_label_sos),
            sosButton.getText()
        );
	    assertTrue(sosButton.isEnabled());
	    
	    // check if SOS text exists
	    int sosTextId = ch.ffhs.esa.lifeguard.R.id.textViewSOSButton;
	    assertNotNull(activity.findViewById(sosTextId));
	    //TextView sosText = (TextView)activity.findViewById(sosTextId);
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.MainActivity#openConfiguration()}.
	 */
	public final void testOpenConfiguration() {
		activity.openConfiguration();
		Intent triggeredIntent = getStartedActivityIntent();
		assertNotNull("Intent was null", triggeredIntent);
		assertEquals(ConfigurationActivity.class.getName(), triggeredIntent.getComponent().getClassName());
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.MainActivity#viewContacts()}.
	 */
	public final void testViewContacts() {
		activity.viewContacts();
		Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", triggeredIntent);
        assertEquals(ContactListActivity.class.getName(), triggeredIntent.getComponent().getClassName());
	}
}
