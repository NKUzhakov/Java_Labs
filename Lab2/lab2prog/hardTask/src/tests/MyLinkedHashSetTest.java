package tests;

import customDataStructures.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MyLinkedHashSetTest {
    @Test
    public void normalDataManipulationTest(){
        MyLinkedHashSet linkedHashSet = new MyLinkedHashSet();
        
        assertTrue(linkedHashSet.add("first"));
        linkedHashSet.add("second");
        Object[] strs = {"third", "fourth", "fifth"};
        linkedHashSet.addAll(strs);
        Object[] testRes1 = linkedHashSet.toArray();
        Object[] expectedArr1 = {"first", "second", "third", "fourth", "fifth"};
        assertArrayEquals(testRes1, expectedArr1);
        assertEquals(linkedHashSet.size(), 5);

        linkedHashSet.remove(0);
        linkedHashSet.remove(3);
        assertTrue(linkedHashSet.contains("third"));
        assertFalse(linkedHashSet.contains("fifth"));
        assertEquals(linkedHashSet.size(), 3);
        Object[] testRes2 = linkedHashSet.toArray();
        Object[] expectedArr2 = {"second", "third", "fourth"};
        assertArrayEquals(testRes2, expectedArr2);

        assertFalse(linkedHashSet.add("fourth"));
        Object[] testRes3 = linkedHashSet.toArray();
        assertArrayEquals(testRes3, expectedArr2);

        assertTrue(linkedHashSet.remove("third"));
        assertFalse(linkedHashSet.remove("Not existing"));
        
        Object[] expectedArr4 = {"second", "fourth"};
        Object[] testRes4 = linkedHashSet.toArray();
        assertArrayEquals(testRes4, expectedArr4);
        assertEquals(linkedHashSet.size(), 2);

        Object[] additional = {"new1", "second", "new2"};
        assertFalse(linkedHashSet.addAll(additional));
        Object[] testRes5 = linkedHashSet.toArray();
        Object[] expectedArr5 = {"second", "fourth", "new1", "new2"};
        assertArrayEquals(testRes5, expectedArr5);

        Object[] original = {"original1", "original2"};
        assertTrue(linkedHashSet.addAll(original));
        Object[] testRes6 = linkedHashSet.toArray();
        Object[] expectedArr6 = {"second", "fourth", "new1", "new2", "original1", "original2"};
        assertArrayEquals(testRes6, expectedArr6);


        assertEquals(linkedHashSet.get(0), "second");
        assertEquals(linkedHashSet.get(5), "original2");
    }
    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void wrongIndexAccess(){
        MyLinkedHashSet linkedHashSet = new MyLinkedHashSet();
        Object[] elements = {"first", "second", "third"};
        linkedHashSet.addAll(elements);
        linkedHashSet.get(5);
    }
    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void wrongIndexRemove(){
        MyLinkedHashSet linkedHashSet = new MyLinkedHashSet();
        Object[] elements = {"first", "second", "third"};
        linkedHashSet.addAll(elements);
        linkedHashSet.remove(5);
    }
}
