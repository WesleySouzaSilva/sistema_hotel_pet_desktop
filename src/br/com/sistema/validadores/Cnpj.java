package br.com.sistema.validadores;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Cnpj {

	// classe nÃ£o poderÃ¡ ser instanciada
	private Cnpj() {
	}

	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	// Exemplo: "17726829000179"
	public static boolean isValido(String cnpj) {
		if (cnpj == null)
			return false;

		// remove todos os caracteres que nÃ£o sÃ£o nÃºmeros (0..9)
		cnpj = cnpj.replaceAll("[\\D]", "");
		if ((cnpj.length() != 14) || cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
				|| cnpj.equals("22222222222222") || cnpj.equals("3333333333333") || cnpj.equals("44444444444444")
				|| cnpj.equals("55555555555555") || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
				|| cnpj.equals("88888888888888") || cnpj.equals("99999999999999")) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Confirmação de CNPJ");
			alerta.setHeaderText("CNPJ inválido ! ");
			alerta.setContentText("Preencha com um CNPJ válido !");
			return false;
		}

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}
}
