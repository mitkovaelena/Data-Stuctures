package limitedMemory.main;

import java.util.Iterator;
import java.util.function.Consumer;

public class LinkedList<E> implements Iterable<E> {

    public class Node {
        private E value;
        private Node next;
        private Node prev;

        private Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return this.value;
        }

        private Node getNext() {
            return this.next;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        private Node getPrev() {
            return this.prev;
        }

        private void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public Node addFirst(E element) {
        Node oldHead = this.head;
        Node newNode = new Node(element);
        this.head = newNode;
        this.head.setNext(oldHead);

        if (this.size() == 0) {
            this.tail = head;
        } else {
            oldHead.setPrev(this.head);
        }
        this.setSize(this.size() + 1);
        return newNode;
    }

    public Node addLast(E element) {
        Node oldTail = this.tail;
        Node newNode = new Node(element);
        this.tail = newNode;
        this.tail.setPrev(oldTail);

        if (this.size() == 0) {
            this.head = tail;
        } else {
            oldTail.setNext(this.tail);
        }
        this.setSize(this.size() + 1);

        return newNode;
    }

    public E removeFirst() {
        if (this.size() == 0) {
            throw new IllegalArgumentException();
        }

        Node oldHead = this.head;
        this.head = this.head.getNext();

        this.setSize(this.size() - 1);

        if (this.size() == 0) {
            this.tail = null;
        } else {
            this.head.setPrev(null);
        }

        return oldHead.getValue();
    }

    public void remove(Node node){
        if(node.getPrev() == null){
            this.head = this.head.getNext();
        } else {
            node.getPrev().setNext(node.getNext());
        }
        this.size--;
    }

    public E removeLast() {
        if (this.size() == 0) {
            throw new IllegalArgumentException();
        }
        Node oldTail = this.tail;

        if (this.size() == 1) {
            this.head = null;
            this.tail = null;
        } else {
            Node newTail = this.tail.getPrev();
            newTail.setNext(null);
            this.tail = newTail;
        }

        this.setSize(this.size() - 1);
        return oldTail.getValue();
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private final class ListIterator implements Iterator<E> {
        private Node current;

        private ListIterator() {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public E next() {
            E returnValue = this.current.getValue();
            this.current = this.current.getNext();
            return returnValue;
        }
    }


    public E[] toArray() {
        E[] itemsArray = (E[]) new Object[this.size()];
        Node current = this.head;
        for (int i = 0; i < itemsArray.length; i++) {
            itemsArray[i] = current.getValue();
            current = current.getNext();
        }
        return itemsArray;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Node current = this.head;

        while (current != null) {
            action.accept(current.getValue());
            current = current.getNext();
        }
    }
}
