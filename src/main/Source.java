package main;

import java.io.BufferedReader;
import java.io.IOException;

/*A mechanism to progress and backtrack through the file
and to check if the end of the file was reached*/
public class Source {

	public String s; //the current line being worked on
	public int i, l, lastOCOMMENTLine; //i = index of current character, l = number of current line, lastOCOMMENTLINE = location of last open comment
	public boolean end, comment, endOfFile;
	BufferedReader code;
	
	//Default constructor
	public Source(BufferedReader code) {
		
		s = null;
		i = -1;
		l = 1;
		lastOCOMMENTLine = 0;
		end = false;
		endOfFile = false;
		this.code = code;
		
	}
	
	//Skip to next character
	public char nextChar() throws IOException {
		 
		code.mark(1);
		i++;
		
		int c = code.read();
		
		if (c == '\n') {
			
			l++;
			
			return ' ';
			
		}
		
		if (c == '\r' || c == '\t') {
			
			return ' ';
			
		}
		
		if (c == -1) {
			
			endOfFile = true;
			
		}
		
		return (char)c;
		
	}
	
	//Backup to previous character.
	public void backupChar() throws IOException {
		
		code.reset();
		
	}
	
}