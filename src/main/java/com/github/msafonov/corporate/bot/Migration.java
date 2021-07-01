package com.github.msafonov.corporate.bot;

import org.flywaydb.core.Flyway;

import java.sql.SQLException;

public class Migration {
    private String url;
    private String user;
    private String password;
    private String schema;

    public Migration(String url, String user, String password, String schema) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.schema = schema;
    }

    public void migrateToNewVersion() throws SQLException {
        String data_url = url + "?currentSchema=" + schema;
        Flyway flyway = Flyway.configure().dataSource(data_url, user, password).load();
        flyway.migrate();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
