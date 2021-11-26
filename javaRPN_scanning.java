import java.io.*;  
import java.util.*;  

import java.util.Scanner;
import java.util.Stack;

public class javaRPN_scanning {
	
	private static Scanner scanner = new Scanner(System.in);
    
    private static Stack<Integer> buffer = new Stack<>();


	public static void main(String[] args) {
        int current=0;
		String strLine="";
		boolean error = false;
		
		
		try (FileInputStream fstream = new FileInputStream("Calc1.stk")){
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	
		while ((strLine = br.readLine()) != null)   {
            if (isInt(strLine)) {
                buffer.push(Integer.parseInt(strLine));
                Token value = new Token(TokenType.NUM,strLine);
                System.out.println(value.toString()); 
                
            } else if (isOperador(strLine)) {
                current = operadores(strLine, buffer);
                
                buffer.push(current);
                
                if(strLine.equals("-")){
                    Token value2 = new Token(TokenType.MINUS,strLine);
                    System.out.println(value2.toString());     
                }else if(strLine.equals("+")){
                    Token value2 = new Token(TokenType.PLUS,strLine);
                    System.out.println(value2.toString());     
                }
                else if(strLine.equals("/")){
                    Token value2 = new Token(TokenType.SLASH,strLine);
                    System.out.println(value2.toString());     
                }
                else if(strLine.equals("*")){
                    Token value2 = new Token(TokenType.STAR,strLine);
                    System.out.println(value2.toString());     
                }
                
            }else{
                error = true;
                break;
            }
        
        
		}} catch (IOException e) {
            e.printStackTrace();
        }

		
        if(error){
            System.out.println("Error: Unexpected character: "+strLine);    
        }else{
            
            System.out.println(String.valueOf(current));    
        }
        
        
		
		
    }
	
	public static int operadores(String operation, Stack<Integer> buffer) {
        int result = (buffer.empty()) ? 0 : buffer.pop();

        if (!buffer.empty()) {
            result = operando(operation, buffer.pop(), result);
        }

        return result;
    }


    public static int operando(String operation, int left, int right) {
        switch (operation) {
            case "+": return left + right;
            case "-": return left - right;
            case "*": return left * right;
            case "/": {
                if (right == 0) {
                    System.out.println("-- divisao por zero --");
                    return left;
                }
                return left / right;
            }
            default: return left;
        }
    }


    public static boolean isInt(String input) {
        if (input == null) return false;

        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static boolean isOperador(String input) {
        if (input == null) return false;

        return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
    }

}

class Token {

	public final TokenType type; // token type
	public final String lexeme; // token value

	public Token (TokenType type, String value) {
		this.type = type;
		this.lexeme = value;
	}

	@Override
	public String toString() {
		return "Token [type=" + this.type + ", lexeme=" + this.lexeme + "]";
	}
}

enum TokenType {

	// Literals.
	NUM,

	// Single-character tokens for operations.
	MINUS, PLUS, SLASH, STAR,
	
	EOF

}