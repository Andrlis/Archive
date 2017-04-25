package data_classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {

	public String requestType;
	public String requestMessage;
	public ArrayList<UserCard> cardList;

	public Request() {
		requestType = null;
		requestMessage = null;
		cardList = null;
	}
}
