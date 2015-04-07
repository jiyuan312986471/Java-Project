package model;

import java.io.Serializable;

public class History implements Serializable {

	private static final long serialVersionUID = 7857381850503305807L;
	
	private String userEmail;
	private String exoTitle;
	private String infoError;
	private String infoTest;
	
	public History() {
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getExoTitle() {
		return exoTitle;
	}
	
	public void setExoTitle(String exoTitle) {
		this.exoTitle = exoTitle;
	}
	
	public String getInfoError() {
		return infoError;
	}
	
	public void setInfoError(String infoError) {
		this.infoError = infoError;
	}

	public String getInfoTest() {
		return infoTest;
	}
	
	public void setInfoTest(String infoTest) {
		this.infoTest = infoTest;
	}
}
