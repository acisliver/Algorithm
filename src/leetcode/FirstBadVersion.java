package leetcode;

// https://leetcode.com/problems/first-bad-version/submissions/
// Binary Search
public class FirstBadVersion {
    public boolean isBadVersion(int version) {
        return version > 0;
    }

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;

        while (left < right) {

            int mid = (left + right) >>> 1;
            boolean midVal = isBadVersion(mid);

            if (midVal) right = mid;
            else left = mid + 1;
        }

        return left;
    }
}
