/**
 * 
 */
package ch.ffhs.esa.lifeguard;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

/**
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class MainActivityFunctionalTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity activity;
    
    public MainActivityFunctionalTest() {
        super(MainActivity.class);
    }
    
    /* (non-Javadoc)
     * @see android.test.ActivityInstrumentationTestCase2#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        activity = getActivity();
    }

    /* (non-Javadoc)
     * @see android.test.ActivityInstrumentationTestCase2#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public final void testStartOfConfigurationActivity() {
        ActivityMonitor monitor = getInstrumentation().addMonitor(
            ConfigurationActivity.class.getName(), null, false
        );
        
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
        getInstrumentation().invokeMenuActionSync(activity, ch.ffhs.esa.lifeguard.R.id.action_configuration, 0);
        
        ConfigurationActivity configurationActivity = (ConfigurationActivity)monitor.waitForActivityWithTimeout(2000);
        assertNotNull(configurationActivity);
        assertTrue(getInstrumentation().checkMonitorHit(monitor, 1));
        configurationActivity.finish();
    }
    
    public final void testStartOfContactListActivity() {
        ActivityMonitor monitor = getInstrumentation().addMonitor(
            ContactListActivity.class.getName(), null, false
        );
        
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
        getInstrumentation().invokeMenuActionSync(activity, ch.ffhs.esa.lifeguard.R.id.action_contact_list, 0);
        
        ContactListActivity contactListActivity = (ContactListActivity)monitor.waitForActivityWithTimeout(2000);
        assertNotNull(contactListActivity);
        assertTrue(getInstrumentation().checkMonitorHit(monitor, 1));
        contactListActivity.finish();
    }

}
