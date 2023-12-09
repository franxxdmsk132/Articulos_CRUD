package com.tf4beta.ems.main.entity;

import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;
@Entity
@Table (name = "Bodega")
public class Bodega {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_bodega")
    private Integer codigo_bodega;
    @Column(name = "codigoB")
    private String codigoB;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ubicacion")
    private String ubicacion;

    public Bodega(Integer codigo_bodega, String codigoB, String nombre, String ubicacion) {
        this.codigo_bodega = codigo_bodega;
        this.codigoB = codigoB;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }


    public Bodega() {
    }

    @Override
    public String toString() {
        return "Bodega{" +
                "codigo_bodega=" + codigo_bodega +
                ", codigo='" + codigoB + '\'' +
                ", nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                '}';
    }

    public Integer getCodigo_bodega() {
        return codigo_bodega;
    }

    public void setCodigo_bodega(int codigo_bodega) {
        this.codigo_bodega = codigo_bodega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCodigoB() {
        return codigoB;
    }

    public void setCodigoB(String codigo) {
        this.codigoB = codigo;
    }
}
