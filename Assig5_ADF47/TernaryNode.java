//Antonino Febbraro
//Assignment 5 - Ternary tree
//Due April 20th, 2016

class TernaryNode<T> {
    private T data;
    private TernaryNode<T> leftChild;  // Reference to left child
    private TernaryNode<T> rightChild; // Reference to right child
    private TernaryNode<T> middleChild; // Reference to middle child

    public TernaryNode() {
        this(null); // Call next constructor
    }

    public TernaryNode(T dataPortion) {
        this(dataPortion, null, null,null); // Call next constructor
    }

    public TernaryNode(T dataPortion, TernaryNode<T> newLeftChild,TernaryNode<T> newMiddleChild,
                      TernaryNode<T> newRightChild) {
        data = dataPortion;
        leftChild = newLeftChild;
        middleChild = newMiddleChild;
        rightChild = newRightChild;
    }

    /** Retrieves the data portion of this node.
     *  @return  The object in the data portion of the node. */
    public T getData() {
        return data;
    }

    /** Sets the data portion of this node.
     *  @param newData  The data object. */
    public void setData(T newData) {
        data = newData;
    }

    /** Retrieves the left child of this node.
     *  @return  The node’s left child. */
    public TernaryNode<T> getLeftChild() {
        return leftChild;
    }

    /** Retrieves the left child of this node.
     *  @return  The node’s middle child. */
    public TernaryNode<T> getMiddleChild() {
        return middleChild;
    }

    /** Sets this node’s left child to a given node.
     *  @param newLeftChild  A node that will be the left child. */
    public void setLeftChild(TernaryNode<T> newLeftChild) {
        leftChild = newLeftChild;
    }

    /** Sets this node’s left child to a given node.
     *  @param newLeftChild  A node that will be the middle child. */
    public void setMiddleChild(TernaryNode<T> newMiddleChild) {
        middleChild = newMiddleChild;
    }

    /** Detects whether this node has a left child.
     *  @return  True if the node has a left child. */
    public boolean hasLeftChild() {
        return leftChild != null;
    }

    /** Detects whether this node has a middle child.
     *  @return  True if the node has a middle child. */
    public boolean hasMiddleChild() {
        return middleChild != null;
    }

    /** Retrieves the right child of this node.
     *  @return  The node’s right child. */
    public TernaryNode<T> getRightChild() {
        return rightChild;
    }

    /** Sets this node’s right child to a given node.
     *  @param newRightChild  A node that will be the right child. */
    public void setRightChild(TernaryNode<T> newRightChild) {
        rightChild = newRightChild;
    }

    /** Detects whether this node has a right child.
     *  @return  True if the node has a right child. */
    public boolean hasRightChild() {
        return rightChild != null;
    }

    /** Detects whether this node is a leaf.
     *  @return  True if the node is a leaf. */
    public boolean isLeaf() {
        return (leftChild == null) && (rightChild == null) && (middleChild == null);
    }

    /** Counts the nodes in the subtree rooted at this node.
     *  @return  The number of nodes in the subtree rooted at this node. */
    public int getNumberOfNodes() {
        int leftNumber = 0;
        int middleNumber = 0;
        int rightNumber = 0;

        if (leftChild != null) {
            leftNumber = leftChild.getNumberOfNodes();
        }

        if(middleChild != null) {

          middleNumber = middleChild.getNumberOfNodes();
        }

        if (rightChild != null) {
            rightNumber = rightChild.getNumberOfNodes();
        }

        return 1 + leftNumber + rightNumber + middleNumber;
    }

    /** Computes the height of the subtree rooted at this node.
     *  @return  The height of the subtree rooted at this node. */
    public int getHeight() {
        return getHeight(this); // Call private getHeight
    }

    private int getHeight(TernaryNode<T> node) {
        int height = 0;

        if (node != null)
            height = 1 + Math.max(getHeight(node.getLeftChild()),Math.max(getHeight(node.getMiddleChild()),
                                  getHeight(node.getRightChild())));

        return height;
    }

    /** Copies the subtree rooted at this node.
     *  @return  The root of a copy of the subtree rooted at this node. */
    public TernaryNode<T> copy() {
        TernaryNode<T> newRoot = new TernaryNode<>(data);

        if (leftChild != null) {
            newRoot.setLeftChild(leftChild.copy());
        }

        if (middleChild != null) {
            newRoot.setMiddleChild(middleChild.copy());
        }

        if (rightChild != null) {
            newRoot.setRightChild(rightChild.copy());
        }

        return newRoot;
    }
}
