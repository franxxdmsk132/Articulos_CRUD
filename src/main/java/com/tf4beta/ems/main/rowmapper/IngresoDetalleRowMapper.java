package com.tf4beta.ems.main.rowmapper;

import com.tf4beta.ems.main.entity.Articulo;
import com.tf4beta.ems.main.entity.Bodega;
import com.tf4beta.ems.main.entity.Ingreso;
import com.tf4beta.ems.main.entity.IngresoDetalles;
import org.springframework.jdbc.core.RowMapper;

///import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IngresoDetalleRowMapper implements RowMapper <IngresoDetalles> {


    @Override
    public IngresoDetalles mapRow(ResultSet rs, int rowNum)throws SQLException{

        IngresoDetalles ingresoDetalles = new IngresoDetalles();
        ingresoDetalles.setId_Ingresos_detalle(rs.getInt("id_ingresos_detalle"));
        ingresoDetalles.setCantidad_ingresada(rs.getInt("cantidad_ingresada"));
        ingresoDetalles.setPrecio_compra(rs.getDouble("precio_compra"));

        ///Ingreso
        Ingreso ingreso = createIngresoFromResultSet(rs);
        ingresoDetalles.setIngreso(ingreso);

        ///Articulo
        Articulo articulo = crateArticuloFromResultSet(rs);
        ingresoDetalles.setArticulo(articulo);

        return ingresoDetalles;
    }
    public Ingreso createIngresoFromResultSet(ResultSet rs) throws SQLException {
        Ingreso ingreso = new Ingreso();
        ingreso.setId_ingresocab(rs.getInt("id_ingreso_cab"));
        ingreso.setFecha(rs.getString("fecha"));
        ///Bodega
        Bodega bodega = createBodegaFromResultSet(rs);
        ingreso.setBodega(bodega);
        return ingreso;

    }
    private Bodega createBodegaFromResultSet(ResultSet rs) throws SQLException{
        Bodega bodega = new Bodega();
        bodega.setCodigo_bodega(rs.getInt("codigo_bodega"));
        bodega.setNombre(rs.getString("nombre"));
        bodega.setUbicacion(rs.getString("ubicacion"));
        bodega.setCodigoB(rs.getString("codigoB"));
        return bodega;
    }
    public Articulo crateArticuloFromResultSet(ResultSet rs) throws SQLException{
        Articulo articulo = new Articulo();
        articulo.setId_articulo(rs.getInt("id_articulo"));
        articulo.setCodigoA(rs.getString("codigoa"));
        articulo.setNombre_articulo(rs.getString("nombre_articulo"));
        articulo.setClasificacion(rs.getString("clasificacion"));
        articulo.setStock_actual(rs.getInt("stock_actual"));
        articulo.setStock_minimo(rs.getInt("stock_minimo"));
        articulo.setStock_maximo(rs.getInt("stock_maximo"));
        articulo.setCosto_promedio(rs.getDouble("costo_promedio"));

        ////crear instancia de bodega
        Bodega bodega = createBodegaFromResultSet(rs);
        //Establecer ña instancia de bodega en el articulo
        articulo.setBodegas(bodega);
        return articulo;
    }
}
