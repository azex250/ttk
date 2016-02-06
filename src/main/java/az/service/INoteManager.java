package az.service;

import az.model.Note;
import java.util.List;

public interface INoteManager {
	public void createNote(Note note);
	public List<Note> getNotes();	
	public List<Note> getNotesByTag(String tag);
	public List<Note> getNotesByTitle(String title);
	public List<String> getTags();
}
