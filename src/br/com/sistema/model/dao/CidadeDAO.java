package br.com.sistema.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.sistema.conexao.AbstractGenericDAO;
import br.com.sistema.conexao.Conexao;
import br.com.sistema.model.Cidade;

public class CidadeDAO extends AbstractGenericDAO<Cidade> {

	public CidadeDAO(Conexao conexao) {
		super(conexao);

		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean inserir(Cidade pojo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean apagar(Cidade pojo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean atualizar(Cidade novo) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Cidade> listar() {
		String sql = "SELECT nome from cidade ORDER BY nome;";
		List<Cidade> lista = new ArrayList<>();

		try {
			Statement cmd = dbConnection.createStatement();

			ResultSet rs = cmd.executeQuery(sql);
			// enquanto houver um próximo registro, leia-os
			while (rs.next()) {
				String nome = rs.getString("nome");

				Cidade cidade = new Cidade(null, nome, null);
				lista.add(cidade);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;

	}

	public List<Cidade> buscarCidade(Integer id) {
		String sql = "SELECT * FROM cidade WHERE estado = ? ORDER BY nome";
		List<Cidade> lista = new ArrayList<>();

		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);
			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery();

			// enquanto houver um próximo registro, leia-os
			while (rs.next()) {
				int id_cidade = rs.getInt("id");
				String nome = rs.getString("nome");

				Cidade cidade = new Cidade(id_cidade, nome, null);
				lista.add(cidade);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<Cidade> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}