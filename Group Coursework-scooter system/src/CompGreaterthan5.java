import java.util.Comparator;
/**
 * Compare whether the distance of the two values are greater than 5.
 *
 * @author Group 44
 * @version 1.0
 */
public class CompGreaterthan5 implements Comparator< int[] > {
	@Override
    public int compare( int[] a1,int[] a2) {
        if ( a1[2]==a2[2]) {
            return 0;
        }
        else if (a1[2]>=a2[2]) {
            return 1;
        }
        else {
            return -1;
        }
	}

}
