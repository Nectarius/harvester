package service;

import view.PlainAccountView;

import java.util.List;

/**
 * @author Konstantin Molodtsov
 * @author $Author$ (current maintainer)
 * @version $Revision$, $Date$
 * @since 0.1
 */
public interface AccountService {

    /**
     *
     * @param name n
     * @return account view
     */
    PlainAccountView findByLogin(String name);
}
