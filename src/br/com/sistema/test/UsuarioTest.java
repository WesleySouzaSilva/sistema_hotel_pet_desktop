package br.com.sistema.test;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import br.com.sistema.conexao.Conexao;
import br.com.sistema.model.Usuario;
import br.com.sistema.model.dao.UsuarioDAO;

public class UsuarioTest {

	private static Conexao conexao = new Conexao("sistema_pet", "sistema_pet", "bd_pet486231");
	private static Integer usuarioId = null;

	@BeforeAll
	public static void testInserirUsuarioComAtributosValidos() {
		// criacao do usuario deve ser antes dos demais tests
		UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

		Usuario usuario = new Usuario(null, "Alice", "senha123", "admin");
		boolean inserido = usuarioDAO.inserir(usuario);
		usuarioId = usuario.getId();
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

		Usuario usuario = new Usuario(usuarioId, "Bob", "novaSenha", "user");
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
	
	@Test
    public void testBuscarNomeComNomeExistente() {
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        String nomeExistente = "Alice";

        List<Usuario> usuarios = usuarioDAO.buscarNome(nomeExistente);

        assertNotNull(usuarios);
        assertFalse(!usuarios.isEmpty());
       
    }

    @Test
    public void testBuscarNomeComNomeNaoExistente() {
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        String nomeNaoExistente = "NomeQueNaoExiste";

        List<Usuario> usuarios = usuarioDAO.buscarNome(nomeNaoExistente);

        assertNotNull(usuarios);
        assertTrue(usuarios.isEmpty());
    }

    @Test
    public void testLogarComCredenciaisValidas() {
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        String usuarioValido = "Alice";
        String senhaValida = "senha123";

        boolean logado = usuarioDAO.logar(usuarioValido, senhaValida);

        assertTrue(logado);
    }

    @Test
    public void testLogarComCredenciaisInvalidas() {
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        String usuarioInvalido = "UsuarioInvalido";
        String senhaInvalida = "SenhaInvalida";

        boolean logado = usuarioDAO.logar(usuarioInvalido, senhaInvalida);

        assertFalse(logado);
    }

    @Test
    public void testBuscarIdComIdExistente() {
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        Integer idExistente = usuarioId;

        List<Usuario> usuarios = usuarioDAO.buscarId(idExistente);

        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty());
       
    }

    @Test
    public void testBuscarIdComIdNaoExistente() {
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        Integer idNaoExistente = 10000000;

        List<Usuario> usuarios = usuarioDAO.buscarId(idNaoExistente);

        assertNotNull(usuarios);
        assertTrue(usuarios.isEmpty());
    }
    
    @AfterAll
	public static void testDeletarUsuarioValido() {
    	// esse test deve ser executado por ultimo
		UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

		Usuario usuario = new Usuario(usuarioId, null, null, null);
		boolean deletado = usuarioDAO.apagar(usuario);

		assertTrue(deletado);
	}
}
