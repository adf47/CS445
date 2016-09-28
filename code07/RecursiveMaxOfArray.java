
/**
 * Use a double recursion to compute the maximum of an array of values.
 *
 * @author Charles Hoot
 * @version 4.0
 */
public class RecursiveMaxOfArray
{


    /**
     * Compute the maximum of a range of values in an array recursively.
     *
     * @param data   An array of integers.
     * @param from  The low index for searching for the maximum.
     * @param to    The high index for searching for the maximum.
     *
     * preconditions: from <= to, from >=0, to<length, length>0
     *
     * @return     The maximum value in the array.
     */

    public  int max(int data[], int from, int to)
    {
        int result = 0;

        if(data == null)
          throw new BadArgumentsForMaxException("Not good Input 1");
        if(data.length <= 0)
          throw new BadArgumentsForMaxException("Not good Input 2");
        if(from < 0)
          throw new BadArgumentsForMaxException("Not good Input 3");
        if(to > (data.length-1))
          throw new BadArgumentsForMaxException("Not good Input 4");
        if(from > to)
          throw new BadArgumentsForMaxException("Not good Input 5");

        // ADD YOUR CODE HERE
        int max = 0;
        if (from >= to)
        {
            result = data[from];
        }
        else if (data[from] < data[to])
        {
            return max(data, from + 1, to);
        }
      else
      {
        result = data[from];
      }

        return result;
    }


}
