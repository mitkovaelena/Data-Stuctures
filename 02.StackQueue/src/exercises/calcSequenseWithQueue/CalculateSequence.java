package exercises.calcSequenseWithQueue;

import java.util.ArrayDeque;
import java.util.Scanner;

public class CalculateSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long start = Long.parseLong(scanner.nextLine());
        ArrayDeque<Long> queue = new ArrayDeque<>();
        queue.add(start);
        System.out.print(start);
        for (int i = 1; i < 50; i++) {
            if(i%3 == 0){
                long temp = queue.poll()+2;
                queue.add(temp);
                System.out.print(", " + temp);
            }
            if(i%3 == 1){
                long temp2 = queue.peek()+1;
                queue.add(temp2);
                System.out.print(", " + temp2);
            }
            if(i%3 == 2){
                long temp3 = 2*queue.peek()+1;
                queue.add(temp3);
                System.out.print(", " + temp3);
            }
        }
    }
}
