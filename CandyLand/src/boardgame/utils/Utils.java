package boardgame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

	/*We can access this anywhere (public) and it takes in a string we're
	 * calling path */
	public static String loadFileAsString(String path){
		
		//This allows you to add characters to a string
		StringBuilder builder = new StringBuilder();
		
		//this is in case an error occurs
		try{
			//build a buffered reader and try reading in the file
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			//This will let us continue reading the file until we reach the end of the line
			//similar to an !eof loop in c++
			while((line = br.readLine()) != null){
				builder.append(line + "\n");
			}
			//we want to close the file that we opened
			br.close();
		} catch (IOException e){
			//if an error occurs, print it to the screen
			e.printStackTrace();
		}
		
		//we have to return the file we just loaded into a single string
		return builder.toString();
	}
	
	//this takes in a string and then converts the string into actual numbers
	//since our file is a series of numbers
	public static int parseInt(String number){
		try{
			return Integer.parseInt(number);
		}catch(NumberFormatException e){
			//catch this just in case it hits, for example, a letter
			e.printStackTrace();
			//return 0 to avoid program crashing
			return 0;
		}
	}
}
