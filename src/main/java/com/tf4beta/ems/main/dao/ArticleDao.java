package com.tf4beta.ems.main.dao;


import com.tf4beta.ems.main.entity.Articulo;
import com.tf4beta.ems.main.rowmapper.ArticleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ArticleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Articulo articulo) {
        String sql = "INSERT INTO articulo (codigoa, nombre_articulo, clasificacion, stock_maximo, stock_minimo, stock_actual, costo_promedio, codigo_bodega) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                articulo.getCodigoA(),
                articulo.getNombre_articulo(),
                articulo.getClasificacion(),
                articulo.getStock_maximo(),
                articulo.getStock_minimo(),
                articulo.getStock_actual(),
                articulo.getCosto_promedio(),
                articulo.getBodegas().getCodigo_bodega()
        );
    }

public void delete(String codigoA){
        String sql = "DELETE FROM articulo WHERE codigoa = '" + codigoA + "'";

        jdbcTemplate.update(sql);

}

    public void update(Articulo articulo) {
        String sql = "UPDATE articulo SET nombre_articulo = ? , clasificacion = ?, stock_maximo = ?, stock_minimo = ?, stock_actual = ?, costo_promedio = ?, codigo_bodega = ? WHERE codigoa = ?";
        jdbcTemplate.update(
                sql,
                articulo.getNombre_articulo(),
                articulo.getClasificacion(),
                articulo.getStock_maximo(),
                articulo.getStock_minimo(),
                articulo.getStock_actual(),
                articulo.getCosto_promedio(),
                articulo.getBodegas().getCodigo_bodega(),
                articulo.getCodigoA()
        );
    }



    public Articulo findById(String codigoA){
        String sql = "SELECT a.* ,b.nombre , b.codigob FROM articulo a LEFT JOIN bodega b ON a.codigo_bodega = b.codigo_bodega WHERE codigoA = ?";
        return jdbcTemplate.queryForObject(sql, new ArticleRowMapper(),codigoA);

    }
    public Articulo findByIdAllDetails(String codigoA){
        String sql = "SELECT a.* ,b.nombre , b.codigob, b.ubicacion FROM articulo a LEFT JOIN bodega b ON a.codigo_bodega = b.codigo_bodega WHERE codigoA = ?";
        return jdbcTemplate.queryForObject(sql, new ArticleRowMapper(),codigoA);

    }

    public List <Articulo> findAll(){
        String sql = "SELECT *  FROM articulo LEFT JOIN bodega  ON articulo.codigo_bodega = bodega.codigo_bodega ";
        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }
    public List<Articulo> findAllWithBodegaDetails() {
        String sql = "SELECT a.*, b.codigo_bodega, b.nombre , b.codigob  FROM articulo a " +
                "LEFT JOIN bodega b ON a.codigo_bodega = b.codigo_bodega";

        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }


    public List<Articulo>searchByName(String searchName){
        String sql = "SELECT * FROM articulo WHERE nombre_articulo LIKE '%" + searchName + "%'";
        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }

    public List<Articulo> search(Articulo articulo) {
        String sql = "SELECT * FROM articulo WHERE " +
                "codigoa LIKE ? AND " +
                "nombre_articulo LIKE ? AND " +
                "clasificacion LIKE ? AND " +
                "stock_actual = ? AND " +
                "stock_minimo = ? AND " +
                "stock_maximo = ? AND " +
                "costo_promedio = ? AND " +
                "codigo_bodega = ? AND " +
                "nombre LIKE ? AND " +
                "ubicacion LIKE ? AND " +
                "codigoB LIKE ?";

        return jdbcTemplate.query(sql, new Object[]{
                "%" + articulo.getCodigoA() + "%",
                "%" + articulo.getNombre_articulo() + "%",
                "%" + articulo.getClasificacion() + "%",
                articulo.getStock_actual(),
                articulo.getStock_minimo(),
                articulo.getStock_maximo(),
                articulo.getCosto_promedio(),
                articulo.getBodegas().getCodigo_bodega(),
                "%" + articulo.getBodegas().getNombre() + "%",
                "%" + articulo.getBodegas().getUbicacion() + "%",
                "%" + articulo.getBodegas().getCodigoB() + "%"
        }, new ArticleRowMapper());
    }

}
