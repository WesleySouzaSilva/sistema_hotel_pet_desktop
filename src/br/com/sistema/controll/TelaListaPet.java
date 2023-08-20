package br.com.sistema.controll;

import br.com.sistema.model.Pet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaListaPet {

	@FXML
	private Button btnEditarPet;

	@FXML
	private Button btnExcluirPet;

	@FXML
	private Button btnGerarAgendamentoPet;

	@FXML
	private Button btnImprimirListaPet;

	@FXML
	private Button btnNovoPet;

	@FXML
	private Button btnPesquisarPet;

	@FXML
	private TableColumn<Pet, String> clnCategoria;

	@FXML
	private TableColumn<Pet, String> clnDataNascimento;

	@FXML
	private TableColumn<Pet, String> clnNomePet;

	@FXML
	private TableColumn<Pet, String> clnNomeResponsavel;

	@FXML
	private TableColumn<Pet, String> clnSexo;

	@FXML
	private TableColumn<Pet, String> clnVeterinario;

	@FXML
	private ComboBox<String> cmbBuscarPet;

	@FXML
	private TableView<Pet> tbPet;

	@FXML
	private TextArea txtObservacaoPet;

	@FXML
	private TextField txtPesquisaPet;

	@FXML
	private Label txtQtdePet;

	public void initialize() {

		
	}

}
