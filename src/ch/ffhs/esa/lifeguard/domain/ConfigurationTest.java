package ch.ffhs.esa.lifeguard.domain;

import junit.framework.TestCase;

/**
 * Test case for Configuration
 * 
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class ConfigurationTest extends TestCase {

	private ConfigurationInterface configuration;
	
	protected void setUp() throws Exception {
		super.setUp();
		configuration = new Configuration();
		configuration.setDelay(100);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		configuration = null;
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.domain.Configuration#toString()}.
	 */
	public final void testToString() {
		assertEquals("100", configuration.toString());
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.domain.Configuration#getId()}.
	 */
	public final void testGetSetId() {
		assertEquals(0, configuration.getId());
		configuration.setId(1L);
		assertEquals(1L, configuration.getId());
		configuration.setId(123L);
		assertEquals(123L, configuration.getId());
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.domain.Configuration#getDelay()}.
	 */
	public final void testGetSetDelay() {
		assertEquals(100, configuration.getDelay());
		configuration.setDelay(0);
		assertEquals(60, configuration.getDelay()); // 0 is not allowed
		configuration.setDelay(1000);
		assertEquals(1000, configuration.getDelay());
	}
}
