package br.com.sistema.model;

import java.math.BigDecimal;
import java.util.Date;

public class Agenda {

	private Integer id;
	private Date dataEntrada;
	private Date dataSaida;
	private BigDecimal valor;
	private String tipo;
	private String situacao;
	private String observacao;
	private String valorFormatado;
	private String dataEntradaFormatada;
	private String dataSaidaformatada;
	private String nomeResponsavel;
	private String nomePet;
	private Pessoa pessoa;
	private Pet pet;

	public Agenda() {

	}

	public Agenda(Integer id, Date dataEntrada, Date dataSaida, BigDecimal valor, String situacao, String tipo,
			String observacao, String valorFormatado, String dataEntradaFormatada, String dataSaidaformatada,
			String nomeResponsavel, String nomePet, Pessoa pessoa, Pet pet) {
		super();
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.situacao = situacao;
		this.observacao = observacao;
		this.valorFormatado = valorFormatado;
		this.dataEntradaFormatada = dataEntradaFormatada;
		this.dataSaidaformatada = dataSaidaformatada;
		this.nomeResponsavel = nomeResponsavel;
		this.nomePet = nomePet;
		this.pessoa = pessoa;
		this.pet = pet;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getValorFormatado() {
		return valorFormatado;
	}

	public void setValorFormatado(String valorFormatado) {
		this.valorFormatado = valorFormatado;
	}

	public String getDataEntradaFormatada() {
		return dataEntradaFormatada;
	}

	public void setDataEntradaFormatada(String dataEntradaFormatada) {
		this.dataEntradaFormatada = dataEntradaFormatada;
	}

	public String getDataSaidaformatada() {
		return dataSaidaformatada;
	}

	public void setDataSaidaformatada(String dataSaidaformatada) {
		this.dataSaidaformatada = dataSaidaformatada;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getNomePet() {
		return nomePet;
	}

	public void setNomePet(String nomePet) {
		this.nomePet = nomePet;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
