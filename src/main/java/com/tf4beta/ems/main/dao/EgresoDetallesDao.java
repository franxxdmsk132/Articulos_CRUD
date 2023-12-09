package com.tf4beta.ems.main.dao;

import com.tf4beta.ems.main.entity.EgresoDetalles;
import com.tf4beta.ems.main.rowmapper.EgresoDetalleRowMapper;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EgresoDetallesDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //guardar
    /*
    public void save(EgresoDetalles egresoDetalles) {
        String sql = "INSERT INTO egreso_detalles (cantidad, costo, id_egreso_cab, id_articulo) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                egresoDetalles.getCantidad(),
                egresoDetalles.getCosto(),
                egresoDetalles.getEgreso().getId_egreso_cab(),
                egresoDetalles.getArticulo().getId_articulo()
        );
    }*/

    //Guardar y Actualizar Stock
    public void save(EgresoDetalles egresoDetalles) {
        // Obtener el stock actual
        String sqlStock = "SELECT stock_actual FROM articulo WHERE id_articulo = ?";
        int stockActual = jdbcTemplate.queryForObject(sqlStock, Integer.class, egresoDetalles.getArticulo().getId_articulo());

        // Restar la cantidad egresada del stock
        int nuevaCantidad = stockActual - egresoDetalles.getCantidad();

        // Actualizar el stock en la tabla de artículos
        String sqlUpdateStock = "UPDATE articulo SET stock_actual = ? WHERE id_articulo = ?";
        jdbcTemplate.update(sqlUpdateStock, nuevaCantidad, egresoDetalles.getArticulo().getId_articulo());

        // Insertar en la tabla egreso_detalles
        String sqlInsert = "INSERT INTO egreso_detalles (cantidad, costo, id_egreso_cab, id_articulo) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                sqlInsert,
                egresoDetalles.getCantidad(),
                egresoDetalles.getCosto(),
                egresoDetalles.getEgreso().getId_egreso_cab(),
                egresoDetalles.getArticulo().getId_articulo()
        );
    }

    //NO VALE
    public void delete(Integer id_egreso_detalles) {
        String sql = "DELETE egreso_detalles, egreso " +
                "FROM egreso_detalles " +
                "JOIN egreso " +
                "ON egreso.id_egreso_cab = egreso_detalles.id_egreso_cab " +
                "WHERE egreso_detalles.id_egreso_detalles = ?";
        jdbcTemplate.update(sql, id_egreso_detalles);
    }

    //Borrar por Id y que tambien se elimine el registro en la tabla de egreso
    public void deleteEgresoDetallesAndEgreso(int idEgresoDetalles) {
        // Obtén el ID de egreso_cab correspondiente a idEgresoDetalles
        String sqlSelectEgresoCab = "SELECT id_egreso_cab FROM egreso_detalles WHERE id_egreso_detalles = ?";
        Integer idEgresoCab = jdbcTemplate.queryForObject(sqlSelectEgresoCab, Integer.class, idEgresoDetalles);

        // Borra el registro de egreso_detalles
        String sqlDeleteEgresoDetalles = "DELETE FROM egreso_detalles WHERE id_egreso_detalles = ?";
        jdbcTemplate.update(sqlDeleteEgresoDetalles, idEgresoDetalles);

        // Borra el registro de egreso usando el ID obtenido anteriormente
        if (idEgresoCab != null) {
            String sqlDeleteEgreso = "DELETE FROM egreso WHERE id_egreso_cab = ?";
            jdbcTemplate.update(sqlDeleteEgreso, idEgresoCab);
        }
    }

    //actualizar
    public void update(EgresoDetalles egresoDetalles) {
        String sql = "UPDATE egreso_detalles SET cantidad = ?, costo = ?, id_egreso_cab = ?, id_articulo = ? WHERE id_egreso_detalles = ?";
        jdbcTemplate.update(
                sql,
                egresoDetalles.getCantidad(),
                egresoDetalles.getCosto(),
                egresoDetalles.getEgreso().getId_egreso_cab(),
                egresoDetalles.getArticulo().getId_articulo(),
                egresoDetalles.getId_egreso_detalles()
        );
    }

    //findById de Egresos
    public EgresoDetalles findById(int id_egreso_detalles) {
        String sql = "SELECT * FROM egreso_detalles WHERE id_egreso_detalles = ?";
        return jdbcTemplate.queryForObject(sql, new EgresoDetalleRowMapper(), id_egreso_detalles);
    }

    //findById para mostrar en la pantalla de detalles con todos los datos de todas las tablas
    public EgresoDetalles findByIdWithAllDetails(int id_egreso_detalles) {
        String sql = "SELECT `egreso_detalles`.*, `egreso`.*, `articulo`.*, `bodega`.*\n" +
                "FROM `egreso_detalles`\n" +
                "\tLEFT JOIN `egreso` ON `egreso_detalles`.`id_egreso_cab` = `egreso`.`id_egreso_cab`\n" +
                "\tLEFT JOIN `articulo` ON `egreso_detalles`.`id_articulo` = `articulo`.`id_articulo`\n" +
                "\tLEFT JOIN `bodega` ON `articulo`.`codigo_bodega` = `bodega`.`codigo_bodega`\n" +
                "WHERE `egreso_detalles`.`id_egreso_detalles` = ?";
        return jdbcTemplate.queryForObject(sql, new EgresoDetalleRowMapper(), id_egreso_detalles);
    }

    //meeh
    public List<EgresoDetalles> findAll() {
        String sql = "SELECT * FROM egreso_detalles";
        return jdbcTemplate.query(sql, new EgresoDetalleRowMapper());
    }

    //este es para el listado
    public List<EgresoDetalles> findAllWithAllDetails() {
        String sql = "SELECT `egreso_detalles`.*, `egreso`.*, `articulo`.*, `bodega`.*\n" +
                "FROM `egreso_detalles` \n" +
                "\tLEFT JOIN `egreso` ON `egreso_detalles`.`id_egreso_cab` = `egreso`.`id_egreso_cab` \n" +
                "\tLEFT JOIN `articulo` ON `egreso_detalles`.`id_articulo` = `articulo`.`id_articulo` \n" +
                "\tLEFT JOIN `bodega` ON `articulo`.`codigo_bodega` = `bodega`.`codigo_bodega`";
        return jdbcTemplate.query(sql, new EgresoDetalleRowMapper());
    }

}