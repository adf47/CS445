//Antonino Febbraro
//Assignment 5 - Ternary tree
//Due April 20th, 2016

import java.util.Iterator;
import java.util.NoSuchElementException;

import StackAndQueuePackage.*; // Needed by tree iterators

/**
 * A class that implements the ADT binary tree.
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author William C. Garrison
 * @version 4.5
 */
public class TernaryTree<T> implements TernaryTreeInterface<T> {
    private TernaryNode<T> root;

    public TernaryTree() {
        root = null;
    }

    public TernaryTree(T rootData) {
        root = new TernaryNode<>(rootData);
    }

    public TernaryTree(T rootData, TernaryTree<T> leftTree,TernaryTree<T> middleTree,
                      TernaryTree<T> rightTree) {
        privateSetTree(rootData, leftTree, middleTree,rightTree);
    }

    public void setTree(T rootData) {
        root = new TernaryNode<>(rootData);
    }

    public void setTree(T rootData, TernaryTreeInterface<T> leftTree,TernaryTreeInterface<T> middleTree,
                        TernaryTreeInterface<T> rightTree) {
        privateSetTree(rootData, (TernaryTree<T>)leftTree, (TernaryTree<T>)middleTree,
                       (TernaryTree<T>)rightTree);
    }

    private void privateSetTree(T rootData, TernaryTree<T> leftTree,TernaryTree<T> middleTree,
                                TernaryTree<T> rightTree) {
        root = new TernaryNode<>(rootData);

        if ((leftTree != null) && !leftTree.isEmpty()) {
            root.setLeftChild(leftTree.root);
        }

        if ((middleTree != null) && !middleTree.isEmpty()) {
            root.setMiddleChild(middleTree.root);
        }

        if ((rightTree != null) && !rightTree.isEmpty()) {
            if (rightTree != leftTree) {
                root.setRightChild(rightTree.root);
            } else {
                root.setRightChild(rightTree.root.copy());
            }
        }

        if ((leftTree != null) && (leftTree != this)) {
            leftTree.clear();
        }

        if ((middleTree != null) && (middleTree != this)) {
            middleTree.clear();
        }

        if ((rightTree != null) && (rightTree != this)) {
            rightTree.clear();
        }
    }

    public T getRootData() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        } else {
            return root.getData();
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    protected void setRootData(T rootData) {
        root.setData(rootData);
    }

    protected void setRootNode(TernaryNode<T> rootNode) {
        root = rootNode;
    }

    protected TernaryNode<T> getRootNode() {
        return root;
    }

    public int getHeight() {
        return root.getHeight();
    }

    public int getNumberOfNodes() {
        return root.getNumberOfNodes();
    }

    public Iterator<T> getPreorderIterator() {
        return new PreorderIterator();
    }

    /*NOTE: The reason why the Inorder Iterator is not needed, is because, it just.
    does not make sense to use it. The ADT does not define how to implement this iterator,
    meaning the ADT does not define whether you should go left child -- root -- middle child -- right child
    or if you should go left child -- middle child -- root -- right child. Since the ADT does not tell us which one
    to do, it does not really make sense as to implement this iterator. Hence, why we throw an
    UnsupportedOperationException.  */
    public Iterator<T> getInorderIterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> getPostorderIterator() {
        return new PostorderIterator();
    }

    public Iterator<T> getLevelOrderIterator() {
        return new LevelOrderIterator();
    }

//NOTE: Iterators that I must implement
    private class PreorderIterator implements Iterator<T> {
        private StackInterface<TernaryNode<T>> nodeStack;

        public PreorderIterator() {
            nodeStack = new LinkedStack<>();
            if (root != null) {
                nodeStack.push(root);
            }
        }

        public boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        public T next() {
            TernaryNode<T> nextNode;

            if (hasNext()) {
                nextNode = nodeStack.pop();
                TernaryNode<T> leftChild = nextNode.getLeftChild();
                TernaryNode<T> middleChild = nextNode.getMiddleChild();
                TernaryNode<T> rightChild = nextNode.getRightChild();

                // Push into stack in reverse order of recursive calls
                if (rightChild != null) {
                    nodeStack.push(rightChild);
                }

                if (middleChild != null) {
                    nodeStack.push(middleChild);
                }

                if (leftChild != null) {
                    nodeStack.push(leftChild);
                }
            } else {
                throw new NoSuchElementException();
            }

            return nextNode.getData();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public void iterativePreorderTraverse() {
        StackInterface<TernaryNode<T>> nodeStack = new LinkedStack<>();
        if (root != null) {
            nodeStack.push(root);
        }
        TernaryNode<T> nextNode;
        while (!nodeStack.isEmpty()) {
            nextNode = nodeStack.pop();
            TernaryNode<T> leftChild = nextNode.getLeftChild();
            TernaryNode<T> middleChild = nextNode.getMiddleChild();
            TernaryNode<T> rightChild = nextNode.getRightChild();

            // Push into stack in reverse order of recursive calls
            if (rightChild != null) {
                nodeStack.push(rightChild);
            }

            if (middleChild != null) {
                nodeStack.push(middleChild);
            }

            if (leftChild != null) {
                nodeStack.push(leftChild);
            }

            System.out.print(nextNode.getData() + " ");
        }
    }

    private class PostorderIterator implements Iterator<T> {
          private StackInterface<TernaryNode<T>> nodeStack;
          private TernaryNode<T> currentNode;

          public PostorderIterator() {
              nodeStack = new LinkedStack<>();
              currentNode = root;
          } // end default constructor

          public boolean hasNext() {
              return !nodeStack.isEmpty() || (currentNode != null);
          } // end hasNext
          public T next() {
              boolean foundNext = false;
              TernaryNode<T> leftChild, middleChild, rightChild, nextNode = null;

              // Find leftmost leaf
              while (currentNode != null) {
                  nodeStack.push(currentNode);
                  leftChild = currentNode.getLeftChild();
                  middleChild = currentNode.getMiddleChild();
                  rightChild = currentNode.getRightChild();
                  if (leftChild == null) {
                      if( middleChild == null ) {
                          currentNode = rightChild;
                      } else {
                          currentNode = middleChild;
                      }
                  } else {
                      currentNode = leftChild;
                  }
              } // end while

              // Stack is not empty either because we just pushed a node, or
              // it wasn't empty to begin with since hasNext() is true.
              // But Iterator specifies an exception for next() in case
              // hasNext() is false.

              if (!nodeStack.isEmpty()) {
                  nextNode = nodeStack.pop();
                  // nextNode != null since stack was not empty before pop

                  TernaryNode<T> parent = null;
                  if (!nodeStack.isEmpty()) {
                      parent = nodeStack.peek();
                      if (nextNode == parent.getLeftChild()) {
                        if(parent.getMiddleChild()!= null)
                          currentNode = parent.getMiddleChild();
                        else
                            currentNode = parent.getRightChild();
                      } else if( nextNode == parent.getMiddleChild() ) {
                          currentNode = parent.getRightChild();
                      } else {
                          currentNode = null;
                      }
                  } else {
                      currentNode = null;
                  }
              } else {
                  throw new NoSuchElementException();
              }

              return nextNode.getData();
          }


          public void remove() {
              throw new UnsupportedOperationException();
          }
      }

    //WORK: on this Iterator
    private class LevelOrderIterator implements Iterator<T> {
        private QueueInterface<TernaryNode<T>> nodeQueue;

        public LevelOrderIterator() {
            nodeQueue = new LinkedQueue<TernaryNode<T>>();
            if (root != null) {
                nodeQueue.enqueue(root);
            }
        }

        public boolean hasNext() {
            return !nodeQueue.isEmpty();
        }

        public T next() {
            TernaryNode<T> nextNode;

            if (hasNext()) {
                nextNode = nodeQueue.dequeue();
                TernaryNode<T> leftChild = nextNode.getLeftChild();
                TernaryNode<T> middleChild = nextNode.getMiddleChild();
                TernaryNode<T> rightChild = nextNode.getRightChild();

                // Add to queue in order of recursive calls
                if (leftChild != null) {
                    nodeQueue.enqueue(leftChild);
                }

                if( middleChild != null ) {
                    nodeQueue.enqueue(middleChild);
                }

                if (rightChild != null) {
                    nodeQueue.enqueue(rightChild);
                }
            } else {
                throw new NoSuchElementException();
            }

            return nextNode.getData();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
