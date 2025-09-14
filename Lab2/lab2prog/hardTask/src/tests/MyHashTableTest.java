package tests;

import customDataStructures.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MyHashTableTest {
    @Test
    public void normalDataManipulationTest(){
        MyHashTable hashTable = new MyHashTable();
        for(int i = 0; i<16; i++){
            String currentElement = "Number " + (i+1);
            hashTable.add(currentElement);
        }
        for(int i = 0; i<16; i++){
            String currentElement = "Number " + (i+1);
            assertTrue(hashTable.contains(currentElement));
        }
        assertEquals(hashTable.size(), 16);

        for(int i = 0; i<8; i++){
            String currentElement = "Number " + (i+1);
            assertTrue(hashTable.remove(currentElement));
        }
        for(int i = 0; i<8; i++){
            String currentElement = "Number " + (i+1);
            assertFalse(hashTable.contains(currentElement));
        }
        for(int i = 9; i<16; i++){
            String currentElement = "Number " + (i+1);
            assertTrue(hashTable.contains(currentElement));
        }
        assertEquals(hashTable.size(), 8);
        
        assertFalse(hashTable.remove("Not existing"));
        assertEquals(hashTable.size(), 8);

        assertTrue(hashTable.remove("Number 15"));
        assertEquals(hashTable.size(), 7);
        assertFalse(hashTable.remove("Number 15"));
        assertEquals(hashTable.size(), 7);

    }
}
