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

public abstract class Node {
	
	protected Node parent;
	protected Node left;
	protected Node right;
	
	
	public Node() { 
		this.parent = null;
		this.left = null;
		this.right = null;
	}
	
	public Node(Node parent) { 
		this.parent = parent;
		this.left = null;
		this.right = null;
	
	}
	
	public Node(Node left, Node right, Node parent) { 
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	
	public Node(Node left, Node right) { 
		this.left = left;
		this.right = right;
	}

	public Node getParent() { return parent; }

	public void setParent(Node parent) { this.parent = parent; }

	public Node getLeft() { return left; }

	public void setLeft(Node left) { this.left = left; }

	public Node getRight() { return right; }

	public void setRight(Node right) { this.right = right; }
	
	public boolean isRoot() {//se não tiver parente, é uma raiz
		return(this.parent == null);
	}
	
	public boolean isLeaf() {//se não possuir filhos, então é uma folha
		return(this.left == null && this.right == null);
	}
	
	public int getDegree() {// calcula o numero de filhos do nó
		int count = 0;
		
		if(this.left != null) {
			count++;
		}
		if(this.right != null) {
			count++;
		}
		
		return count;
	}
	
	public int getLevel() { return getLevel(this); }
	
	private int getLevel(Node node) {//percorre recursivamente do nó ate a raiz da arvore para saber o nivel
		if(node.parent == null) {
			return 0;
		}else {
		
			return 1 + getLevel(node.parent);
		}
	}
	
	public int getHeight() { return getHeight(this); }
	
	private int getHeight(Node node) { //percorre recursivamente ate encontrar o nó folha do maior caminho e então soma 1
		if(node == null) {
			return -1;
		}else {
			
			int leftHeight = getHeight(node.left);
			int rightHeight = getHeight(node.right);
			
			if(leftHeight > rightHeight) { return 1 + leftHeight;}
			else { return 1 + rightHeight; }
		}
		
	}
	@Override
	public String toString() {
		return null;
	}
	
	public float visitar() {
		return Float.NaN;
	}
	
}

