
import entity.GuestStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import service.GuestService;
import view.PlainGuestView;


/**
 * Created by nectarius on 12/17/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class SaveGuestTest {

    @Autowired
    private GuestService guestService;

    @Test
    @Transactional
    @Rollback(value = true)
    public void test() {

        PlainGuestView plainGuestView = new PlainGuestView();

        plainGuestView.setName("Alexander");

        plainGuestView.setSurname("Alexandrov");

        plainGuestView.setStatus(GuestStatus.NOT_YET_DECIDED);

        Long id = guestService.saveOrUpdateGuest(plainGuestView).getId();

        String surname = guestService.findOne(id).getSurname();

        System.out.println(id);

        Assert.assertEquals(true, surname.equals("Alexandrov"));

    }
}
