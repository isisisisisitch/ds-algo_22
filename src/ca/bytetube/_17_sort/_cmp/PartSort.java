package ca.bytetube._17_sort._cmp;


public class PartSort {
    public static void main(String[] args) {
        int[] arr = {1,5,4,3,2,6,7,2,8};
        int[] sort = sort(arr);
        for (int e :sort) {
            System.out.print(e+" ");
        }
    }


    public static int[] sort(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};

        //从左向右扫描，得到数组的最右逆序对的右边界
        int r = -1;
        int max = nums[0];
        for (int cur = 1; cur < nums.length; cur++) {
            if (nums[cur] >= max) {
                max = nums[cur];
            } else {
                //当前产生了一个逆序对，记录该逆序对的右边界
                r = cur;
            }
        }

        //从右向左扫描，得到数组的最左逆序对的左边界
        int l = -1;
        int min = nums[nums.length - 1];
        for (int cur = nums.length - 2; cur >= 0; cur--) {
            if (nums[cur] <= min) {
                min = nums[cur];
            } else {
                //当前产生了一个逆序对，记录该逆序对的右边界
                l = cur;
            }
        }


        return new int[]{l, r};
    }
}
