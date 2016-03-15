package src.Prototype3.Controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import src.Prototype3.Model.Model;
import src.Prototype3.View.View;

import java.awt.event.ActionListener;

import static org.junit.Assert.*;

/**
 * Created by Jamie on 15/03/2016.
 *
 */
public class Prototype3Test {
    Controller theController;
    Model theModel;
    View theView;

    @Before
    public void setUp() throws Exception {
        Prototype3 prototype3 = new Prototype3();
        Prototype3.main(new String[0]);
        theModel = new Model();
        theView = new View();
        theController = new Controller(theView, theModel);

    }


//    @Test
//    public void testAddStackOperation() throws Exception {
//
//    }
//
//    @Test
//    public void testAddStackJavaOperations() throws Exception {
//
//    }
//
//    @Test
//    public void testAddQueueOperation() throws Exception {
//
//    }
//
//    @Test
//    public void testAddQueueJavaOperation() throws Exception {
//
//    }
//
//    @Test
//    public void testAddCircularQueueOperation() throws Exception {
//
//    }
//
//    @Test
//    public void testAddCircularQueueJavaOperation() throws Exception {
//
//    }
//
//    @Test
//    public void testSwitchQueueOperationsList() throws Exception {
//
//    }

    @Test
    public void testReset() throws Exception {
        theModel.push(1);
        theController.reset();
        assertTrue(theModel.getStack().isEmpty());
    }

    @Test
    public void testCreatePreMadeData() throws Exception {
        theController.createPreMadeData();
        assertFalse(theModel.getStack().isEmpty());
        assertFalse(theModel.getQueue().isEmpty());
        assertFalse(theModel.getCircularQueue().isEmpty());
    }

    @Test
    public void testRunPrediction() throws Exception {

    }

    @Test
    public void testRunHarderPredictions() throws Exception {
        theController.runHarderPredictions("Push");
    }
}