package repository;

import entity.Event;
import entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nectarius on 11/17/13.
 */

public interface GuestRepository extends JpaRepository<Guest, Long>{



}
