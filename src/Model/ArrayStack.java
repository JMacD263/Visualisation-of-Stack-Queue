package src.Model;

import java.util.EmptyStackException;

/**
 * This class is the Array Stack implementation used within the System.
 */
public class ArrayStack<Integer> {
    private int top;
    private int size;
    private int[] stack;

    /**
     * Sets the size of the Stack and initialises the value for Top.
     */
    public ArrayStack(){
        size = 10;
        top = -1;
        stack = new int[size];
    }

    /**
     * This method pushes a value to the stack.
     * If the Stack is not full then the top is
     * incremented and the value is set.
     *
     * @param val The value to be pushed to the Stack.
     */
    public void push(int val){
        if(top == (size - 1)){
            throw new StackOverflowError();
        }else{
            top++;
            stack[top] = val;
        }
    }

    /**
     * Returns and removes the element at the top of the Stack.
     * EmptyStackException is thrown when the Stack is empty.
     *
     * @throws EmptyStackException
     * @return returns the value popped from the Stack.
     */
    public int pop() throws EmptyStackException{
        if(top == -1){
            throw new EmptyStackException();
        }else{
            int temp = stack[top];
            top--;
            return temp;
        }
    }

    /**
     * Returns the element at the top of the Stack.
     * EmptyStackException is thrown when the Stack is empty.
     *
     * @throws EmptyStackException
     * @return returns the value peeked from the Stack.
     */
    public int peek() throws EmptyStackException{
        if(top == -1){
            throw new EmptyStackException();
        }
        return stack[top];
    }

    /**
     * This gets the top index of the Stack.
     *
     * @return Returns the index for top.
     */
    public int getTop(){
        return top;
    }

    /**
     * This returns the Stack and all values held in it.
     *
     * @return returns the Array holding the Stack
     */
    public int[] getStack(){
        return stack;
    }

    /**
     * Gets the size of the stack.
     *
     * @return The size of the stack.
     */
    public int size(){
        return size;
    }

    /**
     * Resets the stack to empty, top set back to -1.
     */
    public void clear(){
        size = 10;
        top = -1;
        stack = new int[size];
    }

    /**
     * Checks if top is -1 and therefore if Stack is empty.
     *
     * @return returns if the stack is empty or not.
     */
    public boolean isEmpty(){
        return top == -1;
    }


}
