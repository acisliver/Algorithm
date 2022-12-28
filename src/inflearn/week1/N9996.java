package inflearn.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 한국이 그리울 땐 서버에 접속하지
// https://www.acmicpc.net/problem/9996
public class N9996 {

    private static final String YES = "DA";
    private static final String NO = "NE";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String pattern = br.readLine();
        String[] patternTokens = pattern.split("\\*");
        String prefix = patternTokens[0];
        String suffix = patternTokens[1];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String fileName = br.readLine();

            if (fileName.length() < prefix.length() + suffix.length()) {
                sb.append(NO).append("\n");
                continue;
            }

            if (fileName.startsWith(prefix) && fileName.endsWith(suffix)) {
                sb.append(YES).append("\n");
            } else {
                sb.append(NO).append("\n");
            }
        }

        System.out.println(sb);
    }
}
