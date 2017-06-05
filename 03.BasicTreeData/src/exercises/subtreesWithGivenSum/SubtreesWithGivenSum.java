package exercises.subtreesWithGivenSum;

import lab.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SubtreesWithGivenSum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Map<Integer, Tree<Integer>> treeMap = new HashMap<>();
        Tree<Integer> tree = new Tree<>(0);

        for (int i = 0; i < n - 1; i++) {
            int[] intArr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            treeMap.putIfAbsent(intArr[0], new Tree<Integer>(intArr[0]));
            treeMap.putIfAbsent(intArr[1], new Tree<Integer>(intArr[1]));
            treeMap.get(intArr[0]).addChild(treeMap.get(intArr[1]));
        }
        int sum = Integer.parseInt(reader.readLine());

        for (Tree<Integer> t : treeMap.values()) {
            if (t.getParent() == null) {
                tree = t;
                break;
            }
        }

        System.out.println("Subtrees of sum " + sum + ": ");

        List<Integer> subtreesEqualToSum = new ArrayList<>();
        calculateSubtreeSum(tree, sum, 0, subtreesEqualToSum);

        for (Integer node : subtreesEqualToSum) {
            StringBuilder output = new StringBuilder();
            System.out.println(printPreOrder(treeMap.get(node), output));
        }
    }

    public static String printPreOrder(Tree<Integer> parentNode, StringBuilder builder) {
        if (parentNode != null) {
            builder.append(parentNode.getValue() + " ");
            for (Tree<Integer> child : parentNode.getChildren()) {
                printPreOrder(child, builder);
            }
        }
        return builder.toString();
    }

    private static int calculateSubtreeSum(Tree<Integer> parentNode, int sum, int currentSum, List<Integer> roots) {
        currentSum = parentNode.getValue();

        for (Tree<Integer> child : parentNode.getChildren()) {
            currentSum += calculateSubtreeSum(child, sum, currentSum, roots);
        }

        if (currentSum == sum) {
            roots.add(parentNode.getValue());
        }
        return currentSum;
    }
}
