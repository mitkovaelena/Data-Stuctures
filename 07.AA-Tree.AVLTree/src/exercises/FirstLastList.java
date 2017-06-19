package exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FirstLastList<T extends Comparable<T>> implements IFirstLastList<T> {
    private List<T> elements = new ArrayList<T>();
    private Map<T, ArrayList<T>> keyCount = new TreeMap<>();

    @Override
    public void add(T element) {
        this.elements.add(element);

        this.keyCount.putIfAbsent(element, new ArrayList<T>());
        this.keyCount.get(element).add(element);
    }

    @Override
    public int getCount() {
        return this.elements.size();
    }

    @Override
    public Iterable<T> first(int count) {
        if (this.getCount() < count) {
            throw new IllegalArgumentException();
        }

        ArrayList<T> output = new ArrayList<T>(count);
        for (int i = 0; i < count; i++) {
            output.add(this.elements.get(i));
        }
        return output;
    }

    @Override
    public Iterable<T> last(int count) {
        if (this.getCount() < count) {
            throw new IllegalArgumentException();
        }

        ArrayList<T> output = new ArrayList<T>(count);
        for (int i = this.getCount() - 1; i >= this.getCount() - count; i--) {
            output.add(this.elements.get(i));
        }
        return output;
    }

    @Override
    public Iterable<T> min(int count) {
        if (this.getCount() < count) {
            throw new IllegalArgumentException();
        }
        ArrayList<T> output = new ArrayList<T>();
        if (count == 0) {
            return output;
        }


        for (T key : this.keyCount.keySet()) {
            int ind = 0;
            for (int i = 0; i < this.keyCount.get(key).size() && count > 0; i++) {
                output.add(this.keyCount.get(key).get(i));
                count--;
            }
            if (count == 0) break;
        }

        return output;
    }

    @Override
    public Iterable<T> max(int count) {
        if (this.getCount() < count) {
            throw new IllegalArgumentException();
        }
        ArrayList<T> output = new ArrayList<T>();
        if (count == 0) {
            return output;
        }

        for (T key : this.keyCount.keySet()) {
            for (int i = this.keyCount.get(key).size() - 1; i >= 0; i--) {
                output.add(0, this.keyCount.get(key).get(i));
            }
        }

        output.subList(count, output.size()).clear();
        return output;
    }

    @Override
    public void clear() {
        this.elements = new ArrayList<>();
        this.keyCount = new TreeMap<>();
    }

    @Override
    public int removeAll(T element) {
        if (this.keyCount.containsKey(element)) {
            int temp = this.keyCount.remove(element).size();
            int counter = temp;
            for (int i = 0; i < this.elements.size() && counter > 0; i++) {
                if (this.elements.get(i).compareTo(element) == 0) {
                    this.elements.remove(i);
                    counter--;
                    i--;
                }
            }
            return temp;
        }
        return 0;
    }
}