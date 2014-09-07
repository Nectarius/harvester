package repository;

import entity.Account;
import entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nectarius on 11/17/13.
 */

public interface NoteRepository extends JpaRepository<Note, Long>{


}
