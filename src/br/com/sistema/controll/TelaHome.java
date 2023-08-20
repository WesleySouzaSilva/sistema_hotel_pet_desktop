package br.com.sistema.controll;

import java.io.IOException;
import java.net.URL;

import br.com.sistema.model.Agenda;
import br.com.sistema.model.Servico;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaHome {

	@FXML
	private Button btnConcluir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnEditarServico;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnExcluirServico;

	@FXML
	private Button btnImprimir;

	@FXML
	private Button btnImprimirLista;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnNovoServico;

	@FXML
	private Button btnPesquisar;

	@FXML
	private Button btnVisualizarAgenda;

	@FXML
	private TableView<Agenda> tbAgenda;

	@FXML
	private TableColumn<Agenda, String> clnDataEntrada;

	@FXML
	private TableColumn<Agenda, String> clnDataSaida;

	@FXML
	private TableColumn<Agenda, String> clnNomePet;

	@FXML
	private TableColumn<Agenda, String> clnNomeResponsavel;

	@FXML
	private TableColumn<Agenda, Integer> clnNumero;

	@FXML
	private TableColumn<Agenda, String> clnSituacao;

	@FXML
	private TableColumn<Agenda, String> clnTipo;

	@FXML
	private TableColumn<Agenda, String> clnValor;

	@FXML
	private ComboBox<String> cmbBuscar;

	@FXML
	private ComboBox<String> cmbTipo;

	@FXML
	private Label lblData;

	@FXML
	private Label lblHora;

	@FXML
	private Label lblQtdeHospedagem;

	@FXML
	private Label lblQtdeDisponivelHospedagem;

	@FXML
	private Label lblQtdeTotalHospedagem;

	@FXML
	private Label lblQtdeDiaria;

	@FXML
	private Label lblQtdeDisponivelDiaria;

	@FXML
	private Label lblQtdeTotalDiaria;

	@FXML
	private Label lblUsuario;

	@FXML
	private MenuItem menuClientesPF;

	@FXML
	private MenuItem menuClientesPJ;

	@FXML
	private MenuItem menuListaCategoria;

	@FXML
	private MenuItem menuListaClientes;

	@FXML
	private MenuItem menuListaContasPagar;

	@FXML
	private MenuItem menuListaContasReceber;

	@FXML
	private MenuItem menuListaPet;

	@FXML
	private MenuItem menuListaUsuario;

	@FXML
	private MenuItem menuNovoPet;

	@FXML
	private MenuItem menuNovoClientePet;

	@FXML
	private MenuItem menuNovoClientePet1;

	@FXML
	private MenuItem menuRelatorioDre;

	@FXML
	private TableView<Servico> tbServico;

	@FXML
	private TableColumn<Servico, String> clnDataServico;

	@FXML
	private TableColumn<Servico, String> clnDescricaoServico;

	@FXML
	private TableColumn<Servico, String> clnValorServico;

	@FXML
	private DatePicker txtDataFinal;

	@FXML
	private DatePicker txtDataInicial;

	@FXML
	private TextArea txtObservacao;

	@FXML
	private TextField txtPesquisa;

	public void initialize() {

		menuListaUsuario.setOnAction(e -> {
			try {
				chamarTela("TelaUsuarioPrincipal");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		menuListaClientes.setOnAction(e -> {
			try {
				chamarTela("TelaPrincipalClientes");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		menuClientesPF.setOnAction(e -> {
			try {
				chamarTela("TelaCadastroClientesPF");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		menuClientesPJ.setOnAction(e -> {
			try {
				chamarTela("TelaCadastroClientesPJ");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		menuNovoClientePet.setOnAction(e -> {
			try {
				chamarTela("TelaNovoClientePet");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		menuListaCategoria.setOnAction(e -> {
			try {
				chamarTela("TelaListaCategoriaPet");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		menuListaPet.setOnAction(e -> {
			try {
				chamarTela("TelaListaPet");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		menuNovoPet.setOnAction(e -> {
			try {
				chamarTela("TelaNovoPet");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		menuNovoClientePet1.setOnAction(e -> {
			try {
				chamarTela("TelaNovoClientePet");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		menuListaContasPagar.setOnAction(e -> {
			try {
				chamarTela("TelaListaContasPagar");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		menuListaContasReceber.setOnAction(e -> {
			try {
				chamarTela("TelaListaContasReceber");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		menuRelatorioDre.setOnAction(e -> {
			try {
				chamarTela("TelaRelatorioDre");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

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
