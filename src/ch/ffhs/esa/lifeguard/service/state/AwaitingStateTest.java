package ch.ffhs.esa.lifeguard.service.state;

import ch.ffhs.esa.lifeguard.alarm.state.AlarmState;
import ch.ffhs.esa.lifeguard.alarm.state.AlarmStateId;
import ch.ffhs.esa.lifeguard.alarm.state.AwaitingState;
import ch.ffhs.esa.lifeguard.domain.Contact;
import junit.framework.TestCase;

/**
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class AwaitingStateTest extends TestCase {

	private AlarmState state;
	
	protected void setUp() throws Exception {
		super.setUp();
		state = new AwaitingState(new Contact ("Hans Wurscht", "0793332211"));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		state = null;
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.service.state.AwaitingState#getId()}.
	 */
	public final void testGetId() {
		assertEquals(AlarmStateId.AWAITING, state.getId());
	}

}
