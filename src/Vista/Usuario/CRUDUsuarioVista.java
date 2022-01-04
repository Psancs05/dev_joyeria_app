package Vista.Usuario;

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

import LogicaNegocio.UsuarioControlador;
import globals.enums.TipoUsuario;
import modelo.VO.UsuarioVO;

public class CRUDUsuarioVista extends JDialog{
	
	private UsuarioControlador controladorUsuario;
	private final JPanel contentPanel = new JPanel();
	
	public CRUDUsuarioVista(UsuarioControlador controlador) {
		this.controladorUsuario = controlador;
	}

	JTextField tfDNI;
	JTextField tfNombre;
	JTextField tfEmail;
	JTextField tfPassword;
	JComboBox comboBoxTipoUsuario;

	public void pulsarBotonAniadir() {

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
		tfDNI.setBounds(316, 181, 94, 38);
		getContentPane().add(tfDNI);
		tfDNI.setBounds(316, 34, 131, 38);

		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbNombre.setBounds(10, 88, 77, 33);
		getContentPane().add(lbNombre);

		tfNombre = new JTextField();
		tfNombre.setBounds(316, 230, 94, 38);
		getContentPane().add(tfNombre);
		tfNombre.setBounds(316, 83, 131, 38);

		JLabel lbEmail = new JLabel("Email");
		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbEmail.setBounds(10, 137, 77, 33);
		getContentPane().add(lbEmail);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(316, 132, 131, 38);
		getContentPane().add(tfEmail);

		JLabel lbPassword = new JLabel("Password");
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbPassword.setBounds(10, 186, 77, 33);
		getContentPane().add(lbPassword);

		tfPassword = new JTextField();
		tfPassword.setBounds(316, 181, 131, 38);
		getContentPane().add(tfPassword);
		tfPassword.setColumns(10);

		JLabel lbTipoUsuario = new JLabel("Tipo de Usuario");
		lbTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTipoUsuario.setBounds(10, 233, 200, 35);
		getContentPane().add(lbTipoUsuario);

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
				crearUsuario();
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

		comboBoxTipoUsuario = new JComboBox();
		comboBoxTipoUsuario.setToolTipText("Selecciona\r\n");
		comboBoxTipoUsuario.setBounds(316, 230, 131, 38);
		getContentPane().add(comboBoxTipoUsuario);
		comboBoxTipoUsuario.addItem("default");
		comboBoxTipoUsuario.addItem("Administrador");
		comboBoxTipoUsuario.addItem("Cajero");

	}
	
	public void pulsarBotonModificar(UsuarioVO usuario) {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 526, 382);
		setTitle("Modificar Usuario");
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 310, 510, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Modificar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
			}
		}
		
		JTextField tfDNI;
		JTextField tfNombre;
		JTextField tfEmail;
		JTextField tfPassword;
		JTextField tfTipoUsuario;
		tfDNI = new JTextField();
		tfDNI.setBounds(316, 181, 94, 38);
		getContentPane().add(tfDNI);
		tfDNI.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(316, 230, 94, 38);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lbPassword = new JLabel("Password");
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbPassword.setBounds(10, 186, 77, 33);
		getContentPane().add(lbPassword);
		
		JLabel lbTipoUsuario = new JLabel("Tipo de Usuario");
		lbTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTipoUsuario.setBounds(10, 233, 200, 35);
		getContentPane().add(lbTipoUsuario);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(316, 132, 94, 38);
		getContentPane().add(tfEmail);
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(316, 83, 94, 38);
		getContentPane().add(tfPassword);
		JLabel lbEmail = new JLabel("Email");
		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbEmail.setBounds(10, 137, 77, 33);
		getContentPane().add(lbEmail);
		
		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbNombre.setBounds(10, 88, 77, 33);
		getContentPane().add(lbNombre);
		
		JLabel lbDNI = new JLabel("DNI");
		lbDNI.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbDNI.setBounds(10, 34, 113, 33);
		getContentPane().add(lbDNI);

		comboBoxTipoUsuario = new JComboBox();
		comboBoxTipoUsuario.setToolTipText("Selecciona\r\n");
		comboBoxTipoUsuario.setBounds(316, 230, 131, 38);
		getContentPane().add(comboBoxTipoUsuario);
		comboBoxTipoUsuario.addItem("default");
		comboBoxTipoUsuario.addItem("Administrador");
		comboBoxTipoUsuario.addItem("Cajero");

	}

	public void pulsarBotonEliminar() {

	}

	public void crearUsuario() {
		String DNI = tfDNI.getText();
		String nombre = tfNombre.getText();
		String email = tfEmail.getText();
		String password = tfPassword.getText();
		Object combo = comboBoxTipoUsuario.getSelectedItem();

		System.out.println(DNI + " " + nombre + " " + email + " " + password + " " + combo.toString());

		TipoUsuario tipoUsuario;
		if (combo.toString() == "Administrador") {
		tipoUsuario = TipoUsuario.ADMINISTRADOR;
		} else {
		tipoUsuario = TipoUsuario.CAJERO;
		}
		controladorUsuario.aniadirUsuario(DNI, nombre, email, password,
				tipoUsuario);
		limpiarCampos();
	}

	public void limpiarCampos() {
		tfDNI.setText("");
		tfNombre.setText("");
		tfEmail.setText("");
		tfPassword.setText("");
		comboBoxTipoUsuario.removeAllItems();
	}

	// TODO?: Necesitamos getters y setters en las vistas??
	public UsuarioControlador getControladorUsuario() {
		return controladorUsuario;
	}

	public void setControladorUsuario(UsuarioControlador controladorUsuario) {
		this.controladorUsuario = controladorUsuario;
	}


	
}
