package com.xl0e.nutric.web.server;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class TomcatConfigurator {
    final Tomcat tomcat;

    TomcatConfigurator(Tomcat tomcat) {
        this.tomcat = tomcat;
    }

    public void configureTomcat(Tomcat tomcat) {

        for (Connector c : tomcat.getService().findConnectors()) {
            System.out.println("--" + c.getScheme() + " " + c.getProtocol());
        }
        tomcat.setPort(8888);
    }

    public void configureHttpConnector() {
        Connector connector = tomcat.getConnector();
        for (Connector c : tomcat.getService().findConnectors()) {
            System.out.println("--" + c.getScheme() + " " + c.getProtocol());
        }
        tomcat.setPort(8888);
    }
}
