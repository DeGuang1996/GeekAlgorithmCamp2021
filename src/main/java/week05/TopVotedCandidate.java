package week05;

import java.util.ArrayList;
import java.util.HashMap;

class TopVotedCandidate {

    class Vote {
        public int person;
        public int time;

        public Vote(int person, int time) {
            this.person = person;
            this.time = time;
        }
    }

    private ArrayList<ArrayList<Vote>> totalCount;

    public TopVotedCandidate(int[] persons, int[] times) {
        totalCount = new ArrayList<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < persons.length; i++) {
            int count = hashMap.getOrDefault(persons[i], 0) + 1;
            hashMap.put(persons[i], count);
            while (totalCount.size() <= count) {
                totalCount.add(new ArrayList<>());
            }
            totalCount.get(count).add(new Vote(persons[i], times[i]));
        }
    }

    public int q(int t) {
        int left = 0, right = totalCount.size() - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (totalCount.get(mid).get(0).time <= t) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        ArrayList<Vote> maxVote = totalCount.get(left);
        left = 0;
        right = maxVote.size() - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (maxVote.get(mid).time <= t) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return maxVote.get(left).person;
    }
}
