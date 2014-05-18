package repository;

import entity.Event;
import entity.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



/**
 * Created by nectarius on 11/17/13.
 */

public interface GuestRepository extends JpaRepository<Guest, Long>{

    @Query("select g from Guest g where g.event.id = :eventId ")
    Page<Guest> findAllByEventId(@Param("eventId") Long eventId, Pageable pageable);
}
