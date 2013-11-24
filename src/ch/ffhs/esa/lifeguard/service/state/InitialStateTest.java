package ch.ffhs.esa.lifeguard.service.state;

import ch.ffhs.esa.lifeguard.alarm.state.*;

import junit.framework.TestCase;

/**
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class InitialStateTest extends TestCase {

	private AlarmState state;
	
	protected void setUp() throws Exception {
		super.setUp();
		state = new InitialState();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		state = null;
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.service.state.InitialState#getId()}.
	 */
	public final void testGetId() {
		assertEquals(AlarmStateId.INIT, state.getId());
	}

}
