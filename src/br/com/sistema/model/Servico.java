package br.com.sistema.model;

import java.math.BigDecimal;
import java.util.Date;

public class Servico {

	private Integer id;
	private String descricao;
	private BigDecimal valor;
	private Date data;
	private String dataFormatada;
	private String valorFormatado;
	private Agenda agenda;

	public Servico() {

	}

	public Servico(Integer id, String descricao, BigDecimal valor, Date data, String dataFormatada,
			String valorFormatado, Agenda agenda) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.dataFormatada = dataFormatada;
		this.valorFormatado = valorFormatado;
		this.agenda = agenda;
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

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

}
