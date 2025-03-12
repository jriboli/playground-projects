package com.codewithmosh.collections;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueDemo {
    public static void show() {
        Queue<String> queue = new ArrayDeque<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        // c -> b -> a
        // Another method to add to the queue
        // queue2.offer("d");

        var frontOfQueue = queue.peek();
        System.out.println("First item in queue: "+ frontOfQueue);
        System.out.println("Processing item in queue...");
        var itemProcessed = queue.remove();
        frontOfQueue = queue.peek();
        System.out.println("Completed " + itemProcessed + ", moving onto " + frontOfQueue);

    }

}
