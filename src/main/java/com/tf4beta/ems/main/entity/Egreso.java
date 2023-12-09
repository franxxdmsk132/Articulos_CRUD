package com.tf4beta.ems.main.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Egreso")
public class Egreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_egreso_cab")
    private Integer id_egreso_cab;

    @Column (name = "fecha")
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "codigo_bodega")
    private Bodega bodega;


    public Egreso() {
    }

    public Egreso(Integer id_egreso_cab, String fecha, Bodega bodega) {
        this.id_egreso_cab = id_egreso_cab;
        this.fecha = fecha;
        this.bodega = bodega;
    }

    @Override
    public String toString() {
        return "Egreso{" +
                "id_egreso_cab=" + id_egreso_cab +
                ", fecha='" + fecha + '\'' +
                ", bodega=" + bodega +
                '}';
    }

    public Integer getId_egreso_cab() {
        return id_egreso_cab;
    }

    public void setId_egreso_cab(Integer id_egreso_cab) {
        this.id_egreso_cab = id_egreso_cab;
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
