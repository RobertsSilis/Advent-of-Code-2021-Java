import java.util.ArrayList; //Allows using ArrayList type


public class DayOne 
{
	/*
	 * As the submarine drops below the surface of the ocean, it automatically performs a sonar sweep of the nearby sea floor. 
	 * On a small screen, the sonar sweep report (your puzzle input) appears: 
	 * each line is a measurement of the sea floor depth as the sweep looks further and further away from the submarine.
	 * The first order of business is to figure out how quickly the depth increases, 
	 * just so you know what you're dealing with - you never know if the keys will get carried into deeper water 
	 * by an ocean current or a fish or something.
	 * To do this, count the number of times a depth measurement increases from the previous measurement. 
	 * (There is no measurement before the first measurement.)
	 * How many measurements are larger than the previous measurement?
	 */
	
	static int taskOne(ArrayList<Integer> input)
	{
		int x = 0;
		for(int i = 0; i < input.size()-1; i++)
		{
			if(input.get(i) < input.get(i+1))
			{
				x++;
			}
		}
		return x;
	}
	
	/*
	 * Considering every single measurement isn't as useful as you expected: 
	 * there's just too much noise in the data. Instead, consider sums of a three-measurement sliding window. 
	 * Stop when there aren't enough measurements left to create a new three-measurement sum.
	 * How many sums are larger than the previous sum?
	 */
	
	
	static int taskTwo(ArrayList<Integer> input)
	{
		int x = 0;
		for(int i = 0; i < input.size()-3; i++)
		{
			if(input.get(i) + input.get(i+1) + input.get(i+2) < input.get(i+1) + input.get(i+2) + input.get(i+3))
			{
				x++;
			}
		}
		return x;
	}
}
