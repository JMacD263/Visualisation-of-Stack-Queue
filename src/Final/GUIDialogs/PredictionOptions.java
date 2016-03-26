package src.Final.GUIDialogs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class extends JDialog and asks the user which prediction options they would like.
 * These options are then saved once the JDialog has been closed.
 *
 */
public class PredictionOptions extends JDialog{
    private JRadioButton preMadeDataRadioButton;
    private JRadioButton blankCanvasRadioButton;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JButton saveButton;
    private JPanel rootPanel;
    private int noPredictions;
    private boolean isBlank;

    /**
     * This method sets the size and name of the JDialog.
     * The radio buttons are also added as well as their listeners.
     */
    public PredictionOptions() {
        this.setTitle("Prediction Options");
        this.setSize(660, 250);
        this.setLocation(200, 200);
        this.add(rootPanel);

        ButtonGroup bgNoPredictions = new ButtonGroup();
        radioButton1.setActionCommand("1");
        radioButton2.setActionCommand("2");
        bgNoPredictions.add(radioButton1);
        bgNoPredictions.add(radioButton2);
        radioButton2.setSelected(true);

        ButtonGroup bgDisplayType = new ButtonGroup();
        preMadeDataRadioButton.setActionCommand("PreMade");
        blankCanvasRadioButton.setActionCommand("BlankCanvas");
        bgDisplayType.add(preMadeDataRadioButton);
        bgDisplayType.add(blankCanvasRadioButton);
        blankCanvasRadioButton.setSelected(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bgNoPredictions.getSelection().getActionCommand().equals("1")){ // set number of predictions
                    noPredictions = 1;
                }else{
                    noPredictions = 2;
                }

                isBlank = bgDisplayType.getSelection().getActionCommand().equals("BlankCanvas"); // true/false for isBlank

                setVisible(false);
            }
        });

    }

    /**
     * This gets the users selected number of predictions.
     *
     * @return the users selected number of predictions.
     */
    public int getNoPredictions(){
        return noPredictions;
    }

    /**
     * Gets the users selection for using current data or random data.
     *
     * @return Returns the users choice if they want to use current data or random data.
     */
    public boolean getIsBlank(){
        return isBlank;
    }
}
