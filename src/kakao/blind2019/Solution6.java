package kakao.blind2019;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// https://school.programmers.co.kr/learn/courses/30/lessons/42893
// Regex, String
public class Solution6 {
    public static void main(String[] args) {
        Solution6 s = new Solution6();

        System.out.println(s.solution("blind", new String[]{
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"
        }));
    }

    public int solution(String word, String[] pages) {

        Map<String, Page> pageMap = new HashMap<>();

        for (int i = 0; i < pages.length; i++) {
            String page = pages[i];
            Page parsedPage = new Page(i, word, page);
            pageMap.put(parsedPage.url, parsedPage);
        }

        for (Page page : pageMap.values()) {

            for (String link : page.links) {
                if (!pageMap.containsKey(link)) continue;

                Page linkedPage = pageMap.get(link);
                linkedPage.linkScore += page.basicScore / page.links.size();
            }
        }

        return pageMap.values().stream().max(Page::compareTo).get().num;
    }

    class Page implements Comparable<Page>{

        public int num;
        public String url;
        public List<String> links;
        public double basicScore;
        public double linkScore;

        public Page(int num, String word, String html) {

            this.num = num;
            this.links = new LinkedList<>();
            this.basicScore = 0;
            this.linkScore = 0;

            // home url
            Matcher homeMatcher = Pattern.compile("<meta property=\"og:url\" content=\"https://(\\S*)\"").matcher(html);

            if (homeMatcher.find()) {
                this.url = homeMatcher.group(1);
            }

            // outer link
            Matcher linkMatcher = Pattern.compile("<a href=\"https://(\\S*)\"").matcher(html);

            while (linkMatcher.find()) {
                this.links.add(linkMatcher.group(1));
            }

            // search word
            String body = html
                    .split("<body>")[1]
                    .split("</body>")[0];

            this.basicScore = Arrays.stream(body.split("[^a-zA-Z]"))
                    .filter(word::equalsIgnoreCase)
                    .count();
        }

        @Override
        public int compareTo(Page o) {
            double total = this.basicScore + this.linkScore - o.basicScore - o.linkScore;
            if (total == 0) {
                return Integer.compare(o.num, this.num);
            } else {
                return Double.compare(this.basicScore + this.linkScore, o.basicScore + o.linkScore);
            }
        }
    }
}
