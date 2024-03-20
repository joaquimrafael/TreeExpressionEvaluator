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
		for(int i=0;i<array.length;i++) {
			System.out.println(array[i]);
		}
		String s2 = Expression.conversionPolishNotation(s);
		System.out.println(s2);
		System.out.println(Expression.evaluate(s));
		
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
	
	private static BinaryTree createBinaryTree(StringBuilder sb, String[] token) {
		String operators = "+-*/";
		Stack<Node> s = new Stack<Node>();
		BinaryTree bt = new BinaryTree();
		boolean found = false;
		for(int i=0;i<sb.length();i++) {
			int j=i;
			while(j<token.length) {
				if(sb.substring(i, j+1).equals(token[i])) {
					found = true;
					break;
				}
				j++;
			}
			if(found) {
				if(Expression.isNumber(sb.substring(i, j+1))) {
					s.push(new Operando(Float.parseFloat(sb.substring(i, j+1))));
				}else {
					if(operators.indexOf(sb.substring(i, j+1))!=-1) {
						Node rightChild = s.pop();
						Node leftChild = s.pop();
						s.push(new Operador(sb.charAt(i),leftChild,rightChild));
					}
				}
				
			}
			token[i] = null;
			found = false;
			i = j+1;
		}
		bt.setRoot((Operador)s.pop());
		return(bt);
	}

}
