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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import LogicaNegocio.ProductoControlador;
import LogicaNegocio.VentaControlador;
import Vista.Vista.VistaGeneral;
import modelo.VO.ProductoVO;

public class CatalogoVista extends JFrame {

	private JPanel contentPane;
	private JList<ProductoVO> list;
	private DefaultListModel<ProductoVO> model;

	private ArrayList<ProductoVO> productos;
	private ProductoControlador controladorProducto;
	private VentaControlador controladorVenta;
	private boolean isVenta; // Define si el catalogo se abre en la vista de venta o catalogo

	public CatalogoVista(boolean isVenta) {
		this.isVenta = isVenta;

		if (isVenta)
			this.controladorVenta = VentaControlador.getInstance();
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

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				setVisible(false);
				VistaGeneral vistaGeneral = new VistaGeneral();
				vistaGeneral.setVisible(true);
			}
		});
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblCatalogo = new JLabel("Catalogo");
		contentPane.add(lblCatalogo, BorderLayout.NORTH);

		if (isVenta) {
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

	}

	private void getProductos() {
		this.controladorProducto = ProductoControlador.getInstance();
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
						System.out.println("Click on: " + o.toString());
						if (isVenta) {
							// TODO: Arreglar UI productos seleccionados
							controladorVenta.seleccionProducto((ProductoVO) o);
							// System.out.println("Producto seleccionado para la venta");
						} else {
							EspecificacionProducto especificacion = new EspecificacionProducto((ProductoVO) o);
							especificacion.setVisible(true);
						}
					}
				}
			}
		};
		list.addMouseListener(mouseListener);

	}

}
