package main;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

	/**
	 * @param args
	 */
	
	static long start = System.currentTimeMillis();

	public static void increment(Record i) {i.setName("hello");}
	
	//Main method
	public static void main(String[] args) {
		
		try {
			
			PrintWriter tokens = null, derivation = null, tables = null, mooncode = null; //mooncode unused now
			PrintStream errors = null;
			BufferedReader code = null;
			
			//Set up streams
			try
			{
				code = new BufferedReader(new FileReader("code.txt"));
				tokens = new PrintWriter(new FileOutputStream("tokens.txt"));
				derivation = new PrintWriter(new FileOutputStream("derivation.txt"));
				errors = new PrintStream(new FileOutputStream("errors.txt"));
				System.setErr(errors); //redirect System.err to errors.txt
				tables = new PrintWriter(new FileOutputStream("tables.txt"));
				
				//MOON CODE UNUSED
				mooncode = new PrintWriter(new FileOutputStream("mooncode.m"));
				
				
				
			}
			
			catch(FileNotFoundException e) 
			{							   
				System.out.println("Problem opening files.");
				System.out.println("Program will terminate.");
				System.exit(0);
			}	
			
			//Streams are set up at this point
			
			Source source = new Source(code); //Object for tracking progress and backtracking through source code. See Source.java for more info.
			Lexer lexer = new Lexer(source, tokens);
			
			//MOONCODE UNUSED
			Parser parser = new Parser(lexer, derivation, tables, mooncode);
			
			if (parser.parse()) {
				
				System.out.println("Success!");
				
			}
			
			else {
				System.out.println("Failure!");
				System.out.println("Check errors.txt");
			}
				
			//END OF FILE
			
			//close files
			code.close();
			tokens.close();
			derivation.close();
			errors.close();
			tables.close();
			mooncode.close();
		}
		
		catch (IOException e) {
		
			System.exit(0);
			
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Execution time was "+(end-start)+" ms.");
		
	}
	
}