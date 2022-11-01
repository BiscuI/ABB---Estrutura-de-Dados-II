package imd.visao;

import imd.modelo.*;

public class Visao {
	public static void main(String[] args) {
		Arvore abb = new Arvore();
		
		abb.adicionarNo(abb.getRaizArvore(), 50);
		abb.adicionarNo(abb.getRaizArvore(), 30);
		abb.adicionarNo(abb.getRaizArvore(), 20);
		abb.adicionarNo(abb.getRaizArvore(), 40);
		abb.adicionarNo(abb.getRaizArvore(), 70);
		abb.adicionarNo(abb.getRaizArvore(), 60);
		abb.adicionarNo(abb.getRaizArvore(), 80);
		abb.adicionarNo(abb.getRaizArvore(), 62);
		
		//abb.adicionarNo(abb.getRaizArvore(), 3);
		//No node = abb.buscaNo(abb.getRaizArvore(), 40);
		
		//abb.removerNo(abb.getRaizArvore(), 30);
		//System.out.println(node.getDireita().getConteudo());
		//System.out.println(node.getEsquerda().getConteudo());
		//System.out.println(abb.stringPreOrdem(abb.getRaizArvore()));
		
		/*Qtd de nós da árvore está OK. Implementar somatório dos nós à esquerda e direita;*/
		
		//abb.atualizaAltura(abb.getRaizArvore());
		
		
		Arvore.imprimeOrdemSimetrica(abb.getRaizArvore());
		//abb.enesimoElemento(4);
		System.out.println("");
		System.out.println("Nós na árvore: " +abb.getQtdDeNos());
		System.out.println("MEDIANA = " +abb.mediana());
		System.out.println("Altura raiz esquerda = " +abb.getRaizArvore().getAltura());
		abb.media(abb.getRaizArvore());
		
		
		abb.removerNo(abb.getRaizArvore(), 62);
		Arvore.imprimeOrdemSimetrica(abb.getRaizArvore());
		//abb.enesimoElemento(4);
		
		/* CORRIGIR ATUALIZAÇÃO DE ALTURA AO REMOVER ELEMENTO*/
		System.out.println("");
		System.out.println("Nós na árvore: " +abb.getQtdDeNos());
		System.out.println("MEDIANA = " +abb.mediana());
		System.out.println("Altura raiz esquerda = " +abb.getRaizArvore().getAltura());
		
		abb.media(abb.getRaizArvore());
	}
}