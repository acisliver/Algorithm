package programers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class N138476 {
    public static void main(String[] args) {
        N138476 n = new N138476();
        System.out.println(n.solution(6, new int[]{1, 3, 2, 5, 4, 5, 2, 3}));
    }

    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> tangerinesFromSize = initializeMap(tangerine);

        List<Map.Entry<Integer, Integer>> list = tangerinesFromSize.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((o1, o2) -> o2 - o1))
                .collect(Collectors.toList());

        while (k > 0) {
            answer += 1;
            k -= list.remove(0).getValue();
        }

        return answer;
    }

    private Map<Integer, Integer> initializeMap(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : array) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        return Collections.unmodifiableMap(map);
    }

}
