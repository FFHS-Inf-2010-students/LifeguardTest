package ch.ffhs.esa.lifeguard.service.state;

import junit.framework.TestCase;

/**
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class TickingStateTest extends TestCase {

	private AlarmState state;
	
	protected void setUp() throws Exception {
		super.setUp();
		state = new TickingState();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		state = null;
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.service.state.TickingState#getId()}.
	 */
	public final void testGetId() {
		assertEquals(AlarmStateId.TICKING, state.getId());
	}

}
