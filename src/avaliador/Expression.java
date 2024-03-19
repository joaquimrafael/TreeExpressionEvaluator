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
 * https://codegym.cc/pt/groups/posts/pt.307.java-queue-interface-e-suas-implementacoes
 * https://github.com/egonSchiele/grokking_algorithms/tree/master/06_breadth-first_search/java/01_breadth_first_search/src
 * 
 *  e materias de sala:
 *  Revisão POO com Java (André Kishimoto)
 *  Herança em Java (André Kishimoto)
 *  Árvores - fundamentos (André Kishimoto)
 */

package avaliador;

import java.util.Stack;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class Expression {
	public static boolean evaluate(StringBuilder sb) {
		sb = clearString(sb);
		return(checkBrackets(sb) && checkContent(sb) && checkFloat(sb) && checkOperands(sb));
	}
	
	private static boolean checkBrackets(StringBuilder sb) {
		Stack<Character> s = new Stack<Character>();
		for(int i=0;i<sb.length();i++) {
			if(sb.charAt(i) == '(') {
				s.push(sb.charAt(i));
			}else {
				if(sb.charAt(i) == ')') {
					if(s.isEmpty()) {
						return(false);
					}
					s.pop();
				}
			}
		}
		return(s.isEmpty());
	}
	
	private static StringBuilder clearString(StringBuilder sb) {
		String s = sb.toString().trim();
		sb.setLength(0);
		sb.append(s);
		int length = sb.length();
		for(int i=0;i<length;i++) {
			if(sb.charAt(i)==' ' || sb.charAt(i) == '\t' || sb.charAt(i) ==  '	') {
				sb.deleteCharAt(i);
			}
		}
		return(sb);
	}
	
	private static boolean checkContent(StringBuilder sb) {
		List<Character> l = new ArrayList<>();
		l.add('+');l.add('-');l.add('*');l.add('/');l.add('(');l.add(')');l.add('.');
		for(int i=0;i<sb.length();i++) {
			Character c = sb.charAt(i);
			if(!Character.isDigit(c) || !l.contains(c)) {
				return(false);
			}
		}
		return(true);
	}
	
    private static boolean checkFloat(StringBuilder sb) {
        boolean foundDot = false;
        for(int i = 0;i < sb.length();i++) {
            char current = sb.charAt(i);
            if(current == '.') {
                if(foundDot || i == 0 || i == sb.length() - 1 || !Character.isDigit(sb.charAt(i - 1)) || !Character.isDigit(sb.charAt(i + 1))) {
                    return(false);
                }
                foundDot = true;
            }
        }
        return(true);
    }
    
    public static String conversionPolishNotation(StringBuilder expression) {
        Stack<Character> s = new Stack<>();
        String operands = "+-*/^";
        StringBuilder output = new StringBuilder();
        for (int i=0;i<expression.length();i++) {
            if (Character.isLetter(expression.charAt(i))) {
                output.append(expression.charAt(i));
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
	
    private static boolean checkOperands(StringBuilder sb) {
    	String operands = "+-*/";
    	Stack<Character> stack = new Stack<Character>();
    	String s = conversionPolishNotation(sb);
    	for(int i=0;i<s.length();i++) {
    		if(operands.indexOf(s.charAt(i))==-1) {
    			stack.push(s.charAt(i));
    		}else {
    			if(stack.size()<2) {
    				return(false);
    			}else {
    				stack.pop();
    				stack.pop();
    				stack.push('R');
    			}
    		}
    	}
    	return(stack.size()==1);
    }
    
    public static List<String> tokenizer(StringBuilder sb) {
    	List<String> l = new ArrayList<String>();
    	String especial = "+-*/()";
    	String s = sb.toString();
    	for(int i=0;i<s.length();i++) {
    		if(especial.indexOf(s.charAt(i))==-1) {
    			int startIndex = i;
    			while(especial.indexOf(s.charAt(i))==-1) {
    				i++;
    			}
    			int stopIndex = i;
    			l.add(s.substring(startIndex,stopIndex));
    		}else {
    			if(s.charAt(i) != '(' || s.charAt(i) != ')') {
    				l.add(String.valueOf(s.charAt(i)));
    			}
    		}
    	}
    	return(l);
    }
	
}
