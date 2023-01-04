package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

// NBA 농구
// https://www.acmicpc.net/problem/2852
public class N2852 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int goals = Integer.parseInt(br.readLine());
        int score = 0;
        Duration duration1 = Duration.ZERO;
        Duration duration2 = Duration.ZERO;
        Duration prevTime = Duration.ZERO;
        Duration endTime = Duration.parse("PT48M");

        for (int i = 0; i < goals; i++) {
            String[] goalInfo = br.readLine().split(" ");
            int team = Integer.parseInt(goalInfo[0]);
            String[] time = goalInfo[1].split(":");
            int minutes = Integer.parseInt(time[0]);
            int seconds = Integer.parseInt(time[1]);
            Duration scoreTime = Duration.of(minutes * 60L + seconds, ChronoUnit.SECONDS);

            Duration offset = scoreTime.minus(prevTime);

            prevTime = scoreTime;

            if (score > 0) {
                duration1 = duration1.plus(offset);
            } else if (score < 0) {
                duration2 = duration2.plus(offset);
            }

            if (team == 1) {
                score += 1;
            } else {
                score -= 1;
            }
        }

        Duration offset = endTime.minus(prevTime);
        if (score > 0) {
            duration1 = duration1.plus(offset);
        } else if (score < 0) {
            duration2 = duration2.plus(offset);
        }

        System.out.println(formatDuration(duration1));
        System.out.println(formatDuration(duration2));
    }

    private static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();

        long mm = (seconds % 3600) / 60;
        long ss = seconds % 60;

        return String.format("%02d:%02d", mm, ss);
    }
}
