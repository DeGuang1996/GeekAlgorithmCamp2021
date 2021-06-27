package week02;

import week01.ListNode;

public class MergeKLists {

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0, null);
        ListNode head = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }
        if (l1 != null) {
            head.next = l1;
        } else {
            head.next = l2;
        }
        return dummy.next;
    }

    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l ,mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        // if (lists.length == 0) {
        //     return null;
        // }
        // int k = lists.length;
        // while (k > 1) {
        //     int cur = (k + 1) / 2;
        //     for (int i = cur; i < k; i++) {
        //         lists[i - cur] = mergeTwoLists(lists[i - cur], lists[i]);
        //     }
        //     k = cur;
        // }
        // return lists[0];
        return merge(lists, 0, lists.length - 1);
    }
}
