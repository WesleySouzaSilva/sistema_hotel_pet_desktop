package br.com.sistema.filtros;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FiltroLetras implements EventHandler<KeyEvent>{

	private Integer maxLength = null;
	
	public FiltroLetras() {	}
	
	public FiltroLetras(Integer maxLength){
		this.maxLength = maxLength;
	}
	
	@Override
	public void handle(KeyEvent e) {
			char c = e.getCharacter().charAt(0);
			if (e.getSource() instanceof TextField && maxLength != null &&
				((TextField) e.getSource()).getText().length() >= maxLength)
					e.consume();
			else if (!Character.isAlphabetic(c) && c != 32)
				e.consume();
	}
}
