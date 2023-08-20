package br.com.sistema.model;

import java.util.Date;

public class Pet {

	private Integer id;
	private String nome;
	private String raca;
	private Date dataNascimento;
	private String dataNascimentoFormatada;
	private String sexo;
	private String veterinario;
	private String observacao;
	private Pessoa pessoa;
	private String pessoaResponsavel;

	public Pet() {

	}

	public Pet(Integer id, String nome, String raca, Date dataNascimento, String dataNascimentoFormatada, String sexo,
			String veterinario, String observacao, Pessoa pessoa, String pessoaResponsavel) {
		super();
		this.id = id;
		this.nome = nome;
		this.raca = raca;
		this.dataNascimento = dataNascimento;
		this.dataNascimentoFormatada = dataNascimentoFormatada;
		this.sexo = sexo;
		this.veterinario = veterinario;
		this.observacao = observacao;
		this.pessoa = pessoa;
		this.pessoaResponsavel = pessoaResponsavel;
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

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getDataNascimentoFormatada() {
		return dataNascimentoFormatada;
	}

	public void setDataNascimentoFormatada(String dataNascimentoFormatada) {
		this.dataNascimentoFormatada = dataNascimentoFormatada;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(String veterinario) {
		this.veterinario = veterinario;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getPessoaResponsavel() {
		return pessoaResponsavel;
	}

	public void setPessoaResponsavel(String pessoaResponsavel) {
		this.pessoaResponsavel = pessoaResponsavel;
	}

}
