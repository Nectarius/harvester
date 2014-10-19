package repository;

import entity.Account;
import entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by nectarius on 11/17/13.
 */

public interface NoteRepository extends JpaRepository<Note, Long>{

    @Query("select n from Note n where n.author.id = :accountId ")
    Page<Note> findAllByAccountId(@Param("accountId") Long accountId, Pageable pageable);

    @Query("select n from Note n where n.author.id = :accountId ")
    List<Note> findAllByAuthor(@Param("accountId") Long accountId);

}
