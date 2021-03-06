import java.util.*;
import java.io.*;

/**
 * A class for checking to see if a sort is behaving correctly.
 *
 * @author Charles Hoot
 * @version 4.0
 */


public class CheckSort
{



    public static void main(String args[])
    {
        int arraySize;
        int trials;
        Integer data[];

        System.out.println("What size array should be used?");
        arraySize = getInt("   It should be an integer value greater than or equal to 1.");

        System.out.println("How many arrays should be used (number of trials)?");
        trials = getInt("   It should be an integer value greater than or equal to 1.");

        SortArray.setQuickSortMinimumSize(1);

        for(int i = 0; i<trials; i++)
        {
            data = generateRandomArray(arraySize);

            System.out.println("The array is: " + getString(data));
            SortArray.bitonicMergeSort(data, arraySize);

            System.out.println("The sorted array is: " + getString(data));

            if(isSorted(data))
                System.out.println("   passes -  array is sorted");
            else
                System.out.println("   failed -  array is not sorted");
        } //end for


    }

    /**
     * Check to see if an array of comparable objects is sorted.
     *
     * @param   data    An array of comparable objects.
     * @return  True if the array is sorted.
     */
    private static <T extends Comparable<? super T>>
    boolean isSorted(T data[])
    {
        boolean result = true;

        // check consecutive pairs
        for(int i = 0; i< data.length - 1; i++)
        {
            if( data[i].compareTo(data[i+1]) > 0)
            {
                result = false;  // out of order data
                break;          // no need to check any more
            }
        }

        return result;
    }




    /**
     * Generate an array of random integer values.  The values will be between 0 and size.
     *
     * @param   size    The size of the array to generate.
     * @return  The array of integers.
     */
    private static Integer[] generateRandomArray(int size)
    {
        Integer result[] = new Integer[size];
        Random generator = new Random();

        for(int i = 0; i< size; i++)
        {
            int value = generator.nextInt(size);
            result[i] = new Integer(value);
        }

        return result;
    }


    /**
     *  A displayable representation of an array of Objects where toString is
     *  applied on each object in the array
     *
     * @param   data    The array to display.
     * @return  A printable string.
     */
    private static String getString(Object [] data)
    {
        String result = new String("[ ");

        for(int i = 0; i< data.length; i++)
        {
            result = result + data[i].toString() + " ";
        }

        result = result + "]";

        return result;
    }


    /**
     * Get an integer value
     *
     * @return     An integer.
     */
    private static int getInt(String rangePrompt)
    {
        Scanner input;
        int result = 10;        //default value is 10
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
}
