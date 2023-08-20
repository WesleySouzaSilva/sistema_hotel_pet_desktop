package br.com.sistema.validadores;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Email {

	// classe não poderá ser instanciada
	private Email() {
	}

	// deve conter um síbolo @
	// email não pode conter espaço
	// deve ter no mínimo 8 caracteres: a@bc.com
	// deve conter pelo menos um caractere antes do @
	// deve conter pelo menos um . (ponto) depois do @
	// deve conter pelo menos dois caractere entre @ e .
	// deve conter pelo menos 2 caracteres após o último .
	public static boolean isValido(String email) {

		int posArroba = email.indexOf("@");
		int posPonto = email.lastIndexOf("."); // último ponto
		if (email.contains(" ") && email.length() >= 8 && posArroba >= 1 && posPonto + 2 > posArroba
				&& email.length() + 1 > posPonto) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Confirma��o de EMAIL");
			alerta.setHeaderText("EMAIL inv�lido ! ");
			alerta.setContentText("Preencha com um EMAIL v�lido !");
		} else {
			return (!email.contains(" ") && email.length() >= 8 && posArroba >= 1 && posPonto + 2 > posArroba
					&& email.length() + 1 > posPonto);
		}
		return email != null;
	}

}