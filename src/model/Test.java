package model;

import java.io.Serializable;

public class Test implements Serializable {

	private static final long serialVersionUID = -9149213580504482202L;
	
	private String exoTitle;
	private String input;
	
	public Test(String exoTitle, String input) {
		setExoTitle(exoTitle);
		setInput(input);
	}
	
	public String getExoTitle() {
		return exoTitle;
	}
	
	public String getInput() {
		return input;
	}
	
	public void setExoTitle(String exoTitle) {
		this.exoTitle = exoTitle;
	}
	
	public void setInput(String input) {
		this.input = input;
	}

}
