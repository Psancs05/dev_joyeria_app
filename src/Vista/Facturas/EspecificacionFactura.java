package Vista.Facturas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import helpers.PDFHelper;
import modelo.VO.ProductoVO;
import modelo.VO.VentaVO;

public class EspecificacionFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private VentaVO venta;

	public EspecificacionFactura(VentaVO venta) {
		this.venta = venta;
		initialize();
	}

	public void initialize() {
		setBounds(100, 100, 758, 517);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblVentaNumero = new JLabel("Venta Numero: " + venta.getID());
		lblVentaNumero.setBounds(28, 12, 160, 40);
		contentPanel.add(lblVentaNumero);

		int size = this.venta.getCantidadArticulos();
		String column[] = { "Nombre", "Precio" };
		String data[][] = new String[size][2];
		ArrayList<ProductoVO> productos = this.venta.getProductos();
		for (int i = 0; i < size; i++) {
			data[i][0] = productos.get(i).getNombre();
			data[i][1] = productos.get(i).getPrecio() + "";
		}
		JTable table = new JTable(data, column);
		table.setBounds(0, 54, 522, 276);
		contentPanel.add(table);

		JLabel lblSubtotal = new JLabel("Subtotal: " + venta.getPrecioTotal() + "€");
		lblSubtotal.setBounds(28, 413, 172, 27);
		contentPanel.add(lblSubtotal);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("Volver");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonPane.add(cancelButton);

		JButton okButton = new JButton("Generar PDF");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = fileChooser.showOpenDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String path = file.getAbsolutePath();
					System.out.println("Seleccionado: " + path);
					boolean exito = PDFHelper.generarPDFVenta(venta, path);
					if (exito)
						JOptionPane.showMessageDialog(null, "PDF generado correctamente");

				} else {
					JOptionPane.showMessageDialog(null, "Error al seleccionar directorio", "Error al crear la factura",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}

}
