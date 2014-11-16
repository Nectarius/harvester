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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.EventRepository;
import repository.GuestRepository;
import service.EventService;
import service.report.ExporterService;
import view.*;
import viewmapper.PlainEventViewMapper;
import viewmapper.PlainGuestViewMapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nectarius on 17.05.14.
 */
@Service
public class EventServiceImpl implements EventService {

    static final String EVENTS_TEMPLATE = "/reports/events.jrxml";

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PlainEventViewMapper plainEventViewMapper;

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class.getName());

    @Autowired
    private ExporterService exporter;

    /**
     * Поиск данных о сотруднике по его идентификатору
     *
     * @param employeeId идентификатор
     * @return представление данных о сотруднике
     */
    public PlainEventView findOne(Long employeeId) {
        return plainEventViewMapper.create(eventRepository.findOne(employeeId));
    }

    @Override
    public void downloadEvents(String type, String token, HttpServletResponse response) {

            try {
                // 1. Add report parameters
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("Title", "User Report");

                // 2.  Retrieve template
                InputStream reportStream = this.getClass().getResourceAsStream(EVENTS_TEMPLATE);

                // 3. Convert template to JasperDesign
                JasperDesign jd = JRXmlLoader.load(reportStream);

                // 4. Compile design to JasperReport
                JasperReport jr = JasperCompileManager.compileReport(jd);

                // 5. Create the JasperPrint object
                // Make sure to pass the JasperReport, report parameters, and data source
                List<PlainEventView> notes = findAllEvents();

                JasperPrint jp = JasperFillManager.fillReport(jr, params, new JRBeanCollectionDataSource(notes));

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
     * @param eventId идентификатор
     */
    public void deleteEvent(Long eventId) {
        eventRepository.delete(eventId);
        LOGGER.info("Deleted event: id {}", eventId);
    }

    /**
     * Сохранение или обновление данных о сотруднике
     *
     * @param view представление данных о сотруднике
     * @return представление данных о сотруднике после изменений
     */
    public PlainEventView saveOrUpdateEvent(PlainEventView view) {

        Event event;

        if (view.getId() == null) {
            event = new Event();
        } else {
            event = eventRepository.findOne(view.getId());
        }

        plainEventViewMapper.copyFrom(view, event);

        event = eventRepository.save(event);

        LOGGER.info("Saved event: id {} name {}", event.getId(), event.getName());

        return plainEventViewMapper.create(event);
    }

    /**
     * @return Страница данных о сотрудниках
     */
    public List<PlainEventView> findAllEvents() {
        List<Event> eventList = eventRepository.findAll();
        return  plainEventViewMapper.createList(eventList);
    }

    /**
     * @param pageNumber номер страницы
     * @param pageSize   количество полей на одной странице
     * @param direction  по возрастанию или убыванию сортировка
     * @param column     колонка для которой будет выполняться сортировка
     * @return Страница данных о сотрудниках
     */
    public PageEventView findAllEventList(int pageNumber, int pageSize, String direction, String column) {

        Sort.Direction direction_ = Sort.Direction.fromString(direction);

        PageRequest request = new PageRequest(pageNumber, pageSize, direction_, column);

        Page<Event> eventList = eventRepository.findAll(request);

        return new PageEventView(eventList.getTotalPages(), plainEventViewMapper.createList(eventList.getContent()));

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
