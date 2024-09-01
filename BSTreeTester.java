
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BSTreeTester {

    private BSTree<String> tree;

    @BeforeEach
    public void setUp() {
        tree = new BSTree<>();
    }

    @Test
    public void testInsert() {
        tree.insert("apple");
        tree.insert("banana");
        tree.insert("apple"); // duplicate key
        tree.insert("cherry");
        Assertions.assertEquals(3, tree.getSize());
        tree.insert("chosenone");
        Assertions.assertEquals(4, tree.getSize());
        tree.insert("nonable");
        Assertions.assertEquals(5, tree.getSize());
    }

    @Test
    public void testgetRoot() {
        Assertions.assertEquals(tree.getRoot(),null);
        tree.insert("apple");
        Assertions.assertEquals("apple", tree.getRoot().getKey());
        tree.insert("book");
        Assertions.assertEquals("apple", tree.getRoot().getKey());
    }

    @Test 
    public void testSize() {
        Assertions.assertEquals(0, tree.getSize());
        tree.insert("apple");
        Assertions.assertEquals(1, tree.getSize());
        tree.insertData("apple","sweet"); 
        Assertions.assertEquals(1, tree.getSize());

    }

    @Test
    public void testFindKey() {
        tree.insert("apple");
        tree.insert("banana");
        Assertions.assertTrue(tree.findKey("apple"));
        Assertions.assertTrue(tree.findKey("banana"));
        Assertions.assertFalse(tree.findKey("cherry"));
    }

    @Test
    public void testFindKeyNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            tree.findKey(null);
        });
    }

    @Test
    public void testInsertData() {
        tree.insert("apple");
        tree.insertData("apple", "info1");
        tree.insertData("apple", "info2");

        LinkedList<String> data = tree.findDataList("apple");
        Assertions.assertEquals(2, data.size());
        Assertions.assertTrue(data.contains("info1"));
        Assertions.assertTrue(data.contains("info2"));
    }

    @Test
    public void testInsertDataNullKey() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            tree.insertData(null, "info");
        });
    }

    @Test
    public void testInsertDataNullData() {
        tree.insert("apple");
        Assertions.assertThrows(NullPointerException.class, () -> {
            tree.insertData("apple", null);
        });
    }

    @Test
    public void testInsertDataKeyNotFound() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tree.insertData("apple", "info");
        });
    }

    @Test
    public void testFindDataList() {
        tree.insert("apple");
        tree.insertData("apple", "info1");
        tree.insertData("apple", "info2");

        LinkedList<String> data = tree.findDataList("apple");
        Assertions.assertEquals(2, data.size());
        Assertions.assertTrue(data.contains("info1"));
        Assertions.assertTrue(data.contains("info2"));
    }

    @Test
    public void testFindDataListNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            tree.findDataList(null);
        });
    }

    @Test
    public void testFindDataListKeyNotFound() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tree.findDataList("nonexistent");
        });
    }

    @Test
    public void testFindHeight() {
        // Test height of empty tree
        Assertions.assertEquals(-1, tree.findHeight());

        // Test height of tree with one node
        tree.insert("apple");
        Assertions.assertEquals(0, tree.findHeight());

        // Test height of tree with multiple nodes
        tree.insert("banana");
        tree.insert("cherry");
        Assertions.assertEquals(1, tree.findHeight()); 
    }

    @Test
    public void testIterator() {
        tree.insert("apple");
        tree.insert("banana");
        tree.insert("cherry");

        Iterator<String> iterator = tree.iterator();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("apple", iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("banana", iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("cherry", iterator.next());
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorNoSuchElement() {
        Iterator<String> iterator = tree.iterator();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            iterator.next(); // Should throw exception because the tree is empty
        });
    }

    @Test
    public void testinsertremovefindHeight() {
        
        tree.insert("apple");
        tree.insert("banana");
        tree.insert("cherry");
        tree.insertData("apple", "info1");
        tree.insertData("banana", "info2");

        // Check insert data
        Assertions.assertTrue(tree.findDataList("apple").contains("info1"));
        Assertions.assertTrue(tree.findDataList("banana").contains("info2"));
        
        // Remove data 
        tree.findDataList("apple").remove("info1");
        Assertions.assertFalse(tree.findDataList("apple").contains("info1"));

        // Check height
        Assertions.assertEquals(1, tree.findHeight());

        // Test iterator
        Iterator<String> iterator = tree.iterator();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("apple", iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("banana", iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("cherry", iterator.next());
        Assertions.assertFalse(iterator.hasNext());
    }
}