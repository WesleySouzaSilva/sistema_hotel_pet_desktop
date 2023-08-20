package br.com.sistema.controll;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import br.com.sistema.conexao.Conexao;
import br.com.sistema.model.dao.UsuarioDAO;
import br.com.sistema.validadores.LeitorXml;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Principal extends Application {

	private static LeitorXml leitorXml = null;
	private static String nomeBanco = null;
	private static String usuarioBanco = null;
	private static String senhaBanco = null;
	public static Conexao conexao = null;
	public static UsuarioDAO usuarioDAO = null;

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		carregaDados("/br/com/sistema/validadores/acesso_sistema.xml", true);
		conexao = new Conexao(nomeBanco, usuarioBanco, senhaBanco);
		usuarioDAO = new UsuarioDAO(conexao);
		launch(args);
		conexao.fecharConexao();
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

	private static void carregaDados(String caminho, Boolean carrega)
			throws ParserConfigurationException, SAXException, IOException {
		if (carrega) {
			InputStream arquivo = Principal.class.getResourceAsStream(caminho);
			if (arquivo != null) {
				List<String> listaTags = Arrays.asList("nomeBanco", "usuarioBanco", "senhaBanco");

				leitorXml = new LeitorXml();
				List<List<String>> lista = leitorXml.processar(arquivo, listaTags);

				for (List<String> cliente : lista) {

					for (@SuppressWarnings("unused") String valor : cliente) {
						nomeBanco = cliente.get(0);
						usuarioBanco = cliente.get(1);
						senhaBanco = cliente.get(2);
					}
				}

			}

		}
	}

	public static UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public static String getNomeBanco() {
		return nomeBanco;
	}

	public static String getSenhaBanco() {
		return senhaBanco;
	}

	public static String getUsuarioBanco() {
		return usuarioBanco;
	}
}
