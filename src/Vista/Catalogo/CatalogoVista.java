package Vista.Catalogo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import LogicaNegocio.ProductoControlador;
import modelo.VO.ProductoVO;
import javax.swing.JList;
import javax.swing.JLabel;

public class CatalogoVista extends JFrame {

	private JPanel contentPane;
	private JList list;
	private DefaultListModel<ProductoTile> model;

	private ArrayList<ProductoVO> productos;
	private ProductoControlador controladorProducto;

	public CatalogoVista() {
		initialize();
		this.setVisible(true);
		getProductos();
		mostrarProductosEnLista();
	}

	/**
	 * Create the frame.
	 */
	private void initialize() {
		contentPane = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblCatalogo = new JLabel("Catalogo");
		contentPane.add(lblCatalogo, BorderLayout.NORTH);

	}

	private void getProductos() {
		controladorProducto = ProductoControlador.getInstance();
		productos = controladorProducto.getProductos();
		// for (ProductoVO producto : productos) {
		// System.out.println(producto.toString());
		// }
		System.out.println("Productos: " + productos.size());
	}

	private void mostrarProductosEnLista() {
		// crea una lista con los productos
		model = new DefaultListModel<ProductoTile>();
		for (ProductoVO producto : productos) {
			model.addElement(new ProductoTile(producto));
		}
		list = new JList<ProductoTile>(model);
		// list.setCellRenderer(new ProductoTileRenderer());
		contentPane.add(list, BorderLayout.CENTER);

		for (ProductoVO producto : productos) {
			System.out.println("Producto: " + producto.toString());
			// ProductoTile tile = new ProductoTile(producto);
			// this.list.add(tile);
			this.model.addElement(new ProductoTile(producto));
		}
		this.list.setVisibleRowCount(5);
		JScrollPane laminaDesplazamiento = new JScrollPane(this.list);
		this.contentPane.add(laminaDesplazamiento, BorderLayout.CENTER);
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

}
