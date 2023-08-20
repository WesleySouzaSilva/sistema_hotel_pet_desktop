package br.com.sistema.model;

import java.math.BigDecimal;
import java.util.Date;

public class ContasPagar {

	private Integer id;
	private String descricao;
	private BigDecimal valor;
	private Date data;
	private String dataFormatada;
	private String valorFormatado;
	private String situacao;
	private String valorPagar, valorPago, valorTotal;

	public ContasPagar() {

	}

	public ContasPagar(Integer id, String descricao, BigDecimal valor, Date data, String situacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.situacao = situacao;
	}

	public ContasPagar(Integer id, String descricao, String dataFormatada, String valorFormatado, String situacao,
			String nula) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataFormatada = dataFormatada;
		this.valorFormatado = valorFormatado;
		this.situacao = situacao;
	}

	public ContasPagar(String valorPagar, String valorPago, String valorTotal) {
		super();
		this.valorPagar = valorPagar;
		this.valorPago = valorPago;
		this.valorTotal = valorTotal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public String getValorFormatado() {
		return valorFormatado;
	}

	public void setValorFormatado(String valorFormatado) {
		this.valorFormatado = valorFormatado;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(String valorPagar) {
		this.valorPagar = valorPagar;
	}

	public String getValorPago() {
		return valorPago;
	}

	public void setValorPago(String valorPago) {
		this.valorPago = valorPago;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

}
