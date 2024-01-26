package br.com.sistema.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.sistema.conexao.AbstractGenericDAO;
import br.com.sistema.conexao.Conexao;
import br.com.sistema.model.Estado;

public class EstadoDAO extends AbstractGenericDAO<Estado> {

	public EstadoDAO(Conexao conexao) {
		super(conexao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean inserir(Estado pojo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean apagar(Estado pojo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean atualizar(Estado novo) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Estado> listar() {
		String sql = "SELECT * FROM estado ORDER BY id";
		List<Estado> lista = new ArrayList<Estado>();

		try {
			Statement cmd = dbConnection.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			// enquanto houver um pr�ximo registro, leia-os
			while (rs.next()) {
				String nome = rs.getString("nome");

				Estado estado = new Estado(null, nome, null);
				lista.add(estado);

			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;

	}

	public List<Estado> buscarNome(String campo) {
		String sql = "SELECT * FROM estado WHERE nome = '" + campo + "' ORDER BY nome";
		ArrayList<Estado> lista = new ArrayList<>();
		Estado estado = new Estado();

		try {
			Statement cmd = dbConnection.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			// enquanto houver um pr�ximo registro, leia-os
			while (rs.next()) {

				int ids = rs.getInt("id");
				String nome = rs.getString("nome");
				String uf = rs.getString("uf");
				estado = new Estado(ids, nome, uf);
				lista.add(estado);

			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;

	}

	public Estado buscar(Integer id) {
		String sql = "SELECT * FROM estado WHERE id = ? ORDER BY nome";
		Estado estado = null;

		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);
			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery();

			if (rs.next()) {
				int ids = rs.getInt("id");
				String nome = rs.getString("nome");
				String uf = rs.getString("uf");
				estado = new Estado(ids, nome, uf);

			}

			cmd.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return estado;
	}

	public Estado buscar(String campo) {
		String sql = "SELECT * FROM estado WHERE nome = ? ORDER BY nome";
		Estado estado = null;

		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);
			cmd.setString(1, campo);
			ResultSet rs = cmd.executeQuery();

			if (rs.next()) {
				int ids = rs.getInt("id");
				String nome = rs.getString("nome");
				String uf = rs.getString("uf");
				estado = new Estado(ids, nome, uf);

			}

			cmd.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return estado;
	}

	@Override
	public List<Estado> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
}