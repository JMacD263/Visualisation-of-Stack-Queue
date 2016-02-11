package src.Prototype2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.*;

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
    private LinkedList<String> operationsListQueue = new LinkedList<>();


    View(Model model) {
        super("Stack and Queue Visualisation");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.add(tabbedPane1);

        DrawStuff drawStuff = new DrawStuff(model);
        stackDisplay.add(drawStuff);

        DrawQueueStuff drawQueueStuff = new DrawQueueStuff(model);
        queueDisplay.add(drawQueueStuff);


        //stackDisplay.add(new DrawStuff(model));

        //queueDisplay.add(new DrawQueueStuff(model));

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
                if(model.getStack().size() == 10){
                    JOptionPane.showMessageDialog(null, "Sorry the Stack is full and nothing can be pushed", "Stack Full", JOptionPane.ERROR_MESSAGE);
                } else{
                    while(true){
                        try{
                            int toBePushed = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to push on to the Stack", "Push", JOptionPane.DEFAULT_OPTION));
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
                    drawStuff.highlight(peeked); //highlight thing
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
                            drawStuff.highlight(toBeSearched);
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



         /*
        Start of the Queue button action listeners
         */

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getQueue().size() == 15){
                    JOptionPane.showMessageDialog(null, "Sorry the Queue is full and nothing can be added", "Queue Full", JOptionPane.ERROR_MESSAGE);
                } else{
                    while(true){
                        try{
                            int toBeQueued = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to add to the Queue", "Add", JOptionPane.DEFAULT_OPTION));
                            addQueueOperation("Adding: " + toBeQueued);
                            model.add(toBeQueued);
                            queueDisplay.updateUI();
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
            }
        });


        pollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int polled = model.poll();
                    addQueueOperation("Polled: " + polled);
                    queueDisplay.updateUI();
                } catch(NullPointerException exception){
                    addQueueOperation("Cannot Poll - Queue Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Queue is empty and therefore cannot be polled", "Queue Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        peekButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int peeked = model.peekQueue();
                    addQueueOperation("Peeked: " + peeked);
                    drawQueueStuff.highlight(peeked);
                    queueDisplay.updateUI();
                } catch(NullPointerException exception){
                    addQueueOperation("Cannot Peek - Queue Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Queue is empty and therefore cannot be peeked", "Queue Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        offerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getQueue().size() == 15){
                    JOptionPane.showMessageDialog(null, "Sorry the Queue is full and nothing can be offered", "Queue Full", JOptionPane.ERROR_MESSAGE);
                } else{
                    while(true){
                        try{
                            int toBeOffered = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to add to the Queue", "Offer", JOptionPane.DEFAULT_OPTION));
                            addQueueOperation("Offering: " + toBeOffered);
                            model.add(toBeOffered);
                            queueDisplay.updateUI();
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
            }
        });


        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int removed = model.remove();
                    addQueueOperation("Removed: " + removed);
                    queueDisplay.updateUI();
                } catch(NoSuchElementException exception){
                    addQueueOperation("Cannot Remove - Queue Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Queue is empty and therefore nothing can be removed", "Queue Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        elementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int element = model.element();
                    addQueueOperation("Element: " + element);
                    drawQueueStuff.highlight(element);
                    queueDisplay.updateUI();
                } catch(NoSuchElementException exception){
                    addQueueOperation("Cannot Element - Queue Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Queue is empty and therefore 'element' cannot be performed", "Queue Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Add operations to Stack list.
    public void addOperation(String op){
        if(operationsList.size() < 25){ //Max size of stack operations list
            operationsList.push(op);
        }else{
            operationsList.removeLast();
            operationsList.push(op);
        }
        String listData[] = operationsList.toArray(new String[operationsList.size()]);
        previousOperations.setListData(listData);
    }

    // Add operations to the queue list
    public void addQueueOperation(String op){
        if(operationsListQueue.size() < 25){ //Max size of queue operations list
            operationsListQueue.push(op);
        }else{
            operationsListQueue.removeLast();
            operationsListQueue.push(op);
        }
        String listData[] = operationsListQueue.toArray(new String[operationsListQueue.size()]);
        previousOperationsQueue.setListData(listData);
    }

    private class DrawStuff extends JComponent {
        Model model;
        int toBeHighlighted = -1;

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
            FontMetrics fm = graph2.getFontMetrics();
            int textx;

            for(int i = stack.size(); i > 0; i--){
                stackRepresentation.add(new boxElement(stack.pop()));
            }

            //Draws the boxes with their numbers within them.
            int y = 30;
            int x = ((this.getWidth() / 2) - 20);
            for(boxElement b: stackRepresentation){
                //get correct spacing for boxes
                if(b.getText().length() == 1){
                     textx = (x + 12);
                }else if(b.getText().length() == 2){
                    textx = (x + 8);
                } else {
                     textx = (x + 5);
                }

                //highlight searched thing
                if(b.elementNo == toBeHighlighted){
                    graph2.setColor(new Color(204, 255, 0));
                    graph2.fillRect(x, y, 30, 30);
                    graph2.setColor(Color.black);
                    toBeHighlighted = -1;
                }

                graph2.draw(new Rectangle(x, y, 30, 30));
                graph2.drawString(b.getText(), textx, (y + 20));
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

        public void highlight(int x){
            toBeHighlighted = x;
        }



    }

    private class DrawQueueStuff extends Component {
        Model model;
        int toBeHighlighted = -1;

        public DrawQueueStuff(Model model){
            this.model = model;
        }

        public void paint(Graphics g) {

            Graphics2D graph2 = (Graphics2D) g;
            graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Queue<Integer> queue = model.getQueue();
            ArrayList<boxElement> queueRepresentation = new ArrayList<>();
            FontMetrics fm = graph2.getFontMetrics();
            int textx;

            for(Integer x: queue){
                queueRepresentation.add(new boxElement(x));
            }

            //Draws the boxes with their numbers within them.
            int y = ((this.getHeight() / 2) - 20);
            int x = 30;
            for(boxElement b: queueRepresentation){
                //get correct spacing for boxes
                if(b.getText().length() == 1){
                    textx = (x + 12);
                }else if(b.getText().length() == 2){
                    textx = (x + 8);
                } else {
                    textx = (x + 5);
                }

                //highlight searched thing
                if(b.elementNo == toBeHighlighted){
                    graph2.setColor(new Color(204, 255, 0));
                    graph2.fillRect(x, y, 30, 30);
                    graph2.setColor(Color.black);
                    toBeHighlighted = -1;
                }

                graph2.draw(new Rectangle(x, y, 30, 30));
                graph2.drawString(b.getText(), textx, (y + 20));
                x += 45;
            }

            /*
            This code draws the lines in between the boxes
             */
            x = 75;
            for(int i = 1; i < queueRepresentation.size(); i++){
                graph2.drawLine((x), (y + 15), (x - 15), (y + 15));
                x+=45;
            }

            //This add head and tail to the graphics, showing the queue more clearly.
            if(queueRepresentation.size() > 0){
                graph2.drawString("Head", 30, (y - 25));
            }
            if(queueRepresentation.size() > 1){
                graph2.drawString("Tail", ((45 * queueRepresentation.size()) - 10), (y - 25));
            }

        }

        public void highlight(int x) {
            toBeHighlighted = x;
        }
    }
}
