package Vista.Catalogo;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import modelo.VO.ProductoVO;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class ProductoTile extends JLabel implements ListCellRenderer {

	/**
	 * Create the panel.
	 */
	public ProductoTile(ProductoVO producto) {

		JLabel lblimagenproducto = new JLabel(new ImageIcon(getProductoImagen(producto.getImagen())));
		setLayout(null);
		ImageIcon icon = new ImageIcon(getProductoImagen(producto.getImagen()));
		lblimagenproducto.setIcon(icon);
		lblimagenproducto.setBounds(0, 0, 110, 110);
		add(lblimagenproducto);

		JLabel lblNombreproducto = new JLabel(producto.getNombre());
		lblNombreproducto.setBounds(122, 22, 160, 15);
		add(lblNombreproducto);

		JLabel lblPrecioProducto = new JLabel(producto.getPrecio() + "");
		lblPrecioProducto.setBounds(557, 48, 70, 15);
		add(lblPrecioProducto);

		JLabel lblTipoproducto = new JLabel(producto.getTipoProducto() + "");
		lblTipoproducto.setBounds(122, 48, 160, 15);
		add(lblTipoproducto);

		JLabel lblMaterialproducto = new JLabel(producto.getMaterial() + "");
		lblMaterialproducto.setBounds(122, 75, 160, 15);
		add(lblMaterialproducto);

	}

	public BufferedImage getProductoImagen(Blob productoImagen) {
		try {
			java.io.InputStream in = productoImagen.getBinaryStream();
			BufferedImage image;
			image = ImageIO.read(in);
			return image;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		ProductoVO entry = (ProductoVO) value;
		setText(entry.getNombre());
	    setIcon(new ImageIcon(getProductoImagen(entry.getImagen())));
		return this;
	}
}
