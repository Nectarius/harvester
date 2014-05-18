package service.impl;

import entity.Event;
import entity.Guest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.EventRepository;
import repository.GuestRepository;
import service.GuestService;
import view.PageGuestView;
import view.PlainGuestView;
import viewmapper.PlainEventViewMapper;
import viewmapper.PlainGuestViewMapper;

import java.util.List;

/**
 * Created by nectarius on 10.05.14.
 */
@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PlainGuestViewMapper plainGuestViewMapper;

    @Autowired
    private PlainEventViewMapper plainEventViewMapper;

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

        Page<Guest> employeeList = guestRepository.findAllByEventId(event.getId(), request);

        PageGuestView page = new PageGuestView(employeeList.getTotalPages(), plainGuestViewMapper.createList(employeeList.getContent()));

        page.setEvent(plainEventViewMapper.create(event));

        return page;

    }

    private Event findLastEvent(){
        Pageable theOne = new PageRequest(0, 1);
        Page<Event> eventList = eventRepository.findActive(theOne);
        if(eventList.getSize()!=1){
            return null;
        } else{
           return eventList.getContent().get(0);
        }

    }

}
