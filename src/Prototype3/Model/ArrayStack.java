package src.Prototype3.Model;

import java.util.EmptyStackException;

/**
 * Created by xnb12162 on 16/03/16.
 *
 */
public class ArrayStack<Integer> {
    private int top;
    private int size;
    private int[] stack;

    public ArrayStack(){
        size = 10;
        top = -1;
        stack = new int[size];
    }

    public void push(int val){
        if(top == (size - 1)){
            throw new StackOverflowError();
        }else{
            top++;
            stack[top] = val;
        }
    }

    public int pop(){
        if(top == -1){
            throw new EmptyStackException();
        }else{
            int temp = stack[top];
            top--;
            return temp;
        }
    }

    public int peek(){
        if(top == -1){
            throw new EmptyStackException();
        }
        return stack[top];
    }

    public int getTop(){
        return top;
    }

    public int[] getStack(){
        return stack;
    }

    public int size(){
        return size;
    }

    public void clear(){
        size = 10;
        top = -1;
        stack = new int[size];
    }

    public boolean isEmpty(){
        return top == -1;
    }


}
