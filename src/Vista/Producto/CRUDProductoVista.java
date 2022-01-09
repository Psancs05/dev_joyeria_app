package Vista.Producto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Rectangle;
import com.mysql.cj.jdbc.Blob;

import LogicaNegocio.ProductoControlador;
import globals.enums.TipoProducto;
import globals.enums.TipoUsuario;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;

public class CRUDProductoVista extends JDialog{

    private ProductoControlador controladorProducto;
	private final JPanel contentPanel = new JPanel();
	
	public CRUDProductoVista(ProductoControlador controlador) {
		this.controladorProducto = controlador;
	}

	JTextField tfID;
	JTextField tfPrecio;
	JTextField tfImagen;
	JTextField tfProveedor;
    JTextField tfDescripcion;
	JComboBox comboBoxTipoProducto;

	public void pulsarBotonAniadir() {

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 526, 482);
		setTitle("Aniadir Producto");
		getContentPane().setLayout(null);

		JLabel lbID = new JLabel("ID");
		lbID.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbID.setBounds(10, 34, 113, 33);
		getContentPane().add(lbID);

		tfID = new JTextField();
		tfID.setBounds(316, 181, 94, 38);
		getContentPane().add(tfID);
		tfID.setBounds(316, 34, 131, 38);

		JLabel lbPrecio = new JLabel("Precio");
		lbPrecio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbPrecio.setBounds(10, 88, 77, 33);
		getContentPane().add(lbPrecio);

		tfPrecio = new JTextField();
		tfPrecio.setBounds(316, 230, 94, 38);
		getContentPane().add(tfPrecio);
		tfPrecio.setBounds(316, 83, 131, 38);

		JLabel lbImagen = new JLabel("Imagen");
		lbImagen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbImagen.setBounds(10, 137, 77, 33);
		getContentPane().add(lbImagen);

		tfImagen = new JTextField();
		tfImagen.setColumns(10);
		tfImagen.setBounds(316, 132, 131, 38);
		getContentPane().add(tfImagen);

		JLabel lbProveedor = new JLabel("Proveedor");
		lbProveedor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbProveedor.setBounds(10, 186, 77, 33);
		getContentPane().add(lbProveedor);

		tfProveedor = new JTextField();
		tfProveedor.setBounds(316, 181, 131, 38);
		getContentPane().add(tfProveedor);
		tfProveedor.setColumns(10);

        JLabel lbDescripcion = new JLabel("Descripcion");
		lbDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbDescripcion.setBounds(10, 280, 107, 33);
		getContentPane().add(lbDescripcion);

		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(316, 280, 131, 38);
		getContentPane().add(tfDescripcion);
		tfDescripcion.setColumns(10);

		JLabel lbTipoProducto = new JLabel("Tipo de Producto");
		lbTipoProducto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTipoProducto.setBounds(10, 233, 200, 35);
		getContentPane().add(lbTipoProducto);

		JButton okButton = new JButton("Aniadir");
		okButton.setActionCommand("OK");
		getContentPane().add(okButton);
        okButton.setBounds(280, 395, 100, 35);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Aniadir boton pulsado");
				setVisible(false);
				crearProducto();
				limpiarCampos();
			}
		});

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
        cancelButton.setBounds(400, 395, 100, 35);
		getContentPane().add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		comboBoxTipoProducto = new JComboBox();
		comboBoxTipoProducto.setToolTipText("Selecciona\r\n");
		comboBoxTipoProducto.setBounds(316, 230, 131, 38);
		getContentPane().add(comboBoxTipoProducto);
		comboBoxTipoProducto.addItem("default");
		comboBoxTipoProducto.addItem("Administrador");
		comboBoxTipoProducto.addItem("Cajero");

	}
	
	public void pulsarBotonModificar(ProductoVO producto) {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 526, 482);
		setTitle("Modificar Producto");
		getContentPane().setLayout(null);
		
		JTextField tfID;
		JTextField tfPrecio;
		JTextField tfImagen;
		JTextField tfProveedor;
		JTextField tfTipoProducto;
        JLabel lbID = new JLabel("ID");
		lbID.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbID.setBounds(10, 34, 113, 33);
		getContentPane().add(lbID);

		tfID = new JTextField();
		tfID.setBounds(316, 181, 94, 38);
		getContentPane().add(tfID);
		tfID.setBounds(316, 34, 131, 38);

		JLabel lbPrecio = new JLabel("Precio");
		lbPrecio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbPrecio.setBounds(10, 88, 77, 33);
		getContentPane().add(lbPrecio);

		tfPrecio = new JTextField();
		tfPrecio.setBounds(316, 230, 94, 38);
		getContentPane().add(tfPrecio);
		tfPrecio.setBounds(316, 83, 131, 38);

		JLabel lbImagen = new JLabel("Imagen");
		lbImagen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbImagen.setBounds(10, 137, 77, 33);
		getContentPane().add(lbImagen);

		tfImagen = new JTextField();
		tfImagen.setColumns(10);
		tfImagen.setBounds(316, 132, 131, 38);
		getContentPane().add(tfImagen);

		JLabel lbProveedor = new JLabel("Proveedor");
		lbProveedor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbProveedor.setBounds(10, 186, 77, 33);
		getContentPane().add(lbProveedor);

		tfProveedor = new JTextField();
		tfProveedor.setBounds(316, 181, 131, 38);
		getContentPane().add(tfProveedor);
		tfProveedor.setColumns(10);

        JLabel lbDescripcion = new JLabel("Descripcion");
		lbDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbDescripcion.setBounds(10, 280, 107, 33);
		getContentPane().add(lbDescripcion);

		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(316, 280, 131, 38);
		getContentPane().add(tfDescripcion);
		tfDescripcion.setColumns(10);

		JLabel lbTipoProducto = new JLabel("Tipo de Producto");
		lbTipoProducto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTipoProducto.setBounds(10, 233, 200, 35);
		getContentPane().add(lbTipoProducto);

        comboBoxTipoProducto = new JComboBox();
		comboBoxTipoProducto.setToolTipText("Selecciona\r\n");
		comboBoxTipoProducto.setBounds(316, 230, 131, 38);
		getContentPane().add(comboBoxTipoProducto);
		comboBoxTipoProducto.addItem("default");
		comboBoxTipoProducto.addItem("Administrador");
		comboBoxTipoProducto.addItem("Cajero");

		int ID = producto.getIDProducto();
		double Precio = producto.getPrecio();
		java.sql.Blob Imagen = producto.getImagen();
		ProveedorVO Proveedor = producto.getProveedor();
		TipoProducto tipoProducto = producto.getTipoProducto();

		//TODO ACABAR EL METODO


		JButton okButton = new JButton("Modificar");
		okButton.setActionCommand("OK");
		getContentPane().add(okButton);
        okButton.setBounds(280, 395, 100, 35);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				crearProducto();
				limpiarCampos();
			}
		});

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
        cancelButton.setBounds(400, 395, 100, 35);
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
		String ID = tfID.getText();
		String Precio = tfPrecio.getText();
		String Imagen = tfImagen.getText();
		String Proveedor = tfProveedor.getText();
		String descripcion = tfDescripcion.getText();
		Object combo = comboBoxTipoProducto.getSelectedItem();

		System.out.println(ID + " " + Precio + " " + Imagen + " " + Proveedor + " " + combo.toString());

		TipoProducto tipoProducto;
		if (combo.toString() == "Anillo") {
		tipoProducto = TipoProducto.ANILLO;
		} else if (combo.toString() == "Colgante") {
			tipoProducto = TipoProducto.COLGANTE;
		} else if (combo.toString() == "Gafas") {
			tipoProducto = TipoProducto.GAFAS;
		} else if (combo.toString() == "Pendiente") {
			tipoProducto = TipoProducto.PENDIENTE;
		} else {
			tipoProducto = TipoProducto.PULSERA;
		}
		// controladorProducto.aniadirProducto(ID, Precio, Imagen, Proveedor,
		// 		tipoProducto);
		limpiarCampos();
	}

	public void limpiarCampos() {
		tfID.setText("");
		tfPrecio.setText("");
		tfImagen.setText("");
		tfProveedor.setText("");
		comboBoxTipoProducto.removeAllItems();
	}

	// TODO?: Necesitamos getters y setters en las vistas??
	public ProductoControlador getControladorProducto() {
		return controladorProducto;
	}

	public void setControladorProducto(ProductoControlador controladorProducto) {
		this.controladorProducto = controladorProducto;
	}


}
