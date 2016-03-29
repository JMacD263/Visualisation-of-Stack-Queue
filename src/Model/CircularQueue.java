package src.Model;


/**
 * This class is the Circular Queue implementation used within the System.
 *
 */
public class CircularQueue<Integer> {
    private int q[];
    private int front;
    private int rear;
    private int size;

    /**
     * This sets current size to 0 and the Queue to size 10.
     */
    public CircularQueue(){
        size = 0;
        q = new int[10];
    }

    /**
     * This method enqueues a value to the Circular Queue.
     * It first checks the Circular Queue is not full, if it is
     * then the Queue is doubled in size and then the element
     * is added. Otherwise the element is enqueued as normal
     * and the size is incremented as well as the rear.
     *
     * @param element the element to be enqueued.
     */
    public void enqueue(int element) {
        if (size == q.length){
            int NewQueue[] = new int[(q.length * 2)];
            for(int i=0; i < size; i++) {
                NewQueue[i] = q[front];
                front=(front+1) % q.length;
            }
            front = 0;
            q = NewQueue;
        }
        q[rear] = element;
        rear = (rear+1)%q.length;
        size++;
        if (size == q.length && size == 10){
            rear = 10;
        } else if(size == q.length && size == 20){
            rear = front;
        }
    }

    /**
     * This returns the current number of elements in the Queue.
     *
     * @return the current size of the Queue.
     */
    public int getSize(){
        return size;
    }

    /**
     * Returns and removes the element at the front of the Queue.
     * If the queue is empty a null pointer exception is thrown.
     * Front is changed to the next element and size is decremented.
     *
     * @return The element dequeued from the Queue.
     * @throws NullPointerException
     */
    public int dequeue() throws NullPointerException{
        if(isEmpty()){
            throw new NullPointerException();
        }
        if(rear == 10 && (size == q.length)){
            rear = front;
        }
        int e = q[front];
        q[front] = 0;
        front=(front+1)%q.length;
        size--;
        return e;
    }

    /**
     * This gets the front index of the Queue.
     *
     * @return returns the index for front.
     */
    public int getFront(){
        return front;
    }

    /**
     * This gets the rear index of the Queue.
     *
     * @return returns the index for rear.
     */
    public int getRear(){
        return rear;
    }

    /**
     * Returns the element at the front of the Queue.
     * If the queue is empty a null pointer exception is thrown.
     *
     * @return The element peeked from the Queue.
     * @throws NullPointerException
     */
    public int peek() throws NullPointerException{
        if(isEmpty()){
            throw new NullPointerException();
        }
        return q[front];
    }

    /**
     * This returns the Queue.
     *
     * @return returns the Array holding the Queue.
     */
    public int[] getList(){
        return q;
    }

    /**
     * Checks if the Queue is empty and returns a boolean that shows this.
     *
     * @return returns true if the queue is empty, otherwise false.
     */
    public boolean isEmpty() {
        boolean isEmpty = true;
        for(int i = 0; i < q.length; i++){
            if(q[i] > 0){
                isEmpty = false;
            }
        }
        return isEmpty;
    }

    /**
     * This clears the Queue and resets everything to as it was at initialisation.
     */
    public void clear(){
        size = 0;
        q = new int[10];
        front = rear = 0;
    }

}
