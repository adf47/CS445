
import java.io.*;
import java.util.*;

/**
 * LongestCommonSubsequence is a program that will determine the longest string that is 
 * a subsequence of two input strings. This program applies a brute force solution technique.
 * 
 * @author Charles Hoot
 * @version 4.0
 */
public class LongestCommonSubsequence {

    public static void main(String args[]) {
        BagInterface<String> toCheckContainer = null;

        Scanner input;
        input = new Scanner(System.in);

        System.out.println("This program determines the longest string that is a subsequence of two input string.");
        System.out.println("Please enter the first string:");
        String first = input.next();

        System.out.println("Please enter the second string:");
        String second = input.next();



        // ADD CODE HERE TO CREATE THE BAG WITH THE INITIAL STRING
        ArrayBag<String> one = new ArrayBag();
        toCheckContainer = one;
        toCheckContainer.add(first);
        toCheckContainer.add(second);
        
        System.out.println(toCheckContainer);
		
        System.out.println("The strings to check are: " + toCheckContainer);
        
        boolean test = isSubsequence(first,second);
		if(test) System.out.println("YES "+first +" is a subsequence of "+ second);
		else System.out.println("NO "+first +" is NOT a subsequence of "+ second);
		
		//STEP 7 CODE DONE HERE
		toCheckContainer.clear(); 
		int size = first.length()-1;
		combinations("",first,toCheckContainer,size);
        
        
        System.out.println("Here is the bag of subsequences! "+toCheckContainer); //to test
        
    	String bestSubsequence = new String();
    	
    	if(size==1)
    		bestSubsequence = first;
    	else
    	{
    		Object [] b = toCheckContainer.toArray();
    		//String [] t2 = toCheckContainer.toArray();
    		String [] t = new String [b.length];
    		Object []t2 = new String [b.length];
    		ArrayBag Maxes = new ArrayBag();
    		int aSize = 0;
    		int x = 0;
    		String max = "";
    		int maxSize = 0;
    		
    		/*while(x < b.length)
    		{
    		
    			t[x] = b[x].toString();
    			
    			if(toCheckContainer.getFrequencyOf(t[x])>1)
    			{
    				max = t[x];
    				t2[x] = max;
    				maxSize = toCheckContainer.getFrequencyOf(t[x]);
    			}
    				
    			//int tSize = b[x].toString().length();
    			x++;
    		}*/	
    		
    		
    		//String longest = getLongestString(b);
    		String longest = lcs(first,second);
    		
    		bestSubsequence = longest;
    	}
    			

        // ADD CODE HERE TO CHECK THE STRINGS IN THE BAG
        System.out.println("Found " + bestSubsequence + " for the longest common subsequence");

    }

    /**
     * Determine if one string is a subsequence of the other.
     *
     * @param check See if this is a subsequence of the other argument.
     * @param other The string to check against. 
     * @return     A boolean if check is a subsequence of other. 
     */
    public static boolean isSubsequence(String check, String against) {
        boolean result = false;

        // ADD CODE HERE TO CHECK IF WE HAVE A SUBSEQUENCE
        String s = check;
        String t = against;
        int M = s.length();
        int N = t.length();

        int i = 0;
        for (int j = 0; j < N; j++) {
            if (s.charAt(i) == t.charAt(j)) i++;
            if (i == M) return true;
        }
        return false;
        
        
    }
    
    public static void combinations(String suffix,String prefix,BagInterface<String> bag, int s){
        if(prefix.length()<0)return;
        //System.out.println(suffix);
        //if(suffix.length() == (prefix.length()))
        //if(suffix.length() == s && suffix.length()!=1)
        	bag.add(suffix);
        for(int i=0;i<prefix.length();i++)
         combinations(suffix+prefix.charAt(i),prefix.substring(i+1,prefix.length()),bag,s);
    }
    
     public static String getLongestString(Object[] a) {
     //System.out.println("In the method");
      int maxLength = 0;
      String longestString = null;
      for (int x = 0;x<a.length;x++) {
      	  String s = a[x].toString();	
          if (s.length() > maxLength) {
              maxLength = s.length();
              longestString = s;
          }
      }
      return longestString;
  }
  
  
  public static String lcs(String a, String b){
    int aLen = a.length();
    int bLen = b.length();
    if(aLen == 0 || bLen == 0){
        return "";
    }else if(a.charAt(aLen-1) == b.charAt(bLen-1)){
        return lcs(a.substring(0,aLen-1),b.substring(0,bLen-1))
            + a.charAt(aLen-1);
    }else{
        String x = lcs(a, b.substring(0,bLen-1));
        String y = lcs(a.substring(0,aLen-1), b);
        return (x.length() > y.length()) ? x : y;
    }
}
  
  
}
