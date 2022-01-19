package Vista.Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import LogicaNegocio.BackupControlador;
import LogicaNegocio.ProductoControlador;
import LogicaNegocio.ProveedorControlador;
import LogicaNegocio.UsuarioControlador;
import LogicaNegocio.VentaControlador;
import Vista.Catalogo.CatalogoVista;
import globals.enums.TipoCatalogo;
import globals.enums.TipoUsuario;

public class VistaGeneral {

	private JFrame frame;
	private UsuarioControlador controlador;
	private VentaControlador controladorVenta;

	/**
	 * Create the application.
	 */
	public VistaGeneral() {
		controlador = UsuarioControlador.getInstance();
		initialize();
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		this.frame.setVisible(true);
		Color blanco = new Color(255, 255, 255);
		Color negro = new Color(0, 0, 0);
		frame.getContentPane().setBackground(blanco);
		frame.setBackground(blanco);
		frame.setBounds(100, 100, 1600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Claudio Paniagua Joyas");

		JLabel lblNewLabel = new JLabel("New label");
		String fileSeparator = File.separator;
		lblNewLabel.setIcon(new ImageIcon("assets" + fileSeparator + "logo.jpg"));
		lblNewLabel.setBounds(605, 135, 387, 231);
		frame.getContentPane().add(lblNewLabel);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1584, 21);
		menuBar.setForeground(negro);
		menuBar.setBackground(blanco);
		frame.setJMenuBar(menuBar);

		JMenu UsuarioMenuItem = new JMenu("    Usuario    ");
		UsuarioMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 17));
		if (controlador.getUsuarioActual().getTipoUsuario() == TipoUsuario.CAJERO) {
			UsuarioMenuItem.setEnabled(false);
		}
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
		eliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioControlador.getInstance().mostrarEliminar();
			}
		});
		eliminarUsuario.setBackground(blanco);
		eliminarUsuario.setForeground(negro);
		UsuarioMenuItem.add(eliminarUsuario);

		JMenu ProveedorMenuItem = new JMenu("  Proveedor  ");
		ProveedorMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 17));
		if (controlador.getUsuarioActual().getTipoUsuario() == TipoUsuario.CAJERO) {
			ProveedorMenuItem.setEnabled(false);
		}
		ProveedorMenuItem.setBackground(blanco);
		ProveedorMenuItem.setForeground(negro);
		menuBar.add(ProveedorMenuItem);

		JMenuItem aniadirProveedor = new JMenuItem("Aniadir");
		aniadirProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		aniadirProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProveedorControlador.getInstance().mostrarAniadir();
			}
		});
		aniadirProveedor.setForeground(negro);
		aniadirProveedor.setBackground(blanco);
		ProveedorMenuItem.add(aniadirProveedor);

		JMenuItem modificarProveedor = new JMenuItem("Modificar");
		modificarProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		modificarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProveedorControlador.getInstance().mostrarModificar();
			}
		});
		modificarProveedor.setForeground(negro);
		modificarProveedor.setBackground(blanco);
		ProveedorMenuItem.add(modificarProveedor);

		JMenuItem eliminarProveedor = new JMenuItem("Eliminar");
		eliminarProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		eliminarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProveedorControlador.getInstance().mostrarEliminar();
			}
		});
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
		aniadirProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductoControlador.getInstance().mostrarAniadir();
			}
		});
		ProductoMenuItem.add(aniadirProducto);

		JMenuItem modificarProducto = new JMenuItem("Modificar");
		modificarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		modificarProducto.setForeground(negro);
		modificarProducto.setBackground(blanco);
		modificarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ProductoControlador.getInstance().mostrarModificar();
				// TODO
				CatalogoVista catalogo = new CatalogoVista(TipoCatalogo.MODIFICAR);
			}
		});
		ProductoMenuItem.add(modificarProducto);

		JMenuItem eliminarProducto = new JMenuItem("Eliminar");
		eliminarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		eliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductoControlador.getInstance().mostrarEliminar();
			}
		});
		ProductoMenuItem.add(modificarProducto);
		eliminarProducto.setForeground(negro);
		eliminarProducto.setBackground(blanco);
		ProductoMenuItem.add(eliminarProducto);

		JMenu FacturaMenuItem = new JMenu("  Facturas  ");
		FacturaMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 17));
		FacturaMenuItem.setBackground(blanco);
		FacturaMenuItem.setForeground(negro);
		menuBar.add(FacturaMenuItem);

		JMenuItem listado = new JMenuItem("Listado");
		listado.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		listado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentaControlador.getInstance().mostrarListado();
				frame.setVisible(false);
			}
		});
		listado.setForeground(negro);
		listado.setBackground(blanco);
		FacturaMenuItem.add(listado);

		JMenu BackupMenuItem = new JMenu("  Backup  ");
		BackupMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 17));
		if (controlador.getUsuarioActual().getTipoUsuario() == TipoUsuario.CAJERO) {
			BackupMenuItem.setEnabled(false);
		}
		BackupMenuItem.setBackground(blanco);
		BackupMenuItem.setForeground(negro);
		menuBar.add(BackupMenuItem);

		JMenuItem crearBackup = new JMenuItem("Crear");
		crearBackup.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		crearBackup.setForeground(negro);
		crearBackup.setBackground(blanco);
		BackupMenuItem.add(crearBackup);
		crearBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = fileChooser.showOpenDialog(frame);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					System.out.println("Seleccionado: " + file.getAbsolutePath());
					BackupControlador.getInstance().generarBackup(file.getAbsolutePath());
				} else {
					JOptionPane.showMessageDialog(null, "Error al seleccionar directorio", "Error al crear backup",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JMenuItem restaurarBackup = new JMenuItem("Restaurar");
		restaurarBackup.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		restaurarBackup.setForeground(negro);
		restaurarBackup.setBackground(blanco);
		BackupMenuItem.add(restaurarBackup);
		restaurarBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int option = fileChooser.showOpenDialog(frame);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					System.out.println("Seleccionado: " + file.getAbsolutePath());
					BackupControlador.getInstance().restaurarBackup(file.getAbsolutePath());
				} else {
					JOptionPane.showMessageDialog(null, "Error al seleccionar archivo", "Error al restaurar backup",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JMenu LogOutMenuItem = new JMenu("  Cerrar Sesion  ");
		LogOutMenuItem.setForeground(Color.BLACK);
		LogOutMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 17));
		LogOutMenuItem.setBackground(Color.WHITE);

		JMenuItem cerrarSesion = new JMenuItem("Salir");
		cerrarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		cerrarSesion.setForeground(negro);
		cerrarSesion.setBackground(blanco);
		cerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioControlador.getInstance().cerrarSesion();
				frame.setVisible(false);
				UsuarioControlador.getInstance().mostrarLogin();
			}
		});

		LogOutMenuItem.add(cerrarSesion);
		menuBar.add(LogOutMenuItem);

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
				System.out.println("Abrir venta seleccionado");
				CatalogoVista catalogoVista = new CatalogoVista(TipoCatalogo.VENDER);
				frame.setVisible(false);
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
				System.out.println("Boton Abrir Catalogo seleccionado");
				CatalogoVista catalogoVista = new CatalogoVista(TipoCatalogo.FILTRAR);
				// frame.setVisible(false);
			}
		});
		frame.getContentPane().add(botonCatalogo);
	}

}