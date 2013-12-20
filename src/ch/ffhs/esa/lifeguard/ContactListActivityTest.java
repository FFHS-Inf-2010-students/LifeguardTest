package ch.ffhs.esa.lifeguard;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.ListView;
import ch.ffhs.esa.lifeguard.domain.Contact;
import ch.ffhs.esa.lifeguard.domain.ContactInterface;
import ch.ffhs.esa.lifeguard.domain.ContactsListAdapter;

/**
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class ContactListActivityTest
	extends ActivityUnitTestCase<ContactListActivity> {

    private ContactListActivity activity;
    
	public ContactListActivityTest() {
		super(ContactListActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(
		    getInstrumentation().getTargetContext(), ContactListActivity.class
        );
		startActivity(intent, null, null);
		activity = getActivity();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testItemClick() {
	    getInstrumentation().callActivityOnStart(activity);
	    getInstrumentation().callActivityOnResume(activity);
	    
	    // Check if list exists
	    ListView list = activity.getListView();
	    assertNotNull("Intent was null", list);
	    
	    // Load test data
	    ContactsListAdapter adapter = new ContactsListAdapter(
            getInstrumentation().getContext(),
            createData()
        );
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        
        assertEquals(2, adapter.getCount());
	    
	    // Check if list has at least one item to click
	    View firstItem = list.getAdapter().getView(0, null, null);
	    assertNotNull(firstItem);
	    
	    // Perform a click on the first item
	    list.performItemClick(
            firstItem,
            0,
            list.getAdapter().getItemId(0)
        );
	    
	    // Check if the contact details activity got started
	    Intent intent = getStartedActivityIntent();
	    assertNotNull(intent);
	    assertEquals(
            ContactDetailActivity.class.getName(),
            intent.getComponent().getClassName()
        );
	}
	
	private List<ContactInterface> createData() {
        List<ContactInterface> contacts = new ArrayList<ContactInterface>();
        
        ContactInterface contact = new Contact();
        contact.setId(1L);
        contact.setName("Jane Doe").setPhone("0123456789").setPosition(1);
        contacts.add(contact);
        
        ContactInterface contact2 = new Contact();
        contact2.setId(2L);
        contact2.setName("John Doe").setPhone("0234567890").setPosition(2);
        contacts.add(contact2);
        
        return contacts;
    }
}
