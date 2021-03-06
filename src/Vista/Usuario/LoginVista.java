package Vista.Usuario;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Image;

import LogicaNegocio.UsuarioControlador;
import Vista.Vista.VistaGeneral;

public class LoginVista extends JDialog {

	private UsuarioControlador controladorUsuario;

	public LoginVista(UsuarioControlador usuarioControlador) {
		this.controladorUsuario = usuarioControlador;
	}

	public void botonAutentificar() {

		JTextField dniField;
		JPasswordField passwordField;
		Color gris = new Color(223, 223, 223);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 526, 382);
		setLocationRelativeTo(null);
		setTitle("Login");
		getContentPane().setLayout(null);

		JLabel lblNewLabel_4 = new JLabel();
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("logo.jpg")).getImage()
				.getScaledInstance(120, 80, Image.SCALE_DEFAULT));
		lblNewLabel_4.setIcon(imageIcon);
		lblNewLabel_4.setBounds(204, 28, 141, 70);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel = new JLabel("        DNI");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(132, 123, 121, 33);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("     Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(132, 176, 121, 33);
		getContentPane().add(lblNewLabel_1);

		dniField = new JTextField();
		dniField.setBounds(263, 132, 121, 20);
		getContentPane().add(dniField);
		dniField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setColumns(10);
		passwordField.setBounds(263, 185, 121, 20);
		getContentPane().add(passwordField);

		JButton btnNewButton = new JButton("Iniciar sesion");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBackground(gris);
		btnNewButton.setBounds(194, 245, 141, 51);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dni = dniField.getText();
				String password = String.valueOf(passwordField.getPassword());
				if (dni.equals("") || password.equals("")) {
					JFrame error = new JFrame();
					JOptionPane.showMessageDialog(error, "Introduzca DNI y contrase??a.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (controladorUsuario.comprobarUsuario(dni, password) == false) {
					JFrame error = new JFrame();
					JOptionPane.showMessageDialog(error, "Usuario o contrasenia incorrectos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					new VistaGeneral();
					setVisible(false);
				}

			}
		});
		getContentPane().add(btnNewButton);

	}
}
