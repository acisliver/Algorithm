package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 전깃줄
// https://www.acmicpc.net/problem/2565
public class N2565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Wire> wires = new ArrayList<>();
        List<Integer> distance = new ArrayList<>();
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

            int index = Collections.binarySearch(distance, wire.to);

            if (index < 0) {
                int insertionPoint = index * - 1 - 1;

                if (insertionPoint >= distance.size()) {
                    distance.add(wire.to);
                } else {
                    distance.set(insertionPoint, wire.to);
                }
            } else {
                distance.set(index, wire.to);
            }
        }

        System.out.println(N - distance.size() + 1);
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
