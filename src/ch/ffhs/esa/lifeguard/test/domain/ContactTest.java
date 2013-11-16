package ch.ffhs.esa.lifeguard.test.domain;

import ch.ffhs.esa.lifeguard.domain.Contact;
import junit.framework.TestCase;

/**
 * Test case for Contact
 * 
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class ContactTest extends TestCase {

	private Contact contact;
	
	protected void setUp() throws Exception {
		super.setUp();
		contact = new Contact();
		contact.setName("Jane Doe");
		contact.setPhone("0123456789");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		contact = null;
	}

	public final void testToString() {
		assertEquals("Jane Doe", contact.toString());
		contact.setName("John Doe");
		assertEquals("John Doe", contact.toString());
	}

	public final void testGetSetId() {
		assertEquals(0, contact.getId());
		contact.setId(1L);
		assertEquals(1L, contact.getId());
		contact.setId(123L);
		assertEquals(123L, contact.getId());
	}

	public final void testGetSetName() {
		assertEquals("Jane Doe", contact.getName());
		contact.setName("");
		assertEquals("Jane Doe", contact.getName());
		contact.setName("John Doe");
		assertEquals("John Doe", contact.getName());
	}

	public final void testGetSetPhone() {
		assertEquals("0123456789", contact.getPhone());
		contact.setPhone("1234567890");
		assertEquals("1234567890", contact.getPhone());
		contact.setPhone("");
		assertEquals("1234567890", contact.getPhone());
		contact.setPhone("1");
		assertEquals("1234567890", contact.getPhone());
		contact.setPhone("11");
		assertEquals("1234567890", contact.getPhone());
		contact.setPhone("117");
		assertEquals("117", contact.getPhone());
	}

	public final void testGetPosition() {
		assertEquals(0, contact.getPosition());
		contact.setPosition(1);
		assertEquals(1, contact.getPosition());
		contact.setPosition(-1);
		assertEquals(0, contact.getPosition());
	}

}
