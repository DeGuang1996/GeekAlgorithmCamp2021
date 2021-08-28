package week10.classProblem;

class NumArray {

    // 树状数组解法
    // private int n;
    // private int[] array;
    // private int[] source;
    //
    // public NumArray(int[] nums) {
    //     n = nums.length;
    //     array = new int[n + 1];
    //     source = new int[n + 1];
    //     for (int i = 1; i <= n; i++) {
    //         source[i] = nums[i - 1];
    //         add(i, nums[i - 1]);
    //     }
    // }
    //
    // public void update(int index, int val) {
    //     index++;
    //     int delta = val - source[index];
    //     add(index, delta);
    //     source[index] = val;
    // }
    //
    // public int sumRange(int left, int right) {
    //     left++;
    //     right++;
    //     return query(right) - query(left - 1);
    // }
    //
    // private int query(int x) {
    //     int ans = 0;
    //     for (; x > 0; x -= lowBit(x)) {
    //         ans += array[x];
    //     }
    //     return ans;
    // }
    //
    // private void add(int x, int delta) {
    //     for (; x <= n; x += lowBit(x)) {
    //         array[x] += delta;
    //     }
    // }
    //
    // private int lowBit(int num) {
    //     return num & (-num);
    // }

    private SegmentTree segmentTree;

    public NumArray(int[] nums) {
        segmentTree = new SegmentTree(nums);
    }

    public void update(int index, int val) {
        segmentTree.change(1, index, val);
    }

    public int sumRange(int left, int right) {
        return segmentTree.query(1, left, right);
    }

    // 线段树解法
    static class SegmentTree {

        public SegmentTree(int[] nums) {
            n = nums.length;
            nodes = new Node[4 * n];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = new Node();
            }
            build(1, 0, n - 1, nums);
        }

        public void build(int cur, int l, int r, int[] nums) {
            nodes[cur].left = l;
            nodes[cur].right = r;
            nodes[cur].mark = 0;
            if (l == r) {
                nodes[cur].sum = nums[l];
                return;
            }
            int mid = (l + r) / 2;
            build(cur * 2, l, mid, nums);
            build(cur * 2 + 1, mid + 1, r, nums);
            nodes[cur].sum = nodes[cur * 2].sum + nodes[cur * 2 + 1].sum;
        }

        public void change(int cur, int index, int val) {
            if (nodes[cur].left == nodes[cur].right) {
                nodes[cur].sum = val;
                return;
            }
            spread(cur);
            int mid = (nodes[cur].left + nodes[cur].right) / 2;
            if (index <= mid) {
                change(cur * 2, index, val);
            } else {
                change(cur * 2 + 1, index, val);
            }
            nodes[cur].sum = nodes[cur * 2].sum + nodes[cur * 2 + 1].sum;
        }

        public void change(int cur, int l, int r, int delta) {
            if (l <= nodes[cur].left && r >= nodes[cur].right) {
                nodes[cur].sum += delta * (nodes[cur].right - nodes[cur].left + 1);
                nodes[cur].mark += delta;
                return;
            }
            spread(cur);
            int mid = (nodes[cur].left + nodes[cur].right) / 2;
            if (l <= mid) {
                change(cur * 2, l, r, delta);
            }
            if (r > mid) {
                change(cur * 2 + 1, l, r, delta);
            }
            nodes[cur].sum = nodes[cur * 2].sum + nodes[cur * 2 + 1].sum;
        }

        private void spread(int cur) {
            if (nodes[cur].mark != 0) {
                nodes[cur * 2].sum += nodes[cur].mark * (nodes[cur * 2].right - nodes[cur * 2].left + 1);
                nodes[cur * 2].mark += nodes[cur].mark;
                nodes[cur * 2 + 1].sum += nodes[cur].mark * (nodes[cur * 2 + 1].right - nodes[cur * 2 + 1].left + 1);
                nodes[cur * 2 + 1].mark += nodes[cur].mark;
                nodes[cur].mark = 0;
            }
        }

        public int query(int cur, int l, int r) {
            if (l <= nodes[cur].left && r >= nodes[cur].right) {
                return nodes[cur].sum;
            }
            spread(cur);
            int mid = (nodes[cur].left + nodes[cur].right) / 2;
            int ans = 0;
            if (l <= mid) {
                ans += query(cur * 2, l, r);
            }
            if (r > mid) {
                ans += query(cur * 2 + 1, l, r);
            }
            return ans;
        }

        static class Node {
            private int left;
            private int right;
            private int sum;
            private int mark;
        }

        private int n;
        private Node[] nodes;
    }
}
