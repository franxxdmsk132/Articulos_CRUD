package com.tf4beta.ems.main.entity;


import javax.persistence.*;

@Entity
@Table(name = "ingreso_detalle")
public class IngresoDetalles {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingresos_detalle")
    private Integer id_Ingresos_detalle;

    @Column(name = "cantidad_ingresada")
    private Integer cantidad_ingresada;

    @Column(name = "precio_compra")
    private double precio_compra;

    @ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name = "id_ingresoss_cab")
    private Ingreso ingreso;

    public IngresoDetalles() {
    }

    public IngresoDetalles(Integer id_Ingresos_detalle, Integer cantidad_ingresada, double precio_compra, Articulo articulo, Ingreso ingreso) {
        this.id_Ingresos_detalle = id_Ingresos_detalle;
        this.cantidad_ingresada = cantidad_ingresada;
        this.precio_compra = precio_compra;
        this.articulo = articulo;
        this.ingreso = ingreso;
    }

    @Override
    public String toString() {
        return "IngresoDetalles{" +
                "id_Ingresos_detalle=" + id_Ingresos_detalle +
                ", cantidad_ingresada='" + cantidad_ingresada + '\'' +
                ", precio_compra='" + precio_compra + '\'' +
                ", articulo=" + articulo +
                ", ingreso=" + ingreso +
                '}';
    }

    public Integer getId_Ingresos_detalle() {
        return id_Ingresos_detalle;
    }

    public void setId_Ingresos_detalle(Integer id_Ingresos_detalle) {
        this.id_Ingresos_detalle = id_Ingresos_detalle;
    }

    public Integer getCantidad_ingresada() {
        return cantidad_ingresada;
    }

    public void setCantidad_ingresada(Integer cantidad_ingresada) {
        this.cantidad_ingresada = cantidad_ingresada;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Ingreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
    }
}

