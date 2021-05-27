public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        private T item;
        private Node next;
        private Node previous;

        private Node(T value, Node n, Node p) {
            item = value;
            next = n;
            previous = p;
        }
    }

    private Node frontSentinel;
    private Node backSentinel;
    private int size = 0;

    public LinkedListDeque() {
        frontSentinel = new Node(null, null, null);
        backSentinel = new Node(null, null, frontSentinel);
        frontSentinel.next = backSentinel;
    }

    @Override
    public void addFirst(T item) {
        frontSentinel.next.previous = new Node(item, frontSentinel.next, frontSentinel);
        frontSentinel.next = frontSentinel.next.previous;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        backSentinel.previous.next = new Node(item, backSentinel, backSentinel.previous);
        backSentinel.previous = backSentinel.previous.next;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node p = frontSentinel.next;
        while(p != backSentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    @Override
    public T removeFirst() {
        if(size != 0) {
            T value = frontSentinel.next.item;
            frontSentinel.next = frontSentinel.next.next;
            frontSentinel.next.previous = frontSentinel;
            size -= 1;
            return value;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if(size != 0) {
            T value = backSentinel.previous.item;
            backSentinel.previous = backSentinel.previous.previous;
            backSentinel.previous.next = backSentinel;
            size -= 1;
            return value;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if(index >= size) {
            return null;
        }

        Node p = frontSentinel.next;
        for(int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    private T getRecursive(int index, Node p) {
        if(index == 0) {
            return p.item;
        }
        return getRecursive(index - 1, p.next);
    }

    public T getRecursive(int index) {
        if(index >= size) {
            return null;
        }
        return getRecursive(index, frontSentinel.next);
    }
}
