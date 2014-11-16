package service;

import view.PageGuestView;
import view.PlainGuestView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by nectarius on 10.05.14.
 */
public interface GuestService {

    PageGuestView findAllGuestList(int pageNumber, int pageSize, String direction, String column);

    PlainGuestView saveOrUpdateGuest(PlainGuestView view);

    void deleteGuest(Long guestId);

    PlainGuestView findOne(Long guestId);

    PageGuestView findAllGuestList(Long eventId, Integer pageNumber, Integer pageSize, String direction, String column);

    PlainGuestView save(PlainGuestView guest, Long id);

    void downloadGuests(Long eventId, String type, String token, HttpServletResponse response);

    List<PlainGuestView> findAllGuests(Long eventId);
}
