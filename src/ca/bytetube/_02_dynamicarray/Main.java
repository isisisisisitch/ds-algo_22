package ca.bytetube._02_dynamicarray;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 10; i < 20; i++) {
            list.add(0,i);
        }


       while (list.size() > 0){
           Integer remove = list.remove(0);
           System.out.println(remove);
       }

        System.out.println();
    }
}
