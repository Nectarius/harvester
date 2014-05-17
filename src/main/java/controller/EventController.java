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
import view.PageEventView;
import view.PlainEventView;

import javax.validation.Valid;

/**
 * Created by nectarius on 11/16/13.
 */
@Controller
public class EventController {

    @Autowired
    private EventService EventService;

    private final static Logger LOGGER = LoggerFactory.getLogger(EventController.class.getName());

    /**
     * @param id identity
     * @return data view
     */
    @RequestMapping(value = {"/event/{id}.data"}, method = RequestMethod.GET)
    @ResponseBody
    public PlainEventView findEvent(@PathVariable("id") Long id) {

        PlainEventView plainEventView = EventService.findOne(id);

        return plainEventView;

    }

    /**
     * delete data for Event
     *
     * @param id identity
     * @return
     */
    @RequestMapping(value = {"/event/remove/{id}.data"}, method = RequestMethod.DELETE)
    public ResponseEntity<String> removeEvent(@PathVariable("id") Long id) {

        if (id != null) {
            EventService.deleteEvent(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } else {
            LOGGER.warn("Request event id is null");
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Сохранение данных
     *
     * @param EventView data view for a Event
     * @param result       info
     * @return
     */
    @RequestMapping(value = {"/event/save.data"}, method = RequestMethod.POST)
    public ResponseEntity<String> saveEvent(@Valid @RequestBody PlainEventView EventView, BindingResult result) {

        if (result.hasErrors()) {
            LOGGER.info("incorrect data: {}", result.getAllErrors());
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        } else {
            EventService.saveOrUpdateEvent(EventView);
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
    @RequestMapping(value = "eventlist/{pageNumber}/{pageSize}/{direction}/sortBy{column}.data", method = RequestMethod.GET)
    @ResponseBody
    public PageEventView findEventList(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, @PathVariable("direction") String direction, @PathVariable("column") String column) {

        return EventService.findAllEventList(pageNumber, pageSize, direction, column);

    }




}
