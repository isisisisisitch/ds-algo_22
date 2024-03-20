package ca.bytetube._10_recursion;

import java.util.LinkedHashSet;

public class Hanoi {

    public static void main(String[] args) {
        hanoi0(3, "A", "C", "B");
        System.out.println("========================");
        LinkedHashSet<MoveInfo> set = hanoi(3, "A", "C", "B");
        System.out.println(set);
    }


    public static void hanoi0(int n, String from, String to, String help) {

        //When n==1,move the plate directly from A to C
        if (n == 1) System.out.println("move " + 1 + " from " + from + " to " + to);

        else {
            // When n> 1, it can be divided into 3 major steps
            //① Move n – 1 plates from A to B
            hanoi0(n - 1, from, help, to);
            //② Move the plate n from A to C
            System.out.println("move " + n + " from " + from + " to " + to);
            //③ Move n – 1 plates from B to C
            hanoi0(n - 1, help, to, from);
            //Step ① ③ Obviously a recursive call
        }


    }

    public static LinkedHashSet<MoveInfo> hanoi(int n, String from, String to, String help) {

        LinkedHashSet<MoveInfo> set = new LinkedHashSet<>();

        hanoi(n, from, to, help, set);


        return set;
    }

    private static void hanoi(int n, String from, String to, String help, LinkedHashSet<MoveInfo> set) {
        //When n==1,move the plate directly from A to C

        if (n == 1) {
            set.add(new MoveInfo(n, from, to));
            return;
        } else {
            // When n> 1, it can be divided into 3 major steps
            //① Move n – 1 plates from A to B
            hanoi(n - 1, from, help, to, set);
            //② Move the plate n from A to C
            set.add(new MoveInfo(n, from, to));
            //③ Move n – 1 plates from B to C
            hanoi(n - 1, help, to, from, set);
            //Step ① ③ Obviously a recursive call
        }

    }


    private static class MoveInfo {
        int index;
        String from;
        String to;


        public MoveInfo(int index, String from, String to) {
            this.index = index;
            this.from = from;
            this.to = to;
        }


        @Override
        public String toString() {
            return "move " + index + " from " + from + " to " + to + "\n";
        }
    }

}
