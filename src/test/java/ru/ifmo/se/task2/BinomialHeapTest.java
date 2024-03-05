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
    public void testDeleteNodeFound() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(5);
        heap.insert(10);
        assertEquals(2, heap.getSize());

        heap.delete(5);
        assertEquals(1, heap.getSize());
        assertEquals(10, heap.findMinimum());
    }

    @Test
    public void testDeleteNodeNotFound() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(5);
        heap.insert(10);
        assertEquals(2, heap.getSize());
        assertThrows(IllegalArgumentException.class, () -> heap.delete(15));
    }

    @Test
    public void testDeleteNodeFromEmptyHeap() {
        BinomialHeap heap = new BinomialHeap();
        assertEquals(0, heap.getSize());
        assertThrows(IllegalArgumentException.class, () -> heap.delete(5)); // Attempting to delete from an empty heap
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

        binomialHeap.clear();

        assertTrue(binomialHeap.isEmpty());
        assertEquals(0, binomialHeap.getSize());
    }

    @Test
    void testMerge() {
        BinomialHeap binHeap1 = new BinomialHeap();
        binHeap1.insert(5);
        binHeap1.insert(10);
        binHeap1.insert(1);
        binHeap1.insert(3);
        binHeap1.insert(10);

        BinomialHeap binHeap2 = new BinomialHeap();
        binHeap2.insert(2);
        binHeap2.insert(15);
        binHeap2.insert(15);
        binHeap2.insert(2);

        binHeap2.merge(binHeap1);
        assertEquals(binHeap2.getSize(), 9);
    }

    @Test
    void testGetSizeOnSingleNode() {
        assertEquals(binomialHeap.getSize(), 0);
    }

    @Test
    void testGetSizeOnWithSibling() {
        binomialHeap.insert(2);
        binomialHeap.insert(3);
        binomialHeap.insert(1);
        assertEquals(binomialHeap.getSize(), 3);
    }

    @Test
    void testExtractMinOnEmptyHeap() {
        assertEquals(-1, binomialHeap.extractMin());
    }

    @Test
    void testDecreaseKeyEmptyHeap() {
        assertFalse(binomialHeap.decreaseKeyValue(1, 2));
    }

    @Test
    void testDecreaseKeyNotExists() {
        binomialHeap.insert(2);
        assertFalse(binomialHeap.decreaseKeyValue(1, 2));
    }

    @Test
    void test1() {
        BinomialHeap binHeap1 = new BinomialHeap();
        binHeap1.insert(5);

        BinomialHeap binHeap2 = new BinomialHeap();
        binHeap2.insert(2);
        binHeap2.insert(15);
        binHeap2.insert(11);
        binHeap2.insert(16);

        binHeap1.merge(binHeap2);
    }

    @Test
    void test2() {
        BinomialHeap binHeap1 = new BinomialHeap();
        binHeap1.insert(5);
        binHeap1.insert(2);
        binHeap1.insert(1);

        BinomialHeap binHeap2 = new BinomialHeap();
        binHeap2.insert(4);
        binHeap2.insert(3);
        binHeap2.insert(2);
        binHeap2.insert(1);

        binHeap1.merge(binHeap2);
    }

    @Test
    void testDecreaseKeyValueOnRootSibling() {
        binomialHeap.insert(6);
        binomialHeap.insert(3);
        binomialHeap.insert(5);
        binomialHeap.insert(4);
        binomialHeap.decreaseKeyValue(6, 1);
        assertEquals(binomialHeap.findMinimum(), 1);
    }

    @Test
    void testExtractMinOnRootSibling() {
        binomialHeap.insert(6);
        binomialHeap.insert(3);
        binomialHeap.insert(5);
        binomialHeap.insert(4);
        assertEquals(binomialHeap.extractMin(), 3);
    }

    @Test
    public void testDecreaseKeyValueSingleNode() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(5);

        // Уменьшаем значение узла
        assertTrue(heap.decreaseKeyValue(5, 3));

        // Проверяем, что значение узла уменьшилось
        assertEquals(3, heap.findMinimum());
    }

    @Test
    public void testDecreaseKeyValueMultipleNodes() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(5);
        heap.insert(8);
        heap.insert(10);

        // Уменьшаем значение узла
        assertTrue(heap.decreaseKeyValue(8, 6));

        // Проверяем, что значение узла уменьшилось
        assertEquals(5, heap.findMinimum());
    }

    @Test
    public void testDecreaseKeyValueNonexistentNode() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(5);
        heap.insert(8);

        // Пытаемся уменьшить значение несуществующего узла
        assertFalse(heap.decreaseKeyValue(10, 6));

        // Проверяем, что куча осталась неизменной
        assertEquals(5, heap.findMinimum());
    }

    @Test
    public void testDecreaseKeyValueEmptyHeap() {
        BinomialHeap heap = new BinomialHeap();

        // Пытаемся уменьшить значение в пустой куче
        assertFalse(heap.decreaseKeyValue(5, 3));

        // Проверяем, что куча осталась пустой
        assertTrue(heap.isEmpty());
    }
}
