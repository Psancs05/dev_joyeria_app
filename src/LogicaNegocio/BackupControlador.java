package LogicaNegocio;
import modelo.conexion.Conexion;

public class BackupControlador {


	private static BackupControlador miBackupControlador;
	
	
	/** 
	 * Singleton de BackupControlador
	 * @return BackupControlador
	 */
	public static BackupControlador getInstance(){
		if(miBackupControlador == null){
			miBackupControlador = new BackupControlador();
		}
		return miBackupControlador;
	}
	
	/** 
	 * Metodo que genera un backup de la base de datos en la ubicacion especificada
	 * @param ubicacion path donde guardar el backup
	 */
	public void generarBackup(String ubicacion) {
		Conexion.getInstance().generarBackup(ubicacion);
	}
	
	
	/** 
	 * Metodo al que se le pasa el path the un backup en el sistema y restaura la base de datos con ese backup
	 * @param ubicacion path donde esta el backup
	 */
	public void restaurarBackup(String ubicacion) {
		Conexion.getInstance().restaurarBackup(ubicacion);
	}


	
}
