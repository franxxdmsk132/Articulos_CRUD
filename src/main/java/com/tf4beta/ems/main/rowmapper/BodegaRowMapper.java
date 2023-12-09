package com.tf4beta.ems.main.rowmapper;
import com.tf4beta.ems.main.entity.Bodega;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BodegaRowMapper implements RowMapper<Bodega> {
    @Override
    public Bodega mapRow(ResultSet rs, int rowNum) throws SQLException {
        // TODO Auto-generated method stub

        Bodega bodega = new Bodega();
        bodega.setCodigo_bodega(rs.getInt("codigo_bodega"));
        bodega.setCodigoB(rs.getString("codigoB"));
        bodega.setNombre(rs.getString("nombre"));
        bodega.setUbicacion(rs.getString("ubicacion"));
        return bodega;
    }
}
