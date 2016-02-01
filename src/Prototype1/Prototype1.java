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

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (!stop) {
            System.out.println("Would you like to experiment with Stacks or Queues?");
            System.out.println("Please select a number: 1 for Stack or 2 for Queue or 0 to Exit.");
            int x = Integer.parseInt(s.nextLine());
            if (x == 1) {
                runStack();
                stop = false;
            } else if (x == 2) {
                runQueue();
                stop = false;
            } else if (x == 0) {
                stop = true;
            } else {
                System.out.println("Sorry the number was not in range, please try again.");
            }
        }

        System.out.println("Thanks for learning about Stacks and Queues!");
        System.exit(1);

    }

    static public void runStack() {
        System.out.println("This initial Stack will use the default Java Stack implementation");
        Stack<Integer> stack = new Stack<>();
        while (!stop) {
            try {
                Thread.sleep(250);                 //So things get printed correctly
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.err.println("Please select the number of the operation you would like to perform (or 0 to exit):");
            System.err.println("1. Push (Adds an object to the top of the Stack)");
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
                printStack(stack);
            } else if (x == 2) {
                if (stack.empty()) {
                    System.out.println("Sorry, the Stack is empty and therefore cannot be popped");
                } else {
                    System.out.println("Popping " + stack.pop() + " from the Stack");
                    printStack(stack);
                }
            } else if (x == 3) {
                if (stack.empty()) {
                    System.out.println("Sorry, the Stack is empty and therefore cannot be peeked");
                } else {
                    System.out.println("Peek: " + stack.peek());
                    printStack(stack);
                }
            } else if (x == 4) {
                if (stack.empty()) {
                    System.out.println("True - Stack is empty");
                } else {
                    System.out.println("False - Stack is not empty");
                }
                printStack(stack);
            } else if (x == 5) {
                if (stack.empty()) {
                    System.out.println("Sorry, the Stack is empty and therefore cannot be searched");
                } else {
                    System.out.println("Please enter what you would like to search for");
                    x = Integer.parseInt(s.nextLine());
                    System.out.println("Searching Stack for " + x);
                    if (stack.search(x) < 0) {
                        System.out.println("-1 was returned and therefore " + x + " was not found in the Stack");
                    } else {
                        System.out.println(stack.search(x) + " was returned, therefore " + x + " is in the Stack and " + stack.search(x) + " spaces from the top of the Stack");
                    }
                    printStack(stack);
                }
            } else if (x == 0) {
                stop = true;
            } else {
                System.out.println("Sorry the number was not in range, please try again.");
            }
        }
    }

    static public void printStack(Stack<Integer> s) {
        Stack<Integer> stack = new Stack<>();
        stack.addAll(s);
        System.out.println("\tTop");
        for(int i = stack.size(); i > 0; i--){
            System.out.println("\t " + stack.pop());
        }
        System.out.println("\tBottom");
    }


    static public void runQueue() {
        System.out.println("This initial Queue will use the LinkedList implementation");
        Queue<Integer> queue = new LinkedList<>();
        while (!stop) {
            try {
                Thread.sleep(250);                 //So things get printed correctly
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.err.println("Please select the number of the operation you would like to perform (or 0 to exit):");
            System.err.println("1. Add (Adds an object into the Queue)");
            System.err.println("2. Poll (Returns and removes the element at the head of the Queue, returns Null if Queue is empty)");
            System.err.println("3. Peek (Returns the element at the head of the Queue without removing it, returns Null if Queue is empty)");
            System.err.println("4. Offer (Inserts an object into the Queue, only if there is space)");
            System.err.println("5. Remove (Returns and removes the element at the head of the Queue)");
            System.err.println("6. Element (Returns the element at the head of the Queue without removing it)");
            Scanner s = new Scanner(System.in);
            int x = Integer.parseInt(s.nextLine());
            if (x == 1) {
                System.out.println("Please enter the number you would like to added to the Queue");
                x = Integer.parseInt(s.nextLine());
                System.out.println("Adding " + x + " to the Queue");
                queue.add(x);
                if (queue.size() > 1) {
                    System.out.println("Queue: Head-> " + queue + " <-Tail");
                } else {
                    System.out.println("Queue: " + queue);
                }
            } else if (x == 2) {
                if(queue.peek()==null){
                    System.out.println("Sorry, the Queue is empty and therefore cannot be polled");
                }else{
                    System.out.println("Poll: " + queue.poll() + " from the Queue");
                    if (queue.size() > 1) {
                        System.out.println("Queue: Head-> " + queue + " <-Tail");
                    } else {
                        System.out.println("Queue: " + queue);
                    }
                }
            } else if (x == 3) {
                if(queue.peek()==null){
                    System.out.println("Sorry, the Queue is empty and therefore cannot be peeked");
                }else{
                    System.out.println("Peek: " + queue.peek());
                    if (queue.size() > 1) {
                        System.out.println("Queue: Head-> " + queue + " <-Tail");
                    } else {
                        System.out.println("Queue: " + queue);
                    }
                }
            } else if (x == 4) {
                System.out.println("Please enter the number you would like to offer to the Queue");
                x = Integer.parseInt(s.nextLine());
                System.out.println("Offer " + x + " to the Queue");
                queue.offer(x);
                if (queue.size() > 1) {
                    System.out.println("Queue: Head-> " + queue + " <-Tail");
                } else {
                    System.out.println("Queue: " + queue);
                }
            } else if (x == 5) {
                if(queue.peek()==null){
                    System.out.println("Sorry, the Queue is empty and therefore an element cannot be removed");
                }else{
                    System.out.println("Remove: " + queue.remove() + " from the Queue");
                    if (queue.size() > 1) {
                        System.out.println("Queue: Head-> " + queue + " <-Tail");
                    } else {
                        System.out.println("Queue: " + queue);
                    }
                }
            } else if (x == 6) {
                if(queue.peek()==null){
                    System.out.println("Sorry, the Queue is empty and therefore Element cannot be used");
                }else{
                    System.out.println("Element: " + queue.peek());
                    if (queue.size() > 1) {
                        System.out.println("Queue: Head-> " + queue + " <-Tail");
                    } else {
                        System.out.println("Queue: " + queue);
                    }
                }
            } else if (x == 0) {
                stop = true;
            } else {
                System.out.println("Sorry the number was not in range, please try again.");
            }
        }
    }
}