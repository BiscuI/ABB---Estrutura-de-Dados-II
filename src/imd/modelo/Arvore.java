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
			qtdDeNos++;
			atualizaAltura(raizArvore);
			return raizArvore;
		}

		// Se o valor do novo nó for menor que o do nó raiz(ou de comparação), ele irá
		// para a subarvore da esquerda.
		else if (raiz.getConteudo() > valorNovo) {
			if (raiz.getEsquerda() == null) {
				raiz.setEsquerda(new No(valorNovo));
				qtdDeNos++;
				atualizaAltura(raizArvore);
			} else {
				adicionarNo(raiz.getEsquerda(), valorNovo);
			}

			// Se o valor do novo nó for maior que o do nó raiz(ou de comparação), ele irá
			// para a subarvore da direita.
		} else if (raiz.getConteudo() < valorNovo) {
			if (raiz.getDireita() == null) {
				raiz.setDireita(new No(valorNovo));
				qtdDeNos++;
				atualizaAltura(raizArvore);
			} else {
				adicionarNo(raiz.getDireita(), valorNovo);
			}
		} else {
			System.out.println("O nó já existe na árvore!");
		}
		return raiz;
	}

	// #OK
	public No removerNo(No raiz, int valorExcluir) {
		// System.out.println(raiz.getConteudo());

		if (raizArvore == null) {
			System.out.println("A árvore está vazia!");
			return raizArvore;
		}

		// Se o valor da raiz parâmetro for igual ao valor a excluir, analisamos os
		// casos e executamos a remoção
		if (raiz.getConteudo() == valorExcluir) {
			// 1º Caso: O nó não possui nenhum filho
			if (raiz.getEsquerda() == null && raiz.getDireita() == null) {
				raiz = null;
				qtdDeNos--;
				atualizaAltura(raizArvore);
				return raiz;
				// 2º Caso: O nó possui apenas um filho(à direita ou esquerda)
			} else if (raiz.getEsquerda() == null) {
				// Se ele possui apenas filho na direita/esquerda, ele tem a referência trocada
				// para uma que aponta para seu filho.
				qtdDeNos--;
				atualizaAltura(raizArvore);
				return raiz.getDireita();
			} else if (raiz.getDireita() == null) {
				qtdDeNos--;
				atualizaAltura(raizArvore);
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

		// Se não for igual, significa que ele pode estar em alguma das sub-árvores. É
		// feita a chamada recursiva
		else if (raiz.getConteudo() > valorExcluir) {
			raiz.setEsquerda(removerNo(raiz.getEsquerda(), valorExcluir));
		} else if (raiz.getConteudo() < valorExcluir) {
			raiz.setDireita(removerNo(raiz.getDireita(), valorExcluir));
		}

		return raiz;
	}

	static int iteracoesSimetrica;

	// ITEM 1 -- OK
	public No enesimoElemento(int n) {
		iteracoesSimetrica = n;
		No node = new No();
		node = visitaSimetricaBuscaNo(raizArvore);
		// System.out.println("IT RETURN- " + node.getConteudo());
		return node;
	}

	// Função relativa ao item 1
	private No visitaSimetricaBuscaNo(No raiz) {
		No auxNo = new No();
		// System.out.println("INTER ---- " +iteracoesSimetrica);

		if (raiz != null && iteracoesSimetrica > 0) {
			auxNo = visitaSimetricaBuscaNo(raiz.getEsquerda());

			if (auxNo == null && iteracoesSimetrica > 0) {
				iteracoesSimetrica--;
				if (iteracoesSimetrica == 0) {
					// System.out.println("IT - " + raiz.getConteudo());
					return raiz;
				}
				// System.out.println(raiz.getConteudo() + " ");
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
		System.out.println("IT RETORNO ----" + retorno);
		return retorno;
	}

	// Função relativa ao item 2
	public int visitaSimetricaBuscaIndice(No raiz, int x) {
		int auxComparativo;
		System.out.println("IT ----- " + iteracoesSimetrica);

		if (raiz != null) {

			auxComparativo = visitaSimetricaBuscaIndice(raiz.getEsquerda(), x);

			if (raiz.getConteudo() == x) {
				return iteracoesSimetrica;
			}

			if (auxComparativo == 0) {
				iteracoesSimetrica++;

				System.out.println(raiz.getConteudo());

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
		No noMediana = new No();

		if (qtdDeNos % 2 == 0) {
			noMediana = enesimoElemento(qtdDeNos / 2);
		} else {
			noMediana = enesimoElemento((qtdDeNos / 2) + 1);
		}

		return noMediana.getConteudo();
	}

	// Item 5
	public boolean ehCheia() {

		return false;
	}

	// ITEM 7 -- OK
	public String stringPreOrdem(No raiz) {
		String retorno = "";
		if (raiz != null) {
			retorno += (raiz.getConteudo() + " ");
			retorno += stringPreOrdem(raiz.getEsquerda());
			retorno += stringPreOrdem(raiz.getDireita());
		}
		return retorno;
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
	public void atualizaAltura(No raiz) {
		if(raiz.getEsquerda()!=null) {
			atualizaAltura(raiz.getEsquerda());
		}
		
		if(raiz.getDireita()!=null) {
			atualizaAltura(raiz.getDireita());
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