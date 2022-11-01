package imd.modelo;

public class No {
	private int conteudo;
	private No direita;
	private No esquerda;
	private int altura;
	
	public No() {
		super();	
	}	
	
	public No(int conteudo) {
		super();
		this.conteudo = conteudo;
		this.direita = null;
		this.esquerda = null;
		this.altura = 1;
	}

	
	
	public int getConteudo() {
		return conteudo;
	}
	public void setConteudo(int conteudo) {
		this.conteudo = conteudo;
	}
	public No getDireita() {
		return direita;
	}
	public void setDireita(No direita) {
		this.direita = direita;
	}
	public No getEsquerda() {
		return esquerda;
	}
	public void setEsquerda(No esquerda) {
		this.esquerda = esquerda;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
}