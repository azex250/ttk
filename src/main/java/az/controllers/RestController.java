package az.controllers;

import az.model.Note;
import az.model.AjaxError;
import java.lang.Exception;
import az.service.INoteManager;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired; 

@Controller
@RequestMapping("/ajax")
public class RestController { 
    private INoteManager noteManager;

    @Autowired
    public void setNoteManager(INoteManager noteManager) {
        this.noteManager = noteManager;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void getCreateAjax(Note note) throws Exception {
        if (note.getTitle().equals("error")) {
            throw new Exception("Error on ajax");
        }
        noteManager.createNote(note);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxError handleException(Exception ex, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new AjaxError(ex.getMessage());
    }
}