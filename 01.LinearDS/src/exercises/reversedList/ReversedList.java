package exercises.reversedList;

import java.util.Iterator;

public class ReversedList<T> implements Iterable<T> {
    private static int INITIAL_CAPACITY = 2;
    private int count;
    private T[] items;

    public ReversedList() {
        this.items = (T[]) new Object[INITIAL_CAPACITY];
        this.count = 0;
    }

    public T get(int ind) {
        if (ind < 0 || ind > this.count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.items[this.count - ind - 1];
    }

    public int count() {
        return this.count;
    }

    public int capacity() {
        return this.items.length;
    }

    public void add(T element) {
        if (this.count == this.items.length) {
            this.resize();
        }
        this.items[this.count++] = element;
    }

    public T removeAt(int ind) {
        if (ind < 0 || ind > this.count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T element = this.items[count - ind - 1];
        this.count--;
        this.shift(count - ind);

        if (this.count == this.items.length / 4) {
            this.shrink();
        }
        return element;
    }

    private void shrink() {
        T[] copy = (T[]) new Object[this.items.length / 2];

        for (int i = 0; i < this.count; i++) {
            copy[i] = this.items[i];
        }

        this.items = copy;

    }

    private void shift(int ind) {
        for (int i = ind; i < this.count; i++) {
            this.items[i] = this.items[i + 1];
        }
        this.items[this.count] = null;
    }

    private void resize() {
        T[] copy = (T[]) new Object[this.items.length * 2];

        for (int i = 0; i < this.count; i++) {
            copy[i] = this.items[i];
        }

        this.items = copy;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private final class ListIterator implements Iterator<T> {
        private int counter;

        public ListIterator() {
            this.counter = 0;
        }

        @Override
        public boolean hasNext() {
            return count > this.counter;
        }

        @Override
        public T next() {
            return get(++this.counter);
        }
    }

}


