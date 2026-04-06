package main.computador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Computador {
	public int escolherJogada (int[][] opcoes, HashMap<String, ArrayList<Integer>> pontuacao) {
		Random numQualquer = new Random();
		ArrayList<Integer> jogadasPossiveis = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
				
		//Linhas para "descompactar" as informações do HashMap
		ArrayList<ArrayList<Integer>> escolhas = new ArrayList<>();
		for (String jogador : pontuacao.keySet()) {
			escolhas.add(pontuacao.get(jogador));
			jogadasPossiveis.removeAll(pontuacao.get(jogador));
		}
		
		//Laço que irá verificar até o computador escolher um número
		ArrayList<Integer> possibilidades = new ArrayList<>();
		for (ArrayList<Integer> jogador : escolhas) {
			if(!jogador.isEmpty()) {
				for (int a : jogador) {
					for (int b : jogador ) {
						if (a != b) {
							int soma = a + b;
							int alvo = 15 - soma;
							
							if(alvo >= 1 && alvo <= 9 && !possibilidades.contains(alvo)) {
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
		return jogadasPossiveis.get(numQualquer.nextInt(jogadasPossiveis.size()));
	};
}
