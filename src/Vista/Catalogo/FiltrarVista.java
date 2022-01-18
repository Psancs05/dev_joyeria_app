package Vista.Catalogo;

import javax.swing.JTextField;
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

import LogicaNegocio.CatalogoControlador;
import LogicaNegocio.ProductoControlador;
import LogicaNegocio.ProveedorControlador;
import globals.enums.TipoMaterial;
import globals.enums.TipoProducto;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;

public class FiltrarVista extends JDialog {

	public FiltrarVista() {
		this.controladorProducto = ProductoControlador.getInstance();
		this.controladorProveedor = ProveedorControlador.getInstance();
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

	private ProductoControlador controladorProducto;
	private ProveedorControlador controladorProveedor;
	private ArrayList<ProveedorVO> proveedores;

	public void mostrarVistaFiltrado() {
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

		JLabel lbVenta = new JLabel("ID Venta");
		lbVenta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbVenta.setBounds(10, 359, 107, 33);
		getContentPane().add(lbVenta);

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

		JButton okButton = new JButton("Filtrar");
		okButton.setActionCommand("OK");
		getContentPane().add(okButton);
		okButton.setBounds(506, 485, 89, 23);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CatalogoControlador controladorCatalogo = CatalogoControlador.getInstance();
				String nombre = tfNombre.getText();
				if (nombre.equals("")) {
					nombre = null;
				}

				double precio;
				try {
					precio = Double.parseDouble(tfPrecio.getText());
				} catch (Exception e) {
					precio = -1.0;
				}

				TipoProducto tipoProducto;
				if (comboBoxTipoProducto.getSelectedItem().toString() == "Anillo") {
					tipoProducto = TipoProducto.ANILLO;
				} else if (comboBoxTipoProducto.getSelectedItem().toString() == "Colgante") {
					tipoProducto = TipoProducto.COLGANTE;
				} else if (comboBoxTipoProducto.getSelectedItem().toString() == "Gafas") {
					tipoProducto = TipoProducto.GAFAS;
				} else if (comboBoxTipoProducto.getSelectedItem().toString() == "Pendiente") {
					tipoProducto = TipoProducto.PENDIENTE;
				} else if (comboBoxTipoProducto.getSelectedItem().toString() == "Pulsera") {
					tipoProducto = TipoProducto.PULSERA;
				} else {
					tipoProducto = null;
				}

				TipoMaterial tipoMaterial;
				if (comboBoxMaterialProducto.getSelectedItem().toString() == "Oro") {
					tipoMaterial = TipoMaterial.ORO;
				} else if (comboBoxMaterialProducto.getSelectedItem().toString() == "Plata") {
					tipoMaterial = TipoMaterial.PLATA;
				} else if (comboBoxMaterialProducto.getSelectedItem().toString() == "Oro + Plata") {
					tipoMaterial = TipoMaterial.OROPLATA;
				} else {
					tipoMaterial = null;
				}

				int numeroCuaderno;
				try {
					numeroCuaderno = Integer.parseInt(tfNumC.getText());
				} catch (Exception e) {
					numeroCuaderno = -1;
				}

				int idVenta;
				try {
					idVenta = Integer.parseInt(lbVenta.getText());
				} catch (Exception e) {
					idVenta = -1;
				}

				ProveedorVO proveedor;
				String proveedorText = comboBoxProveedor.getSelectedItem().toString();
				if (proveedorText == "default") {
					proveedor = null;
				} else {
					proveedor = controladorProveedor.getProveedorPorNombre(proveedorText);
				}

				ProductoVO parametro = new ProductoVO(nombre, numeroCuaderno, tipoProducto, precio, null, tipoMaterial,
						proveedor, idVenta, null);

				ArrayList<ProductoVO> listaFiltrada = controladorCatalogo.getListadaFiltrada(parametro);
				CatalogoVista catalogoVista = new CatalogoVista(false);
				catalogoVista.mostrarCatalogo(listaFiltrada);
				catalogoVista.setFiltrado();
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
		comboBoxTipoProducto.addItem("default");
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
		comboBoxMaterialProducto.addItem("default");
		comboBoxMaterialProducto.addItem("Oro");
		comboBoxMaterialProducto.addItem("Plata");
		comboBoxMaterialProducto.addItem("Oro + Plata");
	}

}
