package inflearn.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 가르침
// https://www.acmicpc.net/problem/1062
/*
    1. 단어를 비트마스크 형태로 저장한다.
    2. anta와 tica로 끝나는 단어만 존재하므로 a, c, i, n, t는 무조건 알아야 한다.
    3. 그 외의 글자는 배울지 말지 선택한다.
    4. 배운 글자로 읽을 수 있는 글자의 수를 센다.
 */
public class N1062 {

    static int N, K;
    static int[] WORDS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        K = input[1];
        WORDS = new int[N];

        for (int i = 0; i < N; i++) {
            char[] word = br.readLine().toCharArray();
            for (char c : word) {
                WORDS[i] |= (1 << (c - 'a'));
            }
        }

        int answer = chooseAlphabets(0, K, 0);

        System.out.println(answer);
    }

    private static int chooseAlphabets(int alphabet, int k, int mask) {
        if (k < 0) return 0;
        if (alphabet == 26) return countReadableWords(mask);

        int result = chooseAlphabets(alphabet + 1, k - 1, mask | (1 << alphabet));
        if (alphabet != 'a' - 'a' && alphabet != 'n' - 'a' && alphabet != 't' - 'a' && alphabet != 'i' - 'a' && alphabet != 'c' - 'a') {
            result = Math.max(result, chooseAlphabets(alphabet + 1, k, mask));
        }

        return result;
    }

    private static int countReadableWords(int mask) {
        int count = 0;

        for (int word : WORDS) {
            if ((word & mask) == word) count += 1;
        }

        return count;
    }
}
