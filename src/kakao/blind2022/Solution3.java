package kakao.blind2022;

import java.util.*;

class Solution3 {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        Map<Integer, Integer> inputMap = new HashMap<>();
        Map<Integer, Integer> timeMap = new HashMap<>();

        // 1. 들어오는 차 시간 기록
        // 2. 나가는 경우 들어온 기록으로 시간 계산 후 초기화
        for (String record : records) {
            String[] split = record.split(" ");
            String[] clock = split[0].split(":");
            int time = Integer.parseInt(clock[0]) * 60 + Integer.parseInt(clock[1]);
            int number = Integer.parseInt(split[1]);
            String inOut = split[2];

            if (inOut.equals("IN")) {
                inputMap.put(number, time);
            } else {
                timeMap.put(
                        number,
                        timeMap.getOrDefault(number, 0) + time - inputMap.get(number)
                );
                inputMap.remove(number);
            }
        }

        // 3. 나가는 경우가 없는 경우 계산
        int outLimit = 23 * 60 + 59;
        for (Map.Entry<Integer, Integer> entry : inputMap.entrySet()) {
            timeMap.put(
                    entry.getKey(),
                    timeMap.getOrDefault(entry.getKey(), 0) + outLimit - inputMap.get(entry.getKey())
            );
        }

        // 4. 주차요금 계산
        int basicTime = fees[0];
        int basicFee = fees[1];
        int chunkTime = fees[2];
        int chunkFee = fees[3];

        // 5. 차량 번호가 작은 자동차부터 요금 출력
        return timeMap.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .mapToInt(Map.Entry::getValue)
                .map(time -> {
                    if (time < basicTime) {
                        return basicFee;
                    } else {
                        return (int) (basicFee + Math.ceil((float) (time - basicTime) / chunkTime) * chunkFee);
                    }
                }).toArray();
    }
}
