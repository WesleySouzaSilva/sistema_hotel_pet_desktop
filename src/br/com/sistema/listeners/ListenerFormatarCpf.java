package br.com.sistema.listeners;

import br.com.sistema.validadores.Cpf;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class ListenerFormatarCpf extends ListenerController implements ChangeListener<Boolean> {

	public ListenerFormatarCpf(TextField textField) {
		super(textField);
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean saiu, Boolean entrou) {

		if (saiu) {
			// remove todos os caracteres que n√£o s√£o n√∫meros (0..9)
			StringBuilder cpf = new StringBuilder(getTextField().getText().replaceAll("[\\D]", ""));

			if (!Cpf.isValido(cpf.toString())) {
				Alert dlg = new Alert(AlertType.ERROR);
				dlg.setTitle("ValidaÁ„o de CPF");
				dlg.setContentText("CPF Inv·lido !");
				dlg.showAndWait();
				setMensagem("CPF deve conter 11 d√≠gitos v√°lidos");
			} else {
				cpf.insert(3, '.').insert(7, '.').insert(11, '-');
				getTextField().setText(cpf.toString());
				limparErro();
			}
		}
	}
}
