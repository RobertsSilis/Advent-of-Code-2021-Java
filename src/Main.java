import java.io.File; //Allows managing files
import java.io.FileNotFoundException; //Catches exception
import java.util.Scanner; //Allows reading from a file
import java.util.ArrayList; //Allows using ArrayList type

/*
 * You're minding your own business on a ship at sea when the overboard alarm goes off! 
 * You rush to see if you can help. Apparently, one of the Elves tripped and accidentally sent the sleigh keys flying into the ocean!
 * 
 * Before you know it, you're inside a submarine the Elves keep ready for situations like this. 
 * It's covered in Christmas lights (because of course it is), and it even has an experimental antenna that should be able to track the keys 
 * if you can boost its signal strength high enough; 
 * there's a little meter that indicates the antenna's signal strength by displaying 0-50 stars.
 * 
 * Your instincts tell you that in order to save Christmas, you'll need to get all fifty stars by December 25th.
 * 
 * Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; 
 * the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
 */

public class Main 
{
	public static void main(String[] args) 
	{
		try 
		{
			File numbers = new File("src\\Input.txt");  
			Scanner myReader = new Scanner(numbers);
			ArrayList<Integer> input = new ArrayList<Integer>();  
			while(myReader.hasNextLine())
			{
				input.add(Integer.parseInt(myReader.nextLine()));  //Writes the input file into variable "input"
			}
			System.out.println(DayOne.taskOne(input)); //Prints out the solution to day 1 task 1 problem.
			System.out.println(DayOne.taskTwo(input)); //Prints out the solution to day 1 task 2 problem.
			myReader.close();
			input.clear();
		} 
		catch (FileNotFoundException e) 
		{
		     System.out.println("An error occurred.");
		     e.printStackTrace();
		}
	}
}