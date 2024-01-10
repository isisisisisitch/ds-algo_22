package ca.bytetube._03_list;


public class Main {
    public static void main(String[] args) {

        SingleLinkedList<String> linkedList = new SingleLinkedList<>();
//        char ch = 'a';
//        //System.out.println(ch);
//        String s = String.valueOf((char)(ch + 1));
//        System.out.println(s);
        for (int i = 0; i < 3; i++) {
            linkedList.add(i, String.valueOf((char)('a' + i)));
        }

        String oldEle = linkedList.set(1, null);
       // System.out.println(oldEle);
        System.out.println(linkedList.indexOf(null));

        //System.out.println(linkedList);
    }
}
