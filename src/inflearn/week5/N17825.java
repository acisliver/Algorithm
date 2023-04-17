package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 주사위 윷놀이
// https://www.acmicpc.net/problem/17825
public class N17825 {

    static Node START = new Node(0);

    static {
        Node n40 = new Node(40);
        n40.next = new Node(0);

        Node n25 = new Node(25);
        n25.next = new Node(30);
        n25.next.next = new Node(35);
        n25.next.next.next = n40;

        Node n10 = new Node(10);
        n10.fast = new Node(13);
        n10.fast.next = new Node(16);
        n10.fast.next.next = new Node(19);
        n10.fast.next.next.next = n25;

        Node n20 = new Node(20);
        n20.fast = new Node(22);
        n20.fast.next = new Node(24);
        n20.fast.next.next = n25;

        Node n30 = new Node(30);
        n30.fast = new Node(28);
        n30.fast.next = new Node(27);
        n30.fast.next.next = new Node(26);
        n30.fast.next.next.next = n25;

        Node cur = START;
        for (int i = 2; i <= 40; i += 2) {
            Node next;
            switch (i) {
                case 10: {
                    next = n10;
                    break;
                } case 20: {
                    next = n20;
                    break;
                } case 25: {
                    next = n25;
                    break;
                } case 30: {
                    next = n30;
                    break;
                } case 40 : {
                    next = n40;
                    break;
                } default: {
                    next = new Node(i);
                    break;
                }
            }
            cur.next = next;
            cur = next;
        }
    }

    static int[] DICE;
    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DICE = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Node> mals = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Node node = new Node(0);
            node.next = START.next;
            mals.add(node);
        }

        search(mals, 0, 0);

        System.out.println(ANSWER);
    }

    private static void search(List<Node> mals, int idx, int score) {

        if (idx == DICE.length) {
            ANSWER = Math.max(ANSWER, score);
            return;
        }

        int dice = DICE[idx];
        for (int i = 0; i < mals.size(); i++) {
            Node cur = mals.get(i);
            Node next = cur;
            int move = dice;

            // 이미 도착한 말
            if (next.next == null) continue;

            // 파란색 칸에서 이동 시작
            if (next.fast != null) {
                next = next.fast;
                move -= 1;
            }

            // 나머지 이동
            while (move-- > 0) {
                // 다음 칸이 도착칸이므로 이동 종료
                if (next.value == 40) {
                    next = new Node(0);
                    break;
                }
                next = next.next;
            }

            // 이동을 완료한 칸에 다른 말이 있다면 이동 불가
            if (mals.contains(next)) continue;

            mals.set(i, next);
            search(mals, idx + 1, score + next.value);
            mals.set(i, cur);
        }
    }

    static class Node {

        int value;
        Node next;
        Node fast;

        public Node(int value) {
            this.value = value;
        }
    }
}
