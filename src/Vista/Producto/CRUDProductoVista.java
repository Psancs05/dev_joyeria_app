package Vista.Producto;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
		System.out.println("Creando CRUDProductoVista");
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

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 720, 559);
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
		comboBoxProveedor.addItem("default");
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
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Aniadir boton pulsado");
				crearProducto();
				limpiarCampos();
				setVisible(false);

			}
		});

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		cancelButton.setBounds(605, 485, 89, 23);
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

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 720, 524);
		setTitle("Modificar Producto");
		getContentPane().setLayout(null);

		JTextField tfID;
		JTextField tfPrecio;
		JTextField tfProveedor;
		JTextField tfDescripcion;

		JLabel lbID = new JLabel("ID");
		lbID.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbID.setBounds(10, 34, 113, 33);
		getContentPane().add(lbID);

		tfID = new JTextField();
		tfID.setBounds(316, 34, 378, 38);
		getContentPane().add(tfID);
		tfID.setBounds(316, 34, 131, 38);

		JLabel lbPrecio = new JLabel("Precio");
		lbPrecio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbPrecio.setBounds(10, 88, 77, 33);
		getContentPane().add(lbPrecio);

		tfPrecio = new JTextField();
		tfPrecio.setBounds(316, 83, 165, 38);
		getContentPane().add(tfPrecio);
		tfPrecio.setBounds(316, 83, 131, 38);

		JLabel lbImagen = new JLabel("Imagen");
		lbImagen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbImagen.setBounds(10, 137, 77, 33);
		getContentPane().add(lbImagen);

		JLabel lbProveedor = new JLabel("Proveedor");
		lbProveedor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbProveedor.setBounds(10, 186, 77, 33);
		getContentPane().add(lbProveedor);

		tfProveedor = new JTextField();
		tfProveedor.setBounds(316, 181, 378, 38);
		getContentPane().add(tfProveedor);
		tfProveedor.setColumns(10);

		JLabel lbDescripcion = new JLabel("Descripcion");
		lbDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbDescripcion.setBounds(10, 328, 107, 33);
		getContentPane().add(lbDescripcion);

		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(316, 328, 378, 93);
		getContentPane().add(tfDescripcion);
		tfDescripcion.setColumns(10);

		JButton btnSeleccionIm = new JButton("Selecciona una imagen");
		btnSeleccionIm.setBounds(316, 132, 154, 38);
		getContentPane().add(btnSeleccionIm);
		btnSeleccionIm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser selectorImagen = new JFileChooser();
				selectorImagen.showDialog(null, "Selecciona una imagen");
				selectorImagen.setCurrentDirectory(new File("."));
			}
		});

		JLabel lbTipoProducto = new JLabel("Tipo de Producto");
		lbTipoProducto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTipoProducto.setBounds(10, 233, 200, 35);
		getContentPane().add(lbTipoProducto);

		comboBoxTipoProducto = new JComboBox<String>();
		comboBoxTipoProducto.setToolTipText("Selecciona\r\n");
		comboBoxTipoProducto.setBounds(316, 230, 378, 38);
		getContentPane().add(comboBoxTipoProducto);
		comboBoxTipoProducto.addItem(" ");
		comboBoxTipoProducto.addItem("Pendiente");
		comboBoxTipoProducto.addItem("Anillo");
		comboBoxTipoProducto.addItem("Pulsera");
		comboBoxTipoProducto.addItem("Colgante");
		comboBoxTipoProducto.addItem("Gafas");

		JLabel lbTipoMaterial = new JLabel("Tipo de Material");
		lbTipoMaterial.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTipoMaterial.setBounds(10, 282, 200, 35);
		getContentPane().add(lbTipoMaterial);

		comboBoxMaterialProducto = new JComboBox<String>();
		comboBoxMaterialProducto.setToolTipText("Selecciona\r\n");
		comboBoxMaterialProducto.setBounds(316, 279, 378, 38);
		getContentPane().add(comboBoxMaterialProducto);
		comboBoxMaterialProducto.addItem(" ");
		comboBoxMaterialProducto.addItem("Oro");
		comboBoxMaterialProducto.addItem("Plata");
		comboBoxMaterialProducto.addItem("Oro + Plata");

		JButton okButton = new JButton("Modificar");
		okButton.setActionCommand("OK");
		getContentPane().add(okButton);
		okButton.setBounds(506, 454, 89, 23);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Modificar pulsado");
				limpiarCampos();
				modificarProducto(producto);
				setVisible(false);
			}
		});

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		cancelButton.setBounds(605, 454, 89, 23);
		getContentPane().add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

	}

	public void pulsarBotonEliminar(ProductoVO producto) {
		JFrame adv = new JFrame();
		int result = JOptionPane.showConfirmDialog(null,
				"Quieres eliminar el producto de forma definitiva ?", "Confirmar eliminar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (result == 0) {
			controladorProducto.eliminarProducto(producto);
			System.out.println("Se ha eliminado el Producto " + producto.toString());
		}
	}

	public void mostrarListaProductos(boolean seleccionar) {
		System.out.println(seleccionar);
		CatalogoVista catalogo = new CatalogoVista(TipoCatalogo.ELIMINAR);
		JList<ProductoVO> list = catalogo.getJList();
		ArrayList<ProductoVO> listaDeProductos = catalogo.getListaProductos();

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) {
					int index = theList.locationToIndex(mouseEvent.getPoint());
					// if(!seleccionar){
					// 	pulsarBotonEliminar(listaDeProductos.get(index));
					// 	listaDeProductos.remove(index);
					// } else {
					// 	pulsarBotonModificar(listaDeProductos.get(index));
					// }
					pulsarBotonModificar(listaDeProductos.get(index));
				}
			}
		};
		list.addMouseListener(mouseListener);
	}

	public void crearProducto() {
		String nombre = tfNombre.getText();
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

		System.out.println(comboTipo.toString() + comboMaterial.toString());
		if (nombre.equals("") || tfNumC.getText().equals("") || tfPrecio.getText().equals("") || imagen == null
				|| descripcion.equals("") || proveedor.equals("default") || comboTipo.toString().equals(" ")
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

		// TODO: Comprobar que los valores sean correctos (no campos vacios etc) y
		// gestionar errores

		limpiarCampos();
	}

	public void modificarProducto(ProductoVO productoAntiguo) {
		String nombre = tfNombre.getText();
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

		System.out.println(comboTipo.toString() + comboMaterial.toString());
		if (nombre.equals("") || tfNumC.getText().equals("") || tfPrecio.getText().equals("") || imagen == null
				|| descripcion.equals("") || proveedor.equals("default") || comboTipo.toString().equals(" ")
				|| comboMaterial.toString().equals(" ")) {
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, "Debe rellenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				precio = Double.parseDouble(tfPrecio.getText());
				numCuaderno = Integer.parseInt(tfNumC.getText());
				controladorProducto.modificarProducto(productoAntiguo, nombre, numCuaderno, tipoProducto, proveedor, tipoMaterial, precio,
						imagen,
						descripcion);
			} catch (Exception e) {
				JFrame error = new JFrame();
				JOptionPane.showMessageDialog(error, "El precio y el numero de cuaderno deben ser un numero valido.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		// TODO: Comprobar que los valores sean correctos (no campos vacios etc) y
		// gestionar errores

		limpiarCampos();
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

	// TODO?: Necesitamos getters y setters en las vistas??
	public ProductoControlador getControladorProducto() {
		return controladorProducto;
	}

	public void setControladorProducto(ProductoControlador controladorProducto) {
		this.controladorProducto = controladorProducto;
	}

}
