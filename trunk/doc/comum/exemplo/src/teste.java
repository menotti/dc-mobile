// This is an example of a single line comment using two slashes
 
/* This is an example of a multiple line comment using the
 slash and asterisk.
 This type of comment can be used to hold a lot of information
 or deactivate
 code, but it is very important to remember to close the
 comment. */
 
/**
 * This is an example of a Javadoc comment; 
 * Javadoc can compile documentation
 * from this text.
 */
 
/** Finally, an example of a method written in Java,
 wrapped in a class. */
package fibsandlies;
import java.util.HashMap;
 
public class FibCalculator extends Fibonacci implements Calculator {
    private static HashMap<Integer, Integer> memoized =
	new HashMap<Integer, Integer>();
 
    /** Given a non-negative number FIBINDEX, returns,
     *  the Nth Fibonacci number, where N equals FIBINDEX.
     *  @param fibIndex The index of the Fibonacci number
     *  @return The Fibonacci number itself
     */
    @Override
    public static int fibonacci(int fibIndex) {
        if (memoized.contains(fibIndex)) {
            return memoized.get(fibIndex);
        } else {
            int answer = fibonacci(fibIndex - 1) 
			+ fibonacci(fibIndex - 2);
            memoized.put(fibIndex, answer);
            return answer;
        }
    }
}