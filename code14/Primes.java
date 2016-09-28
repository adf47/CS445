import java.io.*;
import java.util.*;

/**
 * Primes is a program that will compute prime numbers using the sieve of Eratosthenes.
 * 
 * @author Charles Hoot
  * @version 4.0
 */

    
public class Primes
{

    public static void main(String args[])
    {

        int max;
        
        System.out.println("Please enter the maximum value to test for primality");
        max = getInt("   It should be an integer value greater than or equal to 2.");
        
        // COMPLETE THE MAIN
        AList<Integer> canidates = new AList();
        
        for(int x = 1;x<max;x++)
        {
        	canidates.add(x+1);
        }
        System.out.println("Candidates to start with: "+canidates);
        
        AList<Integer> primes = new AList();
        AList<Integer> composites = new AList();
        
        int p = 1;
        while(p!=canidates.getLength())
        {
        if(isPrime(canidates.getEntry(p)))
        {
        int temp = canidates.remove(p);
        primes.add(temp);
        
         getComposites(canidates,composites,temp); 
         }
         else 
         {
         	p++;
         }
       
        
        System.out.println("\nCandidates: "+canidates);
        System.out.println("Primes: "+primes);
        System.out.println("Composites: "+composites); 
       
              
    }
    }
    
    
    /**
     * getComposites - Remove the composite values from possibles list and
     * put them in the composites list.
     *
     * @param  candidates   A list of integers holding the possible values.
     * @param  composites   A list of integers holding the composite values.
     * @param  prime   An Integer that is prime.
     */
    public static void getComposites(ListInterface<Integer> canidates, ListInterface<Integer> composites, Integer prime)
    {
        // COMPLETE THIS METHOD
         for(int t=1;t<=canidates.getLength();t++)
        {
        	if(canidates.getEntry(t)%prime==0 && canidates.getEntry(t)!=prime)
        	{
        		composites.add(canidates.getEntry(t));
        		canidates.remove(t);
        	}
        }
    }
    
    
    
    
    /**
     * Get an integer value.
     *
     * @return     An integer. 
     */
    private static int getInt(String rangePrompt)
    {
        Scanner input;
        int result = 10;        //Default value is 10
        try
        {
            input = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = input.nextInt();
            
        }
        catch(NumberFormatException e)
        {
            System.out.println("Could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }        
        catch(Exception e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;
                                    
    }  
    
    private static boolean isPrime(int num)
    {
    	int x = 2;
    	boolean result = true;
    	while(x!=1000)
    	{
    		if(num%x==0&&x!=num)
    			result = false;
    		
    		x++;	
    	}
    	return result;
    }  
    
}
