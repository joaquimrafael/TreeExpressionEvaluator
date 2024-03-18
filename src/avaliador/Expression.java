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
		return(checkBrackets(sb) && checkContent(sb) && checkFloat(sb));
	}
	
	protected static boolean checkBrackets(StringBuilder sb) {
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
	
	protected static StringBuilder clearString(StringBuilder sb) {
		String s = sb.toString().trim();
		sb.setLength(0);
		sb.append(s);
		return(sb);
	}
	
	protected static boolean checkContent(StringBuilder sb) {
		List<Character> l = new ArrayList<>();
		l.add('+');l.add('-');l.add('*');l.add('/');l.add('(');l.add(')');l.add('.');
		for(int i=0;i<sb.length();i++) {
			Character c = sb.charAt(i);
			if(!c.isDigit(0) || !l.contains(c)) {
				return(false);
			}
		}
		return(true);
	}
	
	protected static boolean checkFloat(StringBuilder sb) {
		for(int i=0;i<sb.length();i++) {
			if(sb.charAt(i) == '.') {
				if(i!=0 && i!=sb.length()-1) {
					Character cBefore = sb.charAt(i-1);
					Character cAfter = sb.charAt(i+1);
					if(cBefore.isDigit(0) && cAfter.isDigit(0)) {
						return(true);
					}else {
						return(false);
					}
				}else {
					return(false);
				}
			}
		}
		return(true);
	}
	
	
}
