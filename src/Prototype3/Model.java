package src.Prototype3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Jamie on 22/02/2016.
 *
 */
public class Model {

    private Stack<Integer> stack = new Stack<>();
    private Queue<Integer> queue = new LinkedList<>();
    private CircularQueue<Integer> circularQueue = new CircularQueue<>();

    //Stack Operations

    public void push(int x){
        stack.push(x);
    }

    public int pop(){
        return stack.pop();
    }

    public int peek(){
        return stack.peek();
    }

    public Stack<Integer> getStack(){
        return  stack;
    }

    //Queue Operations

    public void enqueue(int x) {
        queue.add(x);
    }

    public int dequeue(){
        return queue.poll();
    }

    public int peekQueue(){
        return queue.peek();
    }

    public Queue<Integer> getQueue(){
        return queue;
    }

    //Circular Queue Operations

    public void enqueueCircular(int x){
        circularQueue.enqueue(x);
    }

    public int dequeueCircular(){
        return circularQueue.dequeue();
    }

    public int peekCircular(){
        return circularQueue.peek();
    }

    public int getFront(){
        return circularQueue.getFront();
    }

    public int getRear(){
        return circularQueue.getRear();
    }

    public CircularQueue<Integer> getCircularQueue(){
        return circularQueue;
    }

}
