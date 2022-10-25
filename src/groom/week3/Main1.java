package groom.week3;

import java.io.*;
import java.util.Arrays;

public class Main1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(
                Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .sum()
        );
    }
}
