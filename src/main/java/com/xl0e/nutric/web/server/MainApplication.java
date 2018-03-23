package com.xl0e.nutric.web.server;

import java.util.HashSet;
import java.util.Set;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.web.SpringServletContainerInitializer;

import com.xl0e.nutric.web.config.AppWebApplicationInitializerImpl;

public final class MainApplication {
    public static void main(String[] args) throws LifecycleException, InterruptedException {

        final String baseDir = System.getProperty("user.dir");

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(baseDir);
        Context context = createContext(tomcat, "/", baseDir + "/src/main/webapp");
        createDefaultServlet(context, "/");
        createConnector(tomcat, 8080, 8443);
        tomcat.init();
        tomcat.start();
        tomcat.getServer().await();
    }

    private static Connector createConnector(Tomcat tomcat, int port, int sslPort) {
        Connector connector = new Connector(Http11NioProtocol.class.getName());
        connector.setPort(port);
        if (port != sslPort) {
            connector.setRedirectPort(sslPort);
            createConnector(tomcat, sslPort, sslPort);
        }
        connector.setURIEncoding("utf-8");
        connector.setProperty("acceptCount", "500");
        connector.setProperty("connectionTimeout", "20000");
        connector.setProperty("maxHttpHeaderSize", "8192");
        connector.setProperty("server", "Application");
        connector.setXpoweredBy(false);

        if (port != sslPort) {
            tomcat.setConnector(connector);
        }

        tomcat.getService().addConnector(connector);

        return connector;
    }

    private static Context createContext(Tomcat tomcat, String contextPath, String baseDir) {
        if (contextPath.startsWith("/")) {
            contextPath = new StringBuilder(contextPath).deleteCharAt(0).toString();
        }
        Context context = tomcat.addContext(contextPath, baseDir);
        Set<Class<?>> set = new HashSet<>();
        set.add(AppWebApplicationInitializerImpl.class);
        context.addServletContainerInitializer(new SpringServletContainerInitializer(), set);
        return context;
    }

    private static Wrapper createDefaultServlet(Context inContext, String mapping) {
        Wrapper wrapper = Tomcat.addServlet(inContext, "default", DefaultServlet.class.getName());
        wrapper.addMapping(mapping);
        wrapper.setLoadOnStartup(1);
        return wrapper;
    }

}
