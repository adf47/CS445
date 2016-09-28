//Antonino Febbraro
//CS 445
//Assignment One 

import java.util.*;

public class Set<T> implements SetInterface<T>
{
	private int maxSize;
	private int numberOfElements;
	private T[] set;
	
	public Set(int capacity) //Default constructor
	{
		maxSize = capacity;
		set = (T[])new Object [maxSize];
	}
	
	//Returns the number of elements in the set
	public int getCurrentSize()
	{
		return numberOfElements;
	}
	
	//checks to see if the set is empty
	public boolean isEmpty()
	{
		return numberOfElements == 0;
	}
	
	//ADD METHOD
	public boolean add(T newEntry)
	{
		boolean result = false;
		
		//Throwing the specified exception
		if(newEntry == null)
		{
			throw new java.lang.IllegalArgumentException();
		}	
		else
		{
			//NOTE IN EXTRA CREDIT I RESIZE THE ARRAY
			if(set.length == maxSize && !contains(newEntry))
			{
				//Resize the array here 
				set = Arrays.copyOf(set,(set.length*2));
				set[numberOfElements] = newEntry;
				result = true;
				numberOfElements++;
			}
			else if(!contains(newEntry))
			{
				set[numberOfElements] = newEntry;
				result = true;
				numberOfElements++;
			}
			else
			{
				result = false;
			}
		}
		
		return result;
	}
	
	//Removes a specific item
	public boolean remove(T entry)
	{
		boolean found = false;
		
		if(entry == null)
		{
			throw new java.lang.IllegalArgumentException();
		}
		else
		{	
			//Get the index of the item to be removed
			
			int where = -1;
        	boolean stillLooking = true;
        	int index = 0;
       		while (stillLooking && (index < numberOfElements)) 
       		{
            	if (entry.equals(set[index])) 
            	{
                	stillLooking = false;
               		where = index;
               		break;
           		 } 
            	index++;
        	} 
       	
       		found = index != -1;
       	
       		if(found)
       		{
       			for(int x = index;x<numberOfElements-1;x++)
       			{
       				set[x] = set[x+1];
       			}
       			set[numberOfElements-1] = null;
       			numberOfElements--;
       		}
       	}
       	
       	return found;
	}
	
	//Removes the last object
	public T remove()
	{
		T result  = null;
		
		if(!isEmpty())
		{
			result = set[numberOfElements];
			numberOfElements--;
			set[numberOfElements +1] = null;
		}
		
		return result;
	}
 
    
    //Checks if array contains an item
     public boolean contains(T entry) 
     {
     	boolean result = false;
     	
     	if(entry == null)
     		throw new java.lang.IllegalArgumentException();
     	else
     	{	
     		int where = -1;
        	boolean stillLooking = true;
        	int index = 0;
       		while (stillLooking && (index < numberOfElements)) 
       		{
            	if (entry.equals(set[index])) 
            	{
                	stillLooking = false;
               		where = index;
           		 } 
            	index++;
        	} 
        	result = where >= 0;
        }
        
        return result; 
     } 
     
     //CLEARS the whole entire set
     public void clear()
     {
     	while(!isEmpty())
     		remove();
     }
     
     //Returns an Array of the Set
    public T[] toArray() 
    {
    	
    	@SuppressWarnings("Unchecked")
        T[] result = (T[]) new Object[numberOfElements];
        
        for (int index = 0; index < numberOfElements; index++) 
        {
            result[index] = set[index];
        } 
        
        return result;
    } // end toArray
}