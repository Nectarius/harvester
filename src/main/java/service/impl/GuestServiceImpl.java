package service.impl;

import entity.Event;
import entity.Guest;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.EventRepository;
import repository.GuestRepository;
import service.EventService;
import service.GuestService;
import service.report.ExporterService;
import view.PageGuestView;
import view.PlainEventView;
import view.PlainGuestView;
import viewmapper.PlainEventViewMapper;
import viewmapper.PlainGuestViewMapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nectarius on 10.05.14.
 */
@Service
public class GuestServiceImpl implements GuestService {

    static final String GUESTS_TEMPLATE = "/reports/guests.jrxml";

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PlainGuestViewMapper plainGuestViewMapper;

    @Autowired
    private PlainEventViewMapper plainEventViewMapper;

    @Autowired
    private EventService eventService;

    @Autowired
    private ExporterService exporter;

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GuestServiceImpl.class.getName());

    /**
     * Поиск данных о сотруднике по его идентификатору
     *
     * @param employeeId идентификатор
     * @return представление данных о сотруднике
     */
    public PlainGuestView findOne(Long employeeId) {
        return plainGuestViewMapper.create(guestRepository.findOne(employeeId));
    }

    @Override
    public PageGuestView findAllGuestList(Long eventId, Integer pageNumber, Integer pageSize, String direction, String column) {
        Sort.Direction direction_ = Sort.Direction.fromString(direction);

        PageRequest request = new PageRequest(pageNumber, pageSize, direction_, column);

        Page<Guest> employeeList = guestRepository.findAllByEventId(eventId, request);

        PageGuestView page = new PageGuestView(employeeList.getTotalPages(), plainGuestViewMapper.createList(employeeList.getContent()));

        Event event = eventRepository.findOne(eventId);

        page.setEvent(plainEventViewMapper.create(event));

        return page;
    }


    public List<PlainGuestView> findAllGuests(Long eventId) {
        List<Guest>  guests = guestRepository.findAllByEventId(eventId);
        return plainGuestViewMapper.createList(guests);
    }

    @Override
    public PlainGuestView save(PlainGuestView view, Long eventId) {

        Guest guest = new Guest();

        plainGuestViewMapper.copyFrom(view, guest);

        Event event = eventRepository.findOne(eventId);

        guest.setEvent(event );

        guest = guestRepository.save(guest);

        LOGGER.info("Saved guest: id {} name {}", guest.getId(), guest.getName());

        return plainGuestViewMapper.create(guest);

    }

    @Override
    public void downloadGuests(Long eventId, String type, String token, HttpServletResponse response) {

        try {
            // 1. Add report parameters
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("Title", "User Report");

            // 2.  Retrieve template
            InputStream reportStream = this.getClass().getResourceAsStream(GUESTS_TEMPLATE );

            // 3. Convert template to JasperDesign
            JasperDesign jd = JRXmlLoader.load(reportStream);

            // 4. Compile design to JasperReport
            JasperReport jr = JasperCompileManager.compileReport(jd);

            // 5. Create the JasperPrint object
            // Make sure to pass the JasperReport, report parameters, and data source
            List<PlainGuestView> guests = findAllGuests(eventId);

            params.put("guestsDataSource", new JRBeanCollectionDataSource(guests));

            List<PlainEventView> events = new ArrayList<PlainEventView>();

            PlainEventView plainEventView = eventService.findOne(eventId);

            events.add(plainEventView);

            JasperPrint jp = JasperFillManager.fillReport(jr, params, new JRBeanCollectionDataSource(events));

            // 6. Create an output byte stream where data will be written
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // 7. Export report
            exporter.export(type, jp, response, baos);

            // 8. Write to reponse stream
            write(token, response, baos);

        } catch (JRException jre) {
            //logger.error("Unable to process download");
            throw new RuntimeException(jre);
        }

    }

    /**
     * Удаление данных о сотруднике по его идентификатору
     *
     * @param guestId идентификатор
     */
    public void deleteGuest(Long guestId) {
        guestRepository.delete(guestId);
        LOGGER.info("Deleted guest: id {}", guestId);
    }

    /**
     * Сохранение или обновление данных о сотруднике
     *
     * @param view представление данных о сотруднике
     * @return представление данных о сотруднике после изменений
     */
    public PlainGuestView saveOrUpdateGuest(PlainGuestView view) {

        Guest guest;

        if (view.getId() == null) {
            guest = new Guest();
        } else {
            guest = guestRepository.findOne(view.getId());
        }

        plainGuestViewMapper.copyFrom(view, guest);

        guest = guestRepository.save(guest);

        LOGGER.info("Saved guest: id {} name {}", guest.getId(), guest.getName());

        return plainGuestViewMapper.create(guest);
    }

    /**
     * @param pageNumber номер страницы
     * @param pageSize   количество полей на одной странице
     * @param direction  по возрастанию или убыванию сортировка
     * @param column     колонка для которой будет выполняться сортировка
     * @return Страница данных о сотрудниках
     */
    public PageGuestView findAllGuestList(int pageNumber, int pageSize, String direction, String column) {

        Sort.Direction direction_ = Sort.Direction.fromString(direction);

        PageRequest request = new PageRequest(pageNumber, pageSize, direction_, column);

        Event event = findLastEvent();

        if(event == null){
           return new PageGuestView(0, new ArrayList<PlainGuestView>());
        }

        Page<Guest> employeeList = guestRepository.findAllByEventId(event.getId(), request);

        PageGuestView page = new PageGuestView(employeeList.getTotalPages(), plainGuestViewMapper.createList(employeeList.getContent()));

        page.setEvent(plainEventViewMapper.create(event));

        return page;

    }

    private Event findLastEvent(){
        Pageable theOne = new PageRequest(0, 1);
        Page<Event> eventList = eventRepository.findActive(theOne);
        if(eventList.getSize()<=1){
            return null;
        } else{
           return eventList.getContent().get(0);
        }

    }

    /**
     * Writes the report to the output stream
     */
    private void write(String token, HttpServletResponse response,
                       ByteArrayOutputStream baos) {

        try {
            // logger.debug(baos.size());

            // Retrieve output stream
            ServletOutputStream outputStream = response.getOutputStream();
            // Write to output stream
            baos.writeTo(outputStream);
            // Flush the stream
            outputStream.flush();

            // Remove download token
            //tokenService.remove(token);

        } catch (Exception e) {
            //logger.error("Unable to write report to the output stream");
            throw new RuntimeException(e);
        }
    }

}
