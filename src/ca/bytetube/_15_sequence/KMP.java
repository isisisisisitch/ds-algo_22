package ca.bytetube._15_sequence;

public class KMP {
    public static void main(String[] args) {
        String text = "hello world";
        String pattern = "or";
        System.out.println(indexOf(text, pattern));
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
        int[] next = next(pattern);
        while (pi < pLen && ti <= len) {
            //match successfully
            if (pi < 0 || textChars[ti] == patternChars[pi]) {
                pi++;
                ti++;
            } else {
                //match unsuccessfully
                pi = next[pi];
            }
        }

        return pi == pLen ? ti - pi : -1;
    }

    private static int[] next(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = -1;
        char[] charArray = pattern.toCharArray();
        int i = 0;
        int n = -1;

        while (i < charArray.length - 1) {
            //① if Pattern[i] == Pattern[n]
            //then next[i + 1] = n + 1
            if (n < 0 || charArray[i] == charArray[n]) {
                next[++i] = ++n;

            } else {
                //② if Pattern[i] != Pattern[n],next[n] = k
                //   if Pattern[i] == Pattern[k] , next[i + 1] = k + 1
                n = next[n];//这里的n相当于k
                //   if Pattern[i] != Pattern[k]   Substitute k for n and repeat ②

            }
        }


        return next;
    }
}
