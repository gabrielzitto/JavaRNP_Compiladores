import java.io.*;  
import java.util.*;  

import java.util.Scanner;
import java.util.Stack;

public class javaRPN {
	
	private static Scanner scanner = new Scanner(System.in);
    
    private static Stack<Integer> buffer = new Stack<>();


	public static void main(String[] args) {
        int current=0;
		
		try (FileInputStream fstream = new FileInputStream("Calc1.stk")){
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
            if (isInt(strLine)) {
                buffer.push(Integer.parseInt(strLine));
            } else if (isOperador(strLine)) {
                current = operadores(strLine, buffer);
                
                buffer.push(current);
            }
        
        
		}} catch (IOException e) {
            e.printStackTrace();
        }

		//Close the input stream
		
        
        
        System.out.println(String.valueOf(current));
		
		
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
