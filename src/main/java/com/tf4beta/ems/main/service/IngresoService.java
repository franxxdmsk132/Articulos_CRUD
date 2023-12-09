package com.tf4beta.ems.main.service;


import com.tf4beta.ems.main.dao.IngresoDao;
import com.tf4beta.ems.main.entity.Ingreso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IngresoService {

    @Autowired
    private IngresoDao ingresoDao;

    public List<Ingreso> findAll() {
        return ingresoDao.findAll();
    }

    public List<Ingreso> findAllWithBodegaDetails() {
        return ingresoDao.findAllWhithBodegaDetaills();

    }

    public Ingreso findById(Integer id_ingreso_cab) {
        Ingreso Ingreso = ingresoDao.findById(id_ingreso_cab);
        return Ingreso;
    }

    public Ingreso findByIdAllDetails(Integer id_ingreso_cab) {
        Ingreso Ingreso = ingresoDao.findByIdAllDetails(id_ingreso_cab);
        return Ingreso;
    }

    public void save(Ingreso ingreso) {
        ingresoDao.save(ingreso);
    }

    public void update(Ingreso ingreso) {
        ingresoDao.update(ingreso);
    }

    public void delateByCodigo(Integer id_ingreso_cab) {

        ingresoDao.delate(id_ingreso_cab);
    }
}
