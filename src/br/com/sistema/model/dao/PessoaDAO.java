package br.com.sistema.model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistema.conexao.AbstractGenericDAO;
import br.com.sistema.conexao.Conexao;
import br.com.sistema.model.Pessoa;

public class PessoaDAO extends AbstractGenericDAO<Pessoa> {

	public PessoaDAO(Conexao conexao) {
		super(conexao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean inserir(Pessoa pojo) {
		String sql = "INSERT INTO pessoa(nome, cpf_cnpj, rg, data_nascimento, sexo, endereco_id, email_id, telefone_id, ativo, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);

			cmd.setString(1, pojo.getNome());
			cmd.setString(2, pojo.getCpfcnpj());
			cmd.setString(3, pojo.getRg());
			cmd.setDate(4, (Date) pojo.getDataNascimento());
			cmd.setString(5, pojo.getSexo());
			cmd.setInt(6, pojo.getEndereco().getId());
			cmd.setInt(7, pojo.getEmail().getId());
			cmd.setInt(8, pojo.getTelefone().getId());
			cmd.setString(9, pojo.getAtivo());
			cmd.setString(10, pojo.getTipo());

			int retorno = cmd.executeUpdate();
			cmd.close();
			if (retorno > 0) {
				// salva o id gerado pelo banco no próprio objeto
				pojo.setId(ultimoID("pessoa"));
			}

			return retorno > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean apagar(Pessoa pojo) {
		// melhor usar ON DELETE CASCADE durante a criação da tabela no
		// constraint
		String sqlPessoa = "DELETE FROM pessoa WHERE id =?";
		String sqlEndereco = "DELETE FROM endereco WHERE id =?";
		String sqlTelefone = "DELETE FROM telefone WHERE id = ?";
		String sqlEmail = "DELETE FROM email WHERE id = ?";

		try {
			PreparedStatement cmdEmail = dbConnection.prepareStatement(sqlEmail);
			cmdEmail.setInt(1, pojo.getEmail().getId());
			cmdEmail.executeUpdate();
			cmdEmail.close();

			PreparedStatement cmdTelefone = dbConnection.prepareStatement(sqlTelefone);
			cmdTelefone.setInt(1, pojo.getTelefone().getId());
			cmdTelefone.executeUpdate();
			cmdTelefone.close();

			PreparedStatement cmdEndereco = dbConnection.prepareStatement(sqlEndereco);
			cmdEndereco.setInt(1, pojo.getEndereco().getId());
			cmdEndereco.executeUpdate();
			cmdEndereco.close();

			PreparedStatement cmdPessoa = dbConnection.prepareStatement(sqlPessoa);
			cmdPessoa.setInt(1, pojo.getId());
			int retornoPessoa = cmdPessoa.executeUpdate();
			cmdPessoa.close();
			return retornoPessoa > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean atualizar(Pessoa novo) {
		String sql = "UPDATE pessoa SET nome = ?, cpf_cnpj = ?, rg = ?, data_nascimento = ?, sexo = ? WHERE id = ?";
		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);

			cmd.setString(1, novo.getNome());
			cmd.setString(2, novo.getCpfcnpj());
			cmd.setString(3, novo.getRg());
			cmd.setDate(4, (Date) novo.getDataNascimento());
			cmd.setString(5, novo.getSexo());
			cmd.setInt(6, novo.getId());

			int retorno = cmd.executeUpdate();
			cmd.close();
			if (retorno > 0) {
				// salva o id gerado pelo banco no próprio objeto
				novo.setId(ultimoID("pessoa"));
			}

			return retorno > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Pessoa> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pessoa> buscarCpfCnpj(String campo) {
		String sql = "SELECT * FROM pessoa WHERE cpf_cnpj = '" + campo + "'";
		
		Pessoa pessoa = null;
		ArrayList<Pessoa> lista = new ArrayList<>();

		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String cpfcnpj = rs.getString("cpf_cnpj)");

				pessoa = new Pessoa(id, nome, cpfcnpj, null, null, null, null, null, null, null, null, null);

				lista.add(pessoa);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

}
