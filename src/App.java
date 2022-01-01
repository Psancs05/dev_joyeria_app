import globals.enums.TipoUsuario;
import modelo.DAO.UsuarioDAO;
import modelo.VO.UsuarioVO;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        UsuarioVO napuh = new UsuarioVO("123456F", "Naaman", "nhuerp00", "root", TipoUsuario.CAJERO);
        UsuarioDAO udao = new UsuarioDAO();
        if (udao.create(napuh)) {
            System.out.println("se ha creado");
        } else {
            System.out.println("No se ha creado");
        }

        UsuarioVO copiaNap = (UsuarioVO) udao.search(napuh);
        System.out.println("DNI DE COPIANAP: " + copiaNap.getDNI());
        System.out.println("==?: " + (copiaNap == napuh));
        System.out.println("equals?: " + napuh.equals(copiaNap));
        System.out.println(napuh.toString());
        System.out.println(copiaNap.toString());

    }

    public void iniciar() {
        // TODO: implement
    }
}
