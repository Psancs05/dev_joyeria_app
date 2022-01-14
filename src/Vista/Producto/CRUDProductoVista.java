package Vista.Producto;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import LogicaNegocio.ProductoControlador;
import LogicaNegocio.ProveedorControlador;
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

	public void pulsarBotonAniadir() {

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 720, 524);
		setTitle("Aniadir Producto");
		getContentPane().setLayout(null);

		JLabel lbID = new JLabel("ID");
		lbID.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbID.setBounds(10, 34, 113, 33);
		getContentPane().add(lbID);

		tfNombre = new JTextField();
		tfNombre.setBounds(316, 34, 378, 38);
		getContentPane().add(tfNombre);
		tfNombre.setBounds(316, 34, 131, 38);

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

		comboBoxProveedor = new JComboBox<String>();
		comboBoxProveedor.setBounds(316, 181, 378, 38);
		comboBoxProveedor.setToolTipText("Selecciona\r\n");
		comboBoxProveedor.addItem("default");
		for (ProveedorVO proveedor : this.proveedores) {
			comboBoxProveedor.addItem(proveedor.getNombre());

		}
		getContentPane().add(comboBoxProveedor);
		// comboBoxProveedor.setColumns(10);

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
				imagenPath = selectorImagen.getSelectedFile().getAbsolutePath();

			}
		});

		JLabel lbTipoProducto = new JLabel("Tipo de Producto");
		lbTipoProducto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTipoProducto.setBounds(10, 233, 200, 35);
		getContentPane().add(lbTipoProducto);

		JButton okButton = new JButton("Aniadir");
		okButton.setActionCommand("OK");
		getContentPane().add(okButton);
		okButton.setBounds(506, 454, 89, 23);
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
		cancelButton.setBounds(605, 454, 89, 23);
		getContentPane().add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		comboBoxTipoProducto = new JComboBox<String>();
		comboBoxTipoProducto.setToolTipText("Selecciona\r\n");
		comboBoxTipoProducto.setBounds(316, 230, 378, 38);
		getContentPane().add(comboBoxTipoProducto);
		comboBoxTipoProducto.addItem("default");
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
		comboBoxMaterialProducto.addItem("default");
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
		comboBoxTipoProducto.addItem("default");
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
		comboBoxMaterialProducto.addItem("default");
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
				System.out.println("Aniadir boton pulsado");
				limpiarCampos();
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

	public void pulsarBotonEliminar() {

	}

	public void crearProducto() {
		String nombre = tfNombre.getText();
		int numCuaderno = 1; // TODO: Hacer la interfaz de numCuaderno con eclipse
		double precio = Double.parseDouble(tfPrecio.getText());
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

		// TODO: Aniadir numero cuaderno

		// TODO: Comprobar que los valores sean correctos (no campos vacios etc) y
		// gestionar errores

		controladorProducto.aniadirProducto(nombre, numCuaderno, tipoProducto, proveedor, tipoMaterial, precio, imagen,
				descripcion);
		limpiarCampos();
	}

	public void limpiarCampos() {
		tfNombre.setText("");
		tfPrecio.setText("");
		comboBoxTipoProducto.removeAllItems();
		comboBoxMaterialProducto.removeAllItems();
		comboBoxProveedor.removeAllItems();
	}

	// TODO?: Necesitamos getters y setters en las vistas??
	public ProductoControlador getControladorProducto() {
		return controladorProducto;
	}

	public void setControladorProducto(ProductoControlador controladorProducto) {
		this.controladorProducto = controladorProducto;
	}

}
