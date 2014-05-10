package repository;

import entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nectarius on 11/17/13.
 */

public interface AccountRepository extends JpaRepository<Account, Long>{



}
