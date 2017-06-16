import java.util.function.Consumer;

public class AVL<T extends Comparable<T>> {

    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean contains(T item) {
        Node<T> node = this.search(this.root, item);
        return node != null;
    }

    public void insert(T item) {
        this.root = this.insert(this.root, item);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node<T> node, Consumer<T> action) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.getLeft(), action);
        action.accept(node.getValue());
        this.eachInOrder(node.getRight(), action);
    }

    private Node<T> insert(Node<T> node, T item) {
        if (node == null) {
            return new Node<>(item);
        }

        int cmp = item.compareTo(node.getValue());
        if (cmp < 0) {
            node.setLeft(this.insert(node.getLeft(), item));
        } else if (cmp > 0) {
            node.setRight(this.insert(node.getRight(), item));
        }

        node = this.balance(node);
        this.updateHeight(node);
        return node;
    }

    private int height(Node<T> node) {
        return node == null ? 0 : node.getHeight();
    }

    private void updateHeight(Node<T> node) {
        node.setHeight(Math.max(this.height(node.getLeft()), this.height(node.getRight())) + 1);
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> temp = node.getRight();
        node.setRight(temp.getLeft());
        temp.setLeft(node);

        updateHeight(node);

        return temp;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> temp = node.getLeft();
        node.setLeft(temp.getRight());
        temp.setRight(node);

        updateHeight(node);

        return temp;
    }

    private Node<T> balance(Node<T> node) {
        int balanceFactor = this.height(node.getLeft()) - this.height(node.getRight());

        if (balanceFactor < -1) {
            balanceFactor = this.height(node.getRight().getLeft()) - this.height(node.getRight().getRight());
            if (balanceFactor > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }
        else if (balanceFactor > 1) {
            balanceFactor = this.height(node.getLeft().getLeft()) - this.height(node.getLeft().getRight());
            if (balanceFactor < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }

        return node;
    }

    private Node<T> search(Node<T> node, T item) {
        if (node == null) {
            return null;
        }

        int cmp = item.compareTo(node.getValue());
        if (cmp < 0) {
            return search(node.getLeft(), item);
        } else if (cmp > 0) {
            return search(node.getRight(), item);
        }

        return node;
    }
}
