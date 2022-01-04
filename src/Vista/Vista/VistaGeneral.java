package Vista.Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import LogicaNegocio.UsuarioControlador;

import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VistaGeneral {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	 public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaGeneral window = new VistaGeneral();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaGeneral() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		UsuarioControlador.getInstance().mostrarLogin();
		
		//TODO: hacer que no se muetsre la ventana principal antes de iniciar sesion (actualmente se muestran las 2 a la vez)

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(128, 128, 128));
		frame.setBackground(new Color(105, 105, 105));
		frame.setBounds(100, 100, 1000, 550);
		frame.setTitle("Claudio Panigua Joyas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setBackground(new Color(128, 128, 128));
		menuBar.setBounds(0, 0, 1072, 21);
		frame.setJMenuBar(menuBar);

		JMenu UsuarioMenuItem = new JMenu("Usuario");
		UsuarioMenuItem.setBackground(new Color(128, 128, 128));
		UsuarioMenuItem.setForeground(new Color(255, 255, 255));
		menuBar.add(UsuarioMenuItem);

		JMenuItem aniadirUsuario = new JMenuItem("Aniadir");
		aniadirUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioControlador.getInstance().mostrarAniadir();
			}
		});
		aniadirUsuario.setBackground(new Color(128, 128, 128));
		aniadirUsuario.setForeground(new Color(255, 255, 255));
		UsuarioMenuItem.add(aniadirUsuario);

		JMenuItem modificarUsuario = new JMenuItem("Modificar");
		modificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioControlador.getInstance().mostrarModificar();
			}
		});
		modificarUsuario.setForeground(new Color(255, 255, 255));
		modificarUsuario.setBackground(new Color(128, 128, 128));
		UsuarioMenuItem.add(modificarUsuario);

		JMenuItem eliminarUsuario = new JMenuItem("Eliminar");
		eliminarUsuario.setBackground(new Color(128, 128, 128));
		eliminarUsuario.setForeground(new Color(255, 255, 255));
		UsuarioMenuItem.add(eliminarUsuario);

		JMenu ProveedorMenuItem = new JMenu("Proveedor");
		ProveedorMenuItem.setBackground(new Color(128, 128, 128));
		ProveedorMenuItem.setForeground(new Color(255, 255, 255));
		menuBar.add(ProveedorMenuItem);

		JMenuItem aniadirProveedor = new JMenuItem("Aniadir");
		aniadirProveedor.setForeground(new Color(255, 255, 255));
		aniadirProveedor.setBackground(new Color(128, 128, 128));
		ProveedorMenuItem.add(aniadirProveedor);

		JMenuItem modificarProveedor = new JMenuItem("Modificar");
		modificarProveedor.setForeground(new Color(255, 255, 255));
		modificarProveedor.setBackground(new Color(128, 128, 128));
		ProveedorMenuItem.add(modificarProveedor);

		JMenuItem eliminarProveedor = new JMenuItem("Eliminar");
		eliminarProveedor.setForeground(new Color(255, 255, 255));
		eliminarProveedor.setBackground(new Color(128, 128, 128));
		ProveedorMenuItem.add(eliminarProveedor);

		JMenu ProductoMenuItem = new JMenu("Producto");
		ProductoMenuItem.setBackground(new Color(128, 128, 128));
		ProductoMenuItem.setForeground(new Color(255, 255, 255));
		menuBar.add(ProductoMenuItem);

		JMenuItem aniadirProducto = new JMenuItem("Aniadir");
		aniadirProducto.setForeground(new Color(255, 255, 255));
		aniadirProducto.setBackground(new Color(128, 128, 128));
		ProductoMenuItem.add(aniadirProducto);

		JMenuItem modificarProducto = new JMenuItem("Modificar");
		modificarProducto.setForeground(new Color(255, 255, 255));
		modificarProducto.setBackground(new Color(128, 128, 128));
		ProductoMenuItem.add(modificarProducto);

		JMenuItem eliminarProducto = new JMenuItem("Eliminar");
		eliminarProducto.setForeground(new Color(255, 255, 255));
		eliminarProducto.setBackground(new Color(128, 128, 128));
		ProductoMenuItem.add(eliminarProducto);

		JMenu FacturaMenuItem = new JMenu("Facturas");
		FacturaMenuItem.setBackground(new Color(128, 128, 128));
		FacturaMenuItem.setForeground(new Color(255, 255, 255));
		menuBar.add(FacturaMenuItem);

		JMenu BackupMenuItem = new JMenu("Backup");
		BackupMenuItem.setBackground(new Color(128, 128, 128));
		BackupMenuItem.setForeground(new Color(255, 255, 255));
		menuBar.add(BackupMenuItem);

		JMenuItem crearBackup = new JMenuItem("Crear");
		crearBackup.setForeground(new Color(255, 255, 255));
		crearBackup.setBackground(new Color(128, 128, 128));
		BackupMenuItem.add(crearBackup);

		JMenuItem restaurarBackup = new JMenuItem("Restaurar");
		restaurarBackup.setForeground(new Color(255, 255, 255));
		restaurarBackup.setBackground(new Color(128, 128, 128));
		BackupMenuItem.add(restaurarBackup);

		JMenu AyudaMenuItem = new JMenu("Ayuda");
		AyudaMenuItem.setBackground(new Color(128, 128, 128));
		AyudaMenuItem.setForeground(new Color(255, 255, 255));
		menuBar.add(AyudaMenuItem);

		JButton botonVenta = new JButton("Venta");
		botonVenta.setBounds(171, 282, 200, 87);
		botonVenta.setForeground(new Color(255, 255, 255));
		botonVenta.setBackground(new Color(50, 205, 50));
		botonVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("jsfdahsjkfdhsa");
			}
		});
		frame.getContentPane().add(botonVenta);

		JButton botonCatalogo = new JButton("Catalogo");
		botonCatalogo.setBounds(622, 282, 200, 87);
		botonCatalogo.setForeground(new Color(255, 255, 255));
		botonCatalogo.setBackground(new Color(102, 205, 170));
		botonCatalogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("sa");
			}
		});
		frame.getContentPane().add(botonCatalogo);
	}

}
