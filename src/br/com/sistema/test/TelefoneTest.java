package br.com.sistema.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import br.com.sistema.conexao.Conexao;
import br.com.sistema.model.Telefone;
import br.com.sistema.model.dao.TelefoneDAO;

public class TelefoneTest {

	private static Conexao conexao = new Conexao("sistema_pet", "sistema_pet", "bd_pet486231");
	private static TelefoneDAO telefoneDAO = new TelefoneDAO(conexao);
	private static Integer telefoneId;

	@BeforeAll
	public static void testInserirTelefoneValido() {
		Telefone telefone = new Telefone(null, "4299999999", "4299999999", "4299999999", "4299999999");

		boolean inserido = telefoneDAO.inserir(telefone);

		assertTrue(inserido);
		assertNotNull(telefone.getId());
		telefoneId = telefone.getId();
	}

	@Test
	public void testInserirTelefonmeInvalido() {
		Telefone telefone = new Telefone(null, null, null, null, null);

		boolean sucesso = telefoneDAO.inserir(telefone);

		assertFalse(sucesso);

	}

	@Test
	public void testAtualizarTelefoneValido() {
		Telefone telefone = new Telefone(null, "4200000000", "4200000000", "4200000000", "4200000000");
		telefoneDAO.inserir(telefone);
		telefone.setTelCelular("42998989898");
		boolean atualizado = telefoneDAO.atualizar(telefone);

		assertTrue(atualizado);
	}

	@Test
	public void testAtualizarTelefoneInvalido() {
		Telefone telefone = new Telefone(null, "4200000000", "4200000000", "4200000000", "4200000000");

		boolean atualizado = telefoneDAO.atualizar(telefone);

		assertFalse(atualizado);
	}

	@Test
	public void testBuscarListaTelefoneValido() {
		List<Telefone> telefones = telefoneDAO.listarTodos();

		assertNotNull(telefones);

	}
	
	@Test
	public void testBuscarTelefoneExistenteValido() {
		Integer pessoaId = 1;
		Telefone telefoneEncontrado = telefoneDAO.buscar(pessoaId);

		assertNotNull(telefoneEncontrado);
	}
	
	@Test
	public void testBuscarTelefoneExistenteInvalido() {
		Integer pessoaId = 100000000;
		Telefone telefoneEncontrado = telefoneDAO.buscar(pessoaId);

		assertNull(telefoneEncontrado);
	}
	
	@Test
	public void testApagarTelefoneInvalido() {
		Telefone telefone = new Telefone(null, null, null, null, null);

		boolean deletado = telefoneDAO.apagar(telefone);

		assertFalse(deletado);
	}

	@AfterAll
	public static void testApagarTelefoneValido() {
		Telefone telefone = new Telefone(telefoneId, "4200000000", "4200000000", "4200000000", "4200000000");

		boolean deletado = telefoneDAO.apagar(telefone);

		assertTrue(deletado);
	}

}
