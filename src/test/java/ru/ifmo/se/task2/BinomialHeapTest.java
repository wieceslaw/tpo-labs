package ru.ifmo.se.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author amifideles
 */
class BinomialHeapTest {
    private BinomialHeap binomialHeap;

    @Test
    void name() {
        BinomialHeap binHeap = new BinomialHeap();

        binHeap.insert(12);
        binHeap.insert(8);
        binHeap.insert(5);
        binHeap.insert(15);
        binHeap.insert(7);
        binHeap.insert(2);
        binHeap.insert(9);

        System.out.println("Size of the binomial heap is "
                + binHeap.getSize());
        assertEquals(7, binHeap.getSize());
    }

    @BeforeEach
    public void setUp() {
        binomialHeap = new BinomialHeap();
    }

    @Test
    @DisplayName("Test isEmpty() on an empty heap")
    public void testIsEmptyOnEmptyHeap() {
        assertTrue(binomialHeap.isEmpty());
    }

    @Test
    @DisplayName("Test getSize() on an empty heap")
    public void testGetSizeOnEmptyHeap() {
        assertEquals(0, binomialHeap.getSize());
    }

    @Test
    @DisplayName("Test insert() and getSize() on a non-empty heap")
    public void testInsertAndGetSize() {
        binomialHeap.insert(10);
        binomialHeap.insert(5);
        binomialHeap.insert(8);

        assertFalse(binomialHeap.isEmpty());
        assertEquals(3, binomialHeap.getSize());
    }

    @Test
    @DisplayName("Test findMinimum() on a non-empty heap")
    public void testFindMinimum() {
        binomialHeap.insert(15);
        binomialHeap.insert(20);
        binomialHeap.insert(10);

        assertEquals(10, binomialHeap.findMinimum());
    }

    @Test
    @DisplayName("Test delete() on a non-empty heap")
    public void testDelete() {
        binomialHeap.insert(15);
        binomialHeap.insert(20);
        binomialHeap.insert(10);

        binomialHeap.delete(20);

        assertEquals(2, binomialHeap.getSize());
        assertEquals(10, binomialHeap.findMinimum());
    }

    @Test
    @DisplayName("Test decreaseKeyValue() on a non-empty heap")
    public void testDecreaseKeyValue() {
        binomialHeap.insert(15);
        binomialHeap.insert(20);
        binomialHeap.insert(10);

        binomialHeap.decreaseKeyValue(20, 5);

        assertEquals(3, binomialHeap.getSize());
        assertEquals(5, binomialHeap.findMinimum());
    }

    @Test
    @DisplayName("Test extractMin() on a non-empty heap")
    public void testExtractMin() {
        binomialHeap.insert(15);
        binomialHeap.insert(20);
        binomialHeap.insert(10);

        assertEquals(10, binomialHeap.extractMin());
        assertEquals(2, binomialHeap.getSize());
        assertEquals(15, binomialHeap.findMinimum());
    }

    @Test
    @DisplayName("Test makeEmpty() on a non-empty heap")
    public void testMakeEmpty() {
        binomialHeap.insert(15);
        binomialHeap.insert(20);
        binomialHeap.insert(10);

        binomialHeap.makeEmpty();

        assertTrue(binomialHeap.isEmpty());
        assertEquals(0, binomialHeap.getSize());
    }

    @Test
    @DisplayName("Test unionNodes with equal degree and temp1.sibling.degree < temp2.degree")
    public void testMergeEqualDegreeWithTemp1Sibling() {
        BinomialHeap binHeap1 = new BinomialHeap();
        binHeap1.insert(5);
        binHeap1.insert(10);

        BinomialHeap binHeap2 = new BinomialHeap();
        binHeap2.insert(8);
        binHeap2.insert(15);

        binHeap1.unionNodes(binHeap2.getRoot()); // Assuming you have a method to get the root of binHeap

        assertEquals(4, binHeap1.getSize());
        assertEquals(5, binHeap1.findMinimum());
    }

    @Test
    @DisplayName("Test unionNodes with temp1.sibling == null")
    public void testMergeWithTemp1SiblingNull() {
        BinomialHeap binHeap1 = new BinomialHeap();
        binHeap1.insert(5);
        binHeap1.insert(10);

        BinomialHeap binHeap2 = new BinomialHeap();
        binHeap2.insert(8);
        binHeap2.insert(15);

//        binHeap1.getRoot().sibling = null; // Set temp1.sibling to null

        binHeap1.unionNodes(binHeap2.getRoot());
        binHeap1.displayHeap();
        assertEquals(4, binHeap1.getSize());
        assertEquals(5, binHeap1.findMinimum());
    }

    @Test
    @DisplayName("Test unionNodes with temp1.sibling.degree > temp2.degree")
    public void testMergeWithTemp1SiblingDegreeGreaterThanTemp2Degree() {
        BinomialHeap binHeap1 = new BinomialHeap();
        binHeap1.insert(5);
        binHeap1.insert(10);

        BinomialHeap binHeap2 = new BinomialHeap();
        binHeap2.insert(8);
        binHeap2.insert(15);

        // Setting temp1.sibling.degree > temp2.degree
        binHeap1.getRoot().sibling.degree = 3;

        binHeap1.unionNodes(binHeap2.getRoot());

        assertEquals(4, binHeap1.getSize());
        assertEquals(5, binHeap1.findMinimum());
    }

}