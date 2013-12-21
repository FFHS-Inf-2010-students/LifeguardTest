/**
 * 
 */
package ch.ffhs.esa.lifeguard;

/**
 * @author Juerg Gutknecht <juerg.gutknecht@students.ffhs.ch>
 *
 */
public class LifeguardTest extends Lifeguard {

    private static final String PREFIX = "test_";
    
    @Override
    public String getSharedPreferencesIdentifier() {
        return PREFIX + super.getSharedPreferencesIdentifier();
    }
}
