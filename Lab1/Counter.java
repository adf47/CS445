
/**
 * The counter class implements a counter that will roll over to the initial
 * value when it hits the maximum value.
 * 
 * @author Charles Hoot 
 * @version 4.0
 */
public class Counter
{
    // PUT PRIVATE DATA FIELDS HERE
    private int count;
	private int Max;
	private int Min;
	private boolean test; 
	
    /**
     * The default constructor for objects of class Counter.  Minimum is 0 and the maximum
     * is the largest possible integer.
     */
    public Counter()
    {
        // ADD CODE FOR THE CONSTRUCTOR
        count = 0;
        Min = 0;
        Max = Integer.MAX_VALUE;
    }
    
    
    /**
     * The alternate constructor for objects of class Counter.  The minimum and maximum values are given as parameters.
     * The counter starts at the minimum value.
     * @param min The minimum value that the counter can have
     * @param max The maximum value that the counter can have
     * */
    public Counter(int min, int max)
    {
        // ADD CODE FOR THE ALTERNATE CONSTRUCTOR
        if(max > min){
        	count = min;
       	 	Min = min;
        	Max = max;
        }
        else
        {
        	throw new CounterInitializationException("No good");
        }
      
       
    }
    
    /**
     * Determine if two counters are in the same state
     *
     * @param  otherObject   the object to test against for equality
     * @return     true if the objects are in the same state
     */
    public boolean equals(Object otherObject)
    {
        boolean result = true;
        if (otherObject instanceof Counter)
        {
            // YOUR CODE GOES HERE
            //result = true;
			if(((Counter)otherObject).Max == Max && ((Counter)otherObject).Min == Min && ((Counter)otherObject).count == count)
				result = true;
			else
				result = false;	
        }
        
       
        return result;
    }
    
    

    /**
     * Increases the counter by one
     */
    public void increase()
    {
        // ADD CODE TO INCREASE THE VALUE OF THE COUNTER
      		count++;
      	
       // System.out.println(count);
    }
 
 
     /**
     * Decreases the counter by one
     */
    public void decrease()
    {
        // ADD CODE TO INCREASE THE VALUE OF THE COUNTER
        if(count == Min)
        { 
        	count = Max;
        	test = true;
        }
        else 
       		count = count - 1;
       
        
         	
    }
    
    /**
     * Get the value of the counter
     *
     * @return     the current value of the counter
     */
    public int value()
    {
        // CHANGE THE RETURN TO GIVE THE CURRENT VALUE OF THE COUNTER
        //return -50;
        return count;
		
    }
    
    
    /**
     * Accessor that allows the client to determine if the counter
     *             rolled over on the last count
     *
     * @return     true if the counter rolled over
     */
    public boolean rolledOver()
    {
        // CHANGE THE RETURN TO THE ROLLOVER STATUS OF THE COUNTER
         boolean roll = true;
        
        if(count > Max)
        {
        	count = Min;
        	roll = true;
        }
        else if(count == Max && test)
        {
        	roll = true;
        }
       else if(count < Min)
        {
        	count = Max;
        	roll = true;
        }
        else
        {
        	roll = false;
        }
        return roll;
    }
    
    /**
     * Override the toString method to provide a more informative
     * description of the counter
     *
     * @return     a descriptive string about the object
     */
    public String toString()
    {
        // CHANGE THE RETURN TO A DESCRIPTION OF THE COUNTER
        return "The count is at: "+count;		
    }
 
}
