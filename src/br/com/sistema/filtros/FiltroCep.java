package br.com.sistema.filtros;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FiltroCep implements EventHandler<KeyEvent>{
	
	@Override
	public void handle(KeyEvent e) {
		
		if (e.getSource() instanceof TextField){
			TextField tf = (TextField) e.getSource();
			String cep = tf.getText();
			char c = e.getCharacter().charAt(0);
			// determina quantos caracteres numéricos existem
			int digitos = cep.replaceAll("[\\D]", "").length();
			
			if (cep.length() >= 9 ||  (Character.isDigit(c) && digitos == 8) ||
				(!Character.isDigit(c) &&  ((c != '-' || cep.contains("-")))) )
				   
				e.consume();			
		}
	}
}

