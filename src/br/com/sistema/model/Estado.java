package br.com.sistema.model;

public class Estado {

	private Integer id;
	private String nome;
	private String uf;

	public Estado() {
		this(null, null, null);
	}

	public Estado(Integer id, String nome, String uf) {
		super();
		this.id = id;
		this.nome = nome;
		this.uf = uf;
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getNome();
	}
}
