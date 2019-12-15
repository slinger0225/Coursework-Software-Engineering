import java.util.*;
/**
 * Compare whether the distance of the two days are less than 7 days.
 *
 * @author Group 44
 * @version 1.0
 */
public class Comp7DaysFromNow implements Comparator< int[] > {
	@Override
    public int compare( int[] a1,int[] a2) {
		int time=  a1[0]*30+a1[1];
		int now=  a2[0]*30+a2[1];
        if ( now==time) {
            return 0;
        }
        else if (time>=now-7) {
            return 1;
        }
        else {
            return -1;
        }
	}

}
