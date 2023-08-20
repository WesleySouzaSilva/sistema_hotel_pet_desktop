package br.com.sistema.filtros;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

// Usa Vírgula como marcador decimal
public class FiltroFlutuante implements EventHandler<KeyEvent>{

	private Integer maxLength = null;
	private boolean aceitaNegativo = false;
	
	public FiltroFlutuante() {	}

	public FiltroFlutuante(Integer maxLength){
	    this.maxLength = maxLength;
	}

	public FiltroFlutuante(boolean aceitaNegativo){
		this.aceitaNegativo = aceitaNegativo;
	}
	
	public FiltroFlutuante(Integer maximo, boolean aceitaNegativo){
	    this.maxLength = maximo;
		this.aceitaNegativo = aceitaNegativo;
	}
	
	@Override
	public void handle(KeyEvent e) {
		
		if (e.getSource() instanceof TextField){
			TextField tf = (TextField) e.getSource();
			int posCursor = tf.getCaretPosition();
			String texto = tf.getText();
			char c = e.getCharacter().charAt(0);

			if ( (maxLength != null && texto.length() >= maxLength) || (!Character.isDigit(c) && c != '-' && c != ',') ||
			     (c == '-' && (!aceitaNegativo || posCursor > 0 || (posCursor == 0 && texto.length() > 0 && texto.charAt(0) == '-'))) ||
			     (c == ',' && (texto.contains(",") || (posCursor == 0 && texto.length() > 0 && texto.charAt(0) == '-')))
			   )
			   e.consume();
		}
	}
}
