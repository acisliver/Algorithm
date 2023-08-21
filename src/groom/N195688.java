package groom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// 문자열 나누기
// https://level.goorm.io/exam/195688/%EB%AC%B8%EC%9E%90%EC%97%B4-%EB%82%98%EB%88%84%EA%B8%B0/quiz/1
public class N195688 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> S = List.of(br.readLine().split(""));
        List<List<String>> subLists = new ArrayList<>();

        for (int i = 1; i < S.size() - 1; i++) {
            for (int j = i + 1; j < S.size(); j++) {
                String prefix = String.join("", S.subList(0, i));
                String midfix = String.join("", S.subList(i, j));
                String suffix = String.join("", S.subList(j, S.size()));
                List<String> subList = List.of(prefix, midfix, suffix);
                subLists.add(subList);
            }
        }

        List<String> subList = subLists.stream()
                .flatMap(Collection::stream)
                .distinct()
                .sorted()
                .toList();

        int max = subLists.stream()
                .mapToInt(strings -> {
                    int score = 0;
                    for (String string : strings) {
                        score += subList.indexOf(string) + 1;
                    }
                    return score;
                })
                .max()
                .orElse(0);

        System.out.println(subLists);
        System.out.println(max);
    }
}
