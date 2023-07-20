package programers;

import java.util.*;

// 신고 결과 받기
// https://school.programmers.co.kr/learn/courses/30/lessons/92334
public class N92334 {

    public static void main(String[] args) {
        N92334 n = new N92334();
        System.out.println(Arrays.toString(n.solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"}, 2)));
    }

    public int[] solution(String[] id_list, String[] report, int k) {

        Map<String, Set<Report>> reportsByReportee = new HashMap<>();
        Map<String, Integer> mailsByReporter = new HashMap<>();
        int[] mails = new int[id_list.length];

        for (String userId : id_list) {
            reportsByReportee.put(userId, new HashSet<>());
        }

        for (String r : report) {
            String[] rSplit = r.split(" ");
            String reporter = rSplit[0];
            String reportee = rSplit[1];
            Set<Report> set = reportsByReportee.get(reportee);
            set.add(new Report(reporter, reportee));
        }

        for (var entry : reportsByReportee.entrySet()) {
            Set<Report> reports = entry.getValue();
            if (reports.size() < k) {
                continue;
            }

            for (Report r : reports) {
                String reporter = r.reporter;
                mailsByReporter.merge(reporter, 1, Integer::sum);
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            String userId = id_list[i];
            mails[i] = mailsByReporter.getOrDefault(userId, 0);
        }

        return mails;
    }

    class Report {
        String reporter;
        String reportee;

        public Report(String reporter, String reportee) {
            this.reporter = reporter;
            this.reportee = reportee;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Report report = (Report) o;
            return Objects.equals(reporter, report.reporter) && Objects.equals(reportee, report.reportee);
        }

        @Override
        public int hashCode() {
            return Objects.hash(reporter, reportee);
        }
    }
}
