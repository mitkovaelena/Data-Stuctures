package lab;

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
        } else if (balanceFactor > 1) {
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

    public void deleteMin() {
        if (this.root == null) {
            return;
        }
        this.root = this.deleteMin(this.root, null);
    }

    private Node<T> deleteMin(Node<T> current, Node<T> parent) {
        // haven't reached leaf yet
        if (current.getLeft() != null) {
            return deleteMin(current.getLeft(), current);
        }

        if (parent == null) {
            return current.getRight();
        }
        // case 1, no child nodes
        if (current.getRight() == null) {
            parent.setLeft(null);
        } else { // case 2, right child node
            parent.setLeft(deleteMin(current.getRight(), current));
        }

        balance(parent);
        return parent;
    }

    // BONUS
    public void deleteMax() {
        if (this.root == null) {
            return;
        }
        this.root = this.deleteMax(this.root, null);
    }

    private Node<T> deleteMax(Node<T> current, Node<T> parent) {
        // haven't reached leaf yet
        if (current.getRight() != null) {
            return deleteMin(current.getRight(), current);
        }

        if (parent == null) {
            return current.getLeft();
        }
        // case 1, no child nodes
        if (current.getRight() == null) {
            parent.setRight(null);
        } else { // case 2, right child node
            parent.setRight(deleteMin(current.getLeft(), current));
        }

        balance(parent);
        return parent;
    }

    public void delete(T item) {
        if (this.getRoot() == null) {
            return;
        }
        this.root = delete(this.root, item);
    }

    private Node<T> delete(Node<T> node, T element) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (node == null)
            return node;

        if (element.compareTo(node.getValue()) < 0) {
            node.setLeft(delete(node.getLeft(), element));
        } else if (element.compareTo(node.getValue()) > 0) {
            node.setRight(delete(node.getRight(), element));
        } else {

            // node with only one child or no child
            if ((node.getLeft() == null) || (node.getRight() == null)) {
                Node temp = node.getLeft();
                if (node.getLeft() == null)
                    temp = node.getRight();

                // No child case
                if (temp == null) {
                    node = null;
                } else
                    node = temp;
            } else {

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node<T> temp = minValueNode(node.getRight());

                // Copy the inorder successor's data to this node
                node.setValue(temp.getValue());

                // Delete the inorder successor
                node.setRight(delete(node.getRight(), temp.getValue()));
            }
        }

        // If the tree had only one node then return
        if (node == null)
            return node;

        node = balance(node);
        updateHeight(node);
        return node;
    }

    Node<T> minValueNode(Node<T> node) {
        Node<T> current = node;

        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current;
    }

}
