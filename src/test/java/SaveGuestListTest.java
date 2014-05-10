import entity.GuestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import service.GuestService;
import view.PlainGuestView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nectarius on 12/17/13.
 * удалить перед отправкой
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class SaveGuestListTest {

    @Autowired
    private GuestService guestService;

    private PlainGuestView createGuest(String name, String surname, GuestStatus status){

        PlainGuestView plainGuestView = new PlainGuestView();

        plainGuestView.setName(name);

        plainGuestView.setSurname(surname);

        plainGuestView.setStatus(status);
        return plainGuestView;

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void test() {

        List<PlainGuestView> guestList = new ArrayList<PlainGuestView>();

        guestList.add(createGuest("Ваня", "К", GuestStatus.PROMISED));

        guestList.add(createGuest("Лёша", "М", GuestStatus.PROMISED));

        guestList.add(createGuest("Захар", "...", GuestStatus.NOT_YET_DECIDED));

        guestList.add(createGuest("Костя", "М", GuestStatus.PROMISED));

        guestList.add(createGuest("Женя", "К", GuestStatus.PROMISED));

        guestList.add(createGuest("Витя", "П", GuestStatus.PROMISED));

        guestList.add(createGuest("Василий", "Решетников", GuestStatus.UNLIKELY));

        guestList.add(createGuest("Олег", "К", GuestStatus.PROMISED));

        guestList.add(createGuest("Илья", "Ш", GuestStatus.NOT_YET_DECIDED));

        for (PlainGuestView guest : guestList) {

            guestService.saveOrUpdateGuest(guest);

        }

    }

}
