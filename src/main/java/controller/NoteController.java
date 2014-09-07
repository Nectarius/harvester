package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.EventService;
import service.NoteService;
import view.PageEventView;
import view.PageNoteView;
import view.PlainEventView;
import view.PlainNoteView;

import javax.validation.Valid;

/**
 * Created by nectarius on 11/16/13.
 */
@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    private final static Logger LOGGER = LoggerFactory.getLogger(NoteController.class.getName());

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public String getNotePage() {

        return "notes";
    }

    /**
     * @param id identity
     * @return data view
     */
    @RequestMapping(value = {"/note/{id}.data"}, method = RequestMethod.GET)
    @ResponseBody
    public PlainNoteView findEvent(@PathVariable("id") Long id) {

        PlainNoteView plainNoteView = noteService.findOne(id);

        return plainNoteView;

    }

    /**
     * delete data for Event
     *
     * @param id identity
     * @return
     */
    @RequestMapping(value = {"/note/remove/{id}.data"}, method = RequestMethod.DELETE)
    public ResponseEntity<String> removeNote(@PathVariable("id") Long id) {

        if (id != null) {
            noteService.deleteNote(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } else {
            LOGGER.warn("Request event id is null");
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Сохранение данных
     *
     * @param noteView data view for a Event
     * @param result       info
     * @return
     */
    @RequestMapping(value = {"/note/save.data"}, method = RequestMethod.POST)
    public ResponseEntity<String> saveNote(@Valid @RequestBody PlainNoteView noteView, BindingResult result) {

        if (result.hasErrors()) {
            LOGGER.info("incorrect data: {}", result.getAllErrors());
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        } else {
            noteService.saveOrUpdateNote(noteView);
            return new ResponseEntity<String>(HttpStatus.OK);
        }

    }

    /**
     * @param pageNumber page number
     * @param pageSize   quantity field in one page
     * @param direction  desending or assending
     * @param column     column for sorting
     * @return Event page
     */
    @RequestMapping(value = "notelist/{pageNumber}/{pageSize}/{direction}/sortBy{column}.data", method = RequestMethod.GET)
    @ResponseBody
    public PageNoteView findEventList(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, @PathVariable("direction") String direction, @PathVariable("column") String column) {

        return noteService.findAllNoteList(pageNumber, pageSize, direction, column);

    }


}
