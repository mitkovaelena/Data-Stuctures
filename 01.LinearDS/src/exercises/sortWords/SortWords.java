package exercises.sortWords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class SortWords {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> words = Arrays.asList(reader.readLine().split(" "));
        words.sort(String::compareTo);
        words.forEach(word -> System.out.print(word + " "));
    }
}
