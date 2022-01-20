package Vista.Facturas;

import java.awt.BorderLayout;
import java.util.ArrayList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import LogicaNegocio.VentaControlador;
import Vista.Vista.VistaGeneral;
import modelo.VO.VentaVO;

public class ListadoFacturasVista extends JFrame {
	private JPanel contentPane;
	private ArrayList<VentaVO> ventas;

	private VentaControlador controladorVenta;

	public ListadoFacturasVista() {

	}

	public void initialize() {
		this.setVisible(true);
		controladorVenta = VentaControlador.getInstance();
		ventas = controladorVenta.getListadoVentas();
		contentPane = new JPanel();

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				VistaGeneral vistaGeneral = new VistaGeneral();
				vistaGeneral.setVisible(true);
			}
		});
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblCatalogo = new JLabel("Facturas");
		contentPane.add(lblCatalogo, BorderLayout.NORTH);
		mostrarListadoVentas();

	}

	private void mostrarListadoVentas() {

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 1042, 428);
		setTitle("Facturas");
		getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1038, 389);
		getContentPane().add(scrollPane);
		DefaultListModel<String> model;
		model = new DefaultListModel<String>();
		for (VentaVO venta : ventas) {
			java.util.Date fecha = venta.getFecha();
			double precio = venta.getPrecioTotal();
			int id = venta.getID();
			model.addElement("Venta num: " + id + " | " + fecha + " | " + precio + "€");
		}
		JList<String> list = new JList<String>(model);
		scrollPane.setViewportView(list);
		list.setFixedCellHeight(80);
		// crea una lista con los productos

		// Listener para abrir la especificacion de producto cuando se haga click en uno
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 1) {
					int index = theList.locationToIndex(mouseEvent.getPoint());

					if (index >= 0) {
						Object o = theList.getModel().getElementAt(index);
						System.out.println("Click on: " + o.toString());
						VentaVO venta = ventas.get(index);
						System.out.println("Venta click: " + venta.getID());
						EspecificacionFactura especificacionFactura = new EspecificacionFactura(venta);
						especificacionFactura.setVisible(true);
					}
				}
			}
		};
		list.addMouseListener(mouseListener);
	}
}
