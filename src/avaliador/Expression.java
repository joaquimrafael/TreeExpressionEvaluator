/*	Aplicação 1
 * 
 * Por: Joaquim Rafael Mariano Prieto Pereira TIA: 42201731 RA: 10408805 
 * e Lucas Trebachetti Eiras TIA: 32236905 RA: 10401973
 * 
 * Estruturas de Dados II Professor Andre Kishimoto Sala 04G12
 * 
 * Consulta em: 
 * https://youtu.be/Gt2yBZAhsGM?si=WNOSZxaiCWmrA-sO
 * https://www.geeksforgeeks.org/binary-tree-data-structure/
 * https://www.ime.usp.br/~pf/mac0122-2003/aulas/bin-trees.html
 * https://www.devmedia.com.br/como-trabalhar-com-a-classe-stack/2966
 * https://docs.oracle.com/en/java/
 * https://github.com/joaquimrafael/Expression_Evaluator
 * 
 *  e materias de sala:
 *  Revisão POO com Java (André Kishimoto)
 *  Herança em Java (André Kishimoto)
 *  Árvores - fundamentos (André Kishimoto)
 */

package avaliador;

import java.util.Stack;

import java.util.List;
import java.util.ArrayList;

public class Expression {
	public static boolean evaluate(String s) {
		s = clearString(s);
		try {
			checkBrackets(s); checkContent(s); checkFloat(s); checkOperands(s);
		}catch(RuntimeException e) {
			throw new RuntimeException(e);
		}
		return(true);
	}
	
	private static boolean checkBrackets(String s) {
		Stack<Character> stack = new Stack<Character>();
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i) == '(') {
				stack.push(s.charAt(i));
			}else {
				if(s.charAt(i) == ')') {
					if(stack.isEmpty()) {
						throw new RuntimeException("Fechamendo de parenteses incorreto");
					}
					stack.pop();
				}
			}
		}
		return(stack.isEmpty());
	}
	
	public static String clearString(String s) {
		s = s.trim();
		s = s.replaceAll(" ", "");
		s = s.replaceAll("	", "");
		s = s.replaceAll("\t", "");
		return(s);
	}
	
	private static boolean checkContent(String s) {
		List<Character> l = new ArrayList<>();
		l.add('+');l.add('-');l.add('*');l.add('/');l.add('(');l.add(')');l.add('.');
		for(int i=0;i<s.length();i++) {
			Character c = s.charAt(i);
			if(!Character.isDigit(c) && !l.contains(c)) {
				throw new RuntimeException("O caractere "+ c + " é inválido!");
			}
		}
		return(true);
	}
	
    private static boolean checkFloat(String s) {
        String operands = "+-/*()";
        for(int i = 0;i < s.length();i++) {
            char current = s.charAt(i);
            if(current == '.') {
                if(i == 0 || i == s.length() - 1 || operands.contains(String.valueOf(s.charAt(i+1))) || operands.contains(String.valueOf(s.charAt(i+1)))) {
                    throw new RuntimeException("Existe número Float inválido!");
                }
            }
        }
        return(true);
    }
    
    public static String conversionPolishNotation(String expression) {
        Stack<Character> s = new Stack<>();
        String operands = "+-*/^";
        StringBuilder output = new StringBuilder();
        for (int i=0;i<expression.length();i++) {
            if (Character.isDigit(expression.charAt(i))) {
            	if(i < expression.length() - 1 && expression.charAt(i + 1) == '.') {
            		int j = i+2;
            		while(j < expression.length() && (Character.isDigit(expression.charAt(j)) || expression.charAt(j) == '.')) {
            			j++;
            		}
            		String substring = expression.substring(i,j);
            		output.append(substring);
            		i = j-1;
            	}else if(Character.isDigit(expression.charAt(i))){
            		output.append(expression.charAt(i));
            	}
            } else if(operands.contains(String.valueOf(expression.charAt(i)))){
                while(!s.isEmpty() && (precedence(expression.charAt(i))<=precedence(s.peek()))){
                    output.append(s.pop());
                }
                s.push(expression.charAt(i));
            } else if(expression.charAt(i) == '(') {
                s.push(expression.charAt(i));
            } else if(expression.charAt(i) == ')') {
                while(!s.isEmpty() && s.peek() !='('){
                    output.append(s.pop());
                }
                if(!s.isEmpty() && s.peek() =='('){
                    s.pop();
                }
            }
        }
        while(!s.isEmpty()){
            output.append(s.pop());
        }
        return(output.toString());
    }
    
    private static int precedence(char operator) {
    	if (operator == '*' || operator == '/') {
            return 2;
        }else if (operator == '+' || operator == '-') {
            return 1;
        }else {
            return 0;
        }
    }
    
    private static int precedence(String operator) {
    	if (operator.equals("*") || operator.equals("/")) {
            return 2;
        }else if (operator.equals("+") || operator.equals("-")) {
            return 1;
        }else {
            return 0;
        }
    }
    
    private static String[] tokenizer(String s) {
    	List<String> l = new ArrayList<String>();
    	String especial = "+-*/()";
    	for(int i=0;i<s.length();i++) {
    		if(especial.indexOf(s.charAt(i))==-1) {
    			int startIndex = i;
    			while(i<s.length() && especial.indexOf(s.charAt(i))==-1) {
    				i++;
    			}
    			int stopIndex = i;
    			l.add(s.substring(startIndex,stopIndex));
    			i--;
    		}else {
    				l.add(String.valueOf(s.charAt(i)));
    		}
    	}
    	String[] lstring = l.toArray(new String[0]);
    	return(lstring);
    }
	
    public static String[] tokenizerPostOrder(String s) {
        String[] token = tokenizer(s);
        Stack<String> operatorStack = new Stack<>();
        List<String> postfixList = new ArrayList<>();
        String operands = "+-*/"; 
        
        for (String t : token) {
            if (isNumber(t)) {
                postfixList.add(t);
            } else if (operands.contains(t)) {
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(t)) {
                    postfixList.add(operatorStack.pop());
                }
                operatorStack.push(t);
            } else if (t.equals("(")) {
                operatorStack.push(t);
            } else if (t.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    postfixList.add(operatorStack.pop());
                }
                operatorStack.pop();
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixList.add(operatorStack.pop());
        }

        String[] postfixArray = new String[postfixList.size()];
        postfixList.toArray(postfixArray);
        return postfixArray;
    }
    
    private static boolean checkOperands(String s) {
    	String operands = "+-*/";
    	Stack<String> stack = new Stack<String>();
		String[] tokenPostOrder = tokenizerPostOrder(s);
    	for(int i=0;i<tokenPostOrder.length;i++) {
    		if(operands.indexOf(tokenPostOrder[i].charAt(0))==-1) {
    			stack.push(tokenPostOrder[i]);
    		}else {
    			if(stack.size()<2) {
    				return(false);
    			}else {
    				stack.pop();
    				stack.pop();
    				stack.push("R");
    			}
    		}
    	}
    	if(stack.size()!=1) {
    		throw new RuntimeException("Operador com erro de sintaxe!");
    	}
    	return(true);
    }
    
    public static boolean isNumber(String s) {
    	try {
    		Float.parseFloat(s);
    		return(true);
    	}catch(Exception e) {
    		return(false);
    	}
    }
}

