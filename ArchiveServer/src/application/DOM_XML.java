package application;

import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import data_classes.UserCard;

public class DOM_XML {

	private DocumentBuilder documentBuilder;
	private Document document;
	private Node root;

	public DOM_XML() {
		try {
			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			document = documentBuilder.parse("archive.xml");
			root = document.getDocumentElement();
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace(System.out);
		} catch (SAXException ex) {
			ex.printStackTrace(System.out);
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	}

	public ArrayList<UserCard> getAllCards() {
		ArrayList<UserCard> cardList = new ArrayList<UserCard>();
		UserCard userCard;

		NodeList cards = root.getChildNodes();
		for (int i = 0; i < cards.getLength(); i++) {
			String[] args = new String[8];
			int k = 0;
			Node card = cards.item(i);
			if (card.getNodeType() != Node.TEXT_NODE) {
				NodeList cardProps = card.getChildNodes();
				for (int j = 0; j < cardProps.getLength(); j++) {
					Node cardProp = cardProps.item(j);
					if (cardProp.getNodeType() != Node.TEXT_NODE) {
						args[k] = cardProp.getChildNodes().item(0).getTextContent();
						k++;
					}
				}
				userCard = new UserCard();
				userCard.setCardID(Integer.parseInt(args[0]));
				userCard.setUserName(args[1]);
				userCard.setUserMiddleName(args[2]);
				userCard.setUserLastName(args[3]);
				userCard.setUserEmail(args[4]);
				userCard.setUserPhone(args[5]);
				userCard.setUserJob(args[6]);
				userCard.setUserDescription(args[7]);
				cardList.add(userCard);
			}
		}
		return cardList;
	}

	public void addNewCard(UserCard userCard) {
		try {
			root = document.getDocumentElement();

			Element card = document.createElement("CARD");
			// <ID>
			Element id = document.createElement("ID");
			id.setTextContent(String.valueOf(userCard.getCardID()));
			// <NAME>
			Element name = document.createElement("NAME");
			name.setTextContent(userCard.getUserName());
			// <MIDDLENAME>
			Element mName = document.createElement("MIDDLENAME");
			mName.setTextContent(userCard.getUserMiddleName());
			// <LASTNAME >
			Element lName = document.createElement("LASTNAME");
			lName.setTextContent(userCard.getUserLastName());
			// <EMAIL>
			Element email = document.createElement("EMAIL");
			email.setTextContent(userCard.getUserEmail());
			// <PHONE>
			Element phone = document.createElement("PHONE");
			phone.setTextContent(userCard.getUserPhone());
			// <JOB>
			Element job = document.createElement("JOB");
			job.setTextContent(userCard.getUserJob());
			// <DESCRIPTION>
			Element description = document.createElement("DESCRIPTION");
			description.setTextContent(userCard.getUserDescription());

			// Add card`s elements into <Card>
			card.appendChild(id);
			card.appendChild(name);
			card.appendChild(mName);
			card.appendChild(lName);
			card.appendChild(email);
			card.appendChild(phone);
			card.appendChild(job);
			card.appendChild(description);
			// Add <CARD> to the root element
			root.appendChild(card);

			// Write XML into the file
			writeDocument(document);
		} catch (TransformerFactoryConfigurationError ex) {
			ex.printStackTrace(System.out);
		} catch (DOMException ex) {
			ex.printStackTrace(System.out);
		}
	}

	// Save DOM to file
	private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
		try {
			Transformer tr = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(document);
			FileOutputStream fos = new FileOutputStream("archive.xml");
			StreamResult result = new StreamResult(fos);
			tr.transform(source, result);
		} catch (TransformerException | IOException e) {
			e.printStackTrace(System.out);
		}
	}

	public void deletePerson(String cardId) {
		// <CARD>
		NodeList nodes = document.getElementsByTagName("CARD");

		for (int i = 0; i < nodes.getLength(); i++) {
			Element card = (Element) nodes.item(i);
			// <ID>
			Element id = (Element) card.getElementsByTagName("ID").item(0);
			String cID = id.getTextContent();
			if (cID.equals(cardId)) {
				card.getParentNode().removeChild(card);
			}
		}
		writeDocument(document);
	}
}
