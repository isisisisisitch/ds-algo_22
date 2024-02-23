package ca.bytetube._07_avltree;

import ca.bytetube._07_avltree.BinaryTree.Node;
import ca.bytetube._07_avltree.file.Files;
import ca.bytetube._07_avltree.printer.BinaryTrees;
import org.junit.Test;

public class Main {
    public static void main(String[] args) {

    }

    @Test
    public void test3() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.add(11);
        avlTree.add(6);
        avlTree.add(4);
        avlTree.add(8);
        avlTree.add(15);
        avlTree.add(14);
        avlTree.add(16);
        avlTree.add(12);


//        avlTree.add(9);
//        avlTree.add(11);
//        avlTree.add(10);
//        avlTree.add(12);
//        bst.add(8);
//        bst.add(3);
//        bst.add(10);
//        bst.add(1);
//        bst.add(6);
//        bst.add(14);
//        bst.add(4);
//        bst.add(7);
//        bst.add(13);
        BinaryTrees.println(avlTree);
        //avlTree.remove(11);
        System.out.println("============================================");
        avlTree.remove(16);
         BinaryTrees.println(avlTree);


    }


}
