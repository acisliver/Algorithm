package inflearn.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 드래곤 앤 던전
// https://www.acmicpc.net/problem/16434
public class N16434 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int roomCount = input[0];
        int hATK = input[1];
        int[][] roomInfos = new int[roomCount][3];

        for (int i = 0; i < roomCount; i++) {
            roomInfos[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        long lo = 0;
        long hi = 100;

        while (lo + 1 < hi) {
            long mid = (hi + lo) >>> 1;

            if (check(mid, hATK, roomInfos)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        System.out.println(hi);
    }

    private static boolean check(long health, long atk, int[][] roomInfos) {
        long maxHealth = health;

        for (int[] roomInfo : roomInfos) {
            int type = roomInfo[0];
            int a = roomInfo[1];
            int h = roomInfo[2];
            if (type == 1) {
                long div = h / atk + (h % atk > 0 ? 1 : 0);
                long atkCount = div - 1;
                health -= atkCount * a;
            } else {
                atk += a;
                health = Math.min(maxHealth, health + h);
            }
            if (health <= 0) return false;
        }

        return true;
    }
}
