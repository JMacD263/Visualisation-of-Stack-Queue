package src.Prototype2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by xnb12162 on 01/02/16.
 */
public class Controller {

    private View theView;
    private  Model theModel;

    public Controller(View view, Model model){
        theView = view;
        theModel = model;

        //theView.addListener(new PopListener());

    }

    class PopListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
