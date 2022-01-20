package Vista.Catalogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import LogicaNegocio.CatalogoControlador;
import LogicaNegocio.ProductoControlador;
import LogicaNegocio.VentaControlador;
import globals.enums.TipoCatalogo;
import helpers.PDFHelper;
import modelo.VO.ProductoVO;

public class CatalogoVista extends JFrame {

	private JPanel contentPane;
	private JList<ProductoVO> list;
	private DefaultListModel<ProductoVO> model;
	private JButton filtrarProductos;
	private JButton imprimirEtiquetas;

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

		Color gris = new Color(223, 223, 223);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				setVisible(false);
			}
		});
		setBounds(100, 100, 1000, 600);
		setLocationRelativeTo(null);
		setTitle("Catalogo");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		if (estado == TipoCatalogo.VENDER) {
			// Create a button in the bottom of the window
			JButton btnVenta = new JButton("Inciar venta");
			btnVenta.setBackground(gris);
			contentPane.add(btnVenta, BorderLayout.SOUTH);
			btnVenta.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("Iniciar venta");
					setVisible(false);
					controladorVenta.mostrarVenta();
				}
			});
		}

		// Create a button in the bottom of the window
		if (estado == TipoCatalogo.FILTRAR) {
			JPanel btnPnl = new JPanel();
			filtrarProductos = new JButton("Filtrar Productos");
			filtrarProductos.setBackground(gris);
			contentPane.add(filtrarProductos, BorderLayout.SOUTH);
			filtrarProductos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					controladorCatalogo.mostrarCatalogoFiltar();
				}
			});
			imprimirEtiquetas = new JButton("Imprimir etiqueta");
			btnPnl.add(filtrarProductos);
			btnPnl.add(imprimirEtiquetas);
			contentPane.add(btnPnl, BorderLayout.SOUTH);

			imprimirEtiquetas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int option = fileChooser.showOpenDialog(null);
					if (option == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						String path = file.getAbsolutePath();
						System.out.println("Seleccionado: " + path);
						boolean exito = PDFHelper.generarPDFEtiqueta(controladorProducto.getProductosEtiqueta(), path);
						if (exito) {
							JOptionPane.showMessageDialog(null, "PDF generado correctamente");
							// Se borra la lista de productos para que si se imprime otra vez no se repitan
							controladorProducto.clearProductosEtiqueta();
						}

					} else {
						JOptionPane.showMessageDialog(null, "Error al seleccionar directorio",
								"Error al crear la factura",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}

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
				@SuppressWarnings("rawtypes")
				JList theList = (JList) mouseEvent.getSource();
				theList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				// (event.getModifiers() & ActionEvent.CTRL_MASK) ==ActionEvent.CTRL_MASK
				if (mouseEvent.getClickCount() == 1 && (mouseEvent.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK) {
					int index = theList.locationToIndex(mouseEvent.getPoint());
					if (index >= 0) {
						Object o = theList.getModel().getElementAt(index);
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
							controladorVenta.seleccionProducto((ProductoVO) o);
						} else if (estado == TipoCatalogo.FILTRAR) {
							controladorProducto.seleccionProductoEtiqueta((ProductoVO) o);
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
				} else if(mouseEvent.getClickCount() == 1){
					if (estado == TipoCatalogo.VENDER) {
						controladorVenta.vaciarSeleccionProductos();
					} else if (estado == TipoCatalogo.FILTRAR) {
						controladorProducto.vaciarSeleccionEtiquetas();
					}
					theList.clearSelection();

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
