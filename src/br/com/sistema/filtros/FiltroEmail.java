package br.com.sistema.filtros;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FiltroEmail implements EventHandler<KeyEvent> {

	@Override
	public void handle(KeyEvent e) {

		if (e.getSource() instanceof TextField) {
			TextField tf = (TextField) e.getSource();
			String email = tf.getText();
			char c = e.getCharacter().charAt(0);

			// Caracteres válidos = A..Z, a..z, 0..9,  @ (arroba) . (ponto) - (hífen/traço) _ (sublinhado).
			
			
			if ((!Character.isLetter(c) && !Character.isDigit(c) && !"@.-_".contains(Character.toString(c)))
					|| (c == '@' && email.contains("@")))
				e.consume();
		}
	}
	
}