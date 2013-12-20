package ch.ffhs.esa.lifeguard.domain;

import java.util.List;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import ch.ffhs.esa.lifeguard.persistence.DatabaseHelper;

/**
 * Test case for Contacts
 * 
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class ContactsTest extends AndroidTestCase {

	private Contacts contacts;
	
	private DatabaseHelper db;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		RenamingDelegatingContext context
			= new RenamingDelegatingContext(getContext(), "test_");
		
		this.db = new DatabaseHelper(context);
		this.cleanDatabase();
		this.contacts = new Contacts(this.db);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		this.cleanDatabase();
		this.contacts = null;
	}

	public final void testPersist() {
		ContactInterface c = new Contact();
		c.setName("Unit Test").setPhone("117");
		
		long id = this.contacts.persist(c);
		assertEquals(3, id);
		assertEquals(3, c.getPosition());
	}

	public final void testDelete() {
		ContactInterface c = new Contact();
		c.setName("Unit Test").setPhone("117").setPosition(3);
		
		long id = this.contacts.persist(c);
		assertEquals(3, id);
		assertEquals(3, c.getPosition());
		
		List<ContactInterface> all = this.contacts.getAll();
		assertEquals(3, all.size());
		assertEquals(1, all.get(0).getId());
		assertEquals(2, all.get(1).getId());
		assertEquals(3, all.get(2).getId());
		
		this.contacts.delete(c);
		
		all = this.contacts.getAll();
		assertEquals(2, all.size());
		assertEquals(1, all.get(0).getId());
		assertEquals(2, all.get(1).getId());
	}

	public final void testGetAll() {
		List<ContactInterface> c = this.contacts.getAll();
		assertEquals(2, c.size());
		int pos = 0;
		for (ContactInterface contact : c) {
			assertTrue(contact instanceof ContactInterface);
			assertTrue(contact.getId() > 0);
			assertTrue(contact.getPosition() > pos);
			pos = contact.getPosition();
		}
	}

	public final void testFindById() {
		ContactInterface contact = this.contacts.findById(1L);
		assertEquals(1L, contact.getId());
		assertEquals("Jane Doe", contact.getName());
		
		contact = this.contacts.findById(2L);
		assertEquals(2L, contact.getId());
		assertEquals("John Doe", contact.getName());
		
		contact = this.contacts.findById(3L);
		assertEquals(0, contact.getId());
		assertEquals("", contact.getName());
		assertEquals("", contact.getPhone());
		assertEquals(0, contact.getPosition());
	}
	
	
	private void cleanDatabase() {
	    this.db.getWritableDatabase().execSQL("DELETE FROM contacts");
        this.db.getWritableDatabase().execSQL("DELETE FROM sqlite_sequence WHERE NAME='contacts'");
        this.db.getWritableDatabase().execSQL("INSERT INTO contacts (name, phone, position) VALUES ('Jane Doe', '0123456789', 1);");
        this.db.getWritableDatabase().execSQL("INSERT INTO contacts (name, phone, position) VALUES ('John Doe', '0234567890', 2);");
        this.db.close();
	}

}
