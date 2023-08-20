package br.com.sistema.controll;

import java.net.URL;

import br.com.sistema.conexao.Conexao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Principal extends Application {

	public static Conexao conexao = new Conexao("nome-do-banco", "usuario-banco", "senha-banco");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		Stage stage = new Stage();
		Image image = new Image("/br/com/sistema/icones/W3.png");

		stage.setTitle("Sistema Hotel Pet");
		stage.getIcons().add(image);
		URL FXML = this.getClass().getResource("/br/com/sistema/view/TelaLogin.fxml");

		Parent painel = (Parent) FXMLLoader.load(FXML);
		stage.setScene(new Scene(painel));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		stage.setResizable(false);
	}

	public static Conexao getConexao() {
		return conexao;
	}
	
	
}
