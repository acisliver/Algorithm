package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// 두 수의 합
// https://www.acmicpc.net/problem/N3273
/*
    1. 주어진 배열을 오름차순으로 정렬한다.
    2. 가장 큰 값과 가장 작은 값을 시작으로 x와 값이 같은 조합을 찾는다.
    3. 쌍의 합이 크면 오른쪽 포인터를 줄여 합을 줄인다.
    4. 쌍의 합이 작으면 왼쪽 포인터를 늘려 합을 키운다.
    5. 쌍의 합이 같으면 오른쪽 포인터를 줄여 다음 경우의 수를 구한다.
 */
public class N3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> sequence = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        int x = Integer.parseInt(br.readLine());

        Collections.sort(sequence);

        int answer = 0;
        int low = 0;
        int high = sequence.size() - 1;

        while (low < high) {
            int sum = sequence.get(low) + sequence.get(high);
            if (sum == x) {
                answer += 1;
                high -= 1;
            } else if (sum > x) {
                high -= 1;
            } else {
                low += 1;
            }
        }

        System.out.println(answer);
    }
}
