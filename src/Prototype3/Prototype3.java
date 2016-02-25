package src.Prototype3;

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
