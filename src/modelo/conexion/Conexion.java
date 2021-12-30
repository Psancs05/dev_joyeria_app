package modelo.conexion;

import globals.MiSingleton;

public class Conexion extends MiSingleton {
    private String db;
    private String login;
    private String password;
    private String url;
    private String connection;

    public Conexion(String db, String login, String password, String url, String connection) {
        this.db = db;
        this.login = login;
        this.password = password;
        this.url = url;
        this.connection = connection;
    }

    // Getters & Setters
    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public void disconnect() {
        // TODO: implement
    }

    public void generarBackup(String ubicacion) {
        // TODO: implement
    }

    public void restaurarBackup(String ubicacion) {
        // TODO: implement
    }
}
