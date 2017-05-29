package exercises.linkedQueue;

public class LinkedQueue<E> {

    private int size;
    private QueueNode head;
    private QueueNode tail;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        QueueNode oldTail = this.tail;
        this.tail = new QueueNode(element);
        this.tail.setPrevNode(oldTail);

        if(this.size() == 0){
            this.head = tail;
        } else {
            oldTail.setNextNode(this.tail);
        }
        this.size++;
    }

    public E dequeue() {
        if (this.size() == 0){
            throw new IllegalArgumentException();
        }

        QueueNode oldHead = this.head;
        this.head = this.head.getNextNode();

        size--;

        if (this.size() == 0){
            this.tail = null;
        } else {
            this.head.setPrevNode(null);
        }

        return oldHead.getValue();
    }

    public E[] toArray() {
        E[] newElementsArr = (E[]) new Object[this.size()*2];
        QueueNode crnt = this.head;
        for (int a = 0; a < size(); a++) {
            newElementsArr[a] = crnt.getValue();
            crnt = crnt.getNextNode();
        }
        return newElementsArr;
    }

    private class QueueNode {
        private E value;
        private QueueNode nextNode;
        private QueueNode prevNode;

        public QueueNode(E value) {
            this.value = value;
        }

        public E getValue() {
            return this.value;
        }

        private void setValue(E value) {
            this.value = value;
        }

        public QueueNode getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(QueueNode nextNode) {
            this.nextNode = nextNode;
        }

        public QueueNode getPrevNode() {
            return this.prevNode;
        }

        public void setPrevNode(QueueNode prevNode) {
            this.prevNode = prevNode;
        }
    }
}