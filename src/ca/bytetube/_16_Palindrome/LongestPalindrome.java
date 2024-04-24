package ca.bytetube._16_Palindrome;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * @author dal
 */
public class LongestPalindrome {

    //扩展中心法以连续相同的字符组成的子串作为扩展中心
    public String longestPalindrome(String s) {
        if (s == null) return null;
        char[] cs = s.toCharArray();
        if (cs.length <= 1) return s;
        int maxLen = 1;
        int begin = 0;
        int i = 0;
        while (i < cs.length) {
            int l = i - 1;
            //找到右边第一个不等于cs[i]的位置，记为r
            int r = i;
            while (++r < cs.length && cs[r] == cs[i]) ;
            //r作为下一次的i
            i = r;
            //从l向左，r向右扩展，进而得到更大的回文串
            while (l >= 0 && r <= cs.length - 1 && cs[l] == cs[r]) {
                l--;
                r++;
            }
            //扩展结束，cs[l+1,r)区间就是最大回文子串
            int len = r - ++l;
            if (len > maxLen) {
                maxLen = len;
                begin = l;
            }

        }

        return new String(cs, begin, maxLen);
    }

    //扩展中心法：分别以i位置字符和i位置的右间隙作为回文扩展中心
    public String longestPalindrome2(String s) {
        if (s == null) return null;
        char[] cs = s.toCharArray();
        if (cs.length <= 1) return s;
        int maxLen = 1;
        int begin = 0;

        for (int i = cs.length - 2; i >= 1; i--) {
            //1.以字符为中心向左右扩展
            int len1 = palindromeLength(cs, i - 1, i + 1);

            //2.以字符右间隙为中心向左右扩展
            int len2 = palindromeLength(cs, i, i + 1);

            len1 = Math.max(len1, len2);
            if (len1 > maxLen) {
                maxLen = len1;
                begin = i - ((maxLen - 1) >> 1);
            }
        }

        //单独处理0号位置的右间隙的情况
        if (cs[0] == cs[1] && maxLen < 2) {
            maxLen = 2;
            begin = 0;
        }

        return new String(cs, begin, maxLen);
    }

    private int palindromeLength(char[] cs, int l, int r) {

        while (l >= 0 && r <= cs.length - 1 && cs[l] == cs[r]) {
            l--;
            r++;
        }

        return r - l - 1;
    }


    //dp
    public String longestPalindrome1(String s) {
        if (s == null) return null;
        char[] cs = s.toCharArray();
        if (cs.length <= 1) return s;

        int begin = 0;
        boolean[][] dp = new boolean[cs.length][cs.length];
        int maxLen = 1;
        for (int i = cs.length - 1; i >= 0; i--) {
            for (int j = i; j <= cs.length - 1; j++) {
                int len = j - i + 1;
//                if (len <= 2) {
//                    dp[i][j] = cs[i] == cs[j];
//                } else {
//                    dp[i][j] = dp[i + 1][j - 1] && cs[i] == cs[j];
//                }
                dp[i][j] = (cs[i] == cs[j]) && (len <= 2 || dp[i + 1][j - 1]);

                if (dp[i][j] && len > maxLen) {
                    maxLen = len;
                    begin = i;
                }
            }
        }

        return new String(cs, begin, maxLen);
    }
}
