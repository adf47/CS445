//Antonino Febbraro
//CS 445
//Assignment One 

import java.util.*;

public class Profile implements ProfileInterface
{
	private String name;
	private String about;
	private Set set;
	private ProfileInterface[] followed;
	private int size;
	
	public Profile()//Default constructor
	{
		name = "";
		about = "";
		
		set = new Set(25);//set to this size but Set class will resize the array
	}
	
	public Profile(String n, String a)
	{
		name = n;
		about = a;
		
		set = new Set(25);
	}
	
	//method to set the name
	 public void setName(String newName) 
	 {
	 	if(newName == null)
	 	{
	 		throw new java.lang.IllegalArgumentException();
	 	}
	 	else
	 	{
	 		name = newName;
	 	}
	 			
	 }
	 
	 //Method to get the name
	 public String getName()
	 {
	 	return name;
	 }
	 
	 //method to set "about me" blurb
	 public void setAbout(String newAbout)
	 {
	 	if(newAbout == null)
	 	{
	 		throw new java.lang.IllegalArgumentException();
	 	}
	 	else
	 	{
	 		about = newAbout;
	 	}
	 }
	 
	 //Method to get the about string
	 public String getAbout()
	 {
	 	return about;
	 }
	 
	 //METHOD TO FOLLOW SOMEONE
	  public boolean follow(ProfileInterface other)
	  {
	  	 boolean result = set.add(other);
	  	 size++;
	  	 return result;
	  }
	  
	  //UNFOLLOW PERSON METHOD 
	  public boolean unfollow(ProfileInterface other)
	  {
	  	boolean result = set.remove(other);
	  	size--;
	  	return result;
	  }
	  
	  //METHOD FOR GETTING A LIST OF FOLLOWERS
	   public ProfileInterface[] following(int howMany) 
	   {
	   		
	   		Object[] temp = new Object[set.getCurrentSize()];
	   		temp = set.toArray();
	   		
	   	 	followed = new ProfileInterface[howMany];
	   	 	
	   	 	for(int x =0; x < followed.length;x++)
	   	 	{
	   	 		followed[x] = (ProfileInterface)temp[x];
	   	 	}
	   	 	
	   	 	return followed;
	   }
	   
	   //Method for getting the number of followers (This is a helper method that I created)
	   public int getNumOfFollowers()
	   {
	   		return set.getCurrentSize();
	   }
	   
	   //FOR THE RECCOMEND METHOD
	   public ProfileInterface recommend()
	   {
	
	   		ProfileInterface suggestion = null;
	   		
	   		//Getting random number
	   		Object [] pro = set.toArray();
	   		Random r = new Random();
			int ran = r.nextInt(size)+1;
			Profile profile1 = (Profile)pro[ran-1];//the first profile
			
			//Getting the second aka the suggestion
			boolean samePerson = true;
			Profile profile2 = new Profile();
			while(samePerson)
			{
				int ran2 = r.nextInt(profile1.getNumOfFollowers())+1;
				Object [] temp = profile1.following(profile1.getNumOfFollowers());
				profile2 = (Profile)temp[ran2-1];
				
				if(profile2.getName().equals(name))
					samePerson = true;
				else
					samePerson = false;	
			}
			
			suggestion = profile2;
	   		
	   		return suggestion;
	   } 
	   
	   
	   //Method to see if a person is following that person (Helper method that I created)
	   public boolean isFollowing(ProfileInterface pro)
	   {
	   		String name = pro.getName();
	   		Object [] temp = set.toArray();
	   		boolean result = false;
	   		
	   		Profile [] pros = new Profile[set.getCurrentSize()];
		
			for(int x = 0;x<pros.length;x++)
			{
				pros[x] = (Profile)temp[x];
				
				if(pros[x].getName().equals(name))
					result = true;
			}
	   		
	   		return result;
	   }
	 
	   
	   //toString Method (Name only)
	   public String toString()
	   {	
	   		return name;
	   } 
	   
	   //toString Method (Name and About me)
	   public String toProfile()
	   {
	   		StringBuilder s = new StringBuilder();
	   		
	   		if(about!="")
	   		{
	   			s.append(name+"\n");
	   			s.append(about);
	   		}
	   		else
	   			s.append(name);
	   		
	   		return s.toString();
	   } 
	  
}