package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import br.com.sistema.conexao.Conexao;
import br.com.sistema.model.Email;
import br.com.sistema.model.dao.EmailDAO;

public class EmailTest {

	private static Conexao conexao = new Conexao("sistema_pet", "sistema_pet", "bd_pet486231");
	private static EmailDAO emailDAO = new EmailDAO(conexao);
	private static Integer emailId;

	@BeforeAll
	public static void testInserirEmailValido() {
		Email email = new Email(null, "test@example.com");

		boolean inserido = emailDAO.inserir(email);

		assertTrue(inserido);
		assertNotNull(email.getId());
		emailId = email.getId();
	}

	@Test
	public void testInserirEmailComEmailInvalido() {
		Email email = new Email(null, null);

		boolean sucesso = emailDAO.inserir(email);

		assertFalse(sucesso);

	}

	@Test
	public void testAtualizarEmailValido() {
		Email email = new Email(emailId, "test@example.com");
		emailDAO.inserir(email);
		email.setEmail("updated@example.com");

		boolean atualizado = emailDAO.atualizar(email);

		assertTrue(atualizado);
	}

	@Test
	public void testAtualizarEmailInvalido() {
		Email email = new Email(null, "test@example.com");
		emailDAO.inserir(email);
		email.setEmail(null);

		boolean atualizado = emailDAO.atualizar(email);

		assertFalse(atualizado);
	}

	@Test
	public void testBuscarEmailExistenteValido() {
		Integer pessoaId = 1;
		Email emailEncontrado = emailDAO.buscar(pessoaId);

		assertNotNull(emailEncontrado);
	}

	@Test
	public void testBuscarEmailExistenteInvalido() {
		Email emailNaoExistente = emailDAO.buscar(1000000000);

		assertNull(emailNaoExistente);
	}

	@Test
	public void testBuscarListaEmailValido() {
		List<Email> emails = emailDAO.listarTodos();

		assertNotNull(emails);

	}

	@AfterAll
	public static void testApagarEmailValido() {
		Email email = new Email(emailId, "updated@example.com");
		emailDAO.inserir(email);

		boolean deletado = emailDAO.apagar(email);

		assertTrue(deletado);
	}

}
