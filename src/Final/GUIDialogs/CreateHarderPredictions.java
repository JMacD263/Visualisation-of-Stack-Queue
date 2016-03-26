package src.Final.GUIDialogs;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is used to create the Harder Predictions.
 */
public class CreateHarderPredictions {
    ArrayList<String> operationsList;
    String[] stackOperations = {"push", "pop", "peek"};
    String[] queueOperations = {"enqueue", "dequeue", "peek"};
    String operationsString = "";
    Queue<Integer> globalQueue;
    Stack<Integer> globalStack;
    int stackSize;
    int queueSize;

    /**
     * Initialises the operations list and runs the populateOperationsList method.
     *
     * @param type Specifies if the Harder Prediction is for Stack or Queue.
     */
    public CreateHarderPredictions(String type){
        operationsList =  new ArrayList<>();
        populateOperationsList(type);
    }

    /**
     * This method creates the operation list for the harder prediction.
     * Depending on the type passed in a list for Stack or Queue is made.
     *
     * @param type Specifies if the Harder Prediction is for Stack or Queue.
     */
    private void populateOperationsList(String type) {
        int min = 4;
        int max = 5;
        boolean peekAdded = false;
        switch(type){
            case "Stack":
                for(int i = 0; i < ThreadLocalRandom.current().nextInt(min, max + 1); i++){
                    operationsList.add(stackOperations[0]);
                    if(ThreadLocalRandom.current().nextInt(1, 10 + 1) > 7){
                        operationsList.add(stackOperations[1]);
                    }
                    if(ThreadLocalRandom.current().nextInt(1, 10 + 1) > 8 && !peekAdded){
                        operationsList.add(stackOperations[2]);
                        peekAdded = true;
                    }
                }
                operationsList.add(stackOperations[0]);
                if(ThreadLocalRandom.current().nextInt(1, 2 + 1) == 2){
                    operationsList.add(stackOperations[1]);
                }
                break;
            case "Queue":
                for(int i = 0; i < ThreadLocalRandom.current().nextInt(min, max + 1); i++){
                    operationsList.add(queueOperations[0]);
                    if(ThreadLocalRandom.current().nextInt(1, 10 + 1) > 7){
                        operationsList.add(queueOperations[1]);
                    }
                    if(ThreadLocalRandom.current().nextInt(1, 10 + 1) < 3 && !peekAdded){
                        operationsList.add(queueOperations[2]);
                        peekAdded = true;
                    }
                }
                operationsList.add(queueOperations[0]);
                if(ThreadLocalRandom.current().nextInt(1, 2 + 1) == 2){
                    operationsList.add(queueOperations[1]);
                }
                break;
        }
    }

    /**
     * This method creates a Stack from the operations list and
     * populates it with random numbers between 1 and 9.
     *
     * @return Returns the Stack made from the operations list.
     */
    public Stack<Integer> getStack(){
        Stack<Integer> stack = new Stack<>();
        int[] values = {1,2,3,4,5,6,7,8,9};
        for(String op: operationsList){
            switch (op) {
                case "push":
                    boolean same = true;
                    while (same) {
                        int val = ThreadLocalRandom.current().nextInt(0, 8 + 1);
                        if (values[val] != 0) {
                            stack.push(values[val]);
                            operationsString += "stack.push(" + values[val] +"); ";
                            values[val] = 0;
                            same = false;
                        }
                    }
                    break;
                case "pop":
                    stack.pop();
                    operationsString += "stack.pop(); ";
                    break;
                case "peek":
                    operationsString += "stack.peek(); ";
                    break;
            }
        }
        globalStack = stack;
        stackSize = stack.size();
        return stack;
    }

    /**
     * This method creates a Queue from the operations list and
     * populates it with random numbers between 1 and 9.
     *
     * @return Returns the Queue made from the operations list.
     */
    public Queue<Integer> getQueue(){
        Queue<Integer> queue = new LinkedList<>();
        int[] values = {1,2,3,4,5,6,7,8,9};
        for(String op: operationsList){
            switch (op) {
                case "enqueue":
                    boolean same = true;
                    while (same) {
                        int val = ThreadLocalRandom.current().nextInt(0, 8 + 1);
                        if (values[val] != 0) {
                            queue.add(values[val]);
                            operationsString += "queue.add(" + values[val] +"); ";
                            values[val] = 0;
                            same = false;
                        }
                    }
                    break;
                case "dequeue":
                    queue.poll();
                    operationsString += "queue.poll(); ";
                    break;
                case "peek":
                    operationsString += "queue.peek(); ";
                    break;
            }
        }
        globalQueue = queue;
        queueSize = queue.size();
        return queue;
    }

    /**
     * This method returns the operation list.
     *
     * @return returns the operation list to be added to harder question dialog.
     */
    public String getOperationsString(){
        return operationsString;
    }

    /**
     * This method generates 3 random Stacks similar to the
     * correct Stack. These Stacks are checked to ensure they
     * are not the same as the correct Stack and not empty.
     *
     * @return Returns the 3 incorrect Stacks for the Harder Prediction.
     */
    public ArrayList<Stack<Integer>> getOtherStacks(){
        ArrayList<Stack<Integer>> stacksList = new ArrayList<>();
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> stack3 = new Stack<>();

        String delimiter = " ";
        ArrayList<String> tokenizedOperations = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(operationsString, delimiter);
        while (st.hasMoreElements()) {
            tokenizedOperations.add(st.nextToken());
        }

        for(String operation: tokenizedOperations){
            if(operation.contains("push")){
                int rand = ThreadLocalRandom.current().nextInt(1, 10 + 1);
                if(rand > 3){
                    stack1.push(Integer.parseInt(operation.replaceAll("[\\D]", "")));
                }
                if(rand < 6){
                    stack2.push(Integer.parseInt(operation.replaceAll("[\\D]", "")));
                }
                if(rand > 6){
                    stack3.push(Integer.parseInt(operation.replaceAll("[\\D]", "")));
                }
                if(ThreadLocalRandom.current().nextInt(1, 10 + 1) > 7){
                    stack1.push(ThreadLocalRandom.current().nextInt(1, 9 + 1));
                    stack2.push(ThreadLocalRandom.current().nextInt(1, 9 + 1));
                    stack3.push(ThreadLocalRandom.current().nextInt(1, 9 + 1));
                }
            }else if(operation.contains("pop")){
            try{
                int rand = ThreadLocalRandom.current().nextInt(1, 10 + 1);
                if(rand < 4){
                    stack1.pop();
                }
                if(rand < 6){
                    stack2.pop();
                }
                if(rand > 7){
                    stack3.pop();
                }
            }catch (EmptyStackException exception){
                break;
            }
            }
        }

        if(stack1.empty() || ((stackSize - stack1.size()) >= 2)){
            stack1.push(ThreadLocalRandom.current().nextInt(1, 9 + 1));
        }
        if(stack2.empty() || ((stackSize - stack2.size()) >= 2)){
            stack2.push(ThreadLocalRandom.current().nextInt(1, 9 + 1));
        }
        if(stack3.empty() || ((stackSize - stack3.size()) >= 2)){
            stack3.push(ThreadLocalRandom.current().nextInt(1, 9 + 1));
        }

        if(stack1.size() > 8){
            stack1.pop();
        }
        if(stack2.size() > 8){
            stack2.pop();
        }
        if(stack3.size() > 8){
            stack3.pop();
        }

        if(stack1.equals(globalStack)){
            stack1.push(ThreadLocalRandom.current().nextInt(1, 9 + 1));
        }
        if(stack2.equals(globalStack)){
            stack2.push(ThreadLocalRandom.current().nextInt(1, 9 + 1));
        }
        if(stack3.equals(globalStack)){
            stack3.push(ThreadLocalRandom.current().nextInt(1, 9 + 1));
        }


        stacksList.add(stack1);
        stacksList.add(stack2);
        stacksList.add(stack3);

        return stacksList;

    }

    /**
     * This method generates 3 random Queues similar to the
     * correct Queue. These Queues are checked to ensure they
     * are not the same as the correct Queue and not empty.
     *
     * @return Returns the 3 incorrect Queues for the Harder Prediction.
     */
    public ArrayList<Queue<Integer>> getOtherQueues() {
        ArrayList<Queue<Integer>> queuesList = new ArrayList<>();
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        Queue<Integer> queue3 = new LinkedList<>();

        String delimiter = " ";
        ArrayList<String> tokenizedOperations = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(operationsString, delimiter);
        while (st.hasMoreElements()) {
            tokenizedOperations.add(st.nextToken());
        }

        for (String operation : tokenizedOperations) {
            if (operation.contains("add")) {
                int rand = ThreadLocalRandom.current().nextInt(1, 10 + 1);
                if (rand > 3) {
                    queue1.add(Integer.parseInt(operation.replaceAll("[\\D]", "")));
                }
                if (rand < 6) {
                    queue2.add(Integer.parseInt(operation.replaceAll("[\\D]", "")));
                }
                if (rand > 6) {
                    queue3.add(Integer.parseInt(operation.replaceAll("[\\D]", "")));
                }
                if (ThreadLocalRandom.current().nextInt(1, 10 + 1) > 7) {
                    queue1.add(ThreadLocalRandom.current().nextInt(1, 9 + 1));
                    queue2.add(ThreadLocalRandom.current().nextInt(1, 9 + 1));
                    queue3.add(ThreadLocalRandom.current().nextInt(1, 9 + 1));
                }
            } else if (operation.contains("poll")) {
                try {
                    int rand = ThreadLocalRandom.current().nextInt(1, 10 + 1);
                    if (rand < 4) {
                        queue1.poll();
                    }
                    if (rand < 6) {
                        queue2.poll();
                    }
                    if (rand > 7) {
                        queue3.poll();
                    }
                } catch (NullPointerException exception) {
                    break;
                }
            }
        }


        if (queue1.isEmpty() || ((queueSize - queue1.size()) >= 2)) {
            queue1.add(ThreadLocalRandom.current().nextInt(1, 9 + 1));
        }
        if (queue2.isEmpty() || ((queueSize - queue2.size()) >= 2)) {
            queue2.add(ThreadLocalRandom.current().nextInt(1, 9 + 1));
        }
        if (queue3.isEmpty() || ((queueSize - queue3.size()) >= 2)) {
            queue3.add(ThreadLocalRandom.current().nextInt(1, 9 + 1));
        }

        if (queue1.size() > 8) {
            queue1.poll();
        }
        if (queue2.size() > 8) {
            queue2.poll();
        }
        if (queue3.size() > 8) {
            queue3.poll();
        }

        if(queue1.equals(globalQueue)){
            queue1.add(ThreadLocalRandom.current().nextInt(1, 9 + 1));
        }
        if(queue2.equals(globalQueue)){
            queue2.add(ThreadLocalRandom.current().nextInt(1, 9 + 1));
        }
        if(queue3.equals(globalQueue)){
            queue3.add(ThreadLocalRandom.current().nextInt(1, 9 + 1));
        }


        queuesList.add(queue1);
        queuesList.add(queue2);
        queuesList.add(queue3);

        return queuesList;
    }

}
