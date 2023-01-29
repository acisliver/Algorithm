package kakao.blind2021;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution5 {
    public String solution(String play_time, String adv_time, String[] logs) {
        // 동영상 시간과 광고 시간이 같은 경우
        if (play_time.equals(adv_time)) return "00:00:00";

        String answer = "";

        int play_seconds = toSeconds(play_time);
        int adv_seconds = toSeconds(adv_time);
        List<Log> logs_list = new LinkedList<>();

        for (String log : logs) {
            String time[] = log.split("-");
            String start_time = time[0];
            String end_time = time[1];

            logs_list.add(new Log(toSeconds(start_time), toSeconds(end_time)));
        }

        logs_list.sort(Log::compareTo);

        // 00:00:00 삽입 시



        for (Log log : logs_list) {

        }

        return answer;
    }

    private int toSeconds(String time) {
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        int seconds = Integer.parseInt(times[0]);

        seconds += minute * 60;
        seconds += hour * 60 * 60;

        return seconds;
    }

    private class Log implements Comparable<Log>{
        public int start_seconds;
        public int end_seconds;

        Log(int start_seconds, int end_seconds) {
            this.start_seconds = start_seconds;
            this.end_seconds = end_seconds;
        }

        @Override
        public int compareTo(Log o) {
            return this.start_seconds - o.start_seconds;
        }
    }
}
