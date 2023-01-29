package kakao.blind2023;

import java.util.*;

public class Solution5 {

    public static void main(String[] args) {
        Solution5 s = new Solution5();
        System.out.println(Arrays.toString(s.solution(new String[]{"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"})));
    }

    public String[] solution(String[] commands) {
        List<String> answer = new LinkedList<>();

        Value[][] matrix = new Value[51][51];
        Map<String, List<Value>> map = new HashMap<>();


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = new Value("");
            }
        }

        for (String command : commands) {
            String[] cArr = command.split(" ");
            switch (cArr[0]) {
                case "UPDATE":
                    if (cArr.length == 4) {
                        int r = Integer.parseInt(cArr[1]);
                        int c = Integer.parseInt(cArr[2]);
                        String value = cArr[3];
                        String originValue = matrix[r][c].value;
                        matrix[r][c].value = value;

                        if (map.containsKey(originValue))
                            map.get(originValue).remove(matrix[r][c]);

                        if (map.containsKey(value)) {
                            map.get(value).add(matrix[r][c]);
                        } else {
                            List<Value> list = new LinkedList<>();
                            list.add(matrix[r][c]);
                            map.put(value, list);
                        }

                    } else {
                        String value1 = cArr[1];
                        String value2 = cArr[2];

                        if (map.containsKey(value1)) {
                            List<Value> origin = map.get(value1);
                            for (Value value : origin) {
                                value.value = value2;
                            }
                            map.put(value2, map.remove(value1));
                        }
                    }
                    break;
                case "MERGE":
                    int r1 = Integer.parseInt(cArr[1]);
                    int c1 = Integer.parseInt(cArr[2]);
                    int r2 = Integer.parseInt(cArr[3]);
                    int c2 = Integer.parseInt(cArr[4]);

                    matrix[r2][c2] = matrix[r1][c1];
                    break;
                case "UNMERGE": {
                    int r = Integer.parseInt(cArr[1]);
                    int c = Integer.parseInt(cArr[2]);

                    // 머지된 얘들 초기화
                    Value origin = matrix[r][c];
                    for (int i = 0; i < matrix.length; i++) {
                        for (int j = 0; j < matrix.length; j++) {
                            Value cur = matrix[i][j];
                            if (cur == origin) {
                                matrix[i][j] = new Value("");
                            }
                        }
                    }
                    matrix[r][c] = origin;

                    break;
                }
                case "PRINT": {
                    int r = Integer.parseInt(cArr[1]);
                    int c = Integer.parseInt(cArr[2]);

                    answer.add(matrix[r][c].value.equals("") ? "EMPTY" : matrix[r][c].value);
                    break;
                }


            }
        }

        return answer.toArray(new String[0]);
    }

    public class Value {
        public String value;

        Value(String value) {
            this.value = value;
        }
    }
}
