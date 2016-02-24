package src.Prototype3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Jamie on 24/02/2016.
 */
public class CircularQueue<Integer> {
    private int no; //number of elements in array
    private int q[];
    private int front;
    private int rear;
    private int size;

    public CircularQueue(){
        size = 0;
        q = new int[10];
        no = 10;
    }

    public void enqueue(int element) {
        if (getSize() == q.length){
            q[front] = element;
            front=(front+1)%q.length;
        }
        q[rear] = element;
        rear = (rear+1)%no;
        size++;
    }

    public int getSize(){
        return size;
    }

    public int dequeue() throws NullPointerException{
        if(isEmpty()){
            throw new NullPointerException();
        }
        int e = q[front];
        front=(front+1)%no;
        size--;
        return e;
    }

    public int getFront(){
        return front;
    }

    public int getRear(){
        return rear;
    }

    public int peek() throws NullPointerException{
        if(isEmpty()){
            throw new NullPointerException();
        }
        return q[front];
    }

    public int[] getList(){
        return q;
    }

    public boolean isEmpty() {
        return (size==0);
    }

}
