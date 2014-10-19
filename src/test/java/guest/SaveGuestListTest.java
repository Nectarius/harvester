package guest;

import entity.Event;
import entity.GuestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import repository.EventRepository;
import service.GuestService;
import view.PlainGuestView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nectarius on 12/17/13.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class SaveGuestListTest {

    @Autowired
    private GuestService guestService;

    @Autowired
    private EventRepository eventRepository;

    private PlainGuestView createGuest(String name, String surname, GuestStatus status, String brief, String description, String byWhomWasAdded, String transport){

        PlainGuestView plainGuestView = new PlainGuestView();

        plainGuestView.setName(name);

        plainGuestView.setSurname(surname);

        plainGuestView.setBrief(brief);

        plainGuestView.setDescription(description);

        plainGuestView.setByWhomWasAdded(byWhomWasAdded);

        plainGuestView.setTransport(transport);

        plainGuestView.setStatus(status);

        return plainGuestView;

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void test() {

        List<PlainGuestView> guestList = new ArrayList<PlainGuestView>();

        Event event = eventRepository.findByName("Пейнтбол :-)");

        guestList.add(createGuest("Женя", "К", GuestStatus.PROMISED, "", "подробнее информация ....", "", ""));

        guestList.add(createGuest("Ваня", "К", GuestStatus.PROMISED, "", "подробнее информация ....", "", ""));

        guestList.add(createGuest("Лёша", "М", GuestStatus.PROMISED, "", "подробнее информация ....", "...", "..."));

        guestList.add(createGuest("Коля", "П", GuestStatus.PROMISED, "", "подробнее информация ....", "", ""));

        guestList.add(createGuest("Вася", "К", GuestStatus.PROMISED, "", "подробнее информация ....", "", ""));

        guestList.add(createGuest("Денис", "Ф", GuestStatus.NOT_YET_DECIDED, "", "подробнее информация ....", "", ""));

        guestList.add(createGuest("Олег", "К", GuestStatus.NOT_YET_DECIDED, "", "подробнее информация ....", "", ""));


        for (PlainGuestView guest : guestList) {

            guestService.save(guest, event.getId());

        }

    }

}
