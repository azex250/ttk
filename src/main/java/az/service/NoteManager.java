package az.service;

import az.model.Note;
import az.dao.INoteDAO;
import az.dao.ITagDAO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteManager implements INoteManager {
	private INoteDAO noteDAO;
	private ITagDAO tagDAO;
	
	@Autowired 
	public void setNoteDAO(INoteDAO noteDAO) {
		this.noteDAO = noteDAO;
	}

	@Autowired 
	public void setTagDAO(ITagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}

	@Override
	@Transactional
	public void createNote(Note note) {
		note.setId(noteDAO.create(note));
		List<String> tags = note.getTags();
		if (tags!=null) {
			for (String tag : tags) {
				noteDAO.addTag(note.getId(), tag);
			}
		}
	}

	@Override
	public List<Note> getNotes() {
		return noteDAO.list();	
	}

	@Override
	public List<Note> getNotesByTag(String tag) {
		return noteDAO.listByTag(tag);	
	}

	@Override
	public List<Note> getNotesByTitle(String title) {
		return noteDAO.listByTitle(title);	
	}

	@Override
	public List<String> getTags() {
		return tagDAO.list(); 
	}
}