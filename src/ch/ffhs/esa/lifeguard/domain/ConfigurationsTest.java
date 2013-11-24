/**
 * 
 */
package ch.ffhs.esa.lifeguard.domain;

import java.util.List;

import ch.ffhs.esa.lifeguard.persistence.DatabaseHelper;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

/**
 * Test case for Configurations
 * 
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class ConfigurationsTest extends AndroidTestCase {

	private Configurations configurations;
	
	private DatabaseHelper db;
	
	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		// Create a separate test database
		RenamingDelegatingContext context
		= new RenamingDelegatingContext(getContext(), "test_");
		this.db = new DatabaseHelper(context);
		this.configurations = new Configurations(this.db);
	}

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		this.db.getWritableDatabase().execSQL("DELETE FROM configurations");
		this.db.getWritableDatabase().execSQL("INSERT INTO configurations (delay) VALUES (60);");
		this.configurations = null;
		this.db.close();
	}


	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.domain.Configurations#persist(ch.ffhs.esa.lifeguard.domain.ConfigurationInterface)}.
	 */
	public final void testPersist() {
		ConfigurationInterface c = new Configuration();
		c.setDelay(70);
		long id = this.configurations.persist(c);
		assertEquals(1, id);
		assertEquals(70, c.getDelay());
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.domain.Configurations#delete(ch.ffhs.esa.lifeguard.domain.ConfigurationInterface)}.
	 */
	public final void testDelete() {
		ConfigurationInterface c = new Configuration();
		c.setDelay(90);
		
		long id = this.configurations.persist(c);
		
		this.configurations.delete(c);
		assertEquals(60, this.configurations.findById(id));
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.domain.Configurations#getAll()}.
	 */
	public final void testGetAll() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.domain.Configurations#findById(long)}.
	 */
	public final void testFindById() {
		assertEquals(60, this.configurations.findById(-1));	}
}
