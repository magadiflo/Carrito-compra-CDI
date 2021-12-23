package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ProducesResources {

    @Resource(name = "jdbc/mysqlDB")
    private DataSource ds;

    @Produces
    @RequestScoped
    @Named("conn")
    private Connection beanConnection () throws NamingException, SQLException {
        return this.ds.getConnection();
    }

}
