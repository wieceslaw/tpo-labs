package ru.ifmo.se.task2;

/**
 * @author amifideles
 */
public class BinomialHeapNode {
    int key, degree;
    BinomialHeapNode parent;
    BinomialHeapNode sibling;
    BinomialHeapNode child;

    public BinomialHeapNode(int k) {
        key = k;
        degree = 0;
        parent = null;
        sibling = null;
        child = null;
    }

    public BinomialHeapNode reverse(BinomialHeapNode sib) {
        BinomialHeapNode ret;
        if (sibling != null) {
            ret = sibling.reverse(this);
        } else {
            ret = this;
        }
        sibling = sib;
        return ret;
    }

    public BinomialHeapNode findMinNode() {
        BinomialHeapNode x = this, y = this;
        int min = x.key;
        while (x != null) {
            if (x.key < min) {
                y = x;
                min = x.key;
            }
            x = x.sibling;
        }
        return y;
    }

    public BinomialHeapNode findANodeWithKey(int value) {
        BinomialHeapNode temp = this, node = null;
        while (temp != null) {
            if (temp.key == value) {
                node = temp;
                break;
            }
            if (temp.child == null) {
                temp = temp.sibling;
            } else {
                node = temp.child.findANodeWithKey(value);
                if (node == null) {
                    temp = temp.sibling;
                } else {
                    break;
                }
            }
        }
        return node;
    }

    public int getSize() {
        int result = 1;
        if (child != null) {
            result += child.getSize();
        }
        if (sibling != null) {
            result += sibling.getSize();
        }
        return result;
    }
}
