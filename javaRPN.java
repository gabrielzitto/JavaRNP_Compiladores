

import java.io.*;  
import java.util.*;  

import java.util.Scanner;
import java.util.Stack;

public class javaRPN {
	
	private static Scanner scanner = new Scanner(System.in);
    
    private static Stack<Integer> buffer = new Stack<>();


	public static void main(String[] args) {
        int current=0;
		
		try (FileInputStream fstream = new FileInputStream("/uploads/Calc1.stk")){
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
            System.out.println(strLine);
            if (isInteger(strLine)) {
                buffer.push(Integer.parseInt(strLine));
            } else if (isOperation(strLine)) {
                current = parseOperation(strLine, buffer);
                
                buffer.push(current);
            }
        
        
		}} catch (IOException e) {
            e.printStackTrace();
        }

		//Close the input stream
		
        
        
        System.out.println(String.valueOf(current));
		
		
    }
	
	public static int parseOperation(String operation, Stack<Integer> buffer) {
        int result = (buffer.empty()) ? 0 : buffer.pop();

        if (!buffer.empty()) {
            result = operate(operation, buffer.pop(), result);
        }

        return result;
    }


    public static int operate(String operation, int left, int right) {
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


    public static boolean isInteger(String input) {
        if (input == null) return false;

        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static boolean isOperation(String input) {
        if (input == null) return false;

        return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
    }

}
