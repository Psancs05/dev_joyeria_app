package Vista.Catalogo;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helpers.ImagenHelper;
import modelo.VO.ProductoVO;
import javax.swing.JLabel;

public class EspecificacionProducto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ProductoVO producto;

	/**
	 * Create the dialog.
	 */
	public EspecificacionProducto(ProductoVO producto) {
		this.producto = producto;
	}

	public void initialize() {
		System.out.println("Creando Especificacion Producto");
		JLabel lblImg = new JLabel();
		lblImg.setIcon(new ImageIcon(ImagenHelper.getProductoImagen(producto.getImagen())));
		lblImg.setBounds(12, 12, 90, 90);
		contentPanel.add(lblImg);

		JLabel lblNombre = new JLabel(producto.getNombre());
		lblNombre.setBounds(12, 114, 173, 15);
		contentPanel.add(lblNombre);

		JLabel lblPrecio = new JLabel(producto.getPrecio() + "");
		lblPrecio.setBounds(12, 141, 173, 15);
		contentPanel.add(lblPrecio);

		JLabel lblTipopieza = new JLabel(producto.getTipoProducto().toString());
		lblTipopieza.setBounds(12, 167, 70, 15);
		contentPanel.add(lblTipopieza);

		JLabel lblMaterial = new JLabel(producto.getMaterial().toString());
		lblMaterial.setBounds(12, 194, 173, 15);
		contentPanel.add(lblMaterial);

		JLabel lblDescripcion = new JLabel(producto.getDescripcion());
		lblDescripcion.setBounds(225, 12, 203, 182);
		contentPanel.add(lblDescripcion);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

	}
}
