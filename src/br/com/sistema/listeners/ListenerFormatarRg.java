package br.com.sistema.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class ListenerFormatarRg extends ListenerController implements ChangeListener<Boolean> {

	public ListenerFormatarRg(TextField textField) {
		super(textField);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean saiu, Boolean entrou) {
		if (saiu) {
			// remove todos os caracteres que não são números (0..9)
			StringBuilder rg = new StringBuilder(getTextField().getText().replaceAll("[\\D]", ""));
			System.out.println(rg);
//			if (!Rg.isValido(rg.toString())) {
//				setMensagem("r deve conter 11 dígitos válidos");
//			} else {
//				rg.insert(3, '.').insert(7, '.').insert(10, '-');
//				getTextField().setText(rg.toString());
//				limparErro();
//			}
		}
	}

}
