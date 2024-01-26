package br.com.sistema.controll;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import br.com.sistema.conexao.Conexao;
import br.com.sistema.filtros.FiltroEmail;
import br.com.sistema.filtros.FiltroFone;
import br.com.sistema.filtros.FiltroInteiro;
import br.com.sistema.filtros.FiltroLetras;
import br.com.sistema.listeners.ListenerFormatarCep;
import br.com.sistema.listeners.ListenerFormatarCpf;
import br.com.sistema.listeners.ListenerFormatarFone;
import br.com.sistema.listeners.ListenerParaMaiusculas;
import br.com.sistema.listeners.ListenerParaMinusculas;
import br.com.sistema.listeners.ListenerValidarEmail;
import br.com.sistema.model.Cidade;
import br.com.sistema.model.Email;
import br.com.sistema.model.Endereco;
import br.com.sistema.model.Estado;
import br.com.sistema.model.Pessoa;
import br.com.sistema.model.Telefone;
import br.com.sistema.model.dao.CidadeDAO;
import br.com.sistema.model.dao.EmailDAO;
import br.com.sistema.model.dao.EnderecoDAO;
import br.com.sistema.model.dao.EstadoDAO;
import br.com.sistema.model.dao.PessoaDAO;
import br.com.sistema.model.dao.TelefoneDAO;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class TelaEditarClientePF {

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtRg;

	@FXML
	private ComboBox<String> cmbSexo;

	@FXML
	private TextField txtDataNascimento;

	@FXML
	private TextField txtRua;

	@FXML
	private TextField txtNumero;

	@FXML
	private TextField txtBairro;

	@FXML
	private TextField txtCep;

	@FXML
	private ComboBox<Estado> cmbUf;

	@FXML
	private ComboBox<Cidade> cmbCidade;

	@FXML
	private TextField txtTelComercial;

	@FXML
	private TextField txtTelResidencial;

	@FXML
	private TextField txtTelCelular;

	@FXML
	private TextField txtTelWhatsapp;

	@FXML
	private TextField txtEmail;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnSair;

	private EnderecoDAO enderecoDAO = null;
	private EstadoDAO estadoDAO = null;
	private CidadeDAO cidadeDAO = null;
	private TelefoneDAO telefoneDAO = null;
	private EmailDAO emailDAO = null;
	private PessoaDAO pessoaDAO = null;
	private Date dataSql;
	private Date dataHoje;
	private Date dataMin;
	private TelaPrincipalClientes telaPrincipalClientes = new TelaPrincipalClientes();
	private Conexao conexao = new Conexao(Principal.getNomeBanco(), Principal.getUsuarioBanco(),
			Principal.getSenhaBanco());
	private static Integer telefoneId = null, enderecoId = null, emailId = null;

	public void initialize() {

		carregaDados();
		carregaComboBoxSexo();
		carregaComboBoxEstados();

		cmbUf.setOnAction(e -> {
			comboBoxCidadePorEstado();
		});

		btnSalvar.setOnAction(e -> {
			try {
				acaoSalvar();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		btnSair.setOnAction(e -> {
			acaoSairTela();
		});

		txtCpf.addEventFilter(KeyEvent.KEY_TYPED, new FiltroInteiro(11));
		txtCpf.focusedProperty().addListener(new ListenerFormatarCpf(txtCpf));

		txtNome.textProperty().addListener(new ListenerParaMaiusculas(txtNome));
		txtNome.addEventFilter(KeyEvent.KEY_TYPED, new FiltroLetras());

		txtBairro.textProperty().addListener(new ListenerParaMaiusculas(txtBairro));
		txtBairro.addEventFilter(KeyEvent.KEY_TYPED, new FiltroLetras());

		txtCep.addEventFilter(KeyEvent.KEY_TYPED, new FiltroInteiro(8));
		txtCep.focusedProperty().addListener(new ListenerFormatarCep(txtCep));
		txtNumero.addEventFilter(KeyEvent.KEY_TYPED, new FiltroInteiro(9));
		txtRg.addEventFilter(KeyEvent.KEY_TYPED, new FiltroInteiro(9));

		txtRua.textProperty().addListener(new ListenerParaMaiusculas(txtRua));
		txtRua.addEventFilter(KeyEvent.KEY_TYPED, new FiltroLetras());

		txtTelCelular.addEventFilter(KeyEvent.KEY_TYPED, new FiltroFone());
		txtTelCelular.focusedProperty().addListener(new ListenerFormatarFone(txtTelCelular));
		txtTelComercial.addEventFilter(KeyEvent.KEY_TYPED, new FiltroFone());
		txtTelComercial.focusedProperty().addListener(new ListenerFormatarFone(txtTelComercial));
		txtTelResidencial.addEventFilter(KeyEvent.KEY_TYPED, new FiltroFone());
		txtTelResidencial.focusedProperty().addListener(new ListenerFormatarFone(txtTelResidencial));
		txtTelWhatsapp.addEventFilter(KeyEvent.KEY_TYPED, new FiltroFone());
		txtTelWhatsapp.focusedProperty().addListener(new ListenerFormatarFone(txtTelWhatsapp));

		txtEmail.addEventFilter(KeyEvent.KEY_TYPED, new FiltroEmail());
		txtEmail.textProperty().addListener(new ListenerParaMinusculas(txtEmail));
		txtEmail.focusedProperty().addListener(new ListenerValidarEmail(txtEmail));

		txtNome.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					txtCpf.requestFocus();
					return;
				}

			}
		});

		txtCpf.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					txtRg.requestFocus();
					return;
				}

			}
		});

		txtRg.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					txtDataNascimento.requestFocus();
					return;
				}

			}
		});

		txtDataNascimento.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					cmbSexo.requestFocus();
					return;
				}

			}
		});

		cmbSexo.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					txtRua.requestFocus();
					return;
				}

			}
		});

		txtRua.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					txtBairro.requestFocus();
					return;
				}

			}
		});

		txtBairro.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					txtNumero.requestFocus();
					return;
				}

			}
		});

		txtNumero.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					cmbUf.requestFocus();
					return;
				}

			}
		});

		cmbUf.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					cmbCidade.requestFocus();
					return;
				}

			}
		});

		cmbCidade.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					txtCep.requestFocus();
					return;
				}

			}
		});
	}

	private void carregaDados() {
		this.pessoaDAO = Principal.getPessoaDAO();
		for (Pessoa pessoa : pessoaDAO.buscarDadosPorId(telaPrincipalClientes.getIdPessoa())) {
			txtNome.setText(pessoa.getNome());
			txtCpf.setText(pessoa.getCpfcnpj());
			txtDataNascimento.setText(pessoa.getDataNascimentoFormatada());
			txtBairro.setText(pessoa.getEndereco().getBairro());
			txtCep.setText(pessoa.getEndereco().getCep());
			txtEmail.setText(pessoa.getEmail().getEmail());
			txtRua.setText(pessoa.getEndereco().getRua());
			txtNumero.setText(pessoa.getEndereco().getNumero());
			txtTelCelular.setText(pessoa.getTelefone().getTelCelular());
			txtTelComercial.setText(pessoa.getTelefone().getTelComercial());
			txtTelResidencial.setText(pessoa.getTelefone().getTelResidencial());
			txtTelWhatsapp.setText(pessoa.getTelefone().getTelWhatsapp());
			txtRg.setText(pessoa.getRg());
			telefoneId = pessoa.getTelefone().getId();
			enderecoId = pessoa.getEndereco().getId();
			emailId = pessoa.getEmail().getId();
		}
		conexao.fecharConexao();

	}

	private void acaoSalvar() throws SQLException {

		String nome = null, rg = null, sexo = null, rua = null, bairro = null, numero = null, cidade = null, cep = null,
				uf = null, telCelular = null, telComercial = null, telResidencial = null, telWhatsapp = null,
				emai = null;

		String cpf = null;
		Date dataNascimento = null;

		if (txtNome.getText().isEmpty()) {
			ValidationFields.checkEmptyFields(txtNome);
			Alert dlg = new Alert(AlertType.ERROR);
			dlg.setContentText("Preencha o campo Nome!!!");
			dlg.showAndWait();
			txtNome.requestFocus();
			return;

		} else {

			nome = txtNome.getText();

		}
		if (txtCpf.getText().isEmpty()) {
			cpf = new String("");
		} else {
			cpf = txtCpf.getText();
		}

		if (txtRg.getText().isEmpty()) {

			rg = txtRg.getText();

		} else {
			rg = txtRg.getText();

		}
		if (txtDataNascimento.getText().isEmpty()) {

		} else {
			Date dataAtual = new Date();
			String data = txtDataNascimento.getText();

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String dataString = format.format(dataAtual);

			try {
				Date datas = format.parse(data);
				Date dataF = format.parse(dataString);
				String dataMinima = "01/01/1910";
				Date dataM = format.parse(dataMinima);

				dataMin = new java.sql.Date(dataM.getTime());
				dataSql = new java.sql.Date(datas.getTime());
				dataHoje = new java.sql.Date(dataF.getTime());
				System.out.println("data hoje : " + dataHoje);
				System.out.println("data sql :" + dataSql);
				System.out.println("data Minima :" + dataMin);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if (dataSql.getTime() >= dataHoje.getTime() || dataSql.getTime() <= dataMin.getTime()) {
				ValidationFields.checkEmptyFields(txtDataNascimento);
				Alert dlg = new Alert(AlertType.ERROR);
				dlg.setContentText("Data Nascimento inválida!!!");
				dlg.showAndWait();
				txtDataNascimento.requestFocus();
				return;
			} else {
				dataNascimento = dataSql;
				System.out.println("Data Nascimento:  " + dataNascimento);
			}
		}
		if (cmbSexo.getValue() == null) {
			sexo = new String("");
		} else {

			sexo = cmbSexo.getValue().toString();
		}

		if (txtRua.getText().isEmpty()) {
			rua = txtRua.getText();

		} else {

			rua = txtRua.getText();
		}
		if (txtBairro.getText().isEmpty()) {

			bairro = txtBairro.getText();
		} else {

			bairro = txtBairro.getText();
		}

		if (txtNumero.getText().isEmpty()) {
			numero = txtNumero.getText();

		} else {

			numero = txtNumero.getText();
		}

		if (cmbUf.getValue() == null) {

			uf = new String("");
		} else {

			uf = cmbUf.getValue().toString();

		}
		if (cmbCidade.getValue() == null) {
			cidade = new String("");
		} else {

			cidade = cmbCidade.getValue().toString();

		}

		if (txtCep.getText().isEmpty()) {
			cep = txtCep.getText();

		} else {

			cep = txtCep.getText();
		}

		if (txtTelCelular.getText() == null || txtTelComercial.getText() == null || txtTelResidencial.getText() == null
				|| txtTelWhatsapp.getText() == null) {
			Alert dlg = new Alert(AlertType.ERROR);
			dlg.setContentText("Preencha um dos campos Telefones!!!");
			dlg.showAndWait();
		} else {
			telCelular = txtTelCelular.getText();
			telComercial = txtTelComercial.getText();
			telResidencial = txtTelResidencial.getText();
			telWhatsapp = txtTelWhatsapp.getText();
		}

		if (txtEmail.getText().isEmpty()) {
			emai = txtEmail.getText();

		} else {

			emai = txtEmail.getText();

		}

		String ativo = new String("SIM");
		String tipo = new String("PF");

		ButtonType sim = new ButtonType("SIM", ButtonBar.ButtonData.YES);
		ButtonType nao = new ButtonType("NÃO", ButtonBar.ButtonData.NO);
		Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente atualizar os dados do CLIENTE PF ?", sim,
				nao);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get().equals(sim)) {
			Endereco enderecos = new Endereco(enderecoId, rua, bairro, numero, cidade, uf, cep);
			this.enderecoDAO = Principal.getEnderecoDAO();
			boolean sucess = enderecoDAO.atualizar(enderecos);
			System.out.println("atualizou endereco : " + sucess);
			conexao.fecharConexao();

			Telefone telefones = new Telefone(telefoneId, telComercial, telCelular, telResidencial, telWhatsapp);
			this.telefoneDAO = Principal.getTelefoneDAO();
			boolean suce = telefoneDAO.atualizar(telefones);
			System.out.println("atualizou telefone : " + suce);
			conexao.fecharConexao();

			Email emails = new Email(emailId, emai);
			this.emailDAO = Principal.getEmailDAO();
			boolean su = emailDAO.atualizar(emails);
			System.out.println("atualizou email : " + su);
			conexao.fecharConexao();

			Pessoa pessoa = new Pessoa(telaPrincipalClientes.getIdPessoa(), nome, cpf, rg, sexo, dataNascimento,
					enderecos, telefones, emails, tipo, ativo, null);

			this.pessoaDAO = Principal.getPessoaDAO();
			boolean sucesso = pessoaDAO.atualizar(pessoa);
			if (sucesso) {
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setHeaderText("Dados atualizados com sucesso!");
				alerta.showAndWait();
				voltarTela();
			}
			conexao.fecharConexao();

		}
	}

	private void acaoSairTela() {
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("Confirmação de Cancelamento");
		alerta.setHeaderText("Você quer mesmo cancelar ? ");
		alerta.setContentText("Todos os dados preenchidos serão perdidos \nVocê tem certeza?");
		Optional<ButtonType> escolha = alerta.showAndWait();

		if (escolha.get() == ButtonType.OK) {
			Stage stage = (Stage) btnSair.getScene().getWindow();
			stage.close();
		} else {

		}
	}

	private void carregaComboBoxSexo() {
		ArrayList<String> listaSexo = new ArrayList<String>();
		String feminino = new String("Feminino");
		String masculino = new String("Masculino");

		listaSexo.add(feminino);
		listaSexo.add(masculino);

		cmbSexo.getItems().addAll(listaSexo);

	}

	private void carregaComboBoxEstados() {
		this.estadoDAO = Principal.getEstadoDAO();
		cmbUf.getItems().addAll(estadoDAO.listar());
		cmbUf.toString();
		conexao.fecharConexao();

	}

	private void comboBoxCidadePorEstado() {
		if (cmbUf.getValue().toString().isEmpty()) {

		} else {
			cmbCidade.getItems().clear();
			Estado nome = cmbUf.getSelectionModel().getSelectedItem();
			Estado id = estadoDAO.buscar(nome.getNome());

			this.cidadeDAO = Principal.getCidadeDAO();
			cmbCidade.getItems().addAll(cidadeDAO.buscarCidade(id.getId()));
			cmbCidade.toString();
			conexao.fecharConexao();
		}

	}

	private void voltarTela() {
		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}

	@FXML
	void acaoData() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("##/##/####");
		tff.setCaracteresValidos("0987654321");
		tff.setTf(txtDataNascimento);
		tff.formatter();

	}
}