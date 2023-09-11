package br.com.sistema.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistema.conexao.AbstractGenericDAO;
import br.com.sistema.conexao.Conexao;
import br.com.sistema.model.Telefone;

public class TelefoneDAO extends AbstractGenericDAO<Telefone> {

	public TelefoneDAO(Conexao conexao) {
		super(conexao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean inserir(Telefone pojo) {
		if (pojo.getTelCelular() != null || pojo.getTelComercial() != null || pojo.getTelResidencial() != null
				|| pojo.getTelWhatsapp() != null) {
			String sqlTelefone = "INSERT INTO telefone(tel_celular, tel_comercial, tel_residencial, tel_whatsapp) VALUES (?, ?, ?, ?)";
			try {
				PreparedStatement s = dbConnection.prepareStatement(sqlTelefone);
				s.setString(1, pojo.getTelCelular());
				s.setString(2, pojo.getTelComercial());
				s.setString(3, pojo.getTelResidencial());
				s.setString(4, pojo.getTelWhatsapp());

				int retornaTel = s.executeUpdate();
				if (retornaTel > 0) {
					// salva o id gerado pelo banco no próprio objeto
					pojo.setId(ultimoID("telefone"));
				}

				return retornaTel > 0;

			} catch (SQLException e) {
				e.printStackTrace();

				return false;
			}
		}
		return false;
	}

	@Override
	public boolean apagar(Telefone pojo) {
		if (pojo.getId() != null) {
			String sqlTelefone = "DELETE FROM telefone WHERE id =?";
			try {

				PreparedStatement cmdTelefone = dbConnection.prepareStatement(sqlTelefone);
				cmdTelefone.setInt(1, pojo.getId());
				int retorno = cmdTelefone.executeUpdate();
				cmdTelefone.close();

				return retorno > 0;

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean atualizar(Telefone novo) {
		if (novo.getId() != null) {
			String sql = "UPDATE telefone SET tel_celular = ?, tel_comercial = ?, tel_residencial = ?, tel_whatsapp = ? WHERE id = ?";

			try {
				PreparedStatement cmd = dbConnection.prepareStatement(sql);
				cmd.setString(1, novo.getTelCelular());
				cmd.setString(2, novo.getTelComercial());
				cmd.setString(3, novo.getTelResidencial());
				cmd.setString(4, novo.getTelWhatsapp());
				cmd.setInt(5, novo.getId());
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
	public List<Telefone> listarTodos() {
		String sql = "SELECT * 	FROM telefone";
		Telefone telefone = null;
		List<Telefone> lista = new ArrayList<>();
		try {
			PreparedStatement cmd = dbConnection.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();

			if (rs.next()) {
				String telCelular = rs.getString("tel_celular");
				String telComercial = rs.getString("tel_comercial");
				String telResidencial = rs.getString("tel_residencial");
				String telWhatsapp = rs.getString("tel_whatsapp");

				telefone = new Telefone(null, telComercial, telCelular, telResidencial, telWhatsapp);
				lista.add(telefone);
			}

			cmd.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public Telefone buscar(Integer id) {
		if (id != null) {
			String sql = "SELECT t.id, t.tel_celular, t.tel_comercial, t.tel_residencial, t.tel_whatsapp, p.id, p.telefone_id FROM Telefone AS t, Pessoa as p WHERE p.id = '"
					+ id + "' AND t.id = p.telefone_id";
			Telefone telefone = null;
			try {
				PreparedStatement cmd = dbConnection.prepareStatement(sql);
				ResultSet rs = cmd.executeQuery();

				if (rs.next()) {
					String telCelular = rs.getString("t.tel_celular");
					String telComercial = rs.getString("t.tel_comercial");
					String telResidencial = rs.getString("t.tel_residencial");
					String telWhatsapp = rs.getString("t.tel_whatsapp");

					telefone = new Telefone(null, telComercial, telCelular, telResidencial, telWhatsapp);
				}

				cmd.close();

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}

			return telefone;
		}
		return null;
	}

}
