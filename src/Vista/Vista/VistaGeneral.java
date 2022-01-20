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
		frame.setResizable(false);
		this.frame.setVisible(true);
		Color blanco = new Color(255, 255, 255);
		Color negro = new Color(0, 0, 0);
		frame.getContentPane().setBackground(blanco);
		frame.setBackground(blanco);
		frame.setBounds(100, 100, 1600, 900);
		frame.setLocationRelativeTo(null);
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
				new CatalogoVista(TipoCatalogo.MODIFICAR);
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

		JMenuItem ayudaUsuario = new JMenuItem("Usuario");
		ayudaUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		ayudaUsuario.setForeground(negro);
		ayudaUsuario.setBackground(blanco);
		ayudaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ayuda usuario
				String[] botones = { "Ayuda añadir", "Ayuda modificar", "Ayuda eliminar" };
				int ventana = JOptionPane.showOptionDialog(null,
						"<html><body style='text-align: center'>En este menú se encuentran las opciones para <br/>gestionar los usuarios de la aplicación.</html>",
						"Ayuda proveedor",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null,
						botones, botones[0]);
				if (ventana == 0) {
					JOptionPane.showMessageDialog(
							null,
							"<html><body style='text-align: center'>Despliega una ventana con un formulario a rellenar <br/>con la información necesaria para añadir un usuario en la base de datos.</html>",
							"Ayuda añadir",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (ventana == 1) {
					JOptionPane.showMessageDialog(
							null,
							"<html><body style='text-align: center'>Despliega una lista con los usuarios resgitrados <br/>y permite seleccionar uno para modificar sus atributos.</html>",
							"Ayuda modificar",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (ventana == 2) {
					JOptionPane.showMessageDialog(
							null,
							"<html><body style='text-align: center'>Despliega una ventana para eliminar usuarios.</html>",
							"Ayuda eliminar",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		AyudaMenuItem.add(ayudaUsuario);

		JMenuItem ayudaProveedor = new JMenuItem("Proveedor");
		ayudaProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		ayudaProveedor.setForeground(negro);
		ayudaProveedor.setBackground(blanco);
		ayudaProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ayuda proveedor
				String[] botones = { "Ayuda añadir", "Ayuda modificar", "Ayuda eliminar" };
				int ventana = JOptionPane.showOptionDialog(null,
						"<html><body style='text-align: center'>En este menú se encuentran las opciones para <br/>gestionar los proveedores disponibles para los productos</html>",
						"Ayuda proveedor",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null,
						botones, botones[0]);
				if (ventana == 0) {
					JOptionPane.showMessageDialog(
							null,
							"<html><body style='text-align: center'>Despliega una ventana con un formulario a rellenar con la <br/>información necesaria para registrar a un proveedor en una base de datos.</html>",
							"Ayuda añadir",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (ventana == 1) {
					JOptionPane.showMessageDialog(
							null,
							"<html><body style='text-align: center'>Despliega una lista con los proveedores registrados <br/>y permite seleccionar uno para modificar sus atributos.</html>",
							"Ayuda modificar",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (ventana == 2) {
					JOptionPane.showMessageDialog(
							null,
							"<html><body style='text-align: center'>Despliega una ventana para eliminar proveedores.</html>",
							"Ayuda eliminar",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		AyudaMenuItem.add(ayudaProveedor);

		JMenuItem ayudaProducto = new JMenuItem("Producto");
		ayudaProducto.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		ayudaProducto.setForeground(negro);
		ayudaProducto.setBackground(blanco);
		ayudaProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ayuda producto
				String[] botones = { "Ayuda aniadir", "Ayuda modificar", "Ayuda eliminar" };
				int ventana = JOptionPane.showOptionDialog(null,
						"<html><body style='text-align: center'>En este menu se encuentran las opciones necesarias <br/>para gestionar los productos de la base de datos.</html>",
						"Ayuda proveedor",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null,
						botones, botones[0]);
				if (ventana == 0) {
					JOptionPane.showMessageDialog(
							null,
							"<html><body style='text-align: center'>Despliega una ventana con un formulario a rellenar con la informacion <br/>necesaria para registrar un producto en la base de datos.</html>",
							"Ayuda añadir",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (ventana == 1) {
					JOptionPane.showMessageDialog(
							null,
							"<html><body style='text-align: center'>Despliega una lista con los productos registrados y <br/>permite seleccionar uno para modificar sus atributos<br/>Para seleccionar un producto se ha de hacer ctrl+click. </html>",
							"Ayuda modificar",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (ventana == 2) {
					JOptionPane.showMessageDialog(
							null,
							"<html><body style='text-align: center'>Despliega una lista con los productos <br/>registrados para seleccionar uno y eliminarlo<br/>Para seleccionar un producto se ha de hacer ctrl+click. </html>",
							"Ayuda eliminar",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		AyudaMenuItem.add(ayudaProducto);

		JMenuItem ayudaFacturas = new JMenuItem("Facturas");
		ayudaFacturas.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		ayudaFacturas.setForeground(negro);
		ayudaFacturas.setBackground(blanco);
		ayudaFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ayuda facturas
				JOptionPane.showMessageDialog(
						null,
						"<html><body style='text-align: center'>En este menu se ecuentra la opcion de visualizar el listado de facturas. <br/>La opción \"listado\" muestra todas las facturas y permite selecionar cada una para mostrar más información.<br/>Para guerdar un pdf con una factura hay que indicar el nombre del archivo y la ubicacion</html>",
						"Ayuda factura",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		AyudaMenuItem.add(ayudaFacturas);

		JMenuItem ayudaBackup = new JMenuItem("Backup");
		ayudaBackup.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		ayudaBackup.setForeground(negro);
		ayudaBackup.setBackground(blanco);
		ayudaBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ayuda backup
				String[] botones = { "Ayuda crear backup", "Ayuda restaurar backup" };
				int ventana = JOptionPane.showOptionDialog(null,
						"<html><body style='text-align: center'>En este menú se encuentran las opciones necesarias para crear y restaurar <br/>copias de seguridad de la base de datos de la tienda.</html>",
						"Ayuda backup",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null,
						botones, botones[0]);
				if (ventana == 0) {
					JOptionPane.showMessageDialog(
							null,
							"<html><body style='text-align: center'>Elegir dónde guardar una copia de seguridad de la base de datos <br/>de la tienda y realizar la copia de seguridad en esa ubicación.</html>",
							"Ayuda crear backup",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (ventana == 1) {
					JOptionPane.showMessageDialog(
							null,
							"<html><body style='text-align: center'>Elegir donde se encuentra la copia de seguridad <br/>del sistema para restaurar la base de datos a ese estado.</html>",
							"Ayuda restaurar backup",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		AyudaMenuItem.add(ayudaBackup);

		JMenuItem ayudaCerrarSesion = new JMenuItem("Cerrar sesión");
		ayudaCerrarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		ayudaCerrarSesion.setForeground(negro);
		ayudaCerrarSesion.setBackground(blanco);
		ayudaCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ayuda cerrar sesion
				JOptionPane.showMessageDialog(
						null,
						"Menú destinado a cerrar la sesión del usuario.",
						"Ayuda cerrar sesión",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		AyudaMenuItem.add(ayudaCerrarSesion);

		JMenuItem ayudaVenta = new JMenuItem("Venta");
		ayudaVenta.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		ayudaVenta.setForeground(negro);
		ayudaVenta.setBackground(blanco);
		ayudaVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ayuda venta
				JOptionPane.showMessageDialog(
						null,
						"<html><body style='text-align: center'>Esta opción despliega el catálogo de productos y permite <br/>seleccionar uno o más productos para realizar una venta. <br/>Para seleccionar uno o varios productos se ha de hacer ctrl+click. <br/>Se mostrará una previsualización de la venta con los productos seleccionados y el precio total</html>",
						"Ayuda venta",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		AyudaMenuItem.add(ayudaVenta);

		JMenuItem ayudaCatalogo = new JMenuItem("Catálogo");
		ayudaCatalogo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		ayudaCatalogo.setForeground(negro);
		ayudaCatalogo.setBackground(blanco);
		ayudaCatalogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ayuda catalogo
				JOptionPane.showMessageDialog(
						null,
						new JLabel(
								"<html><body style='text-align: center'>Esta opción despliega el catálogo de productos y permite <br/>selecionar un producto para visualizar su especificación. <br/>Para seleccionar uno o varios productos se ha de hacer ctrl+click. <br/>Además en la parte inferior se encuentra la opción de filtrar catálogo que permite <br/>introducir una serie de parametros para mostrar los productos que coincidan con ellos.</html>",
								JLabel.CENTER),
						"Ayuda catálogo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		AyudaMenuItem.add(ayudaCatalogo);

		// etc

		menuBar.add(AyudaMenuItem);

		JButton botonVenta = new JButton("Venta");
		botonVenta.setFont(new Font("Tahoma", Font.BOLD, 22));
		botonVenta.setBounds(229, 465, 306, 123);
		botonVenta.setForeground(new Color(255, 255, 255));
		botonVenta.setBackground(new Color(30, 177, 0));
		botonVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Abrir venta seleccionado");
				new CatalogoVista(TipoCatalogo.VENDER);
				// frame.setVisible(false);
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
				new CatalogoVista(TipoCatalogo.FILTRAR);
				// frame.setVisible(false);
			}
		});
		frame.getContentPane().add(botonCatalogo);
	}

}