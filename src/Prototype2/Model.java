package src.Prototype2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by xnb12162 on 01/02/16.
 *
 */

public class Model {

    private Stack<Integer> stack = new Stack<>();
    private Queue<Integer> queue = new LinkedList<>();

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

    public boolean empty(){
        return stack.empty();
    }

    public int search(int x){
        return stack.search(x);
    }

    public Stack<Integer> getStack(){
        return  stack;
    }

    //Queue Operations

    public void add(int x) {
        queue.add(x);
    }

    public int poll(){
        return queue.poll();
    }

    public int peekQueue(){
        return queue.peek();
    }

    public void offer(int x){
        queue.offer(x);
    }

    public int remove(){
        return queue.remove();
    }

    public int element(){
        return queue.element();
    }

    public Queue<Integer> getQueue(){
        return queue;
    }

}
