package com.xl0e.nutric.web.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;

import org.apache.tapestry5.web.config.AbstractWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;

import com.xl0e.nutric.config.AppProperties;
import com.xl0e.nutric.config.HibernateConfig;
import com.xl0e.nutric.config.ServiceConfig;
import com.xl0e.nutric.config.TestDataConfig;
import com.xl0e.nutric.web.services.AppModule;
import com.xl0e.util.C;

public class AppWebApplicationInitializerImpl extends AbstractWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        getRootContext().refresh();

        servletContext.setSessionTrackingModes(C.newHashSet(SessionTrackingMode.COOKIE));
    }

    @Override
    protected boolean getUseExternalSpringContext() {
        return true;
    }

    @Override
    protected Class<?> getAppModuleClass() {
        return AppModule.class;
    }

    @Override
    protected String[] getSpringConfigLocations() {
        List<String> list = new ArrayList<>();
        list.add(HibernateConfig.class.getName());
        list.add(ServiceConfig.class.getName());
        if (AppProperties.FILL_TEST_DATA.getBoolean()) {
            list.add(TestDataConfig.class.getName());
        }
        return list.toArray(new String[list.size()]);

    }

}
