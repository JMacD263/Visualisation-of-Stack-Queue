package src.Prototype2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    View() {
        super("Stack and Queue Visualisation");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.add(tabbedPane1);

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

            }
        });


        peekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        emptyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /*
        Start of the Queue button action listeners
         */
    }




}
