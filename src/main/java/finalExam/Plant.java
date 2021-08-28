package finalExam;

import java.util.*;

public class Plant {

    private static final Long MOD = (long) 1e10;

    static class Point implements Comparable<Point> {
        long x;
        long y;

        @Override
        public int compareTo(Point o) {
            if (o.x + o.y == this.x + this.y) {
                return (int) (this.x - o.x);
            }
            return (int) (this.x + this.y - o.x - o.y);
        }
    }

    static class Count implements Comparable<Count> {
        long key;
        long count;

        @Override
        public int compareTo(Count o) {
            if (this.count == o.count) {
                return (int) (o.key - this.key);
            }
            return (int) (o.count - this.count);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Point[] mouse = new Point[n];
        for (int i = 0; i < n; i++) {
            mouse[i] = new Point();
            mouse[i].x = sc.nextLong();
            mouse[i].y = sc.nextLong();
        }
        Point[] plant = new Point[m];
        for (int i = 0; i < m; i++) {
            plant[i] = new Point();
            plant[i].x = sc.nextLong();
            plant[i].y = sc.nextLong();
        }

        HashSet<Long> hashSet = new HashSet<>();
        for (Point point : plant) {
            hashSet.add(point.x + point.y * MOD);
        }

        HashMap<Long, Count> allPoint = new HashMap<>();
        TreeSet<Count> ans = new TreeSet<>();
        for (Point point : mouse) {
            long key = point.x + point.y * MOD;
            if (hashSet.contains(key)) {
                continue;
            }
            Count count = allPoint.getOrDefault(key, new Count());
            count.key = key;
            count.count = count.count + 1;

            allPoint.remove(key);
            ans.remove(count);

            allPoint.put(key, count);
            ans.add(count);
        }

        List<Point> pointList = new ArrayList<>();
        long maxCount = ans.first().count;
        for (Count count : ans) {
            if (count.count == maxCount) {
                Point point = new Point();
                point.x = count.key % MOD;
                point.y = count.key / MOD;
                pointList.add(point);
            }
        }
        Collections.sort(pointList);
        System.out.println(pointList.get(0).x + " " + pointList.get(0).y);
    }
}
