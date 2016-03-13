package src.Prototype3.GUIDialogs;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Jamie on 13/03/2016.
 *
 */
public class CreateHarderPredictions {
    ArrayList<String> operationsList;
    String[] stackOperations = {"push", "pop", "peek"};
    String[] queueOperations = {"enqueue", "dequeue", "peek"};
    String operationsString = "";
    int stackSize;
    int queueSize;


    public CreateHarderPredictions(String type){
        operationsList =  new ArrayList<>();
        populateOperationsList(type);
    }

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
        stackSize = stack.size();
        return stack;
    }

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
        return queue;
    }

    public String getOperationsString(){
        return operationsString;
    }

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

        if(stack1.empty() || ((stackSize - stack1.size()) > 2)){
            stack1.push(ThreadLocalRandom.current().nextInt(1, 9 + 1));
        }
        if(stack2.empty() || ((stackSize - stack2.size()) > 2)){
            stack2.push(ThreadLocalRandom.current().nextInt(1, 9 + 1));
        }
        if(stack3.empty() || ((stackSize - stack3.size()) > 2)){
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


        stacksList.add(stack1);
        stacksList.add(stack2);
        stacksList.add(stack3);

        return stacksList;

    }


}
