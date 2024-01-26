package model;

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
	static UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
	private static Integer usuarioId = null;

	@BeforeAll
	// criacao do usuario deve ser antes dos demais tests
	public static void testInserirUsuarioComAtributosValidos() {

		Usuario usuario = new Usuario(null, "Alice", "senha123", "admin");
		boolean inserido = usuarioDAO.inserir(usuario);
		usuarioId = usuario.getId();
		assertTrue(inserido);
		
	}

	@Test
	public void testInserirUsuarioComAtributosNulos() {

		Usuario usuario = new Usuario(null, null, null, null);
		boolean inserido = usuarioDAO.inserir(usuario);

		assertFalse(inserido);
	}

	@Test
	public void testAtualizarUsuarioValido() {

		Usuario usuario = new Usuario(usuarioId, "Bob", "novaSenha", "user");
		boolean atualizado = usuarioDAO.atualizar(usuario);
		
		assertTrue(atualizado);
	}

	@Test
	public void testAtualizarUsuarioComAtributosNulos() {

		Usuario usuario = new Usuario(null, null, null, null);
		boolean atualizado = usuarioDAO.atualizar(usuario);

		assertFalse(atualizado);
	}

	@Test
	public void testDeletarUsuarioComIdNulo() {

		Usuario usuario = new Usuario(null, null, null, null);
		boolean deletado = usuarioDAO.apagar(usuario);

		assertFalse(deletado);
	}

	@Test
	public void testListarUsuarios() {

		List<Usuario> usuarios = usuarioDAO.listarTodos();

		assertNotNull(usuarios);
		assertFalse(usuarios.isEmpty());
	}
	
	@Test
    public void testBuscarNomeComNomeExistente() {
        String nomeExistente = "Alice";

        List<Usuario> usuarios = usuarioDAO.buscarNome(nomeExistente);

        assertNotNull(usuarios);
        assertFalse(!usuarios.isEmpty());
       
    }

    @Test
    public void testBuscarNomeComNomeNaoExistente() {
        String nomeNaoExistente = "NomeQueNaoExiste";

        List<Usuario> usuarios = usuarioDAO.buscarNome(nomeNaoExistente);

        assertNotNull(usuarios);
        assertTrue(usuarios.isEmpty());
    }

    @Test
    public void testLogarComCredenciaisValidas() {
        String usuarioValido = "Alice";
        String senhaValida = "senha123";

        boolean logado = usuarioDAO.logar(usuarioValido, senhaValida);

        assertTrue(logado);
    }

    @Test
    public void testLogarComCredenciaisInvalidas() {
        String usuarioInvalido = "UsuarioInvalido";
        String senhaInvalida = "SenhaInvalida";

        boolean logado = usuarioDAO.logar(usuarioInvalido, senhaInvalida);

        assertFalse(logado);
    }

    @Test
    public void testBuscarIdComIdExistente() {
        Integer idExistente = usuarioId;

        List<Usuario> usuarios = usuarioDAO.buscarId(idExistente);

        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty());
       
    }

    @Test
    public void testBuscarIdComIdNaoExistente() {
        Integer idNaoExistente = 10000000;

        List<Usuario> usuarios = usuarioDAO.buscarId(idNaoExistente);

        assertNotNull(usuarios);
        assertTrue(usuarios.isEmpty());
    }
    
    @AfterAll
    // esse test deve ser executado por ultimo
	public static void testDeletarUsuarioValido() {
		Usuario usuario = new Usuario(usuarioId, null, null, null);
		boolean deletado = usuarioDAO.apagar(usuario);

		assertTrue(deletado);
	}
}
