import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;
    private int count;

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node node) {
        this.copy(node);
    }

    public Node getRoot() {
        return this.root;
    }

    public int getCount() {
        return count;
    }

    public void insert(T value) {
        this.count++;
        if (this.root == null) {
            this.root = new Node(value);
            return;
        }
        Node parent = null;
        Node current = this.root;
        while (current != null) {
            parent = current;
            if (current.getValue() == value) {
                this.count--;
                return;
            } else if (current.getValue().compareTo(value) > 0) {
                parent.childrenCount++;
                current = current.getLeft();

            } else if (current.getValue().compareTo(value) < 0) {
                parent.childrenCount++;
                current = current.getRight();
            }
        }
        if (parent.getValue().compareTo(value) > 0) {
            parent.setLeft(new Node(value));

        } else if (parent.getValue().compareTo(value) < 0) {
            parent.setRight(new Node(value));
        }

    }

    public boolean contains(T value) {
        Node current = this.root;
        while (current != null) {
            if (current.getValue() == value) break;
            else if (current.getValue().compareTo(value) > 0) current = current.getLeft();
            else if (current.getValue().compareTo(value) < 0) current = current.getRight();
        }
        return current != null;
    }

    public BinarySearchTree<T> search(T item) {
        Node current = this.root;
        while (current != null) {
            if (current.getValue() == item) break;
            else if (current.getValue().compareTo(item) > 0) current = current.getLeft();
            else if (current.getValue().compareTo(item) < 0) current = current.getRight();
        }
        return new BinarySearchTree<T>(current);
    }

    public void eachInOrder(Consumer<T> consumer) {
        eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node root, Consumer<T> consumer) {
        if (root != null) {
            if (root.getLeft() != null)
                eachInOrder(root.getLeft(), consumer);
            consumer.accept(root.getValue());
            if (root.getRight() != null)
                eachInOrder(root.getRight(), consumer);
        }
    }

    private void copy(Node node) {
        if (node == null) {
            return;
        }
        this.insert(node.getValue());
        this.copy(node.getLeft());
        this.copy(node.getRight());
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }
        Node parrent = null;
        Node current = this.root;
        while (current.getLeft() != null) {
            parrent = current;
            parrent.childrenCount--;
            current = current.getLeft();
        }
        if (parrent == null) {
            this.root = current.getRight();
        } else {
            parrent.setLeft(current.getRight());
        }
        this.count--;
    }

    public Iterable<T> range(T from, T to) {
        Deque queue = new ArrayDeque();

        this.range(this.root, queue, from, to);
        return queue;
    }

    private void range(Node node, Deque queue, T from, T to) {
        if (node == null) {
            return;
        }
        int nodeInLowerRange = from.compareTo(node.getValue());
        int nodeInHigherRange = to.compareTo(node.getValue());

        if (nodeInLowerRange < 0) {
            this.range(node.getLeft(), queue, from, to);
        }
        if (nodeInLowerRange <= 0 && nodeInHigherRange >= 0) {
            queue.add(node.getValue());
        }
        if (nodeInHigherRange > 0) {
            this.range(node.getRight(), queue, from, to);
        }
    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }
        Node parrent = null;
        Node current = this.root;
        while (current.getRight() != null) {
            parrent = current;
            parrent.childrenCount--;
            current = current.getRight();
        }
        if (parrent == null) {
            this.root = current.getLeft();
        } else {
            parrent.setRight(current.getLeft());
        }
        this.count--;
    }

    public T ceil(T item) {
        Node parent = null;
        Node current = this.root;
        while (current != null) {
            if (current.getValue().compareTo(item) > 0) {
                parent = current;
                current = current.getLeft();
            } else if (current.getValue().compareTo(item) < 0) {
                if (parent != null && current.getRight() != null && current.getRight().getValue().compareTo(item) < 0) {
                    return parent.getValue();
                }
                parent = current;
                current = current.getRight();
            } else return current.getValue();
        }
        if (parent != null && parent.getValue().compareTo(item) >= 0) {
            return parent.getValue();
        }
        return null;
    }

    public T floor(T item) {
        Node parent = null;
        Node current = this.root;
        while (current != null) {
            if (current.getValue().compareTo(item) > 0) {
                if (parent != null && parent.getValue().compareTo(item) < 0) {
                    return parent.getValue();
                }
                parent = current;
                current = current.getLeft();
            } else if (current.getValue().compareTo(item) < 0) {
                parent = current;
                current = current.getRight();
            } else return current.getValue();
        }
        if (parent != null && parent.getValue().compareTo(item) <= 0) {
            return parent.getValue();
        }
        return null;
    }

    public void delete(T item) {
        if (this.getRoot() == null) {
            return;
        }
        Node parent = null;
        Node forDeleting = this.root;
        while (forDeleting != null) {
            forDeleting.childrenCount--;
            if (forDeleting.getValue() == item) break;
            else if (forDeleting.getValue().compareTo(item) > 0) {
                parent = forDeleting;
                forDeleting = forDeleting.getLeft();
            } else if (forDeleting.getValue().compareTo(item) < 0) {
                parent = forDeleting;
                forDeleting = forDeleting.getRight();
            }
        }

        if (forDeleting == null) {
            return;
        }

        if (forDeleting.getLeft() == null && forDeleting.getRight() == null) {
            changeParent(parent, forDeleting, null);
            if (parent == null) this.root.setValue(null);
            return;
        }
        if (forDeleting.getRight() == null) {
            forDeleting.getLeft().childrenCount = forDeleting.childrenCount - 1;
            changeParent(parent, forDeleting, forDeleting.getLeft());
            return;
        }
        if (forDeleting.getRight().getLeft() == null) {
            forDeleting.getRight().childrenCount = forDeleting.childrenCount - 1;
            forDeleting.getRight().setLeft(forDeleting.getLeft());
            changeParent(parent, forDeleting, forDeleting.getRight());
            return;
        }
        Node prev = forDeleting.getRight();
        Node crnt = prev.getLeft();
        while (crnt.getLeft() != null) {
            crnt.childrenCount--;
            prev = crnt;
            crnt = crnt.getLeft();
        }
        prev.setLeft(null);
        crnt.childrenCount = forDeleting.childrenCount - 1;
        crnt.setLeft(forDeleting.getLeft());
        crnt.setRight(forDeleting.getRight());
        changeParent(parent, forDeleting, crnt);
    }

    private void changeParent(Node parent, Node forDeleting, Node newParent) {
        if (parent == null) this.root = newParent;
        else if (parent.getLeft() == forDeleting) parent.setLeft(newParent);
        else if (parent.getRight() == forDeleting) parent.setRight(newParent);
    }

    public int rank(T item) {
        return rank(this.root, item);
    }

    private int rank(Node node, T item) {
        if (node == null) {
            return 0;
        }

        if (node.getValue().compareTo(item) > 0) {
            return this.rank(node.getLeft(), item);
        } else if (node.getValue().compareTo(item) < 0) {
            return 1 + this.getChildrenCount(node.getLeft()) + this.rank(node.getRight(), item);
        }
        return this.getChildrenCount(node.getLeft());
    }

    private int getChildrenCount(Node node) {
        if (node == null) {
            return 0;
        }

        return node.childrenCount;
    }

    public T select(int rank) {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }
        Node node = this.root;
        while (node != null) {
            if (this.rank(node.getValue()) > rank) {
                node = node.getLeft();
            } else if (this.rank(node.getValue()) < rank) {
                node = node.getRight();
            } else {
                break;
            }
        }
        return node.getValue();
    }

    class Node {
        private T value;
        private Node left;
        private Node right;
        private int childrenCount;

        public int getChildrenCount() {
            return childrenCount;
        }

        public void setChildrenCount(int childrenCount) {
            this.childrenCount = childrenCount;
        }

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.childrenCount = 1;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}

