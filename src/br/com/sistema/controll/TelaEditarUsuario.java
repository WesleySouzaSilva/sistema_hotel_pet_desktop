package br.com.sistema.controll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import br.com.sistema.conexao.Conexao;
import br.com.sistema.listeners.ListenerParaMaiusculas;
import br.com.sistema.model.Usuario;
import br.com.sistema.model.dao.UsuarioDAO;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class TelaEditarUsuario {

	@FXML
	private TextField txtNome;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private ComboBox<String> cmbPermissao;

	@FXML
	private Button btnConfirmar;

	@FXML
	private Button btnCancelar;

	private Conexao conexao = new Conexao(Principal.getNomeBanco(), Principal.getUsuarioBanco(),
			Principal.getSenhaBanco());
	private UsuarioDAO usuarioDAO = null;
	TelaPrincipalUsuario telaPrincipalUsuario = new TelaPrincipalUsuario();

	public void initialize() {
		txtNome.textProperty().addListener(new ListenerParaMaiusculas(txtNome));
		listaDados();
		tipoUsuario();

		btnConfirmar.setOnAction(e -> {
			try {
				salvar();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		btnCancelar.setOnAction(e -> {
			cancelar();
		});

		txtNome.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					txtSenha.requestFocus();
					return;
				}

			}
		});

		txtSenha.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					cmbPermissao.requestFocus();
					return;
				}

			}
		});

		cmbPermissao.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					try {
						salvar();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}

			}
		});
	}

	private void listaDados() {
		this.usuarioDAO = Principal.getUsuarioDAO();
		for (Usuario u : usuarioDAO.buscarId(telaPrincipalUsuario.idUsuario())) {
			txtNome.setText(u.getNome());
			txtSenha.setText(u.getSenha());
			cmbPermissao.setValue(u.getPermissao());
		}
		conexao.fecharConexao();
	}

	private void salvar() throws SQLException {
		String nome = null, senha = null, permissao = null;
		if (txtNome.getText().isEmpty() || txtNome.getText() == null) {
			Alert dlg = new Alert(AlertType.ERROR);
			dlg.setContentText("Preencha o campo NOME!!!");
			dlg.showAndWait();
			txtNome.requestFocus();
			return;
		} else {
			nome = txtNome.getText();

		}

		if (txtSenha.getText().isEmpty() || txtSenha.getText() == null) {
			Alert dlg = new Alert(AlertType.ERROR);
			dlg.setContentText("Preencha o campo SENHA !!");
			dlg.showAndWait();
			txtSenha.requestFocus();
			return;
		} else {
			senha = txtSenha.getText();
		}

		if (cmbPermissao.getValue() == null) {
			ValidationFields.checkEmptyFields(cmbPermissao);
			Alert dlg = new Alert(AlertType.ERROR);
			dlg.setContentText("Selecine o campo PERMISSÃO");
			dlg.showAndWait();
			cmbPermissao.requestFocus();
			return;

		} else {

			permissao = cmbPermissao.getValue().toString();
		}

		Usuario usuario = new Usuario(telaPrincipalUsuario.idUsuario(), nome, senha, permissao);

		ButtonType sim = new ButtonType("SIM", ButtonBar.ButtonData.YES);
		ButtonType nao = new ButtonType("NÃO", ButtonBar.ButtonData.NO);
		Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente atualizar os dados do USUARIO ?", sim, nao);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get().equals(sim)) {
			this.usuarioDAO = Principal.getUsuarioDAO();
			boolean sucesso = usuarioDAO.atualizar(usuario);
			conexao.fecharConexao();
			if (sucesso) {
				Alert dlg = new Alert(AlertType.CONFIRMATION);
				dlg.setContentText("Dados do USUARIO atualizado com sucesso!");
				dlg.showAndWait();
				voltarTela();
			}
		}

	}

	private void cancelar() {
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("Confirmação de Cancelamento");
		alerta.setHeaderText("Você quer mesmo cancelar ? ");
		alerta.setContentText("Todos os dados preenchidos serão perdidos \nVocê tem certeza?");
		Optional<ButtonType> escolha = alerta.showAndWait();

		if (escolha.get() == ButtonType.OK) {
			Stage stage = (Stage) btnCancelar.getScene().getWindow();
			stage.close();
		}
	}

	private void tipoUsuario() {
		ArrayList<String> lista = new ArrayList<>();
		String administrador = new String("ADMINISTRADOR");
		String usuario = new String("USUARIO");

		lista.add(administrador);
		lista.add(usuario);

		cmbPermissao.getItems().addAll(lista);

	}

	public void voltarTela() {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

}
