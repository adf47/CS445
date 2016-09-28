//Antonino Febbraro
//CS 445 - Data Structures
//Assignment One - Main 

import java.util.*;
import java.io.*;
import java.text.*;
import javax.swing.*;

public class SocialClient
{
	
	public static void main(String [] args)  
	{
		//READ IN THE FILE FOR THE PROFILES TO BE LOADED INTO SET 
			String file = "Profiles.txt";
			BufferedReader br = null;
			Set<Profile> profileSet = new Set(25);
			ProfileInterface Interface = new Profile();
			ArrayList<String> followers = new ArrayList();
			
			
			try
			{
				File Files = new File(file);
		
				
				
					br = new BufferedReader(new FileReader(Files));
			
 					String line;
 				
 					while ((line = br.readLine()) != null) { //READING IN THE FILE
 				
 						if(line.contains("$"))
 						{
 							line = line.substring(0, line.length() - 1);//to get rid of $ at the end of string in file
							String [] names = line.split("-");//splitting the name from "About Me"
							
							
							Profile profile = new Profile(names[0],names[1]);
							
							followers.add(names[2]);
							
							profileSet.add(profile); //adding profiles to the Set 
							 
 						}
 					
 					}
 				
 					//If all the file stuff works okay, start the program
 					//adding the followers to each profile
 					Object [] temp = profileSet.toArray();
 					Profile [] pro = new Profile[temp.length];
 					//loop to make the profile array
 					for(int f = 0;f<pro.length;f++)
 					{
 						pro[f] = (Profile)temp[f];
 					}
 					
 					for(int x = 0;x<followers.size();x++)
 					{
 						//pro[x] = (Profile)temp[x];
 						String [] fols = followers.get(x).split(",");
 						int z = 0;
 						while(z < fols.length)
 						{
 							for(int i = 0;i<pro.length;i++)
 							{
 								if(pro[i].getName().equals(fols[z]))
 								{
 									pro[x].follow(pro[i]);
 								}
 							}
 							z++;
 						}
 					}
 					
 					StartProgram(profileSet);
 				
 			} 
 			catch(FileNotFoundException e)
			{
				System.out.println("Sorry! The file you are looking for was not found.");
			}
 			catch(IOException er)
 			{
 				System.out.println("Sorry file could not be loaded");
 			}
 			

 			
	}
	
	
	//THE PROGRAM BEGINS HERE!!--- Basically the main of the program
	public static void StartProgram(Set<Profile> profilesSet)
	{
		System.out.println("WELCOME TO SOCAIL CLIENT!");
		
		boolean start = true;
		
		while(start)
		{
			System.out.println();//Just for Spacing
			try
			{
				System.out.println("Enter in the corresponding number to do something: ");
				System.out.println("1.) List Profiles");
				System.out.println("2.) Create Profile");
				System.out.println("3.) Show Profile");
				System.out.println("4.) Edit Profile");
				System.out.println("5.) Follow a Profile");
				System.out.println("6.) Unfollow a Profile");
				System.out.println("7.) Suggest a Follower");
				System.out.println("8.) Quit the program");
				
				Scanner in = new Scanner(System.in);
				int ans = in.nextInt();
			
				if(ans == 1)
					ListProfiles(profilesSet);
				if(ans == 2)	
					CreateProfile(profilesSet);
				if(ans == 3)	
					ShowProfile(profilesSet);
				if(ans == 4)	
					EditProfile(profilesSet);
				if(ans == 5)
					FollowProfile(profilesSet);	
				if(ans == 6)
					UnfollowProfile(profilesSet);
				if(ans == 7)
					SuggestFollower(profilesSet);		
				if(ans == 8)
				{
					Save(profilesSet);
					start = false;	
				}	
				if(ans < 1 || ans > 8)	
					System.out.println("Invalid number. Enter again");
					
			}
			catch(Exception e)
			{
				System.out.println("Invalid Input. Enter in again.");
			}
		}
		
	}
	
	//List Profiles Method
	public static void ListProfiles(Set<Profile> profilesSet)
	{
		
		//Making an array of profiles so that we can manipulate the data
		//Have to do this because of Java generic array 
		Object [] temp = profilesSet.toArray(); 
		Profile [] pro = new Profile[profilesSet.getCurrentSize()];
		
				
		for(int x = 0;x<pro.length;x++)
		{
			pro[x] = (Profile)temp[x];
		}
	
		System.out.println("Here is a list of all the profiles: ");
		
		for(int x = 0;x<pro.length;x++)
		{
			System.out.println(pro[x].getName());
		}
		
	}
	
	//Method for creating a Profile
	public static void CreateProfile(Set<Profile> profilesSet)
	{
		System.out.println("Lets create a new profile!");
		System.out.println();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter in the Profile name:");
		String name = in.nextLine();
		System.out.println("Enter in the About Me Blurb:");
		String about = in.nextLine();
		Profile pro = new Profile(name,about);
		System.out.println("Thank you for creating a profile!");
		
		profilesSet.add(pro);
	}
	
	//Method for showing a profile and at least 4 followers
	public static void ShowProfile(Set<Profile> profilesSet)
	{
		//Asking user what profile to show
		Scanner in = new Scanner(System.in);
		System.out.println("Enter in the profile name you would like to be shown!");
		String name = in.nextLine();
		
		//Making an array of profiles so that we can manipulate the data
		//Have to do this because of Java generic array 
		Object [] temp = profilesSet.toArray(); 
		Profile [] pro = new Profile[profilesSet.getCurrentSize()];
		
		for(int x = 0;x<pro.length;x++)
		{
			pro[x] = (Profile)temp[x];
		}
		
		//Searching Profiles 
		boolean found = false;
		for(int y = 0;y<pro.length;y++)
		{
				if(pro[y].getName().equals(name))
				{
					System.out.println("Profile: "+name);
					System.out.println("About Me: "+pro[y].getAbout());
					
					//Setting up number of followers to show(Up to four followers)
					ProfileInterface [] test;
					int size = pro[y].getNumOfFollowers();
					
					if(size <= 4)
						test = pro[y].following(size);
					else
						test = pro[y].following(4);	
					
					if(size > 0)
					{
						System.out.println("Here is a list of some of their followers: ");
						for(int f = 0;f<test.length;f++)
							System.out.println(test[f]);
					}
					else
						System.out.println("This user does not have any followers yet.");
						
					found = true;	
				}
		}
		
		if(!found)
			System.out.println("Sorry the profile you are looking for does not exist.");
		
	}
	
	
	//Method for editing a profile
	public static void EditProfile(Set<Profile> profilesSet)
	{
		//Seeing what profile to edit
		Scanner in = new Scanner(System.in);
		System.out.println("Enter in the profile name you would like to edit!");
		String name = in.nextLine();
		
		//Making an array of profiles so that we can manipulate the data
		//Have to do this because of Java generic array 
		Object [] temp = profilesSet.toArray(); 
		Profile [] pro = new Profile[profilesSet.getCurrentSize()];
		
		for(int x = 0;x<pro.length;x++)
		{
			pro[x] = (Profile)temp[x];
		}
		
		//Searching Profiles 
		boolean found = false;
		for(int y = 0;y<pro.length;y++)
		{
				if(pro[y].getName().equals(name))
				{
					//Have user change the name here
					System.out.println("Enter in the new (Edited) name:");
					String names = in.nextLine();
					System.out.println("Enter in the new (Edited) About Me Blurb:");
					String about = in.nextLine();
					pro[y].setName(names);
					pro[y].setAbout(about);
					System.out.println(name+"'s profile has been updated!");
					found = true;
					
				}
		}
		
		if(!found)
			System.out.println("Sorry the profile you are looking for does not exist.");
	}
	
	
	//Method for Making a profile follow another
	public static void FollowProfile(Set<Profile> profilesSet)
	{
		
		//Seeing what profile to edit
		Scanner in = new Scanner(System.in);
		System.out.println("Enter in the profile name you would like to HAVE follow someone:");
		String name1 = in.nextLine();
		System.out.println("Enter in the profile name that is to be followed:");
		String name2 = in.nextLine();
	
		//Making an array of profiles so that we can manipulate the data
		//Have to do this because of Java generic array 
		Object [] temp = profilesSet.toArray(); 
		Profile [] pro = new Profile[profilesSet.getCurrentSize()];
		
		for(int x = 0;x<pro.length;x++)
		{
			pro[x] = (Profile)temp[x];
		}
		
		//Searching Profiles 
		boolean found = false;
		boolean found2 = false;
		boolean followed = false;
		for(int y = 0;y<pro.length;y++)
		{
				if(pro[y].getName().equals(name1))
				{
					found = true;
					
					//Searching for second person
					for(int z = 0;z<pro.length;z++)
					{
						if(pro[z].getName().equals(name2))
						{
							found2 = true;
							
							if(!pro[y].isFollowing(pro[z]))
							{
								pro[y].follow(pro[z]);
								followed = true;
							}
							else
							{
								System.out.println(pro[y]+" is already following "+pro[z]);
							}
						}
					}
					
				}
		}
		
		if(!found)
			System.out.println("Sorry the profile you are looking for does not exist.");
		else if(!found2)
			System.out.println("Sorry the second profile name entered does not exist.");	
		else if(followed)
			System.out.println(name1+" is now following "+name2);	
	}
	
	//Method for Unfollowing a profile
	public static void UnfollowProfile(Set<Profile> profilesSet)
	{
		
		//Seeing what profile to edit
		Scanner in = new Scanner(System.in);
		System.out.println("Enter in the profile name you would like to HAVE unfollow someone:");
		String name1 = in.nextLine();
		System.out.println("Enter in the profile name that is to be unfollowed:");
		String name2 = in.nextLine();
	
		//Making an array of profiles so that we can manipulate the data
		//Have to do this because of Java generic array 
		Object [] temp = profilesSet.toArray(); 
		Profile [] pro = new Profile[profilesSet.getCurrentSize()];
		
		for(int x = 0;x<pro.length;x++)
		{
			pro[x] = (Profile)temp[x];
		}
		
		//Searching Profiles 
		boolean found = false;
		boolean found2 = false;
		boolean unfollowed = false;
		for(int y = 0;y<pro.length;y++)
		{
				if(pro[y].getName().equals(name1))
				{
					found = true;
					
					ProfileInterface [] pro2 = pro[y].following(pro[y].getNumOfFollowers());
					
					//Searching for second person
					for(int z = 0;z<pro2.length;z++)
					{
						if(pro2[z].getName().equals(name2))
						{
							found2 = true;
					 
							//ProfileInterface fol = new Profile();
							//fol.setName(pro[z].getName());
							
							if(pro[y].isFollowing(pro2[z]))
							{
								pro[y].unfollow(pro2[z]);
								unfollowed = true;
							}
							else
							{
								System.out.println(name1+" does not follow "+name2+". "+name2+" cannot be unfollowed.");
							}	
						}
					}
					
				}
		}
		
		if(!found)
			System.out.println("Sorry the profile you are looking for does not exist.");
		else if(!found2)
			System.out.println("Sorry the second profile name entered does not exist.");	
		else if(unfollowed)
			System.out.println(name1+" unfollowed "+name2);	
	}
	
	//Method for suggesting a follower
	public static void SuggestFollower(Set<Profile> profilesSet)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter in the profile name to get a follow suggestion:");
		String name = in.nextLine();
		boolean found = false;
		
		Object [] temp = profilesSet.toArray(); 
		Profile [] pro = new Profile[profilesSet.getCurrentSize()];
		Profile profile1 = new Profile();
		Profile profile2 = new Profile();
		
		for(int x = 0;x<pro.length;x++)
		{
			pro[x] = (Profile)temp[x];
			if(pro[x].getName().equals(name))
			{
				profile1 = pro[x];
				found = true;
			}
		}
		
		if(found)
		{
			ProfileInterface Interface = profile1.recommend();
			profile2 = (Profile)Interface;
			
			boolean enter = true;
			
			while(enter)
			{
				System.out.println("Here is the suggestion: "+profile2+" would you like to follow them? (Y/N)");
				String ans = in.nextLine();
				
				if(ans.equalsIgnoreCase("y"))
				{
					profile1.follow(profile2);
					enter = false;
				}
				else if(ans.equalsIgnoreCase("n"))
					enter = false;
			}
		}
		else
			System.out.println("Sorry, the profile you typed in does not exist.");
		
	}
	
	//Method for saving data back to file
	public static void Save(Set<Profile> profilesSet)
	{
	
			//Making an array of profiles so that we can manipulate the data
		//Have to do this because of Java generic array 
		Object [] temp = profilesSet.toArray(); 
		Profile [] pro = new Profile[profilesSet.getCurrentSize()];
		int j = 0;
		
		for(int x = 0;x<pro.length;x++)
		{
			pro[x] = (Profile)temp[x];
		}
		
		//saving data back to file
		//WRITE FILE TO UPDATE THE FILE, USE A WHILE LOOP AGAIN
		try
		{
			PrintWriter writer = new PrintWriter("Profiles.txt", "UTF-8");
			j=0;
			
			while(j < pro.length){
				
				StringBuilder b = new StringBuilder();
				b.append(pro[j].getName()+"-"+pro[j].getAbout()+"-");
				ProfileInterface [] fol = pro[j].following(pro[j].getNumOfFollowers());
				
				for(int i = 0;i < fol.length;i++)
				{
					if(i == fol.length-1)
						b.append(fol[i]+"$");
					else
						b.append(fol[i]+",");	
				}
				
				writer.println(b.toString());
				
				j++;
			}
			
			writer.close();
		}
		catch(Exception e)
		{
			System.out.println("Sorry, file was not found");
		}
		
	}
	
}