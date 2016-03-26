package src.Final.Controller;

import src.Final.GUIDialogs.CreateHarderPredictions;
import src.Final.GUIDialogs.DrawHarderPredictions;
import src.Final.GUIDialogs.HarderPredictions;
import src.Final.GUIDialogs.PredictionOptions;
import src.Final.Model.Model;
import src.Final.View.DrawQueueRepresentation;
import src.Final.View.DrawStackRepresentation;
import src.Final.View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The Controller class is used to communicate between the View and the Model.
 * All ActionListeners are contained here.
 *
 * @author Jamie
 * @since 22/02/2016.
 *
 */
public class Controller {
    private View theView;
    private Model theModel;
    private LinkedList<String> operationsListStack = new LinkedList<>();
    private LinkedList<String> operationsListArrayStack = new LinkedList<>();
    private LinkedList<String> operationsListQueue = new LinkedList<>();
    private LinkedList<String> operationsListCircularQueue = new LinkedList<>();
    private LinkedList<String> operationsListStackJava = new LinkedList<>();
    private LinkedList<String> operationsListArrayStackJava = new LinkedList<>();
    private LinkedList<String> operationsListQueueJava = new LinkedList<>();
    private LinkedList<String> operationsListCircularQueueJava = new LinkedList<>();
    private DrawStackRepresentation drawStack = new DrawStackRepresentation();
    private DrawQueueRepresentation drawQueue = new DrawQueueRepresentation();
    private boolean isCircular = false;
    private boolean isArray = false;
    private boolean predictionMode = false;
    private boolean harderPredictions = false;
    private int noPredictions;
    private HashMap<String, Integer> predictionCount = new HashMap<>();
    private int maxListSize = 45;
    String regex = "^[1-9]\\d{0,2}$"; //Regex for a positive integer, max 3 digits.

    /**
     * This class initialises the Controller, the Model and View are set from the passed in parameters.
     * Listeners are also added to the View from here.
     * The Stack and Queue panels in the View are also set here.
     *
     * @param view This is an instance of View the controller uses to manipulate the interface.
     * @param model This is the Model that holds all the data of the Stacks and Queues.
     */
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
        theView.addArrayStackListener(new ArrayStackListener());
        theView.addNormalStackListener(new NormalStackListener());
        theView.addResetListener(new ResetListener());
        theView.addHarderPredictionsListener(new HarderPredictionsListener());

        drawStack.setStack(theModel.getStack());
        theView.setStackPanel(drawStack);

        drawQueue.setQueue(theModel.getQueue());
        theView.setQueuePanel(drawQueue);
    }

    /**
     * This method adds an operation to the Stack regular operations list.
     * If the operations list is at its max size the first element
     * entered is removed.
     *
     * @param op This is the String to be added to the Operations List
     */
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

    /**
     * This method adds an operation to the Stack Java operations list.
     * If the operations list is at its max size the first element
     * entered is removed.
     *
     * @param op This is the String to be added to the Java Operations List
     */
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

    /**
     * This method adds an operation to the Array Stack regular operations list.
     * If the operations list is at its max size the first element
     * entered is removed.
     *
     * @param op This is the String to be added to the Operations List
     */
    public void addArrayStackOperation(String op){
        if(operationsListArrayStack.size() < maxListSize) { //Max size of stack operations list
            operationsListArrayStack.push(op);
        }else{
            operationsListArrayStack.removeLast();
            operationsListArrayStack.push(op);
        }
        String listData[] = operationsListArrayStack.toArray(new String[operationsListArrayStack.size()]);
        theView.setPreviousStackOperations(listData);
    }

    /**
     * This method adds an operation to the Array Stack Java operations list.
     * If the operations list is at its max size the first element
     * entered is removed.
     *
     * @param op This is the String to be added to the Operations List
     */
    public void addArrayStackJavaOperation(String op){
        if(operationsListArrayStackJava.size() < maxListSize){ //max size of stack operations list
            operationsListArrayStackJava.push(op);
        }else{
            operationsListArrayStackJava.removeLast();
            operationsListArrayStackJava.push(op);
        }
        String listData[] = operationsListArrayStackJava.toArray(new String[operationsListArrayStackJava.size()]);
        theView.setPreviousStackJavaOperations(listData);
    }

    /**
     * This method adds an operation to the Queue regular operations list.
     * If the operations list is at its max size the first element
     * entered is removed.
     *
     * @param op This is the String to be added to the Operations List
     */
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

    /**
     * This method adds an operation to the Queue Java operations list.
     * If the operations list is at its max size the first element
     * entered is removed.
     *
     * @param op This is the String to be added to the Operations List
     */
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

    /**
     * This method adds an operation to the Circular Queue regular operations list.
     * If the operations list is at its max size the first element
     * entered is removed.
     *
     * @param op This is the String to be added to the Operations List
     */
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

    /**
     * This method adds an operation to the Circular Queue Java operations list.
     * If the operations list is at its max size the first element
     * entered is removed.
     *
     * @param op This is the String to be added to the Operations List
     */
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

    /**
     * This method will switch between the Queue operation lists.
     * If the parameter is Circular then the operations lists
     * now displayed will be Circular and the same will happen for "Normal"
     *
     * @param s This string controls which operations lists are switched
     */
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

    /**
     * This method will switch between the Stack operation lists.
     * If the parameter is Array then the operations lists
     * now displayed will be Array and the same will happen for "Normal"
     *
     * @param s This string controls which operations lists are switched
     */
    public void switchStackOperationsList(String s){
        theView.setPreviousStackOperations(new String[0]);
        theView.setPreviousStackJavaOperations(new String[0]);
        String listData[];
        if(s.equals("Array")){
            listData = operationsListArrayStack.toArray(new String[operationsListArrayStack.size()]);
            theView.setPreviousStackOperations(listData);
            listData = operationsListArrayStackJava.toArray(new String[operationsListArrayStackJava.size()]);
            theView.setPreviousStackJavaOperations(listData);
        }else if(s.equals("Normal")){
            listData = operationsListStack.toArray(new String[operationsListStack.size()]);
            theView.setPreviousStackOperations(listData);
            listData = operationsListStackJava.toArray(new String[operationsListStackJava.size()]);
            theView.setPreviousStackJavaOperations(listData);
        }
    }

    /**
     * This method will reset or clear all current data.
     * The model is cleared in addition to the operation lists.
     * The visualisations are then updated to reflect this.
     */
    public void reset(){
        theModel.reset(); //clears the data stored in the model
        operationsListStack.clear();
        operationsListQueue.clear();
        operationsListCircularQueue.clear();
        operationsListArrayStack.clear();
        operationsListStackJava.clear();
        operationsListQueueJava.clear();
        operationsListCircularQueueJava.clear();
        operationsListArrayStackJava.clear();
        theView.resetPreviousOperations(); //clears the data held within the JList
        theView.updateStackUI();
        theView.updateQueueUI();
    }

    /**
     * This method creates random data for use with the predictions.
     * All implementations are populated with random numbers.
     * 60% of the time dequeues are added to Circular Queue.
     * This is to allow the head and tail to move around.
     */
    public void createPreMadeData(){
        int min = 1;
        int max = 99;
        for(int i = 0; i < ThreadLocalRandom.current().nextInt(4, 10 + 1); i++){ //run between 4 to 10 times
            theModel.push(ThreadLocalRandom.current().nextInt(min, max + 1));
            theModel.arrayPush(ThreadLocalRandom.current().nextInt(min, max + 1));
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

    /**
     * This method runs predictions based on the parameter passed in.
     * It takes in the name of the operation that activated it, e.g. Push
     * and then runs the relevant prediction. A JOptionPane with a questions is displayed
     * and a user must select which answer they think is correct. They are then told if
     * they answered correctly or not. If all answers in an operation group have been
     * answered correctly the amount of times specified in the prediction options then
     * a pop up will alert them that Harder Predictions are now available from the menu.
     *
     * @param type This string details the type of prediction about to take ploace
     */
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
                    answer = JOptionPane.showOptionDialog(null, "What location will the element be enqueued to?", "Enqueue Prediction",
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
                if(count < noPredictions){
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
                }
                break;
            case "ArrayPush":
                if(count < noPredictions) {
                    int correct = theModel.getTop() + 1;
                    int other;
                    int correctAnswer;
                    if (correct > 0 && correct != 9) {
                        if (ThreadLocalRandom.current().nextInt(1, 10 + 1) < 6) {
                            other = correct + 1;
                        } else {
                            other = correct - 1;
                        }
                    } else if (correct == 9) {
                        other = correct - 1;
                    } else {
                        other = correct + 1;
                    }
                    buttons = new String[2];
                    if (ThreadLocalRandom.current().nextInt(1, 10 + 1) < 6) {
                        buttons[0] = Integer.toString(correct);
                        buttons[1] = Integer.toString(other);
                        correctAnswer = 0;
                    } else {
                        buttons[1] = Integer.toString(correct);
                        buttons[0] = Integer.toString(other);
                        correctAnswer = 1;
                    }
                    answer = JOptionPane.showOptionDialog(null, "What location will the element be pushed to?", "Push Prediction",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);
                    if (answer == 0 && correctAnswer == 0) { //if they select the correct answer
                        JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        count++;
                        predictionCount.put("ArrayPush", count);
                    } else if (answer == 1 && correctAnswer == 1) { //if they selected the correct answer
                        JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        count++;
                        predictionCount.put("ArrayPush", count);
                    } else {
                        JOptionPane.showMessageDialog(null, "Sorry that was incorrect, please try again", "Incorrect", JOptionPane.ERROR_MESSAGE);
                    }
                    answered = true;
                }
                break;
            case "ArrayPop":
                if(count < noPredictions) {
                    int correct = theModel.getTop() + 1;
                    int other;
                    if (correct > 0 && correct != 9) {
                        if (ThreadLocalRandom.current().nextInt(1, 10 + 1) < 6) {
                            other = correct + 1;
                        } else {
                            other = correct - 1;
                        }
                    } else if (correct == 9) {
                        other = correct - 1;
                    } else {
                        other = correct + 1;
                    }
                    int correctAnswer;
                    buttons = new String[2];
                    if (ThreadLocalRandom.current().nextInt(1, 10 + 1) < 6) {
                        buttons[0] = Integer.toString(correct);
                        buttons[1] = Integer.toString(other);
                        correctAnswer = 0;
                    } else {
                        buttons[1] = Integer.toString(correct);
                        buttons[0] = Integer.toString(other);
                        correctAnswer = 1;
                    }
                    answer = JOptionPane.showOptionDialog(null, "What location will the element be popped from?", "Pop Prediction",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);
                    if (answer == 0 && correctAnswer == 0) { //if they select the correct answer
                        JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        count++;
                        predictionCount.put("ArrayPop", count);
                    } else if (answer == 1 && correctAnswer == 1) { //if they selected the correct answer
                        JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        count++;
                        predictionCount.put("ArrayPop", count);
                    } else {
                        JOptionPane.showMessageDialog(null, "Sorry that was incorrect, please try again", "Incorrect", JOptionPane.ERROR_MESSAGE);
                    }
                }
                    answered = true;
                break;
            case "ArrayPeek":
                if(count < noPredictions) {
                    int correct = theModel.getTop();
                    int other;
                    if (correct > 0 && correct != 9) {
                        if (ThreadLocalRandom.current().nextInt(1, 10 + 1) < 6) {
                            other = correct + 1;
                        } else {
                            other = correct - 1;
                        }
                    } else if (correct == 9) {
                        other = correct - 1;
                    } else {
                        other = correct + 1;
                    }
                    int correctAnswer;
                    buttons = new String[2];
                    if (ThreadLocalRandom.current().nextInt(1, 10 + 1) < 6) {
                        buttons[0] = Integer.toString(correct);
                        buttons[1] = Integer.toString(other);
                        correctAnswer = 0;
                    } else {
                        buttons[1] = Integer.toString(correct);
                        buttons[0] = Integer.toString(other);
                        correctAnswer = 1;
                    }
                    answer = JOptionPane.showOptionDialog(null, "What location will the element be peeked from?", "Peek Prediction",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);
                    if (answer == 0 && correctAnswer == 0) { //if they select the correct answer
                        JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        count++;
                        predictionCount.put("ArrayPeek", count);
                    } else if (answer == 1 && correctAnswer == 1) { //if they selected the correct answer
                        JOptionPane.showMessageDialog(null, "You predicted correctly, well done!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        count++;
                        predictionCount.put("ArrayPeek", count);
                    } else {
                        JOptionPane.showMessageDialog(null, "Sorry that was incorrect, please try again", "Incorrect", JOptionPane.ERROR_MESSAGE);
                    }
                    answered = true;
                }
                break;
        }

        //For starting harder questions after getting the set number of predictions correct
        if(((predictionCount.get("Push") == noPredictions && predictionCount.get("Pop") == noPredictions && predictionCount.get("StackPeek") == noPredictions) ||
                (predictionCount.get("Enqueue") == noPredictions && predictionCount.get("Dequeue") == noPredictions && predictionCount.get("QueuePeek") == noPredictions) ||
                (predictionCount.get("CircularEnqueue") == noPredictions && predictionCount.get("CircularDequeue") == noPredictions && predictionCount.get("CircularPeek") == noPredictions) ||
                (predictionCount.get("ArrayPush") == noPredictions && predictionCount.get("ArrayPop") == noPredictions && predictionCount.get("ArrayPeek") == noPredictions))
                        && answered){
            JOptionPane.showMessageDialog(null, "If you wish to access harder questions you can now enable them from the prediction menu", "Progress", JOptionPane.INFORMATION_MESSAGE);
            theView.toggleHarderPredictions(true, false);
        }
    }

    /**
     * This method will create a JDialog for the harder questions.
     * The correct panel is selected at random to stop users simply
     * memorising the correct panel. Once answered the user is informed
     * if their answer was correct or not.
     *
     * @param type This string checks which Harder Prediction will take place
     */
    public void runHarderPredictions(String type){
        if(type.equals("Stack")){
            CreateHarderPredictions createHarderPredictions = new CreateHarderPredictions(type);

            DrawHarderPredictions drawHarderPredictions = new DrawHarderPredictions();
            drawHarderPredictions.setVisualisationType(type);
            drawHarderPredictions.setStack(createHarderPredictions.getStack());

            HarderPredictions harderPredictions = new HarderPredictions();
            harderPredictions.setModal(true);
            harderPredictions.setLocationRelativeTo(theView);

            harderPredictions.setQuestionLabel("Please click on the correct " + type + " after the following operations are made:");
            harderPredictions.setOperationsLabel(createHarderPredictions.getOperationsString());

            ArrayList<Stack<Integer>> otherStacks = createHarderPredictions.getOtherStacks();

            DrawHarderPredictions drawHarderPredictions2 = new DrawHarderPredictions();
            drawHarderPredictions2.setVisualisationType(type);
            drawHarderPredictions2.setStack(otherStacks.get(0));

            DrawHarderPredictions drawHarderPredictions3 = new DrawHarderPredictions();
            drawHarderPredictions3.setVisualisationType(type);
            drawHarderPredictions3.setStack(otherStacks.get(1));

            DrawHarderPredictions drawHarderPredictions4 = new DrawHarderPredictions();
            drawHarderPredictions4.setVisualisationType(type);
            drawHarderPredictions4.setStack(otherStacks.get(2));


            int correctPanel = ThreadLocalRandom.current().nextInt(1, 4 + 1);
            String correctAnswer = "";

            switch (correctPanel){
                case 1:
                    harderPredictions.setPanelA(drawHarderPredictions);
                    correctAnswer = "a";
                    harderPredictions.setPanelB(drawHarderPredictions2);
                    harderPredictions.setPanelC(drawHarderPredictions3);
                    harderPredictions.setPanelD(drawHarderPredictions4);
                    break;
                case 2:
                    harderPredictions.setPanelB(drawHarderPredictions);
                    correctAnswer = "b";
                    harderPredictions.setPanelC(drawHarderPredictions2);
                    harderPredictions.setPanelD(drawHarderPredictions3);
                    harderPredictions.setPanelA(drawHarderPredictions4);
                    break;
                case 3:
                    harderPredictions.setPanelC(drawHarderPredictions);
                    correctAnswer = "c";
                    harderPredictions.setPanelD(drawHarderPredictions2);
                    harderPredictions.setPanelA(drawHarderPredictions3);
                    harderPredictions.setPanelB(drawHarderPredictions4);
                    break;
                case 4:
                    harderPredictions.setPanelD(drawHarderPredictions);
                    correctAnswer = "d";
                    harderPredictions.setPanelA(drawHarderPredictions2);
                    harderPredictions.setPanelB(drawHarderPredictions3);
                    harderPredictions.setPanelC(drawHarderPredictions4);
                    break;
            }

            harderPredictions.setVisible(true);

            if(harderPredictions.getAnswer().equals(correctAnswer)){
                JOptionPane.showMessageDialog(null, "You answered correctly! Click another operation button to try another", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Sorry that was incorrect, Click another operation button to try another", "Incorrect", JOptionPane.ERROR_MESSAGE);
            }

        }else if(type.equals("Queue")){
            CreateHarderPredictions createHarderPredictions = new CreateHarderPredictions(type);

            DrawHarderPredictions drawHarderPredictions = new DrawHarderPredictions();
            drawHarderPredictions.setVisualisationType(type);
            drawHarderPredictions.setQueue(createHarderPredictions.getQueue());

            HarderPredictions harderPredictions = new HarderPredictions();
            harderPredictions.setModal(true);
            harderPredictions.setLocationRelativeTo(theView);

            harderPredictions.setQuestionLabel("Please click on the correct " + type + " after the following operations are made:");
            harderPredictions.setOperationsLabel(createHarderPredictions.getOperationsString());

            ArrayList<Queue<Integer>> otherQueues = createHarderPredictions.getOtherQueues();

            DrawHarderPredictions drawHarderPredictions2 = new DrawHarderPredictions();
            drawHarderPredictions2.setVisualisationType(type);
            drawHarderPredictions2.setQueue(otherQueues.get(0));

            DrawHarderPredictions drawHarderPredictions3 = new DrawHarderPredictions();
            drawHarderPredictions3.setVisualisationType(type);
            drawHarderPredictions3.setQueue(otherQueues.get(1));

            DrawHarderPredictions drawHarderPredictions4 = new DrawHarderPredictions();
            drawHarderPredictions4.setVisualisationType(type);
            drawHarderPredictions4.setQueue(otherQueues.get(2));



            int correctPanel = ThreadLocalRandom.current().nextInt(1, 4 + 1);
            String correctAnswer = "";

            switch (correctPanel){
                case 1:
                    harderPredictions.setPanelA(drawHarderPredictions);
                    correctAnswer = "a";
                    harderPredictions.setPanelB(drawHarderPredictions2);
                    harderPredictions.setPanelC(drawHarderPredictions3);
                    harderPredictions.setPanelD(drawHarderPredictions4);
                    break;
                case 2:
                    harderPredictions.setPanelB(drawHarderPredictions);
                    correctAnswer = "b";
                    harderPredictions.setPanelC(drawHarderPredictions2);
                    harderPredictions.setPanelD(drawHarderPredictions3);
                    harderPredictions.setPanelA(drawHarderPredictions4);
                    break;
                case 3:
                    harderPredictions.setPanelC(drawHarderPredictions);
                    correctAnswer = "c";
                    harderPredictions.setPanelD(drawHarderPredictions2);
                    harderPredictions.setPanelA(drawHarderPredictions3);
                    harderPredictions.setPanelB(drawHarderPredictions4);
                    break;
                case 4:
                    harderPredictions.setPanelD(drawHarderPredictions);
                    correctAnswer = "d";
                    harderPredictions.setPanelA(drawHarderPredictions2);
                    harderPredictions.setPanelB(drawHarderPredictions3);
                    harderPredictions.setPanelC(drawHarderPredictions4);
                    break;
            }

            harderPredictions.setVisible(true);

            if(harderPredictions.getAnswer().equals(correctAnswer)){
                JOptionPane.showMessageDialog(null, "You answered correctly! Click another operation button to try another", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Sorry that was incorrect, Click another operation button to try another", "Incorrect", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    /*
    This is a block of inner classes which create ActionListeners for the buttons
     */

    /**
     * When the Push button is clicked the following code is run.
     * If the Stack is not full a user is asked which number they
     * want to push, if the number passes the pattern matching it
     * is added to the Stack and the relevant operation list additions
     * are made. If predictions or harder predictions are enabled they
     * are also activated from this button.
     */
    class PushListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!isArray && operationsListStackJava.size() == 0 && theModel.getStack().size() == 0){
                addStackJavaOperations("Stack<Integer> stack = new Stack<>();");
                addStackOperation("Create Stack");
            }
            int maxStackSize = 10;
            if(theModel.getStack().size() == maxStackSize || theModel.getArrayStack().getTop()  == (maxStackSize - 1)){
                JOptionPane.showMessageDialog(null, "Sorry the Stack is full and nothing can be pushed", "Stack Full", JOptionPane.ERROR_MESSAGE);
            } else{
                while(true){
                    try{
                        if(harderPredictions){
                            runHarderPredictions("Stack");
                            break;
                        }
                        String input = JOptionPane.showInputDialog(null, "Enter a positive Integer of maximum 3 digits you want to push on to the Stack", "Push", JOptionPane.QUESTION_MESSAGE);
                        int toBePushed = Integer.parseInt(input);
                        if(!input.matches(regex)){
                            throw new java.lang.NumberFormatException("Not positive 3 digit Integer");
                        }
                        if(isArray){
                            if(predictionMode){
                                runPrediction("ArrayPush");
                            }
                            addArrayStackOperation("Pushing: " + toBePushed);
                            addArrayStackJavaOperation("stack[top] = " + toBePushed + ";");
                            addArrayStackJavaOperation("top++;");
                            addArrayStackJavaOperation("---------------- Push ----------------");
                            theModel.arrayPush(toBePushed);
                        }else{
                            if(predictionMode){
                                runPrediction("Push");
                            }
                            addStackOperation("Pushing: " + toBePushed);
                            addStackJavaOperations("stack.push(" + toBePushed + ");");
                            theModel.push(toBePushed);
                        }
                        theView.updateStackUI();
                        break;
                    }
                    catch(java.lang.NumberFormatException exception){
                        if(exception.getMessage().equals("null")){
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Sorry that was not the correct format, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    /**
     * When the Pop button is clicked the following code is run.
     * If the Stack is empty an error message is displayed.
     * The relevant operation list additions are also made.
     * If predictions or harder predictions are enabled they
     * are also activated from this button.
     */
    class PopListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(harderPredictions){
                    runHarderPredictions("Stack");
                }else{
                    if(isArray){
                        int popped = theModel.arrayPop();
                        if(predictionMode){
                            runPrediction("ArrayPop");
                        }
                        addArrayStackOperation("Popped: " + popped);
                        addArrayStackJavaOperation("returns " + popped);
                        addArrayStackJavaOperation("return temp;");
                        addArrayStackJavaOperation("top--;");
                        addArrayStackJavaOperation("int temp = stack[top];");
                        addArrayStackJavaOperation("---------------- Pop ----------------");
                    }else{
                        int popped = theModel.pop();
                        if(predictionMode){
                            runPrediction("Pop");
                        }
                        addStackOperation("Popped: " + popped);
                        addStackJavaOperations("stack.pop(); - returns " + popped);
                    }
                    theView.updateStackUI();
                }
            } catch(EmptyStackException exception){
                if(isArray){
                    addArrayStackOperation("Cannot Pop: Stack Empty");
                    addArrayStackJavaOperation("EmptyStackException");
                }else{
                    addStackOperation("Cannot Pop: Stack Empty");
                    addStackJavaOperations("EmptyStackException");
                }
                JOptionPane.showMessageDialog(null, "Sorry the Stack is empty and therefore cannot be popped", "Stack Empty", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * When the Peek button is clicked the following code is run.
     * If the Stack is empty an error message is displayed.
     * The relevant operation list additions are also made.
     * If predictions or harder predictions are enabled they
     * are also activated from this button.
     */
    class StackPeekListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(harderPredictions){
                    runHarderPredictions("Stack");
                }else{
                    if(isArray){
                        int peeked = theModel.arrayPeek();
                        if(predictionMode){
                            runPrediction("ArrayPeek");
                        }
                        addArrayStackOperation("Peeked: " + peeked);
                        addArrayStackJavaOperation("returns " + peeked);
                        addArrayStackJavaOperation("return stack[top];");
                        addArrayStackJavaOperation("---------------- Peek ----------------");
                        drawStack.highlight(peeked);

                    }else{
                        int peeked = theModel.peek();
                        if(predictionMode){
                            runPrediction("StackPeek");
                        }
                        addStackOperation("Peeked: " + peeked);
                        addStackJavaOperations("stack.peek(); - returns " + peeked);
                        drawStack.highlight(peeked);
                    }
                }
                theView.updateStackUI();
            } catch(EmptyStackException exception){
                if(isArray){
                    addArrayStackOperation("Cannot Peek: Stack Empty");
                    addArrayStackJavaOperation("EmptyStackException");
                }else{
                    addStackOperation("Cannot Peek: Stack Empty");
                    addStackJavaOperations("EmptyStackException");
                }
                JOptionPane.showMessageDialog(null, "Sorry the Stack is empty and therefore cannot be peeked", "Stack Empty", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * When the Enqueue button is clicked the following code is run.
     * If the Queue is not full a user is asked which number they
     * want to enqueue, if the number passes the pattern matching it
     * is added to the Queue and the relevant operation list additions
     * are made. If predictions or harder predictions are enabled they
     * are also activated from this button.
     */
    class EnqueueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!isCircular && operationsListQueueJava.size() == 0 && theModel.getQueue().size() == 0){
                addQueueJavaOperation("Queue<Integer> queue = new LinkedList<>();");
                addQueueOperation("Create Queue");
            }
            int maxQueueSize = 20;
            if(theModel.getQueue().size() == maxQueueSize || theModel.getCircularQueue().getSize() == maxQueueSize){
                JOptionPane.showMessageDialog(null, "Sorry the Queue is full and nothing can be enqueued", "Queue Full", JOptionPane.ERROR_MESSAGE);
            } else{
                while(true){
                    try{
                        if(harderPredictions){
                            runHarderPredictions("Queue");
                            break;
                        }
                        String input = JOptionPane.showInputDialog(null, "Enter a positive Integer of maximum 3 digits you want to enqueue to the Queue", "Enqueue", JOptionPane.QUESTION_MESSAGE);
                        int toBeQueued = Integer.parseInt(input);
                        if(!input.matches(regex)){
                            throw new java.lang.NumberFormatException("Not positive 3 digit Integer");
                        }
                        if(isCircular){
                            if(predictionMode){
                                runPrediction("CircularEnqueue");
                            }
                            if(theModel.getCircularQueue().getSize() == 10){
                                theModel.enqueueCircular(toBeQueued);
                                addCircularQueueOperation("Enqueue: " + toBeQueued);
                                addCircularQueueJavaOperation("size++;");
                                addCircularQueueJavaOperation("tail = (tail+1)%queue.length;");
                                addCircularQueueJavaOperation("queue[tail] = " + toBeQueued + ";");
                                addCircularQueueJavaOperation("queue = newQ");
                                addCircularQueueJavaOperation("head = 0;");
                                addCircularQueueJavaOperation("head=(head+1) % queue.length;}");
                                addCircularQueueJavaOperation("newQ[i] = queue[head];");
                                addCircularQueueJavaOperation("for(int i=0; i < size; i++){");
                                addCircularQueueJavaOperation("int newQ[] = new int[(queue.length * 2)];");
                                addCircularQueueJavaOperation("--------------- Enqueue ---------------");
                            }else{
                                theModel.enqueueCircular(toBeQueued);
                                addCircularQueueOperation("Enqueue: " + toBeQueued);
                                addCircularQueueJavaOperation("tail = (tail+1)%queue.length;");
                                addCircularQueueJavaOperation("queue[tail] = " + toBeQueued + ";");
                                addCircularQueueJavaOperation("--------------- Enqueue ---------------");
                            }
                        } else {
                            theModel.enqueue(toBeQueued);
                            if(predictionMode){
                                runPrediction("Enqueue");
                            }
                            addQueueOperation("Enqueue: " + toBeQueued);
                            addQueueJavaOperation("queue.add(" + toBeQueued + ");");
                        }
                        theView.updateQueueUI();
                        break;
                    }
                    catch(java.lang.NumberFormatException exception){
                        if(exception.getMessage().equals("null")){
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Sorry that was not the correct format, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    /**
     * When the Dequeue button is clicked the following code is run.
     * If the Queue is empty an error message is displayed.
     * The relevant operation list additions are also made.
     * If predictions or harder predictions are enabled they
     * are also activated from this button.
     */
    class DequeueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(harderPredictions){
                    runHarderPredictions("Queue");
                }else{
                    int dequeue;
                    if(isCircular){
                        theModel.peekCircular();
                        if(predictionMode){
                            runPrediction("CircularDequeue");
                        }
                        dequeue = theModel.dequeueCircular();
                        addCircularQueueOperation("Dequeue: " + dequeue);
                        addCircularQueueJavaOperation("returns " + dequeue);
                        addCircularQueueJavaOperation("return temp;");
                        addCircularQueueJavaOperation("head=(head+1)%queue.length;");
                        addCircularQueueJavaOperation("queue[head] = 0;");
                        addCircularQueueJavaOperation("int temp = queue[head];");
                        addCircularQueueJavaOperation("--------------- Dequeue ---------------");
                    }else{
                        theModel.peekQueue();
                        if(predictionMode){
                            runPrediction("Dequeue");
                        }
                        dequeue = theModel.dequeue();
                        addQueueOperation("Dequeue: " + dequeue);
                        addQueueJavaOperation("queue.poll(); - returns " + dequeue);
                    }
                }
                theView.updateQueueUI();
            } catch(NullPointerException exception){
                if(isCircular){
                    addCircularQueueOperation("Cannot Dequeue: Queue Empty");
                    addCircularQueueJavaOperation("NullPointerException");
                }else{
                    addQueueOperation("Cannot Dequeue: Queue Empty");
                    addQueueJavaOperation("NullPointerException");
                }
                JOptionPane.showMessageDialog(null, "Sorry the Queue is empty and therefore cannot be dequeued", "Queue Empty", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * When the Peek button is clicked the following code is run.
     * If the Queue is empty an error message is displayed.
     * The relevant operation list additions are also made.
     * If predictions or harder predictions are enabled they
     * are also activated from this button.
     */
    class QueuePeekListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(harderPredictions){
                    runHarderPredictions("Queue");
                }else{
                    int peeked;
                    if(isCircular){
                        peeked = theModel.peekCircular();
                        if(predictionMode){
                            runPrediction("CircularPeek");
                        }
                        addCircularQueueOperation("Peeked: " + peeked);
                        addCircularQueueJavaOperation("returns " + peeked);
                        addCircularQueueJavaOperation("return queue[head];");
                        addCircularQueueJavaOperation("------------------ Peek ------------------");
                    }else{
                        peeked = theModel.peekQueue();
                        if(predictionMode){
                            runPrediction("QueuePeek");
                        }
                        addQueueOperation("Peeked: " + peeked);
                        addQueueJavaOperation("queue.peek(); - returns " + peeked);
                    }
                    drawQueue.highlight(peeked);
                    theView.updateQueueUI();
                }
            } catch(NullPointerException exception){
                if(isCircular){
                    addCircularQueueOperation("Cannot Peek: Queue Empty");
                    addCircularQueueJavaOperation("NullPointerException");
                }else{
                    addQueueOperation("Cannot Peek: Queue Empty");
                    addQueueJavaOperation("NullPointerException");
                }
                JOptionPane.showMessageDialog(null, "Sorry the Queue is empty and therefore cannot be peeked", "Queue Empty", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * If the on radio box is selected from the Predictions menu
     * the following code is run. Prediction Options are displayed
     * and then saved. Labels for the buttons are also hidden.
     */
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
                reset();
                createPreMadeData();
                drawQueue.toggleFirstRun();
                drawStack.toggleFirstRun();
                theView.updateStackUI();
                theView.updateQueueUI();
            }

            //toggle harder predictions to off
            theView.toggleHarderPredictions(false, false);

            // Set up prediction count
            predictionCount.put("Push", 0);
            predictionCount.put("Pop", 0);
            predictionCount.put("StackPeek", 0);
            predictionCount.put("ArrayPush", 0);
            predictionCount.put("ArrayPop", 0);
            predictionCount.put("ArrayPeek", 0);
            predictionCount.put("Enqueue", 0);
            predictionCount.put("Dequeue", 0);
            predictionCount.put("QueuePeek", 0);
            predictionCount.put("CircularEnqueue", 0);
            predictionCount.put("CircularDequeue", 0);
            predictionCount.put("CircularPeek", 0);
        }
    }

    /**
     * If the off radio button is selected from the Predictions menu
     * the following code is run. Predictions are turned off and the
     * labels are set to display again.
     */
    class RadioOffListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            theView.toggleLabels(true);
            //toggle harder predictions to off
            theView.toggleHarderPredictions(false, false);
            theView.toggleCircularQueue(true);
            theView.toggleArrayStack(true);
            predictionMode = false;
            harderPredictions = false;
        }
    }

    /**
     * This switches to the Circular Queue from the Standard
     * Java Library Queue.
     */
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

    /**
     * This switches to the Standard Java Library Queue
     * from the Circular Queue.
     */
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

    /**
     * This switches to the Array Stack from the
     * standard library Stack.
     */
    class ArrayStackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            drawStack.toggleArray(true);
            drawStack.setArrayStack(theModel.getArrayStack());
            isArray = true;
            switchStackOperationsList("Array");
            theView.toggleTabbedPane(0);
            theView.updateStackUI();
        }
    }

    /**
     * This switches to the standard java library Stack
     * from the Array Stack.
     */
    class NormalStackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            drawStack.toggleArray(false);
            isArray = false;
            switchStackOperationsList("Normal");
            theView.toggleTabbedPane(0);
            theView.updateStackUI();
        }
    }

    /**
     * This runs the reset method to clear all data held.
     */
    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            reset();
        }
    }

    /**
     * This turns on harder predictions.
     */
    class HarderPredictionsListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.SELECTED){
                JOptionPane.showMessageDialog(null, "Harder Questions are now enabled, simply click any operation button for Stack or Queue to try them out!", "Harder Questions Enabled", JOptionPane.INFORMATION_MESSAGE);
                theView.toggleCircularQueue(false);
                theView.toggleArrayStack(false);
                harderPredictions = true;
                //toggle harder predictions to on
                theView.toggleHarderPredictions(false, true);
                reset();
            }
        }
    }


}
