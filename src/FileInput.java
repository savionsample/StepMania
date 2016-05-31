/*********************************************************************************

Program:	FileInput
Created by:	Richard Steinberg
Created on: 10/16/07
Description:
	This program provides a simple example of text file input.  It reads a text file
	one line at a time and prints what it has read.  It displays an error message if
	there is any problem finding, opening or reading the file.  

Additional notes:
	The input file should be in the project folder in your workspace.
	s.next()       - Read in the next "token" as a String.  A token is everything 
						up to but not including the next blank or '\n'.  The next 
						call to s.nextXXX will read from after that blank or '\n'.
	s.nextInt()    - Read in the next token and convert it into a int.
	s.nextDouble() - Read in the next token and convert it into a double.
	s.nextLine()   - Read from the current read position to the end of the current
	 					line as a String.  The next call to s.nextXXX will read 
	 					from the start of the next line.
		
********************************************************************************/
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileInput 
{
	
    public static ArrayList<String> readInput(String songName)
    {
		ArrayList<String> arr = new ArrayList<String>();
        Scanner s = null;
        try 
        {
        	// Open the file.  Note that Eclipse looks for the file in your 
        	//	workspace inside your project folder.
        	if (songName.equals("Paris"))
        	{
        		s = new Scanner(new BufferedReader(new FileReader("ParisNotes.txt")));
        	}
        	else if (songName.equals("TearInMyHeart"))
        	{
        		s = new Scanner(new BufferedReader(new FileReader("TearInMyHeartNotes.txt")));
        	}
        	else if (songName.equals("Dragonforce"))
        	{
        		s = new Scanner(new BufferedReader(new FileReader("DragonforceNotes.txt")));
        	}
        	else if (songName.equals("Freeze"))
        	{
        		s = new Scanner(new BufferedReader(new FileReader("FreezeNotes.txt")));
        	}

            // Step through the file, reading one line at a time and printing it.
            while (s.hasNext()) 
            {
            	String oneLine = s.nextLine();
            	arr.add(oneLine);
            }
            return arr;
        
        }
        catch (IOException e)
        {	
        	// Something went wrong!
        	System.out.println("File error - file not found, could not be " +
        						"opened or could not be read.");
        }
        finally 
        {
        	// Do this before proceeding whether or not there was an IOException.
            if (s != null) 
            {
                s.close();
            }
        }
        
        //System.out.println("Done reading the file!");
        return arr;
    }		
   
}