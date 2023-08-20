package br.com.sistema.controll;

import java.io.IOException;
import java.net.URL;

import br.com.sistema.conexao.Conexao;
import br.com.sistema.listeners.ListenerParaMaiusculas;
import br.com.sistema.model.dao.UsuarioDAO;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class TelaLogin {

	@FXML
	private Button btnConfirmar;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private TextField txtUsuario;

	@FXML
	private Button btnSair;

	@FXML
	private Button btnCancelar;

	private Conexao conexao = new Conexao(Principal.getNomeBanco(), Principal.getUsuarioBanco(),
			Principal.getSenhaBanco());
	private String permissao = new String();
	private static String permissaoStatic = null;
	private UsuarioDAO usuarioDAO = null;

	public void initialize() {
		txtUsuario.textProperty().addListener(new ListenerParaMaiusculas(txtUsuario));
		txtSenha.textProperty().addListener(new ListenerParaMaiusculas(txtSenha));

		textFieldInicial(txtUsuario);

		btnConfirmar.setOnAction(e -> {
			try {
				acaoOK();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnCancelar.setOnAction(e -> {
			acaoSair();
		});

		btnSair.setOnAction(e -> {
			acaoSair();
		});

		txtUsuario.setOnKeyPressed(new EventHandler<KeyEvent>() {

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
				if (event.getCode() == KeyCode.ENTER) {
					try {
						acaoOK();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

		conexao.fecharConexao();
	}

	private void acaoOK() throws IOException {
		String nome = txtUsuario.getText();
		String senha = txtSenha.getText();
		this.usuarioDAO = Principal.getUsuarioDAO();
		if (usuarioDAO.logar(nome, senha)) {

			permissaoStatic = txtUsuario.getText();
			((Stage) txtUsuario.getScene().getWindow()).close();
			Stage stage1 = new Stage();

			Image image = new Image("/br/com/sistema/icones/W3.png");

			stage1.setTitle("Sistema Gestão Hotel Pet");
			stage1.getIcons().add(image);

			URL telaHomeFXML =

					this.getClass().getResource("/br/com/sistema/view/TelaHome.fxml");

			Parent painel = (Parent) FXMLLoader.load(telaHomeFXML);

			stage1.setScene(new Scene(painel));

			stage1.show();
			stage1.setResizable(false);

		} else {

			Alert dlg = new Alert(AlertType.WARNING);
			dlg.setContentText("Usuario ou Senha incorretos!");
			dlg.showAndWait();
		}
		conexao.fecharConexao();
	}

	private void acaoSair() {

		Stage stage = (Stage) btnSair.getScene().getWindow();
		stage.close();
	}

	public String permissaoUsuario() {
		return permissao = permissaoStatic;
	}

	public String usuarioNome() {
		return permissao;
	}

	private void textFieldInicial(TextField tf) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				tf.requestFocus();
			}
		});

	}
}
