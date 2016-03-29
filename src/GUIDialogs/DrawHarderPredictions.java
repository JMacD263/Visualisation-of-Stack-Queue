package src.GUIDialogs;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * This class is used to draw the harder prediction Stacks or Queues on the JDialog.
 *
 */
public class DrawHarderPredictions extends JComponent {
    private Stack<Integer> stack = new Stack<>();
    private Queue<Integer> queue = new LinkedList<>();
    private String visualisationType;

    /**
     * This method paints the Stack or Queue that has been set.
     */
    public void paint(Graphics g) {

        Graphics2D graph2 = (Graphics2D) g;
        graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(visualisationType.equals("Stack")){
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
                graph2.draw(new Rectangle(x, y, 30, 30));
                graph2.drawString(s, (x + 12), (y + 20));
                y = y + 45;

            }

            //This code draws the lines in between the boxes
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

        }else if(visualisationType.equals("Queue")){
            ArrayList<String> queueRepresentation = new ArrayList<>();

            for(int x: queue){
                queueRepresentation.add(Integer.toString(x));
            }

            //Draws the boxes with their numbers within them.
            int y = ((this.getHeight() / 2) - 20);
            int x = 40;
            for(String q: queueRepresentation){
                graph2.draw(new Rectangle(x, y, 30, 30));
                graph2.drawString(q, (x + 12), (y + 20));
                x += 45;
            }

            //This code draws the lines in between the boxes
            x = 85;
            for(int i = 1; i < queueRepresentation.size(); i++){
                graph2.drawLine((x), (y + 15), (x - 15), (y + 15));
                x+=45;
            }

            //This adds head and tail to the graphics, showing the queue more clearly.
            if(queueRepresentation.size() > 0){
                graph2.drawString("Head", 40, (y - 25));
            }
            if(queueRepresentation.size() > 1){
                graph2.drawString("Tail", (45 * queueRepresentation.size()), (y - 25));
            }
        }
    }

    /**
     * Sets the stack that is to be drawn.
     *
     * @param stack The Stack to be painted.
     */
    public void setStack(Stack<Integer> stack){
        this.stack = stack;
    }

    /**
     * Sets the queue that is to be drawn.
     *
     * @param queue The Queue to be painted.
     */
    public void setQueue(Queue<Integer> queue){
        this.queue = queue;
    }

    /**
     * Sets if Stack or Queue is to be drawn.
     *
     * @param type String for setting visualisation type.
     */
    public void setVisualisationType(String type){
        visualisationType = type;
    }
}
