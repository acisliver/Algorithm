package leetcode;

import java.util.LinkedList;
import java.util.List;

// Valid Parenthesis String
public class N678 {
    public boolean checkValidString(String s) {
        List<Character> list = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '*') {
                list.add(c);
            } else {
                int idxO = list.lastIndexOf('(');
                int idxA = list.lastIndexOf('*');
                if (idxO < 0 && idxA < 0) {
                    return false;
                }
                if (idxO < 0) {
                    list.remove(idxA);
                }
                if (idxA < 0) {
                    list.remove(idxO);
                }
            }
        }


        return list.isEmpty();
    }
}
