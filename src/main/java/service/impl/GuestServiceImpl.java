package service.impl;

import entity.Guest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.GuestRepository;
import service.GuestService;
import view.PageGuestView;
import view.PlainGuestView;
import viewmapper.PlainGuestViewMapper;

/**
 * Created by nectarius on 10.05.14.
 */
@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private PlainGuestViewMapper plainGuestViewMapper;

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

        Page<Guest> employeeList = guestRepository.findAll(request);

        return new PageGuestView(employeeList.getTotalPages(), plainGuestViewMapper.createList(employeeList.getContent()));

    }

}
