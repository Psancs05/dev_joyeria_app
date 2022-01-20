package globals.enums;

//Enumerado que determina los permisos de un usuario
public enum TipoUsuario {
    ADMINISTRADOR(1), // calls constructor with value 1
    CAJERO(2); // calls constructor with value 2

    private final int levelCode;

    private TipoUsuario(int levelCode) {
        this.levelCode = levelCode;
    }

    public int getLevelCode() {
        return levelCode;
    }

    public static TipoUsuario getTipoUsuario(int levelCode) {
        switch (levelCode) {
            case 1:
                return ADMINISTRADOR;
            case 2:
                return CAJERO;
            default:
                return null;
        }
    }
}
