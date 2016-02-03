package src.Prototype2;

import java.util.Stack;

/**
 * Created by xnb12162 on 01/02/16.
 *
 */

public class Model {

    private Stack<Integer> stack = new Stack<>();

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

}
