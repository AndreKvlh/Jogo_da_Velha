package main.jogo;
import main.tabuleiro.*;
import main.computador.Computador;
import java.util.ArrayList;
import java.util.HashMap;

public class Jogo {
	public static final String JOGADOR_1 = "Jogador 1";
	public static final String JOGADOR_2 = "Jogador 2";
	private Computador computador = new Computador();
	
	//Tabuleiro em quadrado mágico que servirá para calcular o estado
	//de vitória bem como configurar a IA do jogo
	private int[][] quadradoMagico = {
			{4,9,2},
			{3,5,7},
			{8,1,6}
	};
	
	//Tabuleiro que servirá para o jogador selecionar a opção para jogar
	private int[][] opcoesTabuleiro = {
			{1,2,3},
			{4,5,6},
			{7,8,9}
	};
	
	//HashMap feita para guardar as opções escolhidas pelos jogadores
	HashMap<String, ArrayList<Integer>> escolhas = new HashMap<>();
	
	//Foi necessário criar um construtor para adicionar os jogadores
	public Jogo() {
		this.escolhas.put(JOGADOR_1, new ArrayList<>());
		this.escolhas.put(JOGADOR_2, new ArrayList<>());
	};
	
	public void exibirOpcoes() {
		System.out.println("Você é o X. Selecione um dos \nnúmeros abaixo para fazer sua jogada:");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.opcoesTabuleiro[i][j] != 0) System.out.printf("%d", this.opcoesTabuleiro[i][j]);
				else System.out.print(" ");
				if (j < 2) System.out.print("|");
			}
			System.out.println("");
			if (i < 2) System.out.println("-----");
		}
	}
	
	public void realizarJogada(Tabuleiro tabuleiro, int opcao, char peca, String jogador) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.opcoesTabuleiro[i][j] == opcao) {
					tabuleiro.preencherTabuleiro(i,j,peca);
					this.escolhas.get(jogador).add(this.quadradoMagico[i][j]);
					this.quadradoMagico[i][j] = 0;
					this.opcoesTabuleiro[i][j] = 0;
				}
			}
		}
	}
	
	public void jogadaComputador(Tabuleiro tabuleiro) {
		int jogada = computador.escolherJogada(opcoesTabuleiro, escolhas);
		this.realizarJogada(tabuleiro, jogada, 'O', JOGADOR_2);
	}
}
