package src.Final.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * This is the View class which deals with the User Interface.
 * This class extends JFrame and all elements of the GUI are created here.
 */
public class View extends JFrame {

    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JPanel stackPanel;
    private JPanel queuePanel;
    private JButton pushButton;
    private JButton popButton;
    private JButton peekStackButton;
    private JList<String> previousStackOperations;
    private JList<String> stackJavaOperations;
    private JLabel pushLabel;
    private JLabel popLabel;
    private JLabel peekStackLabel;
    private JPanel stackDisplay;
    private JButton enqueueButton;
    private JButton dequeueButton;
    private JButton peekQueueButton;
    private JList<String> previousQueueOperations;
    private JList<String> queueJavaOperations;
    private JPanel queueDisplay;
    private JLabel dequeueLabel;
    private JLabel peekQueueLabel;
    private JLabel enqueueLabel;

    private JRadioButtonMenuItem radioOn;
    private JRadioButtonMenuItem radioOff;
    private JCheckBoxMenuItem harderPredictions;
    private JRadioButtonMenuItem circularQueue;
    private JRadioButtonMenuItem normalQueue;
    private JRadioButtonMenuItem arrayStack;
    private JRadioButtonMenuItem normalStack;
    private JMenuItem resetMenuItem;

    /**
     * This sets the size and state of the JFrame.
     * Icons are set within here.
     * All items in the Menu Bar are added in this method.
     * The JLists are set to centre the text also.
     */
    public View(){
        super("Stack and Queue Visualisation");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1200,800);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.add(tabbedPane1);

        // Add all icons to arraylist
        ArrayList<Image> icons = new ArrayList<>();
        icons.add(Toolkit.getDefaultToolkit().getImage("icons/16.png"));
        icons.add(Toolkit.getDefaultToolkit().getImage("icons/32.png"));
        icons.add(Toolkit.getDefaultToolkit().getImage("icons/64.png"));
        icons.add(Toolkit.getDefaultToolkit().getImage("icons/128.png"));
        // Set icons
        this.setIconImages(icons);

        // Create a menu bar for the JFrame
        JMenuBar menuBar = new JMenuBar();
        // Add to the frame
        setJMenuBar(menuBar);

        // Add drop down prediction menu
        JMenu predictionMenu = new JMenu("Prediction");
        menuBar.add(predictionMenu);
        // Add the radio buttons to turn prediction on/off
        radioOn = new JRadioButtonMenuItem(
                "On");
        radioOff = new JRadioButtonMenuItem(
                "Off");
        // Group buttons together and set off as default
        ButtonGroup bgPrediction = new ButtonGroup();
        bgPrediction.add(radioOn);
        bgPrediction.add(radioOff);
        radioOff.setSelected(true);
        // Add radio buttons to menu bar
        predictionMenu.add(radioOn);
        predictionMenu.add(radioOff);
        // Add separator
        predictionMenu.add(new JSeparator());
        // Add harder predictions checkbox and disable at start.
        harderPredictions = new JCheckBoxMenuItem("Harder Predictions");
        predictionMenu.add(harderPredictions);
        harderPredictions.setEnabled(false);

        // Add edit drop down menu
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        // Add the reset menu option
        resetMenuItem = new JMenuItem("Clear");
        editMenu.add(resetMenuItem);

        // Add drop down stack menu
        JMenu stackMenu = new JMenu("Stack");
        menuBar.add(stackMenu);
        // Add the radio buttons to change stack type
        arrayStack = new JRadioButtonMenuItem(
                "Array Stack");
        normalStack = new JRadioButtonMenuItem(
                "Stack<Integer>");
        // Group buttons together and set normal as default
        ButtonGroup bgStack = new ButtonGroup();
        bgStack.add(arrayStack);
        bgStack.add(normalStack);
        normalStack.setSelected(true);
        // Add radio buttons to menu bar
        stackMenu.add(arrayStack);
        stackMenu.add(normalStack);


        // Add drop down queue menu
        JMenu queueMenu = new JMenu("Queue");
        menuBar.add(queueMenu);
        // Add the radio buttons to change queue type
        circularQueue = new JRadioButtonMenuItem(
                "Circular Queue");
        normalQueue = new JRadioButtonMenuItem(
                "Queue<Integer>");
        // Group buttons together and set normal as default
        ButtonGroup bgQueue = new ButtonGroup();
        bgQueue.add(circularQueue);
        bgQueue.add(normalQueue);
        normalQueue.setSelected(true);
        // Add radio buttons to menu bar
        queueMenu.add(circularQueue);
        queueMenu.add(normalQueue);

        //Centers the text in the JLists
        DefaultListCellRenderer renderer = (DefaultListCellRenderer)previousStackOperations.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        renderer = (DefaultListCellRenderer)stackJavaOperations.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        renderer = (DefaultListCellRenderer)previousQueueOperations.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        renderer = (DefaultListCellRenderer)queueJavaOperations.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
    }

    /**
     * The List of Stack operations are set from the Controller and
     * added to the relevant JList in this method.
     *
     * @param listData This is the Data that will be set to the JList.
     */
    public void setPreviousStackOperations(String listData[]){
        previousStackOperations.setListData(listData);
    }

    /**
     * The List of Queue operations are set from the Controller and
     * added to the relevant JList in this method.
     *
     * @param listData This is the Data that will be set to the JList.
     */
    public void setPreviousQueueOperations(String listData[]){
        previousQueueOperations.setListData(listData);
    }

    /**
     * The List of Java Stack operations are set from the Controller and
     * added to the relevant JList in this method.
     *
     * @param listData This is the Data that will be set to the JList.
     */
    public void setPreviousStackJavaOperations(String listData[]){
        stackJavaOperations.setListData(listData);
    }

    /**
     * The List of Java Queue operations are set from the Controller and
     * added to the relevant JList in this method.
     *
     * @param listData This is the Data that will be set to the JList.
     */
    public void setPreviousQueueJavaOperations(String listData[]){
        queueJavaOperations.setListData(listData);
    }

    /**
     * This method clears the JLists of operations.
     */
    public void resetPreviousOperations(){
        previousStackOperations.setListData(new String[0]);
        previousQueueOperations.setListData(new String[0]);
        stackJavaOperations.setListData(new String[0]);
        queueJavaOperations.setListData(new String[0]);
    }

    /**
     * This updates the Stack panel after changes are made.
     */
    public void updateStackUI(){
        stackDisplay.updateUI();
    }

    /**
     * This updates the Queue panel after changes are made.
     */
    public void updateQueueUI(){
        queueDisplay.updateUI();
    }

    /**
     * This method is used when the predictions are turned on and the labels
     * have to be hidden. It is also used when predictions are turned off and
     * the labels have to be shown again.
     *
     * @param isVisible true if the labels are to be shown, otherwise false.
     */
    public void toggleLabels(boolean isVisible){
        pushLabel.setVisible(isVisible);
        popLabel.setVisible(isVisible);
        peekStackLabel.setVisible(isVisible);
        enqueueLabel.setVisible(isVisible);
        dequeueLabel.setVisible(isVisible);
        peekQueueLabel.setVisible(isVisible);
    }

    /**
     * This takes the drawn Stack visualisation and sets it to the relevant Panel.
     *
     * @param drawStackRepresentation this is the Stack representation to be added to the panel.
     */
    public void setStackPanel(DrawStackRepresentation drawStackRepresentation){
        stackDisplay.add(drawStackRepresentation);
    }

    /**
     * This takes the drawn Queue visualisation and sets it to the relevant Panel.
     * @param drawQueueRepresentation this is the Queue representation to be added to the panel.
     */
    public void setQueuePanel(DrawQueueRepresentation drawQueueRepresentation){
        queueDisplay.add(drawQueueRepresentation);
    }

    /**
     * Used to change the availability of the Harder Prediction checkbox in the prediction menu.
     *
     * @param isHarderPredictions true if harder predictions are enabled, otherwise false.
     * @param isChecked true if the harder predictions has been turned on, otherwise false.
     */
    public void toggleHarderPredictions(boolean isHarderPredictions, boolean isChecked){
        harderPredictions.setEnabled(isHarderPredictions);
        harderPredictions.setSelected(isChecked);
    }

    /**
     * This method is used when harder predictions are enabled to stop users clicking on
     * circular queue. It is also used when harder predictions are turned off to reverse this.
     *
     * @param circularEnabled true if circular queue is to be enabled, false otherwise.
     */
    public void toggleCircularQueue(boolean circularEnabled){
        if(circularEnabled){
            circularQueue.setEnabled(true);
        }else{
            if(circularQueue.isSelected()){
                normalQueue.doClick();
            }
            circularQueue.setEnabled(false);
        }
    }

    /**
     * This method is used when harder predictions are enabled to stop users clicking on
     * array stack. It is also used when harder predictions are turned off to reverse this.
     * @param arrayEnabled true if array stack is to be enabled, false otherwise.
     */
    public void toggleArrayStack(boolean arrayEnabled){
        if(arrayEnabled){
            arrayStack.setEnabled(true);
        }else{
            if(arrayStack.isSelected()){
                normalStack.doClick();
            }
            arrayStack.setEnabled(false);
        }
    }

    /**
     * Switches between the tabbed panes.
     * @param index 0 for Stack pane, 1 for Queue pane.
     */
    public void toggleTabbedPane(int index){
        tabbedPane1.setSelectedIndex(index);
    }

    /*
    The following block of methods all add ActionListeners so that the controller knows when a button is pressed
     */


    /**
     * If the pushButton is clicked execute actionPerformed method in the Controller
     * @param listenForPushButton Listener from the Controller for this button
     */
    public void addPushListener(ActionListener listenForPushButton){
        pushButton.addActionListener(listenForPushButton);
    }

    /**
     * If the popButton is clicked execute actionPerformed method in the Controller
     * @param listenForPopButton Listener from the Controller for this button
     */
    public void addPopListener(ActionListener listenForPopButton){
        popButton.addActionListener(listenForPopButton);
    }

    /**
     * If the peekStackButton is clicked execute actionPerformed method in the Controller
     * @param listenForPeekStackButton Listener from the Controller for this button
     */
    public void addPeekStackListener(ActionListener listenForPeekStackButton){
        peekStackButton.addActionListener(listenForPeekStackButton);
    }

    /**
     * If the enqueueButton is clicked execute actionPerformed method in the Controller
     * @param listenForEnqueueButton Listener from the Controller for this button
     */
    public void addEnqueueListener(ActionListener listenForEnqueueButton){
        enqueueButton.addActionListener(listenForEnqueueButton);
    }

    /**
     * If the dequeueButton is clicked execute actionPerformed method in the Controller
     * @param listenForDequeueButton Listener from the Controller for this button
     */
    public void addDequeueListener(ActionListener listenForDequeueButton){
        dequeueButton.addActionListener(listenForDequeueButton);
    }

    /**
     * If the peekQueueButton is clicked execute actionPerformed method in the Controller
     * @param listenForPeekQueueButton Listener from the Controller for this button
     */
    public void addPeekQueueListener(ActionListener listenForPeekQueueButton){
        peekQueueButton.addActionListener(listenForPeekQueueButton);
    }

    /**
     * If the on radio button is selected in the Prediction menu the actionPerformed method is run in the Controller
     * @param listenForOnRadio Listener from the Controller for this button
     */
    public void addOnRadioListener(ActionListener listenForOnRadio){
        radioOn.addActionListener(listenForOnRadio);
    }

    /**
     * If the off radio button is selected in the Prediction menu the actionPerformed method is run in the Controller
     * @param listenForOffRadio Listener from the Controller for this button
     */
    public void addOffRadioListener(ActionListener listenForOffRadio){
        radioOff.addActionListener(listenForOffRadio);
    }

    /**
     * If the circular queue is selected in the Prediction menu the actionPerformed method is run in the Controller
     * @param listenForCircularQueue Listener from the Controller for this button
     */
    public void addCircularQueueListener(ActionListener listenForCircularQueue) {
        circularQueue.addActionListener(listenForCircularQueue);
    }

    /**
     * If the normal queue is selected in the Prediction menu the actionPerformed method is run in the Controller
     * @param listenForNormalQueue Listener from the Controller for this button
     */
    public void addNormalQueueListener(ActionListener listenForNormalQueue) {
        normalQueue.addActionListener(listenForNormalQueue);
    }

    /**
     * If the array stack is selected in the Prediction menu the actionPerformed method is run in the Controller
     * @param listenForArrayStack Listener from the Controller for this button
     */
    public void addArrayStackListener(ActionListener listenForArrayStack) {
        arrayStack.addActionListener(listenForArrayStack);
    }

    /**
     * If the normal stack is selected in the Prediction menu the actionPerformed method is run in the Controller
     * @param listenForNormalStack Listener from the Controller for this button
     */
    public void addNormalStackListener(ActionListener listenForNormalStack) {
        normalStack.addActionListener(listenForNormalStack);
    }

    /**
     * If the reset menu option is selected the relevant actionPerformed method is run in the Controller
     * @param listenForReset Listener from the Controller for this button
     */
    public void addResetListener(ActionListener listenForReset){
        resetMenuItem.addActionListener(listenForReset);
    }

    /**
     * If the harder predictions checkbox is selected the relevant actionPerformed method is run in the Controller
     * @param listenForHarderPredictions Listener from the Controller for this checkbox
     */
    public void addHarderPredictionsListener(ItemListener listenForHarderPredictions){
        harderPredictions.addItemListener(listenForHarderPredictions);
    }
}