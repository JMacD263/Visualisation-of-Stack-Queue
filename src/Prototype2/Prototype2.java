package src.Prototype2;

/**
 * Created by Jamie on 18/01/2016.
 */
public class Prototype2 {

    public static void main(String[] args) {

        View theView = new View();
        Model theModel = new Model();

        Controller theController = new Controller(theView, theModel);

        theView.setVisible(true);

    }

}
