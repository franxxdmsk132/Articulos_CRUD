package com.tf4beta.ems.main.reportes;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tf4beta.ems.main.entity.IngresoDetalles;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
public class IngresoReporte {
    private List<IngresoDetalles> listaIngreso;

    public IngresoReporte(List<IngresoDetalles> listaIngreso){
        super();
        this.listaIngreso = listaIngreso;

    }

    private void escribirDatosDelaTabla(PdfPTable tabla){
        for (IngresoDetalles ingresoDetalles : listaIngreso){
            tabla.addCell(String.valueOf(ingresoDetalles.getId_Ingresos_detalle()));
            tabla.addCell(ingresoDetalles.getIngreso().getFecha());
            tabla.addCell(ingresoDetalles.getArticulo().getCodigoA());
            tabla.addCell(ingresoDetalles.getArticulo().getNombre_articulo());
            tabla.addCell(String.valueOf(ingresoDetalles.getCantidad_ingresada()));
            tabla.addCell(String.valueOf(ingresoDetalles.getPrecio_compra()));
            tabla.addCell(String.valueOf(ingresoDetalles.getIngreso().getBodega().getCodigo_bodega()));
            tabla.addCell(ingresoDetalles.getIngreso().getBodega().getNombre());
        }
    }
    private void escribirCabeceraDelaTabla(PdfPTable tabla){
        PdfPCell celda = new PdfPCell();

        celda.setBackgroundColor(Color.blue);
        celda.setPadding(2);
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("ID", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Fecha", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("#Articulo", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Nombre Art.", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Cantidad Ingr", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Precio Comp", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("#Bodega", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Nom. Bodega", fuente));
        tabla.addCell(celda);


    }
    public void exportar(HttpServletResponse response) throws DocumentException, IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLUE);
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Lista de Ingresos", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(8);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[] { 1f, 2.5f, 2.4f, 6f, 2.8f, 1.5f, 2f, 2.5f });
        tabla.setWidthPercentage(110);

        escribirCabeceraDelaTabla(tabla);
        escribirDatosDelaTabla(tabla);


        documento.add(tabla);
        documento.close();
    }

}
