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

import java.util.Queue;
import java.util.LinkedList;

public class BinaryTree {
	
	private Node root;
	
	public BinaryTree() {
		this(null);
	}
	
	public BinaryTree(Node root) {
		this.root = root;
	}

	public Node getRoot() { return root; }

	public void setRoot(Node root) { this.root = root; }
	
	public boolean isEmpty() { return(this.root == null); } // raiz nula -> arvore vazia
	
	public int getDegree() { return getDegree(root); }
	
	public int getDegree(Node root) { // compara recursivamente cada nó para descobrir o de maior grau
			
		if(root == null) {
			return 0;
		}else {
			
			int currentCount = root.getDegree();
			
			int leftDegree = getDegree(root.getLeft());
			int rightDegree = getDegree(root.getRight());
			
			return Math.max(currentCount, Math.max(leftDegree, rightDegree));
			
		}
		
	}
	
	public int getHeight() { return this.root.getHeight(); } // devolve a altura da raiz (altura da arvore)
	
	public void inOrder() { inOrder(root); }
	
	private void inOrder(Node root) {// esquerda, raiz e direita
		if(root != null) {
			inOrder(root.getLeft());
			System.out.println(root);
			inOrder(root.getRight());
		}
	}
	
	public void preOrder() { preOrder(root); }
	
	private void preOrder(Node root) {// raiz, esquerda e direita
		if(root != null) {
			System.out.println(root);
			preOrder(root.getLeft());
			preOrder(root.getRight());
		}

	}
	
	public void postOrder() { postOrder(root); }
	
	private void postOrder(Node root) {//esquerda, direita e raiz
		if(root != null) {
			postOrder(root.getLeft());
			postOrder(root.getRight());
			System.out.println(root);
		}
	}
	
	public void levelOrder() { levelOrder(root); }
	
	private void levelOrder(Node root) {//utiliza uma aplicação da pesquisa em largura para imprimir os nós da arvore em nível
		//basicamente cria uma fila que define a prioridade que os nós devem ser impressos a medida que são adicionados
		if(root != null) {
		
			Queue<Node> fila = new LinkedList<>();
			fila.add(root);
			
			while(!fila.isEmpty()) {
				Node current = fila.poll();
				System.out.println(current);
				
				if(current.getLeft() != null) {
					fila.add(current.getLeft());
				}
				if(current.getRight() != null) {
					fila.add(current.getRight());
				}
			}
		}
	}

}