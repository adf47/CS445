//Antonino Febbraro
// Test Client for Assig5TestClient

import java.util.Iterator;

public class Assig5TestClient
{
  public static void main (String [] args)
  {
    String [] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q"};
    TernaryTree treeA = new TernaryTree();
    TernaryTree treeB = new TernaryTree();
    TernaryTree treeC = new TernaryTree();
    TernaryTree treeD = new TernaryTree(letters[3]);
    TernaryTree treeE = new TernaryTree(letters[4]);
    TernaryTree treeF = new TernaryTree(letters[5]);
    TernaryTree treeG = new TernaryTree(letters[6]);
    TernaryTree treeH = new TernaryTree(letters[7]);
    TernaryTree treeI = new TernaryTree(letters[8]);
    TernaryTree treeJ = new TernaryTree(letters[9]);
    TernaryTree treeK = new TernaryTree(letters[10]);
    TernaryTree treeL = new TernaryTree(letters[11]);
    TernaryTree treeM = new TernaryTree(letters[12]);
    TernaryTree treeN = new TernaryTree(letters[13]);
    TernaryTree treeO = new TernaryTree(letters[14]);
    TernaryTree treeP = new TernaryTree();
    TernaryTree treeQ = new TernaryTree(letters[16]);
    //TernaryTreeInterface <String> treeb = treeB;
    //new TernaryTree<string>();

    //Made the tree here....test operations below
    treeP.setTree(letters[15],null,treeQ,null);
    treeL.setTree(letters[11],treeM,treeN,treeO);
    treeG.setTree(letters[6],treeK,null,treeL);
    treeE.setTree(letters[4],treeI,null,treeJ);
    //treeD.setTree(letters[3],treeI,null,treeJ);
    treeC.setTree(letters[2],null,treeG,treeH);
    treeB.setTree(letters[1],treeD,treeE,treeF);
    treeA.setTree(letters[0],treeB,treeP,treeC);
    //treeA.setTree(letters[0],treeB,null,treeC);

    //test Operators
    System.out.println("Tree height: "+treeA.getHeight());
    System.out.println("Number of nodes in tree: "+treeA.getNumberOfNodes());

    System.out.println("PreorderIterator");
    System.out.println();
    Iterator in = treeA.getPreorderIterator();
    int x = 0;
    while(in.hasNext())
    {

      System.out.print(" "+in.next()+" ");
    }
    System.out.println();

    System.out.println("PostorderIterator");
    System.out.println();
    Iterator in2 = treeA.getPostorderIterator();

    while(in2.hasNext())
    {
      System.out.print(" "+in2.next()+" ");
    }

    System.out.println();
    System.out.println("LevelOrderIterator");
    System.out.println();
    Iterator in3 = treeA.getLevelOrderIterator();

    while(in3.hasNext())
    {
      System.out.print(" "+in3.next()+" ");
    }
    System.out.println();
    treeA.clear();
    System.out.println();
    System.out.println("Check to see if Tree was cleared: "+treeA.isEmpty());//Should print true




  }
}
