package week04;

import java.util.*;

class Twitter {

    class NewTwitter implements Comparable<NewTwitter> {
        int time;
        int tweetId;

        public NewTwitter(int time, int tweetId) {
            this.time = time;
            this.tweetId = tweetId;
        }

        @Override
        public int compareTo(NewTwitter newTwitter) {
            return newTwitter.time - this.time;
        }
    }

    private int curTime;
    private int maxPosts;
    private HashMap<Integer, TreeSet<NewTwitter>> allTweets;
    private HashMap<Integer, TreeSet<Integer>> followers;

    /** Initialize your data structure here. */
    public Twitter() {
        curTime = 1;
        maxPosts = 10;
        allTweets = new HashMap<>();
        followers = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        NewTwitter newTwitter = new NewTwitter(curTime++, tweetId);
        TreeSet<NewTwitter> twitters = allTweets.getOrDefault(userId, new TreeSet<>());
        twitters.add(newTwitter);
        allTweets.put(userId, twitters);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<NewTwitter> priorityQueue = new PriorityQueue<>(maxPosts, (term1, term2) -> term2.time - term1.time);
        TreeSet<Integer> follow = followers.getOrDefault(userId, new TreeSet<>());

        for (int followeeId : follow) {
            TreeSet<NewTwitter> followeeTwitters = allTweets.getOrDefault(followeeId, new TreeSet<>());
            int i = 0;
            for (NewTwitter newTwitter : followeeTwitters) {
                priorityQueue.offer(newTwitter);
                i++;
                if (i >= maxPosts) {
                    break;
                }
            }
        }
        TreeSet<NewTwitter> selfTwitters = allTweets.getOrDefault(userId, new TreeSet<>());
        int i = 0;
        for (NewTwitter newTwitter : selfTwitters) {
            priorityQueue.offer(newTwitter);
            i++;
            if (i >= maxPosts) {
                break;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int j = 0; j < maxPosts && !priorityQueue.isEmpty(); j++) {
            ans.add(priorityQueue.peek().tweetId);
            priorityQueue.poll();
        }
        return ans;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        TreeSet<Integer> follow = followers.getOrDefault(followerId, new TreeSet<>());
        follow.add(followeeId);
        followers.put(followerId, follow);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        TreeSet<Integer> follow = followers.getOrDefault(followerId, new TreeSet<>());
        follow.remove(followeeId);
        followers.put(followerId, follow);
    }
}
