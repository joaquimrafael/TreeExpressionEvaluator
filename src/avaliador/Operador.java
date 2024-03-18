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

public class Operador extends Node{
	
	private char operador;
	
	public Operador() {
		super();
	}
	
	public Operador(char operador) {
		super();
		this.operador = operador;
	}
	
	public Operador(char operador, Node parent) {
		super(parent);
		this.operador = operador;
	}
	
	public Operador(char operador, Node left, Node right, Node parent) {
		super(left,right,parent);
		this.operador = operador;
	}
	
	public char getOperador() { return operador; }

	public void setOperador(char operador) { this.operador = operador; }

	@Override
	public float visitar() {
		switch (operador) {
			case '+':
				return left.visitar() + right.visitar();
			case '-':
				return left.visitar() - right.visitar();
			case '*':
				return left.visitar() * right.visitar();
			case '/':
				return left.visitar() / right.visitar(); 
			default:
				return Float.NaN;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.operador);
		return sb.toString();
	}

}
