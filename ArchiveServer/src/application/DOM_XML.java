package application;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
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
}
