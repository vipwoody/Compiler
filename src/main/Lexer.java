package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Lexer {
	
	private Token token;
	private Source source;
	private PrintWriter tokens;
	
	public Lexer (Source source, PrintWriter tokens) {
		
		this.source = source;
		this.tokens = tokens;
		
	}
	
	//Test to check if character is a letter
	public boolean isLetter(char c) {
		
		String temp = "";
		temp += c;
		
		if (temp.matches("[a-zA-Z]")) {
		
		return true;
		
		}
		
		return false;
		
	}
	
	//Test to check if character is a digit
	public boolean isDigit(char c) {
		
		String temp = "";
		temp += c;
		
		if (temp.matches("[0-9]")) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	//Test to check if character is alphanumeric or an underscore
	public boolean isAlphanum(char c) {
		
		String temp = "";
		temp += c;
		
		if (isLetter(c) || isDigit(c) || temp.matches("_")) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	//Test to see if character is a reserved word
	public boolean isReservedWord(String s) {
		
		if (s.equals("and") || s.equals("not") || s.equals("or")
				|| s.equals("if") || s.equals("then") || s.equals("else") 
				|| s.equals("while") || s.equals("do") || s.equals("class") 
				|| s.equals("integer") || s.equals("real") || s.equals("read") 
				|| s.equals("write") || s.equals("return") || s.equals("program")) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	/*This is a handwritten method for analyzing tokens.
	Since errors are detected within this method and written to errors.txt
	this method could throw an IOException*/ 
	public Token nextToken() throws IOException {
		
		char c;
		String tokenString = "";
		
		token = null;	
		
		c = source.nextChar();
		
		//CONSIDER SWITCH CASE
			
		while (c == ' ') {
			
			c = source.nextChar();
			
		}
		
		if (c == '/') {
				
			c = source.nextChar();					
			if (c == '/'){
				
				token = new Token("COMMENT", "//", source.l);
				
				source.lastOCOMMENTLine = source.l;
				
				while (source.l == source.lastOCOMMENTLine) {
					
					c = source.nextChar();
					
				}
				
			}
				
			else if(c == '*'){
				
				source.lastOCOMMENTLine = source.l;
				
				source.comment = true;
				
				while (!source.endOfFile) {
					
					c = source.nextChar();
					
					if (c == '*') {
						
						c = source.nextChar();
						
						if (c == '/') {
							
							token = new Token("BLOCKCOMMENT", "/**/", source.lastOCOMMENTLine);
							source.comment = false;
							break;
							
						}
							
					}
					
				}
				
				if (source.comment) {
					
					System.err.println("ERROR!!! Unclosed comment at line " + source.lastOCOMMENTLine);
					return new Token("ERROR", null, source.l);
					
				}
				
					
			}
				
			else {
				
				token = new Token("FWDSLASH", "/", source.l);
				source.backupChar();
				
			}
				
		}
			
		else if (c == '*') {
			
			token = new Token("ASTERISK", "*", source.l);
			
		}
			
		else if (c == '+') {
			
			token = new Token("PLUS", "+", source.l);
			
		}
		
		else if (c == '-') {
		
			token = new Token("MINUS", "-", source.l);
			
		}
		
		else if (c == ';') {
			
			token = new Token("SEMICOLON", ";", source.l);
			
		}
		
		else if (c == ',') {
		
			token = new Token("COMMA", ",", source.l);
			
		}
		
		else if (c == '(') {
			
			token = new Token("OPAREN", "(", source.l);
			
		}
		
		else if (c == ')') {
			
			token = new Token("CPAREN", ")", source.l);
			
		}
		
		else if (c == '{') {
			
			token = new Token("OBRACE", "{", source.l);
			
		}
		
		else if (c == '}') {
			
				token = new Token("CBRACE", "}", source.l);
				
		}
			
		else if (c == '[') {
				
			token = new Token("OBRACKET", "[", source.l);
			
		}
			
		else if (c == ']') {
			
			token = new Token("CBRACKET", "]", source.l);
			
		}
		
		else if (c == '=') {
			
			c = source.nextChar();
			
			if (c == '=') {
				
				token = new Token("EQ", "==", source.l);
				
			}
				
			else {
				
				token = new Token("DEF", "=", source.l);
				source.backupChar();
				
			}
				
		}
			
		else if (c == '<') {
				
			c = source.nextChar();
				
			if (c == '>') {
				
				token = new Token("NEQ", "<>", source.l);
				
			}
			
			else if (c == '=') {
				
				token = new Token("LEQ", "<=", source.l);
				
			}
				
			else {
				
				token = new Token("LESS", "<", source.l);
				source.backupChar();
				
			}
			
		}
		
		else if (c == '>') {
			
			c = source.nextChar();
			
			if (c == '=') {
				
				token = new Token("GEQ", ">=", source.l);
				
			}
			
			else {
				
				token = new Token("GREATER", ">", source.l);
				source.backupChar();
				
			}
			
		}
		
		else if (isLetter(c)) {
			
			tokenString = tokenString + c;
			
			c = source.nextChar();
				
			while (isAlphanum(c)) {
				
				tokenString = tokenString + c;
				c = source.nextChar();
					
			}
				
			if (isReservedWord(tokenString)) {
				
				token = new Token(tokenString.toUpperCase(), tokenString, source.l);
				tokenString = "";
				
			}
				
			else {
				
				token = new Token("ID", tokenString, source.l);
				tokenString = "";
				
			}
				
			source.backupChar();
			
		}
		
		else if (c == '0') {
			
			tokenString += c;
			c = source.nextChar();
			
			if (c != '.') {
				
				token = new Token("NUM", tokenString, source.l);
				tokenString = "";
				source.backupChar();
				
			}
				
			else if (c == '.') {
			
				tokenString += c;
				c = source.nextChar();
				
				if (isDigit(c)) {
					
					char last = c;
					
					while (isDigit(c)) {
						
						last = c;
						tokenString += c;
						c = source.nextChar();
						
					}
					
					if (last == '0' && !tokenString.equals("0.0")) {
						
						System.err.println("Error!!! Unknown Token: " + tokenString + " at line " + source.l);
						source.backupChar();
						tokenString = "";
						return new Token("ERROR", null, source.l);
						
					}
						
					else {
					
						token = new Token("NUM", tokenString, source.l);
						source.backupChar();
						tokenString = "";
						
					}
					
				}
					
			}
				
		}
			
		else if (isDigit(c)) { //Will only get here if it's not 0
			
			tokenString += c;
			c = source.nextChar();
			
			while (isDigit(c)) {
				
				tokenString += c;
				c = source.nextChar();
				
			}
			
			if (c == '.') {
				
				char last = c;
				
				c = source.nextChar();
				tokenString += last;
				
				if (isDigit(c)) {
					
					while (isDigit(c)) {
						
						last = c;
						tokenString += c;
						c = source.nextChar();
						
					}
					
					// Ex: 1.0 or 42545235.0 etc.
					if ((last == '0') && (tokenString.charAt(tokenString.length()-2) != '.')) {
						
						System.err.println("Error!!! Unknown Token: " + tokenString + " at line " + source.l);
						source.backupChar();
						tokenString = "";
						return new Token("ERROR", null, source.l);
						
					}
					
					else {
					
						token = new Token("NUM", tokenString, source.l);
						source.backupChar();
						tokenString = "";
						
					}
					
				}
				
				//Ex: 1. or 4985723.
				else {
					
					System.err.println("Error!!! Unknown Token " + tokenString + " at line " + source.l);
					source.backupChar();
					tokenString = "";
					return new Token("ERROR", null, source.l);
					
				}
				
			}
			
			//Valid number
			else {
				
				token = new Token("INT", tokenString, source.l);
				source.backupChar();
				tokenString = "";
				
			}
			
		}
		
		else if (c == '.') {
				
			token = new Token("DOT", ".", source.l);
			
		}
		
		else if (source.endOfFile) {
			
			token = new Token("EOF", "$", source.l);
			tokens.println(token.toString());
			return token;
			
		}
		
		else {
			
			System.err.println("Error!!! Unknown Character: " + c + " at line " + source.l);
			return new Token("ERROR", null, source.l);
			
		}
			
	source.endOfFile = false;
	tokens.println(token.toString());
	return token;
		
}

	public void switchSource() throws IOException {
		
		source.code.close();
		
		source.code = new BufferedReader(new FileReader("code.txt"));
		
	}
	
}