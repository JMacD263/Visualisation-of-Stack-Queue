package src.Prototype3.Controller;

import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import src.Prototype3.Model.Model;
import src.Prototype3.View.View;
import src.Prototype3.Controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;

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
//        Prototype3 prototype3 = new Prototype3();
//        Prototype3.main(new String[0]);
        theModel = new Model();
        theView = new View();
        theController = new Controller(theView, theModel);

    }

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
    public void testAddStackOperation() throws Exception {
        theController.addStackOperation("test");

        Field privateField = Controller.class.
                getDeclaredField("operationsListStack");

        privateField.setAccessible(true);

        LinkedList<String> field = (LinkedList<String>) privateField.get(theController);

        assertTrue(field.get(0).equals("test"));

        for(int i = 0; i < 45; i++){ //45 is max list size
            theController.addStackOperation("No. " + i);
        }

        assertFalse(field.contains("test"));
    }

    @Test
    public void testAddStackJavaOperations() throws Exception {
        theController.addStackJavaOperations("test");

        Field privateField = Controller.class.
                getDeclaredField("operationsListStackJava");

        privateField.setAccessible(true);

        LinkedList<String> field = (LinkedList<String>) privateField.get(theController);

        assertTrue(field.get(0).equals("test"));

        for(int i = 0; i < 45; i++){ //45 is max list size
            theController.addStackJavaOperations("No. " + i);
        }

        assertFalse(field.contains("test"));
    }

    @Test
    public void testAddArrayStackOperation() throws Exception {

    }

    @Test
    public void testAddArrayStackJavaOperation() throws Exception {

    }

    @Test
    public void testAddQueueOperation() throws Exception {

    }

    @Test
    public void testAddQueueJavaOperation() throws Exception {

    }

    @Test
    public void testAddCircularQueueOperation() throws Exception {

    }

    @Test
    public void testAddCircularQueueJavaOperation() throws Exception {

    }

    @Test
    public void testSwitchQueueOperationsList() throws Exception {

    }

    @Test
    public void testSwitchStackOperationsList() throws Exception {

    }

    @Test
    public void testRunPrediction() throws Exception {

    }

    @Test
    public void testRunHarderPredictions() throws Exception {

    }
}