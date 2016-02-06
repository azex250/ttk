package az.controllers;

import az.model.Note;
import java.util.List;
import java.lang.Exception;
import az.service.INoteManager;
import az.service.ITagManager;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@RequestMapping("/")
public class MainController { 
    private ITagManager tagManager;
    private INoteManager noteManager;

    @Autowired
    public void setNoteManager(INoteManager noteManager) {
        this.noteManager = noteManager;
    }

    @Autowired
    public void setTagManager(ITagManager tagManager) {
        this.tagManager = tagManager;
    }

    @RequestMapping(value= "/dummyList", method = RequestMethod.GET)
    public String getNotes(
        @RequestParam(name="tag", required=false) String tag,
        @RequestParam(name="title", required=false) String title, 
        Model model
    ) { 
        List<Note> notes;
        if (tag != null) {
            notes = noteManager.getNotesByTag(tag);
        } else if (title != null) {
            notes = noteManager.getNotesByTitle(title);            
        } else {
            notes = noteManager.getNotes();            
        }
        model.addAttribute("notes", notes);
        return "list";
    }

    @RequestMapping(value= "/", method = RequestMethod.GET)
    public String getPage(Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("tag_items", tagManager.getTags());
        return "dummy";
    }

    @RequestMapping(value= "/createNote", method = RequestMethod.POST)
    public String getForm(Note note) throws Exception {
        if (note.getTitle().equals("error")) {
            throw new Exception("Error on regular Post");
        }

        noteManager.createNote(note);
        return "redirect:/";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }
}