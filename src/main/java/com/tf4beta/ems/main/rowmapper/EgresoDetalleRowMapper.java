package com.tf4beta.ems.main.rowmapper;

import com.tf4beta.ems.main.entity.Articulo;
import com.tf4beta.ems.main.entity.Bodega;
import com.tf4beta.ems.main.entity.Egreso;
import com.tf4beta.ems.main.entity.EgresoDetalles;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EgresoDetalleRowMapper implements RowMapper <EgresoDetalles> {
    @Override
    public EgresoDetalles mapRow(ResultSet rs, int rowNum) throws SQLException {
        EgresoDetalles egr = new EgresoDetalles();
        egr.setId_egreso_detalles(rs.getInt("id_egreso_detalles"));
        egr.setCantidad(rs.getInt("cantidad"));
        egr.setCosto(rs.getDouble("costo"));
        //Egreso
        Egreso egreso = createEgresoFromResultSet(rs);
        egr.setEgreso(egreso);
        //Articulo
        Articulo articulo = createArticuloFromResultSet(rs);
        egr.setArticulo(articulo);

        return egr;
    }
    public Egreso createEgresoFromResultSet(ResultSet rs) throws SQLException {

        Egreso egreso = new Egreso();
        egreso.setId_egreso_cab(rs.getInt("id_egreso_cab"));
        egreso.setFecha(rs.getString("fecha"));
        //Bodega
        Bodega bodega = createBodegaFromResultSet(rs);
        egreso.setBodega(bodega);
        return egreso;
    }
    private Bodega createBodegaFromResultSet(ResultSet rs) throws SQLException {
        Bodega bodega = new Bodega();
        bodega.setCodigo_bodega(rs.getInt("codigo_bodega"));
        bodega.setNombre(rs.getString("nombre"));
        bodega.setUbicacion(rs.getString("ubicacion"));
        bodega.setCodigoB(rs.getString("codigoB"));
        return bodega;
    }
    public Articulo createArticuloFromResultSet(ResultSet rs) throws SQLException {
        Articulo articulo = new Articulo();
        articulo.setId_articulo(rs.getInt("id_articulo"));
        articulo.setCodigoA(rs.getString("codigoa"));
        articulo.setNombre_articulo(rs.getString("nombre_articulo"));
        articulo.setClasificacion(rs.getString("clasificacion"));
        articulo.setStock_actual(rs.getInt("stock_actual"));
        articulo.setStock_minimo(rs.getInt("stock_minimo"));
        articulo.setStock_maximo(rs.getInt("stock_maximo"));
        articulo.setCosto_promedio(rs.getDouble("costo_promedio"));

        // Crear instancia de Bodega
        Bodega bodega = createBodegaFromResultSet(rs);

        // Establecer la instancia de Bodega en el artículo
        articulo.setBodegas(bodega);

        return articulo;
    }

}
