package br.com.sistema.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class ListenerFormatarCep extends ListenerController implements ChangeListener<Boolean> {

	public ListenerFormatarCep(TextField textField) {
		super(textField);
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean saiu, Boolean entrou) {

		if (saiu) {
			// remove todos os caracteres que n√£o s√£o n√∫meros (0..9)
			StringBuilder cep = new StringBuilder(getTextField().getText().replaceAll("[\\D]", ""));

			if (cep.length() != 8) {
				Alert dlg = new Alert(AlertType.ERROR);
				dlg.setTitle("ValidaÁ„o de CEP");
				dlg.setContentText("CEP Inv·lido !");
				dlg.showAndWait();
			} else {
				cep.insert(5, '-');
				getTextField().setText(cep.toString());
				limparErro();
			}

		}
	}
}
