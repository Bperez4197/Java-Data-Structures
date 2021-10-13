
import java.util.Stack;
import java.util.Scanner;

public class PostfixEvaluator {
	private final static char ADD      = '+';
	private final static char SUBTRACT = '-';
	private final static char MULTIPLY = '*';
	private final static char DIVIDE   = '/';
	private final static char MODULUS = '%';
	private final static char POWER = '^';
	private final static char FACTORIAL = '!';
	private final static char UNARY = '~';
	private final static char LESSTHAN = '<';
	private final static char GREATERTHAN = '>';
	private final static char EQUALTO = '=';
	private final static char AND = '&';
	private final static char OR = '|';
	private final static char TERNARY = '?';
	
	private Stack<Integer> stack;
	
	public PostfixEvaluator() {
		stack = new Stack<Integer>();
	}
	
	public int evaluate(String expr) throws TooManyOperandsException{
		int op1, op2, op3, result = 0;
		String token;
		Scanner parser = new Scanner(expr);
		
		while(parser.hasNext()) {
			token = parser.next();
			
			if(isOperator(token)) {
				if(token.equals("~") || token.equals("!")) {
					if(stack.size() < 1) {
						throw new TooManyOperandsException("stack");
					}else {
						op2 = (stack.pop()).intValue();
						op1 = 0;
						result = evaluateSingleOperator(token.charAt(0), op1, op2);
						stack.push(result);
					}
				}else if(token.equals("?")){
					if(stack.size() < 3) {
						System.out.println("ERROR. Insufficient operands for ?.");
					}else {
						op3 = (stack.pop()).intValue();
						op2 = (stack.pop()).intValue();
						op1 = (stack.pop()).intValue();
						result = evaluateSingleOperator(token.charAt(0), op1, op2, op3);
						stack.push(result);
					}
				}else {
					if(stack.size() < 2) {
						throw new TooManyOperandsException("stack");
					}else {
						op2 = (stack.pop()).intValue();
						op1 = (stack.pop()).intValue();
						result = evaluateSingleOperator(token.charAt(0), op1, op2);
						stack.push(result);
					}
				}
				
			}else
				stack.push(Integer.parseInt(token));
		}
		parser.close();
		if(stack.size() < 1 || stack.size() > 1) {
			throw new TooManyOperandsException("stack");
		}else {
			return (stack.pop());
		}
	}
	
	private boolean isOperator(String token) {
		return ( token.equals("+") || token.equals("-") || token.equals("*") ||
				token.equals("/") || token.equals("%") || token.equals("^") || token.equals("!") || token.equals("~") ||
				token.equals("<") || token.equals(">") || token.equals("=") || token.equals("?") || token.equals("&") || 
				token.equals("|"));
	}
	
	private int evaluateSingleOperator(char operation, int op1, int op2){
		int result = 0;
		
		switch (operation) {
			case ADD: 
				result = op1 + op2;
				break;            
			case SUBTRACT:
				result = op1 - op2;
				break;
			case MULTIPLY:
				result = op1 * op2;
				break;
			case DIVIDE:
				result = op1 / op2;
				break;
			case MODULUS:
				result = op1 % op2;
				break;
			case POWER:
				result = (int) (Math.pow(op1,op2));
				break;
			case FACTORIAL:
				result = calcFactorial(op2);
				break;
			case UNARY:
				result = op2 * -1;
				break;
			case LESSTHAN:
				if(op1 < op2) {
					result = 1;
				}else {
					result = 0;
				}
				break;
			case GREATERTHAN:
				if(op1 > op2) {
					result = 1;
				}else {
					result = 0;
				}
				break;
			case EQUALTO:
				if(op1 == op2) {
					result = 1;
				}else {
					result = 0;
				}
				break;
			case AND:
				if(op1 > 0 && op2 > 0) {
					result = 1;
				}else {
					result = 0;
				}
				break;	
			case OR:
				if(op1 > 0 || op2 > 0) {
					result = 1;
				}else {
					result = 0;
				}
				break;	
        	}
		return result;
	}
	
	
	private int evaluateSingleOperator(char operation, int op1, int op2, int op3){
		int result;
		if(op1 > 0) {
			result = op2;
		}else {
			result = op3;
		}
		return result;
	}
	
	private int calcFactorial(int number) {
		int result = 1;
		if(number < 0) {
			System.out.println("Error. You tried to get the factorial of a negative number.");
		}else {
			for(int i = 1; i <= number; i++) {
				result *= i;
			}
		}
		
		return result;
	}
}
