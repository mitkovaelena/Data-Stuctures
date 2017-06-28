package limitedMemory.main;

import java.util.*;

public class LimitedMemoryCollection<K, V> implements ILimitedMemoryCollection<K, V> {

    private LinkedList<Pair<K,V>> priority;
    private HashMap<K, LinkedList.Node> pairs;
    private int capacity;
    private int count;

    public LimitedMemoryCollection(int capacity) {
        this.capacity=capacity;
        this.count=0;
        this.priority = new LinkedList<>();
        this.pairs = new HashMap<>();
    }

    @Override
    public V get(K key) {
        if(!this.pairs.containsKey(key)){
            throw new IllegalArgumentException();
        } else {
            LinkedList.Node oldNode = this.pairs.get(key);
            this.priority.remove(oldNode);
            Pair<K,V> pair = (Pair<K, V>) oldNode.getValue();
            LinkedList.Node newNode = this.priority.addFirst(new Pair<K, V>(key, pair.getValue()));
            this.pairs.put(key, newNode);
            return pair.getValue();
        }
    }

    @Override
    public void set(K key, V value) {
        if(!this.pairs.containsKey(key)){
            if (this.count + 1 > this.capacity){
                Pair<K,V> removedPair = this.priority.removeLast();
                this.pairs.remove(removedPair.getKey());
            }
        } else {
            LinkedList.Node oldNode = this.pairs.get(key);
            this.priority.remove(oldNode);
            this.pairs.remove(key);
            this.count--;
        }
        LinkedList.Node newNode = this.priority.addFirst(new Pair<K, V>(key, value));
        this.pairs.put(key, newNode);
        this.count++;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {

        return priority.iterator();
    }
}
