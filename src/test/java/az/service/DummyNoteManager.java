package az.service;

import az.model.Note;
import java.util.List;

public class DummyNoteManager implements INoteManager {
	
	@Override
	public void createNote(Note note) {}

	@Override
	public List<Note> getNotes() {
		return null;	
	}

	@Override
	public List<Note> getNotesByTag(String tag) {
		return null;
	}

	@Override
	public List<Note> getNotesByTitle(String title) {
		return null;
	}

	@Override
	public List<String> getTags() {
		return null; 
	}
}