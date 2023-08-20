package br.com.sistema.controll;

import br.com.sistema.model.ContasPagar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TelaListaContasPagar {

	@FXML
	private Button btnPagarDespesa;

	@FXML
	private ComboBox<String> cmbBuscarDespesa;

	@FXML
	private TextField txtPesquisaDespesa;

	@FXML
	private Button btnAtualizarDespesa;

	@FXML
	private DatePicker txtDataInicialDespesa;

	@FXML
	private DatePicker txtDataFinalDespesa;

	@FXML
	private Button btnImprimirDespesa;

	@FXML
	private TableView<ContasPagar> tbPagamentoDespesa;

	@FXML
	private TableColumn<ContasPagar, String> clnFornecedorDespesa;

	@FXML
	private TableColumn<ContasPagar, String> clnPagamentoValorDespesa;

	@FXML
	private TableColumn<ContasPagar, String> clnPagamentoVencimentoDespesa;

	@FXML
	private TableColumn<ContasPagar, String> clnPagamentoSituacaoDespesa;

	@FXML
	private Label lblValorPagarDespesa;

	@FXML
	private Label lblValorPagoDespesa;

	@FXML
	private Label lblValorTotalDespesa;

	@FXML
	private Button btnNovaDespesa;

	@FXML
	private Button btnExcluir;

	public void initialize() {

	}

}
