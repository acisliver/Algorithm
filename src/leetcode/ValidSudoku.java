package leetcode;

import java.util.*;

// https://leetcode.com/problems/valid-sudoku/
// HashTable
public class ValidSudoku {
    public static void main(String[] args) {
        System.out.println(new ValidSudoku().isValidSudoku(
                new char[][]{
                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                }
        ));
    }

    public boolean isValidSudoku(char[][] board) {

        Map<Integer, Set<Character>> boxMap = new HashMap<>();
        Map<Integer, Set<Character>> rowMap = new HashMap<>();
        Map<Integer, Set<Character>> colMap = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            boxMap.put(i, new HashSet<>());
            rowMap.put(i, new HashSet<>());
            colMap.put(i, new HashSet<>());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char number = board[i][j];
                if (number == '.') continue;

                Set<Character> boxSet = boxMap.get(i / 3 * 3 + j / 3);
                Set<Character> rowSet = rowMap.get(i);
                Set<Character> colSet = colMap.get(j);

                if (boxSet.contains(number)) return false;
                if (rowSet.contains(number)) return false;
                if (colSet.contains(number)) return false;

                boxSet.add(number);
                rowSet.add(number);
                colSet.add(number);
            }
        }

        return true;
    }
}
