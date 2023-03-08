package kakao.blind2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 표현 가능한 이진트리
// https://school.programmers.co.kr/learn/courses/30/lessons/150367
class Solution4 {
    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        System.out.println(Arrays.toString(solution4.solution(new long[]{423})));
    }

    public int[] solution(long[] numbers) {
        List<Integer> answer = new ArrayList<>();

        for (long number : numbers) {
            if (isBinaryTree(number)) {
                answer.add(1);
            } else {
                answer.add(0);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean isBinaryTree(long number) {
        String binary = Long.toBinaryString(number);
        String fullBinary = getFullBinary(binary);
        int len = fullBinary.length();

        int root = len / 2;

        if (fullBinary.charAt(root) == '0') {
            return false;
        }

        return isBinaryTree(fullBinary.substring(0, root)) && isBinaryTree(fullBinary.substring(root + 1));
    }

    private String getFullBinary(String binary) {

        int length = binary.length();
        int nodeCount = 1;
        int level = 1;
        while (length > nodeCount) {
            level *= 2;
            nodeCount += level;
        }

        int offset = nodeCount - length;
        return "0".repeat(offset) + binary;
    }

    private boolean isBinaryTree(String binary) {
        int len = binary.length();
        if (binary.length() == 0) return true;

        int root = len / 2;

        if (binary.charAt(root) == '0') {
            return isZeroTree(binary.substring(0, root)) && isZeroTree(binary.substring(root + 1));
        }

        return isBinaryTree(binary.substring(0, root)) && isBinaryTree(binary.substring(root + 1));
    }

    private boolean isZeroTree(String binary) {
        int len = binary.length();
        if (binary.length() == 0) return true;

        int root = len / 2;

        if (binary.charAt(root) == '1') {
            return false;
        }

        return isZeroTree(binary.substring(0, root)) && isZeroTree(binary.substring(root + 1));
    }
}
