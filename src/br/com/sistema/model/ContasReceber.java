package br.com.sistema.model;

import java.math.BigDecimal;
import java.util.Date;

public class ContasReceber {

	private Integer id;
	private String nomeCliente;
	private String nomePet;
	private String tipo;
	private BigDecimal valor;
	private BigDecimal valorRestante;
	private Date data;
	private String dataFormatada;
	private String valorFormatado;
	private String situacao;
	private String valorPagar, valorPago, valorTotal;
	private Agenda agenda;
	private String saldo;

	public ContasReceber() {

	}

	public ContasReceber(Integer id, String nomeCliente, String nomePet, String tipo, BigDecimal valor,
			BigDecimal valorRestante, Date data, String dataFormatada, String valorFormatado, String situacao,
			Agenda agenda) {
		super();
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.nomePet = nomePet;
		this.tipo = tipo;
		this.valor = valor;
		this.valorRestante = valorRestante;
		this.data = data;
		this.dataFormatada = dataFormatada;
		this.valorFormatado = valorFormatado;
		this.situacao = situacao;
		this.agenda = agenda;
	}

	public ContasReceber(String valorPagar, String valorPago, String valorTotal, String saldo) {
		super();
		this.valorPagar = valorPagar;
		this.valorPago = valorPago;
		this.valorTotal = valorTotal;
		this.saldo = saldo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomePet() {
		return nomePet;
	}

	public void setNomePet(String nomePet) {
		this.nomePet = nomePet;
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

	public BigDecimal getValorRestante() {
		return valorRestante;
	}

	public void setValorRestante(BigDecimal valorRestante) {
		this.valorRestante = valorRestante;
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

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
}
