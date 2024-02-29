package ru.ifmo.se.task2;

/**
 * @author amifideles
 */
public class BinomialHeap {
    private BinomialHeapNode nodes;

    public BinomialHeap() {
        nodes = null;
    }

    public int getSize() {
        if (nodes == null) {
            return 0;
        }
        return nodes.getSize();
    }

    public boolean isEmpty() {
        return nodes == null;
    }

    public void clear() {
        nodes = null;
    }

    public void insert(int value) {
        if (value > 0) {
            BinomialHeapNode temp = new BinomialHeapNode(value);
            if (nodes == null) {
                nodes = temp;
            } else {
                unionNodes(temp);
            }
        }
    }

    public void merge(BinomialHeap heap) {
        unionNodes(heap.nodes);
    }

    private void mergeNode(BinomialHeapNode binHeap) {
        BinomialHeapNode temp1 = nodes, temp2 = binHeap;
        while ((temp1 != null) && (temp2 != null)) {
            if (temp1.degree == temp2.degree) {
                BinomialHeapNode tmp = temp2;
                temp2 = temp2.sibling;
                tmp.sibling = temp1.sibling;
                temp1.sibling = tmp;
                temp1 = tmp.sibling;
            } else {
                if (temp1.degree < temp2.degree) {
                    if ((temp1.sibling == null) || (temp1.sibling.degree > temp2.degree)) {
                        BinomialHeapNode tmp = temp2;
                        temp2 = temp2.sibling;
                        tmp.sibling = temp1.sibling;
                        temp1.sibling = tmp;
                        temp1 = tmp.sibling;
                    } else {
                        temp1 = temp1.sibling;
                    }
                } else {
                    BinomialHeapNode tmp = temp1;
                    temp1 = temp2;
                    temp2 = temp2.sibling;
                    temp1.sibling = tmp;
                    if (tmp == nodes) {
                        nodes = temp1;
                    }
                }
            }
        }
        if (temp1 == null) {
            temp1 = nodes;
            while (temp1.sibling != null) {
                temp1 = temp1.sibling;
            }
            temp1.sibling = temp2;
        }
    }

    private void unionNodes(BinomialHeapNode binHeap) {
        mergeNode(binHeap);
        BinomialHeapNode prevTemp = null, temp = nodes, nextTemp = nodes.sibling;
        while (nextTemp != null) {
            if ((temp.degree != nextTemp.degree) ||
                    ((nextTemp.sibling != null) && (nextTemp.sibling.degree == temp.degree))) {
                prevTemp = temp;
                temp = nextTemp;
            } else {
                if (temp.key <= nextTemp.key) {
                    temp.sibling = nextTemp.sibling;
                    nextTemp.parent = temp;
                    nextTemp.sibling = temp.child;
                    temp.child = nextTemp;
                    temp.degree++;
                } else {
                    if (prevTemp == null) {
                        nodes = nextTemp;
                    } else {
                        prevTemp.sibling = nextTemp;
                    }
                    temp.parent = nextTemp;
                    temp.sibling = nextTemp.child;
                    nextTemp.child = temp;
                    nextTemp.degree++;
                    temp = nextTemp;
                }
            }
            nextTemp = temp.sibling;
        }
    }

    public int findMinimum() {
        return nodes.findMinNode().key;
    }

    public void delete(int value) {
        if ((nodes != null) && (nodes.findANodeWithKey(value) != null)) {
            decreaseKeyValue(value, findMinimum() - 1);
            extractMin();
        }
    }

    public boolean decreaseKeyValue(int oldValue, int newValue) {
        if (nodes == null) {
            return false;
        }
        BinomialHeapNode temp = nodes.findANodeWithKey(oldValue);
        if (temp == null) {
            return false;
        }
        temp.key = newValue;
        BinomialHeapNode tempParent = temp.parent;
        while ((tempParent != null) && (temp.key < tempParent.key)) {
            int z = temp.key;
            temp.key = tempParent.key;
            tempParent.key = z;
            temp = tempParent;
            tempParent = tempParent.parent;
        }
        return true;
    }

    public int extractMin() {
        if (nodes == null) {
            return -1;
        }

        BinomialHeapNode temp = nodes, prevTemp = null;
        BinomialHeapNode minNode = nodes.findMinNode();

        while (temp.key != minNode.key) {
            prevTemp = temp;
            temp = temp.sibling;
        }

        if (prevTemp == null) {
            nodes = temp.sibling;
        } else {
            prevTemp.sibling = temp.sibling;
        }

        temp = temp.child;
        BinomialHeapNode fakeNode = temp;

        while (temp != null) {
            temp.parent = null;
            temp = temp.sibling;
        }

        if ((nodes != null) || (fakeNode != null)) {
            if (nodes == null) {
                nodes = fakeNode.reverse(null);
            } else if (fakeNode != null) {
                unionNodes(fakeNode.reverse(null));
            }
        }
        return minNode.key;
    }
}
