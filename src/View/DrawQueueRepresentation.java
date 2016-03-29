package src.View;

import src.Model.CircularQueue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class extends JComponent and is used to draw the Queues.
 */
public class DrawQueueRepresentation extends JComponent {
    private String toBeHighlighted = "";
    private Queue<Integer> queue = new LinkedList<>();
    private CircularQueue<Integer> circularQueue;
    private boolean circular = false;
    private boolean firstRun = true;

    /**
     * This method checks if the Queue is circular or not and runs the corresponding draw method.
     */
    public void paint(Graphics g){

        Graphics2D graph2 = (Graphics2D)g;
        graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(circular){
            circularQueueSettings(graph2);
        } else {
            normalQueueSettings(graph2);
        }

    }

    /**
     * This method draws the boxes and elements of the Queue.
     * This is for the Java Standard Library Queue.
     * Highlighting is also done within this method.
     */
    private void normalQueueSettings(Graphics2D graph2) {
        ArrayList<String> queueRepresentation = new ArrayList<>();

        for(int x: queue){
            queueRepresentation.add(Integer.toString(x));
        }

        //Draws the boxes with their numbers within them.
        int y = ((this.getHeight() / 2) - 20);
        int x = 30;
        for(String q: queueRepresentation){
            //get correct spacing for boxes
            int textx;
            if(q.length() == 1){
                textx = (x + 12);
            }else if(q.length() == 2){
                textx = (x + 8);
            } else {
                textx = (x + 5);
            }

            //highlight peeked
            if(q.equals(toBeHighlighted)){
                graph2.setColor(new Color(204, 255, 0));
                graph2.fillRect(x, y, 30, 30);
                graph2.setColor(Color.black);
                toBeHighlighted = "";
            }

            graph2.draw(new Rectangle(x, y, 30, 30));
            graph2.drawString(q, textx, (y + 20));
            x += 45;
        }

        //This code draws the lines in between the boxes
        x = 75;
        for(int i = 1; i < queueRepresentation.size(); i++){
            graph2.drawLine((x), (y + 15), (x - 15), (y + 15));
            x+=45;
        }

        //This adds head and tail to the graphics, showing the queue more clearly.
        if(queueRepresentation.size() > 0){
            graph2.drawString("Head", 30, (y - 25));
        }
        if(queueRepresentation.size() > 1){
            graph2.drawString("Tail", ((45 * queueRepresentation.size()) - 10), (y - 25));
        }
    }

    /**
     * This method draws the boxes and elements of the Circular Queue.
     * Highlighting is also done within this method.
     */
    private void circularQueueSettings(Graphics2D graph2) {
        int y = ((this.getHeight() / 2) - 20);
        int x;
        int size = 10;
        int head = 0;
        int tail = 0;

        if(!firstRun){
            head = circularQueue.getFront();
            tail = circularQueue.getRear();

            int list[] = circularQueue.getList();
            size = list.length;

            x = 30;
            for(int i = 0; i < list.length; i++){

                String s = Integer.toString(list[i]);

                int textx;
                if(s.length() == 1){
                    textx = (x + 12);
                }else if(s.length() == 2){
                    textx = (x + 8);
                } else {
                    textx = (x + 5);
                }

                //highlight peeked
                if(!toBeHighlighted.equals("") && list[i] == list[head]){
                    graph2.setColor(new Color(204, 255, 0));
                    graph2.fillRect(x, y, 30, 30);
                    graph2.setColor(Color.black);
                    toBeHighlighted = "";
                }

                if(!s.equals("0")){
                    graph2.drawString(s, textx, (y + 20));
                }
                x+=45;
            }
        }

        //Draw the boxes and index numbers beneath them
        x = 30;
        for(int i = 0; i < size; i++){
            graph2.draw(new Rectangle(x, y, 30, 30));
            graph2.drawString(Integer.toString(i), (x + 12), (y + 50));
            x += 45;
        }

        //This code draws the lines in between the boxes
        x = 75;
        for(int i = 1; i < size; i++){
            graph2.drawLine((x), (y + 15), (x - 15), (y + 15));
            x+=45;
        }

        //This adds head and tail to the graphics, showing the queue more clearly.
        graph2.drawString("Head", ((45 * (head + 1)) - 15), (y - 40));
        graph2.drawString("Tail", ((45 * (tail + 1)) - 10), (y - 15));

        //Allow the boxes to be filled
        firstRun = false;

    }

    /**
     * This method is run after the boxes have been drawn for Circular Queue.
     */
    public void toggleFirstRun(){
        firstRun = false;
    }

    /**
     * This method sets the Queue which is to be drawn.
     *
     * @param queue This is the Queue to be drawn.
     */
    public void setQueue(Queue<Integer> queue){
        this.queue = queue;
    }

    /**
     * This method sets the Circular Queue which is to be drawn.
     *
     * @param circularQueue This is the Circular Queue to be drawn.
     */
    public void setCircularQueue(CircularQueue<Integer> circularQueue){
        this.circularQueue = circularQueue;
    }

    /**
     * This sets the element which will be highlighted after a peek is performed.
     * @param x This is the element which is to highlighted.
     */
    public void highlight(int x){
        toBeHighlighted = Integer.toString(x);
    }

    /**
     * Sets whether the visualisation is for Circular Queue or Java Standard Library Queue.
     * @param isCircular this is true if the visualisation is for Circular Queue and false if not.
     */
    public void toggleCircular(boolean isCircular) {
        circular = isCircular;
    }


}