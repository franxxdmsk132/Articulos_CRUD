package com.tf4beta.ems.main.entity;


import javax.persistence.*;

@Entity
@Table(name = "ingresos_cab")
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingreso_cab")
    private Integer id_ingresocab;


    @Column(name = "fecha")
    private String fecha;


    @ManyToOne
    @JoinColumn(name = "codigo_bodega")
    private Bodega bodega;

    public Ingreso() {
    }

    public Ingreso(Integer id_ingresocab, String fecha, Bodega bodega) {
        this.id_ingresocab = id_ingresocab;
        this.fecha = fecha;
        this.bodega = bodega;
    }

    public String toString(){
        return "Ingreso_cab{"+
                "id_ingreso_cab=" + id_ingresocab +
                " ,fecha= '" + fecha + '\'' +
                ",bodega=" + bodega +
                '}';

    }

    public Integer getId_ingresocab() {
        return id_ingresocab;
    }

    public void setId_ingresocab(Integer id_ingresocab) {
        this.id_ingresocab = id_ingresocab;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }
}
