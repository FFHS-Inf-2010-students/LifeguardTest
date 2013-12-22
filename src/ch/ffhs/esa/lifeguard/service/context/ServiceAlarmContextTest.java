package ch.ffhs.esa.lifeguard.service.context;

import android.test.AndroidTestCase;
import ch.ffhs.esa.lifeguard.alarm.context.AlarmContext;
import ch.ffhs.esa.lifeguard.alarm.context.ServiceAlarmContext;
import ch.ffhs.esa.lifeguard.alarm.state.AlarmState;
import ch.ffhs.esa.lifeguard.alarm.state.AlarmStateId;
import ch.ffhs.esa.lifeguard.alarm.state.AlarmStateListener;
import ch.ffhs.esa.lifeguard.alarm.state.InitialState;

/**
 * @author
 * 
 */
public class ServiceAlarmContextTest extends AndroidTestCase {

	private ServiceAlarmContext alarmContext;

	private static class StateListener implements AlarmStateListener {
		public boolean wasCalled = false;

		public void onStateChanged(AlarmContext context) {
			wasCalled = true;
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		alarmContext = new ServiceAlarmContext(getContext());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		alarmContext.cancel();
	}

	/**
	 * Test method for
	 * {@link ch.ffhs.esa.lifeguard.service.context.ServiceAlarmContext#setNext(ch.ffhs.esa.lifeguard.service.state.AlarmState)}
	 * .
	 */
	public final void testSetNext() {
		alarmContext.setNext(new InitialState());
		assertEquals(alarmContext.getState().getId(), AlarmStateId.INIT);
	}

	/**
	 * Test method for
	 * {@link ch.ffhs.esa.lifeguard.service.context.ServiceAlarmContext#getState()}
	 * .
	 */
	public final void testGetState() {
		AlarmState state = alarmContext.getState();
		assertNotNull(state);
	}

	/**
	 * Test method for
	 * {@link ch.ffhs.esa.lifeguard.service.context.ServiceAlarmContext#addListener(ch.ffhs.esa.lifeguard.service.state.AlarmStateListener)}
	 * .
	 */
	public final void testAddListener() {
		StateListener listener = new StateListener();
		alarmContext.addListener(listener);
		alarmContext.setNext(new InitialState());
		assertTrue(listener.wasCalled);
	}
}
