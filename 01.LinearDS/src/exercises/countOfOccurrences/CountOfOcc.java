package exercises.countOfOccurrences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class CountOfOcc {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> occupancies = new TreeMap<>();
        int[] intArr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Arrays.stream(intArr).forEach(i -> {
            occupancies.putIfAbsent(i, 0);
            occupancies.put(i, occupancies.get(i)+1);
        });

        occupancies.forEach((a,b) -> System.out.println(a + " -> " + b + " times"));
    }
}
