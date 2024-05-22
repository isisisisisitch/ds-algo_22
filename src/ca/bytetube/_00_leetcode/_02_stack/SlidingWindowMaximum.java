package ca.bytetube._00_leetcode._02_stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * @author dal
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) return nums;
        int[] maxes = new int[nums.length - k + 1];
        int maxIndex = 0;

        //1.先求出第一个窗口中的最大值索引
        for (int i = 1; i < k; i++) {
            if (nums[i] > nums[maxIndex]) maxIndex = i;
        }

        for (int li = 0; li < maxes.length; li++) {
            //li驱动ri的移动
            int ri = li + k - 1;
            if (maxIndex < li) {//最大值索引过期
                maxIndex = li;
                for (int i = li + 1; i <= ri; i++) {
                    if (nums[i] > nums[maxIndex]) maxIndex = i;
                }

            }
            //最大值索引未过期，并且新加入的值比原来窗口内的最大值还要大
            else if (nums[ri] >= nums[maxIndex]) {
                maxIndex = ri;
            }
            //最大值索引未过期，并且新加入的值比原来窗口内的最大值还要小

                maxes[li] = nums[maxIndex];


        }


        return maxes;
    }

    /**
     * 1.If nums[i] >= nums[tail],removing tail until nums[tail] > nums[i]
     * 2.Add index i into tail of dequeue
     * 3.If W >= 0(第一个合法窗口形成)
     * * Remove expired head of dequeue (value of head < w)
     * * Update window max value at W position to nums[head]
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (k == 1) return nums;

        int[] maxes = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();//
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) deque.pollLast();
            deque.addLast(i);

            int w = i - k + 1;
            if (w < 0) continue;
            //代码能够走到这里，说明合法窗口已经形成

            if (w > deque.peekFirst()) deque.removeFirst();//删除过期队头元素
            maxes[w] = nums[deque.peekFirst()];

        }

        return maxes;
    }
}
