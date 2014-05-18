package repository;

import entity.Account;
import entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by nectarius on 11/17/13.
 */

public interface EventRepository extends JpaRepository<Event, Long>{

    @Query("select e from Event e where e.status = true")
    Page<Event> findActive(Pageable pageable);

    Event findByName(String name);
}
