package br.com.sistema.filtros;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FiltroChassis implements EventHandler<KeyEvent> {

	private Integer maxLength = null;

	public FiltroChassis() {
	}

	public FiltroChassis(Integer maxLength) {
		this.maxLength = maxLength;
	}

	@Override
	public void handle(KeyEvent e) {
		if (e.getSource() instanceof TextField && maxLength != null
				&& ((TextField) e.getSource()).getText().length() > maxLength)
			e.consume();

	}
}
