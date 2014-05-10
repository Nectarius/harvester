package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.GuestService;
import view.PageGuestView;
import view.PlainGuestView;

import javax.validation.Valid;

/**
 * Created by nectarius on 11/16/13.
 */
@Controller
public class HarvesterController  {

    @Autowired
    private GuestService guestService;

    private final static Logger LOGGER = LoggerFactory.getLogger(HarvesterController.class.getName());

    /**
     * @param id identity
     * @return data view
     */
    @RequestMapping(value = {"/guest/{id}.data"}, method = RequestMethod.GET)
    @ResponseBody
    public PlainGuestView findGuest(@PathVariable("id") Long id) {

        return guestService.findOne(id);

    }

    /**
     * delete data for guest
     *
     * @param id identity
     * @return
     */
    @RequestMapping(value = {"/guest/remove/{id}.data"}, method = RequestMethod.DELETE)
    public ResponseEntity<String> removeGuest(@PathVariable("id") Long id) {

        if (id != null) {
            guestService.deleteGuest(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } else {
            LOGGER.warn("Request guest id is null");
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Сохранение данных
     *
     * @param guestView data view for a guest
     * @param result       info
     * @return
     */
    @RequestMapping(value = {"/guest/save.data"}, method = RequestMethod.POST)
    public ResponseEntity<String> saveGuest(@Valid @RequestBody PlainGuestView guestView, BindingResult result) {

        if (result.hasErrors()) {
            LOGGER.info("incorrect data: {}", result.getAllErrors());
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        } else {
            guestService.saveOrUpdateGuest(guestView);
            return new ResponseEntity<String>(HttpStatus.OK);
        }

    }

    /**
     * @param pageNumber page number
     * @param pageSize   quantity field in one page
     * @param direction  desending or assending
     * @param column     column for sorting
     * @return guest page
     */
    @RequestMapping(value = "guestlist/{pageNumber}/{pageSize}/{direction}/sortBy{column}.data", method = RequestMethod.GET)
    @ResponseBody
    public PageGuestView findGuestList(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, @PathVariable("direction") String direction, @PathVariable("column") String column) {

        return guestService.findAllGuestList(pageNumber, pageSize, direction, column);

    }




}
