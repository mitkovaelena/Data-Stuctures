package lab;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HashTable<TKey, TValue> implements Iterable<KeyValue<TKey, TValue>> {

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int size;
    private int capacity;
    private List<KeyValue<TKey, TValue>>[] slots;

    public HashTable() {
        this(INITIAL_CAPACITY);
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.slots = new LinkedList[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void add(TKey key, TValue value) {

        this.growIfNeeded();
        int hash = Math.abs(key.hashCode()) % this.slots.length;

        if (this.slots[hash] == null) {
            this.slots[hash] = new LinkedList<>();
        }

        List<KeyValue<TKey, TValue>> crntList = this.slots[hash];

        for (KeyValue keyValuePair : crntList) {
            if (keyValuePair.getKey().equals(key)) {
                throw new IllegalArgumentException("Key already exists: " + key);
            }
        }

        KeyValue newKeyValue = new KeyValue<TKey, TValue>(key, value);
        crntList.add(newKeyValue);
        this.size++;

    }

    private void growIfNeeded() {

        if (this.size() + 1 > this.capacity() * LOAD_FACTOR) {
            this.grow();
        }
    }

    private void grow() {

        HashTable<TKey, TValue> newTable = new HashTable<TKey, TValue>(this.capacity * 2);
        this.capacity *= 2;

        for (KeyValue<TKey, TValue> keyValue : this) {
            newTable.add(keyValue.getKey(), keyValue.getValue());
        }
        this.slots = newTable.slots;
        this.setSize(newTable.size());
    }

    public int size() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int capacity() {
        return this.capacity;
    }

    public boolean addOrReplace(TKey key, TValue value) {
        this.growIfNeeded();
        int hash = Math.abs(key.hashCode()) % this.slots.length;

        if (this.slots[hash] == null) {
            this.slots[hash] = new LinkedList<>();
        }

        List<KeyValue<TKey, TValue>> crntList = this.slots[hash];

        for (KeyValue keyValuePair : crntList) {
            if (keyValuePair.getKey().equals(key)) {
                keyValuePair.setValue(value);
                return false;
            }
        }

        KeyValue newKeyValue = new KeyValue<TKey, TValue>(key, value);
        crntList.add(newKeyValue);
        this.size++;
        return true;
    }

    public TValue get(TKey key) {
        KeyValue<TKey, TValue> outputKeyValuePair = this.find(key);
        if (outputKeyValuePair == null) {
            throw new IllegalArgumentException("Key don't exists: " + key);
        }
        return outputKeyValuePair.getValue();
    }

    public boolean tryGetValue(TKey key, TValue value) {
        return this.containsKey(key); //?
    }

    public KeyValue<TKey, TValue> find(TKey key) {
        int hash = Math.abs(key.hashCode()) % this.slots.length;

        if (this.slots[hash] != null) {
            List<KeyValue<TKey, TValue>> crntList = this.slots[hash];

            for (KeyValue<TKey, TValue> keyValuePair : crntList) {
                if (keyValuePair.getKey().equals(key)) {
                    return keyValuePair;
                }
            }
        }
        return null;
    }

    public boolean containsKey(TKey key) {
        return this.find(key) != null;
    }

    public boolean remove(TKey key) {
        int hash = Math.abs(key.hashCode()) % this.slots.length;

        if (this.slots[hash] != null) {
            List<KeyValue<TKey, TValue>> crntList = this.slots[hash];

            for (KeyValue<TKey, TValue> keyValuePair : crntList) {
                if (keyValuePair.getKey().equals(key)) {
                    crntList.remove(keyValuePair);
                    this.size--;
                    return true;
                }
            }
        }
        return false;
    }

    public void clear() {
        this.capacity = INITIAL_CAPACITY;
        this.setSize(0);
        this.slots = new LinkedList[INITIAL_CAPACITY];
    }

    public Iterable<TKey> keys() {
        LinkedList<TKey> elements = new LinkedList<>();
        for (List<KeyValue<TKey, TValue>> keyValueList : this.slots) {
            if (keyValueList != null) {
                for (KeyValue<TKey, TValue> keyValuePair : keyValueList) {
                    elements.add(keyValuePair.getKey());
                }
            }
        }
        return elements;
    }

    public Iterable<TValue> values() {
        LinkedList<TValue> elements = new LinkedList<>();
        for (List<KeyValue<TKey, TValue>> keyValueList : this.slots) {
            if (keyValueList != null) {
                for (KeyValue<TKey, TValue> keyValuePair : keyValueList) {
                    elements.add(keyValuePair.getValue());
                }
            }
        }
        return elements;
    }

    @Override
    public Iterator<KeyValue<TKey, TValue>> iterator() {
        LinkedList<KeyValue<TKey, TValue>> elements = new LinkedList<>();
        for (List<KeyValue<TKey, TValue>> keyValueList : this.slots) {
            if (keyValueList != null) {
                elements.addAll(keyValueList);
            }
        }
        return elements.iterator();
    }
}
