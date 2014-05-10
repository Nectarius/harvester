import entity.GuestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import service.EmployeeService;
import service.GuestService;
import view.PlainEmployeeView;
import view.PlainGuestView;

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

    @Test
    @Transactional
    @Rollback(value = true)
    public void test() {

        Random random = new Random(500);

        for (int i = 0; i < 55; i++) {
            PlainGuestView guest = new PlainGuestView();
            guest.setName("Имя" + String.valueOf(i));
            guest.setSurname("Фамилия" + random.nextInt());
            guest.setStatus(GuestStatus.NOT_YET_DECIDED);
            guestService.saveOrUpdateGuest(guest);
        }

    }

}
