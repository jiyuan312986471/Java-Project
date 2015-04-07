package model;

import java.io.Serializable;

public class Exercise implements Serializable {

	private static final long serialVersionUID = 3725102659456207234L;
	
	private String title;
	private String description;
	
	public Exercise() {
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
