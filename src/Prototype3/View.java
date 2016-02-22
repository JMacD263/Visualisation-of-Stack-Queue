package src.Prototype3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jamie on 22/02/2016.
 */
public class View extends JFrame {

    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JPanel stackPanel;
    private JPanel queuePanel;
    private JButton pushButton;
    private JButton popButton;
    private JButton peekStackButton;
    private JList previousStackOperations;
    private JList list2;
    private JLabel pushLabel;
    private JLabel popLabel;
    private JLabel peekStackLabel;
    private JPanel stackDisplay;
    private JButton enqueueButton;
    private JButton dequeueButton;
    private JButton peekQueueButton;
    private JList previousQueueOperations;
    private JList list3;
    private JPanel queueDisplay;
    private JLabel dequeueLabel;
    private JLabel peekQueueLabel;
    private JLabel enqueueLabel;

    public View(){
        super("Stack and Queue Visualisation");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.add(tabbedPane1);

        // Create a menu bar for the JFrame
        JMenuBar menuBar = new JMenuBar();
        // Add to the frame
        setJMenuBar(menuBar);
        // Add drop down menu
        JMenu predictionMenu = new JMenu("Prediction Mode");
        menuBar.add(predictionMenu);
        // Add the radio buttons to turn on/off
        JRadioButtonMenuItem radioOn = new JRadioButtonMenuItem(
                "On");
        JRadioButtonMenuItem radioOff = new JRadioButtonMenuItem(
                "Off");
        // Group buttons together and set off as default
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioOn);
        bg.add(radioOff);
        radioOff.setSelected(true);
        // Add radio buttons to menu bar
        predictionMenu.add(radioOn);
        predictionMenu.add(radioOff);

        //anon inner class, change to MVC
        radioOn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("You have clicked on the new action");
            }
        });

    }

    public void setPreviousStackOperations(String listData[]){
        previousStackOperations.setListData(listData);
    }

    public void setPreviousQueueOperations(String listData[]){
        previousQueueOperations.setListData(listData);
    }

    public void updateStackUI(){
        stackDisplay.updateUI();
    }

    public void updateQueueUI(){
        queueDisplay.updateUI();
    }

    /*
    The following block of methods all add ActionListeners so that the controller knows when a button is pressed
     */

    // If the pushButton is clicked execute actionPerformed method in the Controller
    void addPushListener(ActionListener listenForPushButton){
        pushButton.addActionListener(listenForPushButton);
    }

    // If the popButton is clicked execute actionPerformed method in the Controller
    void addPopListener(ActionListener listenForPopButton){
        popButton.addActionListener(listenForPopButton);
    }

    // If the peekStackButton is clicked execute actionPerformed method in the Controller
    void addPeekStackListener(ActionListener listenForPeekStackButton){
        peekStackButton.addActionListener(listenForPeekStackButton);
    }

    // If the enqueueButton is clicked execute actionPerformed method in the Controller
    void addEnqueueListener(ActionListener listenForEnqueueButton){
        enqueueButton.addActionListener(listenForEnqueueButton);
    }

    // If the dequeueButton is clicked execute actionPerformed method in the Controller
    void addDequeueListener(ActionListener listenForDequeueButton){
        dequeueButton.addActionListener(listenForDequeueButton);
    }

    // If the peekQueueButton is clicked execute actionPerformed method in the Controller
    void addPeekQueueListener(ActionListener listenForPeekQueueButton){
        peekQueueButton.addActionListener(listenForPeekQueueButton);
    }

}
