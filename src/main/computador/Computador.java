package main.computador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Computador {
	public int escolherJogada (int[][] opcoes, HashMap<String, ArrayList<Integer>> pontuacao) {
		int linha = 0, coluna = 0;
		int escolhaFinal;
		boolean escolhido = false;
		Random numQualquer = new Random();
		
		//Linhas para "descompactar" as informações do HashMap
		ArrayList<ArrayList<Integer>> escolhas = new ArrayList<>();
		for (String jogador : pontuacao.keySet()) {
			escolhas.add(pontuacao.get(jogador));
		}
		
		//Laço que irá verificar até o computador escolher um número
		do {
			ArrayList<Integer> possibilidades = new ArrayList<>();
			for (ArrayList<Integer> jogador : escolhas) {
				if(!jogador.isEmpty()) {
					for (int a : jogador) {
						for (int b : jogador ) {
							if (a != b) {
								int soma = a + b;
								int alvo = 15 - soma;
								
								if(alvo >= 1 && alvo <= 9) {
									possibilidades.add(alvo);
								}
							}
						}
					}
				}
			}
			ArrayList<Integer> peneira = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					for (Integer numero : possibilidades) {
						if (numero == opcoes[i][j]) peneira.add(numero);
					}
				}
			}
			if (!peneira.isEmpty()) {
				if(peneira.size() > 1) {
					return peneira.get(numQualquer.nextInt(peneira.size()));
				}
				return peneira.get(0);
			}
			return numQualquer.nextInt(9) + 1;
		} while (!escolhido);
	};
}
