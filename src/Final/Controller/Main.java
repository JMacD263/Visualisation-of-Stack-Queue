package src.Final.Controller;

import src.Final.Model.Model;
import src.Final.View.View;

/**
 * This class runs the program.
 *
 */
public class Main {

    /**
     * This method creates instances of the Model, View and Controller classes.
     * The View is also set as visible.
     *
     */
    public static void main(String[] args) {

        Model theModel = new Model();
        View theView = new View();

        Controller theController = new Controller(theView, theModel);

        theView.setVisible(true);


    }

}
