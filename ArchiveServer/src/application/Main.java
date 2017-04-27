package application;

import data_classes.UserCard;

public class Main  {

	public static void main(String[] args) {
		Server server = new Server();
		server.waitConnection();
		server.creatIOThreads();
		server.startWork();
		server.endWork();
//		DOM_XML dom = new DOM_XML();
//		dom.getAllCards();
//		UserCard card = new UserCard(3, "Петров", "Петр", "Петрович", "petrov@gmail.com","+375335987632", "Инженер", "ITechArt");
//		dom.addNewCard(card);
//		//dom.deletePerson("4");
	}
}
