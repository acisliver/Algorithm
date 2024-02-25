package leetcode;

import java.util.*;
import java.util.stream.Collectors;

// Find All People With Secret
public class N2092 {

    public static void main(String[] args) {
        System.out.println(new N2092().findAllPeople(4, new int[][]{{3,1,3},{1,2,2},{0,3,3}}, 3));
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        List<Integer> answer = new ArrayList<>();
        int[] parents = new int[n];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        union(0, firstPerson, parents);
        answer.add(0);
        answer.add(firstPerson);

        Arrays.sort(meetings, Comparator.comparingInt(m -> m[2]));
        List<Set<int[]>> conMeetings = new ArrayList<>();

        int now = 0;
        for (int[] meeting : meetings) {
            int x = meeting[0];
            int y = meeting[1];
            int time = meeting[2];
            if (time == now) {
                conMeetings.get(conMeetings.size() - 1)
                        .add(new int[]{x, y});
            } else {
                now = time;
                Set<int[]> set = new HashSet<>();
                set.add(new int[]{x, y});
                conMeetings.add(set);
            }
        }

        for (Set<int[]> conMeeting : conMeetings) {
            for (int[] meeting : conMeeting) {
                union(meeting[0], meeting[1], parents);
            }
            for (int[] meeting : conMeeting) {
                for (int person : meeting) {
                    if (find(person, parents) == 0) {
                        answer.add(person);
                    } else {
                        parents[person] = person;
                    }
                }
            }
        }

        return answer.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private void union(int a, int b, int[] parents) {
        int r1 = find(a, parents);
        int r2 = find(b, parents);

        if (r1 == r2) return;
        if (r1 > r2) {
            parents[r1] = r2;
        } else {
            parents[r2] = r1;
        }
    }

    private int find(int a, int[] parents) {
        if (parents[a] == a) {
            return a;
        }

        return parents[a] = find(parents[a], parents);
    }
}
