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

import java.util.Scanner;
import java.util.Stack;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		BinaryTree tree = new BinaryTree();
		Boolean created = false;
		
		int option;
		
		StringBuilder s = new StringBuilder("(3+6)*(4-1)+5");
		List<String> l = Expression.tokenizer(s);
		String[] array = l.toArray(new String[l.size()]);
		String s2 = Expression.conversionPolishNotation(s);
		System.out.println(s2);
		System.out.println(Expression.evaluate(s));
		BinaryTree bt = createBinaryTree(s2, array);
		System.out.println(bt.getRoot().visitar());
		
		while(true) {
		
			System.out.println("\n-Avaliador de expressões matemáticas- \r\n"
				+ "1. Entrada da expressão aritmética na notação infixa.\r\n"
				+ "2. Criação da árvore binária de expressão aritmética.\r\n"
				+ "3. Exibição da árvore binária de expressão aritmética.\r\n"
				+ "4. Cálculo da expressão (realizando o percurso da árvore).\r\n"
				+ "5. Encerramento do programa.\r\n"
				+ "Digite a opção desejada:");
			System.out.println("Expressão atual: "+sb.toString());
			option = input.nextInt();
			
			switch(option) {
			case 1:
				Scanner read = new Scanner(System.in);
				System.out.println("Digite a expressão a ser avaliada: ");
				String expression = read.nextLine();
				sb.append(expression);
				created = true;
				break;
			case 2:
				if(!created) {
					System.out.println("Primeiro insira a expressão a ser avaliada!");
				}else {}
				break;	
			case 3:
				if(!created) {
					System.out.println("Primeiro insira a expressão a ser avaliada!");
				}else {
				System.out.println("Pré-ordem: ");
				tree.preOrder();
				System.out.println();
				
				System.out.println("Em ordem: ");
				tree.inOrder();
				System.out.println();
				
				System.out.println("Pós-ordem: ");
				tree.postOrder();
				System.out.println();
				}
				break;	
			case 4:
				if(!created) {
					System.out.println("Primeiro insira a expressão a ser avaliada!");
				}else {
					
				}
				break;
			case 5:
				System.out.println("Saindo...");
				input.close();
				return;
				
			default:
				System.out.println("Opção Inválida");
				break;
			}
		
		}
	}
	
	private static BinaryTree createBinaryTree(String st, String[] token) {
		String operators = "+-*/";
		Stack<Node> s = new Stack<Node>();
		BinaryTree bt = new BinaryTree();
		String[] tokenPostOrder = new String[st.length()];
		int index = 0;
	    for(int i = 0; i < st.length();i++) {
	        for(int j =i+1; j<=st.length();j++) {
	            String substring = st.substring(i,j);
	            for(int k=0; k<token.length;k++) {
	                if(substring.equals(token[k])) {
	                    tokenPostOrder[index++] = substring;
	                    token[k] = null;
	                    break;
	                }
	            }
	        }
	    }
	    
		for(int i=0;i<st.length();i++) {
			if(Expression.isNumber(tokenPostOrder[i])) {
				s.push(new Operando(Float.parseFloat(tokenPostOrder[i])));
			}else {
				if(operators.indexOf(tokenPostOrder[i])!=-1) {
					Node rightChild = s.pop();
					Node leftChild = s.pop();
					s.push(new Operador(st.charAt(i),leftChild,rightChild));
				}
			}
		}

		bt.setRoot(s.pop());
		return(bt);
	}
}
