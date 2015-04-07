package model;

import java.io.Serializable;

public class Test implements Serializable {

	private static final long serialVersionUID = -9149213580504482202L;
	
	private String exoTitle;
	private String input;
	private String output;
	
	public Test() {
	}
	
	public String getExoTitle() {
		return exoTitle;
	}
	
	public String getInput() {
		return input;
	}
	
	public String getOutput() {
		return output;
	}
	
	public void setExoTitle(String exoTitle) {
		this.exoTitle = exoTitle;
	}
	
	public void setInput(String input) {
		this.input = input;
	}
	
	public void setOutput(String output) {
		this.output = output;
	}

}
