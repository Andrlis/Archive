package data_classes;

import java.io.Serializable;

public class UserCard implements Serializable {

	private int cardID;
	private String userName;
	private String userMiddleName;
	private String userLastName;
	private String userEmail;
	private String userPhone;
	private String userJob;
	private String userDescription;

	public UserCard(int id, String name, String mName, String lName, String mail, String phone, String job,
			String descrip) {
		this.cardID = id;
		this.userName = name;
		this.userMiddleName = mName;
		this.userLastName = lName;
		this.userEmail = mail;
		this.userPhone = phone;
		this.userJob = job;
		this.userDescription = descrip;
	}

	public UserCard() {
		this.cardID = 0;
		this.userName = "No information";
		this.userMiddleName = "No information";
		this.userLastName = "No information";
		this.userEmail = "No information";
		this.userPhone = "No information";
		this.userJob = "No information";
		this.userDescription = "No information";
	}

	/**
	 * @return the cardID
	 */
	public int getCardID() {
		return cardID;
	}

	/**
	 * @param cardID the cardID to set
	 */
	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userMiddleName
	 */
	public String getUserMiddleName() {
		return userMiddleName;
	}

	/**
	 * @param userMiddleName
	 *            the userMiddleName to set
	 */
	public void setUserMiddleName(String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}

	/**
	 * @return the userLastName
	 */
	public String getUserLastName() {
		return userLastName;
	}

	/**
	 * @param userLastName
	 *            the userLastName to set
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail
	 *            the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the userPhone
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * @param userPhone
	 *            the userPhone to set
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	/**
	 * @return the userJob
	 */
	public String getUserJob() {
		return userJob;
	}

	/**
	 * @param userJob
	 *            the userJob to set
	 */
	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}

	/**
	 * @return the userDescription
	 */
	public String getUserDescription() {
		return userDescription;
	}

	/**
	 * @param userDescription
	 *            the userDescription to set
	 */
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
}
