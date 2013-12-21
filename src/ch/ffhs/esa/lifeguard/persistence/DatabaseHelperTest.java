/**
 * 
 */
package ch.ffhs.esa.lifeguard.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

/**
 * Test case for DatabaseHelper
 * 
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class DatabaseHelperTest extends AndroidTestCase {

    private DatabaseHelper helper;
    
	protected void setUp() throws Exception {
		super.setUp();
		RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
		helper = new DatabaseHelper(context);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.persistence.DatabaseHelper#onCreate(android.database.sqlite.SQLiteDatabase)}.
	 */
	public final void testOnCreateSQLiteDatabase() {
		helper.onCreate(helper.getWritableDatabase());
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		assertTrue(db.getPath().contains("test_lifeguard.db"));
		
		Cursor cursor = null;
		
		try {
		    cursor = db.rawQuery("SELECT * FROM contacts ORDER BY position ASC", null);
		} catch (SQLiteException e) {
		    e.printStackTrace();
		}
		
		assertNotNull(cursor);
		assertTrue(cursor.moveToFirst());
		
		int position = 0;
		while (!cursor.isAfterLast()) {
		    int newPosition = cursor.getInt(3);
		    Log.d(DatabaseHelperTest.class.getName(), newPosition + " vs. " + position);
		    assertTrue(newPosition > position);
		    position = newPosition;
		    cursor.moveToNext();
		}
		
		db.close();
	}

	/**
	 * Test method for {@link ch.ffhs.esa.lifeguard.persistence.DatabaseHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)}.
	 */
	public final void testOnUpgradeSQLiteDatabaseIntInt() {
		testOnCreateSQLiteDatabase();
	}
}
