package src.Prototype1;

import java.util.Scanner;

/**
 * Created by Jamie on 18/01/2016.
 */
public class Prototype1 {

    public static void main(String [] args) {
        boolean stop = false;
        Scanner s = new Scanner(System.in);
        while (stop == false) {
            System.out.println("Would you like to experiment with Stacks or Queues?");
            System.out.println("Please select a number: 1 for Stack or 2 for Queue or 3 to Exit.");
            int x = Integer.parseInt(s.nextLine());
            if (x == 1) {
                //runStack();
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

    public void runStack(){

    }

    public void runQueue(){

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
