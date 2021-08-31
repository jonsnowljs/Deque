package queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        queue = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (size == queue.length) {
            resize(2 * queue.length);
        }
        queue[size] = item;
        size++;
    }

    private void resize(int capacity) {
        Item[] newList =(Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newList[i] = queue[i];
        }
        queue = newList;

    }

    // remove and return a random item
    public Item dequeue() {
        int randomNum = StdRandom.uniform(0, size);
        Item item = queue[randomNum];
        queue[randomNum] = queue[size - 1];
        queue[size - 1] = null;
        if (size == queue.length/4) {
            resize(queue.length / 2);
        }
        size--;
        return item;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        int randomNum = StdRandom.uniform(0, size);
        return queue[randomNum];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private Item q;
        private Item[] items;
        private int current;

        public RandomIterator() {
            this.items = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                items[i] = queue[i];
            }
            StdRandom.shuffle(items);
            current = 0;
        }

        public boolean hasNext() {
            return current <= size - 1;
        }

        public Item next() {
            int currentInt = current;
            current++;
            return queue[currentInt];
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
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue();
        randomizedQueue.enqueue(12);
        randomizedQueue.enqueue(16);
        randomizedQueue.enqueue(78);
        randomizedQueue.enqueue(18);
        randomizedQueue.enqueue(46);
        randomizedQueue.enqueue(23);
        randomizedQueue.enqueue(566);
        randomizedQueue.enqueue(169);
        randomizedQueue.enqueue(7899);
        randomizedQueue.enqueue(1118);
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();

        randomizedQueue.sample();
        randomizedQueue.size();

    }


}