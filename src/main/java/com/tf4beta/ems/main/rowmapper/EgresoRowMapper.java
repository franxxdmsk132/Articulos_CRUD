package com.tf4beta.ems.main.rowmapper;

import com.tf4beta.ems.main.entity.Bodega;
import com.tf4beta.ems.main.entity.Egreso;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EgresoRowMapper implements RowMapper<Egreso> {

    @Override
    public Egreso mapRow(ResultSet rs, int rowNum) throws SQLException {

        Egreso egreso = new Egreso();
        egreso.setId_egreso_cab(rs.getInt("id_egreso_cab"));
        egreso.setFecha(rs.getString("fecha"));
        // Crear instancia de Bodega
        Bodega bodega = createBodegaFromResultSet(rs);
        // Establecer la instancia de Bodega en el Egreso
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

}
