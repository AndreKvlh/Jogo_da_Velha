package main.jogo;
import main.tabuleiro.*;
import main.computador.Computador;
import java.util.ArrayList;
import java.util.HashMap;

public class Jogo {
	public static final String[] JOGADORES = {"Jogador 1", "Jogador 2"};
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
		this.escolhas.put(JOGADORES[0], new ArrayList<>());
		this.escolhas.put(JOGADORES[1], new ArrayList<>());
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
		boolean escolhido = false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.opcoesTabuleiro[i][j] == opcao) {
					tabuleiro.preencherTabuleiro(i,j,peca);
					this.escolhas.get(jogador).add(this.quadradoMagico[i][j]);
					this.quadradoMagico[i][j] = 0;
					this.opcoesTabuleiro[i][j] = 0;
					escolhido = true;
					break;
				}
			}
			if (escolhido) break;
		}
	}
	
	public void jogadaComputador(Tabuleiro tabuleiro) {
		int jogada = computador.escolherJogada(quadradoMagico, escolhas);
		for (int i = 0; i < 3 ; i++) {
			for (int j = 0; j < 3; j++) {
				if (jogada == quadradoMagico[i][j]) this.realizarJogada(tabuleiro, opcoesTabuleiro[i][j], 'O', JOGADORES[1]);
			};
		};
	}
	
	//Função temporária que vai cair em desuso quando for para a 
	//interface gráfica
	public void limparConsole() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
	
	public boolean checarVitoria(String jogador) {
		boolean vitoria = false;
		boolean velha = true;
		for (int a : escolhas.get(jogador)) {
			for (int b : escolhas.get(jogador)) {
				for (int c : escolhas.get(jogador)) {
					if (a == b || b == c || a == c) {
						break;
					}
					int soma = a + b + c;
					if (soma == 15) {
						System.out.printf("%s venceu!", jogador);
						System.out.println();
						vitoria = true;
						return vitoria;
					}
				}
			}
		}
		
		/*if (!vitoria) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if(quadradoMagico[i][j] != 0) {
						velha = false;
						return velha;
					}
				}
			}
			if (velha) {
				System.out.println("Deu velha");
				return velha;
			}
		}*/
		return false;
	}
	
	public boolean deuVelha(boolean vitoria) {
		boolean velha = true;
		if (!vitoria) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if(quadradoMagico[i][j] != 0) {
						velha = false;
						return velha;
					}
				}
			}
			if (velha) {
				System.out.println("Deu velha");
				return velha;
			}
		}
		return false;
	}
	
	public int getOpcao(int linha, int coluna) {
		return opcoesTabuleiro[linha][coluna];
	}
}
