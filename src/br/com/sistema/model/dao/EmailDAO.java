package br.com.sistema.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		if (pojo.getEmail() != null) {
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
		return false;
	}

	@Override
	public boolean apagar(Email pojo) {
		if (pojo.getId() != null) {
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
		return false;
	}

	@Override
	public boolean atualizar(Email novo) {
		if (novo.getEmail() != null && novo.getId() != null) {
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
		return false;
	}

	@Override
	public List<Email> listarTodos() {
		String sql = "SELECT * FROM email";
		Email emails = null;
		ArrayList<Email> lista = new ArrayList<>();
		try {
			Statement cmd = dbConnection.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			while (rs.next()) {
				int ids = rs.getInt("id");
				String ema = rs.getString("email");

				emails = new Email(ids, ema);
				lista.add(emails);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Email buscar(Integer pessoaId) {
		if (pessoaId != null) {
			String sql = "SELECT e.id, e.email, e.id, p.id, p.email_id FROM Email as e, Pessoa as p WHERE p.id = '" + pessoaId
					+ "' AND e.id = p.email_id";
			Email emails = null;
			try {
				Statement cmd = dbConnection.createStatement();
				ResultSet rs = cmd.executeQuery(sql);
				// enquanto houver um próximo registro, leia-os
				while (rs.next()) {
					int ids = rs.getInt("e.id");
					String email = rs.getString("e.email");

					emails = new Email(ids, email);

				}
				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return emails;
		}
		return null;
	}

	public Integer getEmail(String id) {
		if (id != null) {
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
		return null;
	}

}
