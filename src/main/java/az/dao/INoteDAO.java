package az.dao;

import az.model.Note;
import java.util.List;

public interface INoteDAO {
	public int create(Note note);
	public void addTag(int note_id, String tag_name);
	public List<Note> list();
	public List<Note> listByTag(String tag_name);
	public List<Note> listByTitle(String title);
}