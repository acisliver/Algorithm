package leetcode;

public class N1704 {

    char[] dic = {'a', 'e', 'i', 'o', 'u'};

    public boolean halvesAreAlike(String s) {
        int len = s.length();
        s = s.toLowerCase();

        int a = 0;
        int b = 0;
        for (int i = 0; i < len / 2; i++) {
            for (int j = 0; j < dic.length; j++) {
                char c = dic[j];
                if (c == s.charAt(i)) {
                    a += 1;
                }
            }
        }

        for (int i = len / 2; i < len; i++) {
            for (int j = 0; j < dic.length; j++) {
                char c = dic[j];
                if (c == s.charAt(i)) {
                    b += 1;
                }
            }
        }

        return a == b;
    }
}
