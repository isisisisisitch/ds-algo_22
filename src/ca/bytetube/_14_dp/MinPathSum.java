package ca.bytetube._14_dp;

public class MinPathSum {
    public static void main(String[] args) {
        int[][] matrix = {{3, 1, 0, 2}, {4, 3, 2, 1}, {5, 2, 1, 0}};
        int pathSum = minPathSum(matrix);
        System.out.println(pathSum);
    }

    /**
     * 计算从（0，0）出发到达右下角点的最小路径和
     * 计算从（0，0）点出发到达（i，j）点的最小路径和
     */
    public static int minPathSum(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;
        //① Define state
        int[][] dp = new int[row][col];

        //② Set initial state (boundary)
        dp[0][0] = matrix[0][0];

        //first row
        for (int i = 1; i < col; i++) dp[0][i] = dp[0][i - 1] + matrix[0][i];
        //first col
        for (int i = 1; i < row; i++) dp[i][0] = dp[i - 1][0] + matrix[i][0];
        //common
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                //③ Determine the state transition equation
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + matrix[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }


    /**
     * 计算从（0，0）出发到达右下角点的最小路径和
     * 计算从（i，j）点出发到达右下角点的最小路径和
     */
    public static int minPathSum1(int[][] matrix) {


        return minPathSum(matrix, 0, 0);
    }

    private static int minPathSum(int[][] matrix, int i, int j) {
        if (i == matrix.length - 1 && j == matrix[0].length - 1) return matrix[i][j];
        //1.last row
        if (i == matrix.length - 1) return minPathSum(matrix, i, j + 1) + matrix[i][j];
        //2.last col
        if (j == matrix[0].length - 1) return minPathSum(matrix, i + 1, j) + matrix[i][j];
        //3.common
        int right = minPathSum(matrix, i, j + 1);
        int down = minPathSum(matrix, i + 1, j);
        return Math.min(right, down) + matrix[i][j];
    }

}
