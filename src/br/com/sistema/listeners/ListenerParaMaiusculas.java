package br.com.sistema.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class ListenerParaMaiusculas extends ListenerController implements ChangeListener<String> {

	
	public ListenerParaMaiusculas(TextField textField) {
		super(textField);
	}
	
	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		getTextField().setText(newValue.toUpperCase());
	}
	
}
