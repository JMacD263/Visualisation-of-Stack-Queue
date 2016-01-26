package src.Prototype1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Jamie on 18/01/2016.
 * Text based basic Stack and Queue
 */
public class Prototype1 {
    static boolean stop = false;
    public static void main(String [] args) {
        Scanner s = new Scanner(System.in);
        while (!stop) {
            System.out.println("Would you like to experiment with Stacks or Queues?");
            System.out.println("Please select a number: 1 for Stack or 2 for Queue or 3 to Exit.");
            int x = Integer.parseInt(s.nextLine());
            if (x == 1) {
                runStack();
            } else if (x == 2) {
                //runQueue();
            } else if (x == 3) {
                stop = true;
            } else {
                System.out.println("Sorry the number was not in range, please try again.");
            }
        }

        System.out.println("Thanks for learning about Stacks and Queues!");
        System.exit(1);

    }

    static public void runStack(){
        System.out.println("This initial Stack will use the default Java Stack implementation");
        Stack<Integer> stack = new Stack<>();
        while(!stop){
            System.err.println("Please select the number of the operation you would like to perform (or 6 to exit):");
            System.err.println("1. Push (Adds an object to the head of the Stack)");
            System.err.println("2. Pop (Returns and removes the element on the top of the Stack)");
            System.err.println("3. Peek (Returns the element on the top of the Stack without removing it)");
            System.err.println("4. Empty (Simply returns true if Stack is empty and false if not)");
            System.err.println("5. Search (Returns the distance from the top of the Stack to the first occurrence or -1 if not found)");
            Scanner s = new Scanner(System.in);
            int x = Integer.parseInt(s.nextLine());
            if (x == 1) {
                System.out.println("Please enter the number you would like to pushed to the stack");
                x = Integer.parseInt(s.nextLine());
                System.out.println("Pushing " + x + " on to the Stack");
                stack.push(x);
                if(stack.size()>1){
                    System.out.println("Stack: Tail-> " + stack + " <-Head");
                } else {
                    System.out.println("Stack: " + stack);
                }
            } else if (x == 2) {
                if(stack.empty()){
                    System.out.println("Sorry, the Stack is empty and therefore cannot be popped");
                }else{
                    System.out.println("Popping " + stack.pop() + " from the Stack");
                    if(stack.size()>1){
                        System.out.println("Stack: Tail-> " + stack + " <-Head");
                    } else {
                        System.out.println("Stack: " + stack);
                    }
                }
            } else if (x == 3) {
                if(stack.empty()){
                    System.out.println("Sorry, the Stack is empty and therefore cannot be peeked");
                }else{
                    System.out.println("Peek: " + stack.peek());
                    if(stack.size()>1){
                        System.out.println("Stack: Tail-> " + stack + " <-Head");
                    } else {
                        System.out.println("Stack: " + stack);
                    }
                }
            } else if (x == 4) {
                if(stack.empty()){
                    System.out.println("True - Stack is empty");
                }else{
                    System.out.println("False - Stack is not empty");
                }
                if(stack.size()>1){
                    System.out.println("Stack: Tail-> " + stack + " <-Head");
                } else {
                    System.out.println("Stack: " + stack);
                }
            } else if (x == 5) {
                if(stack.empty()){
                    System.out.println("Sorry, the Stack is empty and therefore cannot be searched");
                }else{
                    System.out.println("Please enter what you would like to search for");
                    x = Integer.parseInt(s.nextLine());
                    System.out.println("Searching Stack for " + x);
                    if(stack.search(x) < 0){
                        System.out.println("-1 was returned and therefore " + x + " was not found in the Stack");
                    }else{
                        System.out.println(stack.search(x) + " was returned, therefore " + x + " is in the Stack and " + stack.search(x) + "spaces from the head of the Stack");
                    }
                    if(stack.size()>1){
                        System.out.println("Stack: Tail-> " + stack + " <-Head");
                    } else {
                        System.out.println("Stack: " + stack);
                    }
                }
            } else if (x == 6) {
                stop = true;
            } else {
                System.out.println("Sorry the number was not in range, please try again.");
            }
        }
    }

    public void runQueue(){
        System.out.println("This initial Queue will use the LinkedList implementation");
        Queue<Integer> queue = new LinkedList<>();

    }

      /*  while (stop == false) {
            System.out
                    .println("Enter 'Front', 'Enqueue', 'Dequeue', 'Size', 'isEmpty' or 'Stop' to quit the program");
            String y = s.nextLine();
            if (y.equals("stop") || y.equals("Stop")) {
                stop = true;
            }
            if (y.equals("Front") || y.equals("front")) {
                try {
                    System.out.println(qadt.front());
                } catch (EmptyQueueException e) {
                    System.err.println("Empty Queue: Cannot find an element");
                }
            }
            if (y.equals("Enqueue") || y.equals("enqueue")) {
                System.out
                        .println("Enter the string you want to enqueue in to the Queue");
                String e = s.nextLine();
                qadt.enqueue(e);
            }
            if (y.equals("Dequeue") || y.equals("dequeue")) {
                try {
                    System.out.println(qadt.dequeue());
                } catch (EmptyQueueException e) {
                    System.err.println("Empty Queue: Cannot Dequeue");
                }

            }
            if (y.equals("Size") || y.equals("size")) {
                System.out.println(qadt.size());
            }
            if (y.equals("isEmpty") || y.equals("isempty")) {
                System.out.println(qadt.isEmpty());
            }
        }*/




}
