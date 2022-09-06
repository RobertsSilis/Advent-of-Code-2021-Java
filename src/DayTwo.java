import java.io.File; //Allows managing files
import java.io.FileNotFoundException; //Catches exception
import java.util.Scanner; //Allows reading from a file
import java.util.ArrayList; //Allows using ArrayList type


public class DayTwo 
{
	static void dayTwo()
	{
		try
		{
			File directions = new File("InputDayTwo.txt"); 
			Scanner myReader = new Scanner(directions);
			ArrayList<String> input = new ArrayList<String>();  //Arraylist used as an array of unknown length.
			while(myReader.hasNext())
			{
				input.add(myReader.next());  //Writes the input file into arraylist "input".
			}
			System.out.println(taskOne(input)); //Prints out the solution to day 1 task 1 problem.
			System.out.println(taskTwo(input)); //Prints out the solution to day 1 task 2 problem.
			myReader.close();
			input.clear();
		}
		catch (FileNotFoundException e) 
		{
		     System.out.println("An error occurred.");
		     e.printStackTrace();
		}
	}
	
	/*
	 * Now, you need to figure out how to pilot this thing.
	 * It seems like the submarine can take a series of commands like forward 1, down 2, or up 3:
	 * 
	 * forward X increases the horizontal position by X units.
	 * down X increases the depth by X units.
	 * up X decreases the depth by X units.
	 * 
	 * The submarine seems to already have a planned course (your puzzle input). 
	 * You should probably figure out where it's going.
	 * 
	 * Your horizontal position and depth both start at 0.
	 * 
	 * Calculate the horizontal position and depth you would have after following the planned course. 
	 * What do you get if you multiply your final horizontal position by your final depth?
	 */
	
	static int taskOne(ArrayList<String> input)
	{
		int x = 0; // Horizontal location
		int y = 0; // Vertical location
		for(int i = 0; i < input.size()-1; i=i+2)
		{
			String direction = input.get(i);
			switch (direction)
			{
			case "forward":
				x = x + Integer.parseInt(input.get(i+1));
				break;
			case "up":
				y = y - Integer.parseInt(input.get(i+1));
				break;
			case "down":
				y = y + Integer.parseInt(input.get(i+1));
				break;
			}	
		}
		return x * y;
	}
	
	/*
	 * Based on your calculations, the planned course doesn't seem to make any sense. 
	 * You find the submarine manual and discover that the process is actually slightly more complicated.
	 * 
	 * In addition to horizontal position and depth, you'll also need to track a third value, aim, which also starts at 0. 
	 * The commands also mean something entirely different than you first thought:
	 * 
	 * down X increases your aim by X units.
	 * up X decreases your aim by X units.
	 * forward X does two things:
	 * It increases your horizontal position by X units.
	 * It increases your depth by your aim multiplied by X.
	 * 
	 * Again note that since you're on a submarine, down and up do the opposite of what you might expect: 
	 * "down" means aiming in the positive direction.
	 * 
	 * Using this new interpretation of the commands, 
	 * calculate the horizontal position and depth you would have after following the planned course. 
	 * What do you get if you multiply your final horizontal position by your final depth?
	 */

	
	static int taskTwo(ArrayList<String> input)
	{
		int x = 0; //Horizontal location
		int y = 0; //Vertical location
		int aim = 0; //Where the submarine's nose is aiming
		for(int i = 0; i < input.size()-1; i=i+2) //i = i+2 because every 1st element is direction and every 2nd is value
		{
			String direction = input.get(i);
			switch (direction)
			{
			case "forward":
				x = x + Integer.parseInt(input.get(i+1));
				y = y + aim * Integer.parseInt(input.get(i+1));
				break;
			case "up":
				aim = aim - Integer.parseInt(input.get(i+1));
				break;
			case "down":
				aim = aim + Integer.parseInt(input.get(i+1));
				break;
			}	
		}
		return x * y;
	}
}
