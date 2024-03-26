package ca.bytetube._12_greedy;

import java.util.Arrays;

public class ChangeCoins {
    public static void main(String[] args) {
        System.out.println(changeCoins(new int[]{25, 20, 5, 1}, 41));
    }

    /**
     * 25 cents, 10 cents, 5 cents, and 1 cent
     * 49 cents
     * 1. 25 cents :41 = 25 + 16
     * 2. 10 cents: 16 = 10 + 6
     * 3. 5 cents: 6 = 5 + 1
     * 4. 1 cent:1 = 1
     */
    public static int changeCoins(int[] faces, int money) {
        Arrays.sort(faces);
        int coins = 0;
        for (int i = faces.length - 1; i >= 0; i--) {
            if (money < faces[i]) continue;
            money -= faces[i];
            System.out.println(faces[i]);
            coins++;

            i++;


        }


        return coins;
    }
}
