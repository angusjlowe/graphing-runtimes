import java.util.Arrays;

/**
 * Created by s1578278 on 18/04/17.
 */
public class AlgoCode {
    public static int algorithm1(int[] input) {
        for(int i = 1; i < input.length-1; i++) {
            int j = i;
            while(j>0 && input[j+1] > input[j]) {
                int temp = input[j];
                input[j] = input[j+1];
                input[j+1] = temp;
                j --;
            }

        }
        return 0;
    }
    public static int algorithm2(int[] input) {
        int total = 0;
        for(int i = 1; i < input.length-1; i++) {
            int j = i;
            while(j>0 && input[j+1] > input[j]) {
                int temp = input[j];
                input[j] = input[j+1];
                input[j+1] = temp;
                j --;
            }

        }
        return 0;
    }
}
