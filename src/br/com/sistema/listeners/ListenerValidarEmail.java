package br.com.sistema.listeners;

import br.com.sistema.validadores.Email;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ListenerValidarEmail extends ListenerController implements ChangeListener<Boolean> {

	private Alert dlg;

	public ListenerValidarEmail(TextField textField) {
		super(textField);
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean saiu, Boolean entrou) {

		if (saiu) {

			if (!Email.isValido(getTextField().getText())) {
				dlg = new Alert(AlertType.ERROR);
				dlg.setContentText("EMAIL invalido!!!");
				dlg.showAndWait();
				return;
			} else {
				limparErro();
			}
		}
	}
}
