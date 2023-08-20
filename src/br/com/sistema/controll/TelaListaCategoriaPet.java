package br.com.sistema.controll;

import java.math.BigDecimal;

import br.com.sistema.model.CategoriaPet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TelaListaCategoriaPet {

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnPesquisar;

	@FXML
	private TableView<CategoriaPet> tbCategoria;

	@FXML
	private TableColumn<CategoriaPet, Integer> clnLimite;

	@FXML
	private TableColumn<CategoriaPet, String> clnNome;

	@FXML
	private TableColumn<CategoriaPet, String> clnTipo;

	@FXML
	private TableColumn<CategoriaPet, BigDecimal> clnValor;

	@FXML
	private ComboBox<String> cmbBuscar;

	@FXML
	private TextField txtPesquisa;

	public void initialize() {

	}
}
