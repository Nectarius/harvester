package repository;

import entity.Account;
import entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nectarius on 11/17/13.
 */

public interface EventRepository extends JpaRepository<Event, Long>{



}
