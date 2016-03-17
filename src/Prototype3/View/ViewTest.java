package src.Prototype3.View;

import org.junit.Before;
import org.junit.Test;
import src.Prototype3.Controller.Controller;
import src.Prototype3.Controller.Prototype3;
import src.Prototype3.Model.Model;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by Jamie on 17/03/2016.
 */
public class ViewTest {
    View theView;

    @Before
    public void setUp() throws Exception {
        theView = new View();
    }

    @Test
    public void testSetPreviousStackOperations() throws Exception {
        String[] test = {"test"};
        theView.setPreviousStackOperations(test);

        Field privateField = View.class.
                getDeclaredField("previousStackOperations");

        privateField.setAccessible(true);

        JList<String> field = (JList<String>) privateField.get(theView);
        field.setSelectedIndex(0);
        assertTrue(field.getSelectedValue().equals("test"));
    }

    @Test
    public void testSetPreviousQueueOperations() throws Exception {
        String[] test = {"test"};
        theView.setPreviousQueueOperations(test);

        Field privateField = View.class.
                getDeclaredField("previousQueueOperations");

        privateField.setAccessible(true);

        JList<String> field = (JList<String>) privateField.get(theView);
        field.setSelectedIndex(0);
        assertTrue(field.getSelectedValue().equals("test"));
    }

    @Test
    public void testSetPreviousStackJavaOperations() throws Exception {
        String[] test = {"test"};
        theView.setPreviousStackJavaOperations(test);

        Field privateField = View.class.
                getDeclaredField("stackJavaOperations");

        privateField.setAccessible(true);

        JList<String> field = (JList<String>) privateField.get(theView);
        field.setSelectedIndex(0);
        assertTrue(field.getSelectedValue().equals("test"));
    }

    @Test
    public void testSetPreviousQueueJavaOperations() throws Exception {
        String[] test = {"test"};
        theView.setPreviousQueueJavaOperations(test);

        Field privateField = View.class.
                getDeclaredField("queueJavaOperations");

        privateField.setAccessible(true);

        JList<String> field = (JList<String>) privateField.get(theView);
        field.setSelectedIndex(0);
        assertTrue(field.getSelectedValue().equals("test"));

    }

    @Test
    public void testResetPreviousOperations() throws Exception {
        String[] test = {"test"};
        theView.setPreviousStackOperations(test);

        theView.resetPreviousOperations();

        Field privateField = View.class.
                getDeclaredField("previousStackOperations");

        privateField.setAccessible(true);

        JList<String> field = (JList<String>) privateField.get(theView);
        field.setSelectedIndex(0);
        assertTrue(field.isSelectionEmpty());
    }

    @Test
    public void testToggleLabels() throws Exception {
        theView.toggleLabels(false);

        Field privateField = View.class.
                getDeclaredField("pushLabel");

        privateField.setAccessible(true);

        JLabel field = (JLabel) privateField.get(theView);

        assertFalse(field.isVisible());
    }

    @Test
    public void testSetStackPanel() throws Exception {
        DrawStackRepresentation drawStackRepresentation = new DrawStackRepresentation();

        Field privateField = View.class.
                getDeclaredField("stackDisplay");

        privateField.setAccessible(true);

        JPanel field = (JPanel) privateField.get(theView);

        int before = field.getComponentCount();

        theView.setStackPanel(drawStackRepresentation);

        int after = field.getComponentCount();

        assertTrue(before == (after - 1));
    }

    @Test
    public void testSetQueuePanel() throws Exception {
        DrawQueueRepresentation drawQueueRepresentation = new DrawQueueRepresentation();

        Field privateField = View.class.
                getDeclaredField("queueDisplay");

        privateField.setAccessible(true);

        JPanel field = (JPanel) privateField.get(theView);

        int before = field.getComponentCount();

        theView.setQueuePanel(drawQueueRepresentation);

        int after = field.getComponentCount();

        assertTrue(before == (after - 1));
    }

    @Test
    public void testToggleHarderPredictions() throws Exception {
        Field privateField = View.class.
                getDeclaredField("harderPredictions");

        privateField.setAccessible(true);

        JCheckBoxMenuItem field = (JCheckBoxMenuItem) privateField.get(theView);

        theView.toggleHarderPredictions(true, true);

        assertTrue(field.isSelected());
        assertTrue(field.isEnabled());
    }

    @Test
    public void testToggleCircularQueue() throws Exception {
        Field privateField = View.class.
                getDeclaredField("circularQueue");

        privateField.setAccessible(true);

        JRadioButtonMenuItem field = (JRadioButtonMenuItem) privateField.get(theView);

        theView.toggleCircularQueue(true);
        assertTrue(field.isEnabled());
        theView.toggleCircularQueue(false);
        assertFalse(field.isEnabled());
    }

    @Test
    public void testToggleArrayStack() throws Exception {
        Field privateField = View.class.
                getDeclaredField("arrayStack");

        privateField.setAccessible(true);

        JRadioButtonMenuItem field = (JRadioButtonMenuItem) privateField.get(theView);

        theView.toggleArrayStack(true);
        assertTrue(field.isEnabled());
        theView.toggleArrayStack(false);
        assertFalse(field.isEnabled());

    }

    @Test
    public void testToggleTabbedPane() throws Exception {
        Field privateField = View.class.
                getDeclaredField("tabbedPane1");

        privateField.setAccessible(true);

        JTabbedPane field = (JTabbedPane) privateField.get(theView);

        theView.toggleTabbedPane(0);
        assertTrue(field.getSelectedIndex() == 0);
        theView.toggleTabbedPane(1);
        assertTrue(field.getSelectedIndex() == 1);
    }
}