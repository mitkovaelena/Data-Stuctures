package exercises;

import lab.HashTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class countSymbols {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] inputLine = reader.readLine().toCharArray();
        HashTable<Character, Integer> result = new HashTable<>();

        for (char el : inputLine){
            result.addOrReplace(el, result.containsKey(el)?result.get(el)+1:1);
        }

        List<Character> keySet = (List<Character>) result.keys();
        keySet = keySet.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for (Character key : keySet) {
            System.out.println(key + ": " + result.get(key) + " time/s");
        }
    }
}