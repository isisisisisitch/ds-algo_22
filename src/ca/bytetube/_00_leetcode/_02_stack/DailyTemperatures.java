package ca.bytetube._00_leetcode._02_stack;
//Monotonic Stack

import java.util.Stack;

/**
 * https://leetcode.com/problems/daily-temperatures/description/
 *
 * @author dal
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        dailyTemperatures(temperatures);
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        //1.从头到尾扫描每一个元素
        //2.把每个元素放入单调栈中（从底部到顶部逐渐变小）
        Stack<Integer> stack = new Stack<>();
        //3.准备2个数组用来统计左边和右边第一个比该元素大的索引

        int[] res = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }


            //第一次直接入栈
            stack.push(i);
        }
        return res;
    }
}
