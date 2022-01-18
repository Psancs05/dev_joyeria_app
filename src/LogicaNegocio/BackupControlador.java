package LogicaNegocio;
import modelo.conexion.Conexion;

public class BackupControlador {


	private static BackupControlador miBackupControlador;
	
	public static BackupControlador getInstance(){
		if(miBackupControlador == null){
			miBackupControlador = new BackupControlador();
		}
		return miBackupControlador;
	}
	
	public void generarBackup(String ubicacion) {
		Conexion.getInstance().generarBackup(ubicacion);
	}
	
	public void restaurarBackup(String ubicacion) {
		Conexion.getInstance().restaurarBackup(ubicacion);
	}


	
}
