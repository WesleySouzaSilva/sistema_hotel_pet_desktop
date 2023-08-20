package br.com.sistema.conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


abstract public class AbstractGenericDAO<G> {

	final protected Connection dbConnection;

	public AbstractGenericDAO(Conexao conexao) {
		this.dbConnection = conexao.getConexao();
	}

	abstract public boolean inserir(G pojo);

	abstract public boolean apagar(G pojo);

	abstract public boolean atualizar(G novo);

	abstract public List<G> listarTodos();

	protected Integer ultimoID(String tabela) {
		try {
			ResultSet rs = dbConnection.createStatement().executeQuery("SELECT MAX(id) as id FROM " + tabela);
			if (rs.next())
				return rs.getInt("id");
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
