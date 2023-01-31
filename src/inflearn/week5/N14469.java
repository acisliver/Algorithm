package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

// 소가 길을 건너간 이유3
// https://www.acmicpc.net/problem/14469
/*
    먼저 온 소부터 검사
 */
public class N14469 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Cow> cows = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        int curTime = 0;

        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int enterTime = input[0];
            int checkTime = input[1];
            Cow cow = new Cow(enterTime, checkTime);
            cows.offer(cow);
        }

        while (!cows.isEmpty()) {
            Cow cow = cows.poll();
            if (curTime <= cow.enterTime) {
                curTime = cow.enterTime;
            }
            curTime += cow.checkTime;
        }

        System.out.println(curTime);
    }

    static class Cow implements Comparable<Cow> {
        int enterTime;
        int checkTime;

        public Cow(int enterTime, int checkTime) {
            this.enterTime = enterTime;
            this.checkTime = checkTime;
        }

        @Override
        public int compareTo(Cow o) {
            int ascCheckTime = Integer.compare(this.checkTime, o.checkTime);
            int ascEnterTime = Integer.compare(this.enterTime, o.enterTime);

            if (ascEnterTime == 0) {
                return ascCheckTime;
            }

            return ascEnterTime;
        }
    }
}
