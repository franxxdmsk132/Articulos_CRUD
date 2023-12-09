package com.tf4beta.ems.main.dao;

import com.tf4beta.ems.main.entity.Bodega;

import com.tf4beta.ems.main.rowmapper.BodegaRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BodegaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Bodega> findAllBodegas() {
        String sql = "SELECT * FROM `bodega`";
        return jdbcTemplate.query(sql, new BodegaRowMapper());
    }

    public Bodega findByCodigoBodega(int id) {
        String sql = "SELECT * FROM bodega WHERE codigo_bodega = ? ";
        return jdbcTemplate.queryForObject(sql, new BodegaRowMapper(), id);
    }

    public void save(Bodega bodega) {
        String sql = "INSERT INTO bodega ( codigoB,nombre, ubicacion) VALUES (?,?, ?)";
        jdbcTemplate.update(sql, bodega.getCodigoB(),  bodega.getNombre(), bodega.getUbicacion());
    }

    public void update(Bodega bodega) {
        String sql = "UPDATE bodega  SET codigoB = ?, nombre= ? , ubicacion = ? WHERE codigo_bodega =? ";
        jdbcTemplate.update(sql, bodega.getCodigoB(), bodega.getNombre(), bodega.getUbicacion(), bodega.getCodigo_bodega());
    }

    public List<Bodega> findByNameB(String findName) {
        String sql = "SELECT * FROM bodega WHERE nombre LIKE ? OR ubicacion LIKE ? OR codigoB LIKE ?";
        String likeParameter = "%" + findName + "%";
        return jdbcTemplate.query(sql, new Object[]{likeParameter, likeParameter, likeParameter}, new BodegaRowMapper());
    }



    public void delete(int codigoBodega) {
        String sql = "DELETE FROM bodega WHERE codigo_bodega = " + codigoBodega;
        jdbcTemplate.update(sql);
    }
}
