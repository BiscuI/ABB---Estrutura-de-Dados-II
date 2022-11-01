package imd.visao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import imd.modelo.Arvore;

public class Visao {
	public static void main(String[] args) {
		Arvore abb = new Arvore();
		Path caminho = Paths.get("C:/Users/b202/Documents/arquivoEntrada.txt");
		List<Integer> entrada = new ArrayList<Integer>();

		try {
			entrada = Arrays.asList(Files.readAllLines(caminho).get(0).split(" ")).stream()
					.map((num) -> Integer.parseInt(num)).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(entrada );
		
		for(Integer i : entrada) {
			abb.adicionarNo(abb.getRaizArvore(), i);
		}
		
		Arvore.imprimeOrdemSimetrica(abb.getRaizArvore());

		// abb.adicionarNo(abb.getRaizArvore(), 3);
		// No node = abb.buscaNo(abb.getRaizArvore(), 40);

		// abb.removerNo(abb.getRaizArvore(), 30);
		// System.out.println(node.getDireita().getConteudo());
		// System.out.println(node.getEsquerda().getConteudo());
		// System.out.println(abb.stringPreOrdem(abb.getRaizArvore()));

		/*
		 * Qtd de nós da árvore está OK. Implementar somatório dos nós à esquerda e
		 * direita;
		 */

		// abb.atualizaAltura(abb.getRaizArvore());

		/*
		 * Arvore.imprimeOrdemSimetrica(abb.getRaizArvore()); abb.enesimoElemento(4);
		 * System.out.println(""); System.out.println("Nós na árvore: "
		 * +abb.getQtdDeNos()); System.out.println("MEDIANA = " +abb.mediana());
		 * System.out.println("Altura raiz esquerda = "
		 * +abb.getRaizArvore().getAltura()); abb.media(abb.getRaizArvore());
		 * System.out.println(""); System.out.println(abb.ehCheia(abb.getRaizArvore()));
		 * 
		 * abb.removerNo(abb.getRaizArvore(), 62);
		 * Arvore.imprimeOrdemSimetrica(abb.getRaizArvore()); abb.enesimoElemento(4);
		 * 
		 * CORRIGIR ATUALIZAÇÃO DE ALTURA AO REMOVER ELEMENTO System.out.println("");
		 * System.out.println("Nós na árvore: " +abb.getQtdDeNos());
		 * System.out.println("MEDIANA = " +abb.mediana());
		 * System.out.println("Altura raiz esquerda = "
		 * +abb.getRaizArvore().getAltura()); System.out.println("");
		 * System.out.println(abb.ehCheia(abb.getRaizArvore()));
		 * 
		 * abb.media(abb.getRaizArvore());
		 */
	}
}