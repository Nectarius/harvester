package repository;

import entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nectarius on 11/17/13.
 */

public interface WordRepository extends JpaRepository<Word , Long>{



}
