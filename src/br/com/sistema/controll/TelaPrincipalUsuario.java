package br.com.sistema.controll;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

import br.com.sistema.conexao.Conexao;
import br.com.sistema.listeners.ListenerParaMaiusculas;
import br.com.sistema.model.Usuario;
import br.com.sistema.model.dao.UsuarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaPrincipalUsuario {

	@FXML
	private TableView<Usuario> tbUsuarios;

	@FXML
	private TableColumn<Usuario, String> clnNome;

	@FXML
	private TableColumn<Usuario, String> clnPermissao;

	@FXML
	private Button btnPesquisar;

	@FXML
	private ComboBox<String> cmbBuscar;

	@FXML
	private TextField txtPesquisa;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	private UsuarioDAO usuarioDAO = null;

	private Conexao conexao = new Conexao(Principal.getNomeBanco(), Principal.getUsuarioBanco(),
			Principal.getSenhaBanco());

	private static Integer usuario_id = null;

	public void initialize() {

		TipoPesquisa();
		txtPesquisa.textProperty().addListener(new ListenerParaMaiusculas(txtPesquisa));

		btnPesquisar.setOnAction(e -> {
			listarTodos();
		});

		btnNovo.setOnAction(e -> {
			try {
				chamarTela("TelaNovoUsuario");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		btnEditar.setOnAction(e -> {
			editar();
		});

		btnExcluir.setOnAction(e -> {
			excluir();
		});

		tbUsuarios.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarUsuario(newValue));

		cmbBuscar.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					txtPesquisa.requestFocus();
				}

			}
		});

		txtPesquisa.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					listarTodos();
				}

			}
		});
	}

	public Integer idUsuario() {
		return usuario_id;
	}

	private void selecionarUsuario(Usuario usuario) {
		if (usuario != null) {
			usuario_id = usuario.getId();
		}
	}

	public void listarTodos() {
		if (cmbBuscar.getValue() == null) {
			Alert dlg = new Alert(AlertType.INFORMATION);
			ValidationFields.checkEmptyFields(cmbBuscar);
			dlg.setContentText("selecione o campo BUSCAR!!!");
			dlg.showAndWait();
			cmbBuscar.requestFocus();
			return;

		} else {

			clnNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
			clnPermissao.setCellValueFactory(new PropertyValueFactory<Usuario, String>("permissao"));

			switch (cmbBuscar.getValue()) {
			case "Nome":
				if (txtPesquisa.getText().isEmpty()) {
					Alert dlg = new Alert(AlertType.INFORMATION);
					dlg.setContentText("Preencha o campo pesquisa com o nome do usuario!");
					dlg.showAndWait();
					ValidationFields.checkEmptyFields(txtPesquisa);
					txtPesquisa.requestFocus();
					return;

				} else {
					this.usuarioDAO = Principal.getUsuarioDAO();
					ObservableList<Usuario> lista = FXCollections
							.observableArrayList(usuarioDAO.buscarNome(txtPesquisa.getText()));
					tbUsuarios.setItems(lista);
					conexao.fecharConexao();
				}
				break;

			case "Todos":
				txtPesquisa.clear();
				this.usuarioDAO = Principal.getUsuarioDAO();
				ObservableList<Usuario> lista = FXCollections.observableArrayList(usuarioDAO.listarTodos());
				tbUsuarios.setItems(lista);
				conexao.fecharConexao();
				break;

			default:
				break;
			}

		}

	}

	private void excluir() {
		if (tbUsuarios.getSelectionModel().getSelectedItem() != null) {
			Usuario us = new Usuario(usuario_id, null, null, null);

			ButtonType sim = new ButtonType("SIM", ButtonBar.ButtonData.YES);
			ButtonType nao = new ButtonType("NÃO", ButtonBar.ButtonData.NO);
			Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente excluir os dados do USUARIO ?", sim, nao);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get().equals(sim)) {
				this.usuarioDAO = Principal.getUsuarioDAO();
				boolean sucesso = usuarioDAO.apagar(us);
				if (sucesso) {
					Alert dlg = new Alert(AlertType.CONFIRMATION);
					dlg.setContentText("Dados do USUARIO excluido com sucesso!");
					dlg.showAndWait();
					listarTodos();
				}
				conexao.fecharConexao();

			}

		} else {
			Alert dlg = new Alert(AlertType.WARNING);
			dlg.setContentText("Selecione o USUARIO que deseja Excluir!");
			dlg.showAndWait();
			ValidationFields.checkEmptyFields(btnExcluir);
			return;
		}

	}

	private void editar() {
		if (tbUsuarios.getSelectionModel().getSelectedItem() != null) {
			ButtonType sim = new ButtonType("SIM", ButtonBar.ButtonData.YES);
			ButtonType nao = new ButtonType("NÃO", ButtonBar.ButtonData.NO);
			Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente editar os dados do usuario ?", sim, nao);

			Optional<ButtonType> result = alert.showAndWait();

			if (result.get().equals(sim)) {
				try {
					chamarTela("TelaEditarUsuario");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else {
			Alert dlg = new Alert(AlertType.WARNING);
			dlg.setContentText("Selecione o USUARIO que deseja Editar!");
			dlg.showAndWait();
			btnEditar.requestFocus();
		}

	}

	private void TipoPesquisa() {

		ArrayList<String> listaBusca = new ArrayList<>();
		String Nome = new String("Nome");
		String Todos = new String("Todos");
		listaBusca.add(Nome);
		listaBusca.add(Todos);

		cmbBuscar.getItems().addAll(listaBusca);
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

}
