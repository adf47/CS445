/**
 * A class that represents a rational number. 
 * 
 * @author Charles Hoot 
 * @version 4.0
*/

public class Rational
{
    // PUT PRIVATE DATA FIELDS HERE
    private int num;
    private int den;
    private int ans;
	private boolean multi;
    /**
     * The default constructor for objects of class Rational.  Creates the rational number 1.
     */
    public Rational()
    {       
        // ADD CODE TO THE CONSTRUCTOR
        num = 1;
        den = 1;
        ans = num/den;
    }

    /**
     * The alternate constructor for objects of class Rational.  Creates a rational number equivalent to n/d.
     * @param n The numerator of the rational number.
     * @param d The denominator of the rational number.
     */    
    public Rational(int n, int d)
    {
        // ADD CODE TO THE ALTERNATE CONSTRUCTOR
        
        	num = n;
     	 	den = d;
        
       if (den == 0)
       {
       		//den =1 ;
         throw new ZeroDenominatorException("Den is Zero");
         //den = 1;
    }

      // Make the numerator "store" the sign
      if (den < 0)
      {
         num = num * -1;
         den = den * -1;
      }
      
      else if(num == 0)
      	den = 1;
      	
		if(!multi) normalize();
    	
    }
    
    /**
     * Get the value of the Numerator
     *
     * @return     the value of the numerator
     */
    public int getNumerator()
    {
        // CHANGE THE RETURN TO SOMETHING APPROPRIATE
        return num;
    }
    
    /**
     * Get the value of the Denominator
     *
     * @return     the value of the denominator
     */
    public int getDenominator()
    {
        // CHANGE THE RETURN TO SOMETHING APPROPRIATE
        return den;
    }


    /**
     * Negate a rational number r
     * 
     * @return a new rational number that is negation of this number -r
     */    
    public Rational negate()
    {               
        // CHANGE THE RETURN TO SOMETHING APPROPRIATE
       int  tnum = num * -1;
       int tden = den;
       
       Rational temp = new Rational(tnum,tden);
    
        return temp;
    }


    /**
     * Invert a rational number r 
     * 
     * @return a new rational number that is 1/r.
     */    
    public Rational invert()
    {               
        // CHANGE THE RETURN TO SOMETHING APPROPRIATE
        int tnum = den;
        int tden = num;
        
        Rational temp = new Rational(tnum,tden);
        
        return temp;
    }





    /**
     * Add two rational numbers
     *
     * @param other the second argument of the add
     * @return a new rational number that is the sum of this and the other rational
     */    
    public Rational add(Rational other)
    {       
        // ADD NEW CODE AND CHANGE THE RETURN TO SOMETHING APPROPRIATE
       int com = den * other.getDenominator();
      int num1 = num * other.getDenominator();
      int num2 = other.getNumerator() * den;
      int sum = num1 + num2;
        
        Rational temp = new Rational(sum,com);
        return temp;
    }
    
     /**
     * Subtract a rational number t from this one r
     *
     * @param other the second argument of subtract
     * @return a new rational number that is r-t
     */    
    public Rational subtract(Rational other)
    {               
        // CHANGE THE RETURN TO SOMETHING APPROPRIATE
        int com = den * other.getDenominator();
      	int num1 = num * other.getDenominator();
      	int num2 = other.getNumerator() * den;
      	int sum = num1 - num2;
        
        Rational temp = new Rational(sum,com);
        return temp;
    }

    /**
     * Multiply two rational numbers
     *
     * @param other the second argument of multiply
     * @return a new rational number that is the sum of this object and the other rational.
     */    
    public Rational multiply(Rational other)
    {       
        // ADD NEW CODE AND CHANGE THE RETURN TO SOMETHING APPROPRIATE
          
      	int num2 = den * other.getDenominator();
      	int num1 = other.getNumerator() * num;
      
         multi = true;
        Rational temp = new Rational(num1,num2);
        // multi = false;
        return temp;
    }
        
 
     /**
     * Divide this rational number r by another one t
     *
     * @param other the second argument of divide
     * @return a new rational number that is r/t
     */    
    public Rational divide(Rational other)
    {               
        // CHANGE THE RETURN TO SOMETHING APPROPRIATE
        Rational temp = other.invert();
        
        int top = temp.num * this.num;
        int bot = temp.den * this.den;
        
        //multi = true;
        Rational temps = new Rational(top,bot);
        
        return temps;
    }
     
 
 
 /**
     * Put the rational number in normal form where the numerator
     * and the denominator share no common factors.  Guarantee that only the numerator
     * is negative.
     *
     */
    private void normalize()
    {
        // ADD CODE TO NORMALIZE THE RATIONAL NUMBER
        if (num != 0)
      {
         int common = gcd (Math.abs(num), den);

         num = num / common;
         den = den / common;
      }
    }
    
    /**
     * Recursively compute the greatest common divisor of two positive integers
     *
     * @param a the first argument of gcd
     * @param b the second argument of gcd
     * @return the gcd of the two arguments
     */
    private int gcd(int a, int b)
    {
        int result = 0;
        if(a<b)
            result = gcd(b,a);
        else if(b==0)
            result = a;
        else
        {
            int remainder = a % b;
            result = gcd(b, remainder);
        }
        return result;
    }
   
    
    
}
