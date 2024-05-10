package ca.bytetube._00_leetcode._04_sort;

public class SortColors {

    public void sortColors3(int[] nums) {
        partition(nums, 1);
    }

    private void partition(int[] nums, int pivot) {
        partition(nums, 0, nums.length - 1, 1);
    }

    private void partition(int[] nums, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        while (l < more) {
            if (nums[l] == pivot) l++;
            else if (nums[l] < pivot) swap(nums, ++less, l++);
            else swap(nums, l, --more);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void sortColors2(int[] nums) {
        int less = -1;
        int more = nums.length;
        int pivot = 1;
        int cur = 0;
        while (cur < more) {
            if (nums[cur] < pivot) {
                //less and cur swap
                less++;
                int temp = nums[cur];
                nums[cur] = nums[less];
                nums[less] = temp;
                cur++;
            } else if (nums[cur] > pivot) {
                //more and cur swap, cur remain same position
                more--;
                int temp = nums[cur];
                nums[cur] = nums[more];
                nums[more] = temp;
            } else {
                cur++;
            }
        }
    }

    public void sortColors(int[] nums) {
        sort(nums, 0, nums.length);
    }

    private void sort(int[] nums, int begin, int end) {

        if (end - begin < 2) return;
        int pivot = pivotIndex(nums, begin, end);
        sort(nums, begin, pivot);
        sort(nums, pivot + 1, end);

    }

    private int pivotIndex(int[] nums, int begin, int end) {
        //1.备份pivot
        int pivot = nums[begin];

        //2.通过begin，end位置上的元素值和pivot进行大小比较，进而确定移动方向
        end--;
        while (begin < end) {
            //2.1 从右向左看
            while (begin < end) {
                if (cmp(pivot, nums[end]) < 0) end--;
                else {
                    nums[begin++] = nums[end];
                    break;
                }
            }

            //2.2 从左向右看
            while (begin < end) {
                if (cmp(pivot, nums[begin]) > 0) begin++;
                else {
                    nums[end--] = nums[begin];
                    break;
                }

            }
        }

        //3.将pivot放到最后的begin和end相遇时的位置
        nums[begin] = pivot;
        return begin;
    }

    protected int cmp(int v1, int v2) {

        return v1 - v2;
    }
}
