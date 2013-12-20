package ch.ffhs.esa.lifeguard;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.test.ActivityUnitTestCase;
import android.test.RenamingDelegatingContext;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import ch.ffhs.esa.lifeguard.domain.Contact;
import ch.ffhs.esa.lifeguard.domain.ContactInterface;
import ch.ffhs.esa.lifeguard.domain.Contacts;
import ch.ffhs.esa.lifeguard.domain.ContactsInterface;
import ch.ffhs.esa.lifeguard.persistence.DatabaseHelper;

/**
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class ContactDetailActivityTest
    extends ActivityUnitTestCase<ContactDetailActivity> {

    /*//////////////////////////////////////////////////////////////////////////
     * PROPERTIES
     */
    
    private SQLiteOpenHelper db;
    
    private ContactDetailActivity activity;
    
    
    /*//////////////////////////////////////////////////////////////////////////
     * CONFIGURATION
     */
    
    public ContactDetailActivityTest() {
        super(ContactDetailActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        
        RenamingDelegatingContext context = new RenamingDelegatingContext(
            getInstrumentation().getTargetContext(), "test_"
        );
        context.makeExistingFilesAndDbsAccessible();
        
        db = new DatabaseHelper(context);
        Lifeguard.setDatabaseHelper(db);
        
        Intent intent = new Intent(
            context,
            ContactDetailActivity.class
        );
        
        this.cleanDatabase();
        
        // Mock data
        intent.putExtra("contact", createMockContact());
        intent.putExtra("count", 2);
        
        startActivity(intent, null, null);
        activity = getActivity();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        this.cleanDatabase();
    }
    
    
    /*//////////////////////////////////////////////////////////////////////////
     * TESTS
     */

    /**
     * Tests whether all view elements are present
     */
    public final void testLayout() {
        // Test if view elements exist
        int[] ids = {
            ch.ffhs.esa.lifeguard.R.id.contactDetailNameLabel,
            ch.ffhs.esa.lifeguard.R.id.contactDetailName,
            ch.ffhs.esa.lifeguard.R.id.contactDetailPhoneLabel,
            ch.ffhs.esa.lifeguard.R.id.contactDetailPhone,
            ch.ffhs.esa.lifeguard.R.id.contactDetailPositionLabel,
            ch.ffhs.esa.lifeguard.R.id.contactDetailPosition,
            ch.ffhs.esa.lifeguard.R.id.contactDetailSaveButton,
            ch.ffhs.esa.lifeguard.R.id.contactDetailCancelButton
        };
        
        for (int id : ids) {
            assertNotNull(activity.findViewById(id));
        }
    }
    
    /**
     * Tests whether the form was correctly populated with the contact details
     */
    public final void testContact() {
        EditText nameField = (EditText)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.contactDetailName);
        assertNotNull(nameField);
        assertEquals("Jane Doe", nameField.getText().toString());
        
        EditText phoneField = (EditText)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.contactDetailPhone);
        assertNotNull(phoneField);
        assertEquals("0123456789", phoneField.getText().toString());
        
        Spinner positionField = (Spinner)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.contactDetailPosition);
        assertNotNull(positionField);
        assertEquals("1", positionField.getSelectedItem());
        assertEquals(2, positionField.getCount());
    }
    
    /**
     * Tests whether the form validates with correct data only
     */
    public final void testValidate() {
        assertTrue(activity.validate());
        
        EditText nameField = (EditText)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.contactDetailName);
        assertNotNull(nameField);
        String value = nameField.getText().toString();
        nameField.setText("");
        assertFalse(activity.validate());
        nameField.setText(value);
        assertTrue(activity.validate());
        
        EditText phoneField = (EditText)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.contactDetailPhone);
        assertNotNull(phoneField);
        value = phoneField.getText().toString();
        phoneField.setText("");
        assertFalse(activity.validate());
        phoneField.setText(value);
        assertTrue(activity.validate());
    }
    
    /**
     * Tests whether clicking the cancel button finishes the activity
     */
    public final void testCancelButton() {
        Button button = (Button)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.contactDetailCancelButton);
        assertNotNull(button);
        
        button.performClick();
        
        assertTrue(isFinishCalled());
    }
    
    /**
     * Tests whether attempting to save incorrect data finishes the activity
     */
    public final void testFailedSaveAttempt() {
        EditText nameField = (EditText)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.contactDetailName);
        assertNotNull(nameField);
        nameField.setText("");
        assertFalse(activity.validate());
        
        Button button = (Button)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.contactDetailSaveButton);
        assertNotNull(button);
        
        button.performClick();
        assertFalse(isFinishCalled());
    }
    
    public final void testSaveContact() {
        SQLiteDatabase database = Lifeguard.getDatabaseHelper().getWritableDatabase();
        
        assertTrue(database.getPath().contains("test_lifeguard.db"));
        
        ContactsInterface contacts = new Contacts(this.db);
        assertEquals(2, contacts.getAll().size());
        
        EditText nameField = (EditText)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.contactDetailName);
        assertNotNull(nameField);
        nameField.setText("Jane Doex");
        
        EditText phoneField = (EditText)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.contactDetailPhone);
        assertNotNull(phoneField);
        phoneField.setText("1234567890");
        
        Button button = (Button)activity.findViewById(ch.ffhs.esa.lifeguard.R.id.contactDetailSaveButton);
        assertNotNull(button);
        
        button.performClick();
        
        assertTrue(activity.validate());
        assertTrue(isFinishCalled());
        
        assertEquals(2, contacts.getAll().size());
        
        ContactInterface jane = (Contact)contacts.findById(1L);
        assertEquals(1L, jane.getId());
        assertEquals(1, jane.getPosition());
        assertEquals("Jane Doex", jane.getName());
        assertEquals("1234567890", jane.getPhone());
        
        ContactInterface john = (Contact)contacts.findById(2L);
        assertEquals(2L, john.getId());
        assertEquals(2, john.getPosition());
        assertEquals("John Doe", john.getName());
        assertEquals("0234567890", john.getPhone());
    }

    
    /*//////////////////////////////////////////////////////////////////////////
     * PRIVATE OPERATIONS
     */
    
    private ContactInterface createMockContact() {
        ContactInterface jane = new Contact();
        
        jane.setId(1L);
        jane.setName("Jane Doe").setPhone("0123456789").setPosition(1);
        
        return jane;
    }
    
    private void cleanDatabase() {
        this.db.getWritableDatabase().execSQL("DELETE FROM contacts");
        this.db.getWritableDatabase().execSQL("DELETE FROM sqlite_sequence WHERE NAME='contacts'");
        this.db.getWritableDatabase().execSQL("INSERT INTO contacts (name, phone, position) VALUES ('Jane Doe', '0123456789', 1);");
        this.db.getWritableDatabase().execSQL("INSERT INTO contacts (name, phone, position) VALUES ('John Doe', '0234567890', 2);");
        this.db.close();
    }
}