package src.View;

import src.Model.ArrayStack;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * This class extends JComponent and is used to draw the Stacks.
 */

public class DrawStackRepresentation extends JComponent {
    String toBeHighlighted = "";
    Stack<Integer> stack = new Stack<>();
    ArrayStack<Integer> arrayStack = new ArrayStack<>();
    boolean isArray = false;
    boolean firstRun = true;

    /**
     * This method checks if the Stack is Array Stack or not and runs the corresponding draw method.
     */
    public void paint(Graphics g){

        Graphics2D graph2 = (Graphics2D)g;
        graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(isArray){
            arrayStackSettings(graph2);
        } else {
            normalStackSettings(graph2);
        }

    }

    /**
     * This method draws the boxes and elements of the Array Queue.
     * Highlighting is also done within this method.
     */
    private void arrayStackSettings(Graphics2D graph2) {
        int x;
        int y;

        ArrayList<String> stackRepresentation = new ArrayList<>();
        int[] stack = arrayStack.getStack();

        for(int i = arrayStack.getTop(); i > -1; i--){
            stackRepresentation.add(Integer.toString(stack[i]));
        }

        if(!firstRun){
            //Draws numbers within the boxes.
            y = 30 + ((9 * 45) -(arrayStack.getTop() * 45));
            x = ((this.getWidth() / 2) - 20);
            for(String string: stackRepresentation){
                //get correct spacing for boxes
                int textx;
                if(string.length() == 1){
                    textx = (x + 12);
                }else if(string.length() == 2){
                    textx = (x + 8);
                } else {
                    textx = (x + 5);
                }

                //highlight peeked
                if(string.equals(toBeHighlighted)){
                    graph2.setColor(new Color(204, 255, 0));
                    graph2.fillRect(x, y, 30, 30);
                    graph2.setColor(Color.black);
                    toBeHighlighted = "";
                }

                //graph2.draw(new Rectangle(x, y, 30, 30));
                graph2.drawString(string, textx, (y + 20));
                y = y + 45;

            }
        }


        //Draw the boxes and index numbers beneath them
        x = ((this.getWidth() / 2) - 20);
        y = 30;
        for(int i = 9; i > -1; i--){
            graph2.draw(new Rectangle(x, y, 30, 30));
            graph2.drawString(Integer.toString(i), (x - 32), (y + 20));
            y += 45;
        }

        /*
        This code draws the lines in between the boxes
        */
        y = 75;
        for(int i = 1; i < arrayStack.size(); i++){
            graph2.drawLine((x + 15), (y), (x + 15), (y - 15));
            y+=45;
        }

        //This add top and bottom to the graphics, showing the stack more clearly.


        if(arrayStack.getTop() == -1){
            graph2.drawString("Top", (x + 55), ((45 * stack.length) + 5) + 45);
        }else if(arrayStack.getTop() == 9){
            graph2.drawString("Top", (x + 55), (50));
        }else{
            graph2.drawString("Top", (x + 55), (((10 * 45)) - (arrayStack.getTop() * 45)) + 5);
        }

        //Allow the boxes to be filled
        firstRun = false;
    }

    /**
     * This method draws the boxes and elements of the Stack.
     * This is for the Java Standard Library Stack.
     * Highlighting is also done within this method.
     */
    private void normalStackSettings(Graphics2D graph2) {
        Stack<Integer> s = new Stack<>();
        s.addAll(stack);

        ArrayList<String> stackRepresentation = new ArrayList<>();

        for(int i = s.size(); i > 0; i--){
            stackRepresentation.add(Integer.toString(s.pop()));
        }

        //Draws the boxes with their numbers within them.
        int y = 30;
        int x = ((this.getWidth() / 2) - 20);
        for(String string: stackRepresentation){
            //get correct spacing for boxes
            int textx;
            if(string.length() == 1){
                textx = (x + 12);
            }else if(string.length() == 2){
                textx = (x + 8);
            } else {
                textx = (x + 5);
            }

            //highlight peeked
            if(string.equals(toBeHighlighted)){
                graph2.setColor(new Color(204, 255, 0));
                graph2.fillRect(x, y, 30, 30);
                graph2.setColor(Color.black);
                toBeHighlighted = "";
            }

            graph2.draw(new Rectangle(x, y, 30, 30));
            graph2.drawString(string, textx, (y + 20));
            y = y + 45;

        }

        /*
        This code draws the lines in between the boxes
        */
        y = 75;
        for(int i = 1; i < stackRepresentation.size(); i++){
            graph2.drawLine((x + 15), (y), (x + 15), (y - 15));
            y+=45;
        }

        //This add top and bottom to the graphics, showing the stack more clearly.
        if(stackRepresentation.size() > 0){
            graph2.drawString("Top", (x - 40), 50);
        }
        if(stackRepresentation.size() > 1){
            graph2.drawString("Bottom", (x - 55), ((45 * stackRepresentation.size()) + 5));
        }
    }

    /**
     * This method is run after the boxes have been drawn for Array Stack.
     */
    public void toggleFirstRun(){
        firstRun = false;
    }

    /**
     * This method sets the Stack which is to be drawn.
     * @param stack the Stack that is to be drawn.
     */
    public void setStack(Stack<Integer> stack){
        this.stack = stack;
    }

    /**
     * This method sets the Array Stack which is to be drawn.
     * @param arrayStack the Array Stack that is to be drawn.
     */
    public void setArrayStack(ArrayStack<Integer> arrayStack){
        this.arrayStack = arrayStack;
    }

    /**
     * This sets the element which will be highlighted after a peek is performed.
     * @param x This is the element which is to highlighted.
     */
    public void highlight(int x){
        toBeHighlighted = Integer.toString(x);
    }

    /**
     * Sets whether the visualisation is for Array Stack or Java Standard Library Stack.
     * @param isArray this is true if the visualisation is for Array Stack and false if not.
     */
    public void toggleArray(boolean isArray){
        this.isArray = isArray;
    }

}

