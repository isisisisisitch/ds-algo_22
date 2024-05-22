package ca.bytetube._00_leetcode._05_tree;


import javax.swing.tree.TreeNode;
import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/maximum-binary-tree/description/
 *
 * @author dal
 */
public class MaximumBinaryTree {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 6, 0, 5};
        int[] parentIndex = parentIndex(arr);
        for (int ele : parentIndex) {
            System.out.print(ele + " ");
        }
    }

    //Monotonic Stack
    public static int[] parentIndex(int[] nums) {

        //1.从头到尾扫描每一个元素
        //2.把每个元素放入单调栈中（从底部到顶部逐渐变小）
        Stack<Integer> stack = new Stack<>();
        //3.准备2个数组用来统计左边和右边第一个比该元素大的索引
        int[] lis = new int[nums.length];
        int[] ris = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            ris[i] = -1;
            lis[i] = -1;
        }

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                ris[stack.pop()] = i;//右边第一个比顶部元素大的值的索引
            }

            if (!stack.isEmpty()) {
                lis[i] = stack.peek();
            }

            //第一次直接入栈
            stack.push(i);
        }
//        System.out.println(Arrays.toString(lis));
//        System.out.println(Arrays.toString(ris));


        int[] pis = new int[nums.length];
        for (int i = 0; i < pis.length; i++) {

            if (lis[i] == -1 && ris[i] == -1) {
                pis[i] = -1;
                continue;
            }

            if (lis[i] == -1) {
                pis[i] = ris[i];
            } else if (ris[i] == -1) {
                pis[i] = lis[i];
            } else if (nums[lis[i]] < nums[ris[i]]) {
                pis[i] = lis[i];
            } else {
                pis[i] = ris[i];
            }
        }

        return pis;
    }


    public TreeNode constructMaximumBinaryTree(int[] nums) {

        return findRoot(nums, 0, nums.length);
    }


    //找到[l,r)范围内的root node
    private TreeNode findRoot(int[] nums, int l, int r) {
        if (l == r) return null;
        int maxIndex = l;
        for (int i = l + 1; i < r; i++) {
            if (nums[i] > nums[maxIndex]) maxIndex = i;
        }
        TreeNode root = new TreeNode(nums[maxIndex]);

        root.left = findRoot(nums, l, maxIndex);
        root.right = findRoot(nums, maxIndex + 1, r);

        return root;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
