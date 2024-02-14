package ca.bytetube._00_leetcode._02_stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/description/
 * @author dal
 */
public class ValidParentheses {
    public static void main(String[] args) {
        isValid("()");
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' ||ch == '{') {
                stack.push(ch);
            }else {
                if (stack.isEmpty()) return false;
                char left = stack.pop();
                if (left == '(' && ch != ')') return false;
                if (left == '[' && ch != ']') return false;
                if (left == '{' && ch != '}') return false;
            }
        }

        return stack.isEmpty();
    }

    public boolean isValid1(String s) {
        while (s.contains("{}") || s.contains("[]")||s.contains("()")){
            s = s.replace("{}","");
            s = s.replace("[]","");
            s = s.replace("()","");
        }

        return s.isEmpty();
    }
}
