package Vista.Catalogo;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import modelo.VO.ProductoVO;

public class ProductoRenderer extends JLabel implements ListCellRenderer<ProductoVO> {

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

	public ImageIcon resizeImagen(ImageIcon imagen) {
		Image image = imagen.getImage();
		Image nuevaImagen = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(nuevaImagen);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends ProductoVO> list, ProductoVO producto, int index,
			boolean isSelected, boolean cellHasFocus) {

		String nombre = producto.getNombre();
		ImageIcon imageIcon = new ImageIcon(getProductoImagen(producto.getImagen()));
		imageIcon = resizeImagen(imageIcon);

		setIcon(imageIcon);
		setText(nombre);
		setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		return this;
	}
}
