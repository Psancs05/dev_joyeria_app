import LogicaNegocio.UsuarioControlador;
import java.awt.EventQueue;

public class App {
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioControlador.getInstance().mostrarLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
