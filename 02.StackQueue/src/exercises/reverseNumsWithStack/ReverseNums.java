package exercises.reverseNumsWithStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class ReverseNums {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputStr = reader.readLine().split(" ");
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < inputStr.length; i++) {
            stack.push(Integer.parseInt(inputStr[i]));
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop()+" ");
        }

    }
}
