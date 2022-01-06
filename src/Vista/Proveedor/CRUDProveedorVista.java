package Vista.Proveedor;

import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import LogicaNegocio.ProveedorControlador;
import modelo.VO.ProveedorVO;

public class CRUDProveedorVista extends JDialog {

	private ProveedorControlador controladorProveedor;

	// * Constructor
	public CRUDProveedorVista(ProveedorControlador controladorProveedor) {
		this.controladorProveedor = controladorProveedor;

		// ? Provisional
		ArrayList<ProveedorVO> listaProveedores = this.controladorProveedor.getProveedores();
		System.out.println("Lista de proveedores");
		for (ProveedorVO prov : listaProveedores) {
			System.out.println(prov.toString());
		}
	}

	JTextField tfCIF;
	JTextField tfNombre;

	public void pulsarBotonAniadir() {

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 526, 382);
		setTitle("Aniadir Proveedor");
		getContentPane().setLayout(null);

		JLabel lbCIF = new JLabel("CIF");
		lbCIF.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbCIF.setBounds(80, 104, 113, 33);
		getContentPane().add(lbCIF);

		tfCIF = new JTextField();
		tfCIF.setBounds(316, 181, 94, 38);
		getContentPane().add(tfCIF);
		tfCIF.setBounds(316, 104, 131, 38);

		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbNombre.setBounds(80, 158, 77, 33);
		getContentPane().add(lbNombre);

		tfNombre = new JTextField();
		tfNombre.setBounds(316, 230, 94, 38);
		getContentPane().add(tfNombre);
		tfNombre.setBounds(316, 158, 131, 38);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 310, 510, 33);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);

		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Aniadir");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Aniadir boton pulsado");
				setVisible(false);
				crearProveedor();
				limpiarCampos();
			}
		});

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

	}

	public void pulsarBotonModificar(ProveedorVO proveedor) {

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 526, 382);
		setTitle("Modificar proveedor");
		getContentPane().setLayout(null);

		JLabel lbCIF = new JLabel("CIF");
		lbCIF.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbCIF.setBounds(80, 104, 113, 33);
		getContentPane().add(lbCIF);

		tfCIF = new JTextField();
		tfCIF.setColumns(10);
		getContentPane().add(tfCIF);
		tfCIF.setBounds(316, 104, 94, 38);

		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbNombre.setBounds(80, 158, 77, 33);
		getContentPane().add(lbNombre);

		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		getContentPane().add(tfNombre);
		tfNombre.setBounds(316, 158, 94, 38);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 310, 510, 33);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);

		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		// Comprobacion de que furrula hasta que tengamos el resto hecho

		String CIF = proveedor.getCIF();
		String nombre = proveedor.getNombre();

		tfCIF.setText(CIF);
		tfNombre.setText(nombre);

		JButton okButton = new JButton("Modificar");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.setForeground(Color.BLACK);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				modificarProveedor(proveedor);
				limpiarCampos();
			}
		});

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		cancelButton.setForeground(Color.BLACK);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

	}

	public void pulsarBotonEliminar() {

	}

	public void crearProveedor() {
		String CIF = tfCIF.getText();
		String nombre = tfNombre.getText();

		System.out.println(CIF + " " + nombre);
		limpiarCampos();
	}

	public void modificarProveedor(ProveedorVO Proveedor) {
		String CIF = tfCIF.getText();
		String nombre = tfNombre.getText();

		System.out.println(CIF + " " + nombre + " el Proveedor antiguo era " + Proveedor.toString());
		controladorProveedor.modificarProveedor(Proveedor, CIF, nombre);
	}

	public void limpiarCampos() {
		tfCIF.setText("");
		tfNombre.setText("");
	}

	// TODO?: Necesitamos getters y setters en las vistas??
	public ProveedorControlador getControladorProveedor() {
		return controladorProveedor;
	}

	public void setControladorProveedor(ProveedorControlador controladorProveedor) {
		this.controladorProveedor = controladorProveedor;
	}
}
