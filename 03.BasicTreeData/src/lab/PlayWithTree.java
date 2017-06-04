package lab;

import lab.binaryTree.BinaryTree;
import lab.tree.Tree;


public class PlayWithTree {

    public static void main(String[] args) {
        Tree<Integer> tree =
                new Tree<>(7,
                        new Tree<>(19,
                                new Tree<>(1),
                                new Tree<>(12),
                                new Tree<>(31)),
                        new Tree<>(21),
                        new Tree<>(14,
                                new Tree<>(23),
                                new Tree<>(6)));

        System.out.println("Tree (indented):");
        String output = tree.print(0, new StringBuilder());
        System.out.println(output);

        System.out.println("Tree (DFS):");
        Iterable<Integer> returnedList = tree.orderDFS();
        for (Integer i : returnedList) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("Tree (BFS):");
        Iterable<Integer> returnedList2 = tree.orderBFS();
        for (Integer i : returnedList2) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("lab.tree.Tree nodes:");
        tree.each(e -> System.out.print(" " + e));
        System.out.println();

        System.out.println();
        BinaryTree<String> binaryTree =
                new BinaryTree<>("*",
                        new BinaryTree<>("+",
                                new BinaryTree<>("3"),
                                new BinaryTree<>("2")),
                        new BinaryTree<>("-",
                                new BinaryTree<>("9"),
                                new BinaryTree<>("6")));

        System.out.println("Binary tree (indented, pre-order):");
        output = binaryTree.printIndentedPreOrder(0, new StringBuilder());
        System.out.println(output);

        System.out.println("Binary tree nodes (in-order):");
        binaryTree.eachInOrder(e -> System.out.print(" " + e));
        System.out.println();

        System.out.println("Binary tree nodes (post-order):");
        binaryTree.eachPostOrder(e -> System.out.print(" " + e));
        System.out.println();
    }
}
