package br.com.sistema.controll;

import br.com.sistema.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

	public void initialize() {

	}

}
