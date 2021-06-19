package week01;

import java.util.ArrayList;

public class MyCircularDeque {

    private final int capacity;
    private ArrayList<Integer> list;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        capacity = k;
        list = new ArrayList<>(k);
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (list.size() > capacity) {
            return false;
        }
        list.add(0, value);
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (list.size() > capacity) {
            return false;
        }
        list.add(value);
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (list.isEmpty()) {
            return false;
        }
        list.remove(0);
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (list.isEmpty()) {
            return false;
        }
        list.remove(list.size() - 1);
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (list.isEmpty()) {
            return -1;
        }
        return list.get(0);
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (list.isEmpty()) {
            return -1;
        }
        return list.get(list.size() - 1);
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return list.size() == capacity;
    }
}
