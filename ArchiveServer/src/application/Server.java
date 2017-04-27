package application;

import java.net.*;
import java.util.ArrayList;

import data_classes.Request;
import data_classes.UserCard;

import java.io.*;

public class Server {

	private DOM_XML dom;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private ObjectOutputStream objOutputStream;
	private ObjectInputStream objInputStream;

	public Server() {
		try {
			serverSocket = new ServerSocket(2015);
			System.out.println("ServerSocket constructor");
			dom = new DOM_XML();
		} catch (IOException exc) {
			exc.printStackTrace();
			System.exit(1);
		}
	}

	public boolean waitConnection() {
		System.out.println("wait conection");

		try {
			clientSocket = serverSocket.accept();
		} catch (IOException exc) {
			exc.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean creatIOThreads() {
		System.out.println("creatIOThreads");

		try {
			objOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
			objInputStream = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException exc) {
			exc.printStackTrace();
			return false;
		}
		return true;
	}

	public void startWork() {
		System.out.println("start work");

		try {
			while (true) {
				Request question = (Request) objInputStream.readObject();
				if (question.requestType == null)
					break;
				if (question.requestType.equals("exit"))
					break;
				switch (question.requestType) {
				case "getAll":
					Request answer = new Request("answer");
					ArrayList<UserCard> list = dom.getAllCards();
					answer.cardList = new ArrayList<UserCard>();
					answer.cardList.addAll(list);
					objOutputStream.writeObject(answer);
					break;
				}
				System.out.println(
						"Server: request type-" + question.requestType + "request message-" + question.requestMessage);
				// objOutputStream.writeObject(question);
			}
		} catch (IOException exc) {
			exc.printStackTrace();
		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		}
	}

	public void endWork() {
		System.out.println("end work");

		try {
			objInputStream.close();
			objOutputStream.close();
			serverSocket.close();
			clientSocket.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}
}
