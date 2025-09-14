package tests;

import customDataStructures.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class IMyListTest{
    @Test
    public void normalDataManipulationTest(){
        MyLinkedList linkedList = new MyLinkedList();
        IListDataManipulationTest(linkedList);
        MyArrayList arrayList = new MyArrayList();
        IListDataManipulationTest(arrayList);
    }

    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void linkedListWrongIndexInput(){
        IListWrongIndexInput(new MyLinkedList());
    }
    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void arrayListWrongIndexInput(){
        IListWrongIndexInput(new MyArrayList());
    }
    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void linkedListWrongIndexArrayInput(){
        IListWrongIndexArrayInput(new MyLinkedList());
    }
    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void arrayListWrongIndexArrayInput(){
        IListWrongIndexArrayInput(new MyArrayList());
    }
    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void linkedListWrongIndexAccess(){
        IListWrongIndexAccess(new MyLinkedList());
    }
    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void arrayListWrongIndexAccess(){
        IListWrongIndexAccess(new MyArrayList());
    }
    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void linkedListWrongIndexRemove(){
        IListWrongIndexRemove(new MyLinkedList());
    }
    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void arrayListWrongIndexRemove(){
        IListWrongIndexRemove(new MyArrayList());
    }
    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void linkedListWrongIndexSet(){
        IListWrongIndexSet(new MyLinkedList());
    }
    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void arrayListWrongIndexSet(){
        IListWrongIndexSet(new MyArrayList());
    }


    private void IListDataManipulationTest(IMyList list){
        list.add("first");
        list.add("second");
        Object[] strs = {"third", "fourth", "fifth"};
        list.addAll(strs);
        Object[] testRes1 = list.toArray();
        Object[] expectedArr1 = {"first", "second", "third", "fourth", "fifth"};
        assertArrayEquals(testRes1, expectedArr1);
        assertEquals(list.size(), 5);

        list.remove(0);
        assertEquals(list.size(), 4);
        list.add(2, "NEW!");
        assertEquals(list.size(), 5);
        Object[] testRes2 = list.toArray();
        Object[] expectedArr2 = {"second", "third", "NEW!", "fourth", "fifth"};
        assertArrayEquals(testRes2, expectedArr2);
        assertEquals(list.get(2), "NEW!");

        String[] newStrs = {"new1", "new2", "new3", "NEW!", "Another"};
        list.addAll(5, newStrs);
        assertEquals(list.size(), 10);
        Object[] testRes3 = list.toArray();
        Object[] expectedArr3 = {"second", "third", "NEW!", "fourth", "fifth", "new1", "new2", "new3", "NEW!", "Another"};
        assertArrayEquals(testRes3, expectedArr3);


        assertEquals(list.indexOf("NEW!"), 2);
        list.remove(2);
        assertEquals(list.size(), 9);
        Object[] expectedArr4 = {"second", "third", "fourth", "fifth", "new1", "new2", "new3", "NEW!", "Another"};
        Object[] testRes4 = list.toArray();
        assertArrayEquals(testRes4, expectedArr4);

        list.set(4, "not new1");
        Object[] expectedArr5 = {"second", "third", "fourth", "fifth", "not new1", "new2", "new3", "NEW!", "Another"};
        Object[] testRes5 = list.toArray();
        assertArrayEquals(testRes5, expectedArr5);
    }

    private void IListWrongIndexInput(IMyList list){
        Object[] initialValues = {"first", "second"};
        list.addAll(initialValues);
        list.add(4, "sometext");
    }
    private void IListWrongIndexArrayInput(IMyList list){
        Object[] initialValues = {"first", "second"};
        list.addAll(initialValues);
        Object[] extraValues = {"N", "N+1", "N+2"};
        list.addAll(4, extraValues);
    }
    private void IListWrongIndexAccess(IMyList list){
        Object[] initialValues = {"first", "second"};
        list.addAll(initialValues);
        Object obj = list.get(4);
    }
    private void IListWrongIndexRemove(IMyList list){
        Object[] initialValues = {"first", "second"};
        list.addAll(initialValues);
        list.remove(4);
    }
    private void IListWrongIndexSet(IMyList list){
        Object[] initialValues = {"first", "second"};
        list.addAll(initialValues);
        list.set(4, "New value");
    }

}


