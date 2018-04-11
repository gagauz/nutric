package com.xl0e.nutric.web.components;

import com.xl0e.nutric.dao.AccountDao;
import com.xl0e.nutric.model.Account;
import com.xl0e.nutric.web.pages.Index;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.security.AuthenticationService;
import org.apache.tapestry5.security.api.User;
import org.apache.tapestry5.security.impl.UsernamePasswordCredentials;
import org.apache.tapestry5.web.services.AlertManagerExt;
import org.apache.tapestry5.web.services.RedirectLink;

public class LoginForm {

    @Component
    private Form loginForm;

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private AccountDao accountDao;

    @Inject
    private Messages messages;

    @Inject
    private AlertManagerExt alertManagerExt;

    @Property
    private String username;

    @Property
    private String password;

    @Parameter(autoconnect = true)
    @Property
    private String mode;

    Object onSuccessFromLoginForm() {

        if (isRegister()) {
            Account a = new Account();
            a.setUsername(username);
            a.setPassword(password);
            try {
                accountDao.save(a);
            } catch (Exception e) {
                alertManagerExt.errorCode("register-failed");
                return RedirectLink.forPage(Index.class, "register");
            }
        }

        User result = authenticationService.login(new UsernamePasswordCredentials(username, password, true));
        if (null == result) {
            alertManagerExt.errorCode("login-failed");
        }
        return RedirectLink.forPage(Index.class);
    }

    public boolean isRegister() {
        return "register".equals(mode);
    }
}
