package main.tabuleiro;

public class Tabuleiro {
	//Criação do tabuleiro, que é uma matriz fixa 3x3
	private char[][] tabuleiro = new char[3][3];
	
	//Método feito para exibir tabuleiro
	public void exibirTabuleiro() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(this.tabuleiro[i][j] != '\u0000') {
					System.out.printf("%c", this.tabuleiro[i][j]);
				} else {
					System.out.print(" ");
				}
				if(j < 2) {
					System.out.print("|");
					continue;
				}
				System.out.println(" ");
			}
			if(i < 2) {
				System.out.println("-----");
			}
		}
	}
	
	//Preencher o tabuleiro na posição escolhida com a peça
	public void preencherTabuleiro(int linha, int coluna, char peca) {
		this.tabuleiro[linha][coluna] = peca;
	}
	
	public char getConteudo(int linha, int coluna) {
		if (this.tabuleiro[linha][coluna] == '\u0000') {
			return ' ';
		}
		return this.tabuleiro[linha][coluna];
	}
	
	public void setConteudo(char peca, int linha, int coluna) {
		this.tabuleiro[linha][coluna] = peca;
	}
}
