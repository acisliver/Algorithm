package programers;

import java.util.Arrays;

// 테이블 해시 함수
// https://school.programmers.co.kr/learn/courses/30/lessons/147354
public class N147354 {
    public static void main(String[] args) {
        N147354 s = new N147354();
        int answer = s.solution(new int[][]{{2,2,6},{1,5,10},{4,2,9},{3,8,3}}, 2, 2, 3);
        System.out.println(answer);
    }

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int hash = 0;

        Arrays.sort(data, (data1, data2) -> {
            int compareResult = Integer.compare(data1[col - 1], data2[col - 1]);
            if (compareResult == 0) {
                return Integer.compare(data2[0], data1[0]);
            }
            return compareResult;
        });

        for (int i = row_begin - 1; i < row_end; i++) {
            int mod = 0;
            for (int j = 0; j < data[i].length; j++) {
                mod += data[i][j] % (i + 1);
            }
            hash ^= mod;
        }

        return hash;
    }
}
