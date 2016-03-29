package src.GUIDialogs;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class extends JDialog and forms the Harder Prediction Questions.
 * Mouse listeners are used to confirm which Stack or Queue was selected.
 * The Stacks and Queues are added to the four panels in the class.
 * The Question and Operation List labels are also set here.
 */
public class HarderPredictions extends JDialog {
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
    public HarderPredictions() {
        this.setTitle("Harder Prediction");
        this.setSize(920, 900);
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
    public void setQuestionLabel(String label) {
        questionLabel.setText(label);
    }

    /**
     * The operations list label is set from the string passed in.
     *
     * @param label The operations list for the Stack or Queue.
     */
    public void setOperationsLabel(String label) {
        operationsLabel.setText(label);
    }

    /**
     * Sets a stack or queue to the "A" panel.
     *
     * @param panelA the Stack or Queue to be added to the panel.
     */
    public void setPanelA(JComponent panelA) {
        aPanel.add(panelA);
    }

    /**
     * Sets a stack or queue to the "B" panel.
     *
     * @param panelB the Stack or Queue to be added to the panel.
     */
    public void setPanelB(JComponent panelB) {
        bPanel.add(panelB);
    }

    /**
     * Sets a stack or queue to the "C" panel.
     *
     * @param panelC the Stack or Queue to be added to the panel.
     */
    public void setPanelC(JComponent panelC) {
        cPanel.add(panelC);
    }

    /**
     * Sets a stack or queue to the "D" panel.
     *
     * @param panelD the Stack or Queue to be added to the panel.
     */
    public void setPanelD(JComponent panelD) {
        dPanel.add(panelD);
    }

    /**
     * This returns which panel the user selected so that
     * it can be checked if they answered correctly.
     *
     * @return returns the panel clicked on by the user.
     */
    public String getAnswer() {
        return answer;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
        questionLabel = new JLabel();
        questionLabel.setText("");
        rootPanel.add(questionLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bPanel = new JPanel();
        bPanel.setLayout(new BorderLayout(0, 0));
        rootPanel.add(bPanel, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        aPanel = new JPanel();
        aPanel.setLayout(new BorderLayout(0, 0));
        rootPanel.add(aPanel, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cPanel = new JPanel();
        cPanel.setLayout(new BorderLayout(0, 0));
        rootPanel.add(cPanel, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        dPanel = new JPanel();
        dPanel.setLayout(new BorderLayout(0, 0));
        rootPanel.add(dPanel, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        operationsLabel = new JLabel();
        operationsLabel.setText("");
        rootPanel.add(operationsLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        rootPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 10), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        rootPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 5), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        rootPanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(7, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        rootPanel.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(7, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        rootPanel.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 7), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
