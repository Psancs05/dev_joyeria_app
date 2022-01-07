package Vista.Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import LogicaNegocio.ProveedorControlador;
import LogicaNegocio.UsuarioControlador;

import javax.swing.ImageIcon;
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
		frame.setTitle("Claudio Paniagua Joyas");


		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("assets\\logo.jpg"));
		lblNewLabel.setBounds(605, 135, 387, 231);
		frame.getContentPane().add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1584, 21);
		menuBar.setForeground(negro);
		menuBar.setBackground(blanco);
		frame.setJMenuBar(menuBar);

		JMenu UsuarioMenuItem = new JMenu("    Usuario    ");
		UsuarioMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 17));
		UsuarioMenuItem.setBackground(blanco);
		UsuarioMenuItem.setForeground(negro);
		menuBar.add(UsuarioMenuItem);

		JMenuItem aniadirUsuario = new JMenuItem("Aniadir");
		aniadirUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		aniadirUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioControlador.getInstance().mostrarAniadir();
			}
		});
		aniadirUsuario.setBackground(blanco);
		aniadirUsuario.setForeground(negro);
		UsuarioMenuItem.add(aniadirUsuario);

		JMenuItem modificarUsuario = new JMenuItem("Modificar");
		modificarUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		modificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioControlador.getInstance().mostrarModificar();
			}
		});
		modificarUsuario.setForeground(negro);
		modificarUsuario.setBackground(blanco);
		UsuarioMenuItem.add(modificarUsuario);

		JMenuItem eliminarUsuario = new JMenuItem("Eliminar");
		eliminarUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		eliminarUsuario.setBackground(blanco);
		eliminarUsuario.setForeground(negro);
		UsuarioMenuItem.add(eliminarUsuario);

		JMenu ProveedorMenuItem = new JMenu("  Proveedor  ");
		ProveedorMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 17));
		ProveedorMenuItem.setBackground(blanco);
		ProveedorMenuItem.setForeground(negro);
		menuBar.add(ProveedorMenuItem);

		JMenuItem aniadirProveedor = new JMenuItem("Aniadir");
		aniadirProveedor.setForeground(negro);
		aniadirProveedor.setBackground(blanco);
		aniadirProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		aniadirProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProveedorControlador.getInstance().mostrarAniadir();
			}
		});
		ProveedorMenuItem.add(aniadirProveedor);

		JMenuItem modificarProveedor = new JMenuItem("Modificar");
		modificarProveedor.setForeground(negro);
		modificarProveedor.setBackground(blanco);
		modificarProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		modificarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProveedorControlador.getInstance().mostrarModificar();
			}
		});
		ProveedorMenuItem.add(modificarProveedor);

		JMenuItem eliminarProveedor = new JMenuItem("Eliminar");
		eliminarProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		eliminarProveedor.setForeground(negro);
		eliminarProveedor.setBackground(blanco);
		ProveedorMenuItem.add(eliminarProveedor);

		JMenu ProductoMenuItem = new JMenu("  Producto  ");
		ProductoMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 17));
		ProductoMenuItem.setBackground(blanco);
		ProductoMenuItem.setForeground(negro);
		menuBar.add(ProductoMenuItem);

		JMenuItem aniadirProducto = new JMenuItem("Aniadir");
		aniadirProducto.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		aniadirProducto.setForeground(negro);
		aniadirProducto.setBackground(blanco);
		ProductoMenuItem.add(aniadirProducto);

		JMenuItem modificarProducto = new JMenuItem("Modificar");
		modificarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		modificarProducto.setForeground(negro);
		modificarProducto.setBackground(blanco);
		ProductoMenuItem.add(modificarProducto);

		JMenuItem eliminarProducto = new JMenuItem("Eliminar");
		eliminarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		eliminarProducto.setForeground(negro);
		eliminarProducto.setBackground(blanco);
		ProductoMenuItem.add(eliminarProducto);

		JMenu FacturaMenuItem = new JMenu("  Facturas  ");
		FacturaMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 17));
		FacturaMenuItem.setBackground(blanco);
		FacturaMenuItem.setForeground(negro);
		menuBar.add(FacturaMenuItem);

		JMenu BackupMenuItem = new JMenu("  Backup  ");
		BackupMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 17));
		BackupMenuItem.setBackground(blanco);
		BackupMenuItem.setForeground(negro);
		menuBar.add(BackupMenuItem);

		JMenuItem crearBackup = new JMenuItem("Crear");
		crearBackup.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		crearBackup.setForeground(negro);
		crearBackup.setBackground(blanco);
		BackupMenuItem.add(crearBackup);

		JMenuItem restaurarBackup = new JMenuItem("Restaurar");
		restaurarBackup.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		restaurarBackup.setForeground(negro);
		restaurarBackup.setBackground(blanco);
		BackupMenuItem.add(restaurarBackup);

		JMenu AyudaMenuItem = new JMenu("  Ayuda  ");
		AyudaMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 17));
		AyudaMenuItem.setBackground(blanco);
		AyudaMenuItem.setForeground(negro);
		menuBar.add(AyudaMenuItem);

		JButton botonVenta = new JButton("Venta");
		botonVenta.setFont(new Font("Tahoma", Font.BOLD, 22));
		botonVenta.setBounds(229, 465, 306, 123);
		botonVenta.setForeground(new Color(255, 255, 255));
		botonVenta.setBackground(new Color(30, 177, 0));
		botonVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("jsfdahsjkfdhsa");
			}
		});
		frame.getContentPane().add(botonVenta);

		JButton botonCatalogo = new JButton("Catalogo");
		botonCatalogo.setFont(new Font("Tahoma", Font.BOLD, 22));
		botonCatalogo.setBounds(1030, 465, 306, 123);
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