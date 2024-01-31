package ca.bytetube._03_list;


import ca.bytetube._03_list.doubly.DoublyCircularLinkedList;
import ca.bytetube._03_list.doubly.LinkedList;
import ca.bytetube._03_list.singly.SingleCircularLinkedList;
import ca.bytetube._03_list.singly.SingleLinkedList;
import org.junit.Test;

public class Main {
    public static void main(String[] args) {
        Josephus(9,2);
//        SingleLinkedList<Integer> linkedList = new SingleLinkedList<>();
//        for (int i = 0; i < 10; i++) {
//            linkedList.add(0, i + 100);
//        }
//        System.out.println(linkedList);
//        while (!linkedList.isEmpty()) {
//            linkedList.remove(0);
//        }
//        System.out.println(linkedList);
//        char ch = 'a';
//        //System.out.println(ch);
//        String s = String.valueOf((char)(ch + 1));
//        System.out.println(s);
//        for (int i = 0; i < 3; i++) {
//            linkedList.add(i, String.valueOf((char)('a' + i)));
//        }
//
//        String oldEle = linkedList.set(1, null);
//       // System.out.println(oldEle);
//        System.out.println(linkedList.indexOf(null));

        //System.out.println(linkedList);
//        Person p1 = new Person(11,"A");
//        Person p2 = new Person(22,"B");
//        Person p3 = new Person(33,"C");
//        Person p4 = new Person(44,"D");
//        Person p5 = new Person(55,"E");
//        linkedList.add(p1);
//        linkedList.add(p2);
//        linkedList.add(p3);
//        linkedList.add(p4);
//        linkedList.add(p5);
//        linkedList.add(p1);
//        System.out.println(linkedList);
//        linkedList.remove(linkedList.size() - 1);
//        System.out.println(linkedList);

    }


    public static void Josephus(int num, int step) {
        DoublyCircularLinkedList<Integer> linkedList = new DoublyCircularLinkedList<>();
        for (int i = 1; i < num; i++) {
            linkedList.add(i);
        }
        linkedList.reset();

        while (!linkedList.isEmpty()) {
            for (int i = 0; i < step; i++) linkedList.next();

            System.out.print(linkedList.remove() + " ");

        }

    }

    @Test
    public void test5() {
        DoublyCircularLinkedList<Integer> linkedList = new DoublyCircularLinkedList<>();
        for (int i = 0; i < 4; i++) {
            linkedList.add(i + 10);
        }


//        System.out.println(linkedList.remove(2));
//        System.out.println(linkedList.remove(0));

        while (!linkedList.isEmpty()) {
            System.out.println(linkedList.remove(0));
        }

        System.out.println();
    }


    @Test
    public void test4() {
        SingleCircularLinkedList<Integer> linkedList = new SingleCircularLinkedList<>();
        for (int i = 0; i < 4; i++) {
            linkedList.add(i + 10);
        }

        //System.out.println(linkedList.remove(2));
        //System.out.println(linkedList.remove(0));

        while (!linkedList.isEmpty()) {
            System.out.println(linkedList.remove(0));
        }

        System.out.println();
    }

    @Test
    public void test3() {
        SingleCircularLinkedList<Integer> linkedList = new SingleCircularLinkedList<>();
        for (int i = 0; i < 4; i++) {
            linkedList.add(i + 10);
        }

//        while (!linkedList.isEmpty()){
//            System.out.println(linkedList.remove(0));
//        }

        System.out.println();
    }


    @Test
    public void test2() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            linkedList.add(0, i + 10);
        }

        while (!linkedList.isEmpty()) {
            System.out.println(linkedList.remove(0));
        }

        System.out.println();
    }

    @Test
    public void test1() {

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            linkedList.add(0, i + 10);
        }

        System.out.println();

    }
}
