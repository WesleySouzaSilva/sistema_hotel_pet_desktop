package br.com.sistema.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.sistema.conexao.AbstractGenericDAO;
import br.com.sistema.conexao.Conexao;
import br.com.sistema.model.Email;

public class EmailDAO extends AbstractGenericDAO<Email> {

	public EmailDAO(Conexao conexao) {
		super(conexao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean inserir(Email pojo) {
		String sqlEmail = "INSERT INTO email(email) VALUES (?)";
		try {
			PreparedStatement statement = dbConnection.prepareStatement(sqlEmail);
			statement.setString(1, pojo.getEmail());
			int retornoEma = statement.executeUpdate();

			if (retornoEma > 0) {
				// salva o id gerado pelo banco no prÃ³prio objeto
				pojo.setId(ultimoID("email"));
			}
			return retornoEma > 0;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean apagar(Email pojo) {
		String sqlEmail = "DELETE FROM email WHERE id =?";
		try {

			PreparedStatement cmdEmail = dbConnection.prepareStatement(sqlEmail);
			cmdEmail.setInt(1, pojo.getId());
			int retorno = cmdEmail.executeUpdate();
			cmdEmail.close();

			return retorno > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean atualizar(Email novo) {
		String sql = "UPDATE email SET email = ? WHERE id = ?";

		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);
			cmd.setString(1, novo.getEmail());
			cmd.setInt(2, novo.getId());
			int retorno = cmd.executeUpdate();
			cmd.close();
			return retorno > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Email> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Email buscar(Integer id) {
		String sql = "SELECT e.id, e.email, e.id, p.id, p.email_id FROM Email as e, Pessoa as p WHERE p.id = '" + id
				+ "' AND e.id = p.email_id";
		Email emails = null;
		try {
			Statement cmd = dbConnection.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			// enquanto houver um próximo registro, leia-os
			while (rs.next()) {
				int ids = rs.getInt("e.id");
				String email = rs.getString("e.email");

				emails = new Email(ids, email, email);

			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emails;
	}

	public Integer getEmail(String id) {
		String sql = "SELECT * FROM email WHERE id = " + id + "";
		Integer emailId = null;
		try {
			Statement cmd = dbConnection.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			// enquanto houver um próximo registro, leia-os
			if (rs.next()) {
				emailId = rs.getInt("id");

			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emailId;
	}

}
