package main;
import main.tabuleiro.Tabuleiro;
import main.jogo.Jogo;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		Tabuleiro tabuleiro = new Tabuleiro();
		Jogo jogo = new Jogo();
		boolean haVitoria = false;
		tabuleiro.exibirTabuleiro();
		do {
			jogo.exibirOpcoes();
			int opcao = leitor.nextInt();
			for(String jogador : jogo.JOGADORES) {
				if (jogador != "Jogador 1") jogo.jogadaComputador(tabuleiro);
				else jogo.realizarJogada(tabuleiro, opcao, 'X', jogo.JOGADORES[0]);
				jogo.limparConsole();
				haVitoria = jogo.checarVitoria(jogador);
				if (haVitoria) break;
			}
			tabuleiro.exibirTabuleiro();
			if (haVitoria) {
				System.out.print("Deseja jogar novamente? [S/N] ");
				leitor.nextLine();
				char escolha = leitor.next().charAt(0);
				if(escolha == 'N' || escolha == 'n') {
					break;
				}
				tabuleiro = new Tabuleiro();
				jogo = new Jogo();
				haVitoria = false;
				jogo.limparConsole();
				tabuleiro.exibirTabuleiro();
				continue;
			}
		} while (true);
		leitor.close();
	}

}
