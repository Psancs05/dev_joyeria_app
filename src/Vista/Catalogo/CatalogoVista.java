package Vista.Catalogo;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import LogicaNegocio.CatalogoControlador;
import LogicaNegocio.ProductoControlador;
import LogicaNegocio.VentaControlador;
import Vista.Vista.VistaGeneral;
import globals.enums.TipoCatalogo;
import modelo.VO.ProductoVO;

public class CatalogoVista extends JFrame {

	private JPanel contentPane;
	private JList<ProductoVO> list;
	private DefaultListModel<ProductoVO> model;
	private JButton filtrarProductos;

	private ArrayList<ProductoVO> productos;
	private ProductoControlador controladorProducto;
	private CatalogoControlador controladorCatalogo;
	private VentaControlador controladorVenta;
	private TipoCatalogo estado; // Define si el catalogo se abre para poder buscar, filtrar, modificar o
	// eliminar

	public CatalogoVista(TipoCatalogo estado) {
		this.estado = estado;
		controladorCatalogo = CatalogoControlador.getInstance();
		controladorProducto = ProductoControlador.getInstance();

		if (estado == TipoCatalogo.VENDER)
			this.controladorVenta = VentaControlador.getInstance();
		initialize();
		this.setVisible(true);
		initialize();
		getProductos();
		mostrarProductosEnLista();
	}

	private void setProductos(ArrayList<ProductoVO> productos) {
		this.productos = productos;

	}

	public void setFiltrado(TipoCatalogo nuevoValor) {
		this.estado = nuevoValor;
		filtrarProductos.setVisible(false);
	}

	public void mostrarCatalogo(ArrayList<ProductoVO> productos) {
		setProductos(productos);
		mostrarProductosEnLista();
	}

	private void initialize() {
		contentPane = new JPanel();

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				setVisible(false);
			}
		});
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblCatalogo = new JLabel("Catalogo");
		contentPane.add(lblCatalogo, BorderLayout.NORTH);

		if (estado == TipoCatalogo.VENDER) {
			// Create a button in the bottom of the window
			JButton btnVenta = new JButton("Inciar venta");

			contentPane.add(btnVenta, BorderLayout.SOUTH);
			btnVenta.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("Iniciar venta");
					controladorVenta.mostrarVenta();
				}
			});
		}

		// Create a button in the bottom of the window
		if (estado == TipoCatalogo.FILTRAR) {
			filtrarProductos = new JButton("Filtrar Productos");
			contentPane.add(filtrarProductos, BorderLayout.SOUTH);
			filtrarProductos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					controladorCatalogo.mostrarCatalogoFiltar();
				}
			});
		}

		// if (estado == TipoCatalogo.ELIMINAR) {
		// // Create a button in the bottom of the window
		// JButton btnEliminar = new JButton("Borrar producto");

		// contentPane.add(btnEliminar, BorderLayout.SOUTH);
		// btnEliminar.addMouseListener(new MouseAdapter() {
		// public void mouseClicked(MouseEvent mouseEvent) {
		// JList theList = (JList) mouseEvent.getSource();

		// int index = theList.locationToIndex(mouseEvent.getPoint());

		// if (index >= 0) {
		// Object o = theList.getModel().getElementAt(index);
		// System.out.println("Click on: " + o.toString());
		// controladorProducto.eliminarProducto((ProductoVO) o);
		// }

		// }
		// });
		// }

	}

	private void getProductos() {
		productos = controladorProducto.getProductos();
		System.out.println("Productos: " + productos.size());
	}

	private void mostrarProductosEnLista() {
		// crea una lista con los productos
		model = new DefaultListModel<ProductoVO>();
		for (ProductoVO producto : productos) {
			model.addElement(producto);
		}

		list = new JList<ProductoVO>(model);
		add(new JScrollPane(list));
		list.setCellRenderer(new ProductoRenderer());

		// Listener para abrir la especificacion de producto cuando se haga click en uno
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 1) {
					int index = theList.locationToIndex(mouseEvent.getPoint());
					if (index >= 0) {
						Object o = theList.getModel().getElementAt(index);
						// System.out.println("Click on: " + o.toString());
						if (estado == TipoCatalogo.ELIMINAR) {
							int result = JOptionPane.showConfirmDialog(null,
									"Quieres eliminar el Producto de forma definitiva ?", "Confirmar eliminar",
									JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
							if (result == 0) {
								controladorProducto.eliminarProducto((ProductoVO) o);
								setVisible(false);
								controladorCatalogo.actualizar(TipoCatalogo.ELIMINAR);
								System.out.println("Se ha eliminado el Producto " + o.toString());
							}
						} else if (estado == TipoCatalogo.MODIFICAR) {
							controladorProducto.mostrarModificar((ProductoVO) o);
						} else if (estado == TipoCatalogo.VENDER) {
							// TODO: Abrir ventana principal
							controladorVenta.seleccionProducto((ProductoVO) o);
						}
					}
				} else if (mouseEvent.getClickCount() == 2) {
					int index = theList.locationToIndex(mouseEvent.getPoint());

					if (index >= 0) {
						Object o = theList.getModel().getElementAt(index);
						System.out.println("Click on: " + o.toString());

						EspecificacionProducto especificacion = new EspecificacionProducto((ProductoVO) o);
						especificacion.setVisible(true);

					}
				}
			}
		};
		list.addMouseListener(mouseListener);
	}

	public JList<ProductoVO> getJList() {
		return list;
	}

	public ArrayList<ProductoVO> getListaProductos() {
		return productos;
	}

}
