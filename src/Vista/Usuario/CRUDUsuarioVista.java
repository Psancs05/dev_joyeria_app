package Vista.Usuario;

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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.awt.geom.Dimension;
import com.itextpdf.text.pdf.parser.Vector;

import LogicaNegocio.UsuarioControlador;
import globals.enums.TipoUsuario;
import modelo.VO.UsuarioVO;

public class CRUDUsuarioVista extends JDialog {

	private UsuarioControlador controladorUsuario;
	private UsuarioVO usuarioSeleccionado;

	public CRUDUsuarioVista(UsuarioControlador usuarioControlador) {
		this.controladorUsuario = usuarioControlador;
	}

	JTextField tfDNI;
	JTextField tfNombre;
	JTextField tfEmail;
	JTextField tfPassword;
	JComboBox<String> cbTipoUsuario;
	ArrayList<UsuarioVO> listaDeUsuarios;

	public void pulsarBotonAniadir() {
		repaint();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 526, 382);
		setTitle("Aniadir Usuario");
		getContentPane().setLayout(null);

		JLabel lbDNI = new JLabel("DNI");
		lbDNI.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbDNI.setBounds(10, 34, 113, 33);
		getContentPane().add(lbDNI);

		tfDNI = new JTextField();
		tfDNI.setColumns(10);
		getContentPane().add(tfDNI);
		tfDNI.setBounds(316, 34, 94, 38);

		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbNombre.setBounds(10, 88, 77, 33);
		getContentPane().add(lbNombre);

		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		getContentPane().add(tfNombre);
		tfNombre.setBounds(316, 83, 94, 38);

		JLabel lbEmail = new JLabel("Email");
		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbEmail.setBounds(10, 137, 77, 33);
		getContentPane().add(lbEmail);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(316, 132, 94, 38);
		getContentPane().add(tfEmail);

		JLabel lbPassword = new JLabel("Password");
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbPassword.setBounds(10, 186, 77, 33);
		getContentPane().add(lbPassword);

		tfPassword = new JTextField();
		tfPassword.setBounds(316, 181, 94, 38);
		getContentPane().add(tfPassword);
		tfPassword.setColumns(10);

		JLabel lbTipoUsuario = new JLabel("Tipo de Usuario");
		lbTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTipoUsuario.setBounds(10, 233, 200, 35);
		getContentPane().add(lbTipoUsuario);

		cbTipoUsuario = new JComboBox<String>();
		cbTipoUsuario.setBounds(316, 230, 94, 38);
		cbTipoUsuario.addItem(" ");
		cbTipoUsuario.addItem("Administrador");
		cbTipoUsuario.addItem("Cajero");
		getContentPane().add(cbTipoUsuario);

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
		okButton.setForeground(Color.BLACK);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Aniadir boton pulsado");
				setVisible(false);
				crearUsuario();
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
		repaint();
	}

	public void pulsarBotonModificar(UsuarioVO usuario) {

		JDialog dialogModificar = new JDialog();

		dialogModificar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogModificar.setVisible(true);
		dialogModificar.setBounds(100, 100, 526, 382);
		dialogModificar.setTitle("Modificar Usuario");
		dialogModificar.getContentPane().setLayout(null);

		JLabel lbDNI = new JLabel("DNI");
		lbDNI.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbDNI.setBounds(10, 34, 113, 33);
		dialogModificar.getContentPane().add(lbDNI);

		tfDNI = new JTextField();
		tfDNI.setColumns(10);
		dialogModificar.getContentPane().add(tfDNI);
		tfDNI.setBounds(316, 34, 94, 38);

		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbNombre.setBounds(10, 88, 77, 33);
		dialogModificar.getContentPane().add(lbNombre);

		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		dialogModificar.getContentPane().add(tfNombre);
		tfNombre.setBounds(316, 83, 94, 38);

		JLabel lbEmail = new JLabel("Email");
		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbEmail.setBounds(10, 137, 77, 33);
		dialogModificar.getContentPane().add(lbEmail);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(316, 132, 94, 38);
		dialogModificar.getContentPane().add(tfEmail);

		JLabel lbPassword = new JLabel("Password");
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbPassword.setBounds(10, 186, 77, 33);
		dialogModificar.getContentPane().add(lbPassword);

		tfPassword = new JTextField();
		tfPassword.setBounds(316, 181, 94, 38);
		dialogModificar.getContentPane().add(tfPassword);
		tfPassword.setColumns(10);

		JLabel lbTipoUsuario = new JLabel("Tipo de Usuario");
		lbTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTipoUsuario.setBounds(10, 233, 200, 35);
		dialogModificar.getContentPane().add(lbTipoUsuario);

		cbTipoUsuario = new JComboBox<String>();
		cbTipoUsuario.setBounds(316, 230, 94, 38);
		cbTipoUsuario.addItem(" ");
		cbTipoUsuario.addItem("Administrador");
		cbTipoUsuario.addItem("Cajero");
		dialogModificar.getContentPane().add(cbTipoUsuario);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 310, 510, 33);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		dialogModificar.getContentPane().add(buttonPane);

		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		dialogModificar.getContentPane().add(buttonPane, BorderLayout.SOUTH);

		// Comprobacion de que furrula hasta que tengamos el resto hecho

		String DNI = usuario.getDNI();
		String nombre = usuario.getNombre();
		String email = usuario.getEmail();
		String password = usuario.getPassword();
		TipoUsuario tipoUsuario = usuario.getTipoUsuario();

		tfDNI.setText(DNI);
		tfNombre.setText(nombre);
		tfEmail.setText(email);
		tfPassword.setText(password);
		int index = 0;
		if (tipoUsuario.toString() == "ADMINISTRADOR") {
			index = 1;
		} else {
			index = 2;
		}
		cbTipoUsuario.setSelectedIndex(index);

		JButton okButton = new JButton("Modificar");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		dialogModificar.getRootPane().setDefaultButton(okButton);
		okButton.setForeground(Color.BLACK);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialogModificar.setVisible(false);
				modificarUsuario(usuario);
				limpiarCampos();
			}
		});

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		cancelButton.setForeground(Color.BLACK);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialogModificar.setVisible(false);
			}
		});
		dialogModificar.repaint();
	}

	public void pulsarBotonEliminar(UsuarioVO usuario) {

		JFrame adv = new JFrame();
		int result = JOptionPane.showConfirmDialog(null, "Quieres eliminar el usuario de forma definitiva ?", "Confirmar eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(result == 0){
			controladorUsuario.eliminarUsuario(usuario);
			listaDeUsuarios.remove(usuario);
			System.out.println("Se ha eliminado el usuario " + usuario.toString());
		}
	}

	public void mostrarListadoDeUsuarios(boolean seleccionar){

		listaDeUsuarios = new ArrayList<UsuarioVO>();
		listaDeUsuarios = controladorUsuario.getInstance().getUsuarios();
		System.out.println(listaDeUsuarios.toString());

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 1042, 428);
		setTitle("Eliminar Usuario");
		getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1038, 389);
		getContentPane().add(scrollPane);
		DefaultListModel<String> model;
		model = new DefaultListModel<String>();
		for (UsuarioVO userVo : listaDeUsuarios) {
			model.addElement(userVo.toStringListado());
		}
		JList list = new JList(model);
		list.setFont(new Font("Tahoma", Font.BOLD, 17));
		scrollPane.setViewportView(list);
		list.setFixedCellHeight(80);

		// Listener para abrir la especificacion de producto cuando se haga click en uno
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) {
						int index = theList.locationToIndex(mouseEvent.getPoint());
						usuarioSeleccionado =  listaDeUsuarios.get(index);
						
						if(seleccionar == true){
							pulsarBotonEliminar(usuarioSeleccionado);
						} else {
							pulsarBotonModificar(usuarioSeleccionado);
						}
					}
					
				}
		};
		list.addMouseListener(mouseListener);
	}
		

	public void crearUsuario() {
		
		String DNI = tfDNI.getText();
		String nombre = tfNombre.getText();
		String email = tfEmail.getText();
		String password = tfPassword.getText();
		Object tipoUsuariocb = cbTipoUsuario.getSelectedItem();

		System.out.println(DNI + " " + nombre + " " + email + " " + password + " " + tipoUsuariocb);
		System.out.println(DNI == "");
		TipoUsuario tipoUsuario;
		if (tipoUsuariocb.toString() == "Administrador") {
			tipoUsuario = TipoUsuario.ADMINISTRADOR;
		} else {
			tipoUsuario = TipoUsuario.CAJERO;
		}
		
		if(DNI.equals("") || nombre.equals("") || email.equals("") || password.equals("") || tipoUsuariocb.toString().equals(" ") ){
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, "Debe rellenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(DNI.length() != 9){
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, "El DNI no tiene 9 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);			
		} else if(!(email.contains("@"))){
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, "El email no contiene @.", "Error", JOptionPane.ERROR_MESSAGE);	
		} else {
			controladorUsuario.aniadirUsuario(DNI, nombre, email, password,
			tipoUsuario);
		}
		limpiarCampos();
	}

	public void modificarUsuario(UsuarioVO usuario) {
		String DNI = tfDNI.getText();
		String nombre = tfNombre.getText();
		String email = tfEmail.getText();
		String password = tfPassword.getText();
		Object tipoUsuariocb = cbTipoUsuario.getSelectedItem();

		System.out.println(DNI + " " + nombre + " " + email + " " + password + " " + tipoUsuariocb
				+ " el usuario antiguo era " + usuario.toString());

		TipoUsuario tipoUsuario;
		if (tipoUsuariocb.toString() == "Administrador") {
			tipoUsuario = TipoUsuario.ADMINISTRADOR;
		} else {
			tipoUsuario = TipoUsuario.CAJERO;
		}

		if(DNI.equals("") || nombre.equals("") || email.equals("") || password.equals("") || tipoUsuariocb.toString().equals(" ") ){
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, "Debe rellenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(DNI.length() != 9){
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, "El DNI no tiene 9 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);			
		} else if(!(email.contains("@"))){
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, "El email no contiene @.", "Error", JOptionPane.ERROR_MESSAGE);	
		} else {
			controladorUsuario.modificarUsuario(usuario, DNI, nombre, email, password,
				tipoUsuario);
		}
	}

	public void limpiarCampos() {
		tfDNI.setText("");
		tfNombre.setText("");
		tfEmail.setText("");
		tfPassword.setText("");
		cbTipoUsuario.setSelectedIndex(0);
	}

	// TODO?: Necesitamos getters y setters en las vistas??
	public UsuarioControlador getControladorUsuario() {
		return controladorUsuario;
	}

	public void setControladorUsuario(UsuarioControlador controladorUsuario) {
		this.controladorUsuario = controladorUsuario;
	}

}
