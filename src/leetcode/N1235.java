package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class N1235 {

    int n;

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobs = new ArrayList<>();
        n = startTime.length;
        int[] dp = new int[startTime.length + 1];
        Arrays.fill(dp, -1);

        for (int i = 0; i < startTime.length; i++) {
            Job job = new Job(startTime[i], endTime[i], profit[i]);
            jobs.add(job);
        }

        Collections.sort(jobs);

        return solve(0, jobs, dp);
    }

    private int getNext(int i, List<Job> jobs) {
        int target = jobs.get(i).endTime;
        int lo = i - 1;
        int hi = n;
        while (lo + 1 < hi) {
            int mid = (lo + hi) >>> 1;
            Job midVal = jobs.get(mid);

            if (midVal.startTime < target) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

    private int solve(int i, List<Job> jobs, int[] dp) {
        if (i >= jobs.size()) {
            return 0;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        Job cur = jobs.get(i);

        int pick = cur.profit + solve(getNext(i, jobs), jobs, dp);
        int skip = solve(i + 1, jobs, dp);

        return dp[i] = Math.max(pick, skip);
    }

    static class Job implements Comparable<Job> {

        int startTime;
        int endTime;
        int profit;

        public Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }

        @Override
        public int compareTo(Job o) {
            return Integer.compare(this.startTime, o.startTime);
        }
    }
}
