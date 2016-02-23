package src.Prototype3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by xnb12162 on 23/02/16.
 *
 */

public class DrawStackRepresentation extends JComponent {
    String toBeHighlighted = "";
    Stack<Integer> stack = new Stack<>();

    public void paint(Graphics g){

        Graphics2D graph2 = (Graphics2D)g;
        graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Stack<Integer> ss = new Stack<>();
        ss.addAll(stack);

        ArrayList<String> stackRepresentation = new ArrayList<>();

        for(int i = ss.size(); i > 0; i--){
            stackRepresentation.add(Integer.toString(ss.pop()));
        }



        //Draws the boxes with their numbers within them.
        int y = 30;
        int x = ((this.getWidth() / 2) - 20);
        for(String s: stackRepresentation){
            //get correct spacing for boxes
            int textx;
            if(s.length() == 1){
                textx = (x + 12);
            }else if(s.length() == 2){
                textx = (x + 8);
            } else {
                textx = (x + 5);
            }

            //highlight peeked
            if(s.equals(toBeHighlighted)){
                graph2.setColor(new Color(204, 255, 0));
                graph2.fillRect(x, y, 30, 30);
                graph2.setColor(Color.black);
                toBeHighlighted = "";
            }

            graph2.draw(new Rectangle(x, y, 30, 30));
            graph2.drawString(s, textx, (y + 20));
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

    public void setStack(Stack<Integer> stack){
        this.stack = stack;
    }

    public void highlight(int x){
        toBeHighlighted = Integer.toString(x);
    }

}

