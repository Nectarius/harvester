package service;

import view.PageEventView;
import view.PlainEventView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by nectarius on 17.05.14.
 */
public interface EventService {

    PageEventView findAllEventList(int pageNumber, int pageSize, String direction, String column);

    PlainEventView saveOrUpdateEvent(PlainEventView view);

    void deleteEvent(Long eventId);

    PlainEventView findOne(Long eventId);

    void downloadEvents(String author, String type, String token, HttpServletResponse response);

}
