package leetcode;

// https://leetcode.com/problems/search-a-2d-matrix/
// 단순 구현
public class SearchMatrix {
    public static void main(String[] args) {
        System.out.println(new SearchMatrix().searchMatrix(new int[][]{{1}}, 0));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int start = 0;

        for (int i = matrix.length - 1; i >= 0; i--) {
            if (matrix[i][0] <= target) {
                start = i;
                break;
            }
        }

        for (int i = 0; i < matrix[start].length; i++) {
            if (matrix[start][i] == target) return true;

            if (matrix[start][i] > target) return false;
        }

        return false;
    }
}
