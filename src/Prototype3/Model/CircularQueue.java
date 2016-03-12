package src.Prototype3.Model;


/**
 * Created by Jamie on 24/02/2016.
 *
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
        if (size == q.length){
            int NewQueue[] = new int[(q.length * 2)];

            for(int i=0; i < size; i++)
            {
                NewQueue[i] = q[front];
                front=(front+1) % q.length;
            }
            front = 0;
            rear = size;
            no = no * 2;
            q = NewQueue;
        }
        q[rear] = element;
        rear = (rear+1)%no;
        size++;
        if (size == q.length && size == 10){
            rear = 10;
        } else if(size == q.length && size == 20){
            rear = front;
        }
    }

    public int getSize(){
        return size;
    }

    public int dequeue() throws NullPointerException{
        if(isEmpty()){
            throw new NullPointerException();
        }
        if(rear == 10 && (size == q.length)){
            rear = front;
        }
        int e = q[front];
        q[front] = 0;
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
        boolean isEmpty = true;
        for(int i = 0; i < q.length; i++){
            if(q[i] > 0){
                isEmpty = false;
            }
        }
        return isEmpty;
    }

    public void clear(){
        size = 0;
        q = new int[10];
        no = 10;
        front = rear = 0;
    }

}
