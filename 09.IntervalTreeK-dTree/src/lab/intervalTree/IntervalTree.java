package lab.intervalTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IntervalTree {

    private class Node {

        private Interval interval;
        private double max;
        private Node right;
        private Node left;

        public Node(Interval interval) {
            this.interval = interval;
            this.max = interval.getHi();
        }
    }

    private Node root;

    public void insert(double lo, double hi) {
        this.root = this.insert(this.root, lo, hi);
    }

    public void eachInOrder(Consumer<Interval> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    public Interval searchAny(double lo, double hi) {
        Node crnt = this.root;
        while (crnt != null && !crnt.interval.intersects(lo, hi)) {
            if (crnt.left != null && crnt.left.max > lo) {
                crnt = crnt.left;
            } else {
                crnt = crnt.right;
            }
        }
        return crnt != null ? crnt.interval : null;
    }

    public Iterable<Interval> searchAll(double lo, double hi) {
        List<Interval> intersectingIntervals = new ArrayList<>();
        searchAll(this.root, lo, hi, intersectingIntervals);
        return intersectingIntervals;
    }

    private void searchAll(Node crnt, double lo, double hi, List<Interval> intersectingIntervals) {  //inorder
        if (crnt == null) {
            return;
        }
        if (crnt.left != null && crnt.left.max > lo) {
            searchAll(crnt.left, lo, hi, intersectingIntervals);
        }
        if (crnt.interval.intersects(lo, hi)) {
            intersectingIntervals.add(crnt.interval);
        }
        if (crnt.right != null && crnt.right.interval.getLo() < hi) {
            searchAll(crnt.right, lo, hi, intersectingIntervals);
        }
    }

    private void eachInOrder(Node node, Consumer<Interval> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.interval);
        this.eachInOrder(node.right, consumer);
    }

    private Node insert(Node node, double lo, double hi) {
        if (node == null) {
            return new Node(new Interval(lo, hi));
        }

        int cmp = Double.compare(lo, node.interval.getLo());
        if (cmp < 0) {
            node.left = insert(node.left, lo, hi);
        } else if (cmp > 0) {
            node.right = insert(node.right, lo, hi);
        }

        updateMax(node);
        return node;
    }

    private void updateMax(Node node) {
        Node maxChild = getMax(node.left, node.right);
        node.max = maxChild.max;
    }

    private Node getMax(Node left, Node right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        return left.max > right.max ? left : right;
    }
}
