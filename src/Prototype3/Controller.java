package src.Prototype3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * Created by Jamie on 22/02/2016.
 *
 */
public class Controller {

    private View theView;
    private Model theModel;
    private LinkedList<String> operationsList = new LinkedList<>();
    private LinkedList<String> operationsListQueue = new LinkedList<>();
    private LinkedList<String> operationsListCircularQueue = new LinkedList<>();
    private DrawStackRepresentation drawStack = new DrawStackRepresentation();
    private DrawQueueRepresentation drawQueue = new DrawQueueRepresentation();
    private boolean isCircular = false;
    private int maxListSize = 35;

    public Controller(View view, Model model) {
            this.theView = view;
            this.theModel = model;

            // Tell the View that whenever a button or menu item
            // is clicked to execute the actionPerformed method
            // in the relevant inner class.
            theView.addPushListener(new PushListener());
            theView.addPopListener(new PopListener());
            theView.addPeekStackListener(new StackPeekListener());
            theView.addEnqueueListener(new EnqueueListener());
            theView.addDequeueListener(new DequeueListener());
            theView.addPeekQueueListener(new QueuePeekListener());
            theView.addOnRadioListener(new RadioOnListener());
            theView.addOffRadioListener(new RadioOffListener());
            theView.addCircularQueueListener(new CircularQueueListener());
            theView.addNormalQueueListener(new NormalQueueListener());
            theView.addResetListener(new ResetListener());


            drawStack.setStack(theModel.getStack());
            theView.setStackPanel(drawStack);

            drawQueue.setQueue(theModel.getQueue());
            theView.setQueuePanel(drawQueue);

        }

    // Add operations to Stack list.
    public void addOperation(String op){
        if(operationsList.size() < maxListSize){ //Max size of stack operations list
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
        if(operationsListQueue.size() < maxListSize){ //Max size of queue operations list
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
            int maxStackSize = 10;
            if(theModel.getStack().size() == maxStackSize){
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
                drawStack.highlight(peeked);
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
            int maxQueueSize = 20;
            if(theModel.getQueue().size() == maxQueueSize || theModel.getCircularQueue().getSize() == maxQueueSize){
                JOptionPane.showMessageDialog(null, "Sorry the Queue is full and nothing can be enqueued", "Queue Full", JOptionPane.ERROR_MESSAGE);
            } else{
                while(true){
                    try{
                        int toBeQueued = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to enqueue to the Queue", "Add", JOptionPane.DEFAULT_OPTION));
                        addQueueOperation("Enqueue: " + toBeQueued);
                        if(isCircular){
                            theModel.enqueueCircular(toBeQueued);
                        } else {
                            theModel.enqueue(toBeQueued);
                        }
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
                int dequeue;
                if(isCircular){
                    dequeue = theModel.dequeueCircular();
                }else{
                    dequeue = theModel.dequeue();
                }
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
                int peeked;
                if(isCircular){
                    peeked = theModel.peekCircular();
                }else{
                    peeked = theModel.peekQueue();
                }
                addQueueOperation("Peeked: " + peeked);
                drawQueue.highlight(peeked);
                theView.updateQueueUI();
            } catch(NullPointerException exception){
                addQueueOperation("Can't Peek: Queue Empty");
                JOptionPane.showMessageDialog(null, "Sorry the Queue is empty and therefore cannot be peeked", "Queue Empty", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class RadioOnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.toggleLabels(false);
        }
    }

    class RadioOffListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.toggleLabels(true);
        }
    }

    class CircularQueueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            drawQueue.toggleCircular(true);
            drawQueue.setCircularQueue(theModel.getCircularQueue());
            isCircular = true;
            theView.updateQueueUI();
        }
    }

    class NormalQueueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            drawQueue.toggleCircular(false);
            isCircular = false;
            theView.updateQueueUI();
        }
    }

    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            theModel.reset(); //clears the data stored in the model
            operationsList.clear();
            operationsListQueue.clear();
            theView.resetPreviousOperations(); //clears the data held within the JList
            theView.updateStackUI();
            theView.updateQueueUI();
        }
    }


}
