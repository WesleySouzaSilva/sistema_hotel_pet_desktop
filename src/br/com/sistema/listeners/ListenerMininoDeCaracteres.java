package br.com.sistema.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ListenerMininoDeCaracteres extends ListenerController implements ChangeListener<Boolean> {

	private int minLength;
	private int maxLength;
	private Alert dlg;

	public ListenerMininoDeCaracteres(TextField textField, int minLength, int maxLength) {
		super(textField);
		this.minLength = minLength;
		this.maxLength = maxLength;
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean saiu, Boolean entrou) {

		if (saiu) {
			getTextField().setText(getTextField().getText().trim());

			if (getTextField().getText().length() < minLength) {
				dlg = new Alert(AlertType.ERROR);
				dlg.setContentText("Quantidade de caracteres invalido!!!");
				dlg.showAndWait();
				return;
			} else {
				if (getTextField().getText().length() > maxLength) {
					dlg = new Alert(AlertType.ERROR);
					dlg.setContentText("Quantidade de caracteres invalido!!!");
					dlg.showAndWait();
					return;
				} else {

				}
				limparErro();
			}
		}
	}
}
