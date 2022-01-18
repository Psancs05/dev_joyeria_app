package Vista.Catalogo;

import helpers.ImagenHelper;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import modelo.VO.ProductoVO;

public class ProductoRenderer extends JLabel implements ListCellRenderer<ProductoVO> {

	@Override
	public Component getListCellRendererComponent(JList<? extends ProductoVO> list, ProductoVO producto, int index,
			boolean isSelected, boolean cellHasFocus) {

		String nombre = producto.getNombre();
		ImageIcon imageIcon = new ImageIcon(ImagenHelper.getProductoImagen(producto.getImagen()));
		imageIcon = ImagenHelper.resizeImagen(imageIcon);

		setIcon(imageIcon);
		setText(nombre);
		setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		if (isSelected) {
			setBackground(new java.awt.Color(0, 0, 0));
			setForeground(new java.awt.Color(205, 232, 255)); // Color de producto seleccionado
		} else {
			setBackground(new java.awt.Color(255, 255, 255));
			setForeground(new java.awt.Color(0, 0, 0));
		}

		return this;
	}
}
