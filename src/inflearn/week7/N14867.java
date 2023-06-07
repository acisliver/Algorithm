package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

// 물통
// https://www.acmicpc.net/problem/14867
public class N14867 {

    static final int INF = 123456789;
    static int A, B, DESIRE_A, DESIRE_B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        A = input[0];
        B = input[1];
        DESIRE_A = input[2];
        DESIRE_B = input[3];

        int answer = bfs(0, 0);
        System.out.println(answer);
    }

    static Queue<State> queue = new LinkedList<>();
    static Map<State, Integer> visited = new HashMap<>();

    private static int bfs(int a, int b) {

        State startState = new State(a, b);
        queue.add(startState);
        visited.put(startState, 0);

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            int d = visited.get(cur);

            // fill
            enqueue(new State(A, cur.b), d);
            enqueue(new State(cur.a, B), d);

            // empty
            enqueue(new State(0, cur.b), d);
            enqueue(new State(cur.a, 0), d);

            // move a to b
            if (cur.a + cur.b > B) {
                enqueue(new State(cur.a - (B - cur.b), B), d);
            } else {
                enqueue(new State(0, cur.a + cur.b), d);
            }

            // move b to a
            if (cur.a + cur.b > A) {
                enqueue(new State(A, cur.b - (A - cur.a)), d);
            } else {
                enqueue(new State(cur.a + cur.b, 0), d);
            }

        }

        State desiredState = new State(DESIRE_A, DESIRE_B);
        if (visited.containsKey(desiredState)) {
            return visited.get(desiredState);
        }
        return -1;
    }

    private static void enqueue(State state, int d) {
        if (visited.containsKey(state)) return;
        queue.add(state);
        visited.put(state, d + 1);
    }

    // 버전이 맞다면 record 사용이 적합
    private static final class State {
        private final int a;
        private final int b;

        private State(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (State) obj;
            return this.a == that.a &&
                    this.b == that.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }
}
