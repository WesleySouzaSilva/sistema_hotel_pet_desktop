package br.com.sistema.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import br.com.sistema.conexao.Conexao;
import br.com.sistema.model.Usuario;
import br.com.sistema.model.dao.UsuarioDAO;

public class UsuarioDAOTest {

	private Conexao conexao = new Conexao("sistema_pet", "sistema_pet", "bd_pet486231");

	@Test
	public void testInserirUsuarioValido() {
		UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

		Usuario usuario = new Usuario(1, "Alice", "senha123", "admin");
		boolean inserido = usuarioDAO.inserir(usuario);

		assertTrue(inserido);
	}

	@Test
	public void testInserirUsuarioComAtributosNulos() {
		UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

		Usuario usuario = new Usuario(null, null, null, null);
		boolean inserido = usuarioDAO.inserir(usuario);

		assertFalse(inserido);
	}

	@Test
	public void testAtualizarUsuarioValido() {
		UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

		Usuario usuario = new Usuario(1, "Bob", "novaSenha", "user");
		boolean atualizado = usuarioDAO.atualizar(usuario);

		assertTrue(atualizado);
	}

	@Test
	public void testAtualizarUsuarioComAtributosNulos() {
		UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

		Usuario usuario = new Usuario(null, null, null, null);
		boolean atualizado = usuarioDAO.atualizar(usuario);

		assertFalse(atualizado);
	}

	@Test
	public void testDeletarUsuarioValido() {
		UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

		Usuario usuario = new Usuario(1, null, null, null);
		boolean deletado = usuarioDAO.apagar(usuario);

		assertTrue(deletado);
	}

	@Test
	public void testDeletarUsuarioComIdNulo() {
		UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

		Usuario usuario = new Usuario(null, null, null, null);
		boolean deletado = usuarioDAO.apagar(usuario);

		assertFalse(deletado);
	}

	@Test
	public void testListarUsuarios() {
		UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

		List<Usuario> usuarios = usuarioDAO.listarTodos();

		assertNotNull(usuarios);
		assertFalse(usuarios.isEmpty());
	}

}
