package modelo.VO;

public class ProveedorVO {
    private String CIF;
    private String nombre;

    public ProveedorVO(String CIF, String nombre) {
        this.CIF = CIF;
        this.nombre = nombre;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
