package security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.util.Collection;

/**
 * @author Konstantin Molodtsov
 * @author $Author$ (current maintainer)
 * @version $Revision$, $Date$
 * @since 0.1
 */
public class GraphDashboardDecisionVoter implements AccessDecisionVoter<Object> {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        if (!authentication.isAuthenticated()) {
            return ACCESS_DENIED;
        }
        return 0;
    }

}
