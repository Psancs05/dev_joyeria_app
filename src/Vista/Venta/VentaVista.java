package Vista.Venta;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import LogicaNegocio.VentaControlador;
import modelo.VO.ProductoVO;

public class VentaVista extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private VentaControlador controladorVenta;
	private ArrayList<ProductoVO> productosVenta;
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public VentaVista(VentaControlador controladorVenta, ArrayList<ProductoVO> productos) {
		this.controladorVenta = controladorVenta;
		this.productosVenta = productos;
		System.out.println("productosVenta: " + productosVenta.size());
		initialite();

	}

	public void initialite() {

		Color gris = new Color(223, 223, 223);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblProductosSeleccionados = new JLabel("Productos seleccionados");
		lblProductosSeleccionados.setBounds(12, 12, 271, 15);
		getContentPane().add(lblProductosSeleccionados);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 39, 220, 2);
		getContentPane().add(separator);

		int size = this.productosVenta.size();
		String column[] = { "Nombre", "Precio" };
		String data[][] = new String[size][2];
		for (int i = 0; i < size; i++) {
			data[i][0] = productosVenta.get(i).getNombre();
			data[i][1] = productosVenta.get(i).getPrecio() + "";
		}
		table = new JTable(data, column);
		table.setBounds(12, 50, 238, 130);

		getContentPane().add(table);

		JLabel lblPrecioTotal = new JLabel("Precio Total");
		lblPrecioTotal.setBounds(12, 208, 106, 15);
		getContentPane().add(lblPrecioTotal);

		JLabel lblPrecio = new JLabel(controladorVenta.getPrecioTotal() + "");
		lblPrecio.setBounds(193, 208, 178, 15);
		getContentPane().add(lblPrecio);

		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane);
		buttonPane.setLayout(null);

		JButton okButton = new JButton("Aceptar");
		okButton.setBackground(gris);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controladorVenta.aceptarVenta();
				JOptionPane.showMessageDialog(null, "Venta creada correctamente", "Exito",
						JOptionPane.INFORMATION_MESSAGE);
				controladorVenta.mostrarCatalogo();
				dispose();
			}
		});
		okButton.setBounds(232, 233, 89, 25);
		buttonPane.add(okButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setBackground(gris);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Borramos los productos seleccionados para que no se repitan al volver a abrir
				// la venta
				controladorVenta.clearProductosVenta();
				controladorVenta.mostrarCatalogo();
				dispose();
			}
		});
		cancelButton.setBounds(333, 233, 96, 25);
		buttonPane.add(cancelButton);

	}
}
