package src.Prototype3;


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
            int NewStack[] = new int[(q.length * 2)];
            System.arraycopy(q, 0, NewStack, 0, q.length);
            //front=(front+1)%q.length;
            //front = 0;
            rear = size;
            no = no * 2;
            q = NewStack;
        }
        q[rear] = element;
//        if(rear == 9 && front == 0){
//            rear = 10;
//        }else{
//            rear = (rear+1)%no;
//        }
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
