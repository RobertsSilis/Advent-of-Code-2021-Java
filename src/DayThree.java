import java.io.File; //Allows managing files
import java.io.FileNotFoundException; //Catches exception
import java.util.ArrayList;
import java.util.Scanner; //Allows reading from a file

/*
--- Day 3: Binary Diagnostic ---

The submarine has been making some odd creaking noises, so you ask it to produce a diagnostic report just in case.

The diagnostic report (your puzzle input) consists of a list of binary numbers which, when decoded properly, can tell you many useful things about the conditions of the submarine. The first parameter to check is the power consumption.

You need to use the binary numbers in the diagnostic report to generate two new binary numbers (called the gamma rate and the epsilon rate). The power consumption can then be found by multiplying the gamma rate by the epsilon rate.

Each bit in the gamma rate can be determined by finding the most common bit in the corresponding position of all numbers in the diagnostic report. For example, given the following diagnostic report:

00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010

 */

public class DayThree 
{
	static void dayThree()
	{
		System.out.println(taskOne());
		System.out.println(taskTwo());
	}			
	
/*
 
Considering only the first bit of each number, there are five 0 bits and seven 1 bits. Since the most common bit is 1, the first bit of the gamma rate is 1.

The most common second bit of the numbers in the diagnostic report is 0, so the second bit of the gamma rate is 0.

The most common value of the third, fourth, and fifth bits are 1, 1, and 0, respectively, and so the final three bits of the gamma rate are 110.

So, the gamma rate is the binary number 10110, or 22 in decimal.

The epsilon rate is calculated in a similar way; rather than use the most common bit, the least common bit from each position is used. So, the epsilon rate is 01001, or 9 in decimal. Multiplying the gamma rate (22) by the epsilon rate (9) produces the power consumption, 198.

Use the binary numbers in your diagnostic report to calculate the gamma rate and epsilon rate, then multiply them together. What is the power consumption of the submarine? (Be sure to represent your answer in decimal, not binary.)
 
 */

	static int taskOne()
	{
		ArrayList<Integer> results = new ArrayList<Integer>();
		try
		{
			File directions = new File("InputDayThree.txt"); 
			Scanner myReader = new Scanner(directions);
			String input = myReader.nextLine();
			for(int i = 0; i < input.length(); i++)  //Forms ArrayList "results" length based on the number of bits in the first line of input
			{
				if(input.charAt(i) == '1')
				{
					results.add(1);
				}
				else
				{
					results.add(-1);
				}	
			}
			while(myReader.hasNextLine()) //Gives values to "results" based on most frequent binary value
			{
				input = myReader.nextLine();
				for(int i = 0; i<input.length(); i++)
				{
					if(input.charAt(i) == '1')
					{
						results.set(i, results.get(i)+1);
					}
					else
					{
						results.set(i, results.get(i)-1);
					}
				}
			}
			myReader.close();
		}
		catch (FileNotFoundException e) 
		{
		     System.out.println("An error occurred.");
		     e.printStackTrace();
		}
		for(int i = 0; i < results.size(); i++)  //creates a binary number based on values of Results
		{
			if(results.get(i) < 0)
			{
				results.set(i, 0);
			}
			else
			{
				results.set(i, 1);
			}
		}
		int gamma = 0, epsilon = 0;
		for(int i = 0, j = results.size() -1; i < results.size(); i++, j--) //finds out gamma value, going trough bit by bit
		{
			gamma = gamma + (int)Math.pow(2, j) * results.get(i);
		}
		epsilon = (int)Math.pow(2, results.size()) - gamma - 1; //assigns value to epsilon, based on gamma value
		//System.out.println(gamma);
		//System.out.println(epsilon);
		return gamma * epsilon;
	}
	
/*
	
--- Part Two ---
Next, you should verify the life support rating, which can be determined by multiplying the oxygen generator rating by the CO2 scrubber rating.

Both the oxygen generator rating and the CO2 scrubber rating are values that can be found in your diagnostic report - finding them is the tricky part. Both values are located using a similar process that involves filtering out values until only one remains. Before searching for either rating value, start with the full list of binary numbers from your diagnostic report and consider just the first bit of those numbers. Then:

Keep only numbers selected by the bit criteria for the type of rating value for which you are searching. Discard numbers which do not match the bit criteria.
If you only have one number left, stop; this is the rating value for which you are searching.
Otherwise, repeat the process, considering the next bit to the right.
The bit criteria depends on which type of rating value you want to find:

To find oxygen generator rating, determine the most common value (0 or 1) in the current bit position, and keep only numbers with that bit in that position. If 0 and 1 are equally common, keep values with a 1 in the position being considered.
To find CO2 scrubber rating, determine the least common value (0 or 1) in the current bit position, and keep only numbers with that bit in that position. If 0 and 1 are equally common, keep values with a 0 in the position being considered.
For example, to determine the oxygen generator rating value using the same example diagnostic report from above:

Start with all 12 numbers and consider only the first bit of each number. There are more 1 bits (7) than 0 bits (5), so keep only the 7 numbers with a 1 in the first position: 11110, 10110, 10111, 10101, 11100, 10000, and 11001.
Then, consider the second bit of the 7 remaining numbers: there are more 0 bits (4) than 1 bits (3), so keep only the 4 numbers with a 0 in the second position: 10110, 10111, 10101, and 10000.
In the third position, three of the four numbers have a 1, so keep those three: 10110, 10111, and 10101.
In the fourth position, two of the three numbers have a 1, so keep those two: 10110 and 10111.
In the fifth position, there are an equal number of 0 bits and 1 bits (one each). So, to find the oxygen generator rating, keep the number with a 1 in that position: 10111.
As there is only one number left, stop; the oxygen generator rating is 10111, or 23 in decimal.
Then, to determine the CO2 scrubber rating value from the same example above:

Start again with all 12 numbers and consider only the first bit of each number. There are fewer 0 bits (5) than 1 bits (7), so keep only the 5 numbers with a 0 in the first position: 00100, 01111, 00111, 00010, and 01010.
Then, consider the second bit of the 5 remaining numbers: there are fewer 1 bits (2) than 0 bits (3), so keep only the 2 numbers with a 1 in the second position: 01111 and 01010.
In the third position, there are an equal number of 0 bits and 1 bits (one each). So, to find the CO2 scrubber rating, keep the number with a 0 in that position: 01010.
As there is only one number left, stop; the CO2 scrubber rating is 01010, or 10 in decimal.
Finally, to find the life support rating, multiply the oxygen generator rating (23) by the CO2 scrubber rating (10) to get 230.

Use the binary numbers in your diagnostic report to calculate the oxygen generator rating and CO2 scrubber rating, then multiply them together. What is the life support rating of the submarine? (Be sure to represent your answer in decimal, not binary.)
	
*/
	
	static int taskTwo()
	{
		ArrayList<String> o2Results = new ArrayList<String>();
		try
		{
			
			File directions = new File("InputDayThree.txt"); 
			Scanner myReader = new Scanner(directions);
			while(myReader.hasNextLine())
			{
				o2Results.add(myReader.nextLine()); //Creates an arraylist with all input values
			}
			myReader.close();	
		}
		catch (FileNotFoundException e) 
		{
		     System.out.println("An error occurred.");
		     e.printStackTrace();
		}
		ArrayList<String> co2Results = new ArrayList<String>(o2Results); //Copies the first arraylist as both are using the same input
		for(int j = 0; o2Results.size() > 1; j++) //First loop to determine the correct answer for oxygen generator rating
		{
			int onesZeros = 0;
			for(int i = 0; i < o2Results.size(); i++) //determines which is the most frequent bite in position j
			{
				String line = o2Results.get(i);
				if(line.charAt(j) == '1')
				{
					onesZeros++;
				}
				else
				{
					onesZeros--;
				}
			}
			char keep;
			if(onesZeros >= 0) //defines that the most frequent bite should be kept
			{
				keep = '1';
			}
			else
			{
				keep = '0';
			}
			for(int i = 0; i < o2Results.size(); i++) //deletes all elements, that have the wrong bite
			{
				String line = o2Results.get(i);
				if(line.charAt(j) != keep)
				{
					o2Results.remove(i);
					i--;
				}
			}
		}

		for(int j = 0; co2Results.size() > 1; j++) //second loop to determine the answer for CO2 scrubber rating
		{
			int onesZeros = 0;
			for(int i = 0; i < co2Results.size(); i++) //determines which is the most frequent bite in position j
			{
				String line = co2Results.get(i);
				if(line.charAt(j) == '1')
				{
					onesZeros++;
				}
				else
				{
					onesZeros--;
				}
			}
			char keep;
			if(onesZeros < 0)  //defines that the most frequent bite should NOT be kept
			{
				keep = '1';
			}
			else
			{
				keep = '0';
			}
			for(int i = 0; i < co2Results.size(); i++) //deletes all elements, that have the wrong bite
			{
				String line = co2Results.get(i);
				if(line.charAt(j) != keep)
				{
					co2Results.remove(i);
					i--;
				}
			}
		}
		String oxygen, co2;
		int oxyInt = 0, co2Int = 0;
		oxygen = o2Results.get(0);
		co2 = co2Results.get(0);
		o2Results.clear();
		co2Results.clear();
		for(int i = 0, j = oxygen.length() -1; i < oxygen.length(); i++, j--) //converts a string in binary to an int
		{
			oxyInt = oxyInt + (int)Math.pow(2, j) * Character.getNumericValue(oxygen.charAt(i));
			co2Int = co2Int + (int)Math.pow(2, j) * Character.getNumericValue(co2.charAt(i));
		}
		return oxyInt * co2Int;
	}
}
