package modelo.VO;

public class ProveedorVO {
    // * Atributos
    private String CIF;
    private String nombre;

    // * Constructor
    public ProveedorVO(String CIF, String nombre) {
        this.CIF = CIF;
        this.nombre = nombre;
    }

    // * Getters y Setters
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

    // * Metodos toString y equals
    @Override
    public String toString() {
        return "ProveedorVO [CIF=" + CIF + ", nombre=" + nombre + "]";
    }

    public String toStringListado() {
        return nombre + "          " + CIF;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProveedorVO other = (ProveedorVO) obj;
        if (CIF == null) {
            if (other.CIF != null)
                return false;
        } else if (!CIF.equals(other.CIF))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

}
