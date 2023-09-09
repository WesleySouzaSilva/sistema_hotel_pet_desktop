package br.com.sistema.controll;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import br.com.sistema.conexao.Conexao;
import br.com.sistema.listeners.ListenerParaMaiusculas;
import br.com.sistema.model.Email;
import br.com.sistema.model.Endereco;
import br.com.sistema.model.Pessoa;
import br.com.sistema.model.Telefone;
import br.com.sistema.model.dao.EmailDAO;
import br.com.sistema.model.dao.EnderecoDAO;
import br.com.sistema.model.dao.PessoaDAO;
import br.com.sistema.model.dao.TelefoneDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	private Label lblHora;

	@FXML
	private MenuItem menuCadastroPF;

	@FXML
	private MenuItem menuCadastroPJ;

	private PessoaDAO pessoaDAO = null;
	private EnderecoDAO enderecoDAO = null;
	private TelefoneDAO telefoneDAO = null;
	private EmailDAO emailDAO = null;
	private static Integer pessoa_id;
	private Conexao conexao = new Conexao(Principal.getNomeBanco(), Principal.getUsuarioBanco(),
			Principal.getSenhaBanco());

	public void initialize() {

		comboBoxBusca();
		btnPesquisar.setOnAction(e -> {
			acaoPesquisarPessoa();
		});

		menuCadastroPF.setOnAction(e -> {
			try {
				chamarTela("TelaCadastroClientesPF");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		menuCadastroPJ.setOnAction(e -> {
			try {
				chamarTela("TelaCadastroClientesPJ");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		txtPesquisa.textProperty().addListener(new ListenerParaMaiusculas(txtPesquisa));
		
		txtPesquisa.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					acaoPesquisarPessoa();
				}

			}
		});
		tbClientes.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarPessoa((Pessoa) newValue));

		tbClientes.setOnMouseClicked(e -> {
			if (tbClientes.getSelectionModel().getSelectedItem() != null) {
				acaoBuscarCliente();
				buscarEmail();
				buscarEndereco();
				buscarEnderecoTabela();
				buscarTelefone();
			}

		});

		btnEditarCliente.setOnAction(e -> {
			if (tbClientes.getSelectionModel().getSelectedItem() != null) {
				switch (getCliente(pessoa_id, "tipo")) {
				case "PF":
					try {
						chamarTela("TelaEditarClientePF");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;

				case "PJ":
					try {
						chamarTela("TelaEditarClientePJ");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;

				default:
					break;
				}
			} else {
				Alert dlg = new Alert(AlertType.WARNING);
				dlg.setContentText("Selecione o cliente que deseja EDITAR!");
				dlg.showAndWait();
				btnEditarCliente.requestFocus();
				return;
			}
		});

		btnExcluir.setOnAction(e -> {
			if (tbClientes.getSelectionModel().getSelectedItem() != null) {
				excluir();
				acaoPesquisarPessoa();
			} else {
				Alert dlg = new Alert(AlertType.WARNING);
				dlg.setContentText("Selecione o cliente que deseja EXCLUIR!");
				dlg.showAndWait();
				btnEditarCliente.requestFocus();
				return;
			}
		});


	}

	private void selecionarPessoa(Pessoa pessoa) {
		if (pessoa != null) {
			pessoa_id = pessoa.getId();
			
		}
	}
	
	public Integer getIdPessoa() {
		return pessoa_id;
	}


	private void acaoPesquisarPessoa() {
		if (cmbBusca.getValue() == null) {
			Alert dlg = new Alert(AlertType.ERROR);
			ValidationFields.checkEmptyFields(cmbBusca);
			dlg.setContentText("selecione o campo BUSCAR!");
			dlg.showAndWait();
			cmbBusca.requestFocus();
			return;
		} else {
			switch (cmbBusca.getValue()) {
			case "Nome":
				if (txtPesquisa.getText().isEmpty()) {
					Alert dlg = new Alert(AlertType.INFORMATION);
					dlg.setContentText("Preencha o campo pesquisa com o Nome do Cliente!");
					dlg.showAndWait();
					ValidationFields.checkEmptyFields(txtPesquisa);
					txtPesquisa.requestFocus();
					return;

				} else {
					this.pessoaDAO = Principal.getPessoaDAO();
					clnNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
					clnCpfCnpj.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("cpfcnpj"));
					ObservableList<Pessoa> lista = FXCollections.observableArrayList(pessoaDAO.buscarCliente("nome", txtPesquisa.getText()));
					tbClientes.setItems(lista);
					conexao.fecharConexao();
				}

				break;
			case "Cpf_Cnpj":
				if (txtPesquisa.getText().isEmpty()) {
					Alert dlg = new Alert(AlertType.INFORMATION);
					dlg.setContentText("Preencha o campo pesquisa com o Cpf_Cnpj do cliente!!!");
					dlg.showAndWait();
					ValidationFields.checkEmptyFields(txtPesquisa);
					txtPesquisa.requestFocus();

				} else {
					this.pessoaDAO = Principal.getPessoaDAO();
					clnNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("Nome"));
					clnCpfCnpj.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("cpfcnpj"));
					ObservableList<Pessoa> lista = FXCollections
							.observableArrayList(pessoaDAO.buscarCliente("cpf_cnpj", formatarCpfCnpj(txtPesquisa.getText())));
					tbClientes.setItems(lista);
					conexao.fecharConexao();

				}

				break;
			case "Todos":
				this.pessoaDAO = Principal.getPessoaDAO();
				clnNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("Nome"));
				clnCpfCnpj.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("cpfcnpj"));
				ObservableList<Pessoa> lista = FXCollections.observableArrayList(pessoaDAO.listarTodos());
				tbClientes.setItems(lista);
				conexao.fecharConexao();

				break;

			default:
				break;

			}

		}
	}

	private void acaoBuscarCliente() {
		this.pessoaDAO = Principal.getPessoaDAO();
		for (Pessoa pessoa : pessoaDAO.buscarDadosPorId(pessoa_id)) {
			lblNome.setText(pessoa.getNome());
			lblCpfCnpj.setText(pessoa.getCpfcnpj());
			lblData.setText(pessoa.getDataNascimentoFormatada());
			lblBairro.setText(pessoa.getEndereco().getBairro());
			lblCep.setText(pessoa.getEndereco().getCep());
			lblCidade.setText(pessoa.getEndereco().getCidade());
			lblEmail.setText(pessoa.getEmail().getEmail());
			lblRua.setText(pessoa.getEndereco().getRua());
			lblEstado.setText(pessoa.getEndereco().getUf());
			lblNumero.setText(pessoa.getEndereco().getNumero());
			lblSexo.setText(pessoa.getSexo());
			lblTelCelular.setText(pessoa.getTelefone().getTelCelular());
			lblTelComercial.setText(pessoa.getTelefone().getTelComercial());
			lblTelResidencial.setText(pessoa.getTelefone().getTelResidencial());
			lblTelWhatsapp.setText(pessoa.getTelefone().getTelWhatsapp());
			lblRg.setText(pessoa.getRg());
		}
		conexao.fecharConexao();

	}
	
	private void excluir() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(pessoa_id);
		ButtonType sim = new ButtonType("SIM", ButtonBar.ButtonData.YES);
		ButtonType nao = new ButtonType("NÃO", ButtonBar.ButtonData.NO);
		Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente excluir os dados do CLIENTE?", sim,
				nao);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get().equals(sim)) {
			this.pessoaDAO = Principal.getPessoaDAO();
			boolean sucesso = pessoaDAO.inativarCliente(pessoa_id);
			if (sucesso) {
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setHeaderText("Dados excluidos com sucesso!");
				alerta.showAndWait();
				limparCampos();
			}
			conexao.fecharConexao();
		}
	}

	private void comboBoxBusca() {
		ArrayList<String> listaBusca = new ArrayList<>();
		String nome = new String("Nome");
		String cpfCnpj = new String("Cpf_Cnpj");
		String todos = new String("Todos");

		listaBusca.add(nome);
		listaBusca.add(cpfCnpj);
		listaBusca.add(todos);

		cmbBusca.getItems().addAll(listaBusca);

	}
	
	private void chamarTela(String tela) throws IOException {
		Stage stage = new Stage();
		Image image = new Image("/br/com/sistema/icones/W3.png");

		stage.getIcons().add(image);
		URL FXML = this.getClass().getResource("/br/com/sistema/view/" + tela + ".fxml");

		Parent painel = (Parent) FXMLLoader.load(FXML);
		stage.setScene(new Scene(painel));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		stage.setResizable(false);
	}

	private void buscarEndereco() {
		this.enderecoDAO = Principal.getEnderecoDAO();
		clnRua.setCellValueFactory(new PropertyValueFactory<Endereco, String>("rua"));
		clnBairro.setCellValueFactory(new PropertyValueFactory<Endereco, String>("bairro"));
		clnNumero.setCellValueFactory(new PropertyValueFactory<Endereco, String>("numero"));
		ObservableList<Endereco> lista = FXCollections.observableArrayList(enderecoDAO.buscar(pessoa_id));
		tbEndereco.setItems(lista);
		conexao.fecharConexao();
	}

	private void buscarTelefone() {
		this.telefoneDAO = Principal.getTelefoneDAO();
		clnCelular.setCellValueFactory(new PropertyValueFactory<Telefone, String>("telCelular"));
		clnComercial.setCellValueFactory(new PropertyValueFactory<Telefone, String>("telComercial"));
		clnResidencial.setCellValueFactory(new PropertyValueFactory<Telefone, String>("telResidencial"));
		clnWhatsapp.setCellValueFactory(new PropertyValueFactory<Telefone, String>("telWhatsapp"));
		ObservableList<Telefone> lista = FXCollections.observableArrayList(telefoneDAO.buscar(pessoa_id));
		tbTelefone.setItems(lista);
		conexao.fecharConexao();
	}

	private void buscarEmail() {
		this.emailDAO = Principal.getEmailDAO();
		clnEmail.setCellValueFactory(new PropertyValueFactory<Email, String>("email"));
		ObservableList<Email> lista = FXCollections.observableArrayList(emailDAO.buscar(pessoa_id));
		tbEmail.setItems(lista);
		conexao.fecharConexao();
	}

	private void buscarEnderecoTabela() {
		this.enderecoDAO = Principal.getEnderecoDAO();
		clnCep.setCellValueFactory(new PropertyValueFactory<Endereco, String>("cep"));
		clnCidade.setCellValueFactory(new PropertyValueFactory<Endereco, String>("cidade"));
		clnEstado.setCellValueFactory(new PropertyValueFactory<Endereco, String>("uf"));
		ObservableList<Endereco> lista = FXCollections.observableArrayList(enderecoDAO.buscar(pessoa_id));
		tbEndereco2.setItems(lista);
		conexao.fecharConexao();
	}

	private void limparCampos() {
		tbClientes.getItems().clear();
		tbEmail.getItems().clear();
		tbEndereco.getItems().clear();
		tbEndereco2.getItems().clear();
		tbTelefone.getItems().clear();
		lblBairro.setText("");
		lblCep.setText("");
		lblCidade.setText("");
		lblCpfCnpj.setText("");
		lblData.setText("");
		lblEmail.setText("");
		lblEstado.setText("");
		lblNome.setText("");
		lblNumero.setText("");
		lblRg.setText("");
		lblRua.setText("");
		lblSexo.setText("");
		lblTelCelular.setText("");
		lblTelComercial.setText("");
		lblTelResidencial.setText("");
		lblTelWhatsapp.setText("");

	}

	private String formatarCpfCnpj(String campo) {
		Integer recebe = campo.length();
		if (recebe == 11) {
			String cpf = campo;
			String bloco1 = cpf.substring(0, 3);
			String bloco2 = cpf.substring(3, 6);
			String bloco3 = cpf.substring(6, 9);
			String bloco4 = cpf.substring(9, 11);
			cpf = bloco1 + "." + bloco2 + "." + bloco3 + "-" + bloco4;
			return cpf;
		} else {
			String cnpj = campo;
			String bloco1 = cnpj.substring(0, 2);
			String bloco2 = cnpj.substring(2, 5);
			String bloco3 = cnpj.substring(5, 8);
			String bloco4 = cnpj.substring(8, 12);
			String bloco5 = cnpj.substring(12, 14);
			cnpj = bloco1 + "." + bloco2 + "." + bloco3 + "/" + bloco4 + "-" + bloco5;
			return cnpj;
		}
	}

	private String getCliente(Integer id, String parametro) {
		conexao = new Conexao(Principal.getNomeBanco(), Principal.getUsuarioBanco(),
				Principal.getSenhaBanco());
		String nome = null;
		String sql = "SELECT * FROM pessoa WHERE id = '" + id + "'";

		try {
			PreparedStatement cmd = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			if (rs.next()) {
				nome = rs.getString(parametro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nome;

	}
}
