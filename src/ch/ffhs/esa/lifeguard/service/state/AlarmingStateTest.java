package ch.ffhs.esa.lifeguard.service.state;

import ch.ffhs.esa.lifeguard.alarm.state.*;

import junit.framework.TestCase;

/**
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class AlarmingStateTest extends TestCase {

	private AlarmState state;
	
	protected void setUp() throws Exception {
		super.setUp();
		state = new AlarmingState();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		state = null;
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.service.state.AlarmingState#getId()}.
	 */
	public final void testGetId() {
		assertEquals(AlarmStateId.ALARMING, state.getId());
	}

}
