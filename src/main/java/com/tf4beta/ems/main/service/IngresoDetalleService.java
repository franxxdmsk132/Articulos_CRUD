package com.tf4beta.ems.main.service;


import com.tf4beta.ems.main.dao.IngresoDetallesDao;
import com.tf4beta.ems.main.entity.IngresoDetalles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IngresoDetalleService {

    @Autowired
    private IngresoDetallesDao ingresoDetallesDao;

    public List<IngresoDetalles> findAll(){
        return ingresoDetallesDao.findAllWithAllDetails();}

    public IngresoDetalles findById (Integer id_ingdetalle){
        IngresoDetalles ingresoDetalles = ingresoDetallesDao.findByIdWithAllDetails(id_ingdetalle);
        return ingresoDetalles;
    }
    public void save (IngresoDetalles ingresoDetalles){
        ingresoDetallesDao.save(ingresoDetalles);
    }
    public void update (IngresoDetalles ingresoDetalles){
        ingresoDetallesDao.update(ingresoDetalles);
    }
    public void deleteBycodigo(Integer id_ing_detalle){
        ingresoDetallesDao.deleteIngresoDetallesAndIngreso (id_ing_detalle);
    }
}

