package src.Prototype3.GUIDialogs;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Jamie on 12/03/2016.
 */
public class HarderPredictions extends JDialog{
    private JLabel questionLabel;
    private JPanel aPanel;
    private JPanel bPanel;
    private JPanel cPanel;
    private JPanel dPanel;
    private JPanel rootPanel;
    private JLabel operationsLabel;
    private JLabel spaceLabel;
    private String answer;

    public HarderPredictions(){
        this.setTitle("Harder Prediction");
        this.setSize(670,650);
        this.add(rootPanel);

        aPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        cPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        dPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        aPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                answer = "a";
                System.out.println("Panel A");
                setVisible(false);
            }
        });

        bPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                answer = "b";
                System.out.println("Panel B");
                setVisible(false);
            }
        });

        cPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                answer = "c";
                System.out.println("Panel C");
                setVisible(false);
            }
        });


        dPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                answer = "d";
                System.out.println("Panel D");
                setVisible(false);
            }
        });
    }

    public void setQuestionLabel(String label){
        questionLabel.setText(label);
    }

    public void setOperationsLabel(String label){
        operationsLabel.setText(label);
    }

    public void setPanelA(JComponent panelA){
        aPanel.add(panelA);
    }

    public void setPanelB(JComponent panelB){
        bPanel.add(panelB);
    }

    public void setPanelC(JComponent panelC){
        cPanel.add(panelC);
    }

    public void setPanelD(JComponent panelD){
        dPanel.add(panelD);
    }

    public String getAnswer(){
        return answer;
    }

}
