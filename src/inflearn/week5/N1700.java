package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// 멀티탭 스케줄링
// https://www.acmicpc.net/problem/1700
/*
    OPT: 미래에 사용하지 않는 자원을 미리 알면 사용 가능
    1. 먼저 오는 순서대로 콘센트를 꼽는다.
    2. 이미 꼽힌 전자기기가 오면 그대로 사용한다.
    3. 콘센트가 다 찼으면
    3.1 나중에 사용하지 않는 콘센트가 있다면 그 자리에 꼽는다.
    3.2 모두 나중에 사용한다면 그 중 가장 나중에 다시 사용하는 기기를 뽑는다.
 */
public class N1700 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0];
        int k = input[1];
        Set<Integer> multiTap = new HashSet<>();
        List<Integer> schedule = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        int answer = 0;

        for (int i = 0; i < k; i++) {
            int device = schedule.get(i);
            if (multiTap.contains(device)) {// 기기가 이미 꽂혀 있는 경우
                continue;
            }

            if (n > multiTap.size()) {      // 기기를 꽂을 자리가 남은 경우
                multiTap.add(device);
            } else {                        // 다른 기기를 뽑아야 하는 경우
                Set<Integer> removeCandidates = new HashSet<>(multiTap);

                for (int j = i + 1; j < k; j++) {   // 나중에 사용하지 않을 기기를 찾는다.
                    // 나중에 사용하는 기기를 후보에서 제거
                    int willUseLaterDevice = schedule.get(j);
                    removeCandidates.remove(willUseLaterDevice);
                }

                if (removeCandidates.size() > 0) {  // 나중에 사용하지 않을 기기가 있다면 제거
                    int removedDevice = removeCandidates.iterator().next();
                    multiTap.remove(removedDevice);
                } else {    // 모두 나중에 사용한다면 그 중 가장 나중에 사용하는 기기 제거
                    Map<Integer, Integer> map = new HashMap<>();

                    for (int j = i + 1; j < k; j++) {
                        int willUseLaterDevice = schedule.get(j);
                        if (!multiTap.contains(willUseLaterDevice)) {
                            continue;
                        }
                        if (!map.containsKey(willUseLaterDevice)) {
                            map.put(willUseLaterDevice, j);
                        }
                    }

                    int useLast = map.entrySet().stream()
                            .max(Map.Entry.comparingByValue())
                            .get().getKey();

                    multiTap.remove(useLast);
                }

                // 꼽혀있던 기기를 뽑고 새 기기를 꼽는다.
                answer += 1;
                multiTap.add(device);
            }

        }

        System.out.println(answer);
    }
}
