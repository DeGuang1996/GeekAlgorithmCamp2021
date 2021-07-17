package week04;

import week01.ListNode;

import java.util.PriorityQueue;

public class MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((term1, term2) -> term1.val - term2.val);
        for (ListNode listNode : lists) {
            if (listNode != null) {
                priorityQueue.offer(listNode);
            }
        }
        ListNode dummy = new ListNode();
        ListNode head = dummy;
        while (!priorityQueue.isEmpty()) {
            ListNode listNode = priorityQueue.poll();
            head.next = listNode;
            head = head.next;
            ListNode next = listNode.next;
            if (next != null) {
                priorityQueue.offer(next);
            }
        }
        return dummy.next;
    }
}
