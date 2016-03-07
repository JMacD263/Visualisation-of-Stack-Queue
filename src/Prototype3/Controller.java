package src.Prototype3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;
import java.util.HashMap;
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
    private boolean predictionMode = false;
    private HashMap<String, Integer> predictionCount = new HashMap<>();
    private int maxListSize = 35;
    String regex = "^[1-9]\\d{0,2}$"; //Regex for a positive integer, max 3 digits.

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

    // Add operations to the circular queue list
    public void addCircularQueueOperation(String op){
        if(operationsListCircularQueue.size() < maxListSize){
            operationsListCircularQueue.push(op);
        }else{
            operationsListCircularQueue.removeLast();
            operationsListCircularQueue.push(op);
        }
        String listData[] = operationsListCircularQueue.toArray(new String[operationsListCircularQueue.size()]);
        theView.setPreviousQueueOperations(listData);
    }

    public void switchQueueOperationsList(String s){
        theView.setPreviousQueueOperations(new String[0]);
        String listData[];
        if(s.equals("Circular")){
            listData = operationsListCircularQueue.toArray(new String[operationsListCircularQueue.size()]);
            theView.setPreviousQueueOperations(listData);
        }else if (s.equals("Normal")){
            listData = operationsListQueue.toArray(new String[operationsListQueue.size()]);
            theView.setPreviousQueueOperations(listData);
        }
    }

    //Method for performing predictions
    public void runPrediction(String type) {
        int count = predictionCount.get(type);
        int answer;
        String[] buttons; //For the custom JOptionPane buttons text

        switch (type) {
            case "Push":
                buttons = new String[]{"Top", "Bottom"};
                answer = JOptionPane.showOptionDialog(null, "Where do you think the element will be pushed?", "Push Prediction",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);
                if (answer == 0) { //if they select Top (The Correct answer)
                    JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                    count++;
                    predictionCount.put("Push", count);
                } else { //if they selected Bottom (incorrect)
                    JOptionPane.showMessageDialog(null, "Sorry that was incorrect, please try again", "Incorrect", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Pop":
                buttons = new String[]{"Top", "Bottom"};
                answer = JOptionPane.showOptionDialog(null, "Where do you think the element will be removed?", "Pop Prediction",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);
                if (answer == 0) { //if they select Top (The Correct answer)
                    JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                    count++;
                    predictionCount.put("Pop", count);
                } else { //if they selected Bottom (incorrect)
                    JOptionPane.showMessageDialog(null, "Sorry that was incorrect, please try again", "Incorrect", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "StackPeek":
                buttons = new String[]{"Top", "Bottom"};
                answer = JOptionPane.showOptionDialog(null, "Where do you think the element will be peeked from?", "Peek Prediction",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);
                if (answer == 0) { //if they select Top (The Correct answer)
                    JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                    count++;
                    predictionCount.put("StackPeek", count);
                } else { //if they selected Bottom (incorrect)
                    JOptionPane.showMessageDialog(null, "Sorry that was incorrect, please try again", "Incorrect", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Enqueue":
                buttons = new String[]{"Head", "Tail"};
                answer = JOptionPane.showOptionDialog(null, "Where do you think the element will be enqueued?", "Enqueue Prediction",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);
                if (answer == 0) { //if they select Head (The incorrect answer)
                    JOptionPane.showMessageDialog(null, "Sorry that was incorrect, please try again", "Incorrect", JOptionPane.ERROR_MESSAGE);
                } else { //if they selected Tail (Correct)
                    JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                    count++;
                    predictionCount.put("Enqueue", count);
                }
                break;
            case "Dequeue":
                buttons = new String[]{"Head", "Tail"};
                answer = JOptionPane.showOptionDialog(null, "Where do you think the element will be dequeued?", "Dequeue Prediction",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);
                if (answer == 0) { //if they select Head (The incorrect answer)
                    JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                    count++;
                    predictionCount.put("Dequeue", count);
                } else { //if they selected Tail (Correct)
                    JOptionPane.showMessageDialog(null, "Sorry that was incorrect, please try again", "Incorrect", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "QueuePeek":
                buttons = new String[]{"Head", "Tail"};
                answer = JOptionPane.showOptionDialog(null, "Where do you think the element will be peeked from?", "Peek Prediction",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);
                if (answer == 0) { //if they select Head (The incorrect answer)
                    JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                    count++;
                    predictionCount.put("QueuePeek", count);
                } else { //if they selected Tail (Correct)
                    JOptionPane.showMessageDialog(null, "Sorry that was incorrect, please try again", "Incorrect", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }

        //For starting harder questions after getting 2 correct
        if (count == 2) {
            buttons = new String[]{"Progress", "Stay"};
            int options = JOptionPane.showOptionDialog(null, "You have predicted correctly again, would you like to progress to harder questions?", "Progress?",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
            if (options == 0) {
                System.out.println("ADD THING FOR HARDER PREDICTIONS");
                //Add code here for harder predictions
            }
        }

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
                        String input = JOptionPane.showInputDialog(null, "Enter a positive Integer of maximum 3 digits you want to push on to the Stack", "Push", JOptionPane.DEFAULT_OPTION);
                        int toBePushed = Integer.parseInt(input);
                        if(!input.matches(regex)){
                            throw new java.lang.NumberFormatException("Not positive 3 digit Integer");
                        }
                        addOperation("Pushing: " + toBePushed);
                        theModel.push(toBePushed);
                        if(predictionMode){
                            runPrediction("Push");
                        }
                        theView.updateStackUI();
                        break;
                    }
                    catch(java.lang.NumberFormatException exception){
                        if(exception.getMessage().equals("null")){
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Sorry that was not a positive Integer, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
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
                if(predictionMode){
                    runPrediction("Pop");
                }
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
                if(predictionMode){
                    runPrediction("StackPeek");
                }
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
                        String input = JOptionPane.showInputDialog(null, "Enter a positive Integer of maximum 3 digits you want to enqueue to the Queue", "Enqueue", JOptionPane.DEFAULT_OPTION);
                        int toBeQueued = Integer.parseInt(input);
                        if(!input.matches(regex)){
                            throw new java.lang.NumberFormatException("Not positive 3 digit Integer");
                        }
                        if(isCircular){
                            theModel.enqueueCircular(toBeQueued);
                            addCircularQueueOperation("Enqueue: " + toBeQueued);
                        } else {
                            theModel.enqueue(toBeQueued);
                            addQueueOperation("Enqueue: " + toBeQueued);
                        }
                        if(predictionMode){
                            runPrediction("Enqueue");
                        }
                        theView.updateQueueUI();
                        break;
                    }
                    catch(java.lang.NumberFormatException exception){
                        if(exception.getMessage().equals("null")){
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Sorry that was not a positive Integer, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
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
                    addCircularQueueOperation("Dequeue: " + dequeue);
                }else{
                    dequeue = theModel.dequeue();
                    addQueueOperation("Dequeue: " + dequeue);
                }
                if(predictionMode){
                    runPrediction("Dequeue");
                }
                theView.updateQueueUI();
            } catch(NullPointerException exception){
                if(isCircular){
                    addCircularQueueOperation("Can't Dequeue: Queue Empty");
                }else{
                    addQueueOperation("Can't Dequeue: Queue Empty");
                }
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
                    addCircularQueueOperation("Peeked: " + peeked);
                }else{
                    peeked = theModel.peekQueue();
                    addQueueOperation("Peeked: " + peeked);
                }
                drawQueue.highlight(peeked);
                if(predictionMode){
                    runPrediction("QueuePeek");
                }
                theView.updateQueueUI();
            } catch(NullPointerException exception){
                if(isCircular){
                    addCircularQueueOperation("Can't Peek: Queue Empty");
                }else{
                    addQueueOperation("Can't Peek: Queue Empty");
                }
                JOptionPane.showMessageDialog(null, "Sorry the Queue is empty and therefore cannot be peeked", "Queue Empty", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class RadioOnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.toggleLabels(false);
            predictionMode = true;

            // Prediction settings dialog
            PredictionOptions predictionOptions = new PredictionOptions();
            predictionOptions.setModal(true);
            predictionOptions.setLocationRelativeTo(theView);
            predictionOptions.setVisible(true);
            System.out.println("Is blank? " + predictionOptions.getIsBlank());
            System.out.println("No. Options " + predictionOptions.getNoPredictions());

            predictionCount.put("Push", 0);
            predictionCount.put("Pop", 0);
            predictionCount.put("StackPeek", 0);
            predictionCount.put("Enqueue", 0);
            predictionCount.put("Dequeue", 0);
            predictionCount.put("QueuePeek", 0);
        }
    }

    class RadioOffListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.toggleLabels(true);
            predictionMode = false;
        }
    }

    class CircularQueueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            drawQueue.toggleCircular(true);
            drawQueue.setCircularQueue(theModel.getCircularQueue());
            isCircular = true;
            switchQueueOperationsList("Circular");
            theView.updateQueueUI();
        }
    }

    class NormalQueueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            drawQueue.toggleCircular(false);
            isCircular = false;
            switchQueueOperationsList("Normal");
            theView.updateQueueUI();
        }
    }

    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            theModel.reset(); //clears the data stored in the model
            operationsList.clear();
            operationsListQueue.clear();
            operationsListCircularQueue.clear();
            theView.resetPreviousOperations(); //clears the data held within the JList
            theView.updateStackUI();
            theView.updateQueueUI();
        }
    }


}
