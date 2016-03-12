package src.Prototype3.Controller;

import src.Prototype3.Controller.Controller;
import src.Prototype3.Model.Model;
import src.Prototype3.View.View;

/**
 * Created by Jamie on 17/02/2016.
 *
 */
public class Prototype3 {

    public static void main(String[] args) {

        Model theModel = new Model();
        View theView = new View();

        Controller theController = new Controller(theView, theModel);

        theView.setVisible(true);


    }

}
