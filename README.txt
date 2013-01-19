Designed and implemented a scanner for a programming language whose lexical specifications are given in the docs. 

The scanner identifies and outputs tokens (valid words and punctuation) in the source program.
Its output is a token that can thereafter be used by the syntactic analyzer to verify that the program is
syntactically valid. When called, the lexical analyzer extracts the next token from the source program. 

The lexical analyzer outputs a token even if the input does not form a correct program. 
The syntax of the language is specified in the docs. 

Appropriate test cases were used (and can be found here) to test for a wide variety of valid and invalid cases.


TO RUN:
Import the project into eclipse and run it.

OUTPUT:
Source code is written in "code.txt"
Errors are written to "errors.txt"
Tokens are written to "tokens.txt"
The derivation is written in "derivation.txt"
Tables for functions are printed in "tables.txt"

MOON code is generated in "moon.m"

NOTE: MOON code generation has been commented out.

Compiler features include full Lexical and Semantic parsing and table generation
but stops short of generating runnable code (for now).
