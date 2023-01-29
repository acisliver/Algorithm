package kakao.blind2019;

import java.util.*;

public class Solution5 {
    public static void main(String[] args) {
        Solution5 s = new Solution5();
        System.out.println(Arrays.deepToString(s.solution(new int[][]
                {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}}
        )));
    }

    public int[][] solution(int[][] nodeinfo) {
        if (nodeinfo.length == 1) return new int[][]{{1}, {1}};

        int[][] answer = {};
        PriorityQueue<Integer> levels = new PriorityQueue<>(Comparator.reverseOrder());
        HashMap<Integer, List<Node>> nodesByLevel = new HashMap<>();

        for (int i = 0; i < nodeinfo.length; i++) {
            int[] info = nodeinfo[i];
            int x = info[0];
            int y = info[1];

            levels.add(y);

            if (nodesByLevel.containsKey(y)) {
                nodesByLevel.get(y).add(new Node(i + 1, x, y));
            } else {
                List<Node> list = new LinkedList<>();
                list.add(new Node(i + 1, x, y));
                nodesByLevel.put(y, list);
            }
        }

        while (!levels.isEmpty()) {
            Integer level = levels.poll();
            for (Node parent : nodesByLevel.get(level)) {
                setChildren(parent, levels, nodesByLevel);
            }
        }

        return answer;
    }

    private void setChildren(Node parent, PriorityQueue<Integer> levels, HashMap<Integer, List<Node>> nodesByLevel) {
        if (!levels.isEmpty()) {
            Integer level = levels.peek();
            for (Node child : nodesByLevel.get(level)) {
                if (child.x < parent.x && parent.left == null) {
                    parent.left = child;
                } else if (child.x > parent.y && parent.right == null){
                    parent.right = child;
                }
            }
        }
    }


    public class Node implements Comparable<Node>{
        public int num;
        public int x;
        public int y;
        public Node left;
        public Node right;

        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if (this.y - o.y == 0) return o.x - this.x;
            return this.y - o.y;
        }
    }
}
