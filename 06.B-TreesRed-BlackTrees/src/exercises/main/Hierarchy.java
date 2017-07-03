package exercises.main;

import java.util.*;

public class Hierarchy<T> implements IHierarchy<T> {
    Map<T, ArrayList<T>> nodeChildren = new LinkedHashMap<T, ArrayList<T>>();
    Map<T, T> nodeParent = new LinkedHashMap<T, T>();

    public Hierarchy(T element) {
        this.nodeChildren.put(element, new ArrayList<T>());
        this.nodeParent.put(element, null);
    }

    public void Add(T parent, T child) {
        if (!this.nodeChildren.containsKey(parent) || this.nodeChildren.containsKey(child)) {
            throw new IllegalArgumentException();
        }
        this.nodeChildren.put(child, new ArrayList<T>());
        this.nodeChildren.get(parent).add(child);
        this.nodeParent.put(child, parent);
    }

    public int getCount() {
        return this.nodeChildren.size();
    }

    public void Remove(T element) {
        if (!this.nodeParent.containsKey(element)) {
            throw new IllegalArgumentException();
        }
        if (this.nodeParent.get(element) == null) {
            throw new IllegalStateException();
        }
        T parent = nodeParent.remove(element);
        nodeChildren.get(parent).remove(element);
        ArrayList<T> children = nodeChildren.remove(element);
        nodeChildren.get(parent).addAll(children);
        for (T child : children) {
            nodeParent.put(child, parent);
        }
    }

    public boolean Contains(T element) {
        return this.nodeChildren.containsKey(element);
    }

    public T GetParent(T element) {
        if (!this.nodeChildren.containsKey(element)) {
            throw new IllegalArgumentException();
        }
        return this.nodeParent.get(element);
    }

    public Iterable<T> GetChildren(T element) {
        if (!this.nodeChildren.containsKey(element)) {
            throw new IllegalArgumentException();
        }
        return this.nodeChildren.get(element);
    }

    public Iterable<T> GetCommonElements(IHierarchy<T> other) {
        List<T> outputList = new ArrayList<T>();
        for (T elem : nodeChildren.keySet()) {
            if (other.Contains(elem)) {
                outputList.add(elem);
            }
        }
        return outputList;
    }

    private T findRoot() {
        for (T element : nodeParent.keySet()) {
            if (nodeParent.get(element) == null) {
                return element;
            }
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new BFSIterator();
    }

    private final class BFSIterator implements Iterator<T> {
        Deque<T> bfsOrderedNodes = new ArrayDeque<T>();

        public BFSIterator() {
            Deque<T> queue = new ArrayDeque<>();
            queue.add(findRoot());
            while (!queue.isEmpty()) {
                T node = queue.poll();
                for (T child : nodeChildren.get(node)) {
                    queue.add(child);
                }
                bfsOrderedNodes.add(node);
            }
        }

        @Override
        public boolean hasNext() {
            return !bfsOrderedNodes.isEmpty();
        }

        @Override
        public T next() {
            return bfsOrderedNodes.poll();
        }
    }
}
