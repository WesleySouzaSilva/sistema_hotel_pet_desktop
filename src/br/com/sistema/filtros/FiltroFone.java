package br.com.sistema.filtros;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FiltroFone implements EventHandler<KeyEvent> {

	// aceita até 11 dígitos, parenteses, espaço e -
	// (42)98765-4321
	@Override
	public void handle(KeyEvent e) {

		if (e.getSource() instanceof TextField) {
			TextField tf = (TextField) e.getSource();
			String fone = tf.getText();
			char c = e.getCharacter().charAt(0);
			// determina quantos caracteres numéricos existem
			int digitos = fone.replaceAll("[\\D]", "").length();
			
			if ((Character.isDigit(c) && digitos >= 11) || 
					(!Character.isDigit(c) &&  (fone.contains(Character.toString(c)) || !"()- ".contains(Character.toString(c))) )
			   )
			   e.consume();
		}
	}
}
