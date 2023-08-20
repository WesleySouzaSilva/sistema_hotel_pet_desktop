package br.com.sistema.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class ListenerValorMinMax extends ListenerController implements ChangeListener<Boolean>{

	private Double minimo;
	private Double maximo;

	public ListenerValorMinMax(TextField textField, Integer minimo, Integer maximo) {
		this(textField, (double) minimo, (double) maximo);
	}

	public ListenerValorMinMax(TextField textField, Double minimo, Double maximo) {
		super(textField);
		this.minimo = minimo;
		this.maximo = maximo;
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean saiu, Boolean entrou) {

		if (saiu) {
			String texto = getTextField().getText().trim();

			if (texto.isEmpty() || texto.equals(",") || texto.equals("-") || texto.equals("-,")) {
				setMensagem("Campo Obrigatório!");

			} else {
				double valor = Double.parseDouble(texto.replace(".", "").replace(',', '.'));

				if (minimo != null && valor < minimo) {
					setMensagem("O valor mínimo é de " + minimo);
				} else if (maximo != null && valor > maximo) {
					setMensagem("O valor máximo é de " + maximo);
				} else
					limparErro();
			}

		}
	}

}
