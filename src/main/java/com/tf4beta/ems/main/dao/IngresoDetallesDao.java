package com.tf4beta.ems.main.dao;


import com.tf4beta.ems.main.entity.IngresoDetalles;
import com.tf4beta.ems.main.rowmapper.IngresoDetalleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class IngresoDetallesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    ////Guardar
    public void save(IngresoDetalles ingresoDetalles) {
        String sqlStock = "SELECT stock_actual FROM articulo WHERE id_articulo = ?";
        int stockActual = jdbcTemplate.queryForObject(sqlStock, Integer.class, ingresoDetalles.getArticulo().getId_articulo());
        int nuevacantidad = stockActual + ingresoDetalles.getCantidad_ingresada();

        String sqlUpdateStock = "UPDATE articulo SET stock_actual = ? WHERE id_articulo = ?";
        jdbcTemplate.update(sqlUpdateStock, nuevacantidad, ingresoDetalles.getArticulo().getId_articulo());

        String sqlInsertar = "INSERT INTO ingreso_detalle(cantidad_ingresada,precio_compra,id_ingresoss_cab,id_articulo)VALUES (?,?,?,?)";
        jdbcTemplate.update(
                sqlInsertar,
                ingresoDetalles.getCantidad_ingresada(),
                ingresoDetalles.getPrecio_compra(),
                ingresoDetalles.getIngreso().getId_ingresocab(),
                ingresoDetalles.getArticulo().getId_articulo()
        );

    }
    ////Borrar por id

    public void deleteIngresoDetallesAndIngreso(int id_ingreso_detalles) {
        String sqlSelectIngresoCab = "SELECT id_ingresoss_cab FROM ingreso_detalle WHERE id_ingresos_detalle = ?";
        Integer idIngresosCab = jdbcTemplate.queryForObject(sqlSelectIngresoCab, Integer.class, id_ingreso_detalles);

        String sqlDeleteIngresoDetalles = "DELETE FROM ingreso_detalle WHERE id_ingresos_detalle =?";
        jdbcTemplate.update(sqlDeleteIngresoDetalles, id_ingreso_detalles);

        if (idIngresosCab != null) {
            String sqlDeleteIngreso = "DELETE FROM ingresos_cab WHERE id_ingreso_cab = ?";
            jdbcTemplate.update(sqlDeleteIngreso, idIngresosCab);
        }
    }


    ////Actualizar
    public void update(IngresoDetalles ingresoDetalles) {
        String sql = "UPDATE ingreso_detalle SET  cantidad_ingresada = ?, precio_compra = ?, id_articulo = ?, id_ingresoss_cab = ? WHERE id_ingresos_detalle = ?";
        jdbcTemplate.update(
                sql,
                ingresoDetalles.getCantidad_ingresada(),
                ingresoDetalles.getPrecio_compra(),
                ingresoDetalles.getArticulo().getId_articulo(),
                ingresoDetalles.getIngreso().getId_ingresocab(),
                ingresoDetalles.getId_Ingresos_detalle()

        );

    }

    public IngresoDetalles findById(int id_ingresos_detalle) {
        String sql = "SELECT * FROM ingreso_detalle WHERE id_ingresos_detalle = ?";
        return jdbcTemplate.queryForObject(sql, new IngresoDetalleRowMapper(), id_ingresos_detalle);
    }

    public IngresoDetalles findByIdWithAllDetails(int id_ingresos_detalle) {
        String sql = "SELECT `ingreso_detalle`.*, `ingresos_cab`.*, `articulo`.*, `bodega`.* " +
                "FROM `ingreso_detalle` " +
                "LEFT JOIN `ingresos_cab` ON `ingreso_detalle`.`id_ingresoss_cab` = `ingresos_cab`.`id_ingreso_cab` " +
                "LEFT JOIN `articulo` ON `ingreso_detalle`.`id_articulo` = `articulo`.`id_articulo`" +
                " LEFT JOIN `bodega` ON `articulo`.`codigo_bodega` = `bodega`.`codigo_bodega` " +
                "WHERE `ingreso_detalle`.`id_ingresos_detalle`=?";
        return jdbcTemplate.queryForObject(sql, new IngresoDetalleRowMapper(), id_ingresos_detalle);
    }

    public List<IngresoDetalles> findAll() {
        String sql = "SELECT * FROM ingreso_detalle";
        return jdbcTemplate.query(sql, new IngresoDetalleRowMapper());
    }

    public List<IngresoDetalles> findAllWithAllDetails() {
        String sql = "SELECT `ingreso_detalle`.*, `ingresos_cab`.*, `articulo`.*, `bodega`.*\n" +
                "FROM `ingreso_detalle`\n" +
                "\tLEFT JOIN `ingresos_cab` ON `ingreso_detalle`.`id_ingresoss_cab` = `ingresos_cab`.`id_ingreso_cab` \n" +
                "\tLEFT JOIN `articulo` ON `ingreso_detalle`.`id_articulo` = `articulo`.`id_articulo`\n" +
                "\t LEFT JOIN `bodega` ON `articulo`.`codigo_bodega` = `bodega`.`codigo_bodega`";
        return jdbcTemplate.query(sql, new IngresoDetalleRowMapper());
    }


}
