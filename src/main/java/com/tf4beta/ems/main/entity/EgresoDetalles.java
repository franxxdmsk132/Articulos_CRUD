package com.tf4beta.ems.main.entity;

import javax.persistence.*;

@Entity
@Table(name = "egresoDetalles")
public class EgresoDetalles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_egreso_detalles")
    private Integer id_egreso_detalles;

    @Column (name = "cantidad")
    private Integer cantidad;

    @Column (name = "costo")
    private Double costo;

    @ManyToOne
    @JoinColumn(name = "id_egreso_cab")
    private Egreso egreso;

    @ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    public EgresoDetalles() {
    }

    public EgresoDetalles(Integer id_egreso_detalles, Integer cantidad, Double costo, Egreso egreso, Articulo articulo) {
        this.id_egreso_detalles = id_egreso_detalles;
        this.cantidad = cantidad;
        this.costo = costo;
        this.egreso = egreso;
        this.articulo = articulo;
    }

    @Override
    public String toString() {
        return "EgresoDetalles{" +
                "id_egreso_detalles=" + id_egreso_detalles +
                ", cantidad='" + cantidad + '\'' +
                ", costo=" + costo +
                ", egreso=" + egreso +
                ", articulo=" + articulo +
                '}';
    }

    public Integer getId_egreso_detalles() {
        return id_egreso_detalles;
    }

    public void setId_egreso_detalles(Integer id_egreso_detalles) {
        this.id_egreso_detalles = id_egreso_detalles;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Egreso getEgreso() {
        return egreso;
    }

    public void setEgreso(Egreso egreso) {
        this.egreso = egreso;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }


}