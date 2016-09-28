//Antonino Febbraro
//CS 445
//Assignment 2

import java.util.*;
import java.util.Deque;
import java.io.*;

public class Assig2
{
	
	public static void main (String [] args) throws IOException
	{
		boolean start = true;
		Scanner in = new Scanner(System.in);
		String input = "";
		
		
		while(start)
		{
			try 
			{
				System.out.println();//for spacing
				System.out.println("Please enter in a math equation or enter in q to quit: ");
				input = in.nextLine();
				
				//checking to see to quit program or still run
				if(input.equalsIgnoreCase("q"))
				{
					start = false;
					break;
				}
				else
				{
					//To check for equation to be legal/balanced
					if(input.charAt(input.length()-1)=='*'||input.charAt(input.length()-1)=='/'||input.charAt(input.length()-1)=='^'||input.charAt(input.length()-1)=='+'||input.charAt(input.length()-1)=='-')
					{
						System.out.println("Equation in not balanced. Please enter again.");
					}
					else if(input.contains("++")||input.contains("++")||input.contains("//")||input.contains("**")||input.contains("--")||input.contains("**")||input.contains("^^"))
					{
						System.out.println("Multiple operands in a row. Please eneter again.");
					}
					else
					{
						tokenize(input);
					}
				}
			
			}
			catch(Exception e)
			{
				System.out.println("Error has occurred. Invalid Input, please enter again.");
			}
		}
	}
	
	//Method to get precedence of Operator
	public static int precedence(char x)
	{
		int result = -1;
		
		if(x == '^')
			result = 4;
		if(x == '*')
			result = 3;
		if(x == '/')
			result = 2;
		if(x == '+')
			result = 1;
		if(x == '-')
			result = 1;		
			
		return result;		
	}
	
	//method to parse the string
	public static void tokenize(String s) throws IOException 
	{
	
		VectorStack <Double> ValueStack = new VectorStack(25);
		VectorStack <Character> OperatorStack = new VectorStack(25);
		
		boolean mat = true;
		int match = 0;
		int x =1;
		
		try
		{
			StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(s));
  			tokenizer.ordinaryChar('-');  // Don't parse minus as part of numbers.
  			tokenizer.ordinaryChar('/');
  			
  			int toke = 0;
  			while ((toke = tokenizer.nextToken()) != StreamTokenizer.TT_EOF && x==1) 
  			{
  				
   				 switch(tokenizer.ttype) 
   				{	
   					//for adding to the value stack
      				case StreamTokenizer.TT_NUMBER:
       				 	ValueStack.push(tokenizer.nval);
       				 	System.out.println();//for spacing
       				 	System.out.println("Processing token: "+tokenizer.nval);
       				 	//System.out.println(ValueStack.pop());
       				 	break;
        			//for exponentials 
        			case '^':
        				OperatorStack.push((char)toke);
        				System.out.println("Processing token: "+(char)toke);	
        				break;
        			//for adding to the operator stack	
      				case '+': case '-': case '*': case'/': 
      					char tempOp = (char)toke; 
      					System.out.println();//for spacing
      					System.out.println("Processing token: "+tempOp);
      					while(!OperatorStack.isEmpty() && precedence(tempOp) <= precedence(OperatorStack.peek()))//check for precedence here
      					{
      						if(!mat)
      							break;
      							
      						boolean check = false;
      						
      						char top = OperatorStack.pop();
      						
      						if(ValueStack.isEmpty())
      							break;
      							
      						double opOne = ValueStack.pop();
      						
      						if(ValueStack.isEmpty())
      						{
      							ValueStack.push(opOne);
      							break;
      						}
      							
      						double opTwo =ValueStack.pop();
      						
      						//System.out.println("Doing math...");
      						double result = 0;
      						
      						if(precedence(top)==3)
      						{
      							result = opOne * opTwo;
      							check = true;
      						}
      						if(precedence(top)==2)
      						{
      							result = opOne/opTwo;
      							check = true;
      						}
      						if(precedence(top)==1  && top == '+')
      						{
      							result = opOne + opTwo;
      							check = true;
      						}
      						if(precedence(top)==1  && top == '-')
      						{
      							result = opTwo - opOne;	
      							check = true;
      						}	
      						if(precedence(top)==4)
      						{
      							result = Math.pow(opOne,opTwo);
      							check = true;
      						}
      								
      						
      						if(check)
      						{
      							System.out.println("Pushing "+result+" onto the value stack.");
      							ValueStack.push(result);
      						}
      					}
        				OperatorStack.push((char)toke);
        				break;
        				
        			case '(':
        				match++;
        				//mat = false;
        				OperatorStack.push((char)toke);
        				System.out.println();//for spacing
        				System.out.println("Processing token: "+(char)toke);
        				break;
        			case ')':
        				match++;
        				mat = true;
        				//OperatorStack.push((char)toke);
        				if(OperatorStack.isEmpty())
        				{
        					break;
        				}
        				char top = OperatorStack.pop(); 	
        				//char top = ')';
        				System.out.println();//for spacing
        				System.out.println("Processing token: "+(char)toke);
        				while(top != '(')
        				{
        					//if(!mat)
      							//break;
      							
      						boolean check = false;
      						//char top = 'o';
      						//if(OperatorStack.peek()!='(')
      						//top = OperatorStack.pop();
      						System.out.println("TOP is: "+top);
      						System.out.println(ValueStack.toString());
      						System.out.println(OperatorStack.toString());
      						if(ValueStack.isEmpty())
      							break;
      							
      						double opTwo = ValueStack.pop();
      						
      						if(ValueStack.isEmpty())
      						{
      							ValueStack.push(opTwo);
      							break;
      						}
      							
      						double opOne =ValueStack.pop();
      						
      						//System.out.println("Doing math...");
      						double result = 0;
      						
      						if(precedence(top)==3)
      						{
      							result = opOne * opTwo;
      							check = true;
      						}
      						else if(precedence(top)==2)
      						{
      							result = opOne/opTwo;
      							check = true;
      						}
      						else if(precedence(top)==1 && top=='+')
      						{
      							result = opOne + opTwo;
      							check = true;
      						}
      						else if(precedence(top)==1 && top=='-')
      						{
      							result = opOne - opTwo;	
      							check = true;
      						}	
      						else if(precedence(top)==4)
      						{
      							result = Math.pow(opOne,opTwo);
      							check = true;
      						}
      								
      						
      						if(check)
      						{
      							System.out.println("Pushing "+result+" onto the value stack.");
      							ValueStack.push(result);
      						}
      						else
      						{
      								
      						}
      						
      						top = OperatorStack.pop();	
      						
        				}	
        				break;
        			
        			default: 
        			System.out.println("Invalid syntax. Please enter again.");
        			x=0;
        			break;
    			}
    			
    			System.out.println();//for spacing
    			System.out.println("Operand Stack: ");
    			System.out.println(ValueStack.toString());
    			System.out.println();
				System.out.println("Operator Stack: ");
    			System.out.println(OperatorStack.toString());
  			}
  			
  			//finishing the math
  			while(!OperatorStack.isEmpty())
  			{
  				char top = OperatorStack.pop();
  				double opTwo = ValueStack.pop();
  				
  				//if(ValueStack.isEmpty())
  				//{
  				//		ValueStack.push(opTwo);
        		//		break;
        		//}
        				
        		double opOne = ValueStack.pop();
        		double result = 0;
        		
        		if(precedence(top)==3)
      				result = opOne * opTwo;
      			if(precedence(top)==2)
      				result = opOne/opTwo;
      			if(precedence(top)==1  && top == '+')
      				result = opOne + opTwo;
      		    if(precedence(top)==1  && top == '-')
      				result = opOne - opTwo;
      			if(precedence(top)==4)
      				result = Math.pow(opOne,opTwo);	
      						
      			ValueStack.push(result);
  			}
  			
  			if((match%2) == 0 && x==1) //to check for balanced equation before printing answer
  			{
  				System.out.println();//for spacing
  				System.out.println("The answer is: "+ ValueStack.peek());
  			}
  			else
  			{
  				System.out.println("Unbalanced equation. Please enter in again.");
  			}
  		}
  		catch(Exception e)
  		{
  			//print error message
  			System.out.println("Invalid Syntax, unbalanced syntax. Please enter in your equation again.");
  		    //System.out.println(e.toString()); //for debugging
  		}
  
	}
	
}