package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// List of Unique Number
// https://www.acmicpc.net/problem/13144
/*
    Two Pointer
    1. Set 자료구조를 활용
    2. 두 포인터의 시작점은 0
    3. high를 늘려가며 범위를 늘린다.
    4. 만약 Set에 high 위치의 값이 들어있다면 low 위치의 값을 Set에서 제거한다.
        즉, 범위를 줄인다.
    5. 만약 Set에 high 위치의 값이 없다면 그 값을 Set에 넣고 Set의 크기만큼 정답에 더해준다.
        -> Set의 크기가 경우의 수이다.
    주의) 만약 100,000개의 숫자가 모두 다르면 시그마 1~100,000이 된다. 이는 int자료형을
         벗어나므로 answer를 long으로 선언해주자
 */
public class N13144 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int low = 0;
        int high = 0;
        long answer = 0;
        Set<Integer> set = new HashSet<>();

        while (high < arr.length) {
            if (set.contains(arr[high])) {
                set.remove(arr[low]);
                low += 1;
            } else {
                set.add(arr[high]);
                answer += set.size();
                high += 1;
            }
        }

        System.out.println(answer);
    }
}
