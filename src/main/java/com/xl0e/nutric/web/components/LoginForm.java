package com.xl0e.nutric.web.components;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.security.AuthenticationService;
import org.apache.tapestry5.security.impl.UserAndPassCredentials;

public class LoginForm {
    @Inject
    private AuthenticationService authenticationService;

    @Property
    private String username;

    @Property
    private String password;

    void onSuccessFromLoginForm() {
        authenticationService.login(new UserAndPassCredentials(username, password));
    }
}
