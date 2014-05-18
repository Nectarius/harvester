package service.impl;

import entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AccountRepository;
import service.AccountService;
import view.PlainAccountView;
import viewmapper.PlainAccountViewMapper;

import java.util.List;

/**
 * @author Konstantin Molodtsov
 * @author $Author$ (current maintainer)
 * @version $Revision$, $Date$
 * @since 0.1
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PlainAccountViewMapper plainAccountViewMapper;


    @Autowired
    private AccountRepository accountRepository;


    @Override
    public PlainAccountView findByLogin(String name) {

        Account account = accountRepository.findByLogin(name);

        return plainAccountViewMapper.create(account);

    }

}
