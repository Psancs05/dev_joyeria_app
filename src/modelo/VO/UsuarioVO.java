package modelo.VO;

import globals.enums.TipoUsuario;

public class UsuarioVO {
    private String DNI;
    private String nombre;
    private String email;
    private String password;
    TipoUsuario tipoUsuario;

    public UsuarioVO(String DNI, String nombre, String email, String password, TipoUsuario tipoUsuario) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    // getters & setters
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
