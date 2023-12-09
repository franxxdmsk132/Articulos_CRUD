package com.tf4beta.ems.main.dao;


import com.tf4beta.ems.main.entity.Ingreso;
import com.tf4beta.ems.main.rowmapper.IngresoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class IngresoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void save (Ingreso ingreso){
        String sql = "INSERT INTO ingresos_cab (fecha, codigo_bodega) VALUES (?, ?)";
        jdbcTemplate.update
                (sql,
                        ingreso.getFecha(),
                        ingreso.getBodega().getCodigo_bodega()
                );
    }
    public void update (Ingreso ingreso) {
        String sql = "UPDATE ingresos_cab SET fecha = ?, codigo_bodega =? WHERE ingresos_cab.id_ingreso_cab = ? ";
        jdbcTemplate.update
                (sql,
                        ingreso.getFecha(),
                        ingreso.getBodega().getCodigo_bodega()
                );
    }


    public Ingreso findById(int id_ingreso_cab){
        String sql = "SELECT i.*, b.nombre,b.codigoB FROM ingreso_cab i LEFT JOIN bodega b ON i.codigo_bodega = b.codigo_bodega WHERE id_ingreso_cab = ?";
        return jdbcTemplate.queryForObject(sql, new IngresoRowMapper(), id_ingreso_cab);
    }
    public  Ingreso findByIdAllDetails(Integer id_ingresocab){
        String sql = "SELECT i.*,b.nombre, b.codigoB, b.ubicacion FROM ingresos_cab i LEFT JOIN bodega b ON i.codigo_bodega = b.codigo_bodega WHERE id_ingreso_cab = ?";
return jdbcTemplate.queryForObject(sql, new IngresoRowMapper(),id_ingresocab);

    }
    public List<Ingreso> findAll() {
        String sql = "SELECT * FROM ingresos_cab LEFT JOIN bodega ON ingresos_cab.codigo_bodega= bodega.codigo_bodega";
        return jdbcTemplate.query(sql, new IngresoRowMapper());

    }
    public List <Ingreso> findAllWhithBodegaDetaills(){
        String sql = "SELECT i.*, b.codigo_bodega, b.nombre , b.codigob FROM ingresos_cab i " +
                "LEFT JOIN bodega b ON i.codigo_bodega = b.codigo_bodega";
        return jdbcTemplate.query(sql, new IngresoRowMapper());
    }
    public void delate(Integer id_ingresocab){
        String sql = "DELETE FROM ingresos_cab WHERE id_ingresos_cab = '"+ id_ingresocab + "'";
        jdbcTemplate.update(sql);
    }
}
