package model;

import java.io.Serializable;

public class Exercise implements Serializable {

	private static final long serialVersionUID = 3725102659456207234L;
	
	private String title;
	private String description;
	private String contentHead;
	private String contentFoot;
	
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
	
	public String getContentHead() {
		return contentHead;
	}
	
	public void setContentHead(String contentHead) {
		this.contentHead = contentHead;
	}
	
	public String getContentFoot() {
		return contentFoot;
	}
	
	public void setContentFoot(String contentFoot) {
		this.contentFoot = contentFoot;
	}

}
