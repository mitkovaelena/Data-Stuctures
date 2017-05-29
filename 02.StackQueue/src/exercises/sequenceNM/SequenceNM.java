package exercises.sequenceNM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class SequenceNM {

    private static class Node {
        private int value;
        private Node prev;

        private Node(int value, Node prev) {
            this.value = value;
            this.prev = prev;
        }

        private int getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getPrev() {
            return this.prev;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Deque<Node> queue = new ArrayDeque<Node>();
        String input[] = reader.readLine().split(" ");

        Integer startInt = Integer.parseInt(input[0]);
        Integer endInt = Integer.parseInt(input[1]);
        queue.add(new Node(startInt, null));

        while (!queue.isEmpty()){
            Node crnt = queue.poll();
            if(crnt.getValue() < endInt){
                int value = crnt.getValue();

                queue.add(new Node(value+1, crnt));
                queue.add(new Node(value+2, crnt));
                queue.add(new Node(value*2, crnt));

            } else if (crnt.getValue() == endInt){
                printSolution(crnt);
                System.out.println(endInt);
                return;
            }
        }
    }

    private static void printSolution(Node crnt) {
        StringBuilder sb = new StringBuilder();

        while (crnt.getPrev() != null){
            crnt = crnt.getPrev();
            sb.insert(0, crnt.getValue() + " -> ");
        }
            System.out.print(sb);
    }
}
