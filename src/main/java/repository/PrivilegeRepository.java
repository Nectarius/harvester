package repository;

import entity.Account;
import entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nectarius on 11/17/13.
 */

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{


}
