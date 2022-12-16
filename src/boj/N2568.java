package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 전깃줄 - 2
// https://www.acmicpc.net/problem/2568
public class N2568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Wire> wires = new ArrayList<>();
        List<Integer> distance = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        distance.add(0);

        for (int i = 0; i < N; i++) {
            int[] inputWire = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Wire wire = new Wire(inputWire[0], inputWire[1]);
            wires.add(wire);
        }

        Collections.sort(wires);

        for (Wire wire : wires) {

            if (wire.to > distance.get(distance.size() - 1)) {
                distance.add(wire.to);
                indexes.add(distance.size() - 1);
            } else {
                int index = Collections.binarySearch(distance, wire.to);

                if (index < 0) {
                    int insertionPoint = index * -1 - 1;
                    distance.set(insertionPoint, wire.to);
                    indexes.add(insertionPoint);
                } else {
                    distance.set(index, wire.to);
                    indexes.add(index);
                }
            }
        }

        int lisSize = distance.size()  - 1;
        sb.append(N - lisSize).append("\n");

        Stack<Integer> stack = new Stack<>();
        for (int i = indexes.size() - 1; i >= 0; i--) {
            if (indexes.get(i) == lisSize) {
                lisSize -= 1;
            } else {
                stack.push(wires.get(i).from);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append("\n");
        }
        System.out.println(sb);
    }

    static class Wire implements Comparable<Wire> {

        int from;
        int to;

        Wire(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Wire o) {
            return Integer.compare(this.from, o.from);
        }
    }
}
