package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first;
    private Node last;

    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        if (size > 0) return false;
        else return true;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) { throw new IllegalArgumentException(); }

        Node newFirst = new Node(item, null, null);
        if (isEmpty()) {
            first = newFirst;
            last = newFirst;
        }
        else {
            newFirst.next = first;
            first.before = newFirst;
            first = newFirst;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) { throw new IllegalArgumentException(); }
        Node newLast = new Node(item, null, null);
        if (isEmpty()) {
            first = newLast;
            last = newLast;
        }
        else {
            newLast.before = last;
            last.next = newLast;
            last = newLast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            Item item = first.name;
            first.next = null;
            first = first.next;

            size--;
            return item;
        }

    }

    // remove and return the item from the back
    public Item removeLast() {
        if  (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            Item item = last.name;
            last.before = null;
            last = last.before;


            size--;
            return item;
        }

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            Item item = current.name;
            current = current.next;
            return item;
        }
    }

    private class Node {
        private Item name;
        private Node next;
        private Node before;

        public Node(Item name, Node next, Node before) {
            this.name = name;
            this.next = next;
            this.before = before;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
       Deque<Integer> deque = new Deque();
       deque.addFirst(5);
       deque.addFirst(15);
       deque.addLast(6);
       deque.addLast(192);
       deque.addFirst(5);
       deque.addFirst(15);

       deque.removeFirst();
       deque.removeLast();
       System.out.print(deque.size);
       for (int name: deque) {
           System.out.print(name);
       }


    }



}