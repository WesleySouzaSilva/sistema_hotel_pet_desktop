package br.com.sistema.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class ListenerFormatarMonetario extends ListenerController implements ChangeListener<Boolean> {

	private int casasDecimais;

	public ListenerFormatarMonetario(TextField textField, int casasDecimais) {
		super(textField);
		this.casasDecimais = casasDecimais;
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean saiu, Boolean entrou) {

		if (saiu) {

			String txt = getTextField().getText().trim();
			boolean negativo = txt.length() > 0 && txt.charAt(0) == '-';
			StringBuilder texto = new StringBuilder();

			txt.chars().forEach(ch -> {
				if (Character.isDigit(ch) || ch == ',')
					texto.append((char) ch);
			});

			if (texto.length() == 0) {
				texto.insert(0, '0');
			}

			// remove zeros iniciais desnecessários
			for (int i = 1; i < texto.length(); i++) {
				if (texto.charAt(0) == '0' && texto.charAt(1) != ',') {
					texto.deleteCharAt(0);
					i--;
				} else
					break;
			}

			int posVirgula = texto.indexOf(","); // -1 = inexistente
			int posPonto = (posVirgula > -1 ? posVirgula : texto.length()) - 3;

			// insere pontos representando milhar
			while (posPonto > 0) {
				texto.insert(posPonto, '.');
				posPonto -= 3;
			}

			// insere vírgula no final
			if (casasDecimais > 0 && texto.indexOf(",") == -1) {
				texto.append(",");
			}

			// insere 0 antes da vírgula
			if (texto.indexOf(",") == 0) {
				texto.insert(0, '0');
			}

			// 0123,4 -> posVirgula = 4
			//
			// acrescenta zeros faltantes após a vírgula
			int faltam = casasDecimais - (texto.substring(texto.indexOf(",") + 1, texto.length()).length());
			for (int i = 0; i < faltam; i++) {
				texto.append('0');
			}

			// remove valores decimais excedentes após a vírgula
			if (texto.indexOf(",") > -1) {
				texto.delete(texto.indexOf(",") + casasDecimais + 1, texto.length());
			}

			// remove vírgula se casasDecimais = 0
			if (casasDecimais == 0 && texto.indexOf(",") > -1) {
				texto.delete(texto.indexOf(","), texto.length());
			}

			if (negativo && Double.parseDouble(texto.toString().replaceAll("[\\D]", "")) != 0.0) {
				texto.insert(0, '-');
			}

			getTextField().setText(texto.toString());

		}
	}

}
