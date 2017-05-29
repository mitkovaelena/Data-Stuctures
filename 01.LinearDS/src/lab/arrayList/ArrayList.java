package lab.arrayList;

import java.util.Iterator;

public class ArrayList<T> implements Iterable<T> {
private static int INITIAL_CAPACITY = 2;
private int count;
private T[] items;

    public ArrayList() {
        this.items = (T[]) new Object[INITIAL_CAPACITY];
        this.count = 0;
    }

    public T get(int ind){
        if(ind < 0 || ind >= this.count){
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.items[ind];
    }

    public int Count(){
        return this.count;
    }

    public void Add(T element){
        if (this.count == this.items.length){
            this.Resize();
        }
        this.items[this.count++] = element;
    }

    public void RemoveAt(int ind){
        if(ind < 0 || ind >= this.count){
            throw new ArrayIndexOutOfBoundsException();
        }
        this.count--;
        this.Shift(ind);

        if (this.count == this.items.length/2){
            this.Shrink();
        }
    }

    private void Shrink() {
        T[] copy = (T[]) new Object[Math.max(this.items.length/2, INITIAL_CAPACITY)];

        for (int i = 0; i < this.count; i++) {
            copy[i] = this.items[i];
        }

        this.items = copy;

    }

    private void Shift(int ind) {
        for (int i = ind; i < this.count; i++) {
            this.items[ind] = this.items[i+1];
        }
        this.items[this.count] = null;
    }

    private void Resize(){
        T[] copy = (T[]) new Object[this.items.length*2];

        for (int i = 0; i < this.count; i++) {
           copy[i] = this.items[i];
        }

        this.items = copy;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private final class ListIterator implements Iterator<T>{
        private int counter;

        public ListIterator(){
            this.counter = 0;
        }

        @Override
        public boolean hasNext(){
            return count > this.counter;
        }

        @Override
        public T next(){
            return get(this.counter++);
        }
    }

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.Add(5);
        list.Add(5);
        list.Add(5);
        list.Add(5);

        for(int i : list){
            System.out.println(i);
        }

        list.Add(3);

        for(int i : list){
            System.out.println(i);
        }
    }
}


