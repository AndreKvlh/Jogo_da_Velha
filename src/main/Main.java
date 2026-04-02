package main;
import main.tabuleiro.Tabuleiro;
import main.jogo.Jogo;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		Tabuleiro tabuleiro = new Tabuleiro();
		Jogo jogo = new Jogo();
		tabuleiro.exibirTabuleiro();
		jogo.exibirOpcoes();
		int opcao = leitor.nextInt();
		jogo.realizarJogada(tabuleiro, opcao, 'X', jogo.JOGADOR_1);
		jogo.jogadaComputador(tabuleiro);
		tabuleiro.exibirTabuleiro();
		jogo.exibirOpcoes();
		leitor.close();
	}

}
