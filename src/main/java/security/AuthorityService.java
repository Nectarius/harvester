package security;

import entity.Account;
import entity.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.AccountRepository;
import service.AccountService;
import view.PlainAccountView;


import java.util.LinkedList;
import java.util.List;


/**
 *
 */
@Service("authorityService")
public class AuthorityService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * @param name n
     * @return user details
     */
    @Override
    public UserDetails loadUserByUsername(String name) {

        Account account = accountRepository.findByLogin(name);
        List<SimpleGrantedAuthority> roleList = new LinkedList<SimpleGrantedAuthority>();
        if (account == null) {
            throw new UsernameNotFoundException("Unknown user (" + name + ")!");
        } // if
        for (Privilege privilege :account.getPrivilegeList()){
            String role = privilege.getName().toUpperCase();
            roleList.add(new SimpleGrantedAuthority("ROLE_" + role));
        } // for
        return new org.springframework.security.core.userdetails.User(account.getLogin(),
                account.getPassword(), true, true, true, true, roleList);

    }

}
