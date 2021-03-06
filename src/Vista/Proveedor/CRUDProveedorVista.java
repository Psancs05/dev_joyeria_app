package Vista.Proveedor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import LogicaNegocio.ProveedorControlador;
import modelo.VO.ProveedorVO;

public class CRUDProveedorVista extends JDialog {

	private ProveedorControlador controladorProveedor;
	private ProveedorVO proveedorSeleccionado;

	// * Constructor
	public CRUDProveedorVista(ProveedorControlador controladorProveedor) {
		this.controladorProveedor = controladorProveedor;
	}

	JTextField tfCIF;
	JLabel lbCIF2;
	JTextField tfNombre;
	ArrayList<ProveedorVO> listaDeProveedores;

	public void pulsarBotonAniadir() {

		Color gris = new Color(223, 223, 223);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 526, 382);
		setLocationRelativeTo(null);
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
		okButton.setBackground(gris);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				crearProveedor();
				limpiarCampos();
			}
		});

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		cancelButton.setBackground(gris);
		buttonPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

	}

	public void pulsarBotonModificar(ProveedorVO proveedor) {

		JDialog dialogModificar = new JDialog();

		Color gris = new Color(223, 223, 223);

		dialogModificar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogModificar.setResizable(false);
		dialogModificar.setVisible(true);
		dialogModificar.setBounds(100, 100, 526, 382);
		dialogModificar.setLocationRelativeTo(null);
		dialogModificar.setTitle("Modificar proveedor");
		dialogModificar.getContentPane().setLayout(null);

		JLabel lbCIF = new JLabel("CIF");
		lbCIF.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbCIF.setBounds(80, 104, 113, 33);
		dialogModificar.getContentPane().add(lbCIF);

		lbCIF2 = new JLabel("");
		lbCIF2.setText(proveedor.getCIF());
		lbCIF2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbCIF2.setBounds(316, 104, 94, 38);
		dialogModificar.getContentPane().add(lbCIF2);

		// tfCIF = new JTextField();
		// tfCIF.setColumns(10);
		// dialogModificar.getContentPane().add(tfCIF);
		// tfCIF.setBounds(316, 104, 94, 38);

		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbNombre.setBounds(80, 158, 77, 33);
		dialogModificar.getContentPane().add(lbNombre);

		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		dialogModificar.getContentPane().add(tfNombre);
		tfNombre.setBounds(316, 158, 94, 38);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 310, 510, 33);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		dialogModificar.getContentPane().add(buttonPane);

		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		dialogModificar.getContentPane().add(buttonPane, BorderLayout.SOUTH);

		// Comprobacion de que furrula hasta que tengamos el resto hecho

		String CIF = proveedor.getCIF();
		String nombre = proveedor.getNombre();

		//tfCIF.setText(CIF);
		tfNombre.setText(nombre);

		JButton okButton = new JButton("Modificar");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		dialogModificar.getRootPane().setDefaultButton(okButton);
		okButton.setBackground(gris);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialogModificar.setVisible(false);
				modificarProveedor(proveedor);
				limpiarCampos();
				controladorProveedor.mostrarModificar();
				setVisible(false);
			}
		});

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		cancelButton.setBackground(gris);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialogModificar.setVisible(false);
			}
		});

	}

	public void pulsarBotonEliminar(ProveedorVO proveedor) {
		int result = JOptionPane.showConfirmDialog(null, "Quieres eliminar el Proveedor de forma definitiva ?",
				"Confirmar eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (result == 0) {
			boolean response = controladorProveedor.eliminarProveedor(proveedor);
			if(!response){
				JFrame error = new JFrame();
				JOptionPane.showMessageDialog(error, "Uno o mas productos pertenecen a este proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			listaDeProveedores.remove(proveedor);
		}
		controladorProveedor.mostrarEliminar();
		setVisible(false);
	}

	public void mostrarListadoDeProveedores(boolean seleccionar) {
		listaDeProveedores = new ArrayList<ProveedorVO>();
		listaDeProveedores = controladorProveedor.getProveedores();

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 1042, 428);
		setLocationRelativeTo(null);
		setTitle("Eliminar Proveedor");
		getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1038, 389);
		getContentPane().add(scrollPane);
		DefaultListModel<String> model;
		model = new DefaultListModel<String>();
		for (ProveedorVO provVo : listaDeProveedores) {
			model.addElement(provVo.toStringListado());
		}
		JList<String> list = new JList<String>(model);
		list.setFont(new Font("Tahoma", Font.BOLD, 17));
		scrollPane.setViewportView(list);
		list.setFixedCellHeight(80);

		// Listener para abrir la especificacion de producto cuando se haga click en uno
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				@SuppressWarnings("rawtypes")
				JList theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) {
					int index = theList.locationToIndex(mouseEvent.getPoint());
					proveedorSeleccionado = listaDeProveedores.get(index);

					if (seleccionar == true) {
						pulsarBotonEliminar(proveedorSeleccionado);
					} else {
						pulsarBotonModificar(proveedorSeleccionado);
					}
				}

			}
		};
		list.addMouseListener(mouseListener);
	}

	public void crearProveedor() {
		String CIF = tfCIF.getText();
		String nombre = tfNombre.getText();
		if (CIF.equals("") || nombre.equals("")) {
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, "Debe rellenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			boolean response = controladorProveedor.aniadirProveedor(CIF, nombre);
			if(!response){
				JFrame error = new JFrame();
				JOptionPane.showMessageDialog(error, "Ya existe un proveedor con ese CIF o nombre", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		limpiarCampos();
	}

	public void modificarProveedor(ProveedorVO Proveedor) {
		String CIF = lbCIF2.getText();
		String nombre = tfNombre.getText();
		if (nombre.equals("")) {
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, "Debe rellenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			controladorProveedor.modificarProveedor(Proveedor, CIF, nombre);
		}
	}

	public void limpiarCampos() {
		tfNombre.setText("");
	}

}
