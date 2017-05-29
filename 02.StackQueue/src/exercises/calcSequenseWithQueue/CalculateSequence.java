package exercises.calcSequenseWithQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class CalculateSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long firstElement = Long.parseLong(reader.readLine());
        Deque<Long> queue = new ArrayDeque<>();

        queue.add(firstElement);
        System.out.print(firstElement);

        for (int i = 1; i < 50; i++) {
            if (i % 3 == 0) {
                long temp = queue.poll() + 2;
                queue.add(temp);
                System.out.print(", " + temp);
            }
            if (i % 3 == 1) {
                long temp2 = queue.peek() + 1;
                queue.add(temp2);
                System.out.print(", " + temp2);
            }
            if (i % 3 == 2) {
                long temp3 = 2 * queue.peek() + 1;
                queue.add(temp3);
                System.out.print(", " + temp3);
            }
        }
    }
}
