package com.tf4beta.ems.main.reportes;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tf4beta.ems.main.entity.EgresoDetalles;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
public class EgresoReporte {

private List<EgresoDetalles> listaEgresos;

public EgresoReporte(List<EgresoDetalles> listaEgresos){
    super();
    this.listaEgresos = listaEgresos;
}

    private void escribirDatosDeLaTabla(PdfPTable tabla) {
        for (EgresoDetalles egresoDetalles : listaEgresos) {
            tabla.addCell(String.valueOf(egresoDetalles.getId_egreso_detalles()));
            tabla.addCell(egresoDetalles.getEgreso().getFecha());
            tabla.addCell(egresoDetalles.getArticulo().getCodigoA());
            tabla.addCell(egresoDetalles.getArticulo().getNombre_articulo());
            tabla.addCell(String.valueOf(egresoDetalles.getCantidad()));
            tabla.addCell(String.valueOf(egresoDetalles.getCosto()));
            tabla.addCell(String.valueOf(egresoDetalles.getEgreso().getBodega().getCodigo_bodega()));
            tabla.addCell(egresoDetalles.getEgreso().getBodega().getNombre());
        }
    }

    private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
        PdfPCell celda = new PdfPCell();

        celda.setBackgroundColor(Color.BLUE);
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

        celda.setPhrase(new Phrase("Cantidad", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Costo", fuente));
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

        Paragraph titulo = new Paragraph("Lista de Egresos", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(8);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[] { 1f, 2.5f, 2.3f, 6f, 2.3f, 1.5f, 2f, 2.5f });
        tabla.setWidthPercentage(110);

        escribirCabeceraDeLaTabla(tabla);
        escribirDatosDeLaTabla(tabla);

        documento.add(tabla);
        documento.close();
    }


}