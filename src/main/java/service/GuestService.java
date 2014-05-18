package service;

import view.PageGuestView;
import view.PlainGuestView;

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
}
