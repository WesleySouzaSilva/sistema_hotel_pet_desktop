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
import br.com.sistema.model.Endereco;
import br.com.sistema.model.dao.EnderecoDAO;

public class EnderecoTest {

    private static Conexao conexao = new Conexao("sistema_pet", "sistema_pet", "bd_pet486231");
    private static EnderecoDAO enderecoDAO = new EnderecoDAO(conexao);
    private static Integer enderecoId;

    @BeforeAll
    public static void testInserirEnderecoValido() {
        Endereco endereco = new Endereco(null, "Rua ABC", "Bairro XPTO", "678", "Cidade A", "Estado AA", "12345-345");

        boolean inserido = enderecoDAO.inserir(endereco);

        assertTrue(inserido);
        assertNotNull(endereco.getId());
        enderecoId = endereco.getId();
    }

    @Test
    public void testInserirEnderecoInvalido() {
        Endereco endereco = new Endereco(null, null, null, null, null, null, null);

        boolean sucesso = enderecoDAO.inserir(endereco);

        assertFalse(sucesso);
    }

    @Test
    public void testAtualizarEnderecoValido() {
        Endereco endereco = new Endereco(enderecoId, "Rua ABC", "Bairro XPTO", "678", "Cidade A", "Estado AA", "12345-345");
        enderecoDAO.inserir(endereco);
        endereco.setRua("Nova Rua Alterada");

        boolean atualizado = enderecoDAO.atualizar(endereco);

        assertTrue(atualizado);
    }

    @Test
    public void testAtualizarEnderecoInvalido() {
        Endereco endereco = new Endereco(null, "Rua ABC", "Bairro XPTO", "678", "Cidade A", "Estado AA", "12345-345");

        boolean atualizado = enderecoDAO.atualizar(endereco);

        assertFalse(atualizado);
    }

    @Test
    public void testBuscarEnderecoExistenteValido() {
    	Integer pessoaId = 1;
        Endereco enderecoEncontrado = enderecoDAO.buscar(pessoaId);

        assertNotNull(enderecoEncontrado);
    }

    @Test
    public void testBuscarEnderecoExistenteInvalido() {
        Integer enderecoIdNaoExistente = 100000000;
        Endereco enderecoEncontrado = enderecoDAO.buscar(enderecoIdNaoExistente);

        assertNull(enderecoEncontrado);
    }

    @Test
    public void testBuscarListaEnderecoValido() {
        List<Endereco> enderecos = enderecoDAO.listarTodos();

        assertNotNull(enderecos);
    }

    @Test
    public void testApagarEnderecoInvalido() {
        Endereco endereco = new Endereco(null, null, null, null, null, null, null);

        boolean deletado = enderecoDAO.apagar(endereco);

        assertFalse(deletado);
    }

    @AfterAll
    public static void testApagarEnderecoValido() {
        Endereco endereco = new Endereco(enderecoId, "Rua ABC", "Bairro XPTO", "678", "Cidade A", "Estado AA", "12345-345");

        boolean deletado = enderecoDAO.apagar(endereco);

        assertTrue(deletado);
    }
}

