package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedList_Loop_5kyu {
    public static void main(String[] args) {
        Node[] head = new Node[1];
        NodeOperation temp = new NodeOperation();
        for(int i = 10; i > 0; i-=2) {
            temp.pushNode(head, i);
        }
        List<Integer> v = new ArrayList<Integer>();
        Node curr = head[0];
        while(curr != null) {
            v.add(curr.data);
            System.out.println("Current : " + curr.data);
            if(curr.data == curr.next.data)
                System.out.println("Next : " + curr.next.data);
            curr = curr.next;
        }

        System.out.println("Middle value of linked list is : ");
        System.out.println(v.get(v.size() / 2));

        System.out.println("Is circular : ");
        System.out.println(temp.isCircular(head[0]));


    }
}

class Node {
    public int data;
    public Node next;
}

class NodeOperation {
    public void pushNode(Node[] headRef, int dataVal) {
        Node newNode = new Node();
        newNode.data = dataVal;
        newNode.next = headRef[0];
        headRef[0] = newNode;
    }

    public boolean isCircular(Node head) {
        if(head == null)
            return true;

        Node node = head.next;

        while(node != null && node != head)
            node = node.next;

        return (node == head);
    }
}
