package src.Prototype3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jamie on 07/03/2016.
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

    public PredictionOptions() {
        this.setTitle("Prediction Options");
        this.setSize(650, 250);
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

    public int getNoPredictions(){
        return noPredictions;
    }

    public boolean getIsBlank(){
        return isBlank;
    }
}
