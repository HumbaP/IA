package com.company;

import structures.Node;
import structures.Tree;

public class Main {

    public static void main(String[] args) {
        System.out.println("Primera prueba");

        char[] f ={'K','E','L','L','Y',' ','<','3'};
        System.out.println(String.valueOf(f));


        System.out.println("FIRST STATE");
        String state= "61 783452";
        Node node=new Node(state);
        System.out.println(node.toString());
        System.out.println("=========================================");

        Tree t=new Tree(node,"12345678 ");
            t.solveWithLIFO();
    }
}
