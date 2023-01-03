package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

// 수학숙제
// https://www.acmicpc.net/problem/2870
public class N2870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<BigInteger> totalNumbers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String input = br.readLine();

            List<BigInteger> numbers = Arrays.stream(input.split("\\D"))
                    .filter(s -> !s.equals(""))
                    .map(BigInteger::new)
                    .collect(Collectors.toList());
            totalNumbers.addAll(numbers);
        }

        Collections.sort(totalNumbers);

        System.out.println(totalNumbers.stream().map(String::valueOf).collect(Collectors.joining("\n")));
    }
}
