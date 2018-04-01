package com.xl0e.nutric.web.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.security.AuthenticationService;

@Import(stylesheet = "styles/combined.less", module = "bootstrap/collapse")
public class Template {

    @Parameter(autoconnect = true)
    @Property
    private Block navbar;

    @Inject
    private AuthenticationService authenticationService;

    void onLogout() {
        authenticationService.logout();
    }

}
