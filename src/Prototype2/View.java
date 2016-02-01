package src.Prototype2;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by xnb12162 on 01/02/16.
 */
public class View extends JFrame {

    private JButton stackPop = new JButton("Pop");


    View(){
        JPanel stackPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);

        stackPanel.add(stackPop);

        this.add(stackPanel);
    }

    void addListener(ActionListener listenerForStackPop){

        stackPop.addActionListener(listenerForStackPop);

    }
}
