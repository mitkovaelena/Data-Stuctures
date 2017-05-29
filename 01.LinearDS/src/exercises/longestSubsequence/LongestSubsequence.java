package exercises.longestSubsequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LongestSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] intArr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int maxLen = 1;
        int count = 1;
        int elem = intArr[0];

        for (int i = 0; i < intArr.length-1; i++) {
            if (intArr[i] == intArr[i+1] ){
                count++;
                if (count > maxLen){
                    maxLen = count;
                    elem = intArr[i];
                }
            } else {
                count = 1;
            }
        }

        for (int i = 0; i < maxLen; i++) {
            System.out.print(elem + " " );
        }
    }
}
