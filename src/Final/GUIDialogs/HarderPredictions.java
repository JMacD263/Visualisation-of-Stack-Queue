package src.Final.GUIDialogs;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class extends JDialog and forms the Harder Prediction Questions.
 * Mouse listeners are used to confirm which Stack or Queue was selected.
 * The Stacks and Queues are added to the four panels in the class.
 * The Question and Operation List labels are also set here.
 *
 */
public class HarderPredictions extends JDialog{
    private JLabel questionLabel;
    private JPanel aPanel;
    private JPanel bPanel;
    private JPanel cPanel;
    private JPanel dPanel;
    private JPanel rootPanel;
    private JLabel operationsLabel;
    private String answer;

    /**
     * Initialises the form, sets borders, the size and the name of the JDialog.
     * Listeners are also added in this constructor.
     */
    public HarderPredictions(){
        this.setTitle("Harder Prediction");
        this.setSize(920,900);
        this.add(rootPanel);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        aPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        cPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        dPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        aPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                answer = "a";
                setVisible(false);
            }
        });

        bPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                answer = "b";
                setVisible(false);
            }
        });

        cPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                answer = "c";
                setVisible(false);
            }
        });


        dPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                answer = "d";
                setVisible(false);
            }
        });
    }

    /**
     * The question label is set from the string passed in.
     *
     * @param label The Question to be asked.
     */
    public void setQuestionLabel(String label){
        questionLabel.setText(label);
    }

    /**
     * The operations list label is set from the string passed in.
     *
     * @param label The operations list for the Stack or Queue.
     */
    public void setOperationsLabel(String label){
        operationsLabel.setText(label);
    }

    /**
     * Sets a stack or queue to the "A" panel.
     *
     * @param panelA the Stack or Queue to be added to the panel.
     */
    public void setPanelA(JComponent panelA){
        aPanel.add(panelA);
    }

    /**
     * Sets a stack or queue to the "B" panel.
     *
     * @param panelB the Stack or Queue to be added to the panel.
     */
    public void setPanelB(JComponent panelB){
        bPanel.add(panelB);
    }

    /**
     * Sets a stack or queue to the "C" panel.
     *
     * @param panelC the Stack or Queue to be added to the panel.
     */
    public void setPanelC(JComponent panelC){
        cPanel.add(panelC);
    }

    /**
     * Sets a stack or queue to the "D" panel.
     *
     * @param panelD the Stack or Queue to be added to the panel.
     */
    public void setPanelD(JComponent panelD){
        dPanel.add(panelD);
    }

    /**
     * This returns which panel the user selected so that
     * it can be checked if they answered correctly.
     *
     * @return returns the panel clicked on by the user.
     */
    public String getAnswer(){
        return answer;
    }

}

