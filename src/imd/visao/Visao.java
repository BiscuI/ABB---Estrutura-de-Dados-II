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
		//Leitura dos arquivos de entrada
		Path caminhoEntrada = Paths.get("C:/users/b202/Documents/arquivoEntrada.txt");
		Path caminhoComandos = Paths.get("C:/users/b202/Documents/arquivoComandos.txt");
		List<String> comandos = new ArrayList<String>();
		List<Integer> entrada = new ArrayList<Integer>();

		try {
			entrada = Arrays.asList(Files.readAllLines(caminhoEntrada).get(0).split(" ")).stream()
					.map((num) -> Integer.parseInt(num)).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			comandos = Files.readAllLines(caminhoComandos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Instanciando a árvore com os valores recebidos no arquivo de entrada
		Arvore abb = new Arvore();
		
		for(Integer i : entrada) {
			abb.adicionarNo(abb.getRaizArvore(), i);
		}
		
		Arvore.imprimeOrdemSimetrica(abb.getRaizArvore());
		System.out.println("");
		
		System.out.println(comandos);
		
		System.err.println(abb.getRaizArvore().getDireita().getAltura());
		for(String s : comandos) {
			String subS[] = s.split(" ");
			
			switch(subS[0]) {
			case "INSIRA":
				System.out.println("------");
				abb.adicionarNo(abb.getRaizArvore(), Integer.parseInt(subS[1]));
				System.out.println("");
				System.out.println("Estado atual da árvore: ");
				Arvore.imprimeOrdemSimetrica(abb.getRaizArvore());
				System.out.println("");
				break;
			case "REMOVA":
				System.out.println("------");
				abb.removerNo(abb.getRaizArvore(), Integer.parseInt(subS[1]));
				System.out.println("");
				System.out.println("Estado atual da árvore: ");
				Arvore.imprimeOrdemSimetrica(abb.getRaizArvore());
				System.out.println("");
				break;
			case "BUSCAR":
				System.out.println("------");
				abb.buscaNo(abb.getRaizArvore(), Integer.parseInt(subS[1]));
				break;
			case "ENESIMO":
				System.out.println("------");
				System.out.println("O nó na posição " +subS[1]+" é: " +abb.enesimoElemento(Integer.parseInt(subS[1])).getConteudo());
				break;
			case "POSICAO":
				System.out.println("------");
				System.out.println("A posição ocupada pelo nó de valor " +subS[1]+" é: " +abb.posicao(Integer.parseInt(subS[1])));
				break;
			case "MEDIANA":
				System.out.println("------");
				System.out.println("A mediana da árvore é: " +abb.mediana());				
				break;
			case "MEDIA":
				System.out.println("------");
				System.out.println("A média dos valores da árvore é: " +abb.media(abb.getRaizArvore()));
				break;
			case "CHEIA":
				System.out.println("------");
				if(abb.ehCheia(abb.getRaizArvore())) {					
					System.out.println("A árvore é cheia!");
				}else {
					System.out.println("A árvore não é cheia!");
				}
				break;
			case "COMPLETA":
				System.out.println("------");
				if(abb.ehCompleta(abb.getRaizArvore())) {					
					System.out.println("A árvore é completa!");
				}else {
					System.out.println("A árvore não é completa!");
				}
				break;
			case "PREORDEM":
				System.out.println("------");
				System.out.println("Pré ordem: " +abb.stringPreOrdem(abb.getRaizArvore()));
				break;
			case "IMPRIMA":
				//IMPLEMENTAR ITEM 8
				break;
			}
		}
	}
}