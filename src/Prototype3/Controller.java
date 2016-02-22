package src.Prototype3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * Created by Jamie on 22/02/2016.
 */
public class Controller {

    private View theView;
    private Model theModel;
    private LinkedList<String> operationsList = new LinkedList<>();
    private LinkedList<String> operationsListQueue = new LinkedList<>();

        public Controller(View view, Model model) {
            this.theView = view;
            this.theModel = model;

            // Tell the View that whenever a button is pushed
            // to execute the actionPerformed method in the
            // relevant inner class
            theView.addPushListener(new PushListener());
            theView.addPopListener(new PopListener());
            theView.addPeekStackListener(new StackPeekListener());
            theView.addEnqueueListener(new EnqueueListener());
            theView.addDequeueListener(new DequeueListener());
            theView.addPeekQueueListener(new QueuePeekListener());

        }

    // Add operations to Stack list.
    public void addOperation(String op){
        if(operationsList.size() < 25){ //Max size of stack operations list
            operationsList.push(op);
        }else{
            operationsList.removeLast();
            operationsList.push(op);
        }
        String listData[] = operationsList.toArray(new String[operationsList.size()]);
        theView.setPreviousStackOperations(listData);
    }

    // Add operations to the queue list
    public void addQueueOperation(String op){
        if(operationsListQueue.size() < 25){ //Max size of queue operations list
            operationsListQueue.push(op);
        }else{
            operationsListQueue.removeLast();
            operationsListQueue.push(op);
        }
        String listData[] = operationsListQueue.toArray(new String[operationsListQueue.size()]);
        theView.setPreviousQueueOperations(listData);
    }

    /*
    This is a block of inner classes which create ActionListeners for the buttons
     */

    class PushListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(theModel.getStack().size() == 10){
                JOptionPane.showMessageDialog(null, "Sorry the Stack is full and nothing can be pushed", "Stack Full", JOptionPane.ERROR_MESSAGE);
            } else{
                while(true){
                    try{
                        int toBePushed = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to push on to the Stack", "Push", JOptionPane.DEFAULT_OPTION));
                        addOperation("Pushing: " + toBePushed);
                        theModel.push(toBePushed);
                        theView.updateStackUI();
                        break;
                    }
                    catch(java.lang.NumberFormatException exception){
                        if(exception.getMessage().equals("null")){
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Sorry that was not an Integer, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    class PopListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                int popped = theModel.pop();
                addOperation("Popped: " + popped);
                theView.updateStackUI();
            } catch(EmptyStackException exception){
                addOperation("Can't Pop: Stack Empty");
                JOptionPane.showMessageDialog(null, "Sorry the Stack is empty and therefore cannot be popped", "Stack Empty", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class StackPeekListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                int peeked = theModel.peek();
                addOperation("Peeked: " + peeked);
                // drawStuff.highlight(peeked); Needs to be changed to MVC HELP
                theView.updateStackUI();
            } catch(EmptyStackException exception){
                addOperation("Can't Peek: Stack Empty");
                JOptionPane.showMessageDialog(null, "Sorry the Stack is empty and therefore cannot be peeked", "Stack Empty", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class EnqueueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(theModel.getQueue().size() == 15){
                JOptionPane.showMessageDialog(null, "Sorry the Queue is full and nothing can be enqueued", "Queue Full", JOptionPane.ERROR_MESSAGE);
            } else{
                while(true){
                    try{
                        int toBeQueued = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to enqueue to the Queue", "Add", JOptionPane.DEFAULT_OPTION));
                        addQueueOperation("Enqueue: " + toBeQueued);
                        theModel.enqueue(toBeQueued);
                        theView.updateQueueUI();
                        break;
                    }
                    catch(java.lang.NumberFormatException exception){
                        if(exception.getMessage().equals("null")){
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Sorry that was not an Integer, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    class DequeueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                int dequeue = theModel.dequeue();
                addQueueOperation("Dequeue: " + dequeue);
                theView.updateQueueUI();
            } catch(NullPointerException exception){
                addQueueOperation("Can't Dequeue: Queue Empty");
                JOptionPane.showMessageDialog(null, "Sorry the Queue is empty and therefore cannot be dequeued", "Queue Empty", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class QueuePeekListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                int peeked = theModel.peekQueue();
                addQueueOperation("Peeked: " + peeked);
                //drawQueueStuff.highlight(peeked); This needs to be changed for MVC HELP
                theView.updateQueueUI();
            } catch(NullPointerException exception){
                addQueueOperation("Can't Peek: Queue Empty");
                JOptionPane.showMessageDialog(null, "Sorry the Queue is empty and therefore cannot be peeked", "Queue Empty", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}
