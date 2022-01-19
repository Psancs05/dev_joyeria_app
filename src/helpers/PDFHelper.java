package helpers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import modelo.VO.ProductoVO;
import modelo.VO.VentaVO;

public class PDFHelper {

    private static Font fuenteTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font fuenteNegrita = new Font(Font.FontFamily.UNDEFINED, 12,
            Font.BOLD);
    private static Font fuenteChiquita = new Font(Font.FontFamily.UNDEFINED, 7,
            Font.NORMAL);
    private static final DecimalFormat df = new DecimalFormat("0.00");
    // TODO: Buscar una forma de como pollas usar la imagen solo con la ubicacion
    // relativa
    private static String Imagen_logo = "/home/napuh/Desktop/dev_joyeria_app/assets/logo.jpg";

    public static boolean generarPDFEtiqueta(ArrayList<ProductoVO> productos, String ubicacion) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(ubicacion));
            document.open();
            addMetadatosPDF(document);
            rellenarEtiquetas(document, productos);
            document.close();
            return true;
        } catch (Exception e) {
            System.out.println("Excepcion en generarPDF etiqueta");
            return false;
        }
    }

    private static void rellenarEtiquetas(Document document, ArrayList<ProductoVO> productos)
            throws DocumentException, IOException {
        Paragraph preface = new Paragraph();
        preface.add(new Paragraph("Etiquetas de producto", fuenteTitulo));

        addEmptyLine(preface, 1);
        document.add(preface);

        PdfPTable tablaProductos = new PdfPTable(new float[] { 1, 1, 1, 1 });

        tablaProductos.setWidthPercentage(100);
        int repeticiones = 4 - (productos.size() % 4);

        for (ProductoVO prod : productos) {
            tablaProductos.addCell(getCellProducto(prod));
        }
        for (int i = 0; i < repeticiones; i++) {
            tablaProductos.addCell(getCell("", PdfPCell.ALIGN_CENTER));
        }
        document.add(tablaProductos);

    }

    public static boolean generarPDFVenta(VentaVO venta, String ubicacion) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(ubicacion));
            document.open();
            addMetadatosFactura(document, venta);
            rellenarInfoVenta(document, venta);
            document.close();
            return true;
        } catch (Exception e) {
            System.out.println("AAAAAAAAAAAAAAAAAA ERROR");
            return false;
        }
    }

    private static void addMetadatosFactura(Document document, VentaVO venta) {
        document.addTitle("Factura " + venta.getID());
        document.addSubject("Factura " + venta.getID() + " Claudio Joyas");
        document.addAuthor("Claudio Joyas");
        document.addCreator("Claudio Joyas");
    }

    private static void addMetadatosPDF(Document document) {
        document.addAuthor("Claudio Joyas");
        document.addCreator("Claudio Joyas");
    }

    private static void rellenarInfoVenta(Document document, VentaVO venta)
            throws DocumentException, IOException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Factura nº " + venta.getID(), fuenteTitulo));

        addEmptyLine(preface, 1);

        PdfPTable table = new PdfPTable(new float[] { 3, 1 });
        table.setWidthPercentage(100);
        table.addCell(getCellBold("Direccion de facturacion", PdfPCell.ALIGN_LEFT));
        table.addCell(getCellBold("Vendido por: ", PdfPCell.ALIGN_RIGHT));
        table.addCell(getCell("Nombre:______________________________________", PdfPCell.ALIGN_LEFT));
        table.addCell(getCell("JOyerias Claudio", PdfPCell.ALIGN_RIGHT));
        table.addCell(getCell("Direccion:_____________________________________", PdfPCell.ALIGN_LEFT));
        table.addCell(getCell("Calle patatin patatan 3", PdfPCell.ALIGN_RIGHT));
        table.addCell(getCell("Ciudad:_______________________________________", PdfPCell.ALIGN_LEFT));
        table.addCell(getCell("CIF 6969696969F", PdfPCell.ALIGN_RIGHT));
        table.addCell(getCell("CP:__________________________________________", PdfPCell.ALIGN_LEFT));
        table.addCell(getCell("Espanha, Leon, 240XX", PdfPCell.ALIGN_RIGHT));
        table.addCell(getCell("Pais:_________________________________________", PdfPCell.ALIGN_LEFT));
        table.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
        table.addCell(getCell("Pais:_______________________________________", PdfPCell.ALIGN_LEFT));

        // metemos docs en la pagina
        document.add(preface);
        document.add(table);

        // metemos detalles de la venta
        Paragraph detallesFactura = new Paragraph();
        addEmptyLine(detallesFactura, 1);
        detallesFactura.add("Numero de factura: " + venta.getID() + "\n");
        detallesFactura.add("Precio total: " + venta.getPrecioTotal() + "\n");
        detallesFactura.add("Fecha de la factura: " + venta.getFecha().toString() + "\n");
        addEmptyLine(detallesFactura, 1);
        document.add(detallesFactura);

        document.add(new Chunk(new LineSeparator()));

        // Metemos detalles de los prods
        Paragraph detallesProd = new Paragraph("Detalles de los productos:", fuenteTitulo);
        addEmptyLine(detallesProd, 1);
        document.add(detallesProd);

        // Creamos tabla
        PdfPTable tablaProds = new PdfPTable(new float[] { 2, 1, 1, 1 });
        tablaProds.setWidthPercentage(100);
        tablaProds.addCell(getCellBold("Descripcion del producto", PdfPCell.LEFT));
        tablaProds.addCell(getCellBold("Material", PdfPCell.LEFT));
        tablaProds.addCell(getCellBold("Tipo de producto", PdfPCell.LEFT));
        tablaProds.addCell(getCellBold("Precio sin IVA", PdfPCell.ALIGN_RIGHT));
        tablaProds.addCell(getCell(" ", PdfPCell.LEFT));
        tablaProds.addCell(getCell(" ", PdfPCell.LEFT));
        tablaProds.addCell(getCell(" ", PdfPCell.LEFT));
        tablaProds.addCell(getCell(" ", PdfPCell.LEFT));
        for (ProductoVO prod : venta.getProductos()) {
            tablaProds.addCell(getCell(prod.getDescripcion(), PdfPCell.ALIGN_MIDDLE));
            tablaProds.addCell(getCell(prod.getMaterial().toString(), PdfPCell.ALIGN_MIDDLE));
            tablaProds.addCell(getCell(prod.getTipoProducto().toString(), PdfPCell.ALIGN_MIDDLE));
            // el precio se muestra sin iva.
            // TODO:diferentes IVAS
            tablaProds.addCell(getCell(String.valueOf(df.format(prod.getPrecio() / 1.21)), PdfPCell.ALIGN_RIGHT));
            // espaciamos
            tablaProds.addCell(getCell(" ", PdfPCell.LEFT));
            tablaProds.addCell(getCell(" ", PdfPCell.LEFT));
            tablaProds.addCell(getCell(" ", PdfPCell.LEFT));
            tablaProds.addCell(getCell(" ", PdfPCell.LEFT));
        }
        tablaProds.addCell(getCell(" ", PdfPCell.LEFT));
        tablaProds.addCell(getCell(" ", PdfPCell.LEFT));
        tablaProds.addCell(getCell("TOTAL:   ", PdfPCell.ALIGN_RIGHT));
        tablaProds.addCell(getCell(String.valueOf(df.format(venta.getPrecioTotal() / 1.21)), PdfPCell.ALIGN_RIGHT));
        tablaProds.addCell(getCell(" ", PdfPCell.LEFT));
        tablaProds.addCell(getCell(" ", PdfPCell.LEFT));
        tablaProds.addCell(getCell("21% IVA:   ", PdfPCell.ALIGN_RIGHT));
        tablaProds.addCell(getCell(String.valueOf(df.format(venta.getPrecioTotal() * 0.21)), PdfPCell.ALIGN_RIGHT));
        tablaProds.addCell(getCell(" ", PdfPCell.LEFT));
        tablaProds.addCell(getCell(" ", PdfPCell.LEFT));
        tablaProds.addCell(getCell("TOTAL CON IVA:   ", PdfPCell.ALIGN_RIGHT));
        tablaProds.addCell(getCell(String.valueOf(df.format(venta.getPrecioTotal())), PdfPCell.ALIGN_RIGHT));

        document.add(tablaProds);
        // TODO: Hasta que no podamos usar la imagen nada
        // try {
        // Image image = Image.getInstance(Imagen_logo);
        // Paragraph p = new Paragraph();
        // p.add(image);
        // float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
        // - document.rightMargin()) / image.getWidth()) * 30;

        // image.scalePercent(scaler);
        // image.setAlignment(Element.ALIGN_CENTER);
        // p.setAlignment(Element.ALIGN_CENTER);
        // document.add(p);
        // } catch (MalformedURLException e) {
        // throw new MalformedURLException();
        // } catch (IOException e) {
        // throw new IOException();
        // }
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private static PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(2);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;

    }

    private static PdfPCell getCellBold(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, fuenteNegrita));
        cell.setPadding(2);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;

    }

    private static PdfPCell getCellProducto(ProductoVO producto) {
        PdfPCell cell = new PdfPCell();
        Phrase contenido = new Phrase();
        contenido.setFont(fuenteChiquita);
        contenido.add("Proveedor: " + producto.getProveedor().getNombre() + "\n");
        contenido.add("Tipo de pieza: " + String.valueOf(producto.getTipoProducto()) + "\n");
        contenido.add("Numero de cuaderno: " + String.valueOf(producto.getNumCuaderno()) + "\n");
        contenido.add(String.valueOf(producto.getPrecio()) + "€");
        cell.setPhrase(contenido);
        cell.setPadding(2);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        return cell;
    }

}
