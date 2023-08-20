package br.com.sistema.controll;

import java.math.BigDecimal;

import br.com.sistema.model.ContasReceber;
import br.com.sistema.model.DetalhesPagamento;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TelaListaContasReceber {

	@FXML
	private TableView<DetalhesPagamento> tbDetalhesPagamento;

	@FXML
	private TableColumn<DetalhesPagamento, String> clnDetalhesTipo;

	@FXML
	private TableColumn<DetalhesPagamento, BigDecimal> clnDetalhesValor;

	@FXML
	private TableColumn<DetalhesPagamento, String> clnDataPagamento;

	@FXML
	private Label lblTotalRecebido;

	@FXML
	private Button btnAtualizar;

	@FXML
	private Button btnImprimir;

	@FXML
	private Button btnReceberParcelado;

	@FXML
	private Button btnReceberTotal;

	@FXML
	private TableView<ContasReceber> tbPagamento;

	@FXML
	private TableColumn<ContasReceber, String> clnCliente;

	@FXML
	private TableColumn<ContasReceber, String> clnData;

	@FXML
	private TableColumn<ContasReceber, String> clnPet;

	@FXML
	private TableColumn<ContasReceber, String> clnSituacao;

	@FXML
	private TableColumn<ContasReceber, String> clnTipo;

	@FXML
	private TableColumn<ContasReceber, String> clnValor;

	@FXML
	private ComboBox<String> cmbBuscar;

	@FXML
	private Label lblSaldo;

	@FXML
	private Label lblValorPago;

	@FXML
	private Label lblValorReceber;

	@FXML
	private Label lblValorTotal;

	@FXML
	private DatePicker txtDataFinal;

	@FXML
	private DatePicker txtDataInicial;

	@FXML
	private TextField txtPesquisa;

	@FXML
	private TextField txtSaldoDevedor;

	@FXML
	private TextField txtTotalReceber;

	@FXML
	private TextField txtTotalRecebido;


	public void initialize() {
		
	}
}
