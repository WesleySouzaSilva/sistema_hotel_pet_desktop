package br.com.sistema.model;

public class Usuario {

	private Integer id;
	private String nome;
	private String senha;
	private String permissao;

	public Usuario() {

	}

	public Usuario(Integer id, String nome, String senha, String permissao) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.permissao = permissao;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPermissao() {
		return permissao;
	}

	public String setPermissao(String permissao) {
		this.permissao = permissao;
		return permissao;
	}

}
