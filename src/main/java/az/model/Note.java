package az.model;

import java.util.List;
import java.util.Arrays;

public class Note {

	private int id;
	private String title;
	private String body;
	private List<String> tags;

	public Note() {
		id = -1;
	}
	
	public Note(String title, String body) {
		this();
		this.title = title;
		this.body = body;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setTags(String tags) {
		if(tags != null && !tags.isEmpty()) { 
			this.tags = Arrays.asList(tags.split(", "));	
		}
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<String> getTags() {
		return this.tags;
	}	
}
