//Antonino Febbraro
//Assignment 3 -- FriendsCoupon
//Due March 6, 2016

import java.util.*;
import java.io.*;
import java.text.*;
import javax.swing.*;
import java.sql.Timestamp;
import java.util.Date;

public class FriendsCoupon
{
  public static boolean solved = false;
  public static int num = 0; //the number of coupons allowed to be distributed.
  //the array of all the coupns possible (I only used 26)
  public static char [] coupons = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N',
  'O','P','Q','R','S','T','U','V','W','X','Y','Z'};


  public static void main (String [] args) //throws IOException
  {
      //RUNNING MY TEST METHODS HERE!!!!!!!!!!

      System.out.println("Running the test methods first.");
      //Running the test methods
      testReject();
      testIsFullSolution();
      testExtend();
      testNext();




      //read in a file here an asign to the people array.
      num = Integer.parseInt(args[1]); //#of coupons to be distributed
      String file = args[0];
      BufferedReader br;
      int size = 0;
      int [][] people = null;

      try
      {
        File Files = new File(file);

        BufferedReader temp = new BufferedReader(new FileReader(Files));
        String siz = temp.readLine();
        int numOfPpl = siz.length()-(siz.length()/2);
        //System.out.println(numOfPpl);
        size = numOfPpl;
        int [][] tempAr = new int[numOfPpl][numOfPpl];

					br = new BufferedReader(new FileReader(Files));

 					String line;
          int lineCount = 0;

 					while ((line = br.readLine()) != null) { //READING IN THE FILE

            String[] numbers = line.split(" ");
            for ( int i = 0 ; i < numOfPpl ; i++)
              tempAr[lineCount][i] = Integer.parseInt(numbers[i]);

              lineCount++;

 					}
          people = Arrays.copyOf(tempAr,tempAr.length);
        }
        catch(Exception e)
        {
          System.out.println("File not found.");
        }

      char [] partial = new char[size];

      //TEST TO SEE IF MATRIX IS SYMETRIC
      if(!isSymmetric(people))
      {
        System.out.println("The array is not symmetric. Please submit a new Frenship matrix.");
        System.exit(0);
      }

      int counts = 0;
      int temps = 65;

      java.util.Date date= new java.util.Date();
      System.out.println("\n\nProgram started: "+new Timestamp(date.getTime()));

      solve(people,partial,counts,temps);

      if(!solved)
        System.out.println("No Assigments Possible.");
  }

  public static void solve(int [][] people, char [] partial,int counts, int temps)
  {

    //System.out.println("Checking to see if rejected.");
    if (reject(people,partial,counts))
    {
      return;
    }
    if (isFullSolution(people,partial,counts))
    {
       System.out.println("\nSOLUTION FOUND: [User 1,User2,...etc.]");
       System.out.println(Arrays.toString(partial));
       solved = true;

       java.util.Date date= new java.util.Date();
       System.out.println("\nSolution found at: "+new Timestamp(date.getTime()));

       System.exit(0); //NOTE: if you get rid of the exit(0) call it will find
                      //all solutions!
     }
    //else System.out.println("Solution not found yet. Still working.");
    char [] attempt = extend(people,partial,counts,temps);
    while (attempt != null)
    {
      solve(people,attempt,counts+1,temps);

      attempt = next(attempt);
    }

  }

  //Reject method
  public static boolean reject(int [][] people,char [] partial,int counts)
  {
    boolean result = false;

    if(partial[0]==0) return false;

    for (int r=0; r<people.length; r++)
    {
      for (int c=0; c<people[r].length; c++)
      {
          //checking to see if friends have the same coupon or not
          if(people[r][c]==1 && people[c][r]==1 && partial[r]==partial[c] && partial[r]!=0)
          {
             result = true;
          }
      }
    }

    return result;
  }

  //isFullSolution method
  public static boolean isFullSolution(int [][] people,char [] partial,int counts)
  {
    boolean result = false;
    boolean test = false;

      //System.out.println("Counts equals: "+counts);

    //if(counts == partial.length) test = true;

    if(!reject(people,partial,counts)) result = true;

    //making sure all values in partial array are filled
    for(int x = 0; x<partial.length;x++)
    {
      if(partial[x]==0)
      {
        result = false;
      }
    }

    return result;
  }

  //extend method
  public static char [] extend(int [][] people,char [] partial,int counts, int temps)
  {
      if(counts == partial.length){
        return null;
      }

      char [] newChar = Arrays.copyOf(partial,partial.length);
      newChar[counts] = (char)temps;

      return newChar;
  }

  //next method
  public static char [] next(char [] partial)
  {
      char [] newChar = Arrays.copyOf(partial,partial.length);

      for(int x = 0;x<partial.length;x++){
          if((x+1)==partial.length || partial[x+1]==0){
            for(int i = 0;i<coupons.length;i++){
              if(coupons[i] == partial[x] && i < num-1){
                newChar[x] = coupons[i+1];
                return newChar;
              }
            }
          }
      }
      return null;
  }

  //method for testing reject methof
  public static void testReject()
  {
    System.out.println("\nTESTING REJECT METHOD");
    int counts = 0;
    int temps = 65;
    int num = 0;

    System.out.println("Testing reject on a full array should return true.");
    int [][] people = {{0,1,1,0},{1,0,0,0},{1,0,0,1},{0,0,1,0}};
    char [] test = {'A','B','A','A'};
    if(reject(people,test,counts))
      System.out.println("        PASSES.");
    else System.out.println("        FAILS.");

    System.out.println("Testing reject with an half full array, should return false.");
    char [] test2 = new char[4];
    test2[0] = 'A';
    test2[1] = 'B';
    if(reject(people,test2,counts))
      System.out.println("        FAILS.");
    else System.out.println("        PASSES.");

    System.out.println("Testing reject with an half full array, should return true.");
    char [] test5 = new char[4];
    test5[0] = 'A';
    test5[1] = 'A';
    if(reject(people,test5,counts))
      System.out.println("        PASSES.");
    else System.out.println("        FAILS.");

    System.out.println("Testing reject on an array of all bad solutions, should return false.");
    char [] test3 = {'A','B','A','B'};
    if(reject(people,test3,counts))
      System.out.println("        PASSES.");
    else System.out.println("        FAILS.");

    System.out.println("Testing reject an empty array, should return false.");
    char [] test4 = {0,0,0,0};
    if(reject(people,test4,counts))
      System.out.println("        FAILS.");
    else System.out.println("        PASSES.");
  }

  //method for testing isFullSolution method
  public static void testIsFullSolution()
  {
    int counts = 3;
    int temps = 65;
    int num = 0;

    System.out.println("\nTESTING IS FULL SOLUTION METHOD");

    System.out.println("Testing if given a completely full solution it returns true.");
    int [][] people = {{0,1,1,0},{1,0,0,0},{1,0,0,1},{0,0,1,0}};
    char [] test = {'A','B','B','A'};
    if(isFullSolution(people,test,counts))
      System.out.println("        PASSES.");
    else System.out.println("        FAILS.");

    System.out.println("Testing if gven not full solution with the last element missing returns false.");
    char [] test2 = {'A','B','A',0};
    if(isFullSolution(people,test2,counts))
      System.out.println("        FAILS.");
    else System.out.println("        PASSES.");

    System.out.println("Testing if gven not full solution when array is full, but not good solution returns false.");
    char [] test3 = {'A','B','A','A'};
    if(isFullSolution(people,test3,counts))
      System.out.println("        FAILS.");
    else System.out.println("        PASSES.");

    System.out.println("Testing if gven not full solution with a half full array returns false.");
    char [] test4 = {'A','B',0,0};
    if(isFullSolution(people,test4,counts))
      System.out.println("        FAILS.");
    else System.out.println("        PASSES.");

    System.out.println("Testing an empty array returns false.");
    char [] test5 = {0,0,0,0};
    if(isFullSolution(people,test5,counts))
      System.out.println("        FAILS.");
    else System.out.println("        PASSES.");

  }

  //method for testing extend
  public static void testExtend()
  {
    int count = 0;
    int temps = 65;
    int num = 0;

    System.out.println("\nTESTING EXTEND METHOD");
    int [][] people = {{0,1,1,0},{1,0,0,0},{1,0,0,1},{0,0,1,0}};
    char [] test = new char[4];

    System.out.println("Testing the extend on empty array.All elements should be 'A'");
    for(int x = 0;x<4;x++)
      test = extend(people,test,x,temps);

    if(test[3] == 'A') System.out.println("        PASSES.");
    else System.out.println("        FAILS.");

    System.out.println("Testing extend on a full array, should return null.");
    char [] test2 = {'A','B','B','A'};
    count = 4;
    if(extend(people,test2,count,temps)==null) System.out.println("        PASSES.");
    else System.out.println("        FAILS.");

    System.out.println("Testing the extend on half full array.");
    char [] test3 = {'A','A',0,0};
    count = 2;
    char [] temp = extend(people,test3,count,temps);
    if(temp[2]=='A') System.out.println("        PASSES.");
    else System.out.println("        FAILS.");

  }

  //Test method to the next method
  public static void testNext()
  {
      System.out.println("\nTESTING THE NEXT METHOD");

      num = 2; //NOTE: for these test methods we are assuming that the max
              // number of coupons is 2!--- which is why num is set to two here.

      System.out.println("Testing next method with only one item in the array.");
      char [] test = {'A',0,0,0};
      char [] temp = next(test);
      if(temp[0]=='B') System.out.println("        PASSES.");
      else System.out.println("        FAILS.");

      System.out.println("Testing next method with array of size 2 that should return null.");
      char [] test2 = {'A','B',0,0};
      temp = next(test2);
      if(temp==null) System.out.println("        PASSES.");
      else System.out.println("        FAILS.");

      System.out.println("Testing next method with array that is full. Last element should now be 'B'");
      char [] test3 = {'A','B','B','A'};
      temp = next(test3);
      if(temp[3]=='B') System.out.println("        PASSES.");
      else System.out.println("        FAILS.");

      System.out.println("Testing next method with array that is full and cannot extend anymore and " +
      "should return null.");
      char [] test4 = {'A','B','B','B'};
      temp = next(test4);
      if(temp == null) System.out.println("        PASSES.");
      else System.out.println("        FAILS.");

      System.out.println("Testing an empty array, should return null.");
      char [] test5 = {0,0,0,0};
      temp = next(test5);
      if(temp == null) System.out.println("        PASSES.");
      else System.out.println("        FAILS.");

  }

  //For checking symmetry of the Arrays
  public static boolean isSymmetric( int [][] A)
  {
        for( int row=0; row < A.length; row++ )
        {
            if(A[row][row] != 0) return false;
        }

        return true;
    }


}
