package ch.ffhs.esa.lifeguard.service.state;

import ch.ffhs.esa.lifeguard.alarm.state.*;
import ch.ffhs.esa.lifeguard.domain.Contact;
import junit.framework.TestCase;

/**
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class ConfirmedStateTest extends TestCase {

	private AlarmState state;
	
	protected void setUp() throws Exception {
		super.setUp();
		Contact contact = new Contact ("Test", "0793369874");
		state = new ConfirmedState(contact);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		state = null;
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.service.state.ConfirmedState#getId()}.
	 */
	public final void testGetId() {
		assertEquals(AlarmStateId.CONFIRMED, state.getId());
	}
}
