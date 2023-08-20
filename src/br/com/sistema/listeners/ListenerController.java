package br.com.sistema.listeners;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

abstract public class ListenerController {

	private TextField textField;
	static private Label labelMensagem = null;
	static private String corErro = "lightblue";

	static public void setLabelMensagem(Label labelMensagem) {
		ListenerController.labelMensagem = labelMensagem;
		System.out.println(ListenerController.labelMensagem);
	}

	// construtor
	public ListenerController(TextField textField) {
		// if (ListenerController.labelMensagem == null){
		// String erro = "Faltou executar o método estático\nsetLabelMensagem(Label
		// labelMensagem)";
		// Alert alerta = new Alert(AlertType.ERROR);
		// alerta.setTitle("Erro Fatal");
		// alerta.setHeaderText("Classe ListenerController");
		// alerta.setContentText(erro);
		// alerta.showAndWait();
		// throw new RuntimeException("\nClasse ListenerController\n" + erro + "\n");
		// }
		this.textField = textField;
	}

	protected void setMensagem(String msg) {
		// labelMensagem.setText(msg);
		getTextField().setStyle("-fx-background-color: " + corErro); // ERRO
	}

	protected TextField getTextField() {
		return textField;
	}

	protected void limparErro() {
		getTextField().setStyle(""); // OK
	}

	static public boolean validar(List<TextField> listaCampos, Button botao) {
		for (TextField campo : listaCampos) {
			campo.requestFocus();
			botao.requestFocus();
			if (campo.getStyle().length() > 0) {
				campo.requestFocus();
				return false;
			}
		}

		return true;
	}

	static public void setCorErro(String cor) {
		corErro = cor;
	}

}
