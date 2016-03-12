package src.Prototype3.Controller;

import src.Prototype3.GUIDialogs.DrawHarderPredictions;
import src.Prototype3.GUIDialogs.HarderPredictions;
import src.Prototype3.Model.Model;
import src.Prototype3.GUIDialogs.PredictionOptions;
import src.Prototype3.View.DrawQueueRepresentation;
import src.Prototype3.View.DrawStackRepresentation;
import src.Prototype3.View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Jamie on 22/02/2016.
 *
 */
public class Controller {

    private View theView;
    private Model theModel;
    private LinkedList<String> operationsListStack = new LinkedList<>();
    private LinkedList<String> operationsListQueue = new LinkedList<>();
    private LinkedList<String> operationsListCircularQueue = new LinkedList<>();
    private LinkedList<String> operationsListStackJava = new LinkedList<>();
    private LinkedList<String> operationsListQueueJava = new LinkedList<>();
    private LinkedList<String> operationsListCircularQueueJava = new LinkedList<>();
    private DrawStackRepresentation drawStack = new DrawStackRepresentation();
    private DrawQueueRepresentation drawQueue = new DrawQueueRepresentation();
    private boolean isCircular = false;
    private boolean predictionMode = false;
    private int noPredictions;
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
        theView.addHarderPredictionsListener(new HarderPredictionsListener());

        drawStack.setStack(theModel.getStack());
        theView.setStackPanel(drawStack);

        drawQueue.setQueue(theModel.getQueue());
        theView.setQueuePanel(drawQueue);
    }

    // Add operations to Stack list.
    public void addStackOperation(String op){
        if(operationsListStack.size() < maxListSize){ //Max size of stack operations list
            operationsListStack.push(op);
        }else{
            operationsListStack.removeLast();
            operationsListStack.push(op);
        }
        String listData[] = operationsListStack.toArray(new String[operationsListStack.size()]);
        theView.setPreviousStackOperations(listData);
    }

    // Add Java code operations to stack list
    public void addStackJavaOperations(String op){
        if(operationsListStackJava.size() < maxListSize){ //Max size of stack operations list
            operationsListStackJava.push(op);
        }else{
            operationsListStackJava.removeLast();
            operationsListStackJava.push(op);
        }
        String listData[] = operationsListStackJava.toArray(new String[operationsListStackJava.size()]);
        theView.setPreviousStackJavaOperations(listData);
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

    // Add Java code operations to queue list
    public void addQueueJavaOperation(String op){
        if(operationsListQueueJava.size() < maxListSize){ //Max size of stack operations list
            operationsListQueueJava.push(op);
        }else{
            operationsListQueueJava.removeLast();
            operationsListQueueJava.push(op);
        }
        String listData[] = operationsListQueueJava.toArray(new String[operationsListQueueJava.size()]);
        theView.setPreviousQueueJavaOperations(listData);
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

    // Add Java code operations to circular queue list
    public void addCircularQueueJavaOperation(String op){
        if(operationsListCircularQueueJava.size() < maxListSize){ //Max size of stack operations list
            operationsListCircularQueueJava.push(op);
        }else{
            operationsListCircularQueueJava.removeLast();
            operationsListCircularQueueJava.push(op);
        }
        String listData[] = operationsListCircularQueueJava.toArray(new String[operationsListCircularQueueJava.size()]);
        theView.setPreviousQueueJavaOperations(listData);
    }

    // Switch Circular and Regular Queue Operations List
    public void switchQueueOperationsList(String s){
        theView.setPreviousQueueOperations(new String[0]);
        theView.setPreviousQueueJavaOperations(new String[0]);
        String listData[];
        if(s.equals("Circular")){
            listData = operationsListCircularQueue.toArray(new String[operationsListCircularQueue.size()]);
            theView.setPreviousQueueOperations(listData);
            listData = operationsListCircularQueueJava.toArray(new String[operationsListCircularQueueJava.size()]);
            theView.setPreviousQueueJavaOperations(listData);
        }else if (s.equals("Normal")){
            listData = operationsListQueue.toArray(new String[operationsListQueue.size()]);
            theView.setPreviousQueueOperations(listData);
            listData = operationsListQueueJava.toArray(new String[operationsListQueueJava.size()]);
            theView.setPreviousQueueJavaOperations(listData);
        }
    }

    // creates data for predictions
    public void createPreMadeData(){
        int min = 1;
        int max = 99;
        for(int i = 0; i < ThreadLocalRandom.current().nextInt(4, 10 + 1); i++){ //run between 4 to 10 times
            theModel.push(ThreadLocalRandom.current().nextInt(min, max + 1));
            theModel.enqueue(ThreadLocalRandom.current().nextInt(min, max + 1));
            theModel.enqueueCircular(ThreadLocalRandom.current().nextInt(min, max + 1));
        }
        if(ThreadLocalRandom.current().nextInt(1, 10 + 1) < 7){ // add some dequeues to move the head and tail around
            for(int i = 0; i < ThreadLocalRandom.current().nextInt(2, 5 + 1); i++) { //run between 2 to 5 times
                theModel.enqueueCircular(ThreadLocalRandom.current().nextInt(min, max + 1));
                theModel.dequeueCircular();
            }
        }
    }

    //Method for performing predictions
    public void runPrediction(String type) {
        int count = predictionCount.get(type);
        int answer;
        boolean answered = false;
        String[] buttons; //For the custom JOptionPane buttons text

        switch (type) {
            case "Push":
                if(count < noPredictions){
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
                    answered = true;
                }
                break;
            case "Pop":
                if(count < noPredictions){
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
                    answered = true;
                }
                break;
            case "StackPeek":
                if(count < noPredictions){
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
                    answered = true;
                }
                break;
            case "Enqueue":
                if(count < noPredictions){
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
                    answered = true;
                }
                break;
            case "Dequeue":
                if(count < noPredictions){
                    buttons = new String[]{"Head", "Tail"};
                    answer = JOptionPane.showOptionDialog(null, "Where do you think the element will be dequeued?", "Dequeue Prediction",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);
                    if (answer == 0) { //if they select Head (The Correct answer)
                        JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        count++;
                        predictionCount.put("Dequeue", count);
                    } else { //if they selected Tail (Incorrect)
                        JOptionPane.showMessageDialog(null, "Sorry that was incorrect, please try again", "Incorrect", JOptionPane.ERROR_MESSAGE);
                    }
                    answered = true;
                }
                break;
            case "QueuePeek":
                if(count < noPredictions){
                    buttons = new String[]{"Head", "Tail"};
                    answer = JOptionPane.showOptionDialog(null, "Where do you think the element will be peeked from?", "Peek Prediction",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);
                    if (answer == 0) { //if they select Head (The Correct answer)
                        JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        count++;
                        predictionCount.put("QueuePeek", count);
                    } else { //if they selected Tail (incorrect)
                        JOptionPane.showMessageDialog(null, "Sorry that was incorrect, please try again", "Incorrect", JOptionPane.ERROR_MESSAGE);
                    }
                    answered = true;
                }
                break;
            case "CircularEnqueue":
                if(count < noPredictions){
                    int correct = theModel.getRear();
                    int other;
                    int correctAnswer;
                    if(correct != 0 && (correct!=9 && correct!=19)){
                        if(ThreadLocalRandom.current().nextInt(1, 10 + 1) < 6){
                            other = correct + 1;
                        }else{
                            other = correct - 1;
                        }
                    }else if(correct == 9 || correct == 19){
                        other = correct - 1;
                    }else{
                        other = correct + 1;
                    }
                    buttons = new String[2];
                    if(ThreadLocalRandom.current().nextInt(1, 10 + 1) < 6){
                        buttons[0] = Integer.toString(correct);
                        buttons[1] = Integer.toString(other);
                        correctAnswer = 0;
                    }else{
                        buttons[1] = Integer.toString(correct);
                        buttons[0] = Integer.toString(other);
                        correctAnswer = 1;
                    }
                    answer = JOptionPane.showOptionDialog(null, "What location will the element be enqueued too?", "Enqueue Prediction",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);
                    if (answer == 0 && correctAnswer == 0) { //if they select the correct answer
                        JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        count++;
                        predictionCount.put("CircularEnqueue", count);
                    } else if(answer == 1 && correctAnswer == 1){ //if they selected the correct answer
                        JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        count++;
                        predictionCount.put("CircularEnqueue", count);
                    } else{
                        JOptionPane.showMessageDialog(null, "Sorry that was incorrect, please try again", "Incorrect", JOptionPane.ERROR_MESSAGE);
                    }
                    answered = true;
                }
                break;
            case "CircularDequeue":
                if(count < noPredictions){
                    int correct = theModel.getFront();
                    int other;
                    int correctAnswer;
                    if(correct != 0 && (correct!=9 && correct!=19)){
                        if(ThreadLocalRandom.current().nextInt(1, 100 + 1) < 51){
                            other = correct + 1;
                        }else{
                            other = correct - 1;
                        }
                    }else if(correct == 9 || correct == 19){
                        other = correct - 1;
                    }else{
                        other = correct + 1;
                    }
                    buttons = new String[2];
                    if(ThreadLocalRandom.current().nextInt(1, 10 + 1) < 6){
                        buttons[0] = Integer.toString(correct);
                        buttons[1] = Integer.toString(other);
                        correctAnswer = 0;
                    }else{
                        buttons[0] = Integer.toString(other);
                        buttons[1] = Integer.toString(correct);
                        correctAnswer = 1;
                    }
                    answer = JOptionPane.showOptionDialog(null, "What location will the element be dequeued from?", "Dequeue Prediction",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);
                    if (answer == 0 && correctAnswer == 0) { //if they select the correct answer
                        JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        count++;
                        predictionCount.put("CircularDequeue", count);
                    } else if(answer == 1 && correctAnswer == 1){ //if they selected the correct answer
                        JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        count++;
                        predictionCount.put("CircularDequeue", count);
                    } else{
                        JOptionPane.showMessageDialog(null, "Sorry that was incorrect, please try again", "Incorrect", JOptionPane.ERROR_MESSAGE);
                    }
                    answered = true;
                }
                break;
            case "CircularPeek":
                int correct = theModel.getFront();
                int other = theModel.getRear();
                int correctAnswer;
                if(correct == other){
                    if(other == 0){
                        other += 1;
                    }else if(other == 9 || other == 19){
                        other -= 1;
                    }else{
                        if(ThreadLocalRandom.current().nextInt(1, 10 + 1) < 6){
                            other += 1;
                        }else{
                            other -= 1;
                        }
                    }
                }
                buttons = new String[2];
                if(ThreadLocalRandom.current().nextInt(1, 100 + 1) < 51){
                    buttons[0] = Integer.toString(correct);
                    buttons[1] = Integer.toString(other);
                    correctAnswer = 0;
                }else{
                    buttons[0] = Integer.toString(other);
                    buttons[1] = Integer.toString(correct);
                    correctAnswer = 1;
                }
                answer = JOptionPane.showOptionDialog(null, "What location will the element be peeked from?", "Peek Prediction",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);
                if (answer == 0 && correctAnswer == 0) { //if they select the correct answer
                    JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                    count++;
                    predictionCount.put("CircularPeek", count);
                } else if(answer == 1 && correctAnswer == 1){ //if they selected the correct answer
                    JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                    count++;
                    predictionCount.put("CircularPeek", count);
                } else{
                    JOptionPane.showMessageDialog(null, "Sorry that was incorrect, please try again", "Incorrect", JOptionPane.ERROR_MESSAGE);
                }
                answered = true;
                break;
        }

        //For starting harder questions after getting the set number of predictions correct
        if (count == noPredictions && answered) {
            buttons = new String[]{"Progress", "Stay"};
            int options = JOptionPane.showOptionDialog(null, "You have predicted correctly again, would you like to progress to harder questions?", "Progress?",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
            if (options == 0) {
                System.out.println("ADD THING FOR HARDER PREDICTIONS");
                //Add code here for harder predictions
                theView.toggleHarderPredictions(false, true);
            }else{
                JOptionPane.showMessageDialog(null, "If you wish to access harder questions you can now enable them from the prediction menu", "Progress", JOptionPane.INFORMATION_MESSAGE);
                theView.toggleHarderPredictions(true, false);
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

                        // /*
                        Stack<Integer> stack = new Stack<>();
                        stack.add(1);
                        stack.add(2);
                        stack.add(3);
                        stack.add(4);
                        stack.add(5);
                        Queue<Integer> queue = new LinkedList<>();
                        queue.add(1);
                        queue.add(2);
                        queue.add(3);
                        queue.add(4);
                        queue.add(5);
                        HarderPredictions harderPredictions = new HarderPredictions();
                        harderPredictions.setModal(true);
                        harderPredictions.setLocationRelativeTo(theView);
                        harderPredictions.setQuestionLabel("Please click on the correct Stack after the following operations are made:");
                        harderPredictions.setOperationsLabel("stack.add(1); stack.add(2); stack.add(3); stack.add(4); stack.add(5);");
                        DrawHarderPredictions drawHarderPredictions = new DrawHarderPredictions();
                        drawHarderPredictions.setVisualisationType("Stack");
                        drawHarderPredictions.setStack(stack);
                        DrawHarderPredictions drawHarderPredictions1 = new DrawHarderPredictions();
                        drawHarderPredictions1.setVisualisationType("Stack");
                        drawHarderPredictions1.setStack(stack);
                        DrawHarderPredictions drawHarderPredictions2 = new DrawHarderPredictions();
                        drawHarderPredictions2.setVisualisationType("Stack");
                        drawHarderPredictions2.setStack(stack);
                        DrawHarderPredictions drawHarderPredictions3 = new DrawHarderPredictions();
                        drawHarderPredictions3.setVisualisationType("Queue");
                        drawHarderPredictions3.setQueue(queue);
                        harderPredictions.setPanelA(drawHarderPredictions);
                        harderPredictions.setPanelB(drawHarderPredictions1);
                        harderPredictions.setPanelC(drawHarderPredictions2);
                        harderPredictions.setPanelD(drawHarderPredictions3);
                        harderPredictions.setVisible(true); // */

                        System.out.println("Answer: " + harderPredictions.getAnswer());

                        //keep all this stuff
                        String input = JOptionPane.showInputDialog(null, "Enter a positive Integer of maximum 3 digits you want to push on to the Stack", "Push", JOptionPane.QUESTION_MESSAGE);
                        int toBePushed = Integer.parseInt(input);
                        if(!input.matches(regex)){
                            throw new java.lang.NumberFormatException("Not positive 3 digit Integer");
                        }
                        addStackOperation("Pushing: " + toBePushed);
                        addStackJavaOperations("stack.push(" + toBePushed + ")");
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
                addStackOperation("Popped: " + popped);
                addStackJavaOperations("stack.pop() - returns " + popped);
                if(predictionMode){
                    runPrediction("Pop");
                }
                theView.updateStackUI();
            } catch(EmptyStackException exception){
                addStackOperation("Cannot Pop: Stack Empty");
                JOptionPane.showMessageDialog(null, "Sorry the Stack is empty and therefore cannot be popped", "Stack Empty", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class StackPeekListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                int peeked = theModel.peek();
                addStackOperation("Peeked: " + peeked);
                addStackJavaOperations("stack.peek() - returns " + peeked);
                drawStack.highlight(peeked);
                if(predictionMode){
                    runPrediction("StackPeek");
                }
                theView.updateStackUI();
            } catch(EmptyStackException exception){
                addStackOperation("Cannot Peek: Stack Empty");
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
                        String input = JOptionPane.showInputDialog(null, "Enter a positive Integer of maximum 3 digits you want to enqueue to the Queue", "Enqueue", JOptionPane.QUESTION_MESSAGE);
                        int toBeQueued = Integer.parseInt(input);
                        if(!input.matches(regex)){
                            throw new java.lang.NumberFormatException("Not positive 3 digit Integer");
                        }
                        if(isCircular){
                            if(predictionMode){
                                runPrediction("CircularEnqueue");
                            }
                            theModel.enqueueCircular(toBeQueued);
                            addCircularQueueOperation("Enqueue: " + toBeQueued);
                            addCircularQueueJavaOperation("rear = (rear+1)%queue.length;");
                            addCircularQueueJavaOperation("queue[rear] = " + toBeQueued + ";");
                            addCircularQueueJavaOperation("--------------- Enqueue ---------------");
                        } else {
                            theModel.enqueue(toBeQueued);
                            if(predictionMode){
                                runPrediction("Enqueue");
                            }
                            addQueueOperation("Enqueue: " + toBeQueued);
                            addQueueJavaOperation("queue.add(" + toBeQueued + ")");
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
                    if(predictionMode){
                        runPrediction("CircularDequeue");
                    }
                    dequeue = theModel.dequeueCircular();
                    addCircularQueueOperation("Dequeue: " + dequeue);
                    addCircularQueueJavaOperation("returns " + dequeue);
                    addCircularQueueJavaOperation("return temp;");
                    addCircularQueueJavaOperation("front=(front+1)%queue.length;");
                    addCircularQueueJavaOperation("queue[front] = 0;");
                    addCircularQueueJavaOperation("int temp = queue[front];");
                    addCircularQueueJavaOperation("--------------- Dequeue ---------------");
                }else{
                    dequeue = theModel.dequeue();
                    addQueueOperation("Dequeue: " + dequeue);
                    addQueueJavaOperation("queue.poll() - returns " + dequeue);
                    if(predictionMode){
                        runPrediction("Dequeue");
                    }
                }
                theView.updateQueueUI();
            } catch(NullPointerException exception){
                if(isCircular){
                    addCircularQueueOperation("Cannot Dequeue: Queue Empty");
                }else{
                    addQueueOperation("Cannot Dequeue: Queue Empty");
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
                    if(predictionMode){
                        runPrediction("CircularPeek");
                    }
                    peeked = theModel.peekCircular();
                    addCircularQueueOperation("Peeked: " + peeked);
                    addCircularQueueJavaOperation("returns " + peeked);
                    addCircularQueueJavaOperation("return queue[front];");
                    addCircularQueueJavaOperation("------------------ Peek ------------------");
                }else{
                    peeked = theModel.peekQueue();
                    addQueueOperation("Peeked: " + peeked);
                    addQueueJavaOperation("queue.peek() - returns " + peeked);
                    if(predictionMode){
                        runPrediction("QueuePeek");
                    }
                }
                drawQueue.highlight(peeked);
                theView.updateQueueUI();
            } catch(NullPointerException exception){
                if(isCircular){
                    addCircularQueueOperation("Cannot Peek: Queue Empty");
                }else{
                    addQueueOperation("Cannot Peek: Queue Empty");
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
            noPredictions = predictionOptions.getNoPredictions();

            if(!predictionOptions.getIsBlank()){
                theModel.reset(); //clears the data stored in the model
                operationsListStack.clear();
                operationsListQueue.clear();
                operationsListCircularQueue.clear();
                theView.resetPreviousOperations(); //clears the data held within the JList
                createPreMadeData();
                drawQueue.toggleFirstRun();
                theView.updateStackUI();
                theView.updateQueueUI();
            }

            //toggle harder predictions to off
            theView.toggleHarderPredictions(false, false);

            // Set up prediction count
            predictionCount.put("Push", 0);
            predictionCount.put("Pop", 0);
            predictionCount.put("StackPeek", 0);
            predictionCount.put("Enqueue", 0);
            predictionCount.put("Dequeue", 0);
            predictionCount.put("QueuePeek", 0);
            predictionCount.put("CircularEnqueue", 0);
            predictionCount.put("CircularDequeue", 0);
            predictionCount.put("CircularPeek", 0);
        }
    }

    class RadioOffListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.toggleLabels(true);
            //toggle harder predictions to off
            theView.toggleHarderPredictions(false, false);
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
            theView.toggleTabbedPane(1);
            theView.updateQueueUI();
        }
    }

    class NormalQueueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            drawQueue.toggleCircular(false);
            isCircular = false;
            switchQueueOperationsList("Normal");
            theView.toggleTabbedPane(1);
            theView.updateQueueUI();
        }
    }

    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            theModel.reset(); //clears the data stored in the model
            operationsListStack.clear();
            operationsListQueue.clear();
            operationsListCircularQueue.clear();
            theView.resetPreviousOperations(); //clears the data held within the JList
            theView.updateStackUI();
            theView.updateQueueUI();
        }
    }

    class HarderPredictionsListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.SELECTED){
                JOptionPane.showMessageDialog(null, "Harder Questions Enabled", "Harder Questions", JOptionPane.INFORMATION_MESSAGE);
                //toggle harder predictions to on
                theView.toggleHarderPredictions(false, true);
            }
        }
    }


}