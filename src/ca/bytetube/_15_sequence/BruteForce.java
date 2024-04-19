package ca.bytetube._15_sequence;

public class BruteForce {
    public static void main(String[] args) {
        String text = "hello world";
        String pattern = "other";
        System.out.println(indexOf(text,pattern));
    }


    public static int indexOf(String text, String pattern) {
        if (text == null || pattern == null) return -1;

        char[] textChars = text.toCharArray();
        char[] patternChars = pattern.toCharArray();
        int pLen = patternChars.length;
        int tLen = textChars.length;
        if (tLen == 0 || pLen == 0) return -1;
        if (tLen < pLen) return -1;

        int pi = 0;
        int ti = 0;
        int len = tLen - pLen;

        while (pi < pLen && ti <= len) {
            //match successfully
            if (textChars[ti] == patternChars[pi]) {
                pi++;
                ti++;
            } else {
                //match unsuccessfully
                ti -= pi - 1;
                pi = 0;
            }
        }

        return pi == pLen ? ti - pi : -1;
    }
}
