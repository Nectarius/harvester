package event;

import entity.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import repository.EventRepository;

/**
 * Created by nectarius on 17.05.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class SaveEventTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    @Transactional
    @Rollback(value = true)
    public void test() {

        Event event = new Event();

        event.setName("Пейнтбол :-)");

        event.setDescription("...");

        event.setWebSite("http://www.pk-blokada.ru/");

        event.setPath("...");

        event.setStatus(true);

        eventRepository.save(event);

    }

}
