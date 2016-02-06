package az.model;

public class AjaxError {
	private String message;
	private String title;
	public AjaxError(String message) {
		this.message = message;
		this.title = "error";
	}
	public String getMesage() {
		return message;
	}

	public String getTitle() {
		return title;
	}
}