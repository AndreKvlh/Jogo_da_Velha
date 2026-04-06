package main.gui;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.tabuleiro.*;
import main.jogo.*;

public class TelaJogo extends Application {
	private Tabuleiro tabuleiro = new Tabuleiro();
	private Jogo jogo = new Jogo();
	private GridPane gradeVisual = new GridPane();
	private int jogadorAtivo = 0;
	private boolean fimDeJogo = false;
	
	@Override
    public void start(Stage palco) {
		this.atualizarTabuleiro();
		
		Scene cena = new Scene (gradeVisual, 400, 400);
		palco.setScene(cena);
        palco.setTitle("Jogo da Velha");
        palco.show();
    }
	
	public void atualizarTabuleiro() {
		gradeVisual.getChildren().clear();
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				char simbolo = tabuleiro.getConteudo(i, j);
				Button botao = new Button(String.valueOf(simbolo));
				botao.setMinSize(100, 100);
				
				final int linha = i;
				final int coluna = j;
				
				botao.setOnAction(evento -> {
					jogo.realizarJogada(tabuleiro, jogo.getOpcao(linha, coluna), 'X', jogo.JOGADORES[0]);
					char novoSimbolo = tabuleiro.getConteudo(linha, coluna);
					botao.setText(String.valueOf(novoSimbolo));
					fimDeJogo = jogo.checarVitoria(jogo.JOGADORES[jogadorAtivo]);
					if(fimDeJogo) {
						this.atualizarBotoes();
						this.exibirAlerta("Você venceu!");
					}
					if(jogo.deuVelha(fimDeJogo)) {
						this.exibirAlerta("Deu velha!");
					}
					this.alterarTurno();
				
					jogo.jogadaComputador(tabuleiro);
					fimDeJogo = jogo.checarVitoria(jogo.JOGADORES[jogadorAtivo]);
					if(fimDeJogo) {
						this.atualizarBotoes();
						this.exibirAlerta("O oponente venceu!");
					}
					this.atualizarBotoes();
					if(jogo.deuVelha(fimDeJogo)) {
						this.exibirAlerta("Deu velha!");
					}
					this.alterarTurno();
				});
				gradeVisual.add(botao,j,i);
			}
		}
	}
	
	public void atualizarBotoes() {
		for (Node node : gradeVisual.getChildren()) {
			if (node instanceof Button botao) {
				int linha = gradeVisual.getRowIndex(botao);
				int coluna = gradeVisual.getColumnIndex(botao);
				
				char simbolo = tabuleiro.getConteudo(linha, coluna);
				
				if(simbolo != ' ') {
					botao.setText(String.valueOf(simbolo));
					botao.setDisable(true);
				}
			}
		}
	}
	
	private void alterarTurno() {
		if (jogadorAtivo == 0) jogadorAtivo = 1;
		else jogadorAtivo = 0;
	}
	
	private void exibirAlerta(String mensagem) {
		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		
		alerta.setTitle("Fim de Jogo");
		alerta.setHeaderText(null);
		alerta.setContentText(mensagem);
		
		alerta.showAndWait().ifPresent(resposta -> {
			if (resposta == ButtonType.OK) {
				this.reiniciarJogo();
			}
		});
	}
	
	private void reiniciarJogo() {
		this.tabuleiro = new Tabuleiro();
		this.jogo = new Jogo();
		this.jogadorAtivo = 0;
		this.fimDeJogo = false;
		for (Node node : gradeVisual.getChildren()) {
			if (node instanceof Button botao) {
				botao.setText(" ");
				botao.setDisable(false);
			}
		}
	};
		
	
	public static void main(String[] args) {
        launch(args);
    }
}
