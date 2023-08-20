package br.com.sistema.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private Connection conexao = null;

	public Conexao(String nomeBanco, String usuario, String senha) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Conexao com ip 192.168.0.253
			conexao = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + nomeBanco + "?useTimezone=true&serverTimezone=UTC", usuario,
					senha); 
			System.out.println("Conectou!!");

		} catch (ClassNotFoundException e) {
			System.out.println("Nao foi encontrado o Driver de conexao");
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("Falha de conexao com o banco");
			e.printStackTrace();
		}
	}

	public Connection getConexao() {
		return conexao;
	}

	public void fecharConexao() {
		try {
			conexao.close();
			System.out.println("Desconectou!!");
		} catch (Exception e) {
			System.out.println("Falha ao fechar a conexão com o banco");
			e.printStackTrace();
		}
	}
}
