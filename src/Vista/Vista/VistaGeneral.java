package Vista.Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import LogicaNegocio.UsuarioControlador;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenu;
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

		//TODO: hacer que el frame principal no se muestre hasta que se complete el login
		Color blanco = new Color(255, 255, 255);
		Color negro = new Color(0, 0, 0);
		frame = new JFrame();
		frame.getContentPane().setBackground(blanco);
		frame.setBackground(blanco);
		frame.setBounds(100, 100, 1600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1584, 21);
		menuBar.setForeground(negro);
		menuBar.setBackground(blanco);
		frame.setJMenuBar(menuBar);

		JMenu UsuarioMenuItem = new JMenu("Usuario");
		UsuarioMenuItem.setBackground(blanco);
		UsuarioMenuItem.setForeground(negro);
		menuBar.add(UsuarioMenuItem);

		JMenuItem aniadirUsuario = new JMenuItem("Aniadir");
		aniadirUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioControlador.getInstance().mostrarAniadir();
			}
		});
		aniadirUsuario.setBackground(blanco);
		aniadirUsuario.setForeground(negro);
		UsuarioMenuItem.add(aniadirUsuario);

		JMenuItem modificarUsuario = new JMenuItem("Modificar");
		modificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioControlador.getInstance().mostrarModificar();
			}
		});
		modificarUsuario.setForeground(negro);
		modificarUsuario.setBackground(blanco);
		UsuarioMenuItem.add(modificarUsuario);

		JMenuItem eliminarUsuario = new JMenuItem("Eliminar");
		eliminarUsuario.setBackground(blanco);
		eliminarUsuario.setForeground(negro);
		UsuarioMenuItem.add(eliminarUsuario);

		JMenu ProveedorMenuItem = new JMenu("Proveedor");
		ProveedorMenuItem.setBackground(blanco);
		ProveedorMenuItem.setForeground(negro);
		menuBar.add(ProveedorMenuItem);

		JMenuItem aniadirProveedor = new JMenuItem("Aniadir");
		aniadirProveedor.setForeground(negro);
		aniadirProveedor.setBackground(blanco);
		ProveedorMenuItem.add(aniadirProveedor);

		JMenuItem modificarProveedor = new JMenuItem("Modificar");
		modificarProveedor.setForeground(negro);
		modificarProveedor.setBackground(blanco);
		ProveedorMenuItem.add(modificarProveedor);

		JMenuItem eliminarProveedor = new JMenuItem("Eliminar");
		eliminarProveedor.setForeground(negro);
		eliminarProveedor.setBackground(new Color(128, 128, 128));
		ProveedorMenuItem.add(eliminarProveedor);

		JMenu ProductoMenuItem = new JMenu("Producto");
		ProductoMenuItem.setBackground(blanco);
		ProductoMenuItem.setForeground(negro);
		menuBar.add(ProductoMenuItem);

		JMenuItem aniadirProducto = new JMenuItem("Aniadir");
		aniadirProducto.setForeground(negro);
		aniadirProducto.setBackground(blanco);
		ProductoMenuItem.add(aniadirProducto);

		JMenuItem modificarProducto = new JMenuItem("Modificar");
		modificarProducto.setForeground(negro);
		modificarProducto.setBackground(blanco);
		ProductoMenuItem.add(modificarProducto);

		JMenuItem eliminarProducto = new JMenuItem("Eliminar");
		eliminarProducto.setForeground(negro);
		eliminarProducto.setBackground(blanco);
		ProductoMenuItem.add(eliminarProducto);

		JMenu FacturaMenuItem = new JMenu("Facturas");
		FacturaMenuItem.setBackground(blanco);
		FacturaMenuItem.setForeground(negro);
		menuBar.add(FacturaMenuItem);

		JMenu BackupMenuItem = new JMenu("Backup");
		BackupMenuItem.setBackground(blanco);
		BackupMenuItem.setForeground(negro);
		menuBar.add(BackupMenuItem);

		JMenuItem crearBackup = new JMenuItem("Crear");
		crearBackup.setForeground(negro);
		crearBackup.setBackground(blanco);
		BackupMenuItem.add(crearBackup);

		JMenuItem restaurarBackup = new JMenuItem("Restaurar");
		restaurarBackup.setForeground(negro);
		restaurarBackup.setBackground(blanco);
		BackupMenuItem.add(restaurarBackup);

		JMenu AyudaMenuItem = new JMenu("Ayuda");
		AyudaMenuItem.setBackground(blanco);
		AyudaMenuItem.setForeground(negro);
		menuBar.add(AyudaMenuItem);

		JButton botonVenta = new JButton("Venta");
		botonVenta.setFont(new Font("Tahoma", Font.PLAIN, 22));
		botonVenta.setBounds(263, 464, 306, 123);
		botonVenta.setForeground(new Color(255, 255, 255));
		botonVenta.setBackground(new Color(30, 177, 0));
		botonVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("jsfdahsjkfdhsa");
			}
		});
		frame.getContentPane().add(botonVenta);

		JButton botonCatalogo = new JButton("Catalogo");
		botonCatalogo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		botonCatalogo.setBounds(1064, 464, 306, 123);
		botonCatalogo.setForeground(new Color(255, 255, 255));
		botonCatalogo.setBackground(new Color(0, 148, 177));
		botonCatalogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("sa");
			}
		});
		frame.getContentPane().add(botonCatalogo);
	}

}
