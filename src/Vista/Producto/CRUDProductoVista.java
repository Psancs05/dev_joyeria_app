package Vista.Producto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import LogicaNegocio.ProductoControlador;
import LogicaNegocio.ProveedorControlador;
import Vista.Catalogo.CatalogoVista;
import globals.enums.TipoCatalogo;
import globals.enums.TipoMaterial;
import globals.enums.TipoProducto;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;

public class CRUDProductoVista extends JDialog {

	private ProductoControlador controladorProducto;
	private ProveedorControlador controladorProveedor;
	private ArrayList<ProveedorVO> proveedores;

	public CRUDProductoVista(ProductoControlador controladorProducto) {
		this.controladorProducto = controladorProducto;
		this.controladorProveedor = ProveedorControlador.getInstance();
		this.proveedores = controladorProveedor.getProveedores();
	}

	JTextField tfNombre;
	JTextField tfPrecio;
	String imagenPath;
	JTextField tfDescripcion;
	JComboBox<String> comboBoxProveedor;
	JComboBox<String> comboBoxTipoProducto;
	JComboBox<String> comboBoxMaterialProducto;
	JTextField tfNumC;

	public void pulsarBotonAniadir() {

		Color gris = new Color(223, 223, 223);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 720, 559);
		setLocationRelativeTo(null);
		setTitle("Aniadir Producto");
		getContentPane().setLayout(null);

		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbNombre.setBounds(10, 11, 113, 33);
		getContentPane().add(lbNombre);

		tfNombre = new JTextField();
		tfNombre.setBounds(316, 11, 378, 38);
		getContentPane().add(tfNombre);
		tfNombre.setBounds(316, 16, 131, 38);

		JLabel lbNumC = new JLabel("Numero de Cuaderno");
		lbNumC.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbNumC.setBounds(10, 65, 176, 33);
		getContentPane().add(lbNumC);

		tfNumC = new JTextField();
		tfNumC.setBounds(316, 65, 131, 38);
		getContentPane().add(tfNumC);

		JLabel lbPrecio = new JLabel("Precio");
		lbPrecio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbPrecio.setBounds(10, 119, 77, 33);
		getContentPane().add(lbPrecio);

		tfPrecio = new JTextField();
		tfPrecio.setBounds(316, 114, 165, 38);
		getContentPane().add(tfPrecio);
		tfPrecio.setBounds(316, 114, 131, 38);

		JLabel lbImagen = new JLabel("Imagen");
		lbImagen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbImagen.setBounds(10, 168, 77, 33);
		getContentPane().add(lbImagen);

		JLabel lbProveedor = new JLabel("Proveedor");
		lbProveedor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbProveedor.setBounds(10, 217, 77, 33);
		getContentPane().add(lbProveedor);

		comboBoxProveedor = new JComboBox<String>();
		comboBoxProveedor.setBounds(316, 212, 378, 38);
		comboBoxProveedor.setToolTipText("Selecciona\r\n");
		comboBoxProveedor.addItem("");
		for (ProveedorVO proveedor : this.proveedores) {
			comboBoxProveedor.addItem(proveedor.getNombre());

		}
		getContentPane().add(comboBoxProveedor);
		// comboBoxProveedor.setColumns(10);

		JLabel lbDescripcion = new JLabel("Descripcion");
		lbDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbDescripcion.setBounds(10, 359, 107, 33);
		getContentPane().add(lbDescripcion);

		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(316, 359, 378, 93);
		getContentPane().add(tfDescripcion);
		tfDescripcion.setColumns(10);

		JButton btnSeleccionIm = new JButton("Selecciona una imagen");
		btnSeleccionIm.setBounds(316, 163, 154, 38);
		getContentPane().add(btnSeleccionIm);
		btnSeleccionIm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser selectorImagen = new JFileChooser();
				selectorImagen.showDialog(null, "Selecciona una imagen");
				selectorImagen.setCurrentDirectory(new File("."));
				imagenPath = selectorImagen.getSelectedFile().getAbsolutePath();

			}
		});

		JLabel lbTipoProducto = new JLabel("Tipo de Producto");
		lbTipoProducto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTipoProducto.setBounds(10, 264, 200, 35);
		getContentPane().add(lbTipoProducto);

		JButton okButton = new JButton("Aniadir");
		okButton.setActionCommand("OK");
		getContentPane().add(okButton);
		okButton.setBounds(506, 485, 89, 23);
		okButton.setBackground(gris);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearProducto();
				limpiarCampos();
				setVisible(false);

			}
		});

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		cancelButton.setBounds(605, 485, 89, 23);
		cancelButton.setBackground(gris);
		getContentPane().add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		comboBoxTipoProducto = new JComboBox<String>();
		comboBoxTipoProducto.setToolTipText("Selecciona\r\n");
		comboBoxTipoProducto.setBounds(316, 261, 378, 38);
		getContentPane().add(comboBoxTipoProducto);
		comboBoxTipoProducto.addItem(" ");
		comboBoxTipoProducto.addItem("Pendiente");
		comboBoxTipoProducto.addItem("Anillo");
		comboBoxTipoProducto.addItem("Pulsera");
		comboBoxTipoProducto.addItem("Colgante");
		comboBoxTipoProducto.addItem("Gafas");

		JLabel lbTipoMaterial = new JLabel("Tipo de Material");
		lbTipoMaterial.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTipoMaterial.setBounds(10, 313, 200, 35);
		getContentPane().add(lbTipoMaterial);

		comboBoxMaterialProducto = new JComboBox<String>();
		comboBoxMaterialProducto.setToolTipText("Selecciona\r\n");
		comboBoxMaterialProducto.setBounds(316, 310, 378, 38);
		getContentPane().add(comboBoxMaterialProducto);
		comboBoxMaterialProducto.addItem(" ");
		comboBoxMaterialProducto.addItem("Oro");
		comboBoxMaterialProducto.addItem("Plata");
		comboBoxMaterialProducto.addItem("Oro + Plata");
	}

	public void pulsarBotonModificar(ProductoVO producto) {
		JDialog dialog = new JDialog();

		Color gris = new Color(223, 223, 223);

		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setVisible(true);
		dialog.setBounds(100, 100, 720, 524);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("Modificar Producto");
		dialog.getContentPane().setLayout(null);

		JLabel lbID = new JLabel("Num cuaderno");
		lbID.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbID.setBounds(10, 34, 113, 33);
		dialog.getContentPane().add(lbID);

		tfNumC = new JTextField(producto.getNumCuaderno() + "");
		tfNumC.setBounds(316, 34, 378, 38);
		dialog.getContentPane().add(tfNumC);
		tfNumC.setBounds(316, 34, 131, 38);

		JLabel lbPrecio = new JLabel("Precio");
		lbPrecio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbPrecio.setBounds(10, 88, 77, 33);
		dialog.getContentPane().add(lbPrecio);

		tfPrecio = new JTextField(producto.getPrecio() + "");
		tfPrecio.setBounds(316, 83, 165, 38);
		dialog.getContentPane().add(tfPrecio);
		tfPrecio.setBounds(316, 83, 131, 38);

		JLabel lbImagen = new JLabel("Imagen");
		lbImagen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbImagen.setBounds(10, 137, 77, 33);
		dialog.getContentPane().add(lbImagen);

		JLabel lbProveedor = new JLabel("Proveedor");
		lbProveedor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbProveedor.setBounds(10, 186, 77, 33);
		dialog.getContentPane().add(lbProveedor);

		comboBoxProveedor = new JComboBox<String>();
		comboBoxProveedor.setSelectedItem(producto.getProveedor());
		comboBoxProveedor.setToolTipText("Selecciona\r\n");
		comboBoxProveedor.setBounds(316, 181, 378, 38);
		dialog.getContentPane().add(comboBoxProveedor);
		ArrayList<ProveedorVO> proveedores = this.controladorProveedor.getProveedores();
		for (ProveedorVO proveedor : proveedores) {
			comboBoxProveedor.addItem(proveedor.getNombre());
		}

		JLabel lbDescripcion = new JLabel("Descripcion");
		lbDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbDescripcion.setBounds(10, 328, 107, 33);
		dialog.getContentPane().add(lbDescripcion);

		tfDescripcion = new JTextField(producto.getDescripcion());
		tfDescripcion.setBounds(316, 328, 378, 93);
		dialog.getContentPane().add(tfDescripcion);
		tfDescripcion.setColumns(10);

		JButton btnSeleccionIm = new JButton("Selecciona una imagen");
		btnSeleccionIm.setBounds(316, 132, 154, 38);
		dialog.getContentPane().add(btnSeleccionIm);
		btnSeleccionIm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser selectorImagen = new JFileChooser();
				selectorImagen.showDialog(null, "Selecciona una imagen");
				selectorImagen.setCurrentDirectory(new File("."));
				imagenPath = selectorImagen.getSelectedFile().getAbsolutePath();
			}
		});

		JLabel lbTipoProducto = new JLabel("Tipo de Producto");
		lbTipoProducto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTipoProducto.setBounds(10, 233, 200, 35);
		dialog.getContentPane().add(lbTipoProducto);

		comboBoxTipoProducto = new JComboBox<String>();
		comboBoxTipoProducto.setSelectedItem(producto.getTipoProducto());
		comboBoxTipoProducto.setToolTipText("Selecciona\r\n");
		comboBoxTipoProducto.setBounds(316, 230, 378, 38);
		dialog.getContentPane().add(comboBoxTipoProducto);
		comboBoxTipoProducto.addItem(" ");
		comboBoxTipoProducto.addItem("Pendiente");
		comboBoxTipoProducto.addItem("Anillo");
		comboBoxTipoProducto.addItem("Pulsera");
		comboBoxTipoProducto.addItem("Colgante");
		comboBoxTipoProducto.addItem("Gafas");

		JLabel lbTipoMaterial = new JLabel("Tipo de Material");
		lbTipoMaterial.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTipoMaterial.setBounds(10, 282, 200, 35);
		dialog.getContentPane().add(lbTipoMaterial);
		comboBoxMaterialProducto = new JComboBox<String>();
		comboBoxMaterialProducto.setSelectedItem(producto.getMaterial());
		comboBoxMaterialProducto.setToolTipText("Selecciona\r\n");

		comboBoxMaterialProducto.setBounds(316, 279, 378, 38);
		dialog.getContentPane().add(comboBoxMaterialProducto);
		comboBoxMaterialProducto.addItem(" ");
		comboBoxMaterialProducto.addItem("Oro");
		comboBoxMaterialProducto.addItem("Plata");
		comboBoxMaterialProducto.addItem("Oro + Plata");

		JButton okButton = new JButton("Modificar");
		okButton.setActionCommand("OK");
		dialog.getContentPane().add(okButton);
		okButton.setBounds(506, 454, 89, 23);
		okButton.setBackground(gris);
		dialog.getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// limpiarCampos();
				modificarProducto(producto);
				dialog.setVisible(false);
			}
		});

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		cancelButton.setBounds(605, 454, 89, 23);
		cancelButton.setBackground(gris);
		dialog.getContentPane().add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.setVisible(false);
			}
		});

	}

	public void pulsarBotonEliminar(ProductoVO producto) {
		int result = JOptionPane.showConfirmDialog(null,
				"Quieres eliminar el producto de forma definitiva ?", "Confirmar eliminar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (result == 0) {
			controladorProducto.eliminarProducto(producto);
		}
	}

	public void mostrarListaProductos(boolean seleccionar) {
		if (seleccionar == false) {
			new CatalogoVista(TipoCatalogo.ELIMINAR);
		} else {
			new CatalogoVista(TipoCatalogo.MODIFICAR);
		}

	}

	public void crearProducto() {
		String nombre = tfNombre.getText();
		int numCuaderno;
		double precio;
		String imagen = imagenPath;
		String descripcion = tfDescripcion.getText();
		String proveedor = comboBoxProveedor.getSelectedItem().toString();

		Object comboTipo = comboBoxTipoProducto.getSelectedItem();
		Object comboMaterial = comboBoxMaterialProducto.getSelectedItem();

		TipoProducto tipoProducto;
		TipoMaterial tipoMaterial;

		if (comboTipo.toString() == "Anillo") {
			tipoProducto = TipoProducto.ANILLO;
		} else if (comboTipo.toString() == "Colgante") {
			tipoProducto = TipoProducto.COLGANTE;
		} else if (comboTipo.toString() == "Gafas") {
			tipoProducto = TipoProducto.GAFAS;
		} else if (comboTipo.toString() == "Pendiente") {
			tipoProducto = TipoProducto.PENDIENTE;
		} else {
			tipoProducto = TipoProducto.PULSERA;
		}

		if (comboMaterial.toString() == "Oro") {
			tipoMaterial = TipoMaterial.ORO;
		} else if (comboMaterial.toString() == "Plata") {
			tipoMaterial = TipoMaterial.PLATA;
		} else {
			tipoMaterial = TipoMaterial.OROPLATA;
		}

		if (nombre.equals("") || tfNumC.getText().equals("") || tfPrecio.getText().equals("") || imagen == null
				|| descripcion.equals("") || proveedor.equals("") || comboTipo.toString().equals(" ")
				|| comboMaterial.toString().equals(" ")) {
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, "Debe rellenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				precio = Double.parseDouble(tfPrecio.getText());
				numCuaderno = Integer.parseInt(tfNumC.getText());
				controladorProducto.aniadirProducto(nombre, numCuaderno, tipoProducto, proveedor, tipoMaterial, precio,
						imagen,
						descripcion);
			} catch (Exception e) {
				JFrame error = new JFrame();
				JOptionPane.showMessageDialog(error, "El precio y el numero de cuaderno deben ser un numero valido.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		limpiarCampos();
	}

	public void modificarProducto(ProductoVO productoAntiguo) {
		String nombre = productoAntiguo.getNombre();
		int numCuaderno;
		double precio;
		String imagen = imagenPath;
		String descripcion = tfDescripcion.getText();
		String proveedor = comboBoxProveedor.getSelectedItem().toString();

		Object comboTipo = comboBoxTipoProducto.getSelectedItem();
		Object comboMaterial = comboBoxTipoProducto.getSelectedItem();

		TipoProducto tipoProducto;
		TipoMaterial tipoMaterial;

		if (comboTipo.toString() == "Anillo") {
			tipoProducto = TipoProducto.ANILLO;
		} else if (comboTipo.toString() == "Colgante") {
			tipoProducto = TipoProducto.COLGANTE;
		} else if (comboTipo.toString() == "Gafas") {
			tipoProducto = TipoProducto.GAFAS;
		} else if (comboTipo.toString() == "Pendiente") {
			tipoProducto = TipoProducto.PENDIENTE;
		} else {
			tipoProducto = TipoProducto.PULSERA;
		}

		if (comboMaterial.toString() == "Oro") {
			tipoMaterial = TipoMaterial.ORO;
		} else if (comboMaterial.toString() == "Plata") {
			tipoMaterial = TipoMaterial.PLATA;
		} else {
			tipoMaterial = TipoMaterial.OROPLATA;
		}

		if (nombre.equals("") || tfNumC.getText().equals("") || tfPrecio.getText().equals("") || imagen == null
				|| descripcion.equals("") || proveedor.equals("") || comboTipo.toString().equals("")
				|| comboMaterial.toString().equals("")) {
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, "Debe rellenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				precio = Double.parseDouble(tfPrecio.getText());
				numCuaderno = Integer.parseInt(tfNumC.getText());
				controladorProducto.modificarProducto(productoAntiguo, nombre, numCuaderno, tipoProducto, proveedor,
						tipoMaterial, precio,
						imagen,
						descripcion);
			} catch (Exception e) {
				JFrame error = new JFrame();
				JOptionPane.showMessageDialog(error, "El precio y el numero de cuaderno deben ser un numero valido.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void limpiarCampos() {
		tfNombre.setText("");
		tfNumC.setText("");
		tfPrecio.setText("");
		tfDescripcion.setText("");
		comboBoxTipoProducto.setSelectedItem(0);
		comboBoxMaterialProducto.setSelectedItem(0);
		comboBoxProveedor.setSelectedItem(0);
	}

}
