package ca.bytetube._06_tree;

import ca.bytetube._06_tree.BinaryTree.Node;
import ca.bytetube._06_tree.file.Files;
import ca.bytetube._06_tree.printer.BinaryTrees;
import org.junit.Test;

public class Main {
    public static void main(String[] args) {
        test2();


    }

    @Test
    public void test3() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(9);
        bst.add(11);
        bst.add(10);
        bst.add(12);
//        bst.add(8);
//        bst.add(3);
//        bst.add(10);
//        bst.add(1);
//        bst.add(6);
//        bst.add(14);
//        bst.add(4);
//        bst.add(7);
//        bst.add(13);
        BinaryTrees.println(bst);
        bst.remove(11);
        System.out.println("============================================");
        BinaryTrees.println(bst);


    }


    public static void test2() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();


//        for (int i = 0; i < 10; i++) {
//            bst.add(random.nextInt(100));
//        }

        bst.add(8);
        bst.add(3);
        bst.add(10);
        bst.add(1);
        bst.add(6);
        bst.add(14);
        bst.add(4);
        bst.add(7);
        bst.add(13);
        BinaryTrees.println(bst);

        System.out.println(bst.contains(12));

        System.out.println(bst.predecessor(bst.node(8)));
        System.out.println(bst.successor(bst.node(8)));


    }


    public static void test1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();


//        for (int i = 0; i < 10; i++) {
//            bst.add(random.nextInt(100));
//        }
        bst.add(8);
        bst.add(3);
        bst.add(10);
        bst.add(1);
        bst.add(6);
        bst.add(14);
        bst.add(4);
        bst.add(7);
        bst.add(13);
        String s = BinaryTrees.printString(bst);
        Files.writeToFile("/Users/dalluo/Desktop/test.txt", s, true);


    }


    public static void test0() {
        Node root = new Node<>(7);
        root.left = new Node(4);
        root.right = new Node(9);
        root.left.left = new Node(2);
        root.left.right = new Node(5);
        root.right.left = new Node(8);
        root.right.right = new Node(11);
        root.left.left.left = new Node(1);
        root.left.left.right = new Node(3);
        root.right.right.left = new Node(10);
        root.right.right.right = new Node(12);

        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        System.out.println(binaryTree.height(root));


    }

}
