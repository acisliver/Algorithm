package groom.week4;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            String dealType = input[0];
            int money = Integer.parseInt(input[1]);

            while (!queue.isEmpty()) {
                if (N < queue.peek()) break;

                N -= queue.poll();
            }

            switch (dealType) {
                case "deposit":
                    N += money;
                    break;
                case "pay":
                    if (N >= money) {
                        N -= money;
                    }
                    break;
                case "reservation":
                    if (queue.isEmpty() && N >= money) {
                        N -= money;
                    } else {
                        queue.add(money);
                    }
            }
        }

        while (!queue.isEmpty()) {
            if (N < queue.peek()) break;

            N -= queue.poll();
        }

        System.out.println(N);
    }
}
