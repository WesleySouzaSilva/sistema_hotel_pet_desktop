package br.com.sistema.model;

import java.math.BigDecimal;
import java.util.Date;

public class DetalhesPagamento {

	private Integer id;
	private String tipo;
	private BigDecimal valor;
	private Date data;
	private String dataFormatada;
	private ContasReceber contasReceber;

	public DetalhesPagamento() {

	}

	public DetalhesPagamento(Integer id, String tipo, BigDecimal valor, Date data, String dataFormatada,
			ContasReceber contasReceber) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.valor = valor;
		this.data = data;
		this.dataFormatada = dataFormatada;
		this.contasReceber = contasReceber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDataFormatada() {
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

	public ContasReceber getcontasReceber() {
		return contasReceber;
	}

	public void setcontasReceber(ContasReceber contasReceber) {
		this.contasReceber = contasReceber;
	}

}
