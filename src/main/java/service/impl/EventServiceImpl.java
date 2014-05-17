package service.impl;

import entity.Event;
import entity.Guest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.EventRepository;
import repository.GuestRepository;
import service.EventService;
import view.PageEventView;
import view.PageGuestView;
import view.PlainEventView;
import view.PlainGuestView;
import viewmapper.PlainEventViewMapper;
import viewmapper.PlainGuestViewMapper;

/**
 * Created by nectarius on 17.05.14.
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PlainEventViewMapper plainEventViewMapper;

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class.getName());

    /**
     * Поиск данных о сотруднике по его идентификатору
     *
     * @param employeeId идентификатор
     * @return представление данных о сотруднике
     */
    public PlainEventView findOne(Long employeeId) {
        return plainEventViewMapper.create(eventRepository.findOne(employeeId));
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

}
