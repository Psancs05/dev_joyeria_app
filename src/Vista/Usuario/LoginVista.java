package Vista.Usuario;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.itextpdf.text.Font;

import LogicaNegocio.UsuarioControlador;

public class LoginVista extends JDialog{

    private UsuarioControlador controladorUsuario;

    public LoginVista(UsuarioControlador usuarioControlador) {
		this.controladorUsuario = usuarioControlador;
	}

    public void botonAutentificar(){

        JTextField textField;
	    JTextField textField_1;

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 526, 382);
		setTitle("Login");
		getContentPane().setLayout(null);


        JLabel lblNewLabel = new JLabel("Usuario");
		// lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(147, 123, 70, 33);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		// lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(147, 176, 90, 33);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(263, 132, 121, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(263, 185, 121, 20);
		getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("Iniciar sesion");
		btnNewButton.setBounds(147, 256, 106, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(263, 256, 106, 23);
		getContentPane().add(btnCancelar);


    }

}
