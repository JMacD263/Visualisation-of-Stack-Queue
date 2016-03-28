package src.Final.Controller;

import org.junit.Before;
import org.junit.Test;
import src.Final.Model.Model;
import src.Final.View.View;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by Jamie on 27/03/2016.
 */
public class ControllerTest {
    Controller theController;
    Model theModel;
    View theView;

    @Before
    public void setUp() throws Exception {
        Main main = new Main();
        Main.main(new String[0]);
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
        theController.addArrayStackOperation("test");

        Field privateField = Controller.class.
                getDeclaredField("operationsListArrayStack");

        privateField.setAccessible(true);

        LinkedList<String> field = (LinkedList<String>) privateField.get(theController);

        assertTrue(field.get(0).equals("test"));

        for(int i = 0; i < 45; i++){ //45 is max list size
            theController.addArrayStackOperation("No. " + i);
        }

        assertFalse(field.contains("test"));
    }

    @Test
    public void testAddArrayJavaOperation() throws Exception {
        theController.addArrayStackJavaOperation("test");

        Field privateField = Controller.class.
                getDeclaredField("operationsListArrayStackJava");

        privateField.setAccessible(true);

        LinkedList<String> field = (LinkedList<String>) privateField.get(theController);

        assertTrue(field.get(0).equals("test"));

        for(int i = 0; i < 45; i++){ //45 is max list size
            theController.addArrayStackJavaOperation("No. " + i);
        }

        assertFalse(field.contains("test"));
    }

    @Test
    public void testAddQueueOperation() throws Exception {
        theController.addQueueOperation("test");

        Field privateField = Controller.class.
                getDeclaredField("operationsListQueue");

        privateField.setAccessible(true);

        LinkedList<String> field = (LinkedList<String>) privateField.get(theController);

        assertTrue(field.get(0).equals("test"));

        for(int i = 0; i < 45; i++){ //45 is max list size
            theController.addQueueOperation("No. " + i);
        }

        assertFalse(field.contains("test"));
    }

    @Test
    public void testAddQueueJavaOperation() throws Exception {
        theController.addQueueJavaOperation("test");

        Field privateField = Controller.class.
                getDeclaredField("operationsListQueueJava");

        privateField.setAccessible(true);

        LinkedList<String> field = (LinkedList<String>) privateField.get(theController);

        assertTrue(field.get(0).equals("test"));

        for(int i = 0; i < 45; i++){ //45 is max list size
            theController.addQueueJavaOperation("No. " + i);
        }

        assertFalse(field.contains("test"));
    }

    @Test
    public void testAddCircularOperation() throws Exception {
        theController.addCircularQueueOperation("test");

        Field privateField = Controller.class.
                getDeclaredField("operationsListCircularQueue");

        privateField.setAccessible(true);

        LinkedList<String> field = (LinkedList<String>) privateField.get(theController);

        assertTrue(field.get(0).equals("test"));

        for(int i = 0; i < 45; i++){ //45 is max list size
            theController.addCircularQueueOperation("No. " + i);
        }

        assertFalse(field.contains("test"));
    }

    @Test
    public void testAddCircJavaOperation() throws Exception {
        theController.addCircularQueueJavaOperation("test");

        Field privateField = Controller.class.
                getDeclaredField("operationsListCircularQueueJava");

        privateField.setAccessible(true);

        LinkedList<String> field = (LinkedList<String>) privateField.get(theController);

        assertTrue(field.get(0).equals("test"));

        for(int i = 0; i < 45; i++){ //45 is max list size
            theController.addCircularQueueJavaOperation("No. " + i);
        }

        assertFalse(field.contains("test"));
    }

    @Test
    public void testSwitchQueueList() throws Exception {
        theController.addCircularQueueOperation("different");
        theController.addQueueOperation("test");

        Field privateField = View.class.
                getDeclaredField("previousQueueOperations");

        privateField.setAccessible(true);

        JList<String> field = (JList<String>) privateField.get(theView);
        field.setSelectedIndex(0);
        assertTrue(field.getSelectedValue().equals("test"));

        theController.switchQueueOperationsList("Circular");

        field.setSelectedIndex(0);
        assertFalse(field.getSelectedValue().equals("test"));

        theController.switchQueueOperationsList("Normal");

        field.setSelectedIndex(0);
        assertFalse(field.getSelectedValue().equals("different"));
    }

    @Test
    public void testSwitchStackList() throws Exception {
        theController.addArrayStackOperation("different");
        theController.addStackOperation("test");

        Field privateField = View.class.
                getDeclaredField("previousStackOperations");

        privateField.setAccessible(true);

        JList<String> field = (JList<String>) privateField.get(theView);
        field.setSelectedIndex(0);
        assertTrue(field.getSelectedValue().equals("test"));

        theController.switchStackOperationsList("Array");

        field.setSelectedIndex(0);
        assertFalse(field.getSelectedValue().equals("test"));

        theController.switchStackOperationsList("Normal");

        field.setSelectedIndex(0);
        assertFalse(field.getSelectedValue().equals("different"));
    }


}