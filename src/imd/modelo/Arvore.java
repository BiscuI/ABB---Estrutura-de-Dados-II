package imd.modelo;

public class Arvore {
	private No raizArvore;
	private int qtdDeNos;

	public Arvore() {
		raizArvore = null;
	}

	public Arvore(int valor) {
		No raiz = new No(valor);
		this.raizArvore = raiz;
		this.qtdDeNos = 0;
	}

	// #OK
	public No buscaNo(No raiz, int valorBusca) {

		if (raiz == null) {
			System.out.println("O nó não está na árvore");
			return null;
		} else if (raiz.getConteudo() > valorBusca) {
			return buscaNo(raiz.getEsquerda(), valorBusca);
		} else if (raiz.getConteudo() < valorBusca) {
			return buscaNo(raiz.getDireita(), valorBusca);
		} else {
			System.out.println("O nó foi encontrado!");
			return raiz;
		}
	}

	// #OK
	public No adicionarNo(No raiz, int valorNovo) {
		// se a raiz da árvore ainda for nula, ela é iniciada
		if (raizArvore == null) {
			raizArvore = new No(valorNovo);
			System.out.println(valorNovo + " INSERIDO COM SUCESSO!");
			qtdDeNos++;
			atualizaAltura(raizArvore, valorNovo);
			return raizArvore;
		}

		// Se o valor do novo nó for menor que o do nó raiz(ou de comparação), ele irá
		// para a subarvore da esquerda.
		else if (raiz.getConteudo() > valorNovo) {
			if (raiz.getEsquerda() == null) {
				raiz.setEsquerda(new No(valorNovo));
				qtdDeNos++;
				System.out.println(valorNovo + " INSERIDO COM SUCESSO!");
				atualizaAltura(raizArvore, valorNovo);
			} else {
				adicionarNo(raiz.getEsquerda(), valorNovo);
			}

			// Se o valor do novo nó for maior que o do nó raiz(ou de comparação), ele irá
			// para a subarvore da direita.
		} else if (raiz.getConteudo() < valorNovo) {
			if (raiz.getDireita() == null) {
				raiz.setDireita(new No(valorNovo));
				qtdDeNos++;
				System.out.println(valorNovo + " INSERIDO COM SUCESSO!");
				atualizaAltura(raizArvore, valorNovo);
			} else {
				adicionarNo(raiz.getDireita(), valorNovo);
			}
		} else {
			System.out.println(valorNovo + " já está na árvore, não pode ser inserido!");
		}
		return raiz;
	}

	// #OK
	public No removerNo(No raiz, int valorExcluir) {

		if (raizArvore == null) {
			System.out.println("A árvore está vazia!");
			return raizArvore;
		}
		
		//Se o nó não for nulo, executamos as verificações
		if(raiz != null) {
			
			// Se o valor da raiz parâmetro for igual ao valor a excluir, analisamos os
			// casos e executamos a remoção
			if (raiz.getConteudo() == valorExcluir) {
				// 1º Caso: O nó não possui nenhum filho
				if (raiz.getEsquerda() == null && raiz.getDireita() == null) {
					raiz = null;
					qtdDeNos--;
					System.out.println("VALOR REMOVIDO COM SUCESSO!");
					atualizaAltura(raizArvore, valorExcluir);
					return raiz;
					// 2º Caso: O nó possui apenas um filho(à direita ou esquerda)
				} else if (raiz.getEsquerda() == null) {
					// Se ele possui apenas filho na direita/esquerda, ele tem a referência trocada para uma que aponta para seu filho.
					System.out.println("VALOR REMOVIDO COM SUCESSO!");
					qtdDeNos--;
					atualizaAltura(raizArvore, valorExcluir);
					return raiz.getDireita();
				} else if (raiz.getDireita() == null) {
					System.out.println("VALOR REMOVIDO COM SUCESSO!");
					qtdDeNos--;
					atualizaAltura(raizArvore, valorExcluir);
					return raiz.getEsquerda();
	
					// 3º Caso: O nó possui dois filhos
				} else {
					No aux = raiz.getDireita();
	
					// Pegando o elemento mais à esquerda da sub-árvore da direita.
					while (aux.getEsquerda() != null) {
						aux = aux.getEsquerda();
					}
	
					raiz.setConteudo(aux.getConteudo());
					raiz.setDireita(removerNo(raiz.getDireita(), raiz.getConteudo()));
				}
				/*
				 * aux.setDireita(raiz.getDireita()); aux.setEsquerda(raiz.getEsquerda()); raiz
				 * = aux;
				 */
			}
	
			// Se não for igual, significa que ele pode estar em alguma das sub-árvores. É feita a chamada recursiva
			else if (raiz.getConteudo() > valorExcluir) {
				raiz.setEsquerda(removerNo(raiz.getEsquerda(), valorExcluir));
			} else if (raiz.getConteudo() < valorExcluir) {
				raiz.setDireita(removerNo(raiz.getDireita(), valorExcluir));
			}
		}
		
		// Se o nó for nulo, então o valor a ser removido não se encontra na árvore
		else {
			System.out.println("O valor " + valorExcluir + " não está na árvore, não pode ser removido!");
			return null;
		}
		return raiz;
	}

	static int iteracoesSimetrica;

	// ITEM 1 -- OK
	public No enesimoElemento(int n) {
		iteracoesSimetrica = n;
		No node = new No();
		node = visitaSimetricaBuscaNo(raizArvore);
		return node;
	}

	// Função relativa ao item 1
	private No visitaSimetricaBuscaNo(No raiz) {
		No auxNo = new No();

		if (raiz != null && iteracoesSimetrica > 0) {
			auxNo = visitaSimetricaBuscaNo(raiz.getEsquerda());

			if (auxNo == null && iteracoesSimetrica > 0) {
				iteracoesSimetrica--;
				if (iteracoesSimetrica == 0) {
					return raiz;
				}
				auxNo = visitaSimetricaBuscaNo(raiz.getDireita());

				if (auxNo != null) {
					return auxNo;
				}
			} else {
				return auxNo;
			}
		}
		return null;
	}

	// ITEM 2 --- OK
	public int posicao(int x) {
		iteracoesSimetrica = 1;
		int retorno = visitaSimetricaBuscaIndice(raizArvore, x);
		return retorno;
	}

	// Função relativa ao item 2
	public int visitaSimetricaBuscaIndice(No raiz, int x) {
		int auxComparativo;

		if (raiz != null) {

			auxComparativo = visitaSimetricaBuscaIndice(raiz.getEsquerda(), x);

			if (raiz.getConteudo() == x) {
				return iteracoesSimetrica;
			}

			if (auxComparativo == 0) {
				iteracoesSimetrica++;

				auxComparativo = visitaSimetricaBuscaIndice(raiz.getDireita(), x);

				if (auxComparativo != 0) {
					return auxComparativo;
				}
			} else {
				return auxComparativo;
			}
		}
		return 0;
	}

	// Item 3 --- OK
	public int mediana() {
		int mediana;

		if (qtdDeNos % 2 == 0) {
			mediana = enesimoElemento(qtdDeNos / 2).getConteudo();
			mediana += enesimoElemento((qtdDeNos / 2) + 1).getConteudo();
			mediana /= 2;
		} else {
			mediana = enesimoElemento((qtdDeNos / 2) + 1).getConteudo();
		}

		return mediana;
	}

	//Item 4 --- OK
	public double media(No raiz) {
		int sum = somatorioDosNos(raiz);
		double media = (double)sum/qtdDeNos;
		
		return media;
	}
	
	//Função relativa ao item 4
	public int somatorioDosNos(No raiz) {
		int conteudo = 0;
		
		if(raiz != null) {
			conteudo += somatorioDosNos(raiz.getEsquerda());
			conteudo += raiz.getConteudo();
			conteudo += somatorioDosNos(raiz.getDireita());
		}
		
		return conteudo;
	}
	
	// Item 5 --- OK
	public boolean ehCheia(No raiz) {
		boolean ehCheia = false;
		
		//Caso 1 - árvore ou sub-árvore é vazia
		if(raiz == null) {
			ehCheia = true;
		}
		
		//Caso 2 - Se o nó é uma folha, a árvore estará cheia se ele não tiver nenhum filho
		if(raiz.getEsquerda() == null && raiz.getDireita() == null) {
			ehCheia = true;
		}
		
		//Caso 3 - Se o nó possui dois filhos, a árvore estará cheia caso ambos os nós também sejam 'cheios'
		if(raiz.getEsquerda() != null && raiz.getDireita() != null) {
			ehCheia = ehCheia(raiz.getDireita()) && ehCheia(raiz.getEsquerda());
		}
		
		return ehCheia;
	}

	
	//Item 6 --
	public boolean ehCompleta(No raiz) {
		boolean verificador = true;
		
		if(raiz == null) {
			verificador = true;
		}
		
		if(raiz.getEsquerda() == null && raiz.getAltura()>2) {
			verificador = false;
		}
		if(raiz.getDireita() == null && raiz.getAltura()>2) {
			verificador = false;
		}
		
		if(raiz.getEsquerda() == null && raiz.getDireita() == null) {
			verificador = ehCompleta(raiz.getDireita()) && ehCheia(raiz.getEsquerda());
		}
		
		//System.err.println(raiz.getConteudo()+ "retorno = " +verificador);
		return verificador;
	}
	
	// Item 7 -- OK
	public String stringPreOrdem(No raiz) {
		String retorno = "";
		if (raiz != null) {
			retorno += (raiz.getConteudo() + " ");
			retorno += stringPreOrdem(raiz.getEsquerda());
			retorno += stringPreOrdem(raiz.getDireita());
		}
		return retorno;
	}
	
	// Item 8
	public void imprimeArvore(int s) {
		if(s == 1) {
			System.out.println("Impressão da árvore no Fomato 1:");
			imprimeFormatoUm(raizArvore, 0);
		}
		else {
			System.out.println("Impressão da árvore no Fomato 2:");
			imprimeFormatoDois(raizArvore);
		}
	}
	
	// Função relativa ao item 8
	public void imprimeFormatoUm(No raiz, int nivel) {
		for(int i = 0; i < nivel; i++) {
			System.out.print("\t");
		}
		System.out.println(raiz.getConteudo());
		
		if(raiz.getEsquerda()!=null) {
			imprimeFormatoUm(raiz.getEsquerda(), nivel + 1);
		}
		
		if(raiz.getDireita()!=null) {
			imprimeFormatoUm(raiz.getDireita(), nivel + 1);
		}
	}
	
	// Função relativa ao item 8
	public void imprimeFormatoDois(No raiz) {
		System.out.print("(");
		System.out.print(raiz.getConteudo());
		
		if(raiz.getEsquerda()!=null) {
			System.out.print(" ");
			imprimeFormatoDois(raiz.getEsquerda());
		}
		
		if(raiz.getDireita()!=null) {
			System.out.print(" ");
			imprimeFormatoDois(raiz.getDireita());
		}
		System.out.print(")");
	}

	// #OK
	public static void imprimeOrdemSimetrica(No raiz) {
		if (raiz != null) {
			imprimeOrdemSimetrica(raiz.getEsquerda());
			System.out.print(raiz.getConteudo() + " ");
			imprimeOrdemSimetrica(raiz.getDireita());
		}
	}
	
	//#OK
	public void atualizaAltura(No raiz, int valorNovo) {
		if(valorNovo < raiz.getConteudo()) {
			if(raiz.getEsquerda()!=null) {
				atualizaAltura(raiz.getEsquerda(), valorNovo);
			}
		}
		else if(valorNovo > raiz.getConteudo()) {
			if(raiz.getDireita()!=null) {
				atualizaAltura(raiz.getDireita(), valorNovo);
			}
		}

		calculaAlturaNo(raiz);
	}

	//#OK
	private void calculaAlturaNo(No raiz) {
		int alt1 = 0, alt2 = 0;
		
		if(raiz.getEsquerda() == null) {
			alt1 = 0;
		}else {
			alt1 = raiz.getEsquerda().getAltura();
		}
		
		if(raiz.getDireita() == null) {
			alt2 = 0;
		}else {
			alt2 = raiz.getDireita().getAltura();
		}
		
		if(alt1>alt2) {
			raiz.setAltura(alt1+1);
		}else {
			raiz.setAltura(alt2+1);
		}
	}

	public No getRaizArvore() {
		return raizArvore;
	}

	public void setRaizArvore(No raiz) {
		this.raizArvore = raiz;
	}

	public int getQtdDeNos() {
		return qtdDeNos;
	}

	public void setQtdDeNos(int qtdDeNos) {
		this.qtdDeNos = qtdDeNos;
	}

	public static int getIteracoesSimetrica() {
		return iteracoesSimetrica;
	}

	public static void setIteracoesSimetrica(int iteracoesSimetrica) {
		Arvore.iteracoesSimetrica = iteracoesSimetrica;
	}
}