package com.tf4beta.ems.main.dao;

import com.tf4beta.ems.main.entity.Articulo;
import com.tf4beta.ems.main.entity.Egreso;
import com.tf4beta.ems.main.rowmapper.ArticleRowMapper;
import com.tf4beta.ems.main.rowmapper.EgresoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EgresoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Egreso egreso) {
        String sql="INSERT INTO egreso  (fecha, codigo_bodega) VALUES (?, ?)";
        jdbcTemplate.update
                (sql,
                        egreso.getFecha(),
                        egreso.getBodega().getCodigo_bodega()
                );
    }

    public void update(Egreso egreso){
        String sql = "UPDATE egreso SET  fecha = ?, codigo_bodega = ? WHERE egreso.id_egreso_cab = ?";
        jdbcTemplate.update(
                sql,
                egreso.getFecha(),
                egreso.getBodega().getCodigo_bodega(),
                egreso.getId_egreso_cab()
        );
    }
    public Egreso findById(int id_egreso_cab) {
        String sql = "SELECT e.* , b.nombre, b.codigoB FROM egreso e LEFT JOIN bodega b  ON e.codigo_bodega = b.codigo_bodega WHERE id_egreso_cab = ?";
        return jdbcTemplate.queryForObject(sql, new EgresoRowMapper(), id_egreso_cab);
    }

    public Egreso findByIdAllDetails(Integer id_egreso_cab){
        String sql = "SELECT e.* ,b.nombre , b.codigob, b.ubicacion FROM egreso e LEFT JOIN bodega b ON a.codigo_bodega = b.codigo_bodega WHERE id_egresos_cab = ?";
        return jdbcTemplate.queryForObject(sql, new EgresoRowMapper(),id_egreso_cab);

    }
    public List <Egreso> findAll(){
        String sql = "SELECT *  FROM egreso LEFT JOIN bodega  ON egreso.codigo_bodega = bodega.codigo_bodega ";
        return jdbcTemplate.query(sql, new EgresoRowMapper());
    }
    public List<Egreso> findAllWithBodegaDetails() {
        String sql = "SELECT e.*, b.codigo_bodega, b.nombre , b.codigob  FROM egreso e " +
                "LEFT JOIN bodega b ON e.codigo_bodega = b.codigo_bodega";

        return jdbcTemplate.query(sql, new EgresoRowMapper());
    }
    public void delete(Integer id_egreso_cb){
        String sql = "DELETE FROM egreso WHERE id_egreso_cab = '" + id_egreso_cb + "'";

        jdbcTemplate.update(sql);

    }

}
