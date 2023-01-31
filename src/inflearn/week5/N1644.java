package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 소수의 연속합
// https://www.acmicpc.net/problem/1644
/*
    1. n 이하의 소수를 구한다.
    2. 0부터 시작하는 포인터 2개를 만든다.
    3. 포인터 사이의 값을 계산해 n과 같은 값일 경우 정답을 더해준다.
    4. n보다 작은 합이 나오면 오른쪽 포인터를 오른쪽으로 옮겨 범위를 늘린다.
    5. n보다 큰 합이 나오면 왼쪽 포인터를 왼쪽으로 옮겨 범위를 좁힌다.
 */
public class N1644 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int answer = 0;
        boolean[] primeCheck = new boolean[n + 1];
        List<Integer> primeNumbers = new ArrayList<>();

        for (int i = 2; i < primeCheck.length; i++) {
            if (primeCheck[i]) continue;
            primeNumbers.add(i);
            for (int j = 2; i * j < primeCheck.length; j++) {
                primeCheck[i * j] = true;
            }
        }

        if (primeNumbers.isEmpty()) {
            System.out.println(answer);
            return;
        }

        int p1 = 0;
        int p2 = 0;
        int temp = primeNumbers.get(p1);

        while (p1 < primeNumbers.size()) {

            if (temp == n) {
                answer += 1;
                p2++;
                if (p2 >= primeNumbers.size()) break;
                temp += primeNumbers.get(p2);
            } else if (temp < n) {
                p2++;
                if (p2 >= primeNumbers.size()) break;
                temp += primeNumbers.get(p2);
            } else {
                temp -= primeNumbers.get(p1);
                p1++;
            }
        }

        System.out.println(answer);
    }


}
