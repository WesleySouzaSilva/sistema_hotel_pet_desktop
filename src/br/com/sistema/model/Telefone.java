package br.com.sistema.model;

public class Telefone {

	private Integer id;
	private String telComercial, telCelular, telResidencial, telWhatsapp;

	public Telefone() {
		
	}

	public Telefone(Integer id, String telComercial, String telCelular, String telResidencial, String telWhatsapp) {
		super();
		this.id = id;
		this.telComercial = telComercial;
		this.telCelular = telCelular;
		this.telResidencial = telResidencial;
		this.telWhatsapp = telWhatsapp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelComercial() {
		return telComercial;
	}

	public void setTelComercial(String telComercial) {
		this.telComercial = telComercial;
	}

	public String getTelCelular() {
		return telCelular;
	}

	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}

	public String getTelResidencial() {
		return telResidencial;
	}

	public void setTelResidencial(String telResidencial) {
		this.telResidencial = telResidencial;
	}

	public String getTelWhatsapp() {
		return telWhatsapp;
	}

	public void setTelWhatsapp(String telWhatsapp) {
		this.telWhatsapp = telWhatsapp;
	}

}
