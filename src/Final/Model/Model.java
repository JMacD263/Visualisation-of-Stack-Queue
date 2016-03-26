package src.Final.Model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This class holds all the data for all implementations.
 *
 */
public class Model {

    private Stack<Integer> stack = new Stack<>();
    private Queue<Integer> queue = new LinkedList<>();
    private CircularQueue<Integer> circularQueue = new CircularQueue<>();
    private ArrayStack<Integer> arrayStack = new ArrayStack<>();

    /**
     * This clears all data held in the model.
     */
    public void reset() {
        stack.clear();
        queue.clear();
        circularQueue.clear();
        arrayStack.clear();
    }

    //Stack Operations

    /**
     * This pushes an element to the stack.
     * @param x the element to be pushed to the stack.
     */
    public void push(int x){
        stack.push(x);
    }

    /**
     * This pops an element from the Stack.
     * @return returns the value popped from the stack.
     */
    public int pop(){
        return stack.pop();
    }

    /**
     * This peeks the stack to see the top value.
     * @return returns the value peeked from the stack.
     */
    public int peek(){
        return stack.peek();
    }

    /**
     * Gets the Stack held in the Model.
     *
     * @return returns the Stack held in the Model.
     */
    public Stack<Integer> getStack(){
        return  stack;
    }

    //ArrayStack Operations

    /**
     * This pushes an element to the array stack.
     * @param x the element to be pushed to the array stack.
     */
    public void arrayPush(int x){
        arrayStack.push(x);
    }

    /**
     * This pops an element from the Array Stack.
     * @return returns the value popped from the array stack.
     */
    public int arrayPop(){
        return arrayStack.pop();
    }

    /**
     * This peeks the array stack to see the top value.
     * @return returns the value peeked from the array stack.
     */
    public int arrayPeek(){
        return arrayStack.peek();
    }

    /**
     * Gets the index for the top of the Array Stack.
     * @return returns the top index of the Array Stack.
     */
    public int getTop(){
        return arrayStack.getTop();
    }

    /**
     * This gets the array stack held in the model.
     *
     * @return returns the Array Stack.
     */
    public ArrayStack<Integer> getArrayStack(){
        return arrayStack;
    }

    //Queue Operations

    /**
     * This enqueues an element to the queue.
     * @param x the element to be enqueued to the queue.
     */
    public void enqueue(int x) {
        queue.add(x);
    }

    /**
     * This dequeues an element from the Queue.
     * @return returns the value dequeued from the queue.
     */
    public int dequeue(){
        return queue.poll();
    }

    /**
     * This peeks the Queue to see the head value.
     * @return returns the value peeked from the queue.
     */
    public int peekQueue(){
        return queue.peek();
    }

    /**
     * This gets the Queue held by the model.
     *
     * @return returns the Queue held in the Model
     */
    public Queue<Integer> getQueue(){
        return queue;
    }

    //Circular Queue Operations

    /**
     * This enqueues an element to the circular queue.
     * @param x the element to be enqueued to the circular queue.
     */
    public void enqueueCircular(int x){
        circularQueue.enqueue(x);
    }

    /**
     * This dequeues an element from the circular Queue.
     * @return returns the value dequeued from the circular queue.
     */
    public int dequeueCircular(){
        return circularQueue.dequeue();
    }

    /**
     * This peeks the circular Queue to see the head value.
     * @return returns the value peeked from the circular queue.
     */
    public int peekCircular(){
        return circularQueue.peek();
    }

    /**
     * Gets the value of the front index of the Circular Queue.
     * @return this returns the index for the front of the Circular Queue.
     */
    public int getFront(){
        return circularQueue.getFront();
    }

    /**
     * Gets the value of the rear index of the Circular Queue.
     * @return this returns the index for the rear of the Circular Queue.
     */
    public int getRear(){
        return circularQueue.getRear();
    }

    /**
     * This gets the Circular Queue held in the model.
     *
     * @return returns the Circular Queue held in the Model.
     */
    public CircularQueue<Integer> getCircularQueue(){
        return circularQueue;
    }

}
