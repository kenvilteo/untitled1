package com.company.untitled1.app;

import com.company.untitled1.entity.User;
import io.jmix.ldap.userdetails.AbstractLdapUserDetailsSynchronizationStrategy;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.stereotype.Component;


@Component("ldap_CustomUserSynchronizationStrategy")
public class CustomUserSynchronizationStrategy extends AbstractLdapUserDetailsSynchronizationStrategy<User> {



    private String getFirstName(String fullName) {
        return fullName.split(" ")[0];
    }

    @Override
    protected Class<User> getUserClass() {
        return User.class;
    }

    //    private static Logger logz = LoggerFactory.getLogger(CustomUserSynchronizationStrategy.class);
    @Override
    protected void mapUserDetailsAttributes(User userDetails, DirContextOperations ctx) {
        userDetails.setFirstName(getFirstName(ctx.getStringAttribute("cn")));
        userDetails.setLastName(ctx.getStringAttribute("sn"));
        userDetails.setEmail(ctx.getStringAttribute("mail"));
        userDetails.setPassword("123456");
     /*   Logger logz = LoggerFactory.getLogger(CustomUserSynchronizationStrategy.class);
        logz.info("TestLog");*/

    }
}