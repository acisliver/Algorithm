package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.in;

// 프린터 큐
// https://www.acmicpc.net/problem/1966
public class N1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {   // 테스트 케이스만큼 반복
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int n = input[0];
            int m = input[1];
            Queue<Node> queue = new LinkedList<>();     // 프린터 큐
            Map<Integer, Integer> docByImportance = new HashMap<>();  // 중요도마다 문서의 개수
            int[] importances = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
                          // 현재 가장 높은 중요도
            for (int i = 0; i < importances.length; i++) {
                int importance = importances[i];
                docByImportance.merge(importance, 1, Integer::sum); // 중요도에 맞는 문서의 개수 1증가
                Node node = new Node(i, importance);    // 인덱스와 중요도를 프린터 큐에 넣음
                queue.add(node);
            }

            Queue<Integer> importanceList = docByImportance.keySet()    // 중요도 정렬
                    .stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toCollection(LinkedList::new));
            int highestImportance = importanceList.poll();              // 가장 높은 중요도 계산

            int th = 1;     // 몇번째 출력물인지
            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                if (cur.importance == highestImportance) {  // 프린트할 수 있는 중요도인 경우 프린트
                    if (cur.index == m) {                   // 궁금한 문서가 출력되면
                        System.out.println(th);
                        break;
                    }
                    docByImportance.merge(highestImportance, -1, Integer::sum); // 중요도에 따른 문서 개수 -1
                    if (docByImportance.get(highestImportance) == 0) {                // 중요도에 따른 문서를 다 출력하면 다음 중요도로 변경
                        highestImportance = importanceList.poll();
                    }
                    th++;
                    continue;
                }

                queue.offer(cur);   // 프린트할 수 없으므로 프린터큐 맨뒤로 이동
            }
        }
    }

    static class Node {
        int index;
        int importance;

        public Node(int index, int importance) {
            this.index = index;
            this.importance = importance;
        }
    }
}
