package src.Prototype2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by xnb12162 on 01/02/16.
 *
 *
 */
public class View extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JButton addButton;
    private JButton pollButton;
    private JButton peekButton1;
    private JButton offerButton;
    private JButton removeButton;
    private JButton elementButton;
    private JButton pushButton;
    private JButton popButton;
    private JButton peekButton;
    private JButton emptyButton;
    private JButton searchButton;
    private JPanel queueDisplay;
    private JPanel stackDisplay;
    private JLabel pushLabel;
    private JLabel popLabel;
    private JLabel peekLabel;
    private JLabel emptyLabel;
    private JLabel searchLabel;
    private JPanel queuePanel;
    private JLabel addLabel;
    private JLabel pollLabel;
    private JLabel peekQueueLabel;
    private JLabel offerLabel;
    private JLabel removeLabel;
    private JLabel elementLabel;
    private JPanel stackPanel;
    private JList previousOperations;
    private JList previousOperationsQueue;
    private LinkedList<String> operationsList = new LinkedList<>();


    View(Model model) {
        super("Stack and Queue Visualisation");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.add(tabbedPane1);

        stackDisplay.add(new DrawStuff(model));

        /*
        This is from the auto-constructor may be useful
        JFrame frame = new JFrame("View");
        frame.setContentPane(new View().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
         */

//        this.add(rootPanel);
//        this.add(addButton);
//        this.add(pollButton);
//        this.add(peekButton1);
//        this.add(offerButton);
//        this.add(removeButton);
//        this.add(elementButton);
//        this.add(pushButton);
//        this.add(popButton);
//        this.add(peekButton);
//        this.add(emptyButton);
//        this.add(searchButton);
//        this.add(queueDisplay);
//        this.add(stackDisplay);
//        this.add(pushLabel);
//        this.add(popLabel);
//        this.add(peekLabel);
//        this.add(emptyLabel);
//        this.add(searchLabel);
//        this.add(queuePanel);
//        this.add(addLabel);
//        this.add(pollLabel);
//        this.add(peekQueueLabel);
//        this.add(offerLabel);
//        this.add(removeLabel);
//        this.add(elementLabel);
//        this.add(stackPanel);

        /*
        Start of the Stack button action listeners
         */
        pushButton.addActionListener(new ActionListener() { //anonymous inner class
            @Override
            public void actionPerformed(ActionEvent e) {
                while(true){
                    try{
                        int toBePushed = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to push on to the Stack", "Push", JOptionPane.DEFAULT_OPTION));
                        System.out.println(toBePushed); //replace with real code to add to data bit
                        addOperation("Pushing: " + toBePushed);
                        model.push(toBePushed);
                        stackDisplay.updateUI();
                        break;
                    }
                    catch(java.lang.NumberFormatException exception){
                        if(exception.getMessage().equals("null")){
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Sorry that was not an Integer, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        popButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int popped = model.pop();
                    addOperation("Popped: " + popped);
                    stackDisplay.updateUI();
                } catch(EmptyStackException exception){
                    addOperation("Cannot Pop - Stack Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Stack is empty and therefore cannot be popped", "Stack Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        peekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int peeked = model.peek();
                    addOperation("Peeked: " + peeked);
                    stackDisplay.updateUI();
                } catch(EmptyStackException exception){
                    addOperation("Cannot Peek - Stack Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Stack is empty and therefore cannot be peeked", "Stack Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        emptyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperation("Stack Empty: " + model.empty());
            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while(true){
                    try{
                        int toBeSearched = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to Search for in the Stack", "Search", JOptionPane.DEFAULT_OPTION));
                        int searched = model.search(toBeSearched);
                        if(searched > -1){
                            addOperation("Searching for " + toBeSearched + " - " + searched + " from the top of the stack");
                        } else{
                            addOperation("Searching for " + toBeSearched + " : " + searched + " therefore not in the stack");
                        }
                        stackDisplay.updateUI();
                        break;
                    }
                    catch(java.lang.NumberFormatException exception){
                        if(exception.getMessage().equals("null")){
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Sorry that was not an Integer, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

    }

    public void addOperation(String op){
        if(operationsList.size() < 20){
            operationsList.push(op);
        }else{
            operationsList.removeLast();
            operationsList.push(op);
        }
        String listData[] = operationsList.toArray(new String[operationsList.size()]);
        previousOperations.setListData(listData);
    }

    private class DrawStuff extends JComponent {
        Model model;

        public DrawStuff(Model model){
            this.model = model;
        }

        public void paint(Graphics g){

            Graphics2D graph2 = (Graphics2D)g;
            graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Stack<Integer> s = model.getStack();
            Stack<Integer> stack = new Stack<>();
            stack.addAll(s);
            ArrayList<boxElement> stackRepresentation = new ArrayList<>();

            for(int i = stack.size(); i > 0; i--){
                stackRepresentation.add(new boxElement(stack.pop()));
            }

            int y = (30);
            int x = ((this.getWidth() / 2) - 20);
            for(boxElement b: stackRepresentation){
                FontMetrics fm = graph2.getFontMetrics();
                int textx;
                if(b.getText().length() < 2){
                     textx = (x + 10);
                } else {
                     textx = (x + 5);
                }
                int texty = (y + 20);
                graph2.draw(new Rectangle(x, y, 30, 30));
                graph2.drawString(b.getText(), textx, texty);
                y = y + 45;
            }


            y = 75;
            for(int i = 1; i < stackRepresentation.size(); i++){
                graph2.drawLine((x + 15), (y), (x + 15), (y - 15));
                y+=45;
            }

            /*Rectangle rect = new Rectangle(200, 200, 150, 150);

            graph2.draw(rect);

            String test = "This is a test";

            FontMetrics metrics = g.getFontMetrics();
            // Determine the X coordinate for the text
            int x = (int)(rect.getX() + (rect.width - metrics.stringWidth(test)) / 2);
            // Determine the Y coordinate for the text
            int y = (int)(rect.getY() + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent());
            // Draw the String
            g.drawString(test, x, y);*/

        }

    }

}
