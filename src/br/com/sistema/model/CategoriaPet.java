package br.com.sistema.model;

import java.math.BigDecimal;

public class CategoriaPet {

	private Integer id;
	private String nome;
	private String tipo;
	private Integer limite;
	private BigDecimal valor;

	public CategoriaPet() {

	}

	public CategoriaPet(Integer id, String nome, String tipo, Integer limite, BigDecimal valor) {
		super();
		this.id = id;
		this.nome = nome;
		this.limite = limite;
		this.tipo = tipo;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getLimite() {
		return limite;
	}

	public void setLimite(Integer limite) {
		this.limite = limite;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		String valor = null;
		if (nome == null) {
			valor = tipo;
		} else {
			valor = nome + ", " + tipo;
		}
		return valor;
	}

}
