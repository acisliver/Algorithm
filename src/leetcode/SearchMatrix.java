package leetcode;

// https://leetcode.com/problems/search-a-2d-matrix/
// Binary Search
public class SearchMatrix {
    public static void main(String[] args) {
        System.out.println(new SearchMatrix().searchMatrix(new int[][]{{1}}, 0));
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        int left = 0;
        int right = matrix.length * matrix[0].length - 1;

        while(left <= right) {
            int mid = (left + right) >>> 1;
            int row = mid / matrix[0].length;
            int col = mid % matrix[0].length;
            int midVal = matrix[row][col];


            if (midVal < target) {
                left = mid + 1;
            } else if (midVal > target){
                right = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
