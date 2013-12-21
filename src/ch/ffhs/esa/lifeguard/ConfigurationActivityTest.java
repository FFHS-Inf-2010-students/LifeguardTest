package ch.ffhs.esa.lifeguard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.test.ActivityUnitTestCase;
import android.test.RenamingDelegatingContext;
import android.test.mock.MockContext;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class ConfigurationActivityTest
	extends ActivityUnitTestCase<ConfigurationActivity> {

    private ConfigurationActivity activity;
    
    private Context context;
    
    private Lifeguard app;
    
    /*//////////////////////////////////////////////////////////////////////////
     * CONFIGURATION 
     */
    
	public ConfigurationActivityTest() {
		super(ConfigurationActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		context = new RenamingMockContext(
            getInstrumentation().getTargetContext()
        );
		
		Intent intent = new Intent(
            context,
            ConfigurationActivity.class
        );
        app = new LifeguardTest();
        setApplication(app);
        startActivity(intent, null, null);
        activity = getActivity();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		//getPrefs().edit().clear().commit();
	}
	
	
	/*//////////////////////////////////////////////////////////////////////////
     * TESTS 
     */

	/**
	 * Tests whether all view elements are present
	 */
	public final void testLayout() {
	    int[] ids = {
            ch.ffhs.esa.lifeguard.R.id.configurationUserName,
            ch.ffhs.esa.lifeguard.R.id.configurationDelay,
            ch.ffhs.esa.lifeguard.R.id.configurationRepeatDelay,
            ch.ffhs.esa.lifeguard.R.id.saveConfiguration,
            ch.ffhs.esa.lifeguard.R.id.cancelConfiguration
	    };
	    
	    for (int id : ids) {
            assertNotNull(activity.findViewById(id));
        }
	}
	
	/**
	 * Tests whether data is correctly entered
	 */
	public final void testForm() {
	    EditText userNameField = getUserNameField();
	    assertNotNull(userNameField);
	    userNameField.setText("Jane Doe");
	    assertEquals("Jane Doe", userNameField.getText().toString());
	    
	    EditText delayField = getDelayField();
	    assertNotNull(delayField);
	    delayField.setText("1");
	    assertEquals("1", delayField.getText().toString());
	    delayField.setText("5000");
	    assertEquals("5000", delayField.getText().toString());
	    delayField.setText("xxx");
	    assertEquals("xxx", delayField.getText().toString());
	    
	    EditText repeatDelayField = getRepeatDelayField();
        assertNotNull(repeatDelayField);
        repeatDelayField.setText("1");
        assertEquals("1", repeatDelayField.getText().toString());
        repeatDelayField.setText("5000");
        assertEquals("5000", repeatDelayField.getText().toString());
        repeatDelayField.setText("xxx");
        assertEquals("xxx", repeatDelayField.getText().toString());
	}
	
	/**
	 * Tests whether clicking the cancel button successfully finishes the activity
	 */
	public final void testCancel() {
	    Button button = getCancelButton();
	    assertNotNull(button);
	    
	    button.performClick();
	    
	    assertTrue(isFinishCalled());
	}
	
	/**
	 * Tests whether clicking the save button successfully saves the preferences
	 */
	public final void testSave() {
        getUserNameField().setText("Jane Doex");
        getDelayField().setText("1234");
        getRepeatDelayField().setText("5678");
        
        Button button = getSaveButton();
        assertNotNull(button);
        
        button.performClick();
        assertTrue(isFinishCalled());
        
        SharedPreferences prefs = getPrefs();
        assertNotNull(prefs);
        
        assertEquals("Jane Doex", prefs.getString("userName", ""));
        assertEquals("1234", prefs.getString("alarmDelay", ""));
        assertEquals("5678", prefs.getString("alarmRepeatDelay", ""));
	}
	
	
	/*//////////////////////////////////////////////////////////////////////////
	 * PRIVATE OPERATIONS
	 */
	
	private EditText getUserNameField() {
	    return (EditText)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.configurationUserName);
	}
	
	private EditText getDelayField() {
	    return (EditText)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.configurationDelay);
	}
	
	private EditText getRepeatDelayField() {
	    return (EditText)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.configurationRepeatDelay);
	}
	
	private Button getSaveButton() {
	    return (Button)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.saveConfiguration);
	}
	
	private Button getCancelButton() {
	    return (Button)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.cancelConfiguration);
	}
	
	private SharedPreferences getPrefs() {
	    return context.getSharedPreferences(app.getSharedPreferencesIdentifier(), 0);
	}
	
	private static class RenamingMockContext extends RenamingDelegatingContext {
	    private static final String PREFIX = "test.";
	    
	    public RenamingMockContext(Context context) {
	        super(new DelegatedMockContext(context), PREFIX);
	    }
	    
	    private static class DelegatedMockContext extends MockContext {
	        private Context delegatedContext;
	        
	        public DelegatedMockContext(Context context) {
	            delegatedContext = context;
	        }
	        
	        @Override
	        public String getPackageName() {
	            return delegatedContext.getPackageName();
	        }
	        
	        @Override
	        public SharedPreferences getSharedPreferences(String name, int mode) {
	            return delegatedContext.getSharedPreferences(name, mode);
	        }
	    }
	}
}