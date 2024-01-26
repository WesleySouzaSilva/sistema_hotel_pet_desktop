package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import br.com.sistema.conexao.Conexao;
import br.com.sistema.model.Email;
import br.com.sistema.model.Endereco;
import br.com.sistema.model.Pessoa;
import br.com.sistema.model.Telefone;
import br.com.sistema.model.dao.PessoaDAO;

public class PessoaTest {

	private static Conexao conexao = new Conexao("sistema_pet", "sistema_pet", "bd_pet486231");
	private static PessoaDAO pessoaDAO = new PessoaDAO(conexao);
	private static Integer pessoaId;
	private static Endereco endereco = new Endereco(5, null, null, null, null, null, null);
	private static Email email = new Email(5, null);
	private static Telefone telefone = new Telefone(5, null, null, null, null);
	private static Date dataAtual = new Date();

	@BeforeAll
	public static void testInserirPessoaValido() {
		java.sql.Date data = new java.sql.Date(dataAtual.getTime());
		Pessoa pessoa = new Pessoa(null, "JOAO DA SILVA", "000.000.000-70", "000000000", "MASCULINO", data, endereco,
				telefone, email, "PF", "SIM", null);

		boolean inserido = pessoaDAO.inserir(pessoa);

		assertTrue(inserido);
		assertNotNull(pessoa.getId());
		pessoaId = pessoa.getId();
		System.out.println("pegou id : " + pessoaId);
	}

	@Test
	public void testInserirPessoaInvalido() {
		Pessoa pessoa = new Pessoa(null, null, null, null, null, null, null, null, null, null, null, null);

		boolean sucesso = pessoaDAO.inserir(pessoa);

		assertFalse(sucesso);

	}

	@Test
	public void testInserirPessoaValidoPJ() {
		Pessoa pessoa = new Pessoa(null, "EMPRESA DO JOAO", "00.000.000/0000-00", null, null, null, endereco, telefone,
				email, "PJ", "SIM", null);
		boolean sucesso = pessoaDAO.inserirPJ(pessoa);

		assertTrue(sucesso);
		assertNotNull(pessoa.getId());

	}

	@Test
	public void testInserirPessoaInvalidoPJ() {
		Pessoa pessoa = new Pessoa(null, null, null, null, null, null, null, null, null, null, null, null);
		boolean sucesso = pessoaDAO.inserirPJ(pessoa);

		assertFalse(sucesso);

	}

	@Test
	public void testInativarPessoaValido() {

		boolean atualizado = pessoaDAO.inativarCliente(5);

		assertTrue(atualizado);
	}

	@Test
	public void testInativarPessoaInvalido() {

		boolean atualizado = pessoaDAO.inativarCliente(1000);

		assertFalse(atualizado);
	}

	@Test
	public void testAtualizarPessoaValido() {
		java.sql.Date data = new java.sql.Date(dataAtual.getTime());
		Pessoa pessoa = new Pessoa(null, "JOAO DA SILVA", "000.000.000-70", "000000000", "MASCULINO", data, endereco,
				telefone, email, "PF", "SIM", null);
		pessoaDAO.inserir(pessoa);
		pessoa.setId(pessoa.getId());
		pessoa.setNome("NOVO NOME DA PESSOA");

		boolean atualizado = pessoaDAO.atualizar(pessoa);

		assertTrue(atualizado);
	}

	@Test
	public void testAtualizarPessoaInvalido() {
		Pessoa pessoa = new Pessoa(null, null, null, null, null, null, null, null, null, null, null, null);
		pessoa.setNome(null);

		boolean atualizado = pessoaDAO.atualizar(pessoa);

		assertFalse(atualizado);
	}

	@Test
	public void testBuscarListaEmailValido() {
		List<Pessoa> pessoas = pessoaDAO.listarTodos();

		assertNotNull(pessoas);

	}

	@AfterAll
	public static void testApagarPessoaValido() {
		Pessoa pessoa = new Pessoa(pessoaId, null, null, null, null, null, endereco, telefone, email, null, null, null);

		boolean deletado = pessoaDAO.apagar(pessoa);

		assertTrue(deletado);
	}

}
