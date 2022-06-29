package leetcode;

// https://leetcode.com/problems/reshape-the-matrix/
// 시뮬레이션
public class MatrixReshape {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int n = mat.length;
        int m = mat[0].length;

        if (n * m != r * c) return mat;

        int[][] answer = new int[r][c];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int nth = j + i * m;
                answer[nth / c][nth % c] = mat[i][j];
            }
        }
        return answer;
    }
}
