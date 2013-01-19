package main;

import java.util.Arrays;

//Data structure for storing information about a token
public class Token {

	private String token;
	private String lexeme;
	private int lineNum;
	
	//Default constructor
	public Token() {}
	
	//Constructor
	public Token(String token, String lexeme, int lineNum) {
		
		this.token = token;
		this.lexeme = lexeme;
		this.lineNum = lineNum;
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLexeme() {
		return lexeme;
	}

	public void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
	
	//returns formatted  info
	public String toString() {
		
		return String.format("Token: %-15s Lexeme: %-15s Line: %-15s", token, lexeme, lineNum);
		
	}
	
	//checks if token belongs to set
	public boolean belongsTo(String[] set) {
		
		return Arrays.asList(set).contains(token);
		
	}
	
}
