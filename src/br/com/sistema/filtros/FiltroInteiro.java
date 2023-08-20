package br.com.sistema.filtros;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FiltroInteiro implements EventHandler<KeyEvent>{

	private Integer maxLength = null;
	private boolean aceitaNegativo = false;
	
	public FiltroInteiro() {	}

	public FiltroInteiro(Integer maxLength){
	    this.maxLength = maxLength;
	}

	public FiltroInteiro(boolean aceitaNegativo){
		this.aceitaNegativo = aceitaNegativo;
	}
	
	public FiltroInteiro(Integer maximo, boolean aceitaNegativo){
	    this.maxLength = maximo;
		this.aceitaNegativo = aceitaNegativo;
	}
	
	@Override
	public void handle(KeyEvent e) {
		
		if (e.getSource() instanceof TextField){
			TextField tf = (TextField) e.getSource();
			int posCursor = tf.getCaretPosition();
			String texto = tf.getText();
			int length = texto.replaceAll("[\\D]", "").length();
			char c = e.getCharacter().charAt(0);

			if ( (maxLength != null && length >= maxLength) || (!Character.isDigit(c) && c != '-') ||
			     (c == '-' && (!aceitaNegativo || posCursor > 0 || (posCursor == 0 && texto.length() > 0 && texto.charAt(0) == '-')))
			   )
			   e.consume();
		}
	}
}
