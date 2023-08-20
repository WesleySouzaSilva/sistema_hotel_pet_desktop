package br.com.sistema.validadores;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LeitorXml {

	private DocumentBuilderFactory fabrica;
	private DocumentBuilder docBuilder;

	public LeitorXml() throws ParserConfigurationException {
		fabrica = DocumentBuilderFactory.newInstance();
		docBuilder = fabrica.newDocumentBuilder();
	}

	public List<List<String>> processar(InputStream arquivoXml, List<String> listaTags)
			throws SAXException, IOException {

		List<List<String>> lista = new ArrayList<>();
		List<String> sublista = new ArrayList<>();

		Document doc = docBuilder.parse(arquivoXml);

		NodeList nodes = doc.getElementsByTagName("*");

		int quantTags = 0;

		for (int i = 0; i < nodes.getLength(); i++) {

			Element elemento = (Element) nodes.item(i);

			String tagNome = elemento.getNodeName();

			if (listaTags.contains(tagNome)) {

				String valor = elemento.getFirstChild().getNodeValue();
				sublista.add(valor);

				quantTags++;

				if (quantTags % listaTags.size() == 0) {
					lista.add(sublista);
					sublista = new ArrayList<>();
				}
			}
		}

		return lista;
	}

}
