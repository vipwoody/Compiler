package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Parser {
	
	//FIRST and FOLLOW sets
	private static final String[] 

			FIRST_prog = {"CLASS", "PROGRAM"},
			FIRST_prog_RHS1 = {"CLASS", "PROGRAM"},
			FOLLOW_prog = {"EOF"},

			FIRST_classDeclList = {"CLASS"},
			FIRST_classDeclList_RHS1 = {"CLASS"},
			FOLLOW_classDeclList = {"PROGRAM"}, 

			FIRST_classDecl = {"CLASS"},
			FIRST_classDecl_RHS1 = {"CLASS"},
			FOLLOW_classDecl = {"CLASS", "PROGRAM"},		

			FIRST_classMemberDecl = {"ID", "INTEGER", "REAL"},
			FIRST_classMemberDecl_RHS1 = {"ID", "INTEGER", "REAL"},
			FOLLOW_classMemberDecl = {"ID", "INTEGER", "REAL", "CBRACE"},

			FIRST_classMemberDeclPRIME = {"OBRACKET", "SEMICOLON", "OPAREN"},
			FIRST_classMemberDeclPRIME_RHS1 = {"OBRACKET", "SEMICOLON"}, 
			FIRST_classMemberDeclPRIME_RHS2 = {"OPAREN"},
			FOLLOW_classMemberDeclPRIME = {"ID", "INTEGER", "REAL", "CBRACE"},

			FIRST_classMemberDeclList = {"ID", "INTEGER", "REAL"},
			FIRST_classMemberDeclList_RHS1 = {"ID", "INTEGER", "REAL"},
			FOLLOW_classMemberDeclList = {"CBRACE"},

			FIRST_funcDefList = {"ID", "INTEGER", "REAL"},
			FIRST_funcDefList_RHS1 = {"ID", "INTEGER", "REAL"},
			FOLLOW_funcDefList = {"EOF"},

			FIRST_progBody = {"PROGRAM"},
			FIRST_progBody_RHS1 = {"PROGRAM"},
			FOLLOW_progBody = {"EOF"},

			FIRST_funcHead = {"ID", "INTEGER", "REAL"},
			FIRST_funcHead_RHS1 = {"ID", "INTEGER", "REAL"},
			FOLLOW_funcHead = {"OBRACE"},

			FIRST_funcDef = {"ID", "INTEGER", "REAL"},
			FIRST_funcDef_RHS1 = {"ID", "INTEGER", "REAL"},
			FOLLOW_funcDef = {"ID", "INTEGER", "REAL", "EOF"},

			FIRST_funcBody = {"OBRACE"},
			FIRST_funcBody_RHS1 = {"OBRACE"},
			FOLLOW_funcBody = {"SEMICOLON"},

			FIRST_funcBodyMember = {"IF", "WHILE", "READ", "WRITE", "RETURN", "ID", "REAL", "INTEGER"},
			FIRST_funcBodyMember_RHS1 = {"IF", "WHILE", "READ", "WRITE", "RETURN"}, 
			FIRST_funcBodyMember_RHS2 = {"ID"}, 
			FIRST_funcBodyMember_RHS3 = {"REAL"}, 
			FIRST_funcBodyMember_RHS4 = {"INTEGER"},
			FOLLOW_funcBodyMember = {"INTEGER", "REAL", "ID", "IF", "WHILE", "READ", "WRITE", "RETURN", "CBRACE"},

			FIRST_funcBodyMemberPRIME = {"OBRACKET", "DOT", "DEF", "ID"},
			FIRST_funcBodyMemberPRIME_RHS1 = {"OBRACKET", "DOT", "DEF"}, 
			FIRST_funcBodyMemberPRIME_RHS2 = {"ID"},
			FOLLOW_funcBodyMemberPRIME = {"INTEGER", "REAL", "ID", "IF", "WHILE", "READ", "WRITE", "RETURN", "CBRACE"},

			FIRST_funcBodyMemberPRIMEPRIME = {"DEF", "DOT"},
			FIRST_funcBodyMemberPRIMEPRIME_RHS1 = {"DEF"},
			FIRST_funcBodyMemberPRIMEPRIME_RHS2 = {"DOT"},
			FOLLOW_funcBodyMemberPRIMEPRIME = {"INTEGER", "REAL", "ID", "IF", "WHILE", "READ", "WRITE", "RETURN", "CBRACE"},

			FIRST_funcBodyMemberList = {"INTEGER", "REAL", "ID", "IF", "WHILE", "READ", "WRITE", "RETURN"},
			FIRST_funcBodyMemberList_RHS1 = {"INTEGER", "REAL", "ID", "IF", "WHILE", "READ", "WRITE", "RETURN"},
			FOLLOW_funcBodyMemberList = {"CBRACE"},

			FIRST_type = {"ID", "INTEGER", "REAL"},
			FIRST_type_RHS1 = {"REAL"}, 
			FIRST_type_RHS2 = {"INTEGER"}, 
			FIRST_type_RHS3 = {"ID"},
			FOLLOW_type = {"ID"},

			FIRST_arraySizeList = {"OBRACKET"},
			FIRST_arraySizeList_RHS1 = {"OBRACKET"},
			FOLLOW_arraySizeList = {"SEMICOLON", "CPAREN", "COMMA"},

			FIRST_statement = {"IF", "WHILE", "READ", "WRITE", "RETURN", "ID"},
			FIRST_statement_RHS1 = {"IF", "WHILE", "READ", "WRITE", "RETURN"}, 
			FIRST_statement_RHS2 = {"ID"},
			FOLLOW_statement = {"ELSE", "SEMICOLON", "ID", "IF", "WHILE", "READ", "RETURN", "WRITE", "CBRACE"},
			
			FIRST_statementPRIME = {"IF", "WHILE", "READ", "WRITE", "RETURN"},
			FIRST_statementPRIME_RHS1 = {"IF"}, 
			FIRST_statementPRIME_RHS2 = {"WHILE"}, 
			FIRST_statementPRIME_RHS3 = {"READ"}, 
			FIRST_statementPRIME_RHS4 = {"RETURN"},
			FIRST_statementPRIME_RHS5 = {"WRITE"},
			FOLLOW_statementPRIME = {"INTEGER", "REAL", "ID", "IF", "WHILE", "READ", "WRITE", "RETURN", "CBRACE", "ELSE", "SEMICOLON", "ID", "IF", "WHILE", "READ", "RETURN", "WRITE", "CBRACE"},

			FIRST_statBlock = {"IF", "WHILE", "READ", "WRITE", "RETURN", "ID", "OBRACE"},
			FIRST_statBlock_RHS1 = {"IF", "WHILE", "READ", "WRITE", "RETURN", "ID"}, 
			FIRST_statBlock_RHS2 = {"OBRACE"},
			FOLLOW_statBlock = {"ELSE", "SEMICOLON"},

			FIRST_statementList = {"ID", "IF", "WHILE", "READ", "RETURN", "WRITE"},
			FIRST_statementList_RHS1 = {"ID", "IF", "WHILE", "READ", "RETURN", "WRITE"},
			FOLLOW_statementList = {"CBRACE"},
			
			FIRST_expr = {"OPAREN", "ID", "NUM", "NOT", "PLUS", "MINUS"},
			FIRST_expr_RHS1 = {"OPAREN", "ID", "NUM", "NOT", "PLUS", "MINUS"},
			FOLLOW_expr = {"SEMICOLON", "CPAREN", "COMMA", "CPAREN"},

			FIRST_exprPRIME = {"LESS", "EQ", "GREATER", "GEQ", "LEQ", "NEQ"},
			FIRST_exprPRIME_RHS1 = {"LESS", "EQ", "GREATER", "GEQ", "LEQ", "NEQ"},
			FOLLOW_exprPRIME = {"SEMICOLON", "CPAREN", "COMMA"},

			FIRST_arithExpr = {"OPAREN", "ID", "NUM", "NOT", "PLUS", "MINUS"},
			FIRST_arithExpr_RHS1 = {"OPAREN", "ID", "NUM", "NOT", "PLUS", "MINUS"},
			FOLLOW_arithExpr = {"LESS", "EQ", "GREATER", "GEQ", "LEQ", "NEQ", "SEMICOLON", "CPAREN", "COMMA", "SEMICOLON", "CPAREN", "COMMA", "CBRACKET"},

			FIRST_arithExprPRIME = {"PLUS", "MINUS", "OR"},
			FIRST_arithExprPRIME_RHS1 = {"PLUS", "MINUS", "OR"},
			FOLLOW_arithExprPRIME = {"SEMICOLON", "CPAREN", "COMMA", "LESS", "EQ", "GREATER", "GEQ", "LEQ", "NEQ", "CBRACKET"},

			FIRST_sign = {"PLUS", "MINUS"},
			FIRST_sign_RHS1 = {"PLUS"}, 
			FIRST_sign_RHS2 = {"MINUS"},
			FOLLOW_sign = {"OPAREN", "ID", "NUM", "NOT", "PLUS", "MINUS"},

			FIRST_term = {"OPAREN", "ID", "NUM", "NOT", "PLUS", "MINUS"},
			FIRST_term_RHS1 = {"OPAREN", "ID", "NUM", "NOT", "PLUS", "MINUS"},
			FOLLOW_term = {"SEMICOLON", "CPAREN", "COMMA", "LESS", "EQ", "GREATER", "GEQ", "LEQ", "NEQ", "CBRACKET", "PLUS", "MINUS", "OR"},

			FIRST_termPRIME = {"ASTERISK", "FWDSLASH", "AND"},
			FIRST_termPRIME_RHS1 = {"ASTERISK", "FWDSLASH", "AND"},
			FOLLOW_termPRIME = {"SEMICOLON", "CPAREN", "COMMA", "LESS", "EQ", "GREATER", "GEQ", "LEQ", "NEQ", "CBRACKET", "PLUS", "MINUS", "OR"},

			FIRST_factor = {"OPAREN", "ID", "NUM", "NOT", "PLUS", "MINUS"},
			FIRST_factor_RHS1 = {"OPAREN"}, 
			FIRST_factor_RHS2 = {"ID"}, 
			FIRST_factor_RHS3 = {"NUM"}, 
			FIRST_factor_RHS4 = {"NOT"}, 
			FIRST_factor_RHS5 = {"PLUS", "MINUS"},
			FOLLOW_factor = {"ASTERISK", "FWDSLASH", "AND", "SEMICOLON", "CPAREN", "COMMA","LESS", "EQ", "GREATER", "GEQ", "LEQ", "NEQ", "CBRACKET", "PLUS", "MINUS", "OR"},

			FIRST_factorPRIME = {"OPAREN"},
			FIRST_factorPRIME_RHS1 = {"OPAREN"},
			FOLLOW_factorPRIME = {"SEMICOLON", "CPAREN", "COMMA", "LESS", "EQ", "GREATER", "GEQ", "LEQ", "NEQ", "CBRACKET", "PLUS", "MINUS", "OR", "ASTERISK", "FWDSLASH", "AND"},

			FIRST_idnestList = {"DOT"},
			FIRST_idnestList_RHS1 = {"DOT"},
			FOLLOW_idnestList = {"DEF", "SEMICOLON", "CPAREN", "COMMA", "LESS", "EQ", "GREATER", "GEQ", "LEQ", "NEQ", "CBRACKET", "PLUS", "MINUS", "OR", "ASTERISK", "FWDSLASH", "AND", "OPAREN"},

			FIRST_variable = {"ID"},
			FIRST_variable_RHS1 = {"ID"},
			FOLLOW_variable = {"DEF", "CPAREN"},

			FIRST_indiceList = {"OBRACKET"},
			FIRST_indiceList_RHS1 = {"OBRACKET"},
			FOLLOW_indiceList = {"DEF", "SEMICOLON", "CPAREN", "COMMA", "LESS", "EQ", "GREATER", "GEQ", "LEQ", "NEQ", "CBRACKET", "PLUS", "MINUS", "OR", "ASTERISK", "FWDSLASH", "AND", "OPAREN", "DOT"},

			FIRST_indice = {"OBRACKET"},
			FIRST_indice_RHS1 = {"OBRACKET"},
			FOLLOW_indice = {"OBRACKET", "SEMICOLON", "CPAREN", "COMMA", "LESS", "EQ", "GREATER", "GEQ", "LEQ", "NEQ", "CBRACKET", "PLUS", "MINUS", "OR", "ASTERISK", "FWDSLASH", "AND", "OPAREN", "DOT"},

			FIRST_arraySize = {"OBRACKET"},
			FIRST_arraySize_RHS1 = {"OBRACKET"},
			FOLLOW_arraySize = {"OBRACKET", "SEMICOLON", "CPAREN", "COMMA"},

			FIRST_fParams = {"ID", "INTEGER", "REAL"},
			FIRST_fParams_RHS1 = {"ID", "INTEGER", "REAL"},
			FOLLOW_fParams = {"CPAREN"},

			FIRST_fParamsTailList = {"COMMA"},
			FIRST_fParamsTailList_RHS1 = {"COMMA"},
			FOLLOW_fParamsTailList = {"CPAREN"},

			FIRST_aParams = {"OPAREN", "ID", "NUM", "NOT", "PLUS", "MINUS"},
			FIRST_aParams_RHS1 = {"OPAREN", "ID", "NUM", "NOT", "PLUS", "MINUS"},
			FOLLOW_aParams = {"CPAREN"},

			FIRST_aParamsTailList = {"COMMA"},
			FIRST_aParamsTailList_RHS1 = {"COMMA"},
			FOLLOW_aParamsTailList = {"CPAREN"},

			FIRST_fParamsTail = {"COMMA"},
			FIRST_fParamsTail_RHS1 = {"COMMA"},
			FOLLOW_fParamsTail = {"COMMA", "CPAREN"},

			FIRST_aParamsTail = {"COMMA"},
			FIRST_aParamsTail_RHS1 = {"COMMA"},
			FOLLOW_aParamsTail = {"COMMA", "CPAREN"},

			FIRST_relOp = {"LESS", "EQ", "GREATER", "GEQ", "LEQ", "NEQ"},
			FIRST_relOp_RHS1 = {"LESS"},
			FIRST_relOp_RHS2 = {">"},
			FIRST_relOp_RHS3 = {"NEQ"},
			FIRST_relOp_RHS4 = {"LEQ"},
			FIRST_relOp_RHS5 = {"EQ"},
			FIRST_relOp_RHS6 = {"GEQ"},
			FOLLOW_relOp = {"OPAREN", "ID", "NUM", "NOT", "PLUS", "MINUS"},

			FIRST_addOp = {"OR", "MINUS", "PLUS"},
			FIRST_addOp_RHS1 = {"OR"},
			FIRST_addOp_RHS2 = {"MINUS"},
			FIRST_addOp_RHS3 = {"PLUS"},
			FOLLOW_addOp = {"OPAREN", "ID", "NUM", "NOT", "PLUS", "MINUS"},

			FIRST_multOp = {"AND", "FWDSLASH", "ASTERISK"},
			FIRST_multOp_RHS1 = {"AND"},
			FIRST_multOp_RHS2 = {"FWDSLASH"},
			FIRST_multOp_RHS3 = {"ASTERISK"},
			FOLLOW_multOp = {"OPAREN", "ID", "NUM", "NOT", "PLUS", "MINUS"};

	private Lexer lexer;
	private PrintWriter pw; 
	
	/* MOON CODE INCOMPLETE
	
	private PrintWriter mooncode;

	*/

	static PrintWriter pw2;
	private Token lookahead;
	private boolean error;

	private boolean parsedComplete = false;
	private String lastLexeme;
	private Record lastIdnest;
	private boolean flush = false;
	
	public static boolean tableError = false;
	
	public Parser(Lexer lexer, PrintWriter pw, PrintWriter pw2, PrintWriter mooncode) {
		
		Parser.pw2 = pw2;
		this.lexer = lexer;
		this.pw = pw;
		
		/* MOON CODE INCOMPLETE
		
		this.mooncode = mooncode;
		
		*/
	}

	//a local version of nextToken which skips comments
	public void nextToken() throws IOException {
		
		lookahead = lexer.nextToken();
		
		while (lookahead.getToken().equals("BLOCKCOMMENT") || lookahead.getToken().equals("COMMENT"))
			lookahead = lexer.nextToken();
			
	}
	
	// Table table = new Table();
	
	//main parse function and head of recursive descent
	public boolean parse() throws IOException {
		
		Table table = new Table();
		
		nextToken();

		boolean c1 = prog(table), c2 = match("EOF");
		
		flush = true;
		
		table.flushDefinitionBuffer(flush);
		
		table.giveAddresses();
		
		SecondParser secondParser = new SecondParser(table, lexer, pw);
		
		boolean c3 = secondParser.parse();
		
		if (c1 && c2 && c3 && !tableError) {
			/* MOON CODE INCOMPLETE
			
			table.writeMOON(mooncode);
			
			*/
			return true;
		}
		else
			return false;

	}

	//matches terminals
	public boolean match(String token) throws IOException {

		if (lookahead.getToken().equals(token)) {

			lastLexeme = lookahead.getLexeme();
			
			nextToken();
			return true;

		}
		
		else {

			System.err.println("Syntax error at line " + lookahead.getLineNum() + ". Expected: " + token);
			nextToken();
			return false;

		}

	}

	//Skips errors and recovers the parser from error detection
	public boolean skipErrors(String[] firstUfollow) throws IOException {

		if (Arrays.asList(firstUfollow).contains(lookahead.getToken()))
			return true;
		
		else {
			
			System.err.println("Syntax error at line " + lookahead.getLineNum());

			while (!Arrays.asList(firstUfollow).contains(lookahead.getToken()) && !lookahead.getToken().equals("EOF"))
				nextToken();

			System.err.println("Parsing resumed at line " + lookahead.getLineNum());
			return false;

		}

	}

	//Creates the union of FIRST and FOLLOW sets
	public String[] union(String[] first, String[] follow) {

		String[] union = new String[first.length + follow.length];

		for (int i = 0; i < first.length; i++)
			union[i] = first[i];


		for (int i = 0; i < follow.length; i++)
			union[i + first.length] = follow[i];

		return union;

	}

	//Grammar Rules

	public boolean prog(Table table) throws IOException { // <prog> -> <classDeclList> <progBody> 

		error = skipErrors(union(FIRST_prog, FOLLOW_prog));

		if (lookahead.belongsTo(FIRST_prog_RHS1)) {

			boolean c1 = classDeclList(table), c2 = progBody(table);
			
			if (c1 && c2)
				pw.println("<prog> -> <classDeclList> <progBody>");

			else
				error = false;

		}

		else
			error = false;

		return error;

	}

	public boolean classDeclList(Table table) throws IOException { // <classDeclList> -> <classDecl> <classDeclList> | EPSILON
		
		error = skipErrors(union(FIRST_classDeclList, FOLLOW_classDeclList));

		if (lookahead.belongsTo(FIRST_classDeclList_RHS1)) {

			boolean c1 = classDecl(table), c2 = classDeclList(table);
			
			if (c1 && c2)
				pw.println("<classDeclList> ->  <classDecl> <classDeclList>");
			
			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_classDeclList))
			pw.println("<classDeclList> -> EPSILON");

		else
			error = false;

		return error;

	}

	public boolean classDecl(Table table) throws IOException { // <classDecl> -> class id { <classMemberDeclList }

		Record record = new Record();
		
		error = skipErrors(union(FIRST_classDecl, FOLLOW_classDecl));

		if (lookahead.belongsTo(FIRST_classDecl_RHS1)) {

			boolean c1 = match("CLASS"); 
			record.setStructure("class");
			
			boolean c2 = match("ID");
			record.setName(lastLexeme);
			
			boolean c3 = match("OBRACE");
			Table newTable = new Table(table);
			
			boolean c4 = classMemberDeclList(newTable);
			record.setLocal(newTable);
			
			boolean c5 = match("CBRACE");
			boolean c6 = match("SEMICOLON");
			
			if (c1 && c2 && c3 && c4 && c5 && c6) {
			
				table.insert(record, parsedComplete, false);
				pw.println("<classDecl> -> class id { <classMemberDeclList> }");

			}
				
			else
				error = false;

		}

		else
			error = false;

		return error;

	}

	public boolean classMemberDecl(Table table) throws IOException { // <classMemberDecl> -> <type> id <classMemberDeclPRIME>

		Record record = new Record();
		
		error = skipErrors(union(FIRST_classMemberDecl, FOLLOW_classMemberDecl));

		if (lookahead.belongsTo(FIRST_classMemberDecl_RHS1)) {

			boolean c1 = type(record); 
			boolean c2 = match("ID"); 
			record.setName(lastLexeme);
			
			boolean c3 = classMemberDeclPRIME(record, table);
			
			if (c1 && c2 && c3) {
				table.insert(record, parsedComplete, false);
				pw.println("<classMemberDecl> -> <type> id <classMemberDeclPRIME>");
			}
			else
				error = false;

		}

		else
			error = false;

		return error;

	}

	public boolean classMemberDeclPRIME(Record record, Table table) throws IOException { // <classMemberDeclPRIME -> <arraySizeList> ; | ( <fParams> ) <funcBody> ;

		error = skipErrors(union(FIRST_classMemberDeclPRIME, FOLLOW_classMemberDeclPRIME));

		if (lookahead.belongsTo(FIRST_classMemberDeclPRIME_RHS1)) {
			
			boolean c1 = arraySizeList(record), c2 = match("SEMICOLON");
			
			if (c1 && c2)
				pw.println("<classMemberDeclPRIME> -> <arraySizeList> ;");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FIRST_classMemberDeclPRIME_RHS2)) {
			
			record.setStructure("function");
			Table newTable = new Table(table);
			
			boolean c1 = match("OPAREN"); 
			boolean c2 = fParams(record, newTable);
			boolean c3 = match("CPAREN"); 
			boolean c4 = funcBody(newTable); 
			boolean c5 = match("SEMICOLON");
			
			record.setLocal(newTable);
			
			
			
			if (c1 && c2 && c3 && c4 && c5)
				pw.println("<classMemberDeclPRIME> -> ( <fParams> ) <funcBody> ;");

			else
				error = false;

		}

		else
			error = false;

		return error;		

	}

	public boolean classMemberDeclList(Table table) throws IOException { // <classMemberDeclList> -> <classMemberDecl> <classMemberDeclList> | EPSILON

		error = skipErrors(union(FIRST_classMemberDeclList, FOLLOW_classMemberDeclList));

		if (lookahead.belongsTo(FIRST_classMemberDeclList_RHS1)) {

			boolean c1 = classMemberDecl(table), c2 = classMemberDeclList(table);
			
			if (c1 && c2)
				pw.println("<classMemberDeclList> -> <classMemberDecl> <classMemberDeclList>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_classMemberDeclList))
			pw.println("<classDeclList> -> EPSILON");

		else
			error = false;

		return error;	

	}

	public boolean funcDefList(Table table) throws IOException { // <funcDefList> -> <funcDef> <funcDefList> | EPSILON

		error = skipErrors(union(FIRST_funcDefList, FOLLOW_funcDefList));

		if (lookahead.belongsTo(FIRST_funcDefList_RHS1)) {

			boolean c1 = funcDef(table), c2 = funcDefList(table);
			
			if (c1 && c2)
				pw.println("<funcDefList> -> <funcDef> <funcDefList>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_funcDefList))
			pw.println("<funcDefList> -> EPSILON");

		else
			error = false;

		return error;	

	}

	public boolean progBody(Table table) throws IOException { // <progBody> -> program <funcBody> <funcDefList>

		error = skipErrors(union(FIRST_progBody, FOLLOW_progBody));

		if (lookahead.belongsTo(FIRST_progBody_RHS1)) {

			boolean c1 = match("PROGRAM"); 
			
			Record program = new Record();
			
			program.setName("program");
			program.setStructure("function");
			
			Table newTable = new Table(table);
			
			boolean c2 = funcBody(newTable), c3 = match("SEMICOLON"), c4 = funcDefList(table);
			
			program.setLocal(newTable);
			
			if (c1 && c2 && c3 && c4) {
				table.insert(program, parsedComplete, false);
				pw.println("<progBody> -> program <funcBody> <funcDefList>");
			}
			else
				error = false;

		}

		else
			error = false;

		return error;

	}	

	public boolean funcHead(Record record, Table table) throws IOException { // <funcHead> -> <type> id ( <fParams> )

		error = skipErrors(union(FIRST_funcHead, FOLLOW_funcHead));

		if (lookahead.belongsTo(FIRST_funcHead_RHS1)) {

			boolean c1 = type(record), c2 = match("ID"); 
			
			record.setName(lastLexeme);
			
			boolean c3 = match("OPAREN"), c4 = fParams(record, table), c5 = match("CPAREN");
			
			if (c1 && c2 && c3 && c4 && c5)
				pw.println("<funcHead> -> <type> id ( <fParams> )");

			else
				error = false;

		}

		else
			error = false;

		return error;

	}

	public boolean funcDef(Table table) throws IOException { // <funcDef> -> <funcHead> <funcBody>

		Record record = new Record();
		
		Table newTable = new Table(table);
		
		record.setStructure("function");
		
		error = skipErrors(union(FIRST_funcDef, FOLLOW_funcDef));

		if (lookahead.belongsTo(FIRST_funcDef_RHS1)) {

			boolean c1 = funcHead(record, newTable), c2 = funcBody(newTable), c3 = match("SEMICOLON");
			
			record.setLocal(newTable);
			
			if (c1 && c2 && c3) {
				table.insert(record, parsedComplete, false);
				pw.println("<funcDef> -> <funcHead> <funcBody>");
			}
			else
				error = false;

		}

		else
			error = false;

		return error;

	}

	public boolean funcBody(Table table) throws IOException { // <funcBody> -> { <funcBodyMemberList> }

		error = skipErrors(union(FIRST_funcBody, FOLLOW_funcBody));

		if (lookahead.belongsTo(FIRST_funcBody_RHS1)) {

			boolean c1 = match("OBRACE"), c2 = funcBodyMemberList(table), c3 = match("CBRACE");
			
			if (c1 && c2 && c3)
				pw.println("<funcBody> -> { <funcBodyMemberList> }");

			else
				error = false;

		}

		else
			error = false;

		return error;

	}

	public boolean funcBodyMember(Table table) throws IOException { // <funcBodyMember> -> <statementPRIME> | id <funcBodyMemberPRIME> | real id <arraySizeList> ; | integer id <arraySizeList> ;

		error = skipErrors(union(FIRST_funcBodyMember, FOLLOW_funcBodyMember));
		
		Record record = new Record();
		
		if (lookahead.belongsTo(FIRST_funcBodyMember_RHS1)) {
			
			boolean c1 = statementPRIME(table);
			
			if (c1)
				pw.println("<funcBodyMember> -> <statementPRIME>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FIRST_funcBodyMember_RHS2)) {

			boolean c1 = match("ID");
			
			record.setType(lastLexeme);
			
			boolean c2 = funcBodyMemberPRIME(record, table);
			
			if (c1 && c2) {
				pw.println("<funcBodyMember> -> id <funcBodyMemberPRIME>");
			}
			else
				error = false;
			
		}

		else if (lookahead.belongsTo(FIRST_funcBodyMember_RHS3)) {
			
			boolean c1 = match("REAL"); 
			record.setType("real");
			
			boolean c2 = match("ID"); 
			record.setName(lastLexeme);
			
			boolean c3 = arraySizeList(record), c4 = match("SEMICOLON");
			
			if (c1 && c2 && c3 && c4) {
				table.insert(record, parsedComplete, false);
				pw.println("<funcBodyMember> -> real id <arraySizeList> ;");
			}
			else
				error = false;
			
		}

		else if (lookahead.belongsTo(FIRST_funcBodyMember_RHS4)) {

			boolean c1 = match("INTEGER"); 
			record.setType("integer");
			
			boolean c2 = match("ID"); 
			record.setName(lastLexeme);
			
			boolean c3 = arraySizeList(record), c4 = match("SEMICOLON");
			
			if (c1 && c2 && c3 && c4) {
				table.insert(record, parsedComplete, false);
				pw.println("<funcBodyMember> -> integer id <arraySizeList> ;");
			}
			else
				error = false;
		}

		else
			error = false;

		return error;

	}

	public boolean funcBodyMemberPRIME(Record record, Table table) throws IOException { // <funcBodyMemberPRIME> -> <indiceList> <funcBodyMemberPRIMEPRIME> | id <arraySizeList> ;

		error = skipErrors(union(FIRST_funcBodyMemberPRIME, FOLLOW_funcBodyMemberPRIME));

		if (lookahead.belongsTo(FIRST_funcBodyMemberPRIME_RHS1)) {
			
			//This is a call
			Record call = new Record();
			call.setName(record.getType());

			call.setType(table.getVariableType(call));
			call.setClassLocal(table.getScope(call.getType()).getLocal());
			
			boolean c1 = indiceList(call, table);
			
			if (parsedComplete) {
				
				table.search(call);
				
			}
			
			boolean c2 = funcBodyMemberPRIMEPRIME(call, table);
			
			if (c1 && c2)
				pw.println("<funcBodyMemberPRIME> -> <indiceList> <funcBodyMemberPRIMEPRIME>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FIRST_funcBodyMemberPRIME_RHS2)) {

			boolean c1 = match("ID"); 
			
			record.setName(lastLexeme);
			
			boolean c2 = arraySizeList(record), c3 = match("SEMICOLON");
			
			if (c1 && c2 && c3) {
				table.insert(record, parsedComplete, false);
				pw.println("<funcBodyMemberPRIME> -> id <arraySizeList> ;");
			}
			else
				error = false;

		}

		else
			error = false;

		return error;

	}

	public boolean funcBodyMemberPRIMEPRIME(Record call, Table table) throws IOException { // <funcBodyMemberPRIMEPRIME> -> = <expr> ; | . <variable> = <expr> ;

		error = skipErrors(union(FIRST_funcBodyMemberPRIMEPRIME, FOLLOW_funcBodyMemberPRIMEPRIME));

		if (lookahead.belongsTo(FIRST_funcBodyMemberPRIMEPRIME_RHS1)) {
			
			boolean c1 = match("DEF"), c2 = expr(table), c3 = match("SEMICOLON");
			
			if (c1 && c2 && c3)
				pw.println("<funcBodyMemberPRIMEPRIME> -> = <expr> ;");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FIRST_funcBodyMemberPRIMEPRIME_RHS2)) {

			Table newScope = new Table();
			
			boolean c1 = match("DOT"); 
			
			if (parsedComplete && table.search(call)) {
				
				newScope = table.find(call).getLocal();

			}
			
			boolean c2 = variable(newScope), c3 = match("DEF"), c4 = expr(table), c5 = match("SEMICOLON");
			
			if (c1 && c2 && c3 && c4 && c5)
				pw.println("<funcBodyMemberPRIMEPRIME> -> . <variable> = <expr> ;");

			else
				error = false;

		}

		else
			error = false;

		return error;

	}

	public boolean funcBodyMemberList(Table table) throws IOException { // <funcBodyMemberList> -> <funcBodyMember> <funcBodyMemberList> | EPSILON

		error = skipErrors(union(FIRST_funcBodyMemberList, FOLLOW_funcBodyMemberList));
		
		if (lookahead.belongsTo(FIRST_funcBodyMemberList_RHS1)) {

			boolean c1 = funcBodyMember(table), c2 = funcBodyMemberList(table);
			
			if (c1 && c2)
				pw.println("<funcBodyMemberList> -> <funcBodyMember> <funcBodyMemberList>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_funcBodyMemberList))
			pw.println("<funcBodyMemberList> -> EPSILON");

		else
			error = false;

		return error;

	}

	public boolean type(Record record) throws IOException { // <type> -> real | integer | id

		error = skipErrors(union(FIRST_type, FOLLOW_type));

		if (lookahead.belongsTo(FIRST_type_RHS1)) {

			boolean c1 = match("REAL");
			
			record.setType("real");
			
			if (c1)
				pw.println("<type> -> real");
			
			else
				error = false;

		}

		else if (lookahead.belongsTo(FIRST_type_RHS2)) {

			boolean c1 = match("INTEGER");
			
			record.setType("integer");
			
			if (c1)
				pw.println("<type> -> integer");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FIRST_type_RHS3)) {

			boolean c1 = match("ID");
			
			record.setType(lastLexeme);
			
			if (c1)
				pw.println("<type> -> id");

			else
				error = false;

		}
		
		else
			error = false;

		return error;

	}

	public boolean arraySizeList(Record record) throws IOException { // <arraySizeList> -> <arraySize> <arraySizeList> | EPSILON
		
		error = skipErrors(union(FIRST_arraySizeList, FOLLOW_arraySizeList));

		if (lookahead.belongsTo(FIRST_arraySizeList_RHS1)) {

			record.setVarStructure("array");
			
			boolean c1 = arraySize(record), c2 = arraySizeList(record);
			
			if (c1 && c2)
				pw.println("<arraySizeList> -> <arraySize> <arraySizeList>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_arraySizeList))
			pw.println("<arraySizeList> -> EPSILON");

		else
			error = false;

		return error;
		
	}
	
	public boolean statement(Table table) throws IOException { // <statement> -> <statementPRIME> | <variable> = <expr> ;
		
		error = skipErrors(union(FIRST_statement, FOLLOW_statement));

		if (lookahead.belongsTo(FIRST_statement_RHS1)) {

			boolean c1 = statementPRIME(table);
			
			if (c1)
				pw.println("<statement> -> <statementPRIME>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FIRST_statement_RHS2)) {

			boolean c1 = variable(table), c2 = match("DEF"), c3 = expr(table), c4 = match("SEMICOLON");
			
			if (c1 && c2 && c3 && c4)
				pw.println("<statement> -> <variable> = <expr> ;");

			else
				error = false;
			
		}
		
		else
			error = false;

		return error;
		
	}
	
	public boolean statementPRIME(Table table) throws IOException { // <statementPRIME> -> write ( <expr> ) ; | return ( <expr> ) ; | read ( <expr> ) ; | while ( <expr> ) do <statBlock> ; | if ( <expr> ) then <statBlock> else <statBlock> ;
		
		error = skipErrors(union(FIRST_statementPRIME, FOLLOW_statementPRIME));

		if (lookahead.belongsTo(FIRST_statementPRIME_RHS1)) {

			boolean c1 = match("IF"), c2 = match("OPAREN"), c3 = expr(table), c4 = match("CPAREN"), c5 = match("THEN"), c6 = statBlock(table), c7 = match("ELSE"), c8 = statBlock(table), c9 = match("SEMICOLON");
			
			if (c1 && c2 && c3 && c4 && c5 && c6 && c7 && c8 && c9)
				pw.println("<statementPRIME> -> if ( <expr> ) then <statBlock> else <statBlock> ;");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FIRST_statementPRIME_RHS2)) {

			boolean c1 = match("WHILE"), c2 = match("OPAREN"), c3 = expr(table), c4 = match("CPAREN"), c5 = match("DO"), c6 = statBlock(table), c7 = match("SEMICOLON");  
			
			if (c1 && c2 && c3 && c4 && c5 && c6 && c7)
				pw.println("<statementPRIME> -> while ( <expr> ) do <statBlock> ;");

			else
				error = false;
			
		}
		
		else if (lookahead.belongsTo(FIRST_statementPRIME_RHS3)) {

			boolean c1 = match("READ"), c2 = match("OPAREN"), c3 = variable(table), c4 = match("CPAREN"), c5 = match("SEMICOLON"); 
			
			if (c1 && c2 && c3 && c4 && c5)
				pw.println("<statementPRIME> -> read ( <variable> ) ;");

			else
				error = false;
			
		}
		
		else if (lookahead.belongsTo(FIRST_statementPRIME_RHS4)) {

			boolean c1 = match("RETURN"), c2 = match("OPAREN"), c3 = expr(table), c4 = match("CPAREN"), c5 = match("SEMICOLON");
			
			if (c1 && c2 && c3 && c4 && c5)
				pw.println("<statementPRIME> -> return ( <expr> ) ;");

			else
				error = false;
			
		}
		
		else if (lookahead.belongsTo(FIRST_statementPRIME_RHS5)) {

			boolean c1 = match("WRITE"), c2 = match("OPAREN"), c3 = expr(table), c4 = match("CPAREN"), c5 = match("SEMICOLON");
			
			if (c1 && c2 && c3 && c4 && c5)
				pw.println("<statementPRIME> -> write ( <expr> ) ;");

			else
				error = false;
			
		}
		
		else
			error = false;

		return error;
		
	}
	
public boolean statBlock(Table table) throws IOException { // <statBlock> -> { <statementList> } | <statement> | EPSILON
		
		error = skipErrors(union(FIRST_statBlock, FOLLOW_statBlock));

		if (lookahead.belongsTo(FIRST_statBlock_RHS1)) {

			boolean c1 = statement(table);
			
			if (c1)
				pw.println("<statBlock> -> <statement>");

			else
				error = false;

		}
		
		if (lookahead.belongsTo(FIRST_statBlock_RHS2)) {
			
			boolean c1 = match("OBRACE"), c2 = statementList(table), c3 = match("CBRACE");
			
			if (c1 && c2 && c3)
				pw.println("<statBlock> -> { <statementList> }");

			else
				error = false;

		}
		
		else if (lookahead.belongsTo(FOLLOW_statBlock))
			pw.println("<statBlock> -> EPSILON");

		else
			error = false;

		return error;
		
	}
	
	public boolean statementList(Table table) throws IOException { // <statementList> -> <statement> <statementList> | EPSILON
	
		error = skipErrors(union(FIRST_statementList, FOLLOW_statementList));

		if (lookahead.belongsTo(FIRST_statementList_RHS1)) {

				boolean c1 = statement(table), c2 = statementList(table);
				
			if (c1 && c2)
				pw.println("<statementList> -> <statement> <statementList>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_statementList))
			pw.println("<statementList> -> EPSILON");

		else
			error = false;

		return error;
		
	}
	
	public boolean expr(Table table) throws IOException { // <expr> -> <arithExpr> <exprPRIME>
		
		error = skipErrors(union(FIRST_expr, FOLLOW_expr));

		if (lookahead.belongsTo(FIRST_expr_RHS1)) {

			boolean c1 = arithExpr(table), c2 = exprPRIME(table);
			
			if (c1 && c2)
				pw.println("<expr> -> <arithExpr> <exprPRIME>");

			else
				error = false;

		}
		
		else
			error = false;

		return error;
		
	}
	
	public boolean exprPRIME(Table table) throws IOException { // <exprPRIME> -> <relOp> <arithExpr> | EPSILON
	
		error = skipErrors(union(FIRST_exprPRIME, FOLLOW_exprPRIME));

		if (lookahead.belongsTo(FIRST_exprPRIME_RHS1)) {

			boolean c1 = relOp(), c2 = arithExpr(table);
			
			if (c1 && c2)
				pw.println("<exprPRIME> -> <relOp> <arithExpr>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_exprPRIME))
			pw.println("<exprPRIME> -> EPSILON");

		else
			error = false;

		return error;
		
	}
	
	public boolean arithExpr(Table table) throws IOException { // <arithExpr> -> <term> <arithExprPRIME>
		
		error = skipErrors(union(FIRST_arithExpr, FOLLOW_arithExpr));

		if (lookahead.belongsTo(FIRST_arithExpr_RHS1)) {

				boolean c1 = term(table), c2 = arithExprPRIME(table);
				
			if (c1 && c2)
				pw.println("<arithExpr> -> <term> <arithExprPRIME>");

			else
				error = false;

		}
		
		else
			error = false;

		return error;
		
	}

	public boolean arithExprPRIME(Table table) throws IOException { // <arithExprPRIME> -> <addOp> <term> <arithExprPRIME> | EPSILON
		
		error = skipErrors(union(FIRST_arithExprPRIME, FOLLOW_arithExprPRIME));

		if (lookahead.belongsTo(FIRST_arithExprPRIME_RHS1)) {
			
			boolean c1 = addOp(), c2 = term(table), c3 = arithExprPRIME(table);
			
			if (c1 && c2 && c3)
				pw.println("<arithExprPRIME> -> <addOp> <term> <arithExprPRIME>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_arithExprPRIME))
			pw.println("<arithExprPRIME> -> EPSILON");

		else
			error = false;

		return error;
		
	}

	public boolean sign() throws IOException { // <sign> -> + | -
		
		error = skipErrors(union(FIRST_sign, FOLLOW_sign));

		if (lookahead.belongsTo(FIRST_sign_RHS1)) {

			boolean c1 = match("PLUS");
			
			if (c1)
				pw.println("<sign> -> +");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FIRST_sign_RHS2)) {
			
			boolean c1 = match("MINUS");
			
			if (c1)
				pw.println("<sign> -> -");

			else
				error = false;
		
		}
		
		else
			error = false;
		
		return error;
		
	}
	
	public boolean term(Table table) throws IOException { // <term> -> <factor> <termPRIME>
		
		error = skipErrors(union(FIRST_term, FOLLOW_term));

		if (lookahead.belongsTo(FIRST_term_RHS1)) {

			boolean c1 = factor(table), c2 = termPRIME(table);
			
			if (c1 && c2)
				pw.println("<term> -> <factor> <termPRIME>");

			else
				error = false;

		}
		
		else
			error = false;

		return error;
		
	}
	
	public boolean termPRIME(Table table) throws IOException { // <termPRIME> -> <multOp> <factor> <termPRIME> | EPSILON
		
		error = skipErrors(union(FIRST_termPRIME, FOLLOW_termPRIME));

		if (lookahead.belongsTo(FIRST_termPRIME_RHS1)) {

			boolean c1 = multOp(), c2 = factor(table), c3 = termPRIME(table);
			
			if (c1 && c2 && c3)
				pw.println("<termPRIME> -> <multOp> <factor> <termPRIME>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_termPRIME))
			pw.println("<termPRIME> -> EPSILON");

		else
			error = false;

		return error;
		
	}

	public boolean factor(Table table) throws IOException { // <factor> -> <sign> <factor> | not <factor> | num | id <idnestList> <factorPrime>
		
		error = skipErrors(union(FIRST_factor, FOLLOW_factor));

		if (lookahead.belongsTo(FIRST_factor_RHS1)) {

			boolean c1 = match("OPAREN"), c2 = expr(table), c3 = match("CPAREN");
		
			if (c1 && c2 && c3)
				pw.println("<factor> -> ( <expr> )");

			else
				error = false;

		}
		
		else if (lookahead.belongsTo(FIRST_factor_RHS2)) {

			Record call = new Record();
			
			boolean c1 = match("ID"); 
			
			call.setName(lastLexeme);
			
			boolean c2 = indiceList(call, table); 
			
			lastIdnest = call;
			
			if(parsedComplete) {
				
				table.search(call);
				
			}
			
			boolean c3 = idnestList(call, table);
			
			boolean c4 = factorPRIME(table);
			
			if (c1 && c2 && c3 && c4)
				pw.println("<factor> -> id <indiceList> <idnestList> <factorPRIME>");

			else
				error = false;

		}
		
		else if (lookahead.belongsTo(FIRST_factor_RHS3)) {

			boolean c1 = match("NUM");
			
			if (c1)
				pw.println("<factor> -> num");

			else
				error = false;

		}
		
		else if (lookahead.belongsTo(FIRST_factor_RHS4)) {
			
			boolean c1 = match("NOT"), c2 = factor(table);
			
			if (c1 && c2)
				pw.println("<factor> -> not <factor>");

			else
				error = false;

		}
		
		else if (lookahead.belongsTo(FIRST_factor_RHS5)) {

			boolean c1 = sign(), c2 = factor(table);
			
			if (c1 && c2)
				pw.println("<factor> -> <sign> <factor>");

			else
				error = false;

		}
		
		else
			error = false;

		return error;
		
	}
	
	public boolean factorPRIME(Table table) throws IOException { // <factorPRIME> -> ( <aParams> ) | EPSILON
		
		error = skipErrors(union(FIRST_factorPRIME, FOLLOW_factorPRIME));

		if (lookahead.belongsTo(FIRST_factorPRIME_RHS1)) {
			
			lastIdnest.setStructure("function");
			
			if(parsedComplete) {
				
				table.search(lastIdnest);
				
			}
			
			boolean c1 = match("OPAREN"), c2 = aParams(lastIdnest, table), c3 = match("CPAREN");
			
			if (c1 && c2 && c3)
				pw.println("<factorPRIME> -> ( <aParams> )");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_factorPRIME)) {
			
			if(parsedComplete) {
				
				table.search(lastIdnest);
				
			}
			
			lastIdnest = null;
			
			pw.println("<factorPRIME> -> EPSILON");
		}
		
		else
			error = false;

		return error;
		
	}
	
	public boolean idnestList(Record call, Table table) throws IOException { // <idnestList> -> . id <indiceList> <idnestList>
		
		Table newScope = new Table();
		
		error = skipErrors(union(FIRST_idnestList, FOLLOW_idnestList));

		if (lookahead.belongsTo(FIRST_idnestList_RHS1)) {

			boolean c1 = match("DOT");
			
			if (parsedComplete && table.search(call)) {
			
				newScope = table.find(call).getLocal();

			}
			
			Record newCall = new Record();
			
			boolean c2 = match("ID"); 
			
			newCall.setName(lastLexeme);
			
			boolean c3 = indiceList(newCall, newScope);
			
			lastIdnest = newCall;
			
			boolean c4 = idnestList(newCall, newScope);
			
			if (c1 && c2 && c3 && c4)
				pw.println("<idnestList> -> . id <indiceList> <idnestList>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_idnestList))
			pw.println("<idnestList> -> EPSILON");

		else
			error = false;

		return error;
		
	}
	
	//Same as idnestList but handles calls differently
	public boolean idnestListPRIME(Record call, Table table) throws IOException { // <idnestList> -> . id <indiceList> <idnestList>
		
		Table newScope = new Table();
		
		error = skipErrors(union(FIRST_idnestList, FOLLOW_idnestList));

		if (lookahead.belongsTo(FIRST_idnestList_RHS1)) {

			boolean c1 = match("DOT");
			
			if (parsedComplete && table.search(call)) {
			
				newScope = table.find(call).getLocal();

			}
			
			Record newCall = new Record();
			
			boolean c2 = match("ID"); 
			
			newCall.setName(lastLexeme);
			
			boolean c3 = indiceList(newCall, newScope);
			
			if (parsedComplete) {
				
				newScope.search(newCall);
				
			}
			
			boolean c4 = idnestListPRIME(newCall, newScope);
			
			if (c1 && c2 && c3 && c4)
				pw.println("<idnestListPRIME> -> . id <indiceList> <idnestListPRIME>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_idnestList))
			pw.println("<idnestList> -> EPSILON");

		else
			error = false;

		return error;
		
	}
	
	public boolean variable(Table table) throws IOException { // <variable> -> id <idnestListPRIME>
		
		error = skipErrors(union(FIRST_variable, FOLLOW_variable));

		if (lookahead.belongsTo(FIRST_variable_RHS1)) {
			
			Record call = new Record();
			
			boolean c1 = match("ID"); 
			
			call.setName(lastLexeme);
			
			boolean c2 = idnestListPRIME(call, table);
			
			if (c1 && c2)
				pw.println("<variable> -> id <idnestListPRIME>");

			else
				error = false;

		}

		else
			error = false;

		return error;
		
	}
	
	public boolean indiceList(Record call, Table table) throws IOException { // <indiceList> -> <indice> <indiceList> | EPSILON
		
		error = skipErrors(union(FIRST_indiceList, FOLLOW_indiceList));

		if (lookahead.belongsTo(FIRST_indiceList_RHS1)) {
			
			call.setVarStructure("array");
			
			boolean c1 = indice(call, table), c2 = indiceList(call, table);
			
			if (c1 && c2)
				pw.println("<indiceList> -> <indice> <indiceList>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_indiceList))
			pw.println("<indiceList> -> EPSILON");

		else
			error = false;

		return error;
		
	}
	
	public boolean indice(Record call, Table table) throws IOException { // <indice> -> [ <arithExpr> ]
		
		error = skipErrors(union(FIRST_indice, FOLLOW_indice));

		if (lookahead.belongsTo(FIRST_indice_RHS1)) {
			
			boolean c1 = match("OBRACKET"), c2 = arithExpr(table), c3 = match("CBRACKET");
			
			if (c1 && c2 && c3) {
				call.getDimension().add(0); //Just to increment size
				pw.println("<indice> -> [ <arithExpr> ]");
			}
			else
				error = false;

		}
		
		else
			error = false;

		return error;
		
	}
	
	public boolean arraySize(Record record) throws IOException { // <arraySize> -> [ int ]
		
		error = skipErrors(union(FIRST_arraySize, FOLLOW_arraySize));

		if (lookahead.belongsTo(FIRST_arraySize_RHS1)) {

			boolean c1 = match("OBRACKET"); 
			boolean c2 = match("INT");
			
			record.getDimension().add(Integer.parseInt(lastLexeme));
			
			boolean c3 = match("CBRACKET");
			
			if (c1 && c2 && c3)
				pw.println("<arraySize> -> [ int ]");
				
			else
				error = false;

		}
		
		else
			error = false;

		return error;
		
	}
	
	public boolean fParams(Record record, Table table) throws IOException { // <fParams> -> <type> id <arraySizeList> <fParamsTailList> | EPSILON
		
		Record newRecord = new Record();
		
		newRecord.setVarKind("parameter");
		
		error = skipErrors(union(FIRST_fParams, FOLLOW_fParams));

		if (lookahead.belongsTo(FIRST_fParams_RHS1)) {
			
			boolean c1 = type(newRecord), c2 = match("ID"); 
			
			newRecord.setName(lastLexeme);
			record.getParams().add(newRecord.getType());
			
			boolean c3 = arraySizeList(newRecord), c4 = fParamsTailList(record, table);
			
			if (c1 && c2 && c3 && c4) {
				table.insert(newRecord, parsedComplete, false);
				pw.println("<fParams> -> <type> id <arraySizeList> <fParamsTailList>");
			}
			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_fParams))
			pw.println("<fParams> -> EPSILON");

		else
			error = false;

		return error;
		
	}
	
	public boolean fParamsTailList(Record record, Table table) throws IOException { // <fParamsTailList> -> <fParamsTail> <fParamsTailList> | EPSILON
		
		error = skipErrors(union(FIRST_fParamsTailList, FOLLOW_fParamsTailList));

		if (lookahead.belongsTo(FIRST_fParamsTailList_RHS1)) {

			boolean c1 = fParamsTail(record, table), c2 = fParamsTailList(record, table);
			
			if (c1 && c2)
				pw.println("<fParamsTailList> -> <fParamsTail> <fParamsTailList>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_fParamsTailList))
			pw.println("<fParamsTailList> -> EPSILON");

		else
			error = false;

		return error;
		
	}
	
	public boolean aParams(Record function, Table table) throws IOException { // <aParams> -> <expr> <aParamsTailList> | EPSILON
		
		error = skipErrors(union(FIRST_aParams, FOLLOW_aParams));

		if (lookahead.belongsTo(FIRST_aParams_RHS1)) {

			boolean c1 = expr(table);
			
			function.getParams().add("");
			
			boolean c2 = aParamsTailList(function, table);
			
			if (c1 && c2)
				pw.println("<aParams> -> <expr> <aParamsTailList>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_aParams))
			pw.println("<aParams> -> EPSILON");

		else
			error = false;

		return error;
		
	}
	
	public boolean aParamsTailList(Record function, Table table) throws IOException { // <aParamsTailList> -> <aParamsTail> <aParamsTailList> | EPSILON
		
		error = skipErrors(union(FIRST_aParamsTailList, FOLLOW_aParamsTailList));

		if (lookahead.belongsTo(FIRST_aParamsTailList_RHS1)) {

			boolean c1 = aParamsTail(table); 
			
			function.getParams().add("");
			
			boolean c2 = aParamsTailList(function, table);
			
			if (c1 && c2)
				pw.println("<aParamsTailList> -> <aParamsTail> <aParamsTailList>");

			else
				error = false;

		}

		else if (lookahead.belongsTo(FOLLOW_aParamsTailList))
			pw.println("<aParamsTailList> -> EPSILON");

		else
			error = false;

		return error;
		
	}
	
	public boolean fParamsTail(Record record, Table table) throws IOException { // <fParamsTail> -> , <type> id <arraySizeList>
		
		Record newRecord = new Record();
		
		newRecord.setVarKind("parameter");
		
		error = skipErrors(union(FIRST_fParamsTail, FOLLOW_fParamsTail));

		if (lookahead.belongsTo(FIRST_fParamsTail_RHS1)) {

			boolean c1 = match("COMMA"), c2 = type(newRecord), c3 = match("ID"); 
			
			newRecord.setName(lastLexeme);
			record.getParams().add(newRecord.getType());
			
			boolean c4 = arraySizeList(newRecord);
			
			if (c1 && c2 && c3 && c4) {
				table.insert(newRecord, parsedComplete, false);
				pw.println("<fParamsTail> -> , <type> id <arraySizeList>");
			}
			else
				error = false;

		}
		
		else
			error = false;

		return error;
		
	}
	
	public boolean aParamsTail(Table table) throws IOException { // <aParamsTail> -> , <expr>
		
		error = skipErrors(union(FIRST_aParamsTail, FOLLOW_aParamsTail));

		if (lookahead.belongsTo(FIRST_aParamsTail_RHS1)) {
			
			boolean c1 = match("COMMA"), c2 = expr(table);
			
			if (c1 && c2)
				pw.println("<aParamsTail> -> , <expr>");

			else
				error = false;

		}
		
		else
			error = false;

		return error;
		
	}
	
	public boolean relOp() throws IOException { // <relOp> -> < | = | > | <> | <= | == | >=
		
		error = skipErrors(union(FIRST_relOp, FOLLOW_relOp));

		if (lookahead.belongsTo(FIRST_relOp_RHS1)) {
			
			boolean c1 = match("LESS");
			
			if (c1)
				pw.println("<relOp> -> < <relOpPRIMEPRIME");

			else
				error = false;

		}
		
		else if (lookahead.belongsTo(FIRST_relOp_RHS2)) {

			boolean c1 = match(">");
			
			if (c1)
				pw.println("<relOp> -> >");

			else
				error = false;

		}
		
		else if (lookahead.belongsTo(FIRST_relOp_RHS3)) {

			boolean c1 = match("NEQ");
			
			if (c1)
				pw.println("<relOp> -> <>");

			else
				error = false;

		}
		
		else if (lookahead.belongsTo(FIRST_relOp_RHS4)) {

			boolean c1 = match("LEQ");
			
			if (c1)
				pw.println("<relOp> -> <=");

			else
				error = false;

		}
		
		else if (lookahead.belongsTo(FIRST_relOp_RHS5)) {
			
			boolean c1 = match("EQ");
			
			if (c1)
				pw.println("<relOp> -> ==");

			else
				error = false;

		}
		
		else if (lookahead.belongsTo(FIRST_relOp_RHS6)) {

			boolean c1 = match("GEQ");
			
			if (c1)
				pw.println("<relOp> -> >=");

			else
				error = false;

		}
		
		else
			error = false;

		return error;
		
	}
	
	public boolean addOp() throws IOException { // <addOp> -> + | - | or
		
		error = skipErrors(union(FIRST_addOp, FOLLOW_addOp));

		if (lookahead.belongsTo(FIRST_addOp_RHS1)) {

			boolean c1 = match("OR");
			
			if (c1)
				pw.println("<addOp> -> or");

			else
				error = false;

		}
		
		else if (lookahead.belongsTo(FIRST_addOp_RHS2)) {

			boolean c1 = match("MINUS");
			
			if (c1)
				pw.println("<addOp> -> -");

			else
				error = false;

		}
		
		else if (lookahead.belongsTo(FIRST_addOp_RHS3)) {
			
			boolean c1 = match("PLUS");
			
			if (c1)
				pw.println("<addOp> -> +");

			else
				error = false;

		}
		
		else
			error = false;

		return error;
		
	}
	
	public boolean multOp() throws IOException { // <multOp> -> * | / | and
		
		error = skipErrors(union(FIRST_multOp, FOLLOW_multOp));

		if (lookahead.belongsTo(FIRST_multOp_RHS1)) {

			boolean c1 = match("AND");
			
			if (c1)
				pw.println("<multOp> -> and");

			else
				error = false;

		}
		
		else if (lookahead.belongsTo(FIRST_multOp_RHS2)) {

			boolean c1 = match("FWDSLASH");
			
			if (c1)
				pw.println("<multOp> -> /");

			else
				error = false;

		}
		
		else if (lookahead.belongsTo(FIRST_multOp_RHS3)) {

			boolean c1 = match("ASTERISK");
			
			if (c1)
				pw.println("<multOp> -> *");

			else
				error = false;

		}
		
		else
			error = false;

		return error;
		
	}
	
}