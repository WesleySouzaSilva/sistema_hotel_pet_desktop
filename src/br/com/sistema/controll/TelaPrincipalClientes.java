package br.com.sistema.controll;

import br.com.sistema.model.Email;
import br.com.sistema.model.Endereco;
import br.com.sistema.model.Pessoa;
import br.com.sistema.model.Telefone;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TelaPrincipalClientes {

	@FXML
	private TextField txtPesquisa;

	@FXML
	private Button btnPesquisar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnEditarCliente;

	@FXML
	private ComboBox<String> cmbBusca;

	@FXML
	public TableView<Pessoa> tbClientes;

	@FXML
	private TableColumn<Pessoa, String> clnNome;

	@FXML
	private TableColumn<Pessoa, String> clnCpfCnpj;

	@FXML
	private TableView<Endereco> tbEndereco;

	@FXML
	private TableView<Endereco> tbEndereco2;

	@FXML
	private TableColumn<Endereco, String> clnRua;

	@FXML
	private TableColumn<Endereco, String> clnBairro;

	@FXML
	private TableColumn<Endereco, String> clnNumero;

	@FXML
	private TableColumn<Endereco, String> clnCep;

	@FXML
	private TableColumn<Endereco, String> clnCidade;

	@FXML
	private TableColumn<Endereco, String> clnEstado;

	@FXML
	private TableView<Telefone> tbTelefone;

	@FXML
	private TableColumn<Telefone, String> clnCelular;

	@FXML
	private TableColumn<Telefone, String> clnComercial;

	@FXML
	private TableColumn<Telefone, String> clnResidencial;

	@FXML
	private TableColumn<Telefone, String> clnWhatsapp;

	@FXML
	private TableView<Email> tbEmail;

	@FXML
	private TableColumn<Email, String> clnEmail;

	@FXML
	private TableColumn<Email, String> clnEmailNFS;
	
	@FXML
	private Label lblNome;

	@FXML
	private Label lblCpfCnpj;

	@FXML
	private Label lblRg;

	@FXML
	private Label lblData;

	@FXML
	private Label lblSexo;

	@FXML
	private Label lblRua;

	@FXML
	private Label lblNumero;

	@FXML
	private Label lblBairro;

	@FXML
	private Label lblCidade;

	@FXML
	private Label lblEstado;

	@FXML
	private Label lblCep;

	@FXML
	private Label lblTelComercial;

	@FXML
	private Label lblTelResidencial;

	@FXML
	private Label lblTelCelular;

	@FXML
	private Label lblTelWhatsapp;

	@FXML
	private Label lblEmail;

	@FXML
	private Label lblEmailNfs;

	@FXML
	private Label lblHora;

	@FXML
	private MenuItem menuCadastroPF;

	@FXML
	private MenuItem menuCadastroPJ;


	public void initialize() {

	}
}
