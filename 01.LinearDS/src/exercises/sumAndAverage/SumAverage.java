package exercises.sumAndAverage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SumAverage {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] intArr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int sum = Arrays.stream(intArr).sum();

        System.out.printf("Sum=%d; Average=%.2f", sum, (double)sum/intArr.length );

    }
}
