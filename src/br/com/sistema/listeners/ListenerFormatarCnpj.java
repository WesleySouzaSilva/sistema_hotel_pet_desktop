package br.com.sistema.listeners;

import br.com.sistema.validadores.Cnpj;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ListenerFormatarCnpj extends ListenerController implements ChangeListener<Boolean> {

	public ListenerFormatarCnpj(TextField textField) {
		super(textField);
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean saiu, Boolean entrou) {

		if (saiu) {
			// remove todos os caracteres que n√£o s√£o n√∫meros (0..9)
			StringBuilder cnpj = new StringBuilder(getTextField().getText().replaceAll("[\\D]", ""));

			if (!Cnpj.isValido(cnpj.toString())) {
				Alert dlg = new Alert(AlertType.ERROR);
				dlg.setTitle("ValidaÁ„o de CNPJ");
				dlg.setContentText("CNPJ Inv·lido !");
				dlg.showAndWait();
			} else {
				cnpj.insert(2, '.').insert(6, '.').insert(10, '/').insert(15, '-');
				getTextField().setText(cnpj.toString());
				limparErro();
			}
		}
	}

}
