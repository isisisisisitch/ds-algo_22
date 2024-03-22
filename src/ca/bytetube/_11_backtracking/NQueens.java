package ca.bytetube._11_backtracking;

/**
 * https://leetcode.com/problems/n-queens-ii/description/
 *
 * @author dal
 */
public class NQueens {
    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        nQueens.totalNQueens(8);
        System.out.println(nQueens.ways);
    }

    //cols[4] = 5;//表示在第5行的第6列上有一个皇后
    private int[] cols;
    private int ways;

    public int totalNQueens(int n) {
        cols = new int[n];
        place(0);
        return ways;
    }

    //在第row行放一个queen
    private void place(int row) {//0
        if (row == cols.length) {
            ways++;
            show();
            return;
        }

        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {
                cols[row] = col;//(0,0)
                place(row + 1);
            }
        }

    }

    private boolean isValid(int row, int col) {
        //i 表示已经走过的行，因为皇后是从第0行开始向下摆放，所以需要看之前所有行的皇后的摆放位置的列和对角线（画出黑色区域）
        for (int i = 0; i < row; i++) {
            //col
            if (cols[i] == col) return false;
            //diagonal
            if (Math.abs(col - cols[i]) == row - i) return false;
        }
        return true;
    }

    private void show(){
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) System.out.print("Q");
                else System.out.print(".");
            }
            System.out.println();
        }
        System.out.println("========================");
    }

}
