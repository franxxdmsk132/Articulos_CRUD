package com.tf4beta.ems.main.rowmapper;

import com.tf4beta.ems.main.entity.Bodega;
import com.tf4beta.ems.main.entity.Ingreso;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngresoRowMapper implements RowMapper<Ingreso> {

    @Override
    public Ingreso mapRow(ResultSet rs, int rowNum) throws SQLException {

        Ingreso ingreso = new Ingreso();
        ingreso.setId_ingresocab(rs.getInt("id_ingreso_cab"));
        ingreso.setFecha(rs.getString("fecha"));

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
}
