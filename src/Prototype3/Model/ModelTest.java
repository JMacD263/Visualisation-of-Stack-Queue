package src.Prototype3.Model;

import org.junit.Before;
import org.junit.Test;
import src.Prototype3.Controller.Controller;
import src.Prototype3.View.View;

import javax.naming.ldap.Control;

import static org.junit.Assert.*;

/**
 * Created by Jamie on 15/03/2016.
 *
 */
public class ModelTest {
    Model model;

    @Before
    public void setUp() throws Exception {
        model = new Model();
    }

    @Test
    public void testReset() throws Exception {
        model.push(1);
        model.enqueue(1);
        model.enqueueCircular(1);
        model.reset();
        assertTrue(model.getStack().empty());
        assertTrue(model.getQueue().isEmpty());
        assertTrue(model.getCircularQueue().isEmpty());
    }

    @Test
    public void testPush() throws Exception {
        model.push(1);
        assertFalse(model.getStack().isEmpty());
    }

    @Test
    public void testPop() throws Exception {
        model.push(1);
        int sizeBefore = model.getStack().size();
        assertTrue(model.pop() == 1);
        int sizeAfter = model.getStack().size();
        assertTrue(sizeAfter == (sizeBefore - 1));
    }

    @Test
    public void testPeek() throws Exception {
        model.push(1);
        assertTrue(model.peek() == 1);
    }


    @Test
    public void testEnqueue() throws Exception {
        model.enqueue(1);
        assertFalse(model.getQueue().isEmpty());
    }

    @Test
    public void testDequeue() throws Exception {
        model.enqueue(1);
        int sizeBefore = model.getQueue().size();
        assertTrue(model.dequeue() == 1);
        int sizeAfter = model.getQueue().size();
        assertTrue(sizeAfter == (sizeBefore - 1));
    }

    @Test
    public void testPeekQueue() throws Exception {
        model.enqueue(1);
        assertTrue(model.peekQueue() == 1);
    }

    @Test
    public void testEnqueueCircular() throws Exception {
        model.enqueueCircular(1);
        assertFalse(model.getCircularQueue().isEmpty());
    }

    @Test
    public void testDequeueCircular() throws Exception {
        model.enqueueCircular(1);
        int sizeBefore = model.getCircularQueue().getSize();
        assertTrue(model.dequeueCircular() == 1);
        int sizeAfter = model.getCircularQueue().getSize();
        assertTrue(sizeAfter == (sizeBefore - 1));
    }

    @Test
    public void testPeekCircular() throws Exception {
        model.enqueueCircular(1);
        assertTrue(model.peekCircular() == 1);
    }

    @Test
    public void testGetFrontAndGetRear() throws Exception {
        assertTrue(model.getFront() == 0);
        model.enqueueCircular(1);
        assertTrue(model.getRear() == 1);
    }

    @Test
    public void testCircularQueue() throws Exception {
        model.getCircularQueue().getList();
        model.enqueueCircular(1);
        model.enqueueCircular(2);
        model.enqueueCircular(3);
        model.enqueueCircular(4);
        model.enqueueCircular(5);
        model.enqueueCircular(6);
        model.enqueueCircular(7);
        model.enqueueCircular(8);
        model.enqueueCircular(9);
        model.enqueueCircular(10);
        model.dequeueCircular();
        model.enqueueCircular(1);
        model.enqueueCircular(11);
        assertTrue(model.getRear() == 11); //make sure that Circular Extended.
        model.enqueueCircular(12);
        model.enqueueCircular(13);
        model.enqueueCircular(14);
        model.enqueueCircular(15);
        model.enqueueCircular(16);
        model.enqueueCircular(17);
        model.enqueueCircular(18);
        model.enqueueCircular(19);
        model.enqueueCircular(20);
        assertEquals(model.getRear(), model.getFront()); //once full, tail goes to front.
    }

    @Test(expected=NullPointerException.class)
    public void testDequeueCircularEmpty() throws NullPointerException{
        model.dequeueCircular();
    }

    @Test(expected=NullPointerException.class)
    public void testPeekCircularEmpty() throws NullPointerException {
        model.peekCircular();
    }
}