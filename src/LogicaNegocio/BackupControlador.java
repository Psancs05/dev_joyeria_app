package LogicaNegocio;

import Vista.Backup.CrearBackupVista;
import Vista.Backup.RestaurarBackupVista;
import modelo.conexion.Conexion;

public class BackupControlador {
	
	private RestaurarBackupVista vistaRestaurarBackup;
	private CrearBackupVista vistaCrearBackup;
	private Conexion conexionDB;
	
	public BackupControlador(RestaurarBackupVista vistaRestaurarBackup, CrearBackupVista vistaCrearBackup,
			Conexion conexionDB) {
		this.vistaRestaurarBackup = vistaRestaurarBackup;
		this.vistaCrearBackup = vistaCrearBackup;
		this.conexionDB = conexionDB;
	}
	
	public void generarBackup(String ubicacion) {
		//TODO
	}
	
	public void restaurarBackup(String unicacion) {
		//TODO
	}

	public RestaurarBackupVista getVistaRestaurarBackup() {
		return vistaRestaurarBackup;
	}

	public void setVistaRestaurarBackup(RestaurarBackupVista vistaRestaurarBackup) {
		this.vistaRestaurarBackup = vistaRestaurarBackup;
	}

	public CrearBackupVista getVistaCrearBackup() {
		return vistaCrearBackup;
	}

	public void setVistaCrearBackup(CrearBackupVista vistaCrearBackup) {
		this.vistaCrearBackup = vistaCrearBackup;
	}

	public Conexion getConexionDB() {
		return conexionDB;
	}

	public void setConexionDB(Conexion conexionDB) {
		this.conexionDB = conexionDB;
	}
	
}
