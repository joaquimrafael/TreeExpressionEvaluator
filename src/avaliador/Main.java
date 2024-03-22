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

import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String expression = "";
		BinaryTree tree = new BinaryTree();
		Boolean created = false;
		Boolean createdBinaryT = false;
		
		int option;
		
		while(true) {
		
			System.out.println("\n-Avaliador de expressões matemáticas- \r\n"
				+ "1. Entrada da expressão aritmética na notação infixa.\r\n"
				+ "2. Criação da árvore binária de expressão aritmética.\r\n"
				+ "3. Exibição da árvore binária de expressão aritmética.\r\n"
				+ "4. Cálculo da expressão (realizando o percurso da árvore).\r\n"
				+ "5. Encerramento do programa.\r\n");
			System.out.println("Expressão atual em pós ordem: "+ Expression.conversionPolishNotation(expression));
			System.out.println("Digite a opção desejada: ");
			option = input.nextInt();
			
			switch(option) {
			case 1:
				while(true) {
					Scanner read = new Scanner(System.in);
					System.out.println("Digite a expressão a ser avaliada: ");
					expression = read.nextLine();
					
					try {
						Expression.evaluate(expression);
						created = true;
					}catch(RuntimeException e){
						expression = "";
						System.err.println(e);
						continue;
					}
				
					break;
				}
				break;
			case 2:
				if(!created) {
					System.out.println("Primeiro insira a expressão a ser avaliada!");
				}else {
					expression = Expression.clearString(expression);
					tree.setRoot(null);
					tree = createBinaryTree(expression);
					System.out.println("Árvore binária criada com sucesso!");
					createdBinaryT = true;
				}
				break;	
			case 3:
				if(!created || !createdBinaryT) {
					System.out.println("Primeiro insira a expressão a ser avaliada e criada a árvore partir dela!");
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
				if(!created || !createdBinaryT) {
					System.out.println("Primeiro insira a expressão a ser avaliada e criada a árvore partir dela!");
				}else {
					System.out.println("Resultado: "+ tree.getRoot().visitar()); 
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
	
	private static BinaryTree createBinaryTree(String s) {
		String operators = "+-*/";
		Stack<Node> stack = new Stack<Node>();
		BinaryTree bt = new BinaryTree();
		String[] tokenPostOrder = Expression.tokenizerPostOrder(s);
		for(int i=0;i<tokenPostOrder.length;i++) {
			if(Expression.isNumber(tokenPostOrder[i])) {
				stack.push(new Operando(Float.parseFloat(tokenPostOrder[i])));
			}else {
				if(operators.indexOf(tokenPostOrder[i])!=-1) {
					Node rightChild = stack.pop();
					Node leftChild = stack.pop();
					stack.push(new Operador(tokenPostOrder[i].charAt(0),leftChild,rightChild));
				}
			}
		}

		bt.setRoot(stack.pop());
		return(bt);
	}
}