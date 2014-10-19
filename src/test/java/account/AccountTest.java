package account;

import entity.Account;
import entity.Event;
import entity.Privilege;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import repository.AccountRepository;
import repository.EventRepository;
import repository.PrivilegeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nectarius on 18.05.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class AccountTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Test
    @Transactional
    @Rollback(value = true)
    public void test() {

        Account account = new Account();

        account.setLogin("Felix");

        account.setPassword("Velvet");

        Privilege privilege = new Privilege();

        privilege.setName("Guest");



        privilege = privilegeRepository.save(privilege);

        List<Privilege> privilegeList = new ArrayList<Privilege>();

        privilegeList .add(privilege);

        account.setPrivilegeList(privilegeList);

        account = accountRepository.save(account);

    }

}
