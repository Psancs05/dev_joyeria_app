package Vista.Catalogo;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helpers.ImagenHelper;
import modelo.VO.ProductoVO;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class EspecificacionProducto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ProductoVO producto;

	/**
	 * Create the dialog.
	 */
	public EspecificacionProducto(ProductoVO producto) {
		this.producto = producto;
		initialize();
	}

	public void initialize() {
		System.out.println("Creando Especificacion Producto");
		JLabel lblImg = new JLabel();
		ImageIcon img = new ImageIcon(ImagenHelper.getProductoImagen(producto.getImagen()));
		img = ImagenHelper.resizeImagen(img);
		lblImg.setIcon(img);
		lblImg.setBounds(12, 68, 230, 215);
		contentPanel.add(lblImg);

		JLabel lblNombre = new JLabel(producto.getNombre());
		lblNombre.setBounds(376, 30, 173, 29);
		contentPanel.add(lblNombre);

		JLabel lblPrecio = new JLabel(producto.getPrecio() + " €");
		lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecio.setBounds(726, 37, 114, 15);
		contentPanel.add(lblPrecio);

		JLabel lblTipopieza = new JLabel(producto.getTipoProducto().toString());
		lblTipopieza.setBounds(376, 103, 131, 15);
		contentPanel.add(lblTipopieza);

		JLabel lblMaterial = new JLabel(producto.getMaterial().toString());
		lblMaterial.setBounds(376, 167, 173, 15);
		contentPanel.add(lblMaterial);

		JLabel lblDescripcion = new JLabel(producto.getDescripcion());
		lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
		lblDescripcion.setBounds(12, 366, 661, 110);
		contentPanel.add(lblDescripcion);

		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblImagen = new JLabel("Imagen");
		lblImagen.setBounds(12, 12, 92, 29);
		lblImagen.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPanel.add(lblImagen);

		JLabel lblNombreDelProducto = new JLabel("Nombre del producto");
		lblNombreDelProducto.setBounds(376, 12, 173, 15);
		lblNombreDelProducto.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPanel.add(lblNombreDelProducto);

		JLabel lblPrecio_1 = new JLabel("Precio");
		lblPrecio_1.setBounds(779, 12, 70, 15);
		lblPrecio_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPanel.add(lblPrecio_1);

		JLabel lblTipoDePieza = new JLabel("Tipo de Pieza");
		lblTipoDePieza.setBounds(376, 77, 131, 15);
		lblTipoDePieza.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPanel.add(lblTipoDePieza);

		JLabel lblTipoDeMaterial = new JLabel("Tipo de Material");
		lblTipoDeMaterial.setBounds(373, 140, 188, 15);
		lblTipoDeMaterial.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPanel.add(lblTipoDeMaterial);

		JLabel lblDescripcionDelProducto = new JLabel("Descripcion del producto");
		lblDescripcionDelProducto.setBounds(12, 348, 188, 15);
		lblDescripcionDelProducto.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPanel.add(lblDescripcionDelProducto);

	}
}
