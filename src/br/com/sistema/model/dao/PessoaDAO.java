package br.com.sistema.model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistema.conexao.AbstractGenericDAO;
import br.com.sistema.conexao.Conexao;
import br.com.sistema.model.Email;
import br.com.sistema.model.Endereco;
import br.com.sistema.model.Pessoa;
import br.com.sistema.model.Telefone;

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
				// salva o id gerado pelo banco no pr贸prio objeto
				pojo.setId(ultimoID("pessoa"));
			}

			return retorno > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean inserirPJ(Pessoa pojo) {
		String sql = "INSERT INTO pessoa(nome, cpf_cnpj, endereco_id, email_id, telefone_id, ativo, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);

			cmd.setString(1, pojo.getNome());
			cmd.setString(2, pojo.getCpfcnpj());
			cmd.setInt(3, pojo.getEndereco().getId());
			cmd.setInt(4, pojo.getEmail().getId());
			cmd.setInt(5, pojo.getTelefone().getId());
			cmd.setString(6, pojo.getAtivo());
			cmd.setString(7, pojo.getTipo());

			int retorno = cmd.executeUpdate();
			cmd.close();
			if (retorno > 0) {
				// salva o id gerado pelo banco no pr贸prio objeto
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
		// melhor usar ON DELETE CASCADE durante a cria莽茫o da tabela no
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

	public boolean inativarCliente(Integer id) {
		String sql = "UPDATE pessoa SET ativo = ? WHERE id = ?";
		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);

			cmd.setString(1, "NAO");
			cmd.setInt(2, id);
			int retorno = cmd.executeUpdate();
			cmd.close();
			if (retorno > 0) {

			}

			return retorno > 0;
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
				// salva o id gerado pelo banco no pr贸prio objeto
				novo.setId(ultimoID("pessoa"));
			}

			return retorno > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean atualizarPJ(Pessoa novo) {
		String sql = "UPDATE pessoa SET nome = ?, cpf_cnpj = ?, tipo = ?, ativo = ?  WHERE id = ?";
		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);

			cmd.setString(1, novo.getNome());
			cmd.setString(2, novo.getCpfcnpj());
			cmd.setString(3, novo.getTipo());
			cmd.setString(4, novo.getAtivo());
			cmd.setInt(5, novo.getId());

			int retorno = cmd.executeUpdate();
			cmd.close();
			if (retorno > 0) {
				// salva o id gerado pelo banco no pr贸prio objeto
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
		String sql = "SELECT * FROM pessoa WHERE ativo = 'SIM' ORDER BY nome";
		Pessoa pessoa = null;
		ArrayList<Pessoa> lista = new ArrayList<>();

		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			// enquanto houver um pr髕imo registro, leia-os
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String cpfcnpj = rs.getString("cpf_cnpj");

				pessoa = new Pessoa(id, nome, cpfcnpj, null, null, null, null, null, null, null, null, null);

				lista.add(pessoa);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public List<Pessoa> buscarDadosPorId(Integer id) {
		String sql = "SELECT p.id, p.nome, p.cpf_cnpj, p.rg, DATE_FORMAT(p.data_nascimento, '%d/%m/%Y'), p.sexo, p.telefone_id, p.endereco_id, p.email_id, p.tipo, p.ativo, e.rua, e.bairro, e.numero, e.cidade, e.estado, e.cep, t.tel_comercial, t.tel_celular, t.tel_residencial, t.tel_whatsapp, m.email FROM pessoa AS p INNER JOIN endereco e INNER JOIN telefone t INNER JOIN email m ON e.id = p.endereco_id AND t.id = p.telefone_id AND m.id = p.email_id WHERE p.id ='"
				+ id + "'";
		Endereco endereco = new Endereco();
		Telefone telefone = new Telefone();
		Email email = new Email();
		ArrayList<Pessoa> lista = new ArrayList<>();

		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			// enquanto houver um pr髕imo registro, leia-os
			while (rs.next()) {
				int ids = rs.getInt("p.id");
				String nome = rs.getString("p.nome");
				String CpfCnpj = rs.getString("p.cpf_cnpj");
				String rg = rs.getString("p.rg");
				String dataNascimento = rs.getString("DATE_FORMAT(p.data_nascimento, '%d/%m/%Y')");
				String sexo = rs.getString("p.sexo");
				String cidade = rs.getString("e.cidade");
				String bairro = rs.getString("e.bairro");
				String cep = rs.getString("e.cep");
				String numero = rs.getString("e.numero");
				String rua = rs.getString("e.rua");
				String estado = rs.getString("e.estado");
				int enderecoId = rs.getInt("p.endereco_id");
				int telefoneId = rs.getInt("p.telefone_id");
				int emailId = rs.getInt("p.email_id");
				String telCelular = rs.getString("t.tel_celular");
				String telComercial = rs.getString("t.tel_comercial");
				String telResidencial = rs.getString("t.tel_residencial");
				String telWhatsapp = rs.getString("t.tel_whatsapp");

				String emails = rs.getString("m.email");

				String tipo = rs.getString("p.tipo");
				String ativo = rs.getString("p.ativo");

				endereco = new Endereco(enderecoId, rua, bairro, numero, cidade, estado, cep);
				telefone = new Telefone(telefoneId, telComercial, telCelular, telResidencial, telWhatsapp);
				email = new Email(emailId, emails);

				Pessoa pesso = new Pessoa(ids, nome, CpfCnpj, rg, sexo, null, endereco, telefone, email, tipo, ativo,
						dataNascimento);
				lista.add(pesso);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public List<Pessoa> buscarCliente(String parametro, String pesquisa) {
		String sql = "SELECT * FROM pessoa WHERE " + parametro + " like '" + pesquisa
				+ "%' AND ativo = 'SIM' ORDER BY nome";
		Pessoa pessoa = null;
		ArrayList<Pessoa> lista = new ArrayList<>();

		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			// enquanto houver um pr髕imo registro, leia-os
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String cpfcnpj = rs.getString("cpf_cnpj");

				pessoa = new Pessoa(id, nome, cpfcnpj, null, null, null, null, null, null, null, null, null);

				lista.add(pessoa);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public List<Pessoa> buscarCliente(String parametro) {
		String sql = "SELECT * FROM pessoa WHERE " + parametro + " AND ativo = 'SIM' ORDER BY nome";
		Pessoa pessoa = null;
		ArrayList<Pessoa> lista = new ArrayList<>();

		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			// enquanto houver um pr髕imo registro, leia-os
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String cpfcnpj = rs.getString("cpf_cnpj");

				pessoa = new Pessoa(id, nome, cpfcnpj, null, null, null, null, null, null, null, null, null);

				lista.add(pessoa);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

}
