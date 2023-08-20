package br.com.sistema.validadores;

public class Cpf {

	// classe não poderá ser instanciada
	private Cpf() {
	}

	// Ex.: CPF "67456982341"
	public static boolean isValido(String cpf) {
		if (cpf == null)
			return false;
		// remove todos os caracteres que não são números (0..9)
		cpf = cpf.replaceAll("[\\D]", "");

		// CPF = 9 dígitos principais (DP) + 2 dígitos verificadores (DV)
		// CPF não pode ser formado por todos os números iguais
		if ((cpf.length() != 11) || cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999"))
			return false;

		// cálculo do 1o dígito verificador
		// ms1 = (6*10)+(7*9)+(4*8)+(5*7)+(6*6)+(9*5)+(8*4)+(2*3)+(3*2) = 315

		int ms1 = 0;
		for (int i = 0, j = 10; i < 9; i++, j--) {
			ms1 += (cpf.charAt(i) - 48) * j;
		}

		// dv1 = 11 - (ms1 % 11) = 4
		int r1 = 11 - (ms1 % 11);
		int dv1 = ((r1 == 10) || (r1 == 11)) ? 0 : r1;

		// ms2 = (6*11)+(7*10)+(4*9)+(5*8)+(6*7)+(9*6)+(8*5)+(2*4)+(3*3)+(4*2) = 373
		int ms2 = 0;
		for (int i = 0, j = 11; i < 9; i++, j--) {
			ms2 += (cpf.charAt(i) - 48) * j;
		}
		ms2 += dv1 * 2;

		// dv2 = 11 - (ms2 % 11) = 1
		int r2 = 11 - (ms2 % 11);
		int dv2 = ((r2 == 10) || (r2 == 11)) ? 0 : r2;

		// dígitos verificadores = 41
		return cpf.endsWith(dv1 + "" + dv2);

	}

}
