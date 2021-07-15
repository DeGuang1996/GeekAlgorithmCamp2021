package week04;

import java.util.PriorityQueue;

public class MedianFinder {

    private PriorityQueue<Integer> queueLower;
    private PriorityQueue<Integer> queueHigher;

    /** initialize your data structure here. */
    public MedianFinder() {
        queueLower = new PriorityQueue<>((o1, o2) -> o2 - o1);
        queueHigher = new PriorityQueue<>();
    }

    public void addNum(int num) {
        queueLower.offer(num);
        queueHigher.offer(queueLower.poll());
        while (queueLower.size() < queueHigher.size()) {
            queueLower.offer(queueHigher.poll());
        }
    }

    public double findMedian() {
        if (queueLower.size() + queueHigher.size() == 0) {
            return 0;
        }
        if ((queueLower.size() + queueHigher.size()) % 2 == 1) {
            return queueLower.peek();
        } else {
            return (double) (queueLower.peek() + queueHigher.peek()) / 2;
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.addNum(3);
        medianFinder.findMedian();
    }
}
